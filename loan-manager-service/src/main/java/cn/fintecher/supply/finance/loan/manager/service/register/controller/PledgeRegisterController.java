package cn.fintecher.supply.finance.loan.manager.service.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.service.register.service.PledgeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/8/17 0017下午 3:17
 */
@RestController
@RequestMapping("/pledge/registerService")
public class PledgeRegisterController {
    
    @Autowired
    private PledgeRegisterService pledgeRegisterService;

    @ResponseBody
    @RequestMapping(value = "/verificationMobile", method = RequestMethod.GET)
    public Message verificationMobile(@RequestParam(value = "userName") String userName){
        return pledgeRegisterService.verificationMobile(userName);
    }

    @ResponseBody
    @RequestMapping(value = "/createPledgeRegisterUser", method = RequestMethod.POST)
    public Message createPledgeRegisterUser(@RequestBody RegisterUserForm registerUserForm){
        return pledgeRegisterService.createPledgeRegisterUser(registerUserForm);
    }

    @ResponseBody
    @RequestMapping(value = "/updatePledgeRegisterUser", method = RequestMethod.POST)
    public Message updatePledgeRegisterUser(@RequestBody RegisterUserEntity registerUserEntity){
        return pledgeRegisterService.updatePledgeRegisterUser(registerUserEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/submitPledgeRegisterBaseInfo", method = RequestMethod.POST)
    public Message submitPledgeRegisterBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        return pledgeRegisterService.submitPledgeRegisterBaseInfo(registerCompanyForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitPledgeRegisterDetailInfo", method = RequestMethod.POST)
    public Message submitPledgeRegisterDetailInfo(@RequestBody RegisterCompanyForm registerCompanyForm){
        return pledgeRegisterService.submitPledgeRegisterDetailInfo(registerCompanyForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchPledgeRegisterPrimaryInfo", method = RequestMethod.GET)
    public Message searchPledgeRegisterPrimaryInfo(@RequestParam(value = "registerCode") String registerCode){
        return pledgeRegisterService.searchPledgeRegisterPrimaryInfo(registerCode);
    }

    @ResponseBody
    @RequestMapping(value = "/submitSuccess", method = RequestMethod.POST)
    public Message submitSuccess(@RequestBody RegisterCompanyForm registerCompanyForm){
        return pledgeRegisterService.submitSuccess(registerCompanyForm);
    }
}
