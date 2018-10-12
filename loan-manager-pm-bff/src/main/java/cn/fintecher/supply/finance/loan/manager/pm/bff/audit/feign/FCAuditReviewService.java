package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:35
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditReviewService {

    @ResponseBody
    @RequestMapping(value = "/audit/reviewService/searchReviewList", method = RequestMethod.POST)
    Message searchReviewList(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/reviewService/searchReviewDetail", method = RequestMethod.GET)
    Message searchReviewDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/reviewService/submitResult", method = RequestMethod.GET)
    Message submitResult(@RequestParam(value = "pid") String pid, @RequestParam(value = "state") String state);

}
