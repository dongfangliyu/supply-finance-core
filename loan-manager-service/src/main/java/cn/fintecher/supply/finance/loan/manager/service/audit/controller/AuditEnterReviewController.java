package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppReviewService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/27 0027上午 10:34
 */
@RestController
@RequestMapping("/audit/entryReviewService")
public class AuditEnterReviewController extends BaseCompanyUserController {

    @Autowired
    private AuditEntryService auditEntryService;

    @Autowired
    private AuditSuppReviewService auditSuppReviewService;


    @ResponseBody
    @RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
    public Message searchReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest){

        try {
            auditSuppReviewRequest.setEnterpriseId(getCompanyUser(auditSuppReviewRequest.getCurrentUserName()).getEnterpriseId());
            return auditSuppReviewService.searchSuppReviewList(auditSuppReviewRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }



    @ResponseBody
    @RequestMapping(value = "/searchReviewDetail", method = RequestMethod.GET)
    public Message searchReviewDetail(@RequestParam(value = "pid") Long pid){
        return auditEntryService.searchEntryDetail(pid.toString());
    }

}
