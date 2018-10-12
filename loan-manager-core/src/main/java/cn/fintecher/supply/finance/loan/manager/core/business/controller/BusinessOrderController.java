package cn.fintecher.supply.finance.loan.manager.core.business.controller;


import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessOrderService;



/**
 * 订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@RestController
@RequestMapping("business/order")
public class BusinessOrderController {
    @Autowired
    private BusinessOrderService businessOrderService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    public Message insertOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessOrderService.insertOrder(businessOrderEntity));
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
    public Message updateOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessOrderService.updateOrder(businessOrderEntity));
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
    public Message<List<BusinessOrderEntity>> selectByOrder(@RequestBody BusinessOrderEntity businessOrderEntity){
    	Message<List<BusinessOrderEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessOrderService.selectByOrder(businessOrderEntity));
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
    public Message<BusinessOrderEntity> queryOrderByPid(@RequestParam("pid") String pid){
    	Message<BusinessOrderEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessOrderService.queryOrderByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
	 * 分页查询条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageCount", method = RequestMethod.POST)
	public Message queryPageCount(@RequestBody BusinessOrderFrom businessOrderFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(businessOrderService.queryPageCount(businessOrderFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}

	/**
	 * 分页查询List内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageList", method = RequestMethod.POST)
	public Message queryPageList(@RequestBody BusinessOrderFrom businessOrderFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(businessOrderService.queryPageList(businessOrderFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}

	/**
	 * 分页查询List内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryFinancingPageList", method = RequestMethod.POST)
	public Message queryFinancingPageList(@RequestBody BusinessFinancingRequest businessFinancingRequest){
		Message msg = new Message(MessageType.MSG_SUCCESS,"business_core",null);
		try {
			msg.setMessage(businessOrderService.queryFinancingPageList(businessFinancingRequest));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}

	/**
	 * 分页查询条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryFinancingPageCount", method = RequestMethod.POST)
	public Message queryFinancingPageCount(@RequestBody BusinessFinancingRequest businessFinancingRequest){
		Message msg = new Message(MessageType.MSG_SUCCESS,"business_core",null);
		try {
			msg.setMessage(businessOrderService.queryFinancingPageCount(businessFinancingRequest));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}
}