package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:28
 */
public interface AuditPlatformReviewService {

    Message searchPlatformReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

}
