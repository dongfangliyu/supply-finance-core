package cn.fintecher.supply.finance.loan.manager.core.audit.controller;


import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskService;



/**
 * 审批交易订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@RestController
@RequestMapping("audit/task")
public class AuditTaskController {
    @Autowired
    private AuditTaskService auditTaskService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertTask", method = RequestMethod.POST)
    public Message insertTask(@RequestBody AuditTaskEntity auditTaskEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskService.insertTask(auditTaskEntity));
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
    @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
    public Message updateTask(@RequestBody AuditTaskEntity auditTaskEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskService.updateTask(auditTaskEntity));
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
    @RequestMapping(value = "/selectByTask", method = RequestMethod.POST)
    public Message<List<AuditTaskEntity>> selectByTask(@RequestBody AuditTaskEntity auditTaskEntity){
    	Message<List<AuditTaskEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskService.selectByTask(auditTaskEntity));
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
    @RequestMapping(value = "/queryTaskByPid", method = RequestMethod.GET)
    public Message<AuditTaskEntity> queryTaskByPid(@RequestParam("pid") String pid){
    	Message<AuditTaskEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskService.queryTaskByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

    /**
	 * 查询保兑初审待处理任务数量
	 * */
	@ResponseBody
	@RequestMapping(value = "/countConfirmingTaskWaitNum", method = RequestMethod.GET)
	public Message<Integer> countConfirmingTaskWaitNum(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "node")Integer node){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"auditTask_core",auditTaskService.countConfirmingTaskWaitNum(userId,node));
		}catch (Exception e){
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"auditTask_core",e.getMessage());
		}
	}


	@ResponseBody
	@RequestMapping(value = "/countConfirmingApprovalList", method = RequestMethod.POST)
	public Message<Integer> countConfirmingApprovalList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"auditTask_core",auditTaskService.countConfirmingApprovalList(confirmingStockInfoApprovalResquest));
		}catch (Exception e){
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"auditTask_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/confirmingApprovalList", method = RequestMethod.POST)
	public Message<List<ConfirmingStockInfoListResponse>> confirmingApprovalList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest){
		try {
			return new Message<List<ConfirmingStockInfoListResponse>>(MessageType.MSG_SUCCESS,"auditTask_core",auditTaskService.confirmingApprovalList(confirmingStockInfoApprovalResquest));
		}catch (Exception e){
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"auditTask_core",e.getMessage());
		}
	}


}