package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/24 0024下午 3:38
 */
@FeignClient(name = "loan-manager-core")
public interface FCFinanceStockInfoCore {

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/finance/stockInfo/insertStockInfo", method = RequestMethod.POST)
    Integer insertStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity);

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/finance/stockInfo/updateStockInfo", method = RequestMethod.POST)
    Integer updateStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity);


    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/finance/stockInfo/selectByStockInfo", method = RequestMethod.POST)
    List<FinanceStockInfoEntity> selectByStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity);

    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/finance/stockInfo/queryStockInfoByPid", method = RequestMethod.GET)
    FinanceStockInfoEntity queryStockInfoByPid(@RequestParam("pid") String pid);

}
