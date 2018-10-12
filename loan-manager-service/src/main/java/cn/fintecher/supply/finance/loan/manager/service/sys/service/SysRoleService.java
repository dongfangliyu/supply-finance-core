package cn.fintecher.supply.finance.loan.manager.service.sys.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.RoleSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.RolesResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

import java.util.List;

/**
 * SysRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysRoleService {

    /**
     * 查询所有可用角色列表
     */
    List<SysRoleEntity> queryAllRoles();
    /**
     * 根据角色ID，查询角色信息
     */
    SysRoleEntity queryObject(Integer roleId);

    /**
     * 角色分页列表
     */
    PagedResponse<RolesResponse> findRolePage(RoleSearchForm roleSearchForm);

    /**
     * 保存角色
     */
    Message save(SysRoleEntity role);
    /**
     * 修改角色
     */
    Message update(SysRoleEntity role);

    Boolean queryUserByRoleId(Integer roleId);
}