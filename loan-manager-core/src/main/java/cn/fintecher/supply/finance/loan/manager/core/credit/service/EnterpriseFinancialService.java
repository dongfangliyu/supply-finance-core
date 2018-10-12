package cn.fintecher.supply.finance.loan.manager.core.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.dao.EnterpriseFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:19 2018/6/21
 */
@Service
public class EnterpriseFinancialService {

    @Autowired
    private EnterpriseFileDao enterpriseFileDao;
    /**
     * 添加
     * @param companyFileEntity  数据实例
     * @return
     */
    public Message<CompanyFileEntity> insert(CompanyFileEntity companyFileEntity) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        try {
        boolean flag =  enterpriseFileDao.insert(companyFileEntity);
           message.setMessage(companyFileEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Boolean deleteCompanyCreditDoc(Long pid){
        CompanyFileEntity entity = new CompanyFileEntity();
        entity.setStatus("DEL");
        entity.setPid(pid);
        return enterpriseFileDao.deleteCompanyCreditDoc(entity);
    }

    public List<CompanyFileEntity> searchAccountingStatementInfo(Long pid) {
        List<CompanyFileEntity> list = enterpriseFileDao.searchAccountingStatementInfo(pid);
        return list;
    }

    public CompanyFileEntity searchAllFileByFid(Long pid){
        return enterpriseFileDao.searchAllFileByFid(pid);
    }

    public CompanyFileEntity searchAllFileInfo(Long pid,String type,String year){
        return enterpriseFileDao.searchAllFileInfo(pid,type,year);
    }

    public void updateCompanyFile(CompanyFileEntity companyFileEntity) {
        enterpriseFileDao.updateCompanyFile(companyFileEntity);
    }
}
