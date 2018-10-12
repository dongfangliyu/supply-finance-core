package cn.fintecher.supply.finance.loan.manager.service.sys.service;

import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;

import java.util.List;
import java.util.Map;

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
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 导航菜单
     * @param userId 登陆id
     * @return 用户拥有的导航菜单
     */
    List<SysMenuEntity> getUserMenuList(Integer userId);

    /**
     *根据菜单的ID，查询菜单信息
     */
    SysMenuEntity queryInfo(Integer menuId);

    /**
     * 查询所有菜单接口
     */
    List<SysMenuEntity> getAllMenus(MenuSearchForm menuSearchForm);

    /**
     * 停用菜单及其子菜单
     */
    void disable(Map<String, Object> map);

    /**
     * 保存菜单
     */
    void save(SysMenuEntity menu);

    /**
     * 修改
     */
    void update(SysMenuEntity menu);


    /**
     * 权限菜单
     * @param userId 登陆id
     * @return 用户拥有的导航菜单
     */
    List<SysMenuEntity> getAuthMenus(Integer userId);
}