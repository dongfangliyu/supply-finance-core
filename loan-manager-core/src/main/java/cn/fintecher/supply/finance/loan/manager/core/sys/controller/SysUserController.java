package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserPasswordChange;
import cn.fintecher.supply.finance.loan.manager.common.sys.UserSearchForm;
import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    public SysUserService sysUserService;

    /**
     * 根据部门Id查询部门下的客户信息
     *
     * 胡进宝
     * @param deptId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/queryUserByDeptId", method = RequestMethod.GET)
    public List<SysUserAdminEntity> queryUserByDeptId(@RequestParam("deptId") Integer deptId){
        return  sysUserService.queryUserByDeptId(deptId);
    }

    /**
     * 根据角色Id查询部门下的客户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/queryUserByRoleId", method = RequestMethod.GET)
    public List<SysUserAdminEntity> queryUserByRoleId(@RequestParam("roleId")Integer roleId){
        return sysUserService.queryUserByRoleId(roleId);
    }

    /**
     * 根据用户名获取用户信息
     */
    @ResponseBody
    @RequestMapping(value ="/userInfoByName/{userName}", method = RequestMethod.GET)
    public SysUserAdminEntity  findUserByName(@PathVariable("userName")String userName){
        return sysUserService.findUserByName(userName);
    }


    /**
     * 修改登录用户密码
     */
    @ResponseBody
    @RequestMapping(value ="updatePassWord", method = RequestMethod.POST)
    public int password(@RequestBody SysUserPasswordChange sysUserPasswordChange){
       return sysUserService.updatePassword(sysUserPasswordChange);
    }

    /**
     * 根据用户id查询所有的角色信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryRoles", method = RequestMethod.GET)
    public List<SysRoleEntity> queryRoles(@RequestParam("userId") Integer userId){
        return sysUserService.queryRoles(userId);
    }

    @ResponseBody
    @RequestMapping(value = "findUserPageByRoleName", method = RequestMethod.POST)
    public List<SysUserAdminEntity> findUserPageByRoleName(@RequestBody UserSearchForm userSearchForm){
        return sysUserService.findUserPageByRoleName(userSearchForm);
    }

    @ResponseBody
    @RequestMapping(value = "findUserPageCountByRoleName", method = RequestMethod.POST)
    public int findUserPageCountByRoleName(@RequestBody UserSearchForm userSearchForm){
        return  sysUserService.findUserPageCountByRoleName(userSearchForm);
    }

    /**
     * 分页
     */
    @ResponseBody
    @RequestMapping(value = "findUserPageCount", method = RequestMethod.POST)
    public int findUserPageCount(@RequestBody UserSearchForm userSearchForm){
        return sysUserService.findUserPageCount(userSearchForm);
    }

    /**
     * 用户列表
     */
    @ResponseBody
    @RequestMapping(value = "findUserPage", method = RequestMethod.POST)
    public List<SysUserAdminEntity> findUserPage(@RequestBody UserSearchForm userSearchForm){
        return sysUserService.findUserPage(userSearchForm);
    }

    /**
     * 根据用户ID，查询用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findUserById", method = RequestMethod.GET)
    public SysUserAdminEntity findUserById(@RequestParam("userId") Integer userId){
       return sysUserService.findUserById(userId);
    }

    /**
     * 查询所有的用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public List<SysUserAdminEntity> getAllUsers(){
        return  sysUserService.getAllUsers();
    }

    /**
     * 检测用户名是否唯一
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkIsExist", method = RequestMethod.GET)
    public boolean checkIsExist(@RequestParam("username") String username){
        return sysUserService.checkIsExist(username);
    }

    /**
     * 根据用户名查找用户
     */
    @ResponseBody
    @RequestMapping(value = "queryByUserName",method =RequestMethod.GET)
    public SysUserAdminEntity queryByUserName(@RequestParam("username")String username){
        return sysUserService.queryByUserName(username);
    }

    /**
     * 根据用户id查找是否拥有admin角色
     */
    @ResponseBody
    @RequestMapping(value = "findUserRoleCountByUserId",method =RequestMethod.GET)
    public int findUserRoleCountByUserId(@RequestParam("userId")Integer userId){
        return sysUserService.findUserRoleCountByUserId(userId);
    }

    /**
     *更新系统用户
     */
    @ResponseBody
    @RequestMapping(value = "updateAdminUser",method =RequestMethod.POST)
    public void updateAdminUser(@RequestBody SysUserAdminEntity user){
        sysUserService.updateAdminUser(user);
    }

    /**
     * 保存系统用户
     * */
    @ResponseBody
    @RequestMapping(value = "saveAdminUser",method =RequestMethod.POST)
    public Integer saveAdminUser(@RequestBody SysUserAdminEntity user){
        return sysUserService.saveAdminUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "test",method =RequestMethod.GET)
    public String test(String id){
        return "param=======>>"+id;
    }


}

