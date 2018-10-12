package cn.fintecher.supply.finance.loan.manager.core.overdue.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;
import cn.fintecher.supply.finance.loan.manager.core.overdue.service.OverdueOrderService;



/**
 * 逾期订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@RestController
@RequestMapping("overdue/order")
public class OverdueOrderController {
    @Autowired
    private OverdueOrderService overdueOrderService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    public Message insertOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.insertOrder(overdueOrderEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public Message updateOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.updateOrder(overdueOrderEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByOrder", method = RequestMethod.POST)
    public Message<List<OverdueOrderEntity>> selectByOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
    	Message<List<OverdueOrderEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.selectByOrder(overdueOrderEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOrderByPid", method = RequestMethod.GET)
    public Message<OverdueOrderEntity> queryOrderByPid(@RequestParam("pid") String pid){
    	Message<OverdueOrderEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.queryOrderByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 分页查询list
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryListByOrderForm", method = RequestMethod.POST)
    public Message<List<OverdueOrderResponse>> queryListByOrderForm(@RequestBody OverdueOrderForm overdueOrderForm){
    	Message<List<OverdueOrderResponse>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.queryListByOrderForm(overdueOrderForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 分页查询总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCountByOrderForm", method = RequestMethod.POST)
    public Message<Integer> queryCountByOrderForm(@RequestBody OverdueOrderForm overdueOrderForm){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.queryCountByOrderForm(overdueOrderForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    
    /**
     * 分页查询excel list
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDownListByOrderForm", method = RequestMethod.POST)
    public Message<List<OverdueOrderResponse>> queryDownListByOrderForm(@RequestBody OverdueOrderForm overdueOrderForm){
    	Message<List<OverdueOrderResponse>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueOrderService.queryDownListByOrderForm(overdueOrderForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

}