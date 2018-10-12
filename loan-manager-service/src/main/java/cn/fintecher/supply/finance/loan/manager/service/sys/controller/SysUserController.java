package cn.fintecher.supply.finance.loan.manager.service.sys.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import cn.fintecher.supply.finance.loan.manager.common.util.group.UpdateGroup;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseSysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    public SysUserService sysUserService;

    /**
     * 验证登录用户密码
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value ="/verPassWord", method = RequestMethod.GET)
    public CommonResponse verPassWord(@RequestParam("passWord") String passWord, @RequestParam("userName") String userName){
        CommonResponse response = new CommonResponse();

        if (ChkUtil.isEmpty(passWord)){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg("参数不能为空！");
            return response;
        }

        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(userName);
            SysUserPasswordChange sysUserPasswordChange = new SysUserPasswordChange();
            sysUserPasswordChange.setUserId(sysUserAdminEntity.getUserId());
            sysUserPasswordChange.setPassword(passWord);

            //验证密码
            Boolean ver = sysUserService.verPassWord(sysUserPasswordChange);
            if(!ver){
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("原密码不正确");
                return response;
            }
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (RuntimeException e) {
            LOGGER.error("修改登录用户密码失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 根据用户名获取用户信息
     */
    @ResponseBody
    @RequestMapping(value ="/findUserByName/{userName}", method = RequestMethod.GET)
    public Message findUserByName(@PathVariable("userName")String userName){
        try {
            if (ChkUtil.isEmpty(userName)){
                return new Message(MessageType.MSG_ERROR,"sys_service","用户名不能为空");
            }
            SysUserAdminEntity user = sysUserService.findUserByName(userName);
            if(user == null){
                return new Message(MessageType.MSG_ERROR,"sys_service","用户不存在");
            }else{
                return new Message(MessageType.MSG_SUCCESS,"sys_service",user);
            }
        } catch (RuntimeException e) {
            return new Message(MessageType.MSG_ERROR,"sys_service",e.getMessage());
        }
    }


    /**
     * 修改登录用户密码
     */
    @ResponseBody
    @RequestMapping(value ="updatePassWord", method = RequestMethod.POST)
    public CommonResponse password(@RequestBody SysUserPasswordChange sysUserPasswordChange){

        CommonResponse response = new CommonResponse();

        if (ChkUtil.isEmpty(sysUserPasswordChange.getPassword()) || ChkUtil.isEmpty(sysUserPasswordChange.getNewPassword())){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg("密码不能为空！");
            return response;
        }

        if(ChkUtil.isEmpty(sysUserPasswordChange.getUserId()) ){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg("用户id不能为空！");
            return response;
        }

        if(ChkUtil.isEmpty(sysUserPasswordChange.getUsername()) ){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg("用户名不能为空！");
            return response;
        }

        try {

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            SysUserAdminEntity sysUserAdminEntity  = sysUserService.findUserById(sysUserPasswordChange.getUserId());
            if(sysUserAdminEntity!=null){
                if(encoder.matches(sysUserPasswordChange.getPassword(),sysUserAdminEntity.getPassword())){
                    //更新密码
                    int count = sysUserService.updatePassword(sysUserPasswordChange);
                    if(count == 0){
                        response.setSuccess(false);
                        response.setCode(Constants.STATUS_NO_SUCCESS);
                        response.setMsg("原密码不正确");
                        return response;
                    }
                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                }else{
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg("原密码不正确");
                    return response;
                }
            }else {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("修改登录用户密码失败！");
                return response;
            }


        } catch (RuntimeException e) {
            LOGGER.error("修改登录用户密码失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }


    /**
     * 用户分页列表
     */
    @ResponseBody
    @RequestMapping(value ="/getUserInfo", method = RequestMethod.POST)
    public PagedResponse<EmpsResponse> getUserInfo(@RequestBody UserSearchForm userSearchForm){
        PagedResponse<EmpsResponse> pagedResponse = new PagedResponse<EmpsResponse>();

        try {
            if (("").equals(userSearchForm.getStatus())) {
                userSearchForm.setStatus(null);
            }

            pagedResponse = sysUserService.findUserPage(userSearchForm);
            pagedResponse.setSuccess(true);
            pagedResponse.setCode(Constants.STATUS_SUCCESS);
            pagedResponse.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
        } catch (RuntimeException e) {
            LOGGER.error("获取用户分页列表失败：{}", e);
            pagedResponse.setCode(Constants.SYSTEM_ERROR);
            pagedResponse.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return pagedResponse;
    }

    @ResponseBody
    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public CommonResponse<EmpResponse> getUserById(@RequestParam("userId") Integer userId){

        CommonResponse<EmpResponse> response = new CommonResponse<>();

        if (ChkUtil.isEmpty(userId)){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg("用户id不能为空！");
            return response;
        }

        try {
            SysUserAdminEntity user = sysUserService.findUserById(userId);
            if(user == null){
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("用户不存在！");
                return response;
            }
            EmpResponse empResponse = new EmpResponse();
            empResponse.setEmp(user);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(empResponse);
        } catch (RuntimeException e) {
            LOGGER.error("根据用户id查找用户失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }


    @ResponseBody
    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public CommonResponse<EmpsResponse> getAllUsers(){
        CommonResponse<EmpsResponse> response = new CommonResponse<>();

        try{
            List<SysUserAdminEntity> userList=sysUserService.getAllUsers();
            EmpsResponse empsResponse = new EmpsResponse();
            empsResponse.setEmps(userList);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(empsResponse);
        } catch (RuntimeException e) {
            LOGGER.error("获取所有用户信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "checkIsExist", method = RequestMethod.GET)
    public CommonResponse<Object> checkIsExist(@RequestParam("username") String username){
        CommonResponse<Object> response = new CommonResponse<>();
        try {
            if(!ChkUtil.isEmpty(username)){
                boolean isExist = sysUserService.checkIsExist(username);
                response.setSuccess(true);
                response.setData(isExist);
                response.setCode(Constants.STATUS_SUCCESS);
                response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            }else{
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("用户名为空");
            }
        } catch (RuntimeException e) {
            LOGGER.error("检测用户名是否重名失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 用户信息添加/修改／停用
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<UserIdResponse> addOrUpdateUser(@RequestBody SysUserAdminEntity user) {
        CommonResponse<UserIdResponse> response = new CommonResponse<>();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(user.getOperateByName());
            if (ChkUtil.isEmpty(user.getUserId())) {   //新增
                ValidatorUtils.validateEntity(user, AddGroup.class);

                SysUserAdminEntity oldUser  = sysUserService.findUserByName(user.getUsername().trim());
                if(oldUser!=null && oldUser.getUserId()>0){
                    LOGGER.error("新增用户信息失败：{用户名重复}");
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg("用户名重复！");
                    return response;
                }

                if (!(ChkUtil.isEmpty(user.getMobile()))) {
                    if (!(isMobile(user.getMobile()))) {
                        LOGGER.error("新增用户信息失败：{手机号码格式不正确}");
                        response.setSuccess(false);
                        response.setCode(Constants.STATUS_NO_SUCCESS);
                        response.setMsg("手机号码格式不正确");
                        return response;
                    }
                }
                try {
                    user.setStatus("1");
                    user.setType("0");
                    user.setUserId(null);
                    user.setCreateTime(DateUtil.getCurrentTime());
                    user.setCreateUserId(sysUserAdminEntity.getUserId());
                    user.setUpdateTime(null);
                    user.setUpdateUserId(null);
                    UserIdResponse idResponse = new UserIdResponse();
                    idResponse.setUserId(sysUserService.save(user));
                    response.setData(idResponse);
                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                } catch (RuntimeException e) {
                    LOGGER.error("新增用户信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                    //response.setMsg("新增设备类型失败");
                }
            } else {   //修改
                try {
                    if (("2").equals(user.getStatus())||("0").equals(user.getStatus())) {
                        if (null != user.getUserId() && 0 != user.getUserId()) {
                            if (Constants.SYSTEM_ADMIN == user.getUserId()) {
                                response.setSuccess(false);
                                response.setCode(Constants.STATUS_NO_SUCCESS);
                                response.setMsg("超级管理员不能停用");
                                return response;
                            }

                            if (sysUserAdminEntity.getUserId() == user.getUserId()) {
                                response.setSuccess(false);
                                response.setCode(Constants.STATUS_NO_SUCCESS);
                                response.setMsg("当前用户不能停用");
                                return response;
                            }
                        }
                    } else {
                        ValidatorUtils.validateEntity(user, UpdateGroup.class);
                    }
                    if (!(ChkUtil.isEmpty(user.getMobile()))) {
                        if (!(isMobile(user.getMobile()))) {
                            LOGGER.error("修改用户信息失败：{手机号码格式不正确}");
                            response.setSuccess(false);
                            response.setCode(Constants.STATUS_NO_SUCCESS);
                            response.setMsg("手机号码格式不正确");
                            return response;
                        }
                    }

                    user.setUpdateTime(DateUtil.getCurrentTime());
                    user.setUpdateUserId(sysUserAdminEntity.getUserId());
                    user.setCreateTime(null);
                    user.setCreateUserId(null);
                    sysUserService.update(user);
                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

                } catch (RuntimeException e) {
                    LOGGER.error("修改用户信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                }
            }
        }catch (Exception e){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg(e.getMessage());
        }
        return response;

    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
