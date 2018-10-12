package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppSigningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author WuTianJuan
 * @Date Created in 18:30 2018/7/23
 */
@Api(tags = "融资放款管理")
@RestController
@RequestMapping("/audit/auditOrder")
public class AuditFinanceLoanController {
    @Autowired
    private AuditFinanceLoanService auditFinanceLoanService;

    @ApiOperation(value = "查询融资放款列表", notes = "查询融资放款列表")
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningListForm auditSigningListForm,Principal principal){
        try {
            auditSigningListForm.setCurrentUserName(principal.getName());
            return auditFinanceLoanService.searchSigningList(auditSigningListForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    @ApiOperation(value = "查询融资放款详情", notes = "查询融资放款详情")
    @RequestMapping(value = "/searchSigningDetail", method = RequestMethod.GET)
    public Message searchSigningDetail(@RequestParam(value="id") String id){
        return auditFinanceLoanService.searchSigningDetail(id);
    }
}
