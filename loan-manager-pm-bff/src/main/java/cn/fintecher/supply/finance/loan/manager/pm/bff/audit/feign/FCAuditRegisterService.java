package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:59
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditRegisterService {

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/searchListAuditRegister", method = RequestMethod.POST)
    Message searchListAuditRegister(@RequestBody AuditRegisterForm auditRegisterForm);

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/searchAuditRegisterCompanyInfo", method = RequestMethod.GET)
    Message searchAuditRegisterCompanyInfo(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/submitAuditRegisterResult", method = RequestMethod.POST)
    Message submitAuditRegisterResult(@RequestBody AuditRegisterResultForm auditRegisterResultForm);

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/searchCompanyInfo", method = RequestMethod.GET)
    Message searchCompanyInfo(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/submitSendLink", method = RequestMethod.GET)
    Message submitSendLink(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/registerService/searchAuditCreditStatus", method = RequestMethod.GET)
    Message searchAuditCreditStatus();
}
