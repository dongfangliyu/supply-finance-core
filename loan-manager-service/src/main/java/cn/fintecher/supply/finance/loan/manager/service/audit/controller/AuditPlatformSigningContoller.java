package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditPlatformSigningService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 14:15
 */
@RestController
@RequestMapping("/audit/auditPlatformSigning")
public class AuditPlatformSigningContoller extends BaseCompanyUserController {

    @Autowired
    private AuditPlatformSigningService auditPlatformSigningService;

    /**
     * 平台签约管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
        try {
            return auditPlatformSigningService.searchPlatformSigningList(auditSigningRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    @ResponseBody
    @RequestMapping(value = "/submitSigning", method = RequestMethod.GET)
    public Message submitSigning(@RequestParam("pid")Long pid,@RequestParam("name") String name){
        try {
            return auditPlatformSigningService.submitSigning(pid,name);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
        }
    }
}
