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
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskHistoryService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/audit/taskHistory")
public class AuditTaskHistoryController {

    @Autowired
    private AuditTaskHistoryService auditTaskHistoryService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertTaskHistory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditTaskHistoryService.insertTaskHistory(auditTaskHistoryEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateTaskHistory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditTaskHistoryService.updateTaskHistory(auditTaskHistoryEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByTaskHistory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<AuditTaskHistoryEntity>> selectByTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity){
        Message<List<AuditTaskHistoryEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<List<AuditTaskHistoryEntity>> message =auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryTaskHistoryByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<AuditTaskHistoryEntity> queryTaskHistoryByPid(@RequestParam("pid") String pid){
        Message<AuditTaskHistoryEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<AuditTaskHistoryEntity> message =auditTaskHistoryService.queryTaskHistoryByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
   


   

}
