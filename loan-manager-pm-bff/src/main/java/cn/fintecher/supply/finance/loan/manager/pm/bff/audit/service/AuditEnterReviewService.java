package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;

/**
 * @author gonghebin
 * @date 2018/7/27 0027上午 10:22
 */
public interface AuditEnterReviewService {

    Message searchReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

    Message searchReviewDetail(Long pid);
}
