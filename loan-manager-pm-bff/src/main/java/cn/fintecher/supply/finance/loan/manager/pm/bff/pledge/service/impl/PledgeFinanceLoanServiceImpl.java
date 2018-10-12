package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.FCPledgeFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:34
 */
@Service
public class PledgeFinanceLoanServiceImpl implements PledgeFinanceLoanService {

    @Autowired
    private FCPledgeFinanceLoanService fcPledgeFinanceLoanService;

    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return fcPledgeFinanceLoanService.searchFinanceLoanList(pledgeFinanceLoanForm);
    }

    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return fcPledgeFinanceLoanService.selectFinanceLoanList(pledgeFinanceLoanForm);
    }

    @Override
    public Message selectFinanceLoanDetail(Long pid) {
        return fcPledgeFinanceLoanService.selectFinanceLoanDetail(pid);
    }

    @Override
    public Message financeLoanPass(Long pid) {
        return fcPledgeFinanceLoanService.financeLoanPass(pid);
    }

    @Override
    public Message financeLoanDefault(Long pid) {
        return fcPledgeFinanceLoanService.financeLoanDefault(pid);
    }

    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return fcPledgeFinanceLoanService.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
    }

    @Override
    public Message financeReviewLoanPass(Long pid,String loanTime) {
        return fcPledgeFinanceLoanService.financeReviewLoanPass(pid,loanTime);
    }

    @Override
    public Message financeReviewLoanDefault(Long pid) {
        return fcPledgeFinanceLoanService.financeReviewLoanDefault(pid);
    }

    @Override
    public Message selectLoanByTermAndTime(Long contractApplyTerm, String loanTime) {
        return fcPledgeFinanceLoanService.selectLoanByTermAndTime(contractApplyTerm,loanTime);
    }

}
