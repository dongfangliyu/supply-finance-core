package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 3:32
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditFinanceCore {

    @ResponseBody
    @RequestMapping(value ="/audit/financeCore/searchFinanceListCount", method = RequestMethod.POST)
    int searchFinanceListCount(@RequestBody AuditFinanceForm auditFinanceForm);

    @ResponseBody
    @RequestMapping(value ="/audit/financeCore/searchFinanceList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchFinanceList(@RequestBody AuditFinanceForm auditFinanceForm);

    @ResponseBody
    @RequestMapping(value ="/audit/financeCore/searchLoanListCount", method = RequestMethod.POST)
    int searchLoanListCount(@RequestBody AuditFinanceForm auditFinanceForm);

    @ResponseBody
    @RequestMapping(value ="/audit/financeCore/searchLoanList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchLoanList(@RequestBody AuditFinanceForm auditFinanceForm);
}
