package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 14:12
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditPlatformSigningService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditPlatformSigning/searchSigningList", method = RequestMethod.POST)
    Message searchPlatformSigningList(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditPlatformSigning/submitSigning", method = RequestMethod.GET)
    Message submitSigning(@RequestParam("pid")Long pid, @RequestParam("name") String name);
}
