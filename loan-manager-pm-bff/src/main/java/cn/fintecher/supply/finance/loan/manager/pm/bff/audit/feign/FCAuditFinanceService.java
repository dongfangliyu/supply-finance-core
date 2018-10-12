package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 2:32
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditFinanceService {

    @ResponseBody
    @RequestMapping(value = "/audit/financeService/searchFinanceList", method = RequestMethod.POST)
    Message searchFinanceList(@RequestBody AuditFinanceForm auditFinanceForm);

    @ResponseBody
    @RequestMapping(value = "/audit/financeService/searchFinanceDetail", method = RequestMethod.GET)
    Message searchFinanceDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/financeService/submitFinancePass", method = RequestMethod.GET)
    Message submitFinancePass(@RequestParam(value = "pid") String pid,@RequestParam(value = "userName") String userName);

    @ResponseBody
    @RequestMapping(value = "/audit/financeService/submitFinanceFail", method = RequestMethod.GET)
    Message submitFinanceFail(@RequestParam(value = "pid") String pid,@RequestParam(value = "userName") String userName);
}
