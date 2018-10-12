package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 18:36 2018/7/23
 */
@RestController
@RequestMapping("/audit/auditOrder")
public class AuditFinanceLoanController extends BaseCompanyUserController {

    @Autowired
    private AuditFinanceLoanService auditFinanceLoanService;

    @ResponseBody
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningListForm auditSigningListForm){
        try {
            auditSigningListForm.setCurrentCompanyId(getCompanyUser(auditSigningListForm.getCurrentUserName()).getEnterpriseId());
            return auditFinanceLoanService.searchSigningList(auditSigningListForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/searchSigningDetail", method = RequestMethod.GET)
    public Message searchSigningDetail(@RequestParam(value="id") String id){
        return auditFinanceLoanService.searchSigningDetail(id);
    }
}
