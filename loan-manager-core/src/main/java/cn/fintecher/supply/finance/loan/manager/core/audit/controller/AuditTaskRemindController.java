package cn.fintecher.supply.finance.loan.manager.core.audit.controller;


import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskRemindService;



/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:19
 */
@RestController
@RequestMapping("/audit/taskRemind")
public class AuditTaskRemindController {
    @Autowired
    private AuditTaskRemindService auditTaskRemindService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertTaskRemind", method = RequestMethod.POST)
    public Message insertTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskRemindService.insertTaskRemind(auditTaskRemindEntity));
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
    @RequestMapping(value = "/updateTaskRemind", method = RequestMethod.POST)
    public Message updateTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskRemindService.updateTaskRemind(auditTaskRemindEntity));
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
    @RequestMapping(value = "/selectByTaskRemind", method = RequestMethod.POST)
    public Message<List<AuditTaskRemindEntity>> selectByTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity){
    	Message<List<AuditTaskRemindEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskRemindService.selectByTaskRemind(auditTaskRemindEntity));
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
    @RequestMapping(value = "/queryTaskRemindByPid", method = RequestMethod.GET)
    public Message<AuditTaskRemindEntity> queryTaskRemindByPid(@RequestParam("pid") String pid){
    	Message<AuditTaskRemindEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditTaskRemindService.queryTaskRemindByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }


	@ResponseBody
	@RequestMapping(value = "/searchRemindCount", method = RequestMethod.POST)
	public int searchRemindCount(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
		return auditTaskRemindService.searchRemindCount(auditTaskRemindForm);
	}

	@ResponseBody
	@RequestMapping(value = "/searchRemind", method = RequestMethod.POST)
	public List<AuditTaskRemindEntity> searchRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm){
		return auditTaskRemindService.searchRemind(auditTaskRemindForm);
	}

	@ResponseBody
	@RequestMapping(value = "/selectAllRemind", method = RequestMethod.GET)
	public List<AuditTaskRemindEntity> selectAllRemind(){
		return auditTaskRemindService.selectAllRemind();
	}
    

}