package cn.fintecher.supply.finance.loan.manager.core.pledge.service;

import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:38
 */
public interface PledgeFinanceLoanService {
    int searchFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    List<PledgeFinanceLoanResponse> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    int selectFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    List<PledgeFinanceLoanResponse> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    int selectFinanceReviewLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

    List<PledgeFinanceLoanResponse> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);
}
