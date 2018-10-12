package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialListResponse;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@FeignClient(name = "loan-manager-core")
public interface PledgeApplyCore {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/queryPageListByPledgeApply", method = RequestMethod.POST)
	public List<PledgeApplyListResponse> queryPageListByPledgeApply(@RequestBody PledgeApplyForm pledgeApplyForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/queryPageCountByPledgeApply", method = RequestMethod.POST)
	public Integer queryPageCountByPledgeApply(@RequestBody PledgeApplyForm pledgeApplyForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/sureReceiveTask", method = RequestMethod.GET)
	public List<PledgeApplyStockInfoEntity> sureReceiveTask(@RequestParam(value = "task")String task);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/selectTrialCount", method = RequestMethod.POST)
	public Integer selectTrialCount(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/selectTrialList", method = RequestMethod.POST)
	public List<PledgeTrialListResponse> selectTrialList(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom);
}
