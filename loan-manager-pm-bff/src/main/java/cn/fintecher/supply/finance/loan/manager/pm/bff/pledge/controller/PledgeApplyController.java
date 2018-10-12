package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
@Api(value = "仓单质押接口", tags = "仓单质押接口")
@RestController
@RequestMapping("/pledge/apply")
public class PledgeApplyController {

    @Autowired
    private PledgeApplyService pledgeapplyService;
    

	@ApiOperation(value="列表", notes="列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchApplyList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<PledgeApplyListResponse>>> searchApplyList(@RequestBody PledgeApplyForm pledgeApplyForm,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        
        try {
        	pledgeApplyForm.setUserName(principa.getName());
            Message message =pledgeapplyService.searchApplyList(pledgeApplyForm);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="详情", notes="详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchApplyDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PledgeApplyInfoResponse> searchApplyDetail(@ApiParam(required = true, name = "id", value = "记录id")@RequestParam("id") Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeapplyService.searchApplyDetail(id,principa.getName());
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="提交", notes="提交")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitApply", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized Message submitApply(@RequestBody PledgeApplySubmit pledgeApplySubmit,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
        	pledgeApplySubmit.setUserName(principa.getName());
            Message message =pledgeapplyService.submitApply(pledgeApplySubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   


   

}
