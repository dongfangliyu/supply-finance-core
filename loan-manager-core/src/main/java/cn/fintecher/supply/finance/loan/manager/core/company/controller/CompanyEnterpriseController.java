package cn.fintecher.supply.finance.loan.manager.core.company.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 11:05
 */
@RestController
@RequestMapping("/company/enterpriseCore")
public class CompanyEnterpriseController {

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    /**
     * 查询企业基本信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchCompanyEnterpriseEntity", method = RequestMethod.GET)
    public CompanyEnterpriseEntity searchCompanyEnterpriseEntity(@RequestParam(value = "enterpriseId") Long enterpriseId){
        return companyEnterpriseService.searchCompanyEnterpriseEntity(enterpriseId);
    }

    /**
     * 查询企业详细信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchCompanyEnterpriseInfoEntity", method = RequestMethod.GET)
    public CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(Long enterpriseId){
        return companyEnterpriseService.searchCompanyEnterpriseInfoEntity(enterpriseId);
    }

    /**
     * 查询银行信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchBaseBankInfo", method = RequestMethod.GET)
    public BaseBankInfoEntity searchBaseBankInfo(@RequestParam(value = "enterpriseId") Long enterpriseId){
        return companyEnterpriseService.searchBaseBankInfo(enterpriseId);
    }

    /**
     * 查询企业人员信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchCompanyOperation", method = RequestMethod.GET)
    public List<CompanyOperationEntity> searchCompanyOperation(@RequestParam(value = "enterpriseId") Long enterpriseId){
        return companyEnterpriseService.searchCompanyOperation(enterpriseId);
    }

    /**
     * 查询企业法人信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchCompanyLegal", method = RequestMethod.GET)
    public CompanyOperationEntity searchCompanyLegal(@RequestParam(value = "enterpriseId") Long enterpriseId){
        return companyEnterpriseService.searchCompanyLegal(enterpriseId);
    }


    @ResponseBody
    @RequestMapping(value ="/searchCompanyByNameAndEnterpriceType", method = RequestMethod.GET)
    public List<CompanyEnterpriseEntity> searchCompanyByNameAndEnterpriceType(@RequestParam(value = "name")String name,@RequestParam(value = "enterpriseType")String enterpriseType){
        return companyEnterpriseService.searchCompanyByNameAndEnterpriceType(name,enterpriseType);
    }

    @ResponseBody
    @RequestMapping(value ="/updateCompanyEnterpriseEntity", method = RequestMethod.POST)
    public void updateCompanyEnterpriseEntity(@RequestBody CompanyEnterpriseEntity companyEnterpriseEntity){
        companyEnterpriseService.updateCompanyEnterpriseEntity(companyEnterpriseEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/updateCompanyEnterpriseInfoEntity", method = RequestMethod.POST)
    public void updateCompanyEnterpriseInfoEntity(@RequestBody CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity){
        companyEnterpriseService.updateCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/updateCompanyOperationEntity", method = RequestMethod.POST)
    public void updateCompanyOperationEntity(@RequestBody CompanyOperationEntity entity){
        companyEnterpriseService.updateCompanyOperationEntity(entity);
    }


    @ResponseBody
    @RequestMapping(value ="/selectByName", method = RequestMethod.GET)
    public CompanyEnterpriseEntity selectByName(@RequestParam(value = "name") String name) {
       return companyEnterpriseService.selectByName(name);
    }

    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */



    @ResponseBody
    @RequestMapping(value ="/selectCustomerManagementList", method = RequestMethod.POST)
    public List<CustomerEntity> selectCustomerManagementList(@RequestBody CustomerFrom customerFrom){
        return companyEnterpriseService.selectCustomerManagementList(customerFrom);
    }


    @ResponseBody
    @RequestMapping(value ="/selectCustomerManagementCount", method = RequestMethod.POST)
    public Integer selectCustomerManagementCount(@RequestBody CustomerFrom customerFrom){
        return companyEnterpriseService.selectCustomerManagementCount(customerFrom);
    }


    @ResponseBody
    @RequestMapping(value ="/selectCustomerBlackList", method = RequestMethod.POST)
    public List<CustomerEntity> selectCustomerBlackList(@RequestBody BlackListFrom blackListFrom){
        return companyEnterpriseService.selectCustomerBlackList(blackListFrom);
    }


    @ResponseBody
    @RequestMapping(value ="/selectCustomerBlackListCount", method = RequestMethod.POST)
    public Integer selectCustomerBlackListCount(@RequestBody BlackListFrom blackListFrom){
        return companyEnterpriseService.selectCustomerBlackListCount(blackListFrom);
    }

    @ResponseBody
    @RequestMapping(value ="/searchListConfirmationCompany", method = RequestMethod.GET)
    public Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany(){
        return companyEnterpriseService.searchListConfirmationCompany();
    }

}
