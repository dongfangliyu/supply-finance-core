package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:21
 */
@FeignClient(name = "loan-manager-service")
public interface FCCompanyEnterpriseService {

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCompanyEnterpriseByPid", method = RequestMethod.GET)
    public CompanyEnterpriseEntity selectCompanyEnterpriseByPid(@RequestParam(value = "pid") Long pid);


    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/searchCompanyLegal", method = RequestMethod.GET)
    public CompanyOperationEntity searchCompanyLegal(@RequestParam(value = "enterpriseId") Long enterpriseId);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCompanyEnterpriseInfoByEnterpriseId", method = RequestMethod.GET)
    public CompanyEnterpriseInfoEntity selectCompanyEnterpriseInfoByEnterpriseId(@RequestParam(value = "pid") Long enterpriseId);
}
