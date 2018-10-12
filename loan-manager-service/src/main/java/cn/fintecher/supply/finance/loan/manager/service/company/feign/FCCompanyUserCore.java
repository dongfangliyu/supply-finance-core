package cn.fintecher.supply.finance.loan.manager.service.company.feign;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/3 0003下午 2:02
 */
@FeignClient(name = "loan-manager-core")
public interface FCCompanyUserCore {
    @ResponseBody
    @RequestMapping(value ="/company/userCore/findCompanyUserByName", method = RequestMethod.GET)
    CompanyUserEntity findCompanyUserByName(@RequestParam(value = "userName") String userName);


    @ResponseBody
    @RequestMapping(value ="/company/userCore/findCompanyUserByNameAndEnterpriseType", method = RequestMethod.GET)
    List<CompanyUserEntity> findCompanyUserByNameAndEnterpriseType(@RequestParam(value = "userName") String userName, @RequestParam(value = "enterpriseType") String enterpriseType);

}
