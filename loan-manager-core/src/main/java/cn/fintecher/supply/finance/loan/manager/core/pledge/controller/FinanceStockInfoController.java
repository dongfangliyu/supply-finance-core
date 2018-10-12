package cn.fintecher.supply.finance.loan.manager.core.pledge.controller;


import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.FinanceStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 17:33:07
 */
@RestController
@RequestMapping("/finance/stockInfo")
public class FinanceStockInfoController {
    @Autowired
    private FinanceStockInfoService financeStockInfoService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertStockInfo", method = RequestMethod.POST)
    public Integer insertStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity){
    	
    	return financeStockInfoService.insertStockInfo(financeStockInfoEntity);
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateStockInfo", method = RequestMethod.POST)
    public Integer updateStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity){
    		
    	return financeStockInfoService.updateStockInfo(financeStockInfoEntity);
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByStockInfo", method = RequestMethod.POST)
    public List<FinanceStockInfoEntity> selectByStockInfo(@RequestBody FinanceStockInfoEntity financeStockInfoEntity){

    	return financeStockInfoService.selectByStockInfo(financeStockInfoEntity);
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStockInfoByPid", method = RequestMethod.GET)
    public FinanceStockInfoEntity queryStockInfoByPid(@RequestParam("pid") String pid){
    	
    	return financeStockInfoService.queryStockInfoByPid(pid);
		
    }
    
    

}