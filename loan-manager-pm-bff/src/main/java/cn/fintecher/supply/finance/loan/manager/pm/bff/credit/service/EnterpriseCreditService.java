package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;


/**
 * @Author WuTianJuan
 * @Date Created in 13:38 2018/6/15
 */
    public interface EnterpriseCreditService {
    /**
     * 查询企业授信状态2
     */
    Message searchCompanyCreditStatus(SearchCompanyCreditForm searchCompanyCreditForm);

    /**
     * 开始授信
     */
    Message startCompanyCredit(Long pid);

    /**
     * 查询财务报表期间
     */
    Message searchAccountingStatementTime();

    /**
     * 申请授信
     */
     Message applyCompanyCredit(Long pid);

    /**
     * 查询授信结果
     */
    Message searchCompanyCreditResult(Long pid);

    
    Message searchSupplierList(String pid);
}

