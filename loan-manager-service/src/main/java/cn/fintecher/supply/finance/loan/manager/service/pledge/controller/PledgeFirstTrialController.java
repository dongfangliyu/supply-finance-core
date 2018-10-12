package cn.fintecher.supply.finance.loan.manager.service.pledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeFirstTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/pledge/firstTrial")
public class PledgeFirstTrialController extends BaseSysUserController{

    @Autowired
    private PledgeFirstTrialService pledgeFirstTrialService;
    

	@ApiOperation(value="", notes="")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/receiveTask", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	 public Message receiveTask(@RequestParam("userName") String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeFirstTrialService.receiveTask(getSysUser(userName));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchPledgeList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message searchPledgeList(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeFirstTrialService.searchPledgeList(pledgeStockTrialFrom,getSysUser(pledgeStockTrialFrom.getUserId()));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message searchDetail(@RequestParam("id")Long id,@RequestParam("userName") String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeFirstTrialService.searchDetail(id,getSysUser(userName));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitContnet", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message submitContnet(@RequestBody PledgeSumbitTrialRequest pledgeApplyInfoResponse){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeFirstTrialService.submitContnet(pledgeApplyInfoResponse,getSysUser(pledgeApplyInfoResponse.getUserName()));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   


   

}
