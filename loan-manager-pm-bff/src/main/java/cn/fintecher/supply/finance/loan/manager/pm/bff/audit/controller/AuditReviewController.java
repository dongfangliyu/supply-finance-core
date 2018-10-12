package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:34
 */
@RestController
@RequestMapping("/audit/auditReview")
@Api(tags = "还款复核")
public class AuditReviewController {

    @Autowired
    private AuditReviewService auditReviewService;

    /**
     * 还款复核列表
     * @param auditRemindForm
     * @return
     */
    @ApiOperation(value="还款复核列表 ", notes="还款复核列表")
    @RequestMapping(value ="/searchReviewList", method = RequestMethod.POST)
    public Message searchReviewList(@RequestBody AuditRemindForm auditRemindForm){
        Message message = auditReviewService.searchReviewList(auditRemindForm);
        return message;
    }

    /**
     * 还款复核详情
     * @return
     */
    @ApiOperation(value="还款复核详情 ", notes="还款复核详情")
    @RequestMapping(value ="/searchReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam(value = "pid") String pid){
        Message message = auditReviewService.searchReviewDetail(pid);
        return message;
    }

    /**
     * 审核通过/驳回
     * @return
     */
    @ApiOperation(value="审核通过/驳回 ", notes="审核通过/驳回")
    @RequestMapping(value ="/submitResult", method = RequestMethod.GET)
    public Message submitResult(@RequestParam(value = "pid") String pid,@RequestParam(value = "state") String state){
        Message message = auditReviewService.submitResult(pid,state);
        return message;
    }

}
