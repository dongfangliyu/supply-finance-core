package cn.fintecher.supply.finance.loan.manager.core.sys.dao;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserPasswordChange;
import cn.fintecher.supply.finance.loan.manager.common.sys.UserSearchForm;
import cn.fintecher.supply.finance.loan.manager.core.common.BaseDao;
import cn.fintecher.supply.finance.loan.manager.core.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {

     /**
      * 根据部门Id查询部门下的客户信息
      *
      * 胡进宝
      * @param deptId
      * @return
      */
     List<SysUserAdminEntity> queryUserByDeptId(Integer deptId);

     /**
      * 根据角色Id查询部门下的客户信息
      *
      * 胡进宝
      * @param deptId
      * @return
      */
     List<SysUserAdminEntity> queryUserByRoleId(Integer roleId);

     /**
      * 根据用户名称查询用户
      */
     SysUserAdminEntity findUserByName(String userName);

     /**
      * 修改密码
      */
     int updatePassword(SysUserPasswordChange sysUserPasswordChange);

     /**
      * 根据用户id查询所有的角色信息
      * @param userId
      * @return
      */
      List<SysRoleEntity> queryRoles(Integer userId);

     List<SysUserAdminEntity> findUserPageByRoleName(@Param("vo") UserSearchForm userSearchForm);

     int findUserPageCountByRoleName(@Param("vo") UserSearchForm userSearchForm);

     /**
      * 用户列表
      */
     List<SysUserAdminEntity> findUserPage(@Param("vo") UserSearchForm userSearchForm);
     /**
      * 分页
      */
     int findUserPageCount(@Param("vo") UserSearchForm userSearchForm);

     SysUserAdminEntity getUserAdminById(Integer userId);

     Integer updateAdminUser(SysUserAdminEntity sysUserAdminEntity);

     Integer saveAdminUser(SysUserAdminEntity sysUserAdminEntity);

     /**
      * 根据用户id查找是否拥有admin角色
      * @param userId
      * @return
      */
     int findUserRoleCountByUserId(Integer userId);

     /**
      * 根据用户名查找用户
      */
     SysUserAdminEntity queryByUserName(String username);

     /**
      * 获取所有的用户信息
      * @return
      */
     List<SysUserAdminEntity> getAllUsers();

}
