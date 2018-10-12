package cn.fintecher.supply.finance.loan.manager.service.business.core;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@FeignClient(name = "loan-manager-core")
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
    public Message<List<BusinessOrderEntity>> selectByOrder(@RequestBody BusinessOrderEntity businessOrderEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryOrderByPid", method = RequestMethod.GET)
    public Message<BusinessOrderEntity> queryOrderByPid(@RequestParam(value = "pid")String pid);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryPageCount", method = RequestMethod.POST)
    public Message queryPageCount(@RequestBody BusinessOrderFrom businessOrderFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryPageList", method = RequestMethod.POST)
    public Message queryPageList(@RequestBody BusinessOrderFrom businessOrderFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryFinancingPageList", method = RequestMethod.POST)
    public Message queryFinancingPageList(@RequestBody BusinessFinancingRequest businessFinancingRequest);


    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/order/queryFinancingPageCount", method = RequestMethod.POST)
    public Message queryFinancingPageCount(@RequestBody BusinessFinancingRequest businessFinancingRequest);

}
