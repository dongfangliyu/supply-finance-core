package cn.fintecher.supply.finance.loan.manager.service.overdue.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:21
 */
@FeignClient(name = "loan-manager-core")
public interface OverdueAuditTaskEntryCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/queryPaymentCountByOrderInfoId", method = RequestMethod.GET)
    public Message<Integer> queryPaymentCountByOrderInfoId(@RequestParam("orderInfoId") String orderInfoId);
    
   

}
