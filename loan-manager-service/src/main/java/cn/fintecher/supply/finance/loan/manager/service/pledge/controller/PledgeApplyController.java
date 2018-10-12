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
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 仓库质押
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/pledge/apply")
public class PledgeApplyController extends BaseCompanyUserController{

    @Autowired
    private PledgeApplyService pledgeApplyService;
    

	@ApiOperation(value="", notes="")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchApplyList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchApplyList(@RequestBody PledgeApplyForm pledgeApplyForm){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
        	CompanyUserEntity user = getCompanyUser(pledgeApplyForm.getUserName());
            Message message =pledgeApplyService.searchApplyList(pledgeApplyForm,user);
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
    @RequestMapping(value ="/searchApplyDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchApplyDetail(@RequestParam("id") Long id,@RequestParam("userName") String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
        	CompanyUserEntity user = getCompanyUser(userName);
            Message message =pledgeApplyService.searchApplyDetail(id,user);
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
    @RequestMapping(value ="/submitApply", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitApply(@RequestBody PledgeApplySubmit pledgeApplySubmit){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
        	CompanyUserEntity user = getCompanyUser(pledgeApplySubmit.getUserName());
            Message message =pledgeApplyService.submitApply(pledgeApplySubmit,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   


   

}
