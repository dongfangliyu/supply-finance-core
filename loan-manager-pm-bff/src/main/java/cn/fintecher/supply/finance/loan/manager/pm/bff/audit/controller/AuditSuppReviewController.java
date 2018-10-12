package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:22
 */
@RestController
@RequestMapping("/audit/auditSuppReview")
@Api(tags = "供应商还款查询接口")
public class AuditSuppReviewController {
    @Autowired
    private AuditSuppReviewService auditSuppReviewService;

    @Autowired
    private AuditEntryService auditEntryService;

    /**
     * 还款查询列表（供应商）
     */
    @ApiOperation(value = "还款查询列表（供应商）", notes = "还款查询列表（供应商）")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
    public Message searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest, Principal principal){
        try {
            auditSuppReviewRequest.setCurrentUserName(principal.getName());
           return auditSuppReviewService.searchSuppReviewList(auditSuppReviewRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    /**
     *还款查询详情（供应商）
     * */
    @ApiOperation(value = "还款查询详情（供应商）", notes = "还款查询详情（供应商）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam Long pid){
        try {
            return auditSuppReviewService.searchSuppReviewDetail(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    /**
     *还款历史列表（供应商）
     * */
    @ApiOperation(value = "还款历史列表（供应商）", notes = "还款历史列表（供应商）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewDetailList", method = RequestMethod.POST)
    public Message searchReviewDetailList(@RequestBody AuditEntryForm auditEntryForm){
        try {
            return auditEntryService.searchEntry(auditEntryForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }
}
