package cn.fintecher.supply.finance.loan.manager.core.credit.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyCreditResultResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author WuTianJuan
 * @Date Created in 13:53 2018/6/15
 */

@Mapper
public interface EnterpriseCreditDao {
    /**
     * 查询企业授信状态
     */
    CompanyEnterpriseEntity searchCompanyCreditStatus(SearchCompanyCreditForm form);

    /**
     * 更新企業授信
     */
    Boolean updateCompanyCreditStep(CompanyEnterpriseEntity companyEnterpriseEntity);

    /**
     * 查询申请授信结果
     */
    CompanyCreditResultResponse searchCompanyCreditResult(Long pid);


    CompanyEnterpriseEntity searchCompanyInfo(Long pid);

    Long searchCountOfCredit();
}
