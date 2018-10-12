package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 10:38
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditEnterSigningService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditEnterSigning/searchSigningList", method = RequestMethod.POST)
    Message searchEnterSigningList(AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditEnterSigning/submitSigning", method = RequestMethod.GET)
    Message submitSigning(@RequestParam("pid")Long pid, @RequestParam("name") String name);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditEnterSigning/isUpLoadContract", method = RequestMethod.GET)
    Message isUpLoadContract(@RequestParam("pid")Long pid);
}
