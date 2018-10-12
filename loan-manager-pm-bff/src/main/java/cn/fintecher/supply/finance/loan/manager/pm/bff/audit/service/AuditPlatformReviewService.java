package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:28
 */
public interface AuditPlatformReviewService {

    Message searchPlatformReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

    Message searchPlatformReviewDetailList(AuditEntryForm auditEntryForm);

    Message searchPlatformReviewDetail(Long pid);

}
