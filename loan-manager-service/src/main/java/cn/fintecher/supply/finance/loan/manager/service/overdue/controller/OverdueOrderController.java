package cn.fintecher.supply.finance.loan.manager.service.overdue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
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
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderService;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderTaskService;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/overdue/order")
public class OverdueOrderController {

	@Autowired
	private OverdueOrderService overdueOrderService;

	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/insertOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message insertOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.insertOrder(overdueOrderEntity);
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
	public Message updateOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.updateOrder(overdueOrderEntity);
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
	public Message<List<OverdueOrderEntity>> selectByOrder(@RequestBody OverdueOrderEntity overdueOrderEntity){
		Message<List<OverdueOrderEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message<List<OverdueOrderEntity>> message =overdueOrderService.selectByOrder(overdueOrderEntity);
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
	public Message<OverdueOrderEntity> queryOrderByPid(@RequestParam("pid") String pid){
		Message<OverdueOrderEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message<OverdueOrderEntity> message =overdueOrderService.queryOrderByPid(pid);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}

	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/searchOrderList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message searchOrderList(@RequestBody OverdueOrderForm overdueOrderForm){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.searchOrderList(overdueOrderForm);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/searchOrderDetail", method = RequestMethod.GET)
	public Message searchOrderDetail(@RequestParam("pid")Long pid){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.searchOrderDetail(pid);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/submitRepaymentRecord", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message submitRepaymentRecord(@RequestBody OverdueOrderRepaymentForm overdueOrderRepaymentForm){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.submitRepaymentRecord(overdueOrderRepaymentForm);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/searchRepaymenList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message searchRepaymenList(@RequestBody OverdueOrderRecordForm overdueOrderRecordForm){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.searchRepaymenList(overdueOrderRecordForm);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/downLoadOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message downLoadOrder(@RequestBody OverdueOrderForm overdueOrderForm){
		Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
		try {
			Message message =overdueOrderService.downLoadOrder(overdueOrderForm);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@Autowired
	private OverdueOrderTaskService overdueOrderTaskService;


	/**
	 * 立刻插入逾期信息
	 */
	@ApiOperation(value="", notes="")
	@RequestMapping(value ="/createOverdueOrder", method = RequestMethod.GET)
	public void createOverdueOrder(){
		overdueOrderTaskService.createOverdueOrder();

	}




}
