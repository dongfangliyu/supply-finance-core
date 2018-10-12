package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEnterEntryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditPlatformReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:27
 */
@RestController
@RequestMapping("/audit/auditPlatformReview")
public class AuditPlatformReviewController {

    @Autowired
    private AuditPlatformReviewService auditPlatformReviewService;

    @Autowired
    private AuditEntryService auditEntryService;

    @Autowired
    private AuditEnterEntryService auditEnterEntryService;

    /**
     * 还款查询列表（平台）
     */
    @ResponseBody
    @RequestMapping(value = "/searchPlatformReviewList", method = RequestMethod.POST)
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
    @ResponseBody
    @RequestMapping(value = "/searchPlatformReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam Long pid){
        try {
            return auditEnterEntryService.searchEntryDetail(pid.toString());
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    /**
     *还款历史列表（平台）
     * */
    @ResponseBody
    @RequestMapping(value = "/searchPlatformReviewDetailList", method = RequestMethod.POST)
    public Message searchReviewDetailList(@RequestBody AuditEntryForm auditEntryForm){
        try {
            return auditEntryService.searchEntry(auditEntryForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

}
