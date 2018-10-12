package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/27 0027上午 10:30
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditEnterReviewService {

    @ResponseBody
    @RequestMapping(value = "/audit/entryReviewService/searchReviewList", method = RequestMethod.POST)
    Message searchReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest);

    @ResponseBody
    @RequestMapping(value = "/audit/entryReviewService/searchReviewDetail", method = RequestMethod.GET)
    Message searchReviewDetail(@RequestParam(value = "pid") Long pid);

}
