package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.RoleSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有可用角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/queryAllRoles", method = RequestMethod.GET)
    public List<SysRoleEntity> queryAllRoles(){
        return sysRoleService.queryAllRoles();
    }

    /**
     * 根据角色ID，查询角色信息
     */
    @ResponseBody
    @RequestMapping(value = "/queryObject", method = RequestMethod.GET)
    public SysRoleEntity queryObject(@RequestParam("roleId") Integer roleId) {
        return sysRoleService.queryObject(roleId);
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/findRolePage", method = RequestMethod.POST)
    public List<SysRoleEntity> findRolePage(@RequestBody RoleSearchForm roleSearchForm) {
        return sysRoleService.findRolePage(roleSearchForm);
    }

    /**
     * 分页查询总数
     */
    @ResponseBody
    @RequestMapping(value = "/findRolePageCount", method = RequestMethod.POST)
    public int findRolePageCount(@RequestBody RoleSearchForm roleSearchForm) {
        return sysRoleService.findRolePageCount(roleSearchForm);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Message save(@RequestBody SysRoleEntity role) {
       return sysRoleService.save(role);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Message update(@RequestBody SysRoleEntity role) {
        return sysRoleService.update(role);
    }

}

