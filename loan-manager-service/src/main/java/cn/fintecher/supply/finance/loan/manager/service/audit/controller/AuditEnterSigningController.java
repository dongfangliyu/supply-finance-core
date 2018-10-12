package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEnterSigningService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 10:29
 */
@RestController
@RequestMapping("/audit/auditEnterSigning")
public class AuditEnterSigningController extends BaseCompanyUserController {
    @Autowired
    private AuditEnterSigningService auditEnterSigningService;

    @ResponseBody
    @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
    public Message isUpLoadContract(@RequestParam("pid")Long pid){
        try {

            return auditEnterSigningService.isUpLoadContract(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 核心企业签约管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
        try {
            auditSigningRequest.setCurrentCompanyId(getCompanyUser(auditSigningRequest.getCurrentUserName()).getEnterpriseId());
            return auditEnterSigningService.searchEnterSigningList(auditSigningRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    @ResponseBody
    @RequestMapping(value = "/submitSigning", method = RequestMethod.GET)
    public Message submitSigning(@RequestParam("pid")Long pid,@RequestParam("name") String name){
        try {
            return auditEnterSigningService.submitSigning(pid,name);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
        }
    }

}
