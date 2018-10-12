package cn.fintecher.supply.finance.loan.manager.service.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;

import java.util.List;

/**
 * SysRoleMenuService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysRoleMenuService {
    void saveOrUpdate(SysRoleEntity role);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Integer> queryMenuIdList(Integer roleId);
}