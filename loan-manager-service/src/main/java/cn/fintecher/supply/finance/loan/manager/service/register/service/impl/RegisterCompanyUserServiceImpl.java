package cn.fintecher.supply.finance.loan.manager.service.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.ConfirmCompanyResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CurrentStepResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseAreaCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessReceivableCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.service.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import cn.fintecher.supply.finance.loan.manager.service.register.service.RegisterCompanyUserService;
import com.google.common.base.Strings;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 5:54
 */
@Service
public class RegisterCompanyUserServiceImpl implements RegisterCompanyUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCompanyUserServiceImpl.class);

    @Autowired
    private FCRegisterCompanyCore fcRegisterCompanyCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCBaseAreaCore fcBaseAreaCore;

    @Autowired
    private BusinessOrderService businessOrderService;

    @Autowired
    private BusinessReceivableCore businessReceivableCore;

    @Autowired
    private CompanyChainCore companyChainCore;

    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    @Autowired
    private FCCompanyUserCore fcCompanyUserCore;



    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    @Value("${authorization.service.url}")
    private String authorizationServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }


    public Message selectRegisteCurrentStep(String registerCode,String enterpriseType) {
        Message message = new Message();
        CurrentStepResponse currentStepResponse = new CurrentStepResponse();
        Integer currentSteps = 1;
        Message<RegisterUserEntity> userEntityMessage = fcRegisterCompanyCore.selectRegisteCurrentStep(registerCode);
        RegisterUserEntity userEntity = userEntityMessage.getMessage();
        if (userEntity == null){
            currentStepResponse.setCurrentSteps(currentSteps.toString());
        }else {
            currentStepResponse.setCurrentSteps(userEntity.getCurrentSteps());
        }
        if (enterpriseType.equals("1")){
            if (userEntity != null){
                if (userEntity.getCurrentSteps().equals("3")) {
                    CompanyChainEntity companyChainEntity = new CompanyChainEntity();
                    companyChainEntity.setMobile(userEntity.getUserName());
                    companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                    Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
                    List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(), CompanyChainEntity.class);
                    if (list1.size() > 0) {
                        currentStepResponse.setName(list1.get(0).getCompanyName());
                    }
                }
            }
        }

        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(currentStepResponse);
        return message;
    }

    public Message createRegisteCompanyUser(RegisterUserForm registerUserForm) {
        Message message = new Message();
        if(!registerUserForm.getVerifyCode().equals("1234")){
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("验证码错误!");
            return message;
        }

        RegisterUserEntity registerUserEntity = registerUserForm.getRegisterUserEntity();
        if (Strings.isNullOrEmpty(registerUserEntity.getRegisterCode())){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        // 判断核心企业注册的手机号是不是供应商的
        if (null != registerUserEntity){
            if (registerUserEntity.getEnterpriseType().equals("0")){
                // 判断供应商(宝哥)
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

            }else {
                // 判断核心企业是否存在
                List<CompanyUserEntity> companyUserEntities = fcCompanyUserCore.findCompanyUserByNameAndEnterpriseType(registerUserEntity.getUserName(), "0");
                if (companyUserEntities.size() > 0){
                    message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
                    message.setMessage("手机号已经注册,请查证后再注册!");
                    return message;
                }

            }
        }


        // 修改状态
        String userName = registerUserEntity.getUserName();
        String registerCode = registerUserEntity.getRegisterCode();
        if (!Strings.isNullOrEmpty(userName) && !Strings.isNullOrEmpty(registerCode)){
            // 根据username查询手机号是否重复
            List<RegisterUserEntity> list = fcRegisterCompanyCore.searchRegisterUserByUserName(userName);
            if (list.size() > 0){
                for (RegisterUserEntity r:list) {
                    if (!Strings.isNullOrEmpty(r.getRegisterCode())){
                        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(r.getRegisterCode());
                        if (null != userInfoEntity){
                            AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByRegisterId(userInfoEntity.getPid().toString());
                            if (auditRegisterEntity != null){
                                message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                                message.setMessage("手机号已注册!");
                                return message;
                            }
                        }

                    }
                }
            }
        }

        if (null != registerUserEntity){
            if (registerUserEntity.getEnterpriseType().equals("1")){
                CompanyChainEntity companyChainEntity = new CompanyChainEntity();
                companyChainEntity.setMobile(registerUserEntity.getUserName());
                companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
                List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
                if (ChkUtil.isEmpty(list1)){
                    message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                    message.setMessage("对不起,您没有权限注册供应商!");
                    return message;
                }else {
                    for (int i = 0; i < list1.size();i++) {
                        BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
                        businessReceivableEntity.setSupplierId(list1.get(i).getPid());
                        businessReceivableEntity.setState("1");
                        businessReceivableEntity.setStatus(Constants.DATA_STATUS_NOL);
                        Message message1 = businessReceivableCore.selectByReceivable(businessReceivableEntity);
                        List<BusinessReceivableEntity> businessReceivableEntities = JSONUtil.toList(message1.getMessage(), BusinessReceivableEntity.class);
                        if (businessReceivableEntities.size() > 0){
                            break;
                        }
                        if (i == list1.size()-1){
                            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                            message.setMessage("对不起,您没有权限注册供应商!");
                            return message;
                        }
                    }
                }
            }else {
                CompanyChainEntity companyChainEntity = new CompanyChainEntity();
                companyChainEntity.setMobile(registerUserEntity.getUserName());
                companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
                List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
                if (list1.size() > 0){
                    message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                    message.setMessage("对不起,您注册的手机号为供应商手机号,请查证后再注册!");
                    return message;
                }
            }
        }


        registerUserEntity.setCurrentSteps("2");
        registerUserEntity.setCreateAt(new Date());
        registerUserEntity.setStatus("NOL");
        message = fcRegisterCompanyCore.createRegisteCompanyUser(registerUserEntity);
        return message;
    }

    public Message updateRegisteCompanyUser(RegisterUserEntity registerUserEntity) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerUserEntity.getRegisterCode())){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerUserEntity.getRegisterCode());

        // 修改状态
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!Strings.isNullOrEmpty(registerUserEntity.getPassword())){
            userEntity.setPassword(encoder.encode(registerUserEntity.getPassword()));
        }
        userEntity.setCurrentSteps("3");
        userEntity.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        message = fcRegisterCompanyCore.updateRegisteCompanyUser(userEntity);
        
        return message;
    }

    @Override
    public Message submitRegisteCompanyBaseInfo(RegisterCompanyForm registerCompanyForm) {
        Message message = new Message();



        message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
        if (Strings.isNullOrEmpty(registerCompanyForm.getRegisterCode())){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        // 修改状态  需要根据静默注册的code查询
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCompanyForm.getRegisterCode());
        RegisterUserInfoEntity registerUserInfoEntity = registerCompanyForm.getRegisterUserInfoEntity();
        if (null != registerUserInfoEntity && !Strings.isNullOrEmpty(registerUserInfoEntity.getName())){

            List<AuditRegisterEntity> list = fcAuditRegisterCore.searchAuditRegisterByName(registerUserInfoEntity.getName());

            CompanyEnterpriseEntity companyEnterpriseEntity = fcRegisterCompanyCore.searchCompanyByName(registerUserInfoEntity.getName());

            CompanyChainEntity companyChainEntity = new CompanyChainEntity();
            if(registerUserInfoEntity.getEnterpriseType().equals("1")){
                companyChainEntity.setCompanyName(registerUserInfoEntity.getName());
                companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message message1 = companyChainCore.selectByChain(companyChainEntity);
                List<CompanyChainEntity> companyChainEntities = JSONUtil.toList(message1.getMessage(), CompanyChainEntity.class);
                if (ChkUtil.isEmpty(companyChainEntities)){
                    message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                    message.setMessage("注册供应商企业名称不存在!");
                    return message;
                }
            }

            if (list.size() > 0 || null != companyEnterpriseEntity){
                message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                message.setMessage("该企业已注册!");
                return message;
            }

            registerUserInfoEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
            registerUserInfoEntity.setStatus("NOL");
            registerUserInfoEntity.setState("0");
            message = fcRegisterCompanyCore.createRegisteCompanyUserInfo(registerUserInfoEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        if (null != registerUserEntity && MsgCodeConstant.ERR_MSG_SUCCESS == message.getCode()){
            registerUserEntity.setCurrentSteps("4");
            fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
        }
        return message;
    }

    @Override
    public Message submitRegisteCompanyDetailInfo(RegisterCompanyForm registerCompanyForm) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
        if (Strings.isNullOrEmpty(registerCompanyForm.getRegisterCode())){
            // 请登陆
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        // 修改状态  需要根据静默注册的code查询
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCompanyForm.getRegisterCode());
        RegisterUserInfoEntity registerUserInfoEntity = registerCompanyForm.getRegisterUserInfoEntity();
        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCompanyForm.getRegisterCode());

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

        if (null != registerUserInfoEntity && null != userInfoEntity){
            registerUserInfoEntity.setPid(userInfoEntity.getPid());
            message = fcRegisterCompanyCore.updateRegisteCompanyUserInfo(registerUserInfoEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        if (null != registerUserEntity && MsgCodeConstant.ERR_MSG_SUCCESS == message.getCode()){
            registerUserEntity.setCurrentSteps("5");
            fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
        }
        return message;
    }

    @Override
    public Message searchRegisteCompanyPrimaryInfo(String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        CompanyPrimaryInfoResponse response = new CompanyPrimaryInfoResponse();
        response = fcRegisterCompanyCore.searchRegisteCompanyPrimaryInfo(registerCode);
        message.setMessage(response);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message resetRegisteCompanyData(String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        // 根据Code查询注册企业信息
        RegisterUserInfoEntity entity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        entity.setStatus("DEL");
        message = fcRegisterCompanyCore.updateRegisteCompanyUserInfo(entity);
        message.setMessage(entity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        // 修改状态
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);
        if (null != registerUserEntity && message.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
            registerUserEntity.setCurrentSteps("3");
            message = fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }

        return message;
    }

    @Override
    public synchronized Message submitRegisteCompany(String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        if (null != registerUserEntity){
            List<RegisterUserEntity> list = fcRegisterCompanyCore.searchRegisterUserByUserName(registerUserEntity.getUserName());
            if (list.size() > 0){
                for (RegisterUserEntity r:list) {
                    RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(r.getRegisterCode());
                    if (null != userInfoEntity){
                        AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByRegisterId(userInfoEntity.getPid().toString());
                        if (auditRegisterEntity != null){
                            message.setMessage("手机号已注册!");
                            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                            return message;
                        }
                    }
                }
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        // 修改状态
        RegisterUserInfoEntity entity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        if (null != entity){
            entity.setState("1");
            entity.setAttemptsNum(5l);
            String currentTime = DateUtil.getCurrentTime();
            try {
                String lastTime = DateUtil.getLastTime(currentTime, 1, 3, null);
                Date date = DateUtil.formatDate(lastTime, null);
                entity.setMoneyTime(date);
                message = fcRegisterCompanyCore.updateRegisteCompanyUserInfo(entity);
                if (null != registerUserEntity && message.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    registerUserEntity.setCurrentSteps("6");
                    message = fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
                }
                message.setMessage(entity);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);

                // 保存审核信息
                AuditRegisterEntity auditRegisterEntity = new AuditRegisterEntity();
                auditRegisterEntity.setName(entity.getName());
                auditRegisterEntity.setRegisterId(entity.getPid().toString());
                auditRegisterEntity.setEnterpriseType(entity.getEnterpriseType());
                auditRegisterEntity.setState("0");
                auditRegisterEntity.setStatus("NOL");
                auditRegisterEntity.setCreateAt(new Date());
                fcAuditRegisterCore.saveAuditRegisterEntity(auditRegisterEntity);
            } catch (ParseException e) {
                e.printStackTrace();
                message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            }
        }

        return message;
    }

    @Override
    public Message showRegisteCompanyHintMsg(String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        CompanyPrimaryInfoResponse response = fcRegisterCompanyCore.searchRegisteCompanyPrimaryInfo(registerCode);
        RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        if (null != userInfoEntity){
            AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByRegisterId(userInfoEntity.getPid().toString());
            if (null != auditRegisterEntity){
                response.setState(auditRegisterEntity.getState());
            }
        }
        if (null != response){
            message.setMessage(response);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        return message;
    }

    @Override
    public synchronized Message confirmRegisteCompany(String registerCode, int accountAmount) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }
        ConfirmCompanyResponse response = new ConfirmCompanyResponse();
        // 查询注册企业信息
        RegisterUserInfoEntity registerUserInfoEntity = fcRegisterCompanyCore.searchRegisteCompanyUserInfoByCode(registerCode);
        RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        if (registerUserInfoEntity.getAttemptsNum().equals(0)){
            response.setAttemptsNum((long) 0);
            response.setType(false);
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SEARCH);
            message.setMessage(response);
            return message;
        }


        CompanyUserEntity byUserName = fcRegisterCompanyCore.searchCompanyUserByUserName(registerUserEntity.getUserName());
        if (ChkUtil.isEmpty(byUserName)){
            if (accountAmount == registerUserInfoEntity.getAccountAmount()){
                // 成功  添加正式用户的逻辑  bank
                response.setType(true);
                // 企业
                Long pid = createCompanyEnterpriseEntity(registerUserInfoEntity);
                // 企业详情
                createCompanyEnterpriseInfoEntity(registerUserInfoEntity,pid);
                // 企业人员关系
                createCompanyOperationEntity(registerUserInfoEntity,pid);
                // 银行
                createBaseBankInfoEntity(registerUserInfoEntity,pid);
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
                    companyFileEntity.setCreateAt(fileEntity.getCreateAt());
                    companyFileEntity.setUpdateAt(fileEntity.getUpdateAt());
                    companyFileEntity.setStatus(fileEntity.getStatus());
                    fcEnterpriseFinancialCore.insert(companyFileEntity);
//                    fileEntity.setOwnerId(pid.toString());
//                    fcRegisterFileCore.updateRegisterFile(fileEntity);
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
                companyUserEntity.setStatus("NOL");
                fcRegisterCompanyCore.saveCompanyUserEntity(companyUserEntity);

                // 查询链属
                if (registerUserInfoEntity.getEnterpriseType().equals("1")){
                    BusinessReceivableEntity receivableEntity = new BusinessReceivableEntity();
                    receivableEntity.setSupplierName(registerUserInfoEntity.getName());
                    receivableEntity.setStatus(Constants.DATA_STATUS_NOL);
                    Message selectByChain = businessReceivableCore.selectByReceivable(receivableEntity);
                    List<BusinessReceivableEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),BusinessReceivableEntity.class);
                    if (null != list1){
                        for (BusinessReceivableEntity businessReceivableEntity: list1) {
                            createBusinessOrder(businessReceivableEntity,pid);
                        }
                    }
                }

                // oauth上生成企业用户帐号
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username",companyUserEntity.getUserName());
                jsonObject.put("password",companyUserEntity.getPassword());
                if (!Strings.isNullOrEmpty(companyUserEntity.getName())){
                    jsonObject.put("name",companyUserEntity.getName());
                }
                message = this.restTemplate.postForObject(authorizationServicePath+"/oauth/createUser",jsonObject,Message.class);
                if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                    companyUserEntity.setPassword(encoder.encode(companyUserEntity.getPassword()));

                }else{
                    return message;
                }
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                if (null != registerUserEntity && message.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    registerUserEntity.setCurrentSteps("7");
                    message = fcRegisterCompanyCore.updateRegisteCompanyUser(registerUserEntity);
                }
                // 查询链属  修改状态为已注册
                CompanyChainEntity companyChainEntity = new CompanyChainEntity();
                companyChainEntity.setMobile(registerUserEntity.getUserName());
                companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
                List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
                for (CompanyChainEntity entity:list1) {
                    entity.setState("1");
                    companyChainCore.updateChain(entity);
                }
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;


            }else {
                // 失败
                if (!registerUserInfoEntity.getAttemptsNum().equals(0)){
                    long l = registerUserInfoEntity.getAttemptsNum() - 1;
                    registerUserInfoEntity.setAttemptsNum(l);
                    fcRegisterCompanyCore.updateRegisteCompanyUserInfo(registerUserInfoEntity);
                    response.setAttemptsNum(l);
                    response.setType(false);
                    message.setCode(MsgCodeConstant.ERR_ENTERPRISE_CREDIT_UPDATE);
                    message.setMessage(response);
                    return message;
                }else {
                    // 尝试次数用完
                    response.setAttemptsNum((long) 0);
                    response.setType(false);
                    message.setMessage(response);
                    return message;
                }
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_SUCCESS);
            message.setMessage("您已经注册成功,请登陆!");
            return message;
        }
    }

    @Override
    public Long createCompanyEnterpriseEntity(RegisterUserInfoEntity registerUserInfoEntity){
        CompanyEnterpriseEntity enterpriseEntity = new CompanyEnterpriseEntity();
        enterpriseEntity.setName(registerUserInfoEntity.getName());
        enterpriseEntity.setType(registerUserInfoEntity.getType());
        enterpriseEntity.setEnpsLicense(registerUserInfoEntity.getEnpsLicense());
        enterpriseEntity.setEnpsCredit(registerUserInfoEntity.getEnpsCredit());
        enterpriseEntity.setLegalAddress(registerUserInfoEntity.getLegalAddress());
        enterpriseEntity.setController(registerUserInfoEntity.getController());
        enterpriseEntity.setFillPersion(registerUserInfoEntity.getFillPersion());
        enterpriseEntity.setEstablishTime(registerUserInfoEntity.getEstablishTime());
        enterpriseEntity.setRealRegisteredPrincipal(registerUserInfoEntity.getRealRegisteredPrincipal());
        enterpriseEntity.setRegisteredPrincipal(registerUserInfoEntity.getRegisteredPrincipal());
        enterpriseEntity.setRegisteredAddressDetail(registerUserInfoEntity.getRegisteredAddressDetail());
        enterpriseEntity.setOperatingAddressDetail(registerUserInfoEntity.getOperatingAddressDetail());
        enterpriseEntity.setLoanNum(registerUserInfoEntity.getLoanNum());
        enterpriseEntity.setEnterpriseType(registerUserInfoEntity.getEnterpriseType());
        enterpriseEntity.setState("10");
        enterpriseEntity.setSubmitTime(registerUserInfoEntity.getSubmitTime());
        enterpriseEntity.setCreditNumber(registerUserInfoEntity.getCreditNumber());
        enterpriseEntity.setCreditStatus((long) 0);
        enterpriseEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        enterpriseEntity.setStatus("NOL");
        // 保存
        Message entity = fcRegisterCompanyCore.saveEnterpriseEntity(enterpriseEntity);
        Long pid = Long.valueOf(String.valueOf(entity.getMessage()));
        return pid;
    }

    @Override
    public void createCompanyEnterpriseInfoEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid){
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = new CompanyEnterpriseInfoEntity();
        companyEnterpriseInfoEntity.setRegisteredAddressProvince(registerUserInfoEntity.getRegisteredAddressProvince());
        companyEnterpriseInfoEntity.setRegisteredAddressProvinceName(registerUserInfoEntity.getRegisteredAddressProvinceName());
        companyEnterpriseInfoEntity.setRegisteredAddressCity(registerUserInfoEntity.getRegisteredAddressCity());
        companyEnterpriseInfoEntity.setRegisteredAddressCityName(registerUserInfoEntity.getRegisteredAddressCityName());
        companyEnterpriseInfoEntity.setRegisteredAddressCounty(registerUserInfoEntity.getRegisteredAddressCounty());
        companyEnterpriseInfoEntity.setRegisteredAddressCountyName(registerUserInfoEntity.getRegisteredAddressCountyName());

        companyEnterpriseInfoEntity.setOperatingAddressProvince(registerUserInfoEntity.getOperatingAddressProvince());
        companyEnterpriseInfoEntity.setOperatingAddressProvinceName(registerUserInfoEntity.getOperatingAddressProvinceName());
        companyEnterpriseInfoEntity.setOperatingAddressCity(registerUserInfoEntity.getOperatingAddressCity());
        companyEnterpriseInfoEntity.setOperatingAddressCityName(registerUserInfoEntity.getOperatingAddressCityName());
        companyEnterpriseInfoEntity.setOperatingAddressCounty(registerUserInfoEntity.getOperatingAddressCounty());
        companyEnterpriseInfoEntity.setOperatingAddressCountyName(registerUserInfoEntity.getOperatingAddressCountyName());

        companyEnterpriseInfoEntity.setAccountAmount(registerUserInfoEntity.getAccountAmount());
//            companyEnterpriseInfoEntity.setAttemptsNum(registerUserInfoEntity.getAttemptsNum());
        companyEnterpriseInfoEntity.setUpstreamSize(registerUserInfoEntity.getUpstreamSize());
        companyEnterpriseInfoEntity.setDownstreamSize(registerUserInfoEntity.getDownstreamSize());
        companyEnterpriseInfoEntity.setEnterpriseId(pid.toString());
        companyEnterpriseInfoEntity.setScopeOperation(registerUserInfoEntity.getScopeOperation());

        companyEnterpriseInfoEntity.setLegalAddressProvince(registerUserInfoEntity.getLegalAddressProvince());
        companyEnterpriseInfoEntity.setLegalAddressProvinceName(registerUserInfoEntity.getLegalAddressProvinceName());
        companyEnterpriseInfoEntity.setLegalAddressCity(registerUserInfoEntity.getLegalAddressCity());
        companyEnterpriseInfoEntity.setLegalAddressCityName(registerUserInfoEntity.getLegalAddressCityName());

        companyEnterpriseInfoEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        companyEnterpriseInfoEntity.setStatus("NOL");

        fcRegisterCompanyCore.saveCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);

    }

    @Override
    public void createCompanyOperationEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid){
        CompanyOperationEntity companyOperationEntity = new CompanyOperationEntity();
        companyOperationEntity.setName(registerUserInfoEntity.getLegalName());
        companyOperationEntity.setPhone(registerUserInfoEntity.getLegalPhone());
        companyOperationEntity.setCardType(registerUserInfoEntity.getLegalCardType());
        companyOperationEntity.setCardNum(registerUserInfoEntity.getLegalCardNum());
        companyOperationEntity.setSex(registerUserInfoEntity.getLegalSex());
        companyOperationEntity.setMail(registerUserInfoEntity.getLegalEmail());
        companyOperationEntity.setAddress(registerUserInfoEntity.getLegalAddress());
        companyOperationEntity.setEnpsId(pid);
        companyOperationEntity.setType("legal");
        companyOperationEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        companyOperationEntity.setStatus("NOL");
        fcRegisterCompanyCore.saveCompanyOperationEntity(companyOperationEntity);

        if ("2".equals(registerUserInfoEntity.getFillPersion())){
            CompanyOperationEntity operationEntity = new CompanyOperationEntity();
            // 有代理人
            operationEntity.setName(registerUserInfoEntity.getAgentName());
            operationEntity.setPhone(registerUserInfoEntity.getAgentPhone());
            operationEntity.setCardType(registerUserInfoEntity.getAgentCardType());
            operationEntity.setCardNum(registerUserInfoEntity.getAgentCardNum());
            operationEntity.setSex(registerUserInfoEntity.getAgentSex());
            operationEntity.setMail(registerUserInfoEntity.getAgentEmail());
            operationEntity.setEnpsId(pid);
            operationEntity.setType("agent");
            operationEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
            operationEntity.setStatus("NOL");
            fcRegisterCompanyCore.saveCompanyOperationEntity(operationEntity);
        }

    }


    @Override
    public void createBaseBankInfoEntity(RegisterUserInfoEntity registerUserInfoEntity,Long pid){
        BaseBankInfoEntity baseBankInfoEntity = new BaseBankInfoEntity();
        baseBankInfoEntity.setBankOpen(registerUserInfoEntity.getBankOpen());
        baseBankInfoEntity.setBankBranch(registerUserInfoEntity.getBankBranch());
        baseBankInfoEntity.setAccountType("其他账户");
        baseBankInfoEntity.setObjectId(pid);
        baseBankInfoEntity.setAccountType(registerUserInfoEntity.getAccountType());
        if ("1".equals(registerUserInfoEntity.getEnterpriseType())){
            baseBankInfoEntity.setObjectType("1");
        }else {
            baseBankInfoEntity.setObjectType("0");
        }
        baseBankInfoEntity.setAccountName(registerUserInfoEntity.getName());
        baseBankInfoEntity.setAccountUse("1");
        baseBankInfoEntity.setBankCard(registerUserInfoEntity.getBankCard());
        baseBankInfoEntity.setCreateAt(new Timestamp(System.currentTimeMillis()));
        baseBankInfoEntity.setStatus("NOL");
        fcRegisterCompanyCore.saveBaseInfoEntity(baseBankInfoEntity);
    }

    private void createBusinessOrder(BusinessReceivableEntity receivableEntity,Long pid){
        BusinessOrderEntity orderEntity = new BusinessOrderEntity();
        orderEntity.setEnterpriseId(receivableEntity.getEnterpriseId());
        orderEntity.setEnterpriseName(receivableEntity.getEnterpriseName());
        orderEntity.setContractNo(receivableEntity.getContractNo());
        orderEntity.setCertificateAmount(receivableEntity.getCertificateAmount());
        orderEntity.setSupplierId(pid);
        orderEntity.setSupplierName(receivableEntity.getSupplierName());
        orderEntity.setAccountNo(receivableEntity.getAccountNo());
        orderEntity.setAccountStartTime(receivableEntity.getAccountStartTime());
        orderEntity.setAccountEndTime(receivableEntity.getAccountEndTime());
        orderEntity.setServiceFee(receivableEntity.getServiceFee());
        orderEntity.setInterestRate(receivableEntity.getInterestRate());
        orderEntity.setRebateRatio(receivableEntity.getRebateRatio());
        orderEntity.setEnterCategory(receivableEntity.getCertificateCategory());
        orderEntity.setEnterCategoryValue(receivableEntity.getCertificateCategoryValue());
        orderEntity.setState("0");
        orderEntity.setFinancingStatus("0");
        orderEntity.setInviteTime(receivableEntity.getInviteTime());
        orderEntity.setOrderCode(receivableEntity.getOrderCode());
        orderEntity.setCreateAt(new Date());
        orderEntity.setStatus(Constants.DATA_STATUS_NOL);
        orderEntity.setEnterCategoryValue(receivableEntity.getCertificateCategoryValue());
        businessOrderService.insertOrder(orderEntity);
    }

}
