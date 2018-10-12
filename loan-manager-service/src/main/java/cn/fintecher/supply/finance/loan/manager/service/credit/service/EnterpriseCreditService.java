package cn.fintecher.supply.finance.loan.manager.service.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


/**
 * @Author WuTianJuan
 * @Date Created in 13:38 2018/6/15
 */
public interface EnterpriseCreditService {
    /**
     * 查询企业授信状态
     */
    Message searchCompanyCreditStatus(@RequestBody SearchCompanyCreditForm searchCompanyCreditForm);

    /**
     * 开始授信
     */
    Message startCompanyCredit(@RequestParam(value="pid") Long pid,@RequestParam(value = "code") String code);

    /**
     * 查询财务报表期间
     */
    Message searchAccountingStatementTime();

    /**
     * 查询授信结果
     */
    Message searchCompanyCreditResult(@RequestParam(value = "pid") Long pid);

    Message searchSupplierList(@RequestParam(value = "pid") String pid);

}

