package cn.fintecher.supply.finance.loan.manager.service.login.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;
import cn.fintecher.supply.finance.loan.manager.service.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/11 15:12
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 刷新Token
     * @param LoginAdminUser
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public Message refreshToken(@RequestParam("refreshToken") String refreshToken){
        return loginService.refreshToken(refreshToken);
    }

    /**
     * 退出登录
     * @param LoginAdminUser
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toLogout", method = RequestMethod.GET)
    public Message toLogout(@RequestParam("accessToken") String accessToken){
        return loginService.toLogout(accessToken);
    }


    /**
     * 后台用户登录
     *
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sysUserToLogin", method = RequestMethod.POST)
    public Message sysUserToLogin(@RequestBody LoginAdminUser loginAdminUser){
        return  loginService.sysUserToLogin(loginAdminUser);
    }

    /**
     * 后台用户登录
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/companyUserToLogin", method = RequestMethod.POST)
    public Message companyUserToLogin(@RequestBody LoginAdminUser loginAdminUser){
        return  loginService.companyUserToLogin(loginAdminUser);
    }


    /**
     * 保兑仓企业用户登录
     *
     * @param LoginAdminUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirmationCompanyUserToLogin", method = RequestMethod.POST)
    public Message confirmationCompanyUserToLogin(@RequestBody LoginAdminUser loginAdminUser){
        return  loginService.confirmationCompanyUserToLogin(loginAdminUser);
    }

}
