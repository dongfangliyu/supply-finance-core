package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditLoanService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:51
 */
@Service
public class AuditLoanServiceImpl implements AuditLoanService{

    @Autowired
    private FCAuditLoanService fcAuditLoanService;

    @Override
    public Message searchLoanList(AuditFinanceForm auditFinanceForm) {
        return fcAuditLoanService.searchLoanList(auditFinanceForm);
    }

    @Override
    public Message searchLoanDetail(String pid) {
        return fcAuditLoanService.searchLoanDetail(pid);
    }

    @Override
    public Message<Object> submitLoanPass(String pid,String loanTime, String userName) {
        return fcAuditLoanService.submitLoanPass(pid,loanTime,userName);
    }

    @Override
    public Message<Object> submitLoanFail(String pid, String userName) {
        return fcAuditLoanService.submitLoanFail(pid,userName);
    }

    @Override
    public Message uploadAuditLoanFile(MultipartFile file, String type, String pid) {
        return fcAuditLoanService.uploadAuditLoanFile(file,type,pid);
    }
}
