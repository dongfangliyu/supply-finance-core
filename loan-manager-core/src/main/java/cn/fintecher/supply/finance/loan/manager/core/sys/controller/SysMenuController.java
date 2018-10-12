package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取不包含按钮的菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "queryNotButtonList",method = RequestMethod.GET)
    public List<SysMenuEntity> queryNotButtonList(){
        return sysMenuService.queryNotButtonList();
    }

    /**
     * 查询导航菜单，非树形菜单
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllMenuList",method = RequestMethod.GET)
    public List<SysMenuEntity> getAllMenuList(@RequestParam("userId") Integer userId){
        return sysMenuService.getAllMenuList(userId);
    }
    /**
     * 查询权限菜单，非树形菜单
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAuthMenus",method = RequestMethod.GET)
    public List<SysMenuEntity> getAuthMenus(@RequestParam("userId") Integer userId){
        return sysMenuService.getAuthMenus(userId);
    }

    /**
     * 查询超级管理员导航菜单，非树形菜单
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllMenuListBySuper",method = RequestMethod.GET)
    public List<SysMenuEntity> getAllMenuListBySuper(){
        return sysMenuService.getAllMenuListBySuper();
    }

    /**
     *根据菜单的ID，查询菜单信息
     */
    @ResponseBody
    @RequestMapping(value = "queryInfo",method = RequestMethod.GET)
    public SysMenuEntity queryInfo(@RequestParam("menuId") Integer menuId){
        return sysMenuService.queryInfo(menuId);
    }

    /**
     * 查询所有菜单接口
     */
    @ResponseBody
    @RequestMapping(value = "getAllMenus",method = RequestMethod.POST)
    public List<SysMenuEntity> getAllMenus(@RequestBody MenuSearchForm menuSearchForm){
        return sysMenuService.getAllMenus(menuSearchForm);
    }

    /**
     * 删除菜单
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void delete(@RequestBody Map<String, Object> map){
        sysMenuService.delete(map);
    }

    /**
     * 查询子菜单Id
     */
    @ResponseBody
    @RequestMapping(value = "queryMenuIdList",method = RequestMethod.GET)
    public List<Integer> queryMenuIdList(@RequestParam("menuId") Integer menuId){
        return sysMenuService.queryMenuIdList(menuId);
    }

    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(@RequestBody SysMenuEntity menu) {
        sysMenuService.save(menu);
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(@RequestBody SysMenuEntity menu) {
        sysMenuService.update(menu);
    }


    /**
     * 分页
     */
    @ResponseBody
    @RequestMapping(value = "findMenuPage",method = RequestMethod.POST)
    public List<SysMenuEntity> findMenuPage(@RequestBody MenuSearchForm menuSearchForm){
        return sysMenuService.findMenuPage(menuSearchForm);
    }
    /**
     * 分页总数
     */
    @ResponseBody
    @RequestMapping(value = "findMenuPageCount",method = RequestMethod.POST)
    public int findMenuPageCount(@RequestBody MenuSearchForm menuSearchForm){
        return sysMenuService.findMenuPageCount(menuSearchForm);
    }


}

