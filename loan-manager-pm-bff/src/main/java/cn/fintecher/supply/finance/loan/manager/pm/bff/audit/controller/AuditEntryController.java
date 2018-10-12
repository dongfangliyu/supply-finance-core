package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:41
 */
@RestController
@RequestMapping("/audit/auditEntry")
@Api(tags = "还款录入")
public class AuditEntryController {

    @Autowired
    private AuditEntryService auditEntryService;

    /**
     * 还款录入列表
     * @param auditRemindForm
     * @return
     */
    @ApiOperation(value="还款录入列表 ", notes="还款录入列表")
    @RequestMapping(value ="/searchEntryList", method = RequestMethod.POST)
    public Message searchEntryList(@RequestBody AuditRemindForm auditRemindForm){
        Message message = auditEntryService.searchEntryList(auditRemindForm);
        return message;
    }

    /**
     * 还款录入详情
     * @return
     */
    @ApiOperation(value="还款录入详情 ", notes="还款录入详情")
    @RequestMapping(value ="/searchEntryDetail", method = RequestMethod.GET)
    public Message searchEntryDetail(@RequestParam(value = "pid") String pid){
        Message message = auditEntryService.searchEntryDetail(pid);
        return message;
    }

    /**
     * 提醒设置列表
     * @return
     */
    @ApiOperation(value="还款录入保存 ", notes="还款录入保存")
    @RequestMapping(value ="/searchEntry", method = RequestMethod.POST)
    public Message searchEntry(@RequestBody AuditEntryForm auditEntryForm){
        Message message = auditEntryService.searchEntry(auditEntryForm);
        return message;
    }

    /**
     * 还款录入保存
     * @return
     */
    @ApiOperation(value="还款录入保存 ", notes="还款录入保存")
    @RequestMapping(value ="/submitEntry", method = RequestMethod.POST)
    public Message submitEntry(@RequestBody AuditEntryForm auditEntryForm){
        Message message = auditEntryService.submitEntry(auditEntryForm);
        return message;
    }


}
