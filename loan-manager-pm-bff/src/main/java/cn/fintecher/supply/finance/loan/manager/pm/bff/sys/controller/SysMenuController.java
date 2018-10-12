package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.controller;

import cn.fintecher.supply.finance.loan.manager.common.sys.MenuResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenusResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysMenu")
@Api(tags="菜单接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value="选择菜单(添加、修改菜单) ", notes="选择菜单(添加、修改菜单) ")
    @RequestMapping(value ="/select", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> select(){
        CommonResponse<List<SysMenuEntity>> response = sysMenuService.select();
        return response;
    }


    /**
     * 导航菜单
     */
    @ApiOperation(value="导航菜单 ", notes="导航菜单 ")
    @RequestMapping(value ="/navigation", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> navigation(Principal principal){

        CommonResponse<List<SysMenuEntity>> response = sysMenuService.navigation(principal.getName());

        return response;
    }
    
    
    /**
     * 导航菜单
     */
    @ApiOperation(value="导航菜单 ", notes="导航菜单树 ")
    @RequestMapping(value ="/navigationTree", method = RequestMethod.GET)
    public CommonResponse<List<SysMenuEntity>> navigationTree(Principal principal){
        CommonResponse<List<SysMenuEntity>> response = sysMenuService.navigation(principal.getName());
        List<SysMenuEntity> menuList = response.getData();
        String menuStr = JSON.toJSONString(menuList);
        if(!menuList.isEmpty()) {
    	   List<SysMenuEntity> ts = (List<SysMenuEntity>) JSONArray.parseArray(menuStr, SysMenuEntity.class);
    	   CommonResponse<List<SysMenuEntity>>  tree = new CommonResponse<List<SysMenuEntity>>();
           tree.setCode("1");
           tree.setMsg("成功");
           tree.setSuccess(true);
           tree.setData(buildByRecursive(ts));
          return tree;
        }
        return response;
    }
    
   
    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<SysMenuEntity> buildByRecursive(List<SysMenuEntity> treeNodes) {
        List<SysMenuEntity> trees = new ArrayList<SysMenuEntity>();
        
        for (SysMenuEntity sysMenuEntity : treeNodes) {
        	if (0 == sysMenuEntity.getParantId()) {
                trees.add(findChildren(sysMenuEntity,treeNodes));
            }
		}
        
        return trees;
    }
 
    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenuEntity findChildren(SysMenuEntity treeNode,List<SysMenuEntity> treeNodes) {
        for (SysMenuEntity it : treeNodes) {
            if(treeNode.getMenuId().equals(it.getParantId())) {
                if (treeNode.getChildrenList() == null) {
                    treeNode.setChildrenList(new ArrayList<SysMenuEntity>());
                }
                treeNode.getChildrenList().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
 
    
    /**
     * 根据菜单的ID，查询菜单信息
     */
    @ApiOperation(value="根据菜单的ID，查询菜单信息", notes="根据菜单的ID，查询菜单信息")
    @RequestMapping(value ="/getMenuById/{menuId}", method = RequestMethod.GET)
    public CommonResponse<MenuResponse> getMenuById(@PathVariable("menuId")Integer menuId){
        CommonResponse<MenuResponse> response = sysMenuService.getMenuById(menuId);
        return response;
    }


    /**
     * 查询所有菜单接口
     */
    @ApiOperation(value="查询所有菜单接口", notes="查询所有菜单接口")
    @RequestMapping(value ="/getAllMenus", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<MenusResponse> getAllMenus(@RequestBody MenuSearchForm menuSearchForm){
        CommonResponse<MenusResponse> response = sysMenuService.getAllMenus(menuSearchForm);
        return response;
    }

    /**
     * 菜单信息添加/修改／停用
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="菜单信息添加/修改／停用", notes="菜单信息添加/修改／停用")
    @RequestMapping(value ="/addOrUpdateMenu", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addOrUpdateMenu(@RequestBody SysMenuEntity menu,Principal principal){
        menu.setOperateByName(principal.getName());
        CommonResponse response =sysMenuService.addOrUpdateMenu(menu);
        return  response;
    }
}

