package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:33
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditSuppReviewService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppReview/searchSuppReviewList", method = RequestMethod.POST)
    Message searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppReview/searchSuppReviewDetail", method = RequestMethod.GET)
    Message searchSuppReviewDetail(@RequestParam("pid") Long pid);
}
