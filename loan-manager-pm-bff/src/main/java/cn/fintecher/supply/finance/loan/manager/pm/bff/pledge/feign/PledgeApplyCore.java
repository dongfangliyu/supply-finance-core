package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;
import feign.Headers;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
@FeignClient(name = "loan-manager-service")
public interface PledgeApplyCore {

	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/searchApplyList", method = RequestMethod.POST)
    public Message searchApplyList(@RequestBody PledgeApplyForm pledgeApplyForm);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/searchApplyDetail", method = RequestMethod.GET)
    public Message searchApplyDetail(@RequestParam("id") Long id,@RequestParam("userName") String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/apply/submitApply", method = RequestMethod.POST)
    public Message submitApply(@RequestBody PledgeApplySubmit pledgeApplySubmit);

}
