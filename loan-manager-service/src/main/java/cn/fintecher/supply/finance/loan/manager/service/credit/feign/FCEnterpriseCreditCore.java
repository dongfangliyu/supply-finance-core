package cn.fintecher.supply.finance.loan.manager.service.credit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 13:38 2018/6/15
 */
@FeignClient(name = "loan-manager-core")
public interface FCEnterpriseCreditCore {
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCompanyCreditStatus", method = RequestMethod.POST)
    CompanyEnterpriseEntity searchCompanyCreditStatus(@RequestBody SearchCompanyCreditForm searchCompanyCreditForm);

    /**
     * 更改授信步骤
     */
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/updateCompanyCreditStep", method = RequestMethod.POST)
    Message startCompanyCredit(@RequestBody CompanyEnterpriseEntity companyEnterpriseEntity);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCompanyCreditResult", method = RequestMethod.GET)
    Message searchCompanyCreditResult(@RequestParam(value = "pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/dictionary/searchAccountingStatementTime", method = RequestMethod.GET)
    Message searchAccountingStatementTime(@RequestParam(value = "year") String year);


    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCompanyInfo", method = RequestMethod.GET)
    CompanyEnterpriseEntity searchCompanyInfo(@RequestParam(value ="pid")Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/applyCompanyCredit", method = RequestMethod.POST)
    Boolean applyCompanyCredit(@RequestBody AuditCompanyEntity entity);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/searchCountOfCredit", method = RequestMethod.GET)
    Long searchCountOfCredit();

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/credit/enterprise/updateCompanyCreditStep", method = RequestMethod.GET)
    Message updateCompanyCreditStep(@RequestBody CompanyEnterpriseEntity companyEnterpriseEntity);
}
