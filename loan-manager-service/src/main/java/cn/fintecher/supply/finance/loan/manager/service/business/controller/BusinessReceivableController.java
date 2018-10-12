package cn.fintecher.supply.finance.loan.manager.service.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessReceivableService;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/business/Receivable")
public class BusinessReceivableController {

    @Autowired
    private BusinessReceivableService businessReceivableService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.insertReceivable(businessReceivableEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.updateReceivable(businessReceivableEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectByReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.selectByReceivable(businessReceivableEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryReceivableByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message queryReceivableByPid(@RequestParam("pid") String pid){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.queryReceivableByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/searchListReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListReceivable(@RequestBody BusinessReceivableFrom businessReceivableFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.searchListReceivable(businessReceivableFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/submitReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.submitReceivable(businessReceivableSubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/inviteSupplier", method = RequestMethod.GET)
    public Message inviteSupplier(@RequestParam("receivableId")Long receivableId,@RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.inviteSupplier(receivableId,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectReceivableDetail", method = RequestMethod.GET)
    public Message selectReceivableDetail(@RequestParam("receivableId")Long receivableId,@RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.selectReceivableDetail(receivableId,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/submitUpdateReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitUpdateReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.submitUpdateReceivable(businessReceivableSubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
	
	  @RequestMapping(value = "/deleteReceivable", method = RequestMethod.POST)
		public Message deleteReceivable(@RequestParam("id")String id, @RequestParam("userName")String userName){
		  Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
	        try {
	            Message message =businessReceivableService.deleteReceivable(id,userName);
	            msg.setCode(message.getCode());
	            msg.setMessage(message.getMessage());
	        } catch (Exception e) {
	        	e.printStackTrace();
	            msg.setCode(MessageType.MSG_ERROR);
	        }
	        return msg;
	  };
   


   

}
