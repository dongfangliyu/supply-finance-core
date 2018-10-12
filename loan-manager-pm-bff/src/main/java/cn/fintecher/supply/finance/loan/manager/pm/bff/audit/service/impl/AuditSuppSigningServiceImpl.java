package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:48
 */
@Service("AuditSuppSigningService")
public class AuditSuppSigningServiceImpl implements AuditSuppSigningService {

    @Autowired
    private FCAuditSuppSigningService fcAuditSigningService;

    @Autowired
    private FCAuditFileService fcAuditFileService;

    @Override
    public Message searchSigningList(AuditSigningRequest auditSigningRequest) {
        return fcAuditSigningService.searchSigningList(auditSigningRequest);
    }

    @Override
    public Message searchSigningDetail(Long pid) {

        return fcAuditSigningService.searchSigningDetail(pid);
    }

    @Override
    public Message submitSigningAccount(AuditRepayBankInfoForm auditRepayBankInfoForm) {
        return fcAuditSigningService.submitSigningAccount(auditRepayBankInfoForm);
    }

    @Override
    public Message submitSigning(Long pid, String name) {
        return fcAuditSigningService.submitSigning(pid,name);
    }

    @Override
    public Message<List<AuditFileEntity>> searchContract(String pid, String type) {
        return fcAuditFileService.searchAllAuditFileByOwnerIdAndCategory(pid,type);
    }

    @Override
    public ResponseEntity<byte[]> downloadContract(Long pid) {
        return fcAuditSigningService.downloadContract(pid);
    }

    @Override
    public Message<List<AuditFileEntity>> searchContractMoreType(String pid) {
        return fcAuditFileService.searchAllAuditFileByOwnerId(pid);
    }

    @Override
    public Message isUpLoadContract(Long pid) {
        return fcAuditSigningService.isUpLoadContract(pid);
    }
}
