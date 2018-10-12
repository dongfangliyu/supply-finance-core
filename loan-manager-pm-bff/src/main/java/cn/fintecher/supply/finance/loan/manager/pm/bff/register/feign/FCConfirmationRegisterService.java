package cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 10:40
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmationRegisterService {
    /**
     * 创建企业注册用户
     * @param registerUserForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/confirmation/register/createRegisteCompanyUser", method = RequestMethod.POST)
    public Message createRegisteCompanyUser(@RequestBody RegisterUserForm registerUserForm);

    /**
     * 更新企业创建用户
     * @param registerCompanyForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/confirmation/register/submitRegisteCompanyBaseInfo", method = RequestMethod.POST)
    Message submitRegisteCompanyBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm);
}
