package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditPlatformSigningService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditPlatformSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 14:08
 */
@Service("AuditPlatformSigningService")
public class AuditPlatformSigningServiceImpl implements AuditPlatformSigningService {

    @Autowired
    private FCAuditPlatformSigningService fcAuditPlatformSigningService;

    @Override
    public Message searchPlatformSigningList(AuditSigningRequest auditSigningRequest) {
        return fcAuditPlatformSigningService.searchPlatformSigningList(auditSigningRequest);
    }

    @Override
    public Message submitSigning(Long pid, String name) {
        return fcAuditPlatformSigningService.submitSigning(pid,name);
    }
}
