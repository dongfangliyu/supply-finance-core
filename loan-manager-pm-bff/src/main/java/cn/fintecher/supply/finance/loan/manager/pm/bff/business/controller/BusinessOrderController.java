package cn.fintecher.supply.finance.loan.manager.pm.bff.business.controller;

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
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessOrderService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Api(value = "供应商应收账款管理", tags = "供应商应收账款管理")
@RestController
@RequestMapping("/business/order")
public class BusinessOrderController {

    @Autowired
    private BusinessOrderService businessOrderService;
    

	@ApiOperation(value="应收账款凭证列表", notes="应收账款凭证列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchListOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListOrder(@RequestBody BusinessOrderFrom businessOrderFrom ,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
        	businessOrderFrom.setUserName(getUserName(principa));
            Message message =businessOrderService.searchListOrder(businessOrderFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="查看应收账款/确认", notes="查看应收账款/确认")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectOrderDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderDetail(@RequestParam(value="id")Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.selectOrderDetail(id,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="确认无误，确认", notes="确认无误，确认")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitConfirm", method = RequestMethod.GET)
    public Message submitConfirm(@RequestParam(value="id")Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.submitConfirm(id,getUserName(principa));
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
