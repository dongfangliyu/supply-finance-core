package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:32
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditRemindService {

    @ResponseBody
    @RequestMapping(value = "/audit/remindService/searchRemindList", method = RequestMethod.POST)
    Message searchRemindList(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/remindService/searchRemindDetail", method = RequestMethod.GET)
    Message searchRemindDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/remindService/searchRemind", method = RequestMethod.POST)
    Message searchRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/remindService/submitRemind", method = RequestMethod.POST)
    Message submitRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/remindService/deleteRemind", method = RequestMethod.GET)
    Message deleteRemind(@RequestParam(value = "pid") String pid);
}
