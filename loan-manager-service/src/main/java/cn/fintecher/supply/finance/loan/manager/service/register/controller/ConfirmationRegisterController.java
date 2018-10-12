package cn.fintecher.supply.finance.loan.manager.service.register.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.service.register.service.ConfirmationRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 10:12
 */
@RestController
@RequestMapping("/confirmation/register")
@Api(tags = "保兑企业注册")
public class ConfirmationRegisterController {

    @Autowired
    private ConfirmationRegisterService confirmationRegisterService;

    /**
     * 创建企业注册用户
     * @param registerUserForm
     * @return
     */
    @ApiOperation(value="创建企业注册用户 ", notes="创建企业注册用户")
    @RequestMapping(value ="/createRegisteCompanyUser", method = RequestMethod.POST)
    public Message createRegisteCompanyUser(@RequestBody RegisterUserForm registerUserForm){
        Message message = confirmationRegisterService.createRegisteCompanyUser(registerUserForm);
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
        Message message = confirmationRegisterService.submitRegisteCompanyBaseInfo(registerCompanyForm);
        return message;
    }

}
