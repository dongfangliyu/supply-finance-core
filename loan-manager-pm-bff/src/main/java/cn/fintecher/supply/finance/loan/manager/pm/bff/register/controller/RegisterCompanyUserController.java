package cn.fintecher.supply.finance.loan.manager.pm.bff.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.RegisterCompanyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 2:36
 */
@RestController
@RequestMapping("/register/user")
@Api(tags = "企业注册")
public class RegisterCompanyUserController {

    @Autowired
    private RegisterCompanyUserService registerCompanyUserService;

    /**
     * 查询当前步骤
     * @param registerCode
     * @return
     */
    @ApiOperation(value="查询当前步骤 ", notes="查询当前步骤")
    @RequestMapping(value ="/selectRegisteCurrentStep", method = RequestMethod.GET)
    public Message selectRegisteCurrentStep(@RequestParam(value = "registerCode") String registerCode,@RequestParam(value = "enterpriseType") String enterpriseType){
        Message message = registerCompanyUserService.selectRegisteCurrentStep(registerCode,enterpriseType);
        return message;
    }


    /**
     * 获取手机验证码
     * @param registerCode
     * @return
     */
    @ApiOperation(value="获取手机验证码 ", notes="获取手机验证码")
    @RequestMapping(value ="/createMobileConfirmCode", method = RequestMethod.GET)
    public Message createMobileConfirmCode(@RequestParam(value = "registerCode") String registerCode){
        Message<Object> message = new Message<>();
//        Message message = registerCompanyUserService.selectRegisteCurrentStep(registerCode);
        return message;
    }

    /**
     * 创建企业注册用户
     * @param registerUserForm
     * @return
     */
    @ApiOperation(value="创建企业注册用户 ", notes="创建企业注册用户")
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
    @ApiOperation(value="更新企业创建用户 ", notes="更新企业创建用户")
    @RequestMapping(value ="/updateRegisteCompanyUser", method = RequestMethod.POST)
    public Message updateRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity){
        Message message = registerCompanyUserService.updateRegisteCompanyUser(registerUserEntity);
        return message;
    }


    /**
     * 提交注册基本信息
     * @param registerCompanyForm
     * @return
     */
    @ApiOperation(value="提交注册基本信息 ", notes="提交注册基本信息")
    @RequestMapping(value ="/submitRegisteCompanyBaseInfo", method = RequestMethod.POST)
    public Message submitRegisteCompanyBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = registerCompanyUserService.submitRegisteCompanyBaseInfo(registerCompanyForm);
        return message;
    }

    /**
     * 提交注册详细信息
     * @param registerCompanyForm
     * @return
     */
    @ApiOperation(value="提交注册详细信息 ", notes="提交注册详细信息")
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
    @ApiOperation(value="查询企业注册主要信息 ", notes="查询企业注册主要信息")
    @RequestMapping(value ="/searchRegisteCompanyPrimaryInfo", method = RequestMethod.GET)
    public Message searchRegisteCompanyPrimaryInfo(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.searchRegisteCompanyPrimaryInfo(registerCode);
        return message;
    }

    /**
     * 查询注册基本信息
     * @param registerCode
     * @return
     */
    @ApiOperation(value="查询注册基本信息 ", notes="查询注册基本信息")
    @RequestMapping(value ="/searchRegisteCompanyBaseInfo", method = RequestMethod.GET)
    public Message searchRegisteCompanyBaseInfo(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.searchRegisteCompanyBaseInfo(registerCode);
        return message;
    }

    /**
     * 重置企业注册数据
     * @param registerCode
     * @return
     */
    @ApiOperation(value="查询企业注册主要信息 ", notes="查询企业注册主要信息")
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
    @ApiOperation(value="确认无误，提交认证 ", notes="确认无误，提交认证")
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
    @ApiOperation(value="显示注册企业提示信息 ", notes="显示注册企业提示信息")
    @RequestMapping(value ="/showRegisteCompanyHintMsg", method = RequestMethod.GET)
    public Message showRegisteCompanyHintMsg(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.showRegisteCompanyHintMsg(registerCode);
        return message;
    }

    /**
     * 显示注册企业确认信息
     * @param registerCode
     * @return
     */
    @ApiOperation(value="显示注册企业确认信息 ", notes="显示注册企业确认信息")
    @RequestMapping(value ="/showRegisteCompanyConfirmMsg", method = RequestMethod.GET)
    public Message showRegisteCompanyConfirmMsg(@RequestParam(value = "registerCode") String registerCode){
        Message message = registerCompanyUserService.showRegisteCompanyConfirmMsg(registerCode);
        return message;
    }


    /**
     * 显示注册企业确认信息
     * @param registerCode
     * @return
     */
    @ApiOperation(value="显示注册企业确认信息 ", notes="显示注册企业确认信息")
    @RequestMapping(value ="/confirmRegisteCompany", method = RequestMethod.GET)
    public Message confirmRegisteCompany(@RequestParam(value = "registerCode") String registerCode, @RequestParam(value = "accountAmount") int accountAmount){
        Message message = registerCompanyUserService.confirmRegisteCompany(registerCode,accountAmount);
        return message;
    }

}
