package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:36
 */
@FeignClient(name = "loan-manager-core")
public interface FCPledgeFinanceLoanCore {

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/searchFinanceLoanListCount", method = RequestMethod.POST)
    int searchFinanceLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/searchFinanceLoanList", method = RequestMethod.POST)
    List<PledgeFinanceLoanResponse> searchFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/selectFinanceLoanListCount", method = RequestMethod.POST)
    int selectFinanceLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/selectFinanceLoanList", method = RequestMethod.POST)
    List<PledgeFinanceLoanResponse> selectFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/selectFinanceReviewLoanListCount", method = RequestMethod.POST)
    int selectFinanceReviewLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);

    @ResponseBody
    @RequestMapping(value = "/finance/loanCore/selectFinanceReviewLoanList", method = RequestMethod.POST)
    List<PledgeFinanceLoanResponse> selectFinanceReviewLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm);
}
