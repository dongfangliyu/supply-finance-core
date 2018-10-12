package cn.fintecher.supply.finance.loan.manager.pm.bff.business.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessReceivableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Api(value = "账款凭证管理", tags = "账款凭证管理")
@RestController
@RequestMapping("/business/receivable")
public class BusinessReceivableController {

    @Autowired
    private BusinessReceivableService businessReceivableService;
    

	@ApiOperation(value="应付账款凭证列表", notes="应付账款凭证列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchListReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListReceivable(@RequestBody BusinessReceivableFrom businessReceivableFrom,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
        	businessReceivableFrom.setUserName(getUserName(principa));
            Message message =businessReceivableService.searchListReceivable(businessReceivableFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="保存应收账款信息", notes="保存应收账款信息")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
        	businessReceivableSubmit.setUserName(getUserName(principa));
            Message message =businessReceivableService.submitReceivable(businessReceivableSubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="邀请供应商", notes="邀请供应商")
        @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
        @RequestMapping(value ="/inviteSupplier", method = RequestMethod.GET)
        public Message inviteSupplier(@RequestParam(value="id")Long id,Principal principa){
            Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
            try {
                Message message =businessReceivableService.inviteSupplier(id,getUserName(principa));
                msg.setCode(message.getCode());
                msg.setMessage(message.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                msg.setCode(MessageType.MSG_ERROR);
            }
            return msg;

    }
    

	@ApiOperation(value="浏览/编辑", notes="浏览/编辑")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectReceivableDetail", method = RequestMethod.GET)
    public Message selectReceivableDetail(@RequestParam(value="id")Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessReceivableService.selectReceivableDetail(id,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="保存编辑信息", notes="保存编辑信息")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitUpdateReceivable", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitUpdateReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
        	businessReceivableSubmit.setUserName(getUserName(principa));
            Message message =businessReceivableService.submitUpdateReceivable(businessReceivableSubmit);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/deleteReceivable", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message deleteReceivable(@ApiParam(required = true, name = "id", value = "id")@RequestParam("id") String id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =businessReceivableService.deleteReceivable(id,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   

	private String getUserName(Principal principa){
		return principa.getName();
	}
   

}
