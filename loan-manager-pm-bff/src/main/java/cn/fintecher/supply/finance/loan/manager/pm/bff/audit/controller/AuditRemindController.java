package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditRemindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:30
 */
@RestController
@RequestMapping("/audit/auditRemind")
@Api(tags = "还款提醒")
public class AuditRemindController {

    @Autowired
    private AuditRemindService auditRemindService;

    /**
     * 还款提醒列表
     * @param auditRemindForm
     * @return
     */
    @ApiOperation(value="还款提醒列表 ", notes="还款提醒列表")
    @RequestMapping(value ="/searchRemindList", method = RequestMethod.POST)
    public Message searchRemindList(@RequestBody AuditRemindForm auditRemindForm){
        Message message = auditRemindService.searchRemindList(auditRemindForm);
        return message;
    }

    /**
     * 还款提醒详情
     * @return
     */
    @ApiOperation(value="还款提醒详情 ", notes="还款提醒详情")
    @RequestMapping(value ="/searchRemindDetail", method = RequestMethod.GET)
    public Message searchRemindDetail(@RequestParam(value = "pid") String pid){
        Message message = auditRemindService.searchRemindDetail(pid);
        return message;
    }

    /**
     * 提醒设置列表
     * @return
     */
    @ApiOperation(value="提醒设置列表 ", notes="提醒设置列表")
    @RequestMapping(value ="/searchRemind", method = RequestMethod.POST)
    public Message searchRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
        Message message = auditRemindService.searchRemind(auditTaskRemindForm);
        return message;
    }

    /**
     * 设置/修改提醒
     * @return
     */
    @ApiOperation(value="设置/修改提醒 ", notes="设置/修改提醒")
    @RequestMapping(value ="/submitRemind", method = RequestMethod.POST)
    public Message submitRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
        Message message = auditRemindService.submitRemind(auditTaskRemindForm);
        return message;
    }

    /**
     * 删除提醒
     * @return
     */
    @ApiOperation(value="删除提醒", notes="删除提醒")
    @RequestMapping(value ="/deleteRemind", method = RequestMethod.GET)
    public Message deleteRemind(@RequestParam(value = "pid") String pid){
        Message message = auditRemindService.deleteRemind(pid);
        return message;
    }


}
