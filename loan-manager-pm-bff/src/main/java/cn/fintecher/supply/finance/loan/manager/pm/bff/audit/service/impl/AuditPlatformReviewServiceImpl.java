package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditPlatformReviewService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditPlatformReviewService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:28
 */
@Service("AuditPlatformReviewService")
public class AuditPlatformReviewServiceImpl implements AuditPlatformReviewService {

    @Autowired
    private FCAuditPlatformReviewService fcAuditPlatformReviewService;

    @Override
    public Message searchPlatformReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
        return fcAuditPlatformReviewService.searchPlatformReviewList( auditSuppReviewRequest);
    }

    @Override
    public Message searchPlatformReviewDetailList(AuditEntryForm auditEntryForm) {
        return fcAuditPlatformReviewService.searchPlatformReviewDetailList( auditEntryForm) ;
    }

    @Override
    public Message searchPlatformReviewDetail(Long pid) {
        return fcAuditPlatformReviewService.searchPlatformReviewDetail( pid);
    }
}
