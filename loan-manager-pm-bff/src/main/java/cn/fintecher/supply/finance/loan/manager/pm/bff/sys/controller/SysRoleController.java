package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags="角色接口")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色和用户绑定
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="角色和用户绑定", notes="角色和用户绑定")
    @RequestMapping(value ="/relRoleAndUser", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse relRoleAndUser(@RequestBody SysRoleUserEntity sysRoleUserEntity){
        CommonResponse response =sysRoleService.relRoleAndUser(sysRoleUserEntity);
        return response;
    }

    /**
     * 查询所有可用角色列表
     */

    @ApiOperation(value="查询所有可用角色列表", notes="查询所有可用角色列表")
    @RequestMapping(value ="/queryAllRoles", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<RolesResponse> queryAllRoles(){
        CommonResponse<RolesResponse> response = sysRoleService.queryAllRoles();
        return response;
    }

    /**
     * 根据角色ID，查询角色信息
     *
     */
    @ApiOperation(value="根据角色ID，查询角色信息", notes="根据角色ID，查询角色信息")
    @RequestMapping(value ="/getRoleById/{roleId}", method = RequestMethod.GET)
    public CommonResponse<RoleResponse> getRoleById(@PathVariable("roleId") Integer roleId){
        CommonResponse<RoleResponse> response = sysRoleService.getRoleById(roleId);
        return response;
    }

    /**
     * 角色分页列表
     */

    @ApiOperation(value="角色分页列表", notes="角色分页列表")
    @RequestMapping(value ="/getAllRoles", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponse<RolesResponse> getAllRoles(@RequestBody RoleSearchForm roleSearchForm){
        PagedResponse<RolesResponse> pagedResponse = sysRoleService.getAllRoles(roleSearchForm);
        return pagedResponse;
    }

   /**
     * 角色信息添加/修改／停用
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="角色信息添加/修改／停用", notes="角色信息添加/修改／停用")
    @RequestMapping(value ="/addOrUpdateRole", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addOrUpdateRole(@RequestBody SysRoleEntity role, Principal principal){
        role.setOperateByName(principal.getName());
        CommonResponse response  = sysRoleService.addOrUpdateRole(role);
        return response;
    }
}

