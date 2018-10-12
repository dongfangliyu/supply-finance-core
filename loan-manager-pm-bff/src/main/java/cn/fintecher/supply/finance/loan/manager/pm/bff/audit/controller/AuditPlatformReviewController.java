package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditPlatformReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:27
 */
@RestController
@RequestMapping("/audit/auditPlatformReview")
@Api(tags = "平台还款查询接口")
public class AuditPlatformReviewController {

    @Autowired
    private AuditPlatformReviewService auditPlatformReviewService;

    /**
     * 还款查询列表（平台）
     */
    @ApiOperation(value = "还款查询列表（平台）", notes = "还款查询列表（平台）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
    public Message searchReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest, Principal principal){
        try {
            return auditPlatformReviewService.searchPlatformReviewList(auditSuppReviewRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    /**
     *还款查询详情（平台）
     * */
    @ApiOperation(value = "还款查询详情（平台）", notes = "还款查询详情（平台）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam Long pid){
        try {
            return auditPlatformReviewService.searchPlatformReviewDetail(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    /**
     *还款历史列表（平台）
     * */
    @ApiOperation(value = "还款历史列表（平台）", notes = "还款历史列表（平台）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewDetailList", method = RequestMethod.POST)
    public Message searchReviewDetailList(@RequestBody AuditEntryForm auditEntryForm){
        try {
            return auditPlatformReviewService.searchPlatformReviewDetailList(auditEntryForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

}
