package cn.fintecher.supply.finance.loan.manager.service.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import cn.fintecher.supply.finance.loan.manager.common.util.group.UpdateGroup;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
public class SysMenuController extends BaseSysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuController.class);
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping(value ="/select", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> select(){

        CommonResponse<List<SysMenuEntity>> response = new CommonResponse<>();

        try {
            List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
            //添加顶级菜单
            SysMenuEntity root = new SysMenuEntity();
            root.setMenuId(0);
            root.setName("一级菜单");
            root.setParantId(-1);
            menuList.add(root);

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(menuList);
        } catch (RuntimeException e) {
            LOGGER.error("查询“选择菜单(添加、修改菜单)”失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 导航菜单
     */
    @ResponseBody
    @RequestMapping(value ="/navigation", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> navigation(@RequestParam("userName") String userName){

        CommonResponse<List<SysMenuEntity>> response = new CommonResponse<>();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(userName);
            List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(sysUserAdminEntity.getUserId()); //此处应该换成getUser()

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(menuList);
        } catch (RuntimeException e) {
            LOGGER.error("查询导航菜单 失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 权限菜单
     */
    @ResponseBody
    @RequestMapping(value ="/getAuthMenus", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> getAuthMenus(@RequestParam("userName") String userName){

        CommonResponse<List<SysMenuEntity>> response = new CommonResponse<>();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(userName);
            List<SysMenuEntity> menuList = sysMenuService.getAuthMenus(sysUserAdminEntity.getUserId());

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(menuList);
        } catch (RuntimeException e) {
            LOGGER.error("查询导航菜单 失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 根据菜单的ID，查询菜单信息
     */
    @ResponseBody
    @RequestMapping(value ="/getMenuById/{menuId}", method = RequestMethod.GET)
    public CommonResponse<MenuResponse> getMenuById(@PathVariable("menuId")Integer menuId){

        CommonResponse<MenuResponse> response = new CommonResponse<>();

        try {
            if (ChkUtil.isEmpty(menuId)){
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("菜单id不能为空！");
                return response;
            }
            SysMenuEntity menu = sysMenuService.queryInfo(menuId);
            if (menu==null){
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("菜单不存在！");
                return response;
            }
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setMenu(menu);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(menuResponse);
        } catch (RuntimeException e) {
            LOGGER.error("根据菜单的ID，查询菜单信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 查询所有菜单接口
     */
    @ResponseBody
    @RequestMapping(value ="/getAllMenus", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<MenusResponse> getAllMenus(@RequestBody MenuSearchForm menuSearchForm){
        CommonResponse<MenusResponse> response = new CommonResponse<>();
        try {
            List<SysMenuEntity> menuList= sysMenuService.getAllMenus(menuSearchForm);
            MenusResponse menusResponse = new MenusResponse();
            menusResponse.setMenus(menuList);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(menusResponse);
        } catch (RuntimeException e) {
            LOGGER.error("查询所有菜单接口（提供树形菜单）失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 菜单信息添加/修改／停用
     */
    @ResponseBody
    @RequestMapping(value ="/addOrUpdateMenu", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addOrUpdateMenu(@RequestBody SysMenuEntity menu){
        CommonResponse response = new CommonResponse();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(menu.getOperateByName());
            if (ChkUtil.isEmpty(menu.getMenuId())) {   //新增
                ValidatorUtils.validateEntity(menu, AddGroup.class);
                try {
                    menu.setStatus("1");
                    menu.setCreateTime(DateUtil.getCurrentTime());
                    menu.setCreateUserId(sysUserAdminEntity.getUserId());
                    menu.setUpdateTime(null);
                    menu.setUpdateUserId(null);
                    sysMenuService.save(menu);

                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                } catch (RuntimeException e) {
                    LOGGER.error("添加菜单失败：{}", e);
                    response.setCode(Constants.SYSTEM_ERROR);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                }
            }else{   //修改
                ValidatorUtils.validateEntity(menu, UpdateGroup.class);
                try {
                    if (null != menu.getStatus()&& "0".equals(menu.getStatus())) {
                        try {
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 0);
                            map.put("menuId", menu.getMenuId());
                            map.put("updateTime", DateUtil.getCurrentTime());
                            map.put("updateUserId", sysUserAdminEntity.getUserId());

                            sysMenuService.disable(map);

                            response.setSuccess(true);
                            response.setCode(Constants.STATUS_SUCCESS);
                            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                        } catch (RuntimeException e) {
                            LOGGER.error("停用菜单失败：{}", e);
                            response.setCode(Constants.SYSTEM_ERROR);
                            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                        }


                    }else{menu.setStatus("1");}

                    menu.setUpdateTime(DateUtil.getCurrentTime());
                    menu.setUpdateUserId(sysUserAdminEntity.getUserId());
                    menu.setCreateTime(null);
                    menu.setCreateUserId(null);
                    sysMenuService.update(menu);
                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

                } catch (RuntimeException e) {
                    LOGGER.error("修改菜单信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                }
            }

        }catch (Exception e){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg(e.getMessage());
        }
        return response;
    }

}

