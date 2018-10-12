package cn.fintecher.supply.finance.loan.manager.pm.bff.business.controller;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.CalculateInterestForm;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessFinancingService;

import javax.print.DocFlavor;
import java.math.BigDecimal;
import java.security.Principal;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
@Api(value = "融资管理", tags = "融资管理")
@RestController
@RequestMapping("/business/financing")
public class BusinessFinancingController {

    @Autowired
    private BusinessFinancingService businessfinancingService;

    @ApiOperation(value="提交申请", notes="提交申请")
    @RequestMapping(value ="/submitApply" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitApply(@RequestBody BusinessFinancingForm businessFinancingForm){
        try {
            return businessfinancingService.submitApply(businessFinancingForm);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }
    }


    @ApiOperation(value="计算利息和服务费", notes="计算利息和服务费")
    @RequestMapping(value ="/calculateInterest", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message calculateInterest(@RequestBody CalculateInterestForm calculateInterestForm){
        try {
            Double  a = calculateInterestForm.getApplicationAmount();
            Integer b = calculateInterestForm.getApplicationTerm();
            Integer c = calculateInterestForm.getInterestRate();
            Integer d = calculateInterestForm.getServiceFee();
            if(a>0&&b>0&&c>0&&d>0){

                JSONObject jsonObject = new JSONObject();

                String interest = new BigDecimal(a)
                        .multiply(new BigDecimal(c))
                        .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(b))
                        .setScale(2,BigDecimal.ROUND_HALF_UP).toString();

                jsonObject.put("interest",interest);

                String serviceMoney = new BigDecimal(a)
                        .multiply(new BigDecimal(d))
                        .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(b))
                        .setScale(2,BigDecimal.ROUND_HALF_UP).toString();

                jsonObject.put("serviceMoney",serviceMoney);

                return new Message(MessageType.MSG_SUCCESS,"business_bff",jsonObject);
            }else{
                return new Message(MessageType.MSG_ERROR,"business_bff","参数未传或者格式不对！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }

    }

	@ApiOperation(value="融资申请列表", notes="融资申请列表")
	@ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchListOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListOrder(@RequestBody BusinessFinancingRequest businessFinancingRequest, Principal principal){
        try {
            businessFinancingRequest.setCurrentUserName(principal.getName());
            return businessfinancingService.searchListOrder(businessFinancingRequest);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }
    }
    

	@ApiOperation(value="核心企业账款信息查询", notes="核心企业账款信息查询")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectOrderEnterDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderEnterDetail(@RequestParam("id") Long id){
        try {
            return businessfinancingService.selectOrderEnterDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
           return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }

    }
    

	@ApiOperation(value="供应商账款信息保存", notes="供应商账款信息保存")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitOrder(@RequestBody BusinessVoucherForm businessVoucherForm){
        try {
            return businessfinancingService.submitOrder(businessVoucherForm);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }
    }
    

	@ApiOperation(value="供应商账款信息查询", notes="供应商账款信息查询")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectOrderSuppDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderSuppDetail(@RequestParam("id") Long id){
        try {
            return businessfinancingService.selectOrderSuppDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }

    }
    

	@ApiOperation(value="申请融资详情", notes="申请融资详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/selectOrderDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectOrderDetail(@RequestParam("id") Long id){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            return businessfinancingService.selectOrderDetail(id);
        } catch (Exception e) {
        	e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"business_bff",e.getMessage());
        }

    }
}
