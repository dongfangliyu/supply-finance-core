package cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterCompanyForm;
import cn.fintecher.supply.finance.loan.manager.common.form.RegisterUserForm;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 1:57
 */
@FeignClient(name = "loan-manager-service")
public interface FCRegisterCompanyService {

    @ResponseBody
    @RequestMapping(value = "/auto/register/addOrUpdateUser", method = RequestMethod.GET)
    public Message addOrUpdateUser();

    /**
     * 查询当前步骤
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/selectRegisteCurrentStep", method = RequestMethod.GET)
    public Message selectRegisteCurrentStep(@RequestParam(value = "registerCode") String registerCode,@RequestParam(value = "enterpriseType") String enterpriseType);

    /**
     * 创建企业注册用户
     * @param registerUserForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/createRegisteCompanyUser", method = RequestMethod.POST)
    public Message createRegisteCompanyUser(@RequestBody RegisterUserForm registerUserForm);

    /**
     * 更新企业创建用户
     * @param registerUserEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/updateRegisteCompanyUser", method = RequestMethod.POST)
    public Message updateRegisteCompanyUser(@RequestBody RegisterUserEntity registerUserEntity);

    /**
     * 更新企业创建用户
     * @param registerCompanyForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/submitRegisteCompanyBaseInfo", method = RequestMethod.POST)
    Message submitRegisteCompanyBaseInfo(@RequestBody RegisterCompanyForm registerCompanyForm);

    /**
     * 更新企业创建用户
     * @param registerCompanyForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/submitRegisteCompanyDetailInfo", method = RequestMethod.POST)
    Message submitRegisteCompanyDetailInfo(@RequestBody RegisterCompanyForm registerCompanyForm);

    /**
     * 查询企业注册信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/searchRegisteCompanyPrimaryInfo", method = RequestMethod.GET)
    Message searchRegisteCompanyPrimaryInfo(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 重置企业注册数据
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/resetRegisteCompanyData", method = RequestMethod.GET)
    Message resetRegisteCompanyData(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 确认无误，提交认证
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/submitRegisteCompany", method = RequestMethod.GET)
    Message submitRegisteCompany(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 显示注册企业提示信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/showRegisteCompanyHintMsg", method = RequestMethod.GET)
    Message showRegisteCompanyHintMsg(@RequestParam(value = "registerCode") String registerCode);

    /**
     * 显示注册企业提示信息
     * @param registerCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/register/company/confirmRegisteCompany", method = RequestMethod.GET)
    Message confirmRegisteCompany(@RequestParam(value = "registerCode") String registerCode, @RequestParam(value = "accountAmount") int accountAmount);

}
