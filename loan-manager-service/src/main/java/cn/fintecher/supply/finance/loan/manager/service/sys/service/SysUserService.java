package cn.fintecher.supply.finance.loan.manager.service.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.EmpsResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserPasswordChange;
import cn.fintecher.supply.finance.loan.manager.common.sys.UserSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

import java.util.List;

/**
 * SysUserService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysUserService {

    /**
     * 根据部门Id查询部门下的客户信息
     *
     * @param deptId
     * @return
     */
    List<SysUserAdminEntity> queryUserByDeptId(Integer deptId);

    /**
     * 根据角色Id查询的客户信息
     *
     * 胡进宝
     * @param deptId
     * @return
     */
    List<SysUserAdminEntity> queryUserByRoleId(Integer roleId);

    SysUserAdminEntity findUserByName(String userName);

    /**
     * 验证原密码是否正确
     * @param userId
     * @param passWord
     * @return
     */
    boolean verPassWord(SysUserPasswordChange sysUserPasswordChange);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    int updatePassword(SysUserPasswordChange sysUserPasswordChange);

    /**
     * 查询用户列表
     */
    PagedResponse<EmpsResponse> findUserPage(UserSearchForm userSearchForm);

    /**
     * 根据用户ID，查询用户
     * @param userId
     * @return
     */
    SysUserAdminEntity findUserById(Integer userId);

    /**
     * 查询所有的用户信息
     * @return
     */
    List<SysUserAdminEntity> getAllUsers();

    /**
     * 检测用户名是否唯一
     * @param username
     * @return
     */
    boolean checkIsExist(String username);

    /**
     * 保存用户
     */
    int save(SysUserAdminEntity user);

    /**
     * 修改用户
     */
    void update(SysUserAdminEntity user);

    /**
     * 根据用户名查找用户
     */
    SysUserAdminEntity queryByUserName(String username);

    /**
     * 根据用户id查找是否拥有admin角色
     * @param userId
     * @return
     */
    int findUserRoleCountByUserId(Integer userId);

}