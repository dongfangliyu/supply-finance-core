package cn.fintecher.supply.finance.loan.manager.service.business.controller;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFinancingService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
@RestController
@RequestMapping("/business/financing")
public class BusinessFinancingController extends BaseCompanyUserController {

    @Autowired
    private BusinessFinancingService businessFinancingService;
    

	@ResponseBody
    @RequestMapping(value ="/searchListOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListOrder(@RequestBody BusinessFinancingRequest businessFinancingRequest){
        try {
            businessFinancingRequest.setCurrentCompanyId(getCompanyUser(businessFinancingRequest.getCurrentUserName()).getEnterpriseId());
            return businessFinancingService.searchListOrder(businessFinancingRequest);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value ="/selectOrderEnterDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderEnterDetail(@RequestParam("id") Long id){
        try {
            return businessFinancingService.selectOrderEnterDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
           return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }
    }
    
    @ResponseBody
    @RequestMapping(value ="/submitOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitOrder(@RequestBody BusinessVoucherForm businessVoucherForm){
        try {
            return businessFinancingService.submitOrder(businessVoucherForm);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }
    }
    
    @ResponseBody
    @RequestMapping(value ="/selectOrderSuppDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderSuppDetail(@RequestParam("id") Long id){
        try {
            return businessFinancingService.selectOrderSuppDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value ="/selectOrderDetail", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderDetail(@RequestParam("id") Long id){
        try {
            return businessFinancingService.selectOrderDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value ="/submitApply", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    Message submitApply(@RequestBody BusinessFinancingForm businessFinancingForm){
        try {
            return businessFinancingService.submitApply(businessFinancingForm);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_service",e.getMessage());
        }
    }

}
