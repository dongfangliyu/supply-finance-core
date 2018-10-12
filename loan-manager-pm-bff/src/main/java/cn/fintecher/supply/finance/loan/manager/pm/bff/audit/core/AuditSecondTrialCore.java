package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;

import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 10:01:21
 */
@FeignClient(name = "loan-manager-service")
public interface AuditSecondTrialCore {

	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/secondTrial/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(@RequestParam("userName") String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/secondTrial/searchOrderList", method = RequestMethod.POST)
    public Message searchOrderList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/secondTrial/searchDetail", method = RequestMethod.GET)
    public Message searchDetail(@RequestParam("pid")Long pid, @RequestParam("userName")String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/secondTrial/submitContnet", method = RequestMethod.POST)
    public Message submitContnet(@RequestBody AuditSumbitContentRequest auditSumbitContentRequest);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/secondTrial/searchDetailResult", method = RequestMethod.GET)
    public Message searchDetailResult(@RequestParam("pid")Long pid, @RequestParam("userName")String userName);

}
