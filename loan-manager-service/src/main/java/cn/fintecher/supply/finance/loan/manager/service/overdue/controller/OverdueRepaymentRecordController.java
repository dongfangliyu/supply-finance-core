package cn.fintecher.supply.finance.loan.manager.service.overdue.controller;

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
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueRepaymentRecordService;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/overdue/repaymentRecord")
public class OverdueRepaymentRecordController {

    @Autowired
    private OverdueRepaymentRecordService overdueRepaymentRecordService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertRepaymentRecord", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
            Message message =overdueRepaymentRecordService.insertRepaymentRecord(overdueRepaymentRecordEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateRepaymentRecord", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
            Message message =overdueRepaymentRecordService.updateRepaymentRecord(overdueRepaymentRecordEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByRepaymentRecord", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<List<OverdueRepaymentRecordEntity>> selectByRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
        Message<List<OverdueRepaymentRecordEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
        try {
            Message<List<OverdueRepaymentRecordEntity>> message =overdueRepaymentRecordService.selectByRepaymentRecord(overdueRepaymentRecordEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryRepaymentRecordByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<OverdueRepaymentRecordEntity> queryRepaymentRecordByPid(@RequestParam("pid") String pid){
        Message<OverdueRepaymentRecordEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
        try {
            Message<OverdueRepaymentRecordEntity> message =overdueRepaymentRecordService.queryRepaymentRecordByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
   


   

}
