package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:35
 */
public interface AuditReviewService {

    Message searchReviewList(AuditRemindForm auditRemindForm);

    Message searchReviewDetail(String pid);

    Message submitResult(String pid, String state);

}
