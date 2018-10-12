package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags="用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 验证录用户密码
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="验证用户密码", notes="验证用户密码")
    @RequestMapping(value ="/verPassWord", method = RequestMethod.GET)
    public CommonResponse verPassWord(@RequestParam(value="passWord",required=true)String passWord, Principal principal){
        CommonResponse response = sysUserService.verPassWord(passWord,principal.getName());
        return response;
    }


    /**
     * 根据用户名获取用户信息
     */
    @ApiOperation(value="根据用户名获取用户信息", notes="根据用户名获取用户信息")
    @RequestMapping(value ="/userInfoByName/{userName}", method = RequestMethod.GET)
    public Message userInfoByName(@PathVariable("userName")String userName){
        return sysUserService.getUserInfoByName(userName);
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value="修改登录用户密码", notes="修改登录用户密码")
    @RequestMapping(value ="updatePassWord", method = RequestMethod.POST)
    public CommonResponse password(@RequestBody SysUserPasswordChange sysUserPasswordChange){
        CommonResponse response =sysUserService.updatePassword(sysUserPasswordChange);
        return response;
    }

    /**
     * 用户分页列表
     */
    @ApiOperation(value="用户分页列表", notes="用户分页列表")
    @RequestMapping(value ="/getUserInfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponse<EmpsResponse> getUserInfo(@RequestBody UserSearchForm userSearchForm){
        PagedResponse<EmpsResponse> pagedResponse = sysUserService.getUserInfo(userSearchForm);
        return pagedResponse;
    }

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    @ApiOperation(value="根据用户id查找用户", notes="根据用户id查找用户")
    @RequestMapping(value ="/getUserById/{userId}", method = RequestMethod.GET)
    public CommonResponse<EmpResponse> getUserById(@PathVariable("userId")Integer userId){
        CommonResponse<EmpResponse> response = sysUserService.getUserById(userId);
        return response;
    }


    /**
     * 获取所有用户信息接口
     */
    @ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
    @RequestMapping(value ="/getAllUsers", method = RequestMethod.GET)
    public CommonResponse<EmpsResponse> getAllUsers(){
        CommonResponse<EmpsResponse> response = sysUserService.getAllUsers();
        return response;
    }

    /**
     * 用户信息添加/修改／停用
     */
    @ApiOperation(value = "用户信息添加/修改／停用", notes = "用户信息添加/修改／停用")
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<UserIdResponse> addOrUpdateUser(@RequestBody SysUserAdminEntity user,Principal principal) {
        user.setOperateByName(principal.getName());
        CommonResponse<UserIdResponse> response = sysUserService.addOrUpdateUser(user);
        return response;
    }


    @ApiOperation(value="检测用户名是否重名", notes="检测用户名是否重名")
    @RequestMapping(value ="/exist/{username}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<Object> checkIsExist(@PathVariable("username")String username){
        CommonResponse<Object> response = sysUserService.checkIsExist(username);
        return response;
    }


}

