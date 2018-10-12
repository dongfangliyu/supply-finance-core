package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

/**
 * SysRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysRoleService {
    CommonResponse relRoleAndUser(SysRoleUserEntity sysRoleUserEntity);
    CommonResponse<RolesResponse> queryAllRoles();
    CommonResponse<RoleResponse> getRoleById(Integer roleId);
    PagedResponse<RolesResponse> getAllRoles(RoleSearchForm roleSearchForm);
    CommonResponse addOrUpdateRole(SysRoleEntity role);
}