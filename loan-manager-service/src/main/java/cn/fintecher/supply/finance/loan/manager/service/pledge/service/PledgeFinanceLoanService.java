package cn.fintecher.supply.finance.loan.manager.service.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:36
 */
public interface PledgeFinanceLoanService {

    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm, CompanyUserEntity user);

    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    Message selectFinanceLoanDetail(Long pid);

    Message financeLoanPass(Long pid);

    Message financeLoanDefault(Long pid);

    Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    Message financeReviewLoanPass(Long pid,String loanTime);

    Message financeReviewLoanDefault(Long pid);

    Message selectLoanByTermAndTime(Long contractApplyTerm, String loanTime);
}
