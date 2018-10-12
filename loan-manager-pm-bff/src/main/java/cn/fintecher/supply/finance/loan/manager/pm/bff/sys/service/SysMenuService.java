package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenusResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;

import java.util.List;

/**
 * SysMenuService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysMenuService {

    /**
     * 选择菜单(添加、修改菜单)
     */
     CommonResponse<List<SysMenuEntity>> select();

    /**
     * 导航菜单
     */
    CommonResponse<List<SysMenuEntity>> navigation(String userName);

    /**
     * 根据菜单的ID，查询菜单信息
     */
    CommonResponse<MenuResponse> getMenuById(Integer menuId);

    /**
     * 查询所有菜单接口
     */
    CommonResponse<MenusResponse> getAllMenus(MenuSearchForm menuSearchForm);

    /**
     * 菜单信息添加/修改／停用
     */
    CommonResponse addOrUpdateMenu(SysMenuEntity menu);

    /**
     * 权限菜单
     */
    CommonResponse<List<SysMenuEntity>> getAuthMenus(String username);
}