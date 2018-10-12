package cn.fintecher.supply.finance.loan.manager.core.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyCreditResultResponse;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditCompanyDao;
import cn.fintecher.supply.finance.loan.manager.core.credit.dao.EnterpriseCreditDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 10:26 2018/6/21
 */
@Service
public class EnterpriseCreditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseCreditService.class);

    @Autowired
    private EnterpriseCreditDao enterpriseCreditDao;

    @Autowired
    private AuditCompanyDao auditCompanyDao;

    public CompanyEnterpriseEntity searchCompanyCreditStatus(SearchCompanyCreditForm form) {
       return enterpriseCreditDao.searchCompanyCreditStatus(form);
    }


    public Message updateCompanyCreditStep(CompanyEnterpriseEntity companyEnterpriseEntity){
        Message msg = new Message();
        try{
            enterpriseCreditDao.updateCompanyCreditStep(companyEnterpriseEntity);
            msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }catch (Exception e){
            msg.setCode(MsgCodeConstant.ERR_ENTERPRISE_CREDIT_UPDATE);
            LOGGER.error("更改企业授信步骤状态失败",e);
            e.printStackTrace();
        }
        return msg;
    }

    public Boolean applyCompanyCredit(AuditCompanyEntity entity){
        return  auditCompanyDao.submitCredit(entity);
    }

    public CompanyCreditResultResponse  searchCompanyCreditResult(Long pid){
        return  enterpriseCreditDao.searchCompanyCreditResult(pid);
    }

    public CompanyEnterpriseEntity searchCompanyInfo(Long pid){
        return  enterpriseCreditDao.searchCompanyInfo(pid);
    }

    public Long searchCountOfCredit(){
        return  enterpriseCreditDao.searchCountOfCredit();
    }
}

