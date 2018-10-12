package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:34
 */
@FeignClient(name = "loan-manager-service")
public interface FCPledgeFinanceLoanService {

    @ResponseBody
    @RequestMapping(value = "/finance/loan/searchFinanceLoanList", method = RequestMethod.POST)
    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/selectFinanceLoanList", method = RequestMethod.POST)
    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/selectFinanceLoanDetail", method = RequestMethod.GET)
    Message selectFinanceLoanDetail(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/financeLoanPass", method = RequestMethod.GET)
    Message financeLoanPass(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/financeLoanDefault", method = RequestMethod.GET)
    Message financeLoanDefault(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/selectFinanceReviewLoanList", method = RequestMethod.POST)
    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/financeReviewLoanPass", method = RequestMethod.GET)
    Message financeReviewLoanPass(@RequestParam(value = "pid") Long pid,@RequestParam(value = "loanTime") String loanTime);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/financeReviewLoanDefault", method = RequestMethod.GET)
    Message financeReviewLoanDefault(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/finance/loan/selectLoanByTermAndTime", method = RequestMethod.GET)
    Message selectLoanByTermAndTime(@RequestParam(value = "contractApplyTerm") Long contractApplyTerm,@RequestParam(value = "LoanTime") String loanTime);
}
