package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserPasswordChange;
import cn.fintecher.supply.finance.loan.manager.common.sys.UserSearchForm;
import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysUserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysUserService 接口
 *
 * @author Walter
 * @version 1.0.0
 * @date 2016-5-13 10:36:28
 * @since 1.0.0
 */
@Service
public class SysUserService {

    public List<SysUserAdminEntity> queryUserByDeptId(Integer deptId){
        return  sysUserDao.queryUserByDeptId(deptId);
    }

    @Autowired
    public SysUserDao sysUserDao;

    public List<SysUserAdminEntity> queryUserByRoleId(Integer roleId){
        return sysUserDao.queryUserByRoleId(roleId);
    }

    public SysUserAdminEntity findUserByName(String userName) {
        return sysUserDao.findUserByName(userName);
    }

    public int updatePassword(SysUserPasswordChange sysUserPasswordChange) {
        return sysUserDao.updatePassword(sysUserPasswordChange);
    }

    /**
     * 根据用户id查询所有的角色信息
     * @param userId
     * @return
     */
    public List<SysRoleEntity> queryRoles(Integer userId){
        return  sysUserDao.queryRoles(userId);
    }

    public List<SysUserAdminEntity> findUserPageByRoleName(UserSearchForm userSearchForm){
        return sysUserDao.findUserPageByRoleName(userSearchForm);
    }

    public int findUserPageCountByRoleName(UserSearchForm userSearchForm){
        return  sysUserDao.findUserPageCountByRoleName(userSearchForm);
    }


    /**
     * 用户列表
     */
    public int findUserPageCount(UserSearchForm userSearchForm){
        return  sysUserDao.findUserPageCount(userSearchForm);
    }

    /**
     * 用户列表
     */
    public List<SysUserAdminEntity> findUserPage(UserSearchForm userSearchForm){
       return sysUserDao.findUserPage(userSearchForm);
    }


    public SysUserAdminEntity findUserById(Integer userId) {
        return sysUserDao.getUserAdminById(userId);
    }

    /**
     * 查询所有的用户信息
     * @return
     */
    public List<SysUserAdminEntity> getAllUsers(){
        return sysUserDao.getAllUsers();
    }

    public boolean checkIsExist(String username){
        SysUserAdminEntity user = sysUserDao.queryByUserName(username);
        if (user == null){
            return true;
        }else{
            return false;
        }
    }

    public SysUserAdminEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    public int findUserRoleCountByUserId(Integer userId) {
        return sysUserDao.findUserRoleCountByUserId(userId);
    }

    public void updateAdminUser(SysUserAdminEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        }
        sysUserDao.updateAdminUser(user);
    }

    public Integer saveAdminUser(SysUserAdminEntity user) {

        sysUserDao.saveAdminUser(user);
        return user.getUserId();
    }
}