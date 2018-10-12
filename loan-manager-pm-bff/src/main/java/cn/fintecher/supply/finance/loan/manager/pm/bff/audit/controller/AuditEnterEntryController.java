package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.CompanyUserService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEnterEntryService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author gonghebin
 * @date 2018/7/24 0024下午 3:56
 */
@RestController
@RequestMapping("/audit/auditEnterEntry")
@Api(tags = "核心企业还款录入")
public class AuditEnterEntryController {

    @Autowired
    private AuditEnterEntryService auditEnterEntryService;

    @Autowired
    private CompanyUserService companyUserService;


    /**
     * 还款录入列表
     * @param auditRemindForm
     * @return
     */
    @ApiOperation(value="还款录入列表 ", notes="还款录入列表")
    @RequestMapping(value ="/searchEntryList", method = RequestMethod.POST)
    public Message searchEntryList(@RequestBody AuditRemindForm auditRemindForm, Principal principal){
        Message<Object> message = new Message<>();
        try {
            if (!Strings.isNullOrEmpty(principal.getName())){
                CompanyUserEntity companyUserEntity = companyUserService.findCompanyUserByName(principal.getName());
                if (companyUserEntity != null){
                    auditRemindForm.setEnterpriseId(companyUserEntity.getEnterpriseId());
                }
            }else {
               message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
               message.setMessage("登陆过期,请重新登陆!");
               return message;
            }
            message = auditEnterEntryService.searchEntryList(auditRemindForm);
            return message;
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 还款录入详情
     * @return
     */
    @ApiOperation(value="还款录入详情 ", notes="还款录入详情")
    @RequestMapping(value ="/searchEntryDetail", method = RequestMethod.GET)
    public Message searchEntryDetail(@RequestParam(value = "pid") String pid){
        Message message = auditEnterEntryService.searchEntryDetail(pid);
        return message;
    }

    /**
     * 还款录入保存
     * @return
     */
    @ApiOperation(value="还款录入保存 ", notes="还款录入保存")
    @RequestMapping(value ="/submitEntry", method = RequestMethod.POST)
    public Message submitEntry(@RequestBody AuditEntryForm auditEntryForm){
        Message message = auditEnterEntryService.submitEntry(auditEntryForm);
        return message;
    }

}
