package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysMenuService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    public List<SysMenuEntity> queryNotButtonList(){
        return sysMenuDao.queryNotButtonList();
    }

    public List<SysMenuEntity> getAllMenuList(Integer userId){
        return sysMenuDao.getAllMenuList(userId);
    }

    public List<SysMenuEntity> getAllMenuListBySuper(){
        return  sysMenuDao.getAllMenuListBySuper();
    }

    public SysMenuEntity queryInfo(Integer menuId){
        return sysMenuDao.queryObject(menuId);
    }

    public List<SysMenuEntity> getAllMenus(MenuSearchForm menuSearchForm){
        return sysMenuDao.getAllMenus(menuSearchForm);
    }

    public List<Integer> queryMenuIdList(Integer menuId){
        return sysMenuDao.queryMenuIdList(menuId);
    }

    public void delete(Map<String, Object> map){
        sysMenuDao.delete(map);
    }

    public void save(SysMenuEntity menu) {
        sysMenuDao.save(menu);
    }

    public void update(SysMenuEntity menu) {
        sysMenuDao.update(menu);
    }

    /**
     * 分页
     */
    public List<SysMenuEntity> findMenuPage(MenuSearchForm menuSearchForm){
        return sysMenuDao.findMenuPage(menuSearchForm);
    }
    /**
     * 分页总数
     */
    public int findMenuPageCount(MenuSearchForm menuSearchForm){
        return sysMenuDao.findMenuPageCount(menuSearchForm);
    }

    public List<SysMenuEntity> getAuthMenus(Integer userId) {
        return sysMenuDao.getAuthMenus(userId);
    }
}