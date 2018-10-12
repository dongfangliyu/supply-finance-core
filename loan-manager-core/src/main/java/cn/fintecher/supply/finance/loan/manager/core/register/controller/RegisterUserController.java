package cn.fintecher.supply.finance.loan.manager.core.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import cn.fintecher.supply.finance.loan.manager.core.register.service.RegisterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 6:06
 */
@RestController
@RequestMapping("/register/companyUser")
public class RegisterUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserController.class);

    @Autowired
    private RegisterUserService registerUserService;


//    /**
//     * 查询企业临时注册用户
//     * @param registerUserEntity
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "searchRegisteCompanyUser", method = RequestMethod.POST)
//    public Message searchRegisteCompanyUser(@RequestBody(required = false)RegisterUserEntity registerUserEntity) {
//        return registerUserService.searchRegisteCompanyUser(registerUserEntity);
//    }

    /**
     * 查询当前步骤
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectRegisteCurrentStep", method = RequestMethod.GET)
    public Message<RegisterUserEntity> selectRegisteCurrentStep(@RequestParam(value = "registerCode")String registerCode) {
        return registerUserService.selectRegisteCurrentStep(registerCode);
    }

//    /**
//     * 根据id查询注册user
//     * @param registerCode
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value ="/selectRegisteUserById", method = RequestMethod.GET)
//    public RegisterUserEntity selectRegisteUserById(Long pid,String registerCode){
//        RegisterUserEntity registerUserEntity = registerUserService.selectRegisteUserById(pid,registerCode);
//        return registerUserEntity;
//    }

    /**
     * 创建企业注册用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/createRegisteCompanyUser", method = RequestMethod.POST)
    public Message createRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity){
        Message message = new Message();
        try {
            registerUserService.createRegisteCompanyUser(registerUserEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        } catch (Exception e) {
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_ADD);
            LOGGER.error("创建企业注册用户异常");
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 更新企业创建用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/updateRegisteCompanyUser", method = RequestMethod.POST)
    public Message updateRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity){
        Message message = new Message();
        try {
            registerUserService.updateRegisteCompanyUser(registerUserEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        } catch (Exception e) {
            message.setCode(MsgCodeConstant.ERR_REGISTER_USER_UPDATE);
            LOGGER.error("更新企业创建用户异常");
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 根据静默注册code查询企业注册用户
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisteCompanyUserByCode", method = RequestMethod.GET)
    public RegisterUserEntity searchRegisteCompanyUserByCode(@RequestParam(value = "registerCode") String registerCode){
        return registerUserService.searchRegisteCompanyUserByCode(registerCode);
    }


    /**
     * 提交注册基本信息
     * @param registerUserInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createRegisteCompanyUserInfo", method = RequestMethod.POST)
    public Message createRegisteCompanyUserInfo(@RequestBody RegisterUserInfoEntity registerUserInfoEntity){
        Message message = new Message();
        try {
            registerUserService.createRegisteCompanyUserInfo(registerUserInfoEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        } catch (Exception e) {
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_ADD);
            LOGGER.error("创建企业注册用户异常");
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 提交注册详细信息
     * @param registerUserInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRegisteCompanyUserInfo", method = RequestMethod.POST)
    public Message updateRegisteCompanyUserInfo(@RequestBody RegisterUserInfoEntity registerUserInfoEntity){
        Message message = new Message();
        try {
            registerUserService.updateRegisteCompanyUserInfo(registerUserInfoEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        } catch (Exception e) {
            message.setCode(MsgCodeConstant.ERR_REGISTER_COMPANY_UPDATE);
            LOGGER.error("创建企业注册用户异常");
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 查询企业注册主要信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisteCompanyPrimaryInfo", method = RequestMethod.GET)
    public CompanyPrimaryInfoResponse searchRegisteCompanyPrimaryInfo(@RequestParam(value = "registerCode") String registerCode){
        CompanyPrimaryInfoResponse companyPrimaryInfoResponse = registerUserService.searchRegisteCompanyPrimaryInfo(registerCode);
        return companyPrimaryInfoResponse;
    }

    /**
     * 通过code查询企业信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisteCompanyUserInfoByCode", method = RequestMethod.GET)
    public RegisterUserInfoEntity searchRegisteCompanyUserInfoByCode(@RequestParam(value = "registerCode") String registerCode){
        return registerUserService.searchRegisteCompanyUserInfoByCode(registerCode);
    }

    /**
     * 保存企业信息
     * @param enterpriseEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveEnterpriseEntity", method = RequestMethod.POST)
    public Message saveEnterpriseEntity(@RequestBody CompanyEnterpriseEntity enterpriseEntity){
        return registerUserService.saveEnterpriseEntity(enterpriseEntity);
    }

    /**
     * 保存企业详情信息
     * @param companyEnterpriseInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCompanyEnterpriseInfoEntity", method = RequestMethod.POST)
    public void saveCompanyEnterpriseInfoEntity(@RequestBody CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity){
        registerUserService.saveCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
    }

    /**
     * 保存企业人员信息
     * @param companyOperationEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCompanyOperationEntity", method = RequestMethod.POST)
    public void saveCompanyOperationEntity(@RequestBody CompanyOperationEntity companyOperationEntity){
        registerUserService.saveCompanyOperationEntity(companyOperationEntity);
    }

    /**
     * 保存企业银行
     * @param baseBankInfoEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveBaseInfoEntity", method = RequestMethod.POST)
    public void saveBaseInfoEntity(@RequestBody BaseBankInfoEntity baseBankInfoEntity){
        registerUserService.saveBaseInfoEntity(baseBankInfoEntity);
    }

    /**
     * 保存企业注册信息
     * @param companyUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCompanyUserEntity", method = RequestMethod.POST)
    public void saveCompanyUserEntity(@RequestBody CompanyUserEntity companyUserEntity){
        registerUserService.saveCompanyUserEntity(companyUserEntity);
    }

    /**
     * 根据username查询注册用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterUserByUserName", method = RequestMethod.GET)
    public List<RegisterUserEntity> searchRegisterUserByUserName(@RequestParam(value = "userName") String userName){
        return registerUserService.searchRegisterUserByUserName(userName);
    }

    /**
     * 根据username查询注册用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterUserByUserNameLast", method = RequestMethod.GET)
    public RegisterUserEntity searchRegisterUserByUserNameLast(@RequestParam(value = "userName") String userName){
        return registerUserService.searchRegisterUserByUserNameLast(userName);
    }

    /**
     * 根据username查询企业用户信息
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchCompanyUserByUserName", method = RequestMethod.GET)
    public CompanyUserEntity searchCompanyUserByUserName(@RequestParam(value = "userName") String userName){
        return registerUserService.searchCompanyUserByUserName(userName);
    }

    /**
     * 根据企业名称查询注册企业
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchCompanyByName", method = RequestMethod.GET)
    public CompanyEnterpriseEntity searchCompanyByName(@RequestParam(value = "name") String name){
        return registerUserService.searchCompanyByName(name);
    }

    /**
     * 根据企业名称查询企业信息
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisteCompanyByName", method = RequestMethod.GET)
    public RegisterUserInfoEntity searchRegisteCompanyByName(@RequestParam(value = "name") String name){
        return registerUserService.searchRegisteCompanyByName(name);
    }

    /**
     * 根据企业id查询注册企业信息
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterUserInfoByPid", method = RequestMethod.GET)
    public RegisterUserInfoEntity searchRegisterUserInfoByPid(@RequestParam(value = "pid") String pid){
        return registerUserService.searchRegisterUserInfoByPid(pid);
    }

}
