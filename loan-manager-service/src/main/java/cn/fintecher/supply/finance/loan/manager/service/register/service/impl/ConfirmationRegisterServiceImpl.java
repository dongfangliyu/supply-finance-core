package cn.fintecher.supply.finance.loan.manager.service.register.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.ConfirmCompanyResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import cn.fintecher.supply.finance.loan.manager.service.register.service.ConfirmationRegisterService;
import com.google.common.base.Strings;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 10:39
 */
@Service
public class ConfirmationRegisterServiceImpl implements ConfirmationRegisterService {

    @Autowired
    private FCRegisterCompanyCore fcRegisterCompanyCore;

    @Autowired
    private CompanyChainCore companyChainCore;

    @Autowired
    private FCCompanyUserCore fcCompanyUserCore;

    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Override
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

        // 判断核心企业注册的手机号是不是链属企业
        if (null != registerUserEntity){
            if (registerUserEntity.getEnterpriseType().equals("3")){
                //判断是否为链属企业
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
        }else {
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            message.setMessage("传参错误!");
            return message;
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
            if (registerUserEntity.getEnterpriseType().equals("4")){
                CompanyChainEntity companyChainEntity = new CompanyChainEntity();
                    companyChainEntity.setMobile(registerUserEntity.getUserName());
                    companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                    Message selectByChain = companyChainCore.selectByChain(companyChainEntity);
                    List<CompanyChainEntity> list1 = JSONUtil.toList(selectByChain.getMessage(),CompanyChainEntity.class);
                    if (ChkUtil.isEmpty(list1)){
                        message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                        message.setMessage("对不起,您没有权限注册经销商!");
                        return message;
                    }else{
                        CompanyChainEntity companyChainEntity1 = list1.get(0);
                        if(!"1".equals(companyChainEntity1.getApplyState())){
                            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                            message.setMessage("对不起,您没有权限注册经销商!");
                            return message;
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
                    message.setMessage("对不起,您注册的手机号为链属企业手机号,请查证后再注册!");
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
            if(registerUserInfoEntity.getEnterpriseType().equals("4")){
                companyChainEntity.setCompanyName(registerUserInfoEntity.getName());
                companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message message1 = companyChainCore.selectByChain(companyChainEntity);
                List<CompanyChainEntity> companyChainEntities = JSONUtil.toList(message1.getMessage(), CompanyChainEntity.class);
                if (ChkUtil.isEmpty(companyChainEntities)){
                    message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
                    message.setMessage("注册经销商企业名称不存在!");
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

}
