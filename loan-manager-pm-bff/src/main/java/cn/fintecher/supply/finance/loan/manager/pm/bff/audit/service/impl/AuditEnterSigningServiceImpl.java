package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditEnterSigningService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEnterSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 10:35
 */
@Service("AuditEnterSigningService")
public class AuditEnterSigningServiceImpl implements AuditEnterSigningService {

    @Autowired
    private FCAuditEnterSigningService fcAuditEnterSigningService;

    @Override
    public Message searchEnterSigningList(AuditSigningRequest auditSigningRequest) {
        return fcAuditEnterSigningService.searchEnterSigningList(auditSigningRequest);
    }

    @Override
    public Message submitSigning(Long pid, String name) {
        return fcAuditEnterSigningService.submitSigning(pid,name);
    }

    @Override
    public Message isUpLoadContract(Long pid) {
        return fcAuditEnterSigningService.isUpLoadContract(pid);
    }
}
