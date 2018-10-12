package cn.fintecher.supply.finance.loan.manager.core.pledge.controller;


import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeStockInfoService;



/**
 * 仓单质押申请信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@RestController
@RequestMapping("pledge/stockInfo")
public class PledgeStockInfoController {
    @Autowired
    private PledgeStockInfoService pledgeStockInfoService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertStockInfo", method = RequestMethod.POST)
    public Integer insertStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity){
    	
    	return pledgeStockInfoService.insertStockInfo(pledgeStockInfoEntity);
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateStockInfo", method = RequestMethod.POST)
    public Integer updateStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity){
    		
    	return pledgeStockInfoService.updateStockInfo(pledgeStockInfoEntity);
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByStockInfo", method = RequestMethod.POST)
    public List<PledgeStockInfoEntity> selectByStockInfo(@RequestBody PledgeStockInfoEntity pledgeStockInfoEntity){

    	return pledgeStockInfoService.selectByStockInfo(pledgeStockInfoEntity);
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStockInfoByPid", method = RequestMethod.GET)
    public PledgeStockInfoEntity queryStockInfoByPid(@RequestParam("pid") String pid){
    	
    	return pledgeStockInfoService.queryStockInfoByPid(pid);
		
    }

    /**
     * web端查询签约列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectWebSigningList", method = RequestMethod.POST)
    public List<PledgeSigningResponse> selectWebSigningList(@RequestBody PledgeStockFrom pledgeStockFrom){
        return pledgeStockInfoService.selectWebSigningList(pledgeStockFrom);
    }

    /**
     * web端查询签约列表Count
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectWebSigningListCount", method = RequestMethod.POST)
    public Integer selectWebSigningListCount(@RequestBody PledgeStockFrom pledgeStockFrom){
        return pledgeStockInfoService.selectWebSigningListCount(pledgeStockFrom);
    }


    /**
     * admin端查询签约列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAdminSigningList", method = RequestMethod.POST)
    public List<PledgeSigningResponse> selectAdminSigningList(@RequestBody PledgeStockFrom pledgeStockFrom){
        return pledgeStockInfoService.selectAdminSigningList(pledgeStockFrom);
    }

    /**
     * admin端查询签约列表Count
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAdminSigningListCount", method = RequestMethod.POST)
    public Integer selectAdminSigningListCount(@RequestBody PledgeStockFrom pledgeStockFrom){
        return pledgeStockInfoService.selectAdminSigningListCount(pledgeStockFrom);
    }

}