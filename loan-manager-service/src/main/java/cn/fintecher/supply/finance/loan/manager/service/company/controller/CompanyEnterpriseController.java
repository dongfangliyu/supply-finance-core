package cn.fintecher.supply.finance.loan.manager.service.company.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:24
 */
@RestController
@RequestMapping("/company/enterpriseService")
public class CompanyEnterpriseController {

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    @ResponseBody
    @RequestMapping(value = "/selectCompanyEnterprise", method = RequestMethod.GET)
    public Message selectCompanyEnterprise(@RequestParam(value = "name") String name){
        return companyEnterpriseService.selectCompanyEnterprise(name);
    }

    @ResponseBody
    @RequestMapping(value = "/selectCompanyEnterpriseByPid", method = RequestMethod.GET)
    public CompanyEnterpriseEntity selectCompanyEnterpriseByPid(@RequestParam(value = "pid") Long pid){
        return companyEnterpriseService.selectCompanyEnterpriseByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/selectCompanyEnterpriseInfoByEnterpriseId", method = RequestMethod.GET)
    public CompanyEnterpriseInfoEntity selectCompanyEnterpriseInfoByEnterpriseId(@RequestParam(value = "pid") Long enterpriseId){
        return companyEnterpriseService.selectCompanyEnterpriseInfoByEnterpriseId(enterpriseId);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyLegal", method = RequestMethod.GET)
    public CompanyOperationEntity searchCompanyLegal(@RequestParam(value = "enterpriseId") Long enterpriseId){
        return companyEnterpriseService.searchCompanyLegal(enterpriseId);
    }

    @ResponseBody
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    public CompanyEnterpriseEntity selectByName(@RequestParam(value = "name") String name) {
            return companyEnterpriseService.selectByName(name);
    }

    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */


    @ResponseBody
    @RequestMapping(value = "/selectCustomerManagementList", method = RequestMethod.POST)
    public Message selectCustomerManagementList(@RequestBody CustomerFrom customerFrom){
        return companyEnterpriseService.selectCustomerManagementList(customerFrom);
    }


    @ResponseBody
    @RequestMapping(value = "/selectCustomerManagementDetail", method = RequestMethod.GET)
    public Message selectCustomerManagementDetail(@RequestParam(value = "id") Long id){
        return companyEnterpriseService.selectCustomerManagementDetail(id);
    }

    @ResponseBody
    @RequestMapping(value = "/selectCustomerBlackList", method = RequestMethod.POST)
    public Message selectCustomerBlackList(@RequestBody BlackListFrom blackListFrom){
        return companyEnterpriseService.selectCustomerBlackList(blackListFrom);
    }


    @ResponseBody
    @RequestMapping(value = "/deleteBlackList", method = RequestMethod.GET)
    public Message deleteBlackList(@RequestParam(value = "id") Long id, @RequestParam(value = "causationInfo")String causationInfo){
        return companyEnterpriseService.deleteBlackList(id, causationInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/submitBlackList", method = RequestMethod.GET)
    public Message submitBlackList(@RequestParam(value = "id") Long id, @RequestParam(value = "causationInfo")String causationInfo){
        return companyEnterpriseService.submitBlackList(id, causationInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/searchListConfirmationCompany", method = RequestMethod.GET)
    public Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany(){
        Message<List<CompanyEnterpriseEntity>> message = companyEnterpriseService.searchListConfirmationCompany();
        return message;
    }

}
