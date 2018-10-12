package cn.fintecher.supply.finance.loan.manager.core.pledge.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeApplyStockInfoService;



/**
 * 仓单质押申请信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@RestController
@RequestMapping("pledge/applyStockInfo")
public class PledgeApplyStockInfoController {
    @Autowired
    private PledgeApplyStockInfoService pledgeApplyStockInfoService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertApplyStockInfo", method = RequestMethod.POST)
    public Integer insertApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity){
    	
    	return pledgeApplyStockInfoService.insertApplyStockInfo(pledgeApplyStockInfoEntity);
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateApplyStockInfo", method = RequestMethod.POST)
    public Integer updateApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity){
    		
    	return pledgeApplyStockInfoService.updateApplyStockInfo(pledgeApplyStockInfoEntity);
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByApplyStockInfo", method = RequestMethod.POST)
    public List<PledgeApplyStockInfoEntity> selectByApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity){

    	return pledgeApplyStockInfoService.selectByApplyStockInfo(pledgeApplyStockInfoEntity);
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryApplyStockInfoByPid", method = RequestMethod.GET)
    public PledgeApplyStockInfoEntity queryApplyStockInfoByPid(@RequestParam("pid") String pid){
    	return pledgeApplyStockInfoService.queryApplyStockInfoByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/countConfirmingTaskCanReceiveNum", method = RequestMethod.GET)
    public Message<Integer> countConfirmingTaskCanReceiveNum(@RequestParam(value = "node")Integer node){
        try {
            return pledgeApplyStockInfoService.countConfirmingTaskCanReceiveNum(node);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"applyStockInfo_core",e.getMessage());
        }
    }
}