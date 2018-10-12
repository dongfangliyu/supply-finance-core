package cn.fintecher.supply.finance.loan.manager.service.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 4:41
 */
@FeignClient(name = "loan-manager-core")
public interface FCRegisterCompanyCore {

    /**
     * 查询当前步骤
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/companyUser/selectRegisteCurrentStep", method = RequestMethod.GET)
    public Message<RegisterUserEntity> selectRegisteCurrentStep(@RequestParam(value = "registerCode") String registerCode);


//    /**
//     * 查询企业注册用户
//     * @param registerUserEntity
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/register/companyUser/searchRegisteCompanyUser", method = RequestMethod.GET)
//    Message searchRegisteCompanyUser(@RequestBody(required = false)RegisterUserEntity registerUserEntity);


    /**
     * 根据静默注册code查询企业注册用户
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisteCompanyUserByCode", method = RequestMethod.GET)
    RegisterUserEntity searchRegisteCompanyUserByCode(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 创建企业注册用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/createRegisteCompanyUser", method = RequestMethod.POST)
    Message createRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity);

    /**
     * 更新企业创建用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/updateRegisteCompanyUser", method = RequestMethod.POST)
    Message updateRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity);


//    /**
//     * 根据id查询注册user
//     * @param registerCode
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value ="/selectRegisteUserById", method = RequestMethod.GET)
//    RegisterUserEntity selectRegisteUserById(Long pid,String registerCode);

    /**
     * 提交注册基本信息
     * @param registerUserInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/createRegisteCompanyUserInfo", method = RequestMethod.POST)
    Message createRegisteCompanyUserInfo(@RequestBody RegisterUserInfoEntity registerUserInfoEntity);

    /**
     * 提交注册详细信息
     * @param registerUserInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/updateRegisteCompanyUserInfo", method = RequestMethod.POST)
    Message updateRegisteCompanyUserInfo(@RequestBody RegisterUserInfoEntity registerUserInfoEntity);

    /**
     * 查询企业注册主要信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisteCompanyPrimaryInfo", method = RequestMethod.GET)
    CompanyPrimaryInfoResponse searchRegisteCompanyPrimaryInfo(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 通过code查询企业信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisteCompanyUserInfoByCode", method = RequestMethod.GET)
    RegisterUserInfoEntity searchRegisteCompanyUserInfoByCode(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 保存企业信息
     * @param enterpriseEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/saveEnterpriseEntity", method = RequestMethod.POST)
    Message saveEnterpriseEntity(@RequestBody CompanyEnterpriseEntity enterpriseEntity);

    /**
     * 保存企业详情信息
     * @param companyEnterpriseInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/saveCompanyEnterpriseInfoEntity", method = RequestMethod.POST)
    void saveCompanyEnterpriseInfoEntity(@RequestBody CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity);

    /**
     * 保存企业人员信息
     * @param companyOperationEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/saveCompanyOperationEntity", method = RequestMethod.POST)
    void saveCompanyOperationEntity(@RequestBody CompanyOperationEntity companyOperationEntity);

    /**
     * 保存企业银行
     * @param baseBankInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/saveBaseInfoEntity", method = RequestMethod.POST)
    void saveBaseInfoEntity(@RequestBody BaseBankInfoEntity baseBankInfoEntity);

    /**
     * 保存企业注册信息
     * @param companyUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/saveCompanyUserEntity", method = RequestMethod.POST)
    void saveCompanyUserEntity(@RequestBody CompanyUserEntity companyUserEntity);

    /**
     * 根据username查询注册用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisterUserByUserName", method = RequestMethod.GET)
    List<RegisterUserEntity> searchRegisterUserByUserName(@RequestParam(value = "userName") String userName);

    /**
     * 根据username查询注册用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisterUserByUserNameLast", method = RequestMethod.GET)
    RegisterUserEntity searchRegisterUserByUserNameLast(@RequestParam(value = "userName") String userName);

    /**
     * 根据username查询企业用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchCompanyUserByUserName", method = RequestMethod.GET)
    CompanyUserEntity searchCompanyUserByUserName(@RequestParam(value = "userName") String userName);

    /**
     * 根据企业名称查询注册企业
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchCompanyByName", method = RequestMethod.GET)
    CompanyEnterpriseEntity searchCompanyByName(@RequestParam(value = "name") String name);

    /**
     * 根据企业名称查询企业信息
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisteCompanyByName", method = RequestMethod.GET)
    RegisterUserInfoEntity searchRegisteCompanyByName(@RequestParam(value = "name") String name);

    /**
     * 根据企业id查询注册企业信息
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/companyUser/searchRegisterUserInfoByPid", method = RequestMethod.GET)
    RegisterUserInfoEntity searchRegisterUserInfoByPid(@RequestParam(value = "pid") String pid);
}
