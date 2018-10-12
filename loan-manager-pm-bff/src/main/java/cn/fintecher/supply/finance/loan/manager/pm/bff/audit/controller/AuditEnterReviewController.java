package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEnterReviewService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author gonghebin
 * @date 2018/7/27 0027上午 10:20
 */
@RestController
@RequestMapping("/audit/auditEnterReview")
@Api(tags = "核心企业还款查询")
public class AuditEnterReviewController {

    @Autowired
    private AuditEnterReviewService auditEnterReviewService;

    @Autowired
    private AuditSuppReviewService auditSuppReviewService;

    @Autowired
    private AuditEntryService auditEntryService;



    /**
     * 还款查询列表（核心企业）
     */
    @ApiOperation(value = "还款查询列表（核心企业）", notes = "还款查询列表（核心企业）")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
    public Message searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest, Principal principal){
        try {
            auditSuppReviewRequest.setCurrentUserName(principal.getName());
            return auditEnterReviewService.searchReviewList(auditSuppReviewRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    /**
     *还款查询详情（核心企业）
     * */
    @ApiOperation(value = "还款查询详情（核心企业）", notes = "还款查询详情（核心企业）")
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
     *还款历史列表（核心企业）
     * */
    @ApiOperation(value = "还款历史列表（核心企业）", notes = "还款历史列表（核心企业）")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchTaskEntryList", method = RequestMethod.POST)
    public Message searchTaskEntryList(@RequestBody AuditEntryForm auditEntryForm){
        try {
            return auditEntryService.searchEntry(auditEntryForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

}
