package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:48
 */
public interface AuditSuppSigningService {
    Message searchSigningList(AuditSigningRequest auditSigningRequest);

    Message searchSigningDetail(Long pid);

    Message submitSigningAccount(AuditRepayBankInfoForm auditRepayBankInfoForm);

    Message submitSigning(Long pid, String name);

    Message searchContract(String pid, String type);

    ResponseEntity<byte[]> downloadContract(Long pid);

    Message<List<AuditFileEntity>> searchContractMoreType(String pid);

    Message isUpLoadContract(Long pid);
}
