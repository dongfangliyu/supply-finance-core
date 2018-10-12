package cn.fintecher.supply.finance.loan.manager.service.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.PledgeRegisterResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseAreaCore;
import cn.fintecher.supply.finance.loan.manager.service.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import cn.fintecher.supply.finance.loan.manager.service.register.service.PledgeRegisterService;
import cn.fintecher.supply.finance.loan.manager.service.register.service.RegisterCompanyUserService;
import com.google.common.base.Strings;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/17 0017下午 3:18
 */
@Service
public class PledgeRegisterServiceImpl implements PledgeRegisterService {

    @Autowired
    private FCRegisterCompanyCore fcRegisterCompanyCore;

    @Autowired
    private CompanyChainCore companyChainCore;

    @Autowired
    private FCBaseAreaCore fcBaseAreaCore;

    @Autowired
    private RegisterCompanyUserService registerCompanyUserService;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    @Value("${authorization.service.url}")
    private String authorizationServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }


    @Override
    public Message verificationMobile(String userName) {
        Message<Object> message = new Message<>();
        if (Strings.isNullOrEmpty(userName)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        boolean flag = false;
        CompanyUserEntity companyUserEntity = fcRegisterCompanyCore.searchCompanyUserByUserName(userName);
        if (ChkUtil.isEmpty(companyUserEntity)){
            flag = true;
        }
        message.setMessage(flag);
        return message;
    }

    @Override
    public synchronized Message createPledgeRegisterUser(RegisterUserForm registerUserForm) {
        Message message = new Message();
        // 判断验证码
        if(!registerUserForm.getVerifyCode().equals("1234")){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("验证码错误!");
            return message;
        }
        // 判断code是否存在
        RegisterUserEntity registerUserEntity = registerUserForm.getRegisterUserEntity();
        String registerCode = registerUserEntity.getRegisterCode();
        String userName = registerUserEntity.getUserName();
        if (Strings.isNullOrEmpty(registerCode) || Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(registerUserEntity.getEnterpriseType())){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("数据异常!");
            return message;
        }

        RegisterUserInfoEntity infoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        if (!ChkUtil.isEmpty(infoEntity)){
            infoEntity.setStatus(Constants.DATA_STATUS_DEL);
            fcRegisterCompanyCore.updateRegisteCompanyUserInfo(infoEntity);
        }

        // 判断手机号是否重复
        CompanyUserEntity companyUserEntity = fcRegisterCompanyCore.searchCompanyUserByUserName(userName);
        if (!ChkUtil.isEmpty(companyUserEntity)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("注册帐号已存在,请重新注册!");
            return message;
        }
        // 判断增加的手机号是不是链属名单上的手机号
        CompanyChainEntity companyChainEntity = new CompanyChainEntity();
        companyChainEntity.setMobile(registerUserEntity.getUserName());
        companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
        Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
        List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
        if (list1.size() > 0){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("手机号已经注册,请查证后再注册!");
            return message;
        }

        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisterUserByUserNameLast(userName);
        if(!ChkUtil.isEmpty(userEntity)){
            // 保证同一个registerCode只存在一个用户
            RegisterUserEntity userEntity1 = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
            if (!ChkUtil.isEmpty(userEntity1)){
                userEntity1.setStatus(Constants.DATA_STATUS_DEL);
                fcRegisterCompanyCore.updateRegisteCompanyUser(userEntity1);
            }

            RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(userEntity.getRegisterCode());
            registerUserEntity.setCurrentSteps("2");
            registerUserEntity.setCreateAt(new Date());
            registerUserEntity.setStatus(Constants.DATA_STATUS_NOL);
            registerUserEntity.setPid(userEntity.getPid());
            fcRegisterCompanyCore.createRegisteCompanyUser(registerUserEntity);

            registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

            // 另外存在的企业也要新增  文件新增
            if (!ChkUtil.isEmpty(userInfoEntity)){
                userInfoEntity.setRegisterCode(registerCode);
                fcRegisterCompanyCore.createRegisteCompanyUserInfo(userInfoEntity);
                List<RegisterFileEntity> registerFileEntities = fcRegisterFileCore.searchRegisterFileByOwnerId(userEntity.getPid().toString());
                if (registerFileEntities.size() > 0){
                    for (RegisterFileEntity entity:registerFileEntities) {
                        entity.setOwnerId(registerUserEntity.getPid().toString());
                        fcRegisterFileCore.saveRegisterFile(entity);
                    }
                }
            }
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }else {
            // 没有注册的用户数据  走新增流程
            registerUserEntity.setCurrentSteps("2");
            registerUserEntity.setCreateAt(new Date());
            registerUserEntity.setStatus(Constants.DATA_STATUS_NOL);
            fcRegisterCompanyCore.createRegisteCompanyUser(registerUserEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);

        }
        return message;
    }

    @Override
    public Message updatePledgeRegisterUser(RegisterUserEntity registerUserEntity) {
        Message message = new Message();
        String registerCode = registerUserEntity.getRegisterCode();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }
        // 判断步骤是否为2
//        if(!"2".equals(registerUserEntity.getCurrentSteps())){
//            message.setMessage("数据异常!");
//            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
//            return message;
//        }

        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        // 修改状态
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(registerUserEntity.getPassword()));
        userEntity.setCurrentSteps("3");
        userEntity.setUpdateAt(new Date());
        message = fcRegisterCompanyCore.updateRegisteCompanyUser(userEntity);
        return message;
    }

    // 查询企业的信息
    @Override
    public Message searchPledgeRegisterPrimaryInfo(String registerCode) {
        Message message = new Message();
        PledgeRegisterResponse pledgeRegisterResponse = new PledgeRegisterResponse();
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        if (Strings.isNullOrEmpty(registerCode)){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }

        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        if (!ChkUtil.isEmpty(userEntity)){
            if (!Strings.isNullOrEmpty(userEntity.getUserName())){
                pledgeRegisterResponse.setUserName(userEntity.getUserName());
            }
        }

        List<RegisterFileEntity> registerFileEntities = fcRegisterFileCore.searchRegisterFileByOwnerId(userEntity.getPid().toString());
        if (registerFileEntities.size() > 0){
            pledgeRegisterResponse.setRegisterFileEntityList(registerFileEntities);
        }

        pledgeRegisterResponse.setUserInfoEntity(userInfoEntity);

        message.setMessage(pledgeRegisterResponse);
        return message;
    }

    @Override
    public synchronized Message submitPledgeRegisterBaseInfo(RegisterCompanyForm registerCompanyForm) {
        Message message = new Message();
        String registerCode = registerCompanyForm.getRegisterCode();
        if (Strings.isNullOrEmpty(registerCode)){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        // 查询用户注册信息
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        RegisterUserInfoEntity registerUserInfoEntity = registerCompanyForm.getRegisterUserInfoEntity();
        if (ChkUtil.isEmpty(registerUserInfoEntity) || Strings.isNullOrEmpty(registerUserInfoEntity.getName())){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }

        // 判断该企业名称是否注册成功
        CompanyEnterpriseEntity companyEnterpriseEntity = fcRegisterCompanyCore.searchCompanyByName(registerUserInfoEntity.getName());
        if (!ChkUtil.isEmpty(companyEnterpriseEntity)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("企业已经注册,请查证后再注册!");
            return message;
        }

        // 判断增加的手机号是不是链属名单上的企业名称
        CompanyChainEntity companyChainEntity = new CompanyChainEntity();
        companyChainEntity.setCompanyName(registerUserInfoEntity.getName());
        companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
        Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
        List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
        if (list1.size() > 0){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("企业已经注册,请查证后再注册!");
            return message;
        }
        // 根据区域code 获取区域名称
        if(null!=registerUserInfoEntity.getLegalAddressProvince()) {
            Long legalAddressProvince = registerUserInfoEntity.getLegalAddressProvince();
            registerUserInfoEntity.setLegalAddressProvinceName(fcBaseAreaCore.getAreaNameById(legalAddressProvince));
        }
        if(null!=registerUserInfoEntity.getLegalAddressCity()) {
            Long legalAddressCity = registerUserInfoEntity.getLegalAddressCity();
            registerUserInfoEntity.setLegalAddressCityName(fcBaseAreaCore.getAreaNameById(legalAddressCity));
        }

        // 查询企业信息
        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        if (ChkUtil.isEmpty(userInfoEntity)){
            // 不存在企业信息 新增
            registerUserInfoEntity.setCreateAt(new Date());
            registerUserInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
            registerUserInfoEntity.setState("0");
            fcRegisterCompanyCore.createRegisteCompanyUserInfo(registerUserInfoEntity);
        }else {
            // 存在 更新
            registerUserInfoEntity.setPid(userInfoEntity.getPid());
            registerUserInfoEntity.setUpdateAt(new Date());
            fcRegisterCompanyCore.updateRegisteCompanyUserInfo(registerUserInfoEntity);
        }
        // 更新步骤
        registerUserEntity.setCurrentSteps("4");
        registerUserEntity.setUpdateAt(new Date());
        fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public synchronized Message submitPledgeRegisterDetailInfo(RegisterCompanyForm registerCompanyForm) {
        Message message = new Message();
        String registerCode = registerCompanyForm.getRegisterCode();
        if (Strings.isNullOrEmpty(registerCode)){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        // 查询用户注册信息
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        RegisterUserInfoEntity registerUserInfoEntity = registerCompanyForm.getRegisterUserInfoEntity();
        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCompanyForm.getRegisterCode());

        // 判断手机号是否重复
        CompanyUserEntity userEntity = fcRegisterCompanyCore.searchCompanyUserByUserName(registerUserEntity.getUserName());
        if (!ChkUtil.isEmpty(userEntity)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("注册成功,请登陆!");
            return message;
        }

        if (ChkUtil.isEmpty(registerUserInfoEntity) || ChkUtil.isEmpty(userInfoEntity) || Strings.isNullOrEmpty(registerUserInfoEntity.getName())){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }

        if(null!=registerUserInfoEntity.getRegisteredAddressCounty()) {
            Long RegisteredAddressCounty = registerUserInfoEntity.getRegisteredAddressCounty();
            registerUserInfoEntity.setRegisteredAddressCountyName(fcBaseAreaCore.getAreaNameById(RegisteredAddressCounty));
        }
        if(null!=registerUserInfoEntity.getRegisteredAddressCity()) {
            Long registeredAddressCity = registerUserInfoEntity.getRegisteredAddressCity();
            registerUserInfoEntity.setRegisteredAddressCityName(fcBaseAreaCore.getAreaNameById(registeredAddressCity));
        }
        if(null!=registerUserInfoEntity.getRegisteredAddressProvince()){
            Long registeredAddressProvince = registerUserInfoEntity.getRegisteredAddressProvince();
            registerUserInfoEntity.setRegisteredAddressProvinceName(fcBaseAreaCore.getAreaNameById(registeredAddressProvince));
        }
        if(null!=registerUserInfoEntity.getOperatingAddressCounty()){
            Long operatingAddressCounty = registerUserInfoEntity.getOperatingAddressCounty();
            registerUserInfoEntity.setOperatingAddressCountyName(fcBaseAreaCore.getAreaNameById(operatingAddressCounty));
        }
        if(null!=registerUserInfoEntity.getOperatingAddressCity()){
            Long operatingAddressCity =registerUserInfoEntity.getOperatingAddressCity();
            registerUserInfoEntity.setOperatingAddressCityName(fcBaseAreaCore.getAreaNameById(operatingAddressCity));
        }
        if(null!=registerUserInfoEntity.getOperatingAddressProvince()){
            Long operatingAddressProvince = registerUserInfoEntity.getOperatingAddressProvince();
            registerUserInfoEntity.setOperatingAddressProvinceName(fcBaseAreaCore.getAreaNameById(operatingAddressProvince));
        }

        registerUserInfoEntity.setPid(userInfoEntity.getPid());
        // 更新企业信息
        fcRegisterCompanyCore.updateRegisteCompanyUserInfo(registerUserInfoEntity);
        // 更新步骤
        registerUserEntity.setCurrentSteps("5");
        message = fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
        return message;
    }

    @Override
    public synchronized Message submitSuccess(RegisterCompanyForm registerCompanyForm) {
        Message message = new Message();
        String registerCode = registerCompanyForm.getRegisterCode();
        if (Strings.isNullOrEmpty(registerCode)){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        // 查询用户注册信息
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        RegisterUserInfoEntity registerUserInfoEntity = registerCompanyForm.getRegisterUserInfoEntity();

        // 判断手机号是否重复
        CompanyUserEntity userEntity = fcRegisterCompanyCore.searchCompanyUserByUserName(registerUserEntity.getUserName());
        if (!ChkUtil.isEmpty(userEntity)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("注册帐号已存在,请勿重复注册!");
            return message;
        }

        // 保存企业信息
        message = submitPledgeRegisterDetailInfo(registerCompanyForm);
        // 获取注册企业
        registerUserInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);

        RegisterUserEntity entity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        if (!"5".equals(entity.getCurrentSteps())){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }

        // 生成真正的企业信息
        if (MsgCodeConstant.ERR_MSG_SUCCESS == message.getCode()) {
            // 企业
            Long pid = registerCompanyUserService.createCompanyEnterpriseEntity(registerUserInfoEntity);
            // 企业详情
            registerCompanyUserService.createCompanyEnterpriseInfoEntity(registerUserInfoEntity, pid);
            // 企业人员关系
            registerCompanyUserService.createCompanyOperationEntity(registerUserInfoEntity, pid);
            // 银行
            registerCompanyUserService.createBaseBankInfoEntity(registerUserInfoEntity, pid);

            // 注册文件
            List<RegisterFileEntity> list = fcRegisterFileCore.searchRegisterFileByOwnerId(registerUserEntity.getPid().toString());
            for (RegisterFileEntity fileEntity : list) {
                CompanyFileEntity companyFileEntity = new CompanyFileEntity();
                companyFileEntity.setFileName(fileEntity.getFileName());
                companyFileEntity.setCategory(fileEntity.getCategory());
                companyFileEntity.setFileSuffix(fileEntity.getFileSuffix());
                companyFileEntity.setFullPath(fileEntity.getFullPath());
                companyFileEntity.setPath(fileEntity.getPath());
                companyFileEntity.setGroup(fileEntity.getFileGroup());
                companyFileEntity.setOwnerId(pid.toString());
                companyFileEntity.setUpdateAt(fileEntity.getUpdateAt());
                companyFileEntity.setCreateAt(fileEntity.getCreateAt());
                companyFileEntity.setStatus(fileEntity.getStatus());
                fcEnterpriseFinancialCore.insert(companyFileEntity);
            }

            // 企业用户
            CompanyUserEntity companyUserEntity = new CompanyUserEntity();
            companyUserEntity.setName(registerUserInfoEntity.getName());
            companyUserEntity.setUserName(registerUserEntity.getUserName());
            companyUserEntity.setPassword(registerUserEntity.getPassword());
            companyUserEntity.setEnterpriseId(pid);
            companyUserEntity.setType(registerUserEntity.getEnterpriseType());
            companyUserEntity.setSalt(registerUserEntity.getSalt());
            companyUserEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
            companyUserEntity.setStatus(Constants.DATA_STATUS_NOL);
            fcRegisterCompanyCore.saveCompanyUserEntity(companyUserEntity);

            // 修改步骤
            entity.setCurrentSteps("6");
            entity.setUpdateAt(new Date());
            fcRegisterCompanyCore.updateRegisteCompanyUser(entity);
            // oauth上生成企业用户帐号
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", companyUserEntity.getUserName());
            jsonObject.put("password", companyUserEntity.getPassword());
            if (!Strings.isNullOrEmpty(companyUserEntity.getName())) {
                jsonObject.put("name", companyUserEntity.getName());
            }
            message = this.restTemplate.postForObject(authorizationServicePath + "/oauth/createUser", jsonObject, Message.class);
            if (MessageType.MSG_SUCCESS.equals(message.getCode())) {
                companyUserEntity.setPassword(encoder.encode(companyUserEntity.getPassword()));
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            } else {
                return message;
            }

        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
        }
        return message;
    }


}
