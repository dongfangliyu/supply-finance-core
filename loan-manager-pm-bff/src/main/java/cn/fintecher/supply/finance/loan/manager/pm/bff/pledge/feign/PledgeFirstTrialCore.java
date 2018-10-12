package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;

import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@FeignClient(name = "loan-manager-service")
public interface PledgeFirstTrialCore {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/firstTrial/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(@RequestParam("userName") String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/firstTrial/searchPledgeList", method = RequestMethod.POST)
    public Message searchPledgeList(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/firstTrial/searchDetail", method = RequestMethod.GET)
    public Message searchDetail(@RequestParam("id")Long id,@RequestParam("userName") String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/firstTrial/submitContnet", method = RequestMethod.POST)
    public Message submitContnet(@RequestBody PledgeSumbitTrialRequest pledgeApplyInfoResponse);
}
