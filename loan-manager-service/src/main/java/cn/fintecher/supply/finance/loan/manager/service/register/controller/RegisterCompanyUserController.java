package cn.fintecher.supply.finance.loan.manager.service.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import cn.fintecher.supply.finance.loan.manager.service.register.service.RegisterCompanyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 2:00
 */
@RestController
@RequestMapping("/register/company")
public class RegisterCompanyUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCompanyUserController.class);

    @Autowired
    private RegisterCompanyUserService registerCompanyUserService;


    /**
     * 查询当前步骤
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/selectRegisteCurrentStep", method = RequestMethod.GET)
    public Message selectRegisteCurrentStep(@RequestParam(value = "registerCode") String registerCode,@RequestParam(value = "enterpriseType") String enterpriseType){
        Message message = registerCompanyUserService.selectRegisteCurrentStep(registerCode,enterpriseType);
        return message;
    }

    /**
     * 创建企业注册用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/createRegisteCompanyUser", method = RequestMethod.POST)
    public Message createRegisteCompanyUser(@RequestBody RegisterUserForm registerUserForm){
        Message message = registerCompanyUserService.createRegisteCompanyUser(registerUserForm);
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
        Message message = registerCompanyUserService.updateRegisteCompanyUser(registerUserEntity);
        return message;
    }

    /**
     * 提交基本信息
     * @param registerCompanyForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/submitRegisteCompanyBaseInfo", method = RequestMethod.POST)
    public Message submitRegisteCompanyBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = registerCompanyUserService.submitRegisteCompanyBaseInfo(registerCompanyForm);
        return message;
    }

    /**
     * 更新企业创建用户
     * @param registerCompanyForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/submitRegisteCompanyDetailInfo", method = RequestMethod.POST)
    public Message submitRegisteCompanyDetailInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = registerCompanyUserService.submitRegisteCompanyDetailInfo(registerCompanyForm);
        return message;
    }

    /**
     * 查询企业注册主要信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchRegisteCompanyPrimaryInfo", method = RequestMethod.GET)
    public Message searchRegisteCompanyPrimaryInfo(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.searchRegisteCompanyPrimaryInfo(registerCode);
        return message;
    }


    /**
     * 重置企业注册数据
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/resetRegisteCompanyData", method = RequestMethod.GET)
    public Message resetRegisteCompanyData(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.resetRegisteCompanyData(registerCode);
        return message;
    }

    /**
     * 确认无误，提交认证
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/submitRegisteCompany", method = RequestMethod.GET)
    public Message submitRegisteCompany(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.submitRegisteCompany(registerCode);
        return message;
    }

    /**
     * 显示注册企业提示信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/showRegisteCompanyHintMsg", method = RequestMethod.GET)
    public Message showRegisteCompanyHintMsg(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.showRegisteCompanyHintMsg(registerCode);
        return message;
    }

    /**
     * 显示注册企业提示信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/confirmRegisteCompany", method = RequestMethod.GET)
    public Message confirmRegisteCompany(@RequestParam(value = "registerCode") String registerCode, @RequestParam(value = "accountAmount")  int accountAmount){
        Message message = registerCompanyUserService.confirmRegisteCompany(registerCode,accountAmount);
        return message;
    }

}
