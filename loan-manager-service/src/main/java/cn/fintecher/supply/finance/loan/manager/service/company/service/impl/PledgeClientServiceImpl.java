package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyEnterpriseResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseAreaCore;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseBankInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseInfoService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyUserService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.PledgeClientService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:30
 */
@Service
public class PledgeClientServiceImpl implements PledgeClientService {

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private CompanyEnterpriseInfoService companyEnterpriseInfoService;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    @Autowired
    private FCBaseBankInfoCore fcBaseBankInfoCore;

    @Autowired
    private FCBaseAreaCore fcBaseAreaCore;


    @Override
    public Message selectPledgeEnterprise(String name) {
        Message message = new Message();
        CompanyUserEntity companyUserEntity = redisService.get(name, CompanyUserEntity.class);
        if (null == companyUserEntity){
            companyUserEntity = companyUserService.findCompanyUserByName(name);
        }
        CompanyEnterpriseResponse response = new CompanyEnterpriseResponse();
        CompanyEnterpriseEntity companyEnterpriseEntity = companyEnterpriseService.searchCompanyEnterpriseEntity(companyUserEntity.getEnterpriseId());
        BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(companyUserEntity.getEnterpriseId());
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = companyEnterpriseInfoService.searchCompanyEnterpriseInfoEntity(companyUserEntity.getEnterpriseId());
        List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(companyUserEntity.getEnterpriseId());
        List<CompanyFileEntity> companyFileEntities = fcEnterpriseFinancialCore.searchAccountingStatementInfo(companyUserEntity.getEnterpriseId());
        if(null != companyFileEntities){
            response.setCompanyFileEntities(companyFileEntities);
        }
        if (null != companyEnterpriseEntity && null != companyEnterpriseInfoEntity && null != list && null != baseBankInfoEntity){
            response.setCompanyEnterpriseEntity(companyEnterpriseEntity);
            response.setCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
            response.setCompanyOperationEntity(list);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            response.setBaseBankInfoEntity(baseBankInfoEntity);
            message.setMessage(response);
        }else {
            message.setCode(MsgCodeConstant.ERR_CREATE_COMPANY_ENTERPRISE);
        }
        return message;
    }

    @Override
    public Message updatePledgeEnterprise(PledgeClientForm pledgeClientForm) {
        Message message = new Message();
        // 银行
        BaseBankInfoEntity baseBankInfoEntity = pledgeClientForm.getBaseBankInfoEntity();
        // 企业信息
        CompanyEnterpriseEntity companyEnterpriseEntity = pledgeClientForm.getCompanyEnterpriseEntity();
        // 企业详情
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = pledgeClientForm.getCompanyEnterpriseInfoEntity();
        // 企业人员
        List<CompanyOperationEntity> companyOperationEntity = pledgeClientForm.getCompanyOperationEntity();
        if (ChkUtil.isEmpty(baseBankInfoEntity) || ChkUtil.isEmpty(companyEnterpriseEntity) || ChkUtil.isEmpty(companyEnterpriseInfoEntity) || companyOperationEntity.size() < 0){
            message.setMessage("数据异常!");
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            return message;
        }
        if (baseBankInfoEntity.getPid() > 0 && companyEnterpriseEntity.getPid() > 0 && companyEnterpriseInfoEntity.getPid() > 0){
            // 更新企业基本信息
            fcCompanyEnterpriseCore.updateCompanyEnterpriseEntity(companyEnterpriseEntity);
            // 更新企业详细信息

            if(null!=companyEnterpriseInfoEntity.getRegisteredAddressCounty()) {
                Long RegisteredAddressCounty = companyEnterpriseInfoEntity.getRegisteredAddressCounty();
                companyEnterpriseInfoEntity.setRegisteredAddressCountyName(fcBaseAreaCore.getAreaNameById(RegisteredAddressCounty));
            }
            if(null!=companyEnterpriseInfoEntity.getRegisteredAddressCity()) {
                Long registeredAddressCity = companyEnterpriseInfoEntity.getRegisteredAddressCity();
                companyEnterpriseInfoEntity.setRegisteredAddressCityName(fcBaseAreaCore.getAreaNameById(registeredAddressCity));
            }
            if(null!=companyEnterpriseInfoEntity.getRegisteredAddressProvince()){
                Long registeredAddressProvince = companyEnterpriseInfoEntity.getRegisteredAddressProvince();
                companyEnterpriseInfoEntity.setRegisteredAddressProvinceName(fcBaseAreaCore.getAreaNameById(registeredAddressProvince));
            }
            if(null!=companyEnterpriseInfoEntity.getOperatingAddressCounty()){
                Long operatingAddressCounty = companyEnterpriseInfoEntity.getOperatingAddressCounty();
                companyEnterpriseInfoEntity.setOperatingAddressCountyName(fcBaseAreaCore.getAreaNameById(operatingAddressCounty));
            }
            if(null!=companyEnterpriseInfoEntity.getOperatingAddressCity()){
                Long operatingAddressCity =companyEnterpriseInfoEntity.getOperatingAddressCity();
                companyEnterpriseInfoEntity.setOperatingAddressCityName(fcBaseAreaCore.getAreaNameById(operatingAddressCity));
            }
            if(null!=companyEnterpriseInfoEntity.getOperatingAddressProvince()){
                Long operatingAddressProvince = companyEnterpriseInfoEntity.getOperatingAddressProvince();
                companyEnterpriseInfoEntity.setOperatingAddressProvinceName(fcBaseAreaCore.getAreaNameById(operatingAddressProvince));
            }

            fcCompanyEnterpriseCore.updateCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
            // 更新银行信息
            fcBaseBankInfoCore.update(baseBankInfoEntity);
            for (CompanyOperationEntity entity:companyOperationEntity) {
                if (entity.getPid() != null){
                    fcCompanyEnterpriseCore.updateCompanyOperationEntity(entity);
                }
            }
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            return message;

        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("传入数据异常!");
            return message;
        }
    }


}
