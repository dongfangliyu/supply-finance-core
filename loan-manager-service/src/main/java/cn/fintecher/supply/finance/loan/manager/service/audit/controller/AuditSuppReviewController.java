package cn.fintecher.supply.finance.loan.manager.service.audit.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppReviewService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:22
 */
@RestController
@RequestMapping("/audit/auditSuppReview")
public class AuditSuppReviewController extends BaseCompanyUserController {
    @Autowired
    private AuditSuppReviewService auditSuppReviewService;

    @ResponseBody
    @RequestMapping(value = "/searchSuppReviewList", method = RequestMethod.POST)
    public Message searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest){
        try {
            auditSuppReviewRequest.setSupplierId(getCompanyUser(auditSuppReviewRequest.getCurrentUserName()).getEnterpriseId());
            return auditSuppReviewService.searchSuppReviewList(auditSuppReviewRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    @ResponseBody
    @RequestMapping(value = "/searchSuppReviewDetail", method = RequestMethod.GET)
    public Message searchSuppReviewDetail(@RequestParam("pid") Long pid){
        try {

            return auditSuppReviewService.searchSuppReviewDetail(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }
}
