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
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditOrderInfoService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/audit/orderInfo")
public class AuditOrderInfoController {

    @Autowired
    private AuditOrderInfoService auditOrderInfoService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertOrderInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditOrderInfoService.insertOrderInfo(auditOrderInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateOrderInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditOrderInfoService.updateOrderInfo(auditOrderInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByOrderInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<AuditOrderInfoEntity>> selectByOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
        Message<List<AuditOrderInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<List<AuditOrderInfoEntity>> message =auditOrderInfoService.selectByOrderInfo(auditOrderInfoEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryOrderInfoByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<AuditOrderInfoEntity> queryOrderInfoByPid(@RequestParam("pid") String pid){
        Message<AuditOrderInfoEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message<AuditOrderInfoEntity> message =auditOrderInfoService.queryOrderInfoByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
   


   

}
