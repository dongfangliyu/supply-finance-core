package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCEnterpriseFinancialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.EnterpriseFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:49 2018/6/21
 */
@Service
public class EnterpriseFinancialServiceImpl implements EnterpriseFinancialService {
    @Autowired
    private FCEnterpriseFinancialService enterpriseFinancialService;

    public Message uploadCompanyCreditDoc(MultipartFile file, String type, String year,Long pid) {
        return enterpriseFinancialService.uploadCompanyCreditDoc(file, type, year,pid);
    }

    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid,String type,String year) {

        return enterpriseFinancialService.donwloadCompanyCreditDoc(pid,type,year);
    }

    public Message deleteCompanyCreditDoc(Long pid) {
        return enterpriseFinancialService.deleteCompanyCreditDoc(pid);
    }

    public Message submitAccountingStatementInfo(Long pid,String code) {
        return enterpriseFinancialService.submitAccountingStatementInfo(pid,code);
    }

    public Message submitCompanyCreditImageInfo(Long pid, String code) {
        return enterpriseFinancialService.submitCompanyCreditImageInfo(pid,code);
    }

    public List<CompanyFileEntity> searchAccountingStatementInfo(Long pid) {
        return enterpriseFinancialService.searchAccountingStatementInfo(pid);
    }

    @Override
    public List<RegisterFileEntity> searchRegisterFile(Long pid) {
        return enterpriseFinancialService.searchRegisterFile(pid);
    }

    @Override
    public ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(Long pid) {
        return enterpriseFinancialService.donwloadCompanyCreditDocByFid(pid);
    }

    @Override
    public Message deletePledgeEnterpriseFile(Long pid) {
        return enterpriseFinancialService.deletePledgeEnterpriseFile(pid);
    }

    @Override
    public Message uploadPledgeEnterpriseFile(MultipartFile file, String type, Long enterpriseId) {
        return enterpriseFinancialService.uploadPledgeEnterpriseFile(file,type,enterpriseId);
    }

}
