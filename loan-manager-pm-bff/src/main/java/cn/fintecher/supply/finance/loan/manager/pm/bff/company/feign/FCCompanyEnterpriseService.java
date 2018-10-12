package cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:21
 */
@FeignClient(name = "loan-manager-service")
public interface FCCompanyEnterpriseService {

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCompanyEnterprise", method = RequestMethod.GET)
    Message selectCompanyEnterprise(@RequestParam(value = "name") String name);


    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCustomerManagementList", method = RequestMethod.POST)
    Message selectCustomerManagementList(@RequestBody CustomerFrom customerFrom);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCustomerManagementDetail", method = RequestMethod.GET)
    Message selectCustomerManagementDetail(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectCustomerBlackList", method = RequestMethod.POST)
    Message selectCustomerBlackList(@RequestBody BlackListFrom blackListFrom);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/deleteBlackList", method = RequestMethod.GET)
    Message deleteBlackList(@RequestParam(value = "id") Long id,@RequestParam(value = "causationInfo")String causationInfo);


    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/submitBlackList", method = RequestMethod.GET)
    Message submitBlackList(@RequestParam(value = "id") Long id,@RequestParam(value = "causationInfo")String causationInfo);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/selectByName", method = RequestMethod.GET)
    CompanyEnterpriseEntity selectByName(@RequestParam(value = "name") String name);

    @ResponseBody
    @RequestMapping(value = "/company/enterpriseService/searchListConfirmationCompany", method = RequestMethod.GET)
    Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany();
}
