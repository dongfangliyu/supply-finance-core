package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

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
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/audit/task")
public class AuditTaskController {

    @Autowired
    private AuditTaskService auditTaskService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertTask", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertTask(@RequestBody AuditTaskEntity auditTaskEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditTaskService.insertTask(auditTaskEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateTask", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateTask(@RequestBody AuditTaskEntity auditTaskEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditTaskService.updateTask(auditTaskEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByTask", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<AuditTaskEntity>> selectByTask(@RequestBody AuditTaskEntity auditTaskEntity){
        Message<List<AuditTaskEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<List<AuditTaskEntity>> message =auditTaskService.selectByTask(auditTaskEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryTaskByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<AuditTaskEntity> queryTaskByPid(@RequestParam("pid") String pid){
        Message<AuditTaskEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<AuditTaskEntity> message =auditTaskService.queryTaskByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
   


   

}
