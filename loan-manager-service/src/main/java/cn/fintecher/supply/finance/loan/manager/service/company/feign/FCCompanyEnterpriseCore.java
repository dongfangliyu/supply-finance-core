package cn.fintecher.supply.finance.loan.manager.service.company.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:25
 */
@FeignClient(name = "loan-manager-core")
public interface FCCompanyEnterpriseCore {

    /**
     * 查询企业基本信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchCompanyEnterpriseEntity", method = RequestMethod.GET)
    CompanyEnterpriseEntity searchCompanyEnterpriseEntity(@RequestParam(value = "enterpriseId") Long enterpriseId);

    /**
     * 查询企业详细信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchCompanyEnterpriseInfoEntity", method = RequestMethod.GET)
    CompanyEnterpriseInfoEntity searchCompanyEnterpriseInfoEntity(@RequestParam(value = "enterpriseId") Long enterpriseId);

    /**
     * 查询银行信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchBaseBankInfo", method = RequestMethod.GET)
    BaseBankInfoEntity searchBaseBankInfo(@RequestParam(value = "enterpriseId") Long enterpriseId);

    /**
     * 查询企业人员信息
     * @param enterpriseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchCompanyOperation", method = RequestMethod.GET)
    List<CompanyOperationEntity> searchCompanyOperation(@RequestParam(value = "enterpriseId") Long enterpriseId);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchCompanyLegal", method = RequestMethod.GET)
    CompanyOperationEntity searchCompanyLegal(@RequestParam(value = "enterpriseId")Long enterpriseId);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchCompanyByNameAndEnterpriceType", method = RequestMethod.GET)
    List<CompanyEnterpriseEntity> searchCompanyByNameAndEnterpriceType(@RequestParam(value = "name")String name,@RequestParam(value = "enterpriseType")String enterpriseType);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/updateCompanyEnterpriseEntity", method = RequestMethod.POST)
    void updateCompanyEnterpriseEntity(@RequestBody CompanyEnterpriseEntity companyEnterpriseEntity);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/updateCompanyEnterpriseInfoEntity", method = RequestMethod.POST)
    void updateCompanyEnterpriseInfoEntity(@RequestBody CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/updateCompanyOperationEntity", method = RequestMethod.POST)
    void updateCompanyOperationEntity(@RequestBody CompanyOperationEntity entity);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/selectByName", method = RequestMethod.GET)
    CompanyEnterpriseEntity selectByName(@RequestParam(value = "name") String name);

    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/selectCustomerManagementList", method = RequestMethod.POST)
    List<CustomerEntity> selectCustomerManagementList(@RequestBody CustomerFrom customerFrom);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/selectCustomerManagementCount", method = RequestMethod.POST)
    Integer selectCustomerManagemenCount(@RequestBody CustomerFrom customerFrom);


    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/selectCustomerBlackList", method = RequestMethod.POST)
    List<CustomerEntity> selectCustomerBlackList(@RequestBody BlackListFrom blackListFrom);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/selectCustomerBlackListCount", method = RequestMethod.POST)
    Integer selectCustomerBlackListCount(@RequestBody BlackListFrom blackListFrom);

    @ResponseBody
    @RequestMapping(value ="/company/enterpriseCore/searchListConfirmationCompany", method = RequestMethod.GET)
    Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany();


}
