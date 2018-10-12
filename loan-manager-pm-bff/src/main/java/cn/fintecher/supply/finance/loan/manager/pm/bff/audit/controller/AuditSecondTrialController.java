package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSecondTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 10:01:21
 */
@Api(value = "审核管理——复审相关接口", tags = "审核管理——复审相关接口")
@RestController
@RequestMapping("/audit/auditSecondTrial")
public class AuditSecondTrialController {

    @Autowired
    private AuditSecondTrialService auditsecondTrialService;
    

	@ApiOperation(value="领取任务", notes="领取任务")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditsecondTrialService.receiveTask(getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="复审审批列表", notes="复审审批列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchOrderList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchOrderList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
        	auditOrderInfoFrom.setUserId(getUserName(principa));
            Message message =auditsecondTrialService.searchOrderList(auditOrderInfoFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="处理详情", notes="处理详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetail", method = RequestMethod.GET)
    public Message searchDetail(@RequestParam("id") Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditsecondTrialService.searchDetail(id,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="提交复审意见", notes="提交复审意见")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitContnet", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitContnet(@RequestBody AuditSumbitContentRequest auditSumbitContentRequest,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
        	auditSumbitContentRequest.setUserName(getUserName(principa));
            Message message =auditsecondTrialService.submitContnet(auditSumbitContentRequest);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="审核通过后查看详情", notes="审核通过后查看详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetailResult", method = RequestMethod.GET)
    public Message searchDetailResult(@RequestParam("id") Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditsecondTrialService.searchDetailResult(id,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
	private String getUserName(Principal principa){
		return principa.getName();
	}  


   

}
