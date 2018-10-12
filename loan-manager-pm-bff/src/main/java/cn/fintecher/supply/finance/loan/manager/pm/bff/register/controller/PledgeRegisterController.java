package cn.fintecher.supply.finance.loan.manager.pm.bff.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.PledgeRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/8/17 0017上午 9:51
 */
@RestController
@RequestMapping("/pledge/register")
@Api(tags = "质押企业注册")
public class PledgeRegisterController {

    @Autowired
    private PledgeRegisterService pledgeRegisterService;

    /**
     * 验证手机号是否重复
     * @param userName
     * @return
     */
    @ApiOperation(value="验证手机号是否重复 ", notes="验证手机号是否重复")
    @RequestMapping(value ="/verificationMobile", method = RequestMethod.GET)
    public Message verificationMobile(@RequestParam(value = "userName") String userName){
        Message message = pledgeRegisterService.verificationMobile(userName);
        return message;
    }

    /**
     * 创建质押企业注册用户
     * @param registerUserForm
     * @return
     */
    @ApiOperation(value="创建质押企业注册用户 ", notes="创建质押企业注册用户")
    @RequestMapping(value ="/createPledgeRegisterUser", method = RequestMethod.POST)
    public Message createPledgeRegisterUser(@RequestBody RegisterUserForm registerUserForm){
        Message message = pledgeRegisterService.createPledgeRegisterUser(registerUserForm);
        return message;
    }

    /**
     * 更新质押企业创建用户(密码)
     * @param registerUserEntity
     * @return
     */
    @ApiOperation(value="更新质押企业创建用户(密码) ", notes="更新质押企业创建用户(密码)")
    @RequestMapping(value ="/updatePledgeRegisterUser", method = RequestMethod.POST)
    public Message updatePledgeRegisterUser(@RequestBody RegisterUserEntity registerUserEntity){
        Message message = pledgeRegisterService.updatePledgeRegisterUser(registerUserEntity);
        return message;
    }

    /**
     * 提交质押注册基本信息
     * @param registerCompanyForm
     * @return
     */
    @ApiOperation(value="提交质押注册基本信息 ", notes="提交质押注册基本信息")
    @RequestMapping(value ="/submitPledgeRegisterBaseInfo", method = RequestMethod.POST)
    public Message submitPledgeRegisterBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = pledgeRegisterService.submitPledgeRegisterBaseInfo(registerCompanyForm);
        return message;
    }

    /**
     * 提交质押注册详细信息 完成注册
     * @param registerCompanyForm
     * @return
     */
    @ApiOperation(value="提交质押注册详细信息 完成注册 ", notes="提交质押注册详细信息 完成注册")
    @RequestMapping(value ="/submitPledgeRegisterDetailInfo", method = RequestMethod.POST)
    public Message submitPledgeRegisterDetailInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = pledgeRegisterService.submitPledgeRegisterDetailInfo(registerCompanyForm);
        return message;
    }

    /**
     * 完成注册
     * @param registerCompanyForm
     * @return
     */
    @ApiOperation(value="完成注册", notes="完成注册")
    @RequestMapping(value ="/submitSuccess", method = RequestMethod.POST)
    public Message submitSuccess(@RequestBody RegisterCompanyForm registerCompanyForm){
        Message message = pledgeRegisterService.submitSuccess(registerCompanyForm);
        return message;
    }

    /**
     * 查询质押企业注册信息
     * @param registerCode
     * @return
     */
    @ApiOperation(value="查询质押企业注册信息 ", notes="查询质押企业注册信息")
    @RequestMapping(value ="/searchPledgeRegisterPrimaryInfo", method = RequestMethod.GET)
    public Message searchPledgeRegisterPrimaryInfo(@RequestParam(value = "registerCode") String registerCode){
        Message message = pledgeRegisterService.searchPledgeRegisterPrimaryInfo(registerCode);
        return message;
    }



}
