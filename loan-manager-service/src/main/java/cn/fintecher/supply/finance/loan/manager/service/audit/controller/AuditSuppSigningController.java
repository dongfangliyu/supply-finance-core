package cn.fintecher.supply.finance.loan.manager.service.audit.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:37
 */
@RestController
@RequestMapping("/audit/auditSuppSigning")
public class AuditSuppSigningController extends BaseCompanyUserController {

    @Autowired
    private AuditSuppSigningService auditSigningService;

    @ResponseBody
    @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
    public Message isUpLoadContract(@RequestParam("pid")Long pid){
        try {

            return auditSigningService.isUpLoadContract(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 供应商签约管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
        try {
            auditSigningRequest.setCurrentCompanyId(getCompanyUser(auditSigningRequest.getCurrentUserName()).getEnterpriseId());
            return auditSigningService.searchSigningList(auditSigningRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }


    /**
     * 供应商签约详情
     */
    @ResponseBody
    @RequestMapping(value = "/searchSigningDetail", method = RequestMethod.GET)
    public Message searchSigningDetail(@RequestParam("pid") Long pid){
        try {
            return auditSigningService.searchSigningDetail(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }


    /**
     * 保存还款账户
     */
    @ResponseBody
    @RequestMapping(value = "/submitSigningAccount", method = RequestMethod.POST)
    public Message submitSigningAccount(@RequestBody AuditRepayBankInfoForm auditRepayBankInfoForm){
        try {
            return auditSigningService.submitSigningAccount(auditRepayBankInfoForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/submitSigning", method = RequestMethod.GET)
    public Message submitSigning(@RequestParam("pid")Long pid,@RequestParam("name") String name){
        try {
            return auditSigningService.submitSigning(pid,name);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/downloadContract", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadContract(@RequestParam("pid") Long pid){
        HttpHeaders headers = new HttpHeaders();
        try {
            return auditSigningService.downloadContract(pid);
        }catch (Exception e){
            e.printStackTrace();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage()), headers, HttpStatus.OK);
        }
    }

}
