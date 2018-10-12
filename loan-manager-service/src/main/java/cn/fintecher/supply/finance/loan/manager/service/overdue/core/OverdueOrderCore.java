package cn.fintecher.supply.finance.loan.manager.service.overdue.core;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@FeignClient(name = "loan-manager-core")
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
    public Message<List<OverdueOrderEntity>> selectByOrder(@RequestBody OverdueOrderEntity overdueOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/queryOrderByPid", method = RequestMethod.GET)
    public Message<OverdueOrderEntity> queryOrderByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/queryListByOrderForm", method = RequestMethod.POST)
	public Message<List<OverdueOrderResponse>> queryListByOrderForm(@RequestBody OverdueOrderForm overdueOrderForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/queryCountByOrderForm", method = RequestMethod.POST)
	public Message<Integer> queryCountByOrderForm(@RequestBody OverdueOrderForm overdueOrderForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/order/queryDownListByOrderForm", method = RequestMethod.POST)
	public Message queryDownListByOrderForm(OverdueOrderForm overdueOrderForm);

}
