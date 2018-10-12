package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditSuppReviewService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:32
 */
@Service("AuditSuppReviewService")
public class AuditSuppReviewServiceImpl implements AuditSuppReviewService {

    @Autowired
    private FCAuditSuppReviewService fcAuditSuppReviewService;

    @Override
    public Message searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
        return fcAuditSuppReviewService.searchSuppReviewList(auditSuppReviewRequest);
    }

    @Override
    public Message searchSuppReviewDetail(Long pid) {
        return fcAuditSuppReviewService.searchSuppReviewDetail(pid);
    }
}
