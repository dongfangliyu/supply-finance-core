package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditLoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:51
 */
@RestController
@RequestMapping("/audit/auditLoan")
@Api(tags = "财务复核")
public class AuditLoanController {
    
    @Autowired
    private AuditLoanService auditLoanService;

    /**
     * 财务复审列表
     * @param auditFinanceForm
     * @return
     */
    @ApiOperation(value="财务复审列表 ", notes="财务复审列表")
    @RequestMapping(value ="/searchLoanList", method = RequestMethod.POST)
    public Message searchLoanList(@RequestBody AuditFinanceForm auditFinanceForm){
        Message message = auditLoanService.searchLoanList(auditFinanceForm);
        return message;
    }

    /**
     * 财务复审详情
     * @return
     */
    @ApiOperation(value="财务复审详情 ", notes="财务复审详情")
    @RequestMapping(value ="/searchLoanDetail", method = RequestMethod.GET)
    public Message searchLoanDetail(@RequestParam(value = "pid") String pid){
        Message message = auditLoanService.searchLoanDetail(pid);
        return message;
    }

    /**
     * 财务复审通过
     * @return
     */
    @ApiOperation(value="财务复审通过 ", notes="财务复审通过")
    @RequestMapping(value ="/submitLoanPass", method = RequestMethod.GET)
    public Message submitLoanPass(@RequestParam(value = "pid") String pid,@RequestParam(value = "loanTime") String loanTime,Principal principa){
        Message<Object> message = new Message<>();
        if(null != principa){
            if (null == principa.getName()){
                message.setCode(MsgCodeConstant.ERR_LOGIN_IN);
                message.setMessage("您还没有登陆,请登陆!");
                return message;
            }
        }
        message = auditLoanService.submitLoanPass(pid,loanTime,getUserName(principa));
        return message;
    }

    /**
     * 财务复审拒绝
     * @return
     */
    @ApiOperation(value="财务复审拒绝 ", notes="财务复审拒绝")
    @RequestMapping(value ="/submitLoanFail", method = RequestMethod.GET)
    public Message submitLoanFail(@RequestParam(value = "pid") String pid,Principal principa){
        Message<Object> message = new Message<>();
        if(null != principa){
            if (null == principa.getName()){
                message.setMessage("您还没有登陆,请登陆!");
                message.setCode(MsgCodeConstant.ERR_LOGIN_IN);
                return message;
            }
        }
        message = auditLoanService.submitLoanFail(pid,getUserName(principa));
        return message;
    }


    /**
     * 上传放款凭证
     */
    @ApiOperation(value = "上传放款凭证", notes = "上传放款凭证")
    @ResponseBody
    @RequestMapping(value = "/uploadAuditLoanFile", method = RequestMethod.POST)
    public Message uploadAuditLoanFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="pid") String pid) {
        return auditLoanService.uploadAuditLoanFile(file, type,pid);
    }

    private String getUserName(Principal principa){
    	return principa.getName();
    }



}
