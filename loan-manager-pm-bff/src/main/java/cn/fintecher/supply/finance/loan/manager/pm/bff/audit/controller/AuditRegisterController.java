package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 8:32
 */
@RestController
@RequestMapping("/audit/auditRegister")
@Api(tags = "注册审核")
public class AuditRegisterController {

    @Autowired
    private AuditRegisterService auditRegisterService;

    /**
     * 查询列表注册审核
     * @param auditRegisterForm
     * @return
     */
    @ApiOperation(value="查询列表注册审核 ", notes="查询列表注册审核")
    @RequestMapping(value ="/searchListAuditRegister", method = RequestMethod.POST)
    public Message searchListAuditRegister(@RequestBody AuditRegisterForm auditRegisterForm){
        Message message = auditRegisterService.searchListAuditRegister(auditRegisterForm);
        return message;
    }

    /**
     * 查询企业审核注册审信息
     * @return
     */
    @ApiOperation(value="查询企业审核注册审信息 ", notes="查询企业审核注册审信息")
    @RequestMapping(value ="/searchAuditRegisterCompanyInfo", method = RequestMethod.GET)
    public Message searchAuditRegisterCompanyInfo(@RequestParam(value = "pid") String pid){
        Message message = auditRegisterService.searchAuditRegisterCompanyInfo(pid);
        return message;
    }


    /**
     * 提交企业认证审核结果
     * @return
     */
    @ApiOperation(value="提交企业认证审核结果 ", notes="提交企业认证审核结果")
    @RequestMapping(value ="/submitAuditRegisterResult", method = RequestMethod.POST)
    public Message submitAuditRegisterResult(@RequestBody AuditRegisterResultForm auditRegisterResultForm){
        Message message = auditRegisterService.submitAuditRegisterResult(auditRegisterResultForm);
        return message;
    }


    /**
     * 查询企业基本信息
     * @return
     */
    @ApiOperation(value="查询企业基本信息 ", notes="查询企业基本信息")
    @RequestMapping(value ="/searchCompanyInfo", method = RequestMethod.GET)
    public Message searchCompanyInfo(@RequestParam(value = "pid") String pid){
        Message message = auditRegisterService.searchCompanyInfo(pid);
        return message;
    }

    /**
     * 提交发送链接
     * @return
     */
    @ApiOperation(value="提交发送链接 ", notes="提交发送链接")
    @RequestMapping(value ="/submitSendLink", method = RequestMethod.GET)
    public Message submitSendLink(@RequestParam(value = "pid") String pid){
        Message message = auditRegisterService.submitSendLink(pid);
        return message;
    }

    /**
     * 查询待处理数据
     * @return
     */
    @ApiOperation(value="查询待处理数据 ", notes="查询待处理数据")
    @RequestMapping(value ="/searchAuditCreditStatus", method = RequestMethod.GET)
    public Message searchAuditCreditStatus(){
        Message message = auditRegisterService.searchAuditCreditStatus();
        return message;
    }

}
