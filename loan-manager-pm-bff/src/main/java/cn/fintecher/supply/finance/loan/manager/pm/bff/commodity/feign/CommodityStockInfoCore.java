package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@FeignClient(name = "loan-manager-service")
public interface CommodityStockInfoCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/insertStockInfo", method = RequestMethod.POST)
    public Message insertStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/updateStockInfo", method = RequestMethod.POST)
    public Message updateStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectByStockInfo", method = RequestMethod.POST)
    public Message selectByStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/queryStockInfoByPid", method = RequestMethod.GET)
    public Message queryStockInfoByPid(@RequestParam(value = "pid")String pid);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/searchStockList", method = RequestMethod.POST)
    public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(@RequestBody CommodityStockForm commdityStockForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/submitStock", method = RequestMethod.POST)
    public Message<Object> submitStock(@RequestBody CommodityStockInfoSubmit commodityStockInfoSubmit);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/searchStockDetail", method = RequestMethod.GET)
    public Message<CommodityStockInfoResponse> searchStockDetail(@RequestParam("pid") Long pid,@RequestParam("userName")  String userName);

    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectStockList", method = RequestMethod.POST)
    public Message selectStockList(@RequestBody CommodityStockForm commodityStockForm);

    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/submitState", method = RequestMethod.GET)
    public Message submitState(@RequestParam("pid") Long pid, @RequestParam("time")String time);

    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectCommodityStockInfo", method = RequestMethod.GET)
    public Message selectCommodityStockInfo(@RequestParam("pid") Long pid);
}
