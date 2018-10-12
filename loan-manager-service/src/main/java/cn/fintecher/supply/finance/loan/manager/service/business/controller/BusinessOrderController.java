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
import java.util.List;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/business/order")
public class BusinessOrderController {

    @Autowired
    private BusinessOrderService businessOrderService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.insertOrder(businessOrderEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.updateOrder(businessOrderEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<BusinessOrderEntity>> selectByOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
        Message<List<BusinessOrderEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message<List<BusinessOrderEntity>> message =businessOrderService.selectByOrder(businessOrderEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryOrderByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<BusinessOrderEntity> queryOrderByPid(@RequestParam("pid") String pid){
        Message<BusinessOrderEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message<BusinessOrderEntity> message =businessOrderService.queryOrderByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/searchListOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListOrder(@RequestBody BusinessOrderFrom businessOrderFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.searchListOrder(businessOrderFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectOrderDetail", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderDetail(@RequestParam("orderId")Long orderId,@RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.selectOrderDetail(orderId,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/submitConfirm", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitConfirm(@RequestParam("orderId")Long orderId,@RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessOrderService.submitConfirm(orderId,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   


   

}
