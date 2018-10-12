package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 13:31
 */
public interface AuditEnterSigningService {
    Message searchEnterSigningList(AuditSigningRequest auditSigningRequest);

    Message submitSigning(Long pid, String name);

    Message isUpLoadContract(Long pid);
}
