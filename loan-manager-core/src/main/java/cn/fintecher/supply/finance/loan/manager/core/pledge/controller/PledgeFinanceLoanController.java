package cn.fintecher.supply.finance.loan.manager.core.pledge.controller;

import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:38
 */
@RestController
@RequestMapping("/finance/loanCore")
public class PledgeFinanceLoanController {

    @Autowired
    private PledgeFinanceLoanService pledgeFinanceLoanService;

    @ResponseBody
    @RequestMapping(value = "/searchFinanceLoanListCount", method = RequestMethod.POST)
    public int searchFinanceLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.searchFinanceLoanListCount(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchFinanceLoanList", method = RequestMethod.POST)
    public List<PledgeFinanceLoanResponse> searchFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.searchFinanceLoanList(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceLoanListCount", method = RequestMethod.POST)
    public int selectFinanceLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceLoanListCount(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceLoanList", method = RequestMethod.POST)
    public List<PledgeFinanceLoanResponse> selectFinanceLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceLoanList(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceReviewLoanListCount", method = RequestMethod.POST)
    public int selectFinanceReviewLoanListCount(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceReviewLoanListCount(pledgeFinanceLoanForm);
    }

    @ResponseBody
    @RequestMapping(value = "/selectFinanceReviewLoanList", method = RequestMethod.POST)
    public List<PledgeFinanceLoanResponse> selectFinanceReviewLoanList(@RequestBody PledgeFinanceLoanForm pledgeFinanceLoanForm){
        return pledgeFinanceLoanService.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
    }

}
