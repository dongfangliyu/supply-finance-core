package cn.fintecher.supply.finance.loan.manager.core.pledge.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialListResponse;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeApplyService;



/**
 * 仓单质押申请信息表
 * 申请及审核使用
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@RestController
@RequestMapping("pledge/apply")
public class PledgeApplyController {
    @Autowired
    private PledgeApplyService pledgeApplyService;
    
    
    @ResponseBody
    @RequestMapping(value = "/queryPageListByPledgeApply", method = RequestMethod.POST)
	public List<PledgeApplyListResponse> queryPageListByPledgeApply(@RequestBody PledgeApplyForm pledgeApplyForm){
    	return pledgeApplyService.queryPageListByPledgeApply(pledgeApplyForm);
    };

    @ResponseBody
    @RequestMapping(value = "/queryPageCountByPledgeApply", method = RequestMethod.POST)
	public Integer queryPageCountByPledgeApply(@RequestBody PledgeApplyForm pledgeApplyForm){
    	return pledgeApplyService.queryPageCountByPledgeApply(pledgeApplyForm);
    };

    @ResponseBody
    @RequestMapping(value = "/sureReceiveTask", method = RequestMethod.GET)
    public List<PledgeApplyStockInfoEntity> sureReceiveTask(@RequestParam(value = "task")String task){
    	return pledgeApplyService.sureReceiveTask(task);
    };
    
    @ResponseBody
    @RequestMapping(value = "/selectTrialCount", method = RequestMethod.POST)
    public Integer selectTrialCount(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom){
    	return pledgeApplyService.selectTrialCount(pledgeStockTrialFrom);
    };
    
    @ResponseBody
    @RequestMapping(value = "/selectTrialList", method = RequestMethod.POST)
    public List<PledgeTrialListResponse> selectTrialList(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom){
    	return pledgeApplyService.selectTrialList(pledgeStockTrialFrom);
    };
    

}