package cn.fintecher.supply.finance.loan.manager.pm.bff.business.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@FeignClient(name = "loan-manager-service")
public interface BusinessOrderCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/insertOrder", method = RequestMethod.POST)
    public Message insertOrder(@RequestBody BusinessOrderEntity businessOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/updateOrder", method = RequestMethod.POST)
    public Message updateOrder(@RequestBody BusinessOrderEntity businessOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/selectByOrder", method = RequestMethod.POST)
    public Message selectByOrder(@RequestBody BusinessOrderEntity businessOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryOrderByPid", method = RequestMethod.GET)
    public Message queryOrderByPid(@RequestParam(value = "pid")String pid);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/searchListOrder", method = RequestMethod.POST)
    public Message searchListOrder(@RequestBody BusinessOrderFrom businessOrderFrom);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/selectOrderDetail", method = RequestMethod.POST)
    public Message selectOrderDetail(@RequestParam("orderId")Long orderId,@RequestParam("userName")String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/submitConfirm", method = RequestMethod.POST)
    public Message submitConfirm(@RequestParam("orderId")Long orderId,@RequestParam("userName")String userName);

}
