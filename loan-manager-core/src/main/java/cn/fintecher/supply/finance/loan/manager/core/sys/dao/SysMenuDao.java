package cn.fintecher.supply.finance.loan.manager.core.sys.dao;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Mapper
public interface SysMenuDao {

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询导航菜单，非树形菜单
     * @param userId
     * @return
     */
    List<SysMenuEntity> getAllMenuList(Integer userId);

    /**
     * 查询超级管理员导航菜单，非树形菜单
     * @param userId
     * @return
     */
    List<SysMenuEntity> getAllMenuListBySuper();

    SysMenuEntity queryObject(Integer menuId);

    /**
     * 查询所有的菜单接口
     * @return
     */
    List<SysMenuEntity> getAllMenus(@Param("vo") MenuSearchForm menuSearchForm);

    int delete(Map<String, Object> map);

    /**
     * 查询子菜单Id
     */
    List<Integer> queryMenuIdList(Integer menuId);

    int save(SysMenuEntity sysMenuEntity);

    int update(SysMenuEntity sysMenuEntity);

    /**
     * 分页
     */
    List<SysMenuEntity> findMenuPage(@Param("vo") MenuSearchForm menuSearchForm);

    /**
     * 分页总数
     */
    int findMenuPageCount(@Param("vo") MenuSearchForm menuSearchForm);

    List<SysMenuEntity> getAuthMenus(Integer userId);
}
