package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@FeignClient(name = "loan-manager-core")
public interface PledgeStockInfoCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/insertStockInfo", method = RequestMethod.POST)
    public Integer insertStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/updateStockInfo", method = RequestMethod.POST)
    public Integer updateStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/selectByStockInfo", method = RequestMethod.POST)
    public List<PledgeStockInfoEntity> selectByStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/queryStockInfoByPid", method = RequestMethod.GET)
    public PledgeStockInfoEntity queryStockInfoByPid(@RequestParam(value = "pid")String pid);

}
