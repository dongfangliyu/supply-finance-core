package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:51
 */
public interface AuditLoanService {
    Message searchLoanList(AuditFinanceForm auditFinanceForm);

    Message searchLoanDetail(String pid);

    Message<Object> submitLoanPass(String pid, String loanTime, String userName);

    Message<Object> submitLoanFail(String pid, String userName);

    Message uploadAuditLoanFile(MultipartFile file, String type, String pid);
}
