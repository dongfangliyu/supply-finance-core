package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:36
 */
@RestController
@RequestMapping("/audit/reviewService")
public class AuditReviewController {

    @Autowired
    private AuditReviewService auditReviewService;

    @ResponseBody
    @RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
    public Message searchReviewList(@RequestBody AuditRemindForm auditRemindForm){
        return auditReviewService.searchReviewList(auditRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam(value = "pid") String pid){
        return auditReviewService.searchReviewDetail(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitResult", method = RequestMethod.GET)
    public Message submitResult(@RequestParam(value = "pid") String pid, @RequestParam(value = "state") String state){
        return auditReviewService.submitResult(pid,state);
    }

}
