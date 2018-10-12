package cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/8/17 0017上午 9:53
 */
@FeignClient(name = "loan-manager-service")
public interface FCPledgeRegisterService {

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/verificationMobile", method = RequestMethod.GET)
    Message verificationMobile(@RequestParam(value = "userName") String userName);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/createPledgeRegisterUser", method = RequestMethod.POST)
    Message createPledgeRegisterUser(@RequestBody RegisterUserForm registerUserForm);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/updatePledgeRegisterUser", method = RequestMethod.POST)
    Message updatePledgeRegisterUser(@RequestBody RegisterUserEntity registerUserEntity);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/submitPledgeRegisterBaseInfo", method = RequestMethod.POST)
    Message submitPledgeRegisterBaseInfo(RegisterCompanyForm registerCompanyForm);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/submitPledgeRegisterDetailInfo", method = RequestMethod.POST)
    Message submitPledgeRegisterDetailInfo(@RequestBody RegisterCompanyForm registerCompanyForm);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/searchPledgeRegisterPrimaryInfo", method = RequestMethod.GET)
    Message searchPledgeRegisterPrimaryInfo(@RequestParam(value = "registerCode") String registerCode);

    @ResponseBody
    @RequestMapping(value = "/pledge/registerService/submitSuccess", method = RequestMethod.POST)
    Message submitSuccess(@RequestBody RegisterCompanyForm registerCompanyForm);
}
