package cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@FeignClient(name = "loan-manager-service")
public interface OverdueOrderCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/insertOrder", method = RequestMethod.POST)
    public Message insertOrder(@RequestBody OverdueOrderEntity overdueOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/updateOrder", method = RequestMethod.POST)
    public Message updateOrder(@RequestBody OverdueOrderEntity overdueOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/selectByOrder", method = RequestMethod.POST)
    public Message selectByOrder(@RequestBody OverdueOrderEntity overdueOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/queryOrderByPid", method = RequestMethod.GET)
    public Message queryOrderByPid(@RequestParam(value = "pid")String pid);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/searchOrderList", method = RequestMethod.POST)
    public Message searchOrderList(@RequestBody OverdueOrderForm overdueOrderForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/searchOrderDetail", method = RequestMethod.GET)
    public Message searchOrderDetail(@RequestParam("pid")Long pid);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/submitRepaymentRecord", method = RequestMethod.POST)
    public Message submitRepaymentRecord(@RequestBody OverdueOrderRepaymentForm overdueOrderRepaymentForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/searchRepaymenList", method = RequestMethod.POST)
    public Message searchRepaymenList(@RequestBody OverdueOrderRecordForm overdueOrderRecordForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/downLoadOrder", method = RequestMethod.POST)
    public Message downLoadOrder(@RequestBody OverdueOrderForm overdueOrderForm);

}
