package cn.fintecher.supply.finance.loan.manager.core.audit.controller;


import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.response.MarginDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskEntryService;



/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:15
 */
@RestController
@RequestMapping("/audit/taskEntry")
public class AuditTaskEntryController {
    @Autowired
    private AuditTaskEntryService auditTaskEntryService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertTaskEntry", method = RequestMethod.POST)
    public Message insertTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.insertTaskEntry(auditTaskEntryEntity));
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
    @RequestMapping(value = "/updateTaskEntry", method = RequestMethod.POST)
    public Message updateTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.updateTaskEntry(auditTaskEntryEntity));
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
    @RequestMapping(value = "/selectByTaskEntry", method = RequestMethod.POST)
    public Message<List<AuditTaskEntryEntity>> selectByTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity){
    	Message<List<AuditTaskEntryEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.selectByTaskEntry(auditTaskEntryEntity));
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
    @RequestMapping(value = "/queryTaskEntryByPid", method = RequestMethod.GET)
    public Message<AuditTaskEntryEntity> queryTaskEntryByPid(@RequestParam("pid") String pid){
    	Message<AuditTaskEntryEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.queryTaskEntryByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 根据orderInfo id查询已付款总额
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryPaymentCountByOrderInfoId", method = RequestMethod.GET)
    public Message<Integer> queryPaymentCountByOrderInfoId(@RequestParam("orderInfoId") String orderInfoId){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.queryPaymentCountByOrderInfoId(orderInfoId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }



	@ResponseBody
	@RequestMapping(value = "/searchTaskEntryListCount", method = RequestMethod.POST)
	public int searchTaskEntryListCount(@RequestBody AuditEntryForm auditEntryForm){
    	return auditTaskEntryService.searchTaskEntryListCount(auditEntryForm);
	}

	@ResponseBody
	@RequestMapping(value = "/searchTaskEntryList", method = RequestMethod.POST)
	public List<AuditTaskEntryEntity> searchTaskEntryList(@RequestBody AuditEntryForm auditEntryForm){
		return auditTaskEntryService.searchTaskEntryList(auditEntryForm);
	}

	@ResponseBody
	@RequestMapping(value = "/searchSumOfRepaymentPrice", method = RequestMethod.GET)
	public Double searchSumOfRepaymentPrice(@RequestParam(value = "pid") Long pid){
		return auditTaskEntryService.searchSumOfRepaymentPrice(pid);
	}

	@ResponseBody
	@RequestMapping(value = "/searchTaskEntityByOrderId", method = RequestMethod.GET)
	public List<AuditTaskEntryEntity> searchTaskEntityByOrderId(@RequestParam(value = "pid") Long pid){
		return auditTaskEntryService.searchTaskEntityByOrderId(pid);
	}

	@ResponseBody
	@RequestMapping(value = "/searchSumOfRepaymentPriceByState", method = RequestMethod.GET)
	public Double searchSumOfRepaymentPriceByState(@RequestParam(value = "orderId") Long orderId){
		return auditTaskEntryService.searchSumOfRepaymentPriceByState(orderId);
	}
	
	@RequestMapping(value = "/searchMarginDetail", method = RequestMethod.POST)
    public Message<List<MarginDetailResponse>> searchMarginDetail(@RequestBody SearchMarginDetailForm searchMarginDetailForm){
    	Message<List<MarginDetailResponse>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskEntryService.searchMarginDetail(searchMarginDetailForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }

}