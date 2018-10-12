package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 2:31
 */
@RestController
@RequestMapping("/audit/auditFinance")
@Api(tags = "财务审核")
public class AuditFinanceController {

    @Autowired
    private AuditFinanceService auditFinanceService;

    /**
     * 财务初审列表
     * @param auditFinanceForm
     * @return
     */
    @ApiOperation(value="财务初审列表 ", notes="财务初审列表")
    @RequestMapping(value ="/searchFinanceList", method = RequestMethod.POST)
    public Message searchFinanceList(@RequestBody AuditFinanceForm auditFinanceForm){
        Message message = auditFinanceService.searchFinanceList(auditFinanceForm);
        return message;
    }

    /**
     * 财务初审详情
     * @return
     */
    @ApiOperation(value="财务初审详情 ", notes="财务初审详情")
    @RequestMapping(value ="/searchFinanceDetail", method = RequestMethod.GET)
    public Message searchFinanceDetail(@RequestParam(value = "pid") String pid){
        Message message = auditFinanceService.searchFinanceDetail(pid);
        return message;
    }

    /**
     * 财务初审通过
     * @return
     */
    @ApiOperation(value="财务初审通过 ", notes="财务初审通过")
    @RequestMapping(value ="/submitFinancePass", method = RequestMethod.GET)
    public Message submitFinancePass(@RequestParam(value = "pid") String pid,Principal principa){
        Message<Object> message = new Message<>();
        if(null != principa){
            if (null == principa.getName()){
                message.setMessage("您还没有登陆,请登陆!");
                message.setCode(MsgCodeConstant.ERR_LOGIN_IN);
                return message;
            }
        }
        message = auditFinanceService.submitFinancePass(pid,getUserName(principa));
        return message;
    }

    /**
     * 财务初审拒绝
     * @return
     */
    @ApiOperation(value="财务初审拒绝 ", notes="财务初审拒绝")
    @RequestMapping(value ="/submitFinanceFail", method = RequestMethod.GET)
    public Message submitFinanceFail(@RequestParam(value = "pid") String pid,Principal principa){
        Message<Object> message = new Message<>();
        if(null != principa){
            if (null == principa.getName()){
                message.setMessage("您还没有登陆,请登陆!");
                message.setCode(MsgCodeConstant.ERR_LOGIN_IN);
                return message;
            }
        }
        message = auditFinanceService.submitFinanceFail(pid,getUserName(principa));
        return message;
    }

    private String getUserName(Principal principa){
    	return principa.getName();
    }


}
