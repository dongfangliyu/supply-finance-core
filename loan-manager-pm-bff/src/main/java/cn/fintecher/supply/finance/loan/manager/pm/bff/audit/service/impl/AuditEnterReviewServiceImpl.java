package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditEnterReviewService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEnterReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/27 0027上午 10:22
 */
@Service
public class AuditEnterReviewServiceImpl implements AuditEnterReviewService{

    @Autowired
    private FCAuditEnterReviewService fcAuditEnterReviewService;

    @Override
    public Message searchReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
        return fcAuditEnterReviewService.searchReviewList(auditSuppReviewRequest);
    }

    @Override
    public Message searchReviewDetail(Long pid) {
        return fcAuditEnterReviewService.searchReviewDetail(pid);
    }
}
