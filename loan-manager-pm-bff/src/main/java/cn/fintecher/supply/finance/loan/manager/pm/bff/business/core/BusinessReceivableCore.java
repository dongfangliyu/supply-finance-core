package cn.fintecher.supply.finance.loan.manager.pm.bff.business.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@FeignClient(name = "loan-manager-service")
public interface BusinessReceivableCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/insertReceivable", method = RequestMethod.POST)
    public Message insertReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/updateReceivable", method = RequestMethod.POST)
    public Message updateReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/selectByReceivable", method = RequestMethod.POST)
    public Message selectByReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/queryReceivableByPid", method = RequestMethod.GET)
    public Message queryReceivableByPid(@RequestParam(value = "pid")String pid);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/searchListReceivable", method = RequestMethod.POST)
    public Message searchListReceivable(@RequestBody BusinessReceivableFrom businessReceivableFrom);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/submitReceivable", method = RequestMethod.POST)
    public Message submitReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/inviteSupplier", method = RequestMethod.GET)
    public Message inviteSupplier(@RequestParam("receivableId")Long receivableId,@RequestParam("userName")String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/selectReceivableDetail", method = RequestMethod.GET)
    public Message selectReceivableDetail(@RequestParam("receivableId")Long receivableId,@RequestParam("userName")String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/submitUpdateReceivable", method = RequestMethod.POST)
    public Message submitUpdateReceivable(@RequestBody BusinessReceivableSubmit businessReceivableSubmit);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/Receivable/deleteReceivable", method = RequestMethod.POST)
	public Message deleteReceivable(@RequestParam("id")String id, @RequestParam("userName")String userName);

}
