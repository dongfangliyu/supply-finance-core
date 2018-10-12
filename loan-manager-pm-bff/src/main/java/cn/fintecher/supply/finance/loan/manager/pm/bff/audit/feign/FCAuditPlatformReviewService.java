package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:34
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditPlatformReviewService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditPlatformReview/searchPlatformReviewList", method = RequestMethod.POST)
    Message searchPlatformReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditPlatformReview/searchPlatformReviewDetailList", method = RequestMethod.POST)
    Message searchPlatformReviewDetailList(@RequestBody AuditEntryForm auditEntryForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditPlatformReview/searchPlatformReviewDetail", method = RequestMethod.GET)
    Message searchPlatformReviewDetail(@RequestParam("pid") Long pid);
}
