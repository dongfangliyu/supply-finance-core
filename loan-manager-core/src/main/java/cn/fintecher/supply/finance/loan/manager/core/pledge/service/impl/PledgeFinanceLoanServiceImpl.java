package cn.fintecher.supply.finance.loan.manager.core.pledge.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.PledgeStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeFinanceLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:39
 */
@Service
public class PledgeFinanceLoanServiceImpl implements PledgeFinanceLoanService {

    @Autowired
    private PledgeStockInfoDao pledgeStockInfoDao;

    @Override
    public int searchFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.searchFinanceLoanListCount(pledgeFinanceLoanForm);
    }

    @Override
    public List<PledgeFinanceLoanResponse> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.searchFinanceLoanList(pledgeFinanceLoanForm);
    }

    @Override
    public int selectFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.selectFinanceLoanListCount(pledgeFinanceLoanForm);
    }

    @Override
    public List<PledgeFinanceLoanResponse> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.selectFinanceLoanList(pledgeFinanceLoanForm);
    }

    @Override
    public int selectFinanceReviewLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.selectFinanceReviewLoanListCount(pledgeFinanceLoanForm);
    }

    @Override
    public List<PledgeFinanceLoanResponse> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        return pledgeStockInfoDao.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
    }
}
