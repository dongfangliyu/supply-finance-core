package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:48
 */
public interface AuditSuppSigningService {
    Message searchSigningList(AuditSigningRequest auditSigningRequest);

    Message searchSigningDetail(Long pid);

    Message submitSigningAccount(AuditRepayBankInfoForm auditRepayBankInfoForm);

    Message submitSigning(Long pid, String name);

    ResponseEntity<byte[]> downloadContract(Long pid) throws IOException, TemplateException;

    Message isUpLoadContract(Long pid);
}
