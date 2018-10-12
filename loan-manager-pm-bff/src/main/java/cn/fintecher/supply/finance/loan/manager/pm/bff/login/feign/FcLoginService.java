package cn.fintecher.supply.finance.loan.manager.pm.bff.login.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/12 14:19
 */
@FeignClient(name = "loan-manager-service")
public interface FcLoginService {
    /**
     * 前台用户登录
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login/companyUserToLogin", method = RequestMethod.POST)
    Message companyUserToLogin(LoginAdminUser loginAdminUser);

    /**
     * 后台用户登录
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login/sysUserToLogin", method = RequestMethod.POST)
    public Message sysUserToLogin(@RequestBody LoginAdminUser loginAdminUser);

    /**
     * 刷新Token
     * @param LoginAdminUser
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login/refreshToken", method = RequestMethod.GET)
    public Message refreshToken(@RequestParam("refreshToken") String refreshToken);

    /**
     * 退出登录
     * @param LoginAdminUser
     * @param request
     * @return
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/login/toLogout", method = RequestMethod.GET)
    Message toLogout(@RequestParam("accessToken") String accessToken);

    /**
     * 保兑仓企业用户登录
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login/confirmationCompanyUserToLogin", method = RequestMethod.POST)
    Message confirmationCompanyUserToLogin(@RequestBody LoginAdminUser loginAdminUser);
}
