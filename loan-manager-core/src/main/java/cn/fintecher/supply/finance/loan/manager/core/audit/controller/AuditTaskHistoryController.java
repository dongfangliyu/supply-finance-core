package cn.fintecher.supply.finance.loan.manager.core.audit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskHistoryService;



/**
 * 审批历史
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@RestController
@RequestMapping("audit/taskHistory")
public class AuditTaskHistoryController {
    @Autowired
    private AuditTaskHistoryService auditTaskHistoryService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertTaskHistory", method = RequestMethod.POST)
    public Message insertTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskHistoryService.insertTaskHistory(auditTaskHistoryEntity));
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
    @RequestMapping(value = "/updateTaskHistory", method = RequestMethod.POST)
    public Message updateTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskHistoryService.updateTaskHistory(auditTaskHistoryEntity));
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
    @RequestMapping(value = "/selectByTaskHistory", method = RequestMethod.POST)
    public Message<List<AuditTaskHistoryEntity>> selectByTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
    	Message<List<AuditTaskHistoryEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity));
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
    @RequestMapping(value = "/queryTaskHistoryByPid", method = RequestMethod.GET)
    public Message<AuditTaskHistoryEntity> queryTaskHistoryByPid(@RequestParam("pid") String pid){
    	Message<AuditTaskHistoryEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskHistoryService.queryTaskHistoryByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    

}