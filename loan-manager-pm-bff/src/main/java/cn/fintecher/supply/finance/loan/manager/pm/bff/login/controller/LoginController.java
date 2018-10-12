package cn.fintecher.supply.finance.loan.manager.pm.bff.login.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;
import cn.fintecher.supply.finance.loan.manager.pm.bff.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/11 15:12
 */
@RestController
@RequestMapping("login")
@Api(tags = "用户登录相关接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 刷新Token
     * @param LoginAdminUser
     * @param request
     * @return
             */
    @ApiOperation(value = "刷新Token", notes = "刷新Token")
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
    @ApiOperation(value = "退出登录", notes = "退出登录")
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
    @ApiOperation(value = "后台用户登录", notes = "后台用户登录")
    @RequestMapping(value = "/sysUserToLogin", method = RequestMethod.POST)
    public Message sysUserToLogin(@RequestBody LoginAdminUser loginAdminUser){
        return  loginService.sysUserToLogin(loginAdminUser);
    }

    /**
     * 前台用户登录
     *
     * @param LoginAdminUser
     * @return
     */
    @ApiOperation(value = "前台用户登录", notes = "前台用户登录")
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
    @ApiOperation(value = "保兑仓企业用户登录", notes = "保兑仓企业用户登录")
    @RequestMapping(value = "/confirmationCompanyUserToLogin", method = RequestMethod.POST)
    public Message confirmationCompanyUserToLogin(@RequestBody LoginAdminUser loginAdminUser){
        return  loginService.confirmationCompanyUserToLogin(loginAdminUser);
    }

}
