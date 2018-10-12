package cn.fintecher.supply.finance.loan.manager.pm.bff.business.core;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
@FeignClient(name = "loan-manager-service")
public interface BusinessFinancingCore {

	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/searchListOrder", method = RequestMethod.POST)
    public Message searchListOrder(@RequestBody BusinessFinancingRequest businessFinancingRequest);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/selectOrderEnterDetail", method = RequestMethod.GET)
    public Message selectOrderEnterDetail(@RequestParam("id") Long id);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/submitOrder", method = RequestMethod.POST)
    public Message submitOrder(@RequestBody BusinessVoucherForm businessVoucherForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/selectOrderSuppDetail", method = RequestMethod.GET)
    public Message selectOrderSuppDetail(@RequestParam("id") Long id);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/selectOrderDetail", method = RequestMethod.POST)
    public Message selectOrderDetail(@RequestParam("id")Long id);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/financing/submitApply", method = RequestMethod.POST)
    Message submitApply(@RequestBody BusinessFinancingForm businessFinancingForm);
}
