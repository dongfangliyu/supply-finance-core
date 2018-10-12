package cn.fintecher.supply.finance.loan.manager.core.sys.dao;


import cn.fintecher.supply.finance.loan.manager.common.sys.RoleSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
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
public interface SysRoleDao {

    List<SysRoleEntity> getByRoleCode(String roleCode);

    /**
     * 查询所有可用角色列表
     */
    List<SysRoleEntity> queryAllRoles();

    SysRoleEntity queryObject(Integer roleId);

    /**
     * 分页查询
     */
    List<SysRoleEntity> findRolePage(@Param("vo") RoleSearchForm roleSearchForm);
    /**
     * 分页查询总数
     */
    int findRolePageCount(@Param("vo") RoleSearchForm roleSearchForm);

    int save(SysRoleEntity t);

    int update(SysRoleEntity t);
}
