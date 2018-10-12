package cn.fintecher.supply.finance.loan.manager.service.commodity.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@FeignClient(name = "loan-manager-core", url = "${loan.manager.core.url}")
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
    public Message<List<CommodityStockInfoEntity>> selectByStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/queryStockInfoByPid", method = RequestMethod.GET)
    public Message<CommodityStockInfoEntity> queryStockInfoByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/queryPageList", method = RequestMethod.POST)
	public Message<List<CommodityStockInfoEntity>> queryPageList(@RequestBody CommodityStockForm commdityStockForm);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/queryPageCount", method = RequestMethod.POST)
    public Message<Integer> queryPageCount(@RequestBody CommodityStockForm commdityStockForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/queryNewWarehousingNo", method = RequestMethod.GET)
	public String queryNewWarehousingNo();

    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectStockList", method = RequestMethod.POST)
    public List<CommodityStockInfoEntity> selectStockList(@RequestBody CommodityStockForm commodityStockForm);

    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectStockListCount", method = RequestMethod.POST)
    public Integer selectStockListCount(@RequestBody CommodityStockForm commodityStockForm);


    @ResponseBody
    @RequestMapping(value = "/commodity/stockInfo/selectCommodityStockInfo", method = RequestMethod.GET)
    public Message<CommodityStockInfoEntity> selectCommodityStockInfo(@RequestParam(value = "pid")Long pid);


}
