package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditReviewService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:35
 */
@Service
public class AuditReviewServiceImpl implements AuditReviewService {

    @Autowired
    private FCAuditReviewService fcAuditReviewService;


    @Override
    public Message searchReviewList(AuditRemindForm auditRemindForm) {
        return fcAuditReviewService.searchReviewList(auditRemindForm);
    }

    @Override
    public Message searchReviewDetail(String pid) {
        return fcAuditReviewService.searchReviewDetail(pid);
    }

    @Override
    public Message submitResult(String pid, String state) {
        return fcAuditReviewService.submitResult(pid,state);
    }

}
