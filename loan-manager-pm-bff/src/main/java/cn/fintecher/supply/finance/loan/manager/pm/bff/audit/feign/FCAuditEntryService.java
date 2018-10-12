package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:44
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditEntryService {

    @ResponseBody
    @RequestMapping(value = "/audit/entryService/searchEntryList", method = RequestMethod.POST)
    Message searchEntryList(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/entryService/searchEntryDetail", method = RequestMethod.GET)
    Message searchEntryDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/entryService/searchEntry", method = RequestMethod.POST)
    Message searchEntry(@RequestBody AuditEntryForm auditEntryForm);

    @ResponseBody
    @RequestMapping(value = "/audit/entryService/submitEntry", method = RequestMethod.POST)
    Message submitEntry(@RequestBody AuditEntryForm auditEntryForm);

}
