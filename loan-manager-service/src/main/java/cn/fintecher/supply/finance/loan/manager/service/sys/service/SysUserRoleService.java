package cn.fintecher.supply.finance.loan.manager.service.sys.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleUserEntity;

import java.util.List;

/**
 * SysUserRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysUserRoleService {

	Message getUsersByRoleId(Long roleId);
	
    List<String> findRolesByUsername(String userName);
    List<String> findFunctionByUsername(String userName);

    /**
     * 角色和用户绑定
     */
    void saveOrUpdate(SysRoleUserEntity sysRoleUserEntity);

	Message getRolesByUserId(Long userId);
}