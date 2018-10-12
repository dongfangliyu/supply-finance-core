package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysRoleMenuService;
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
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据角色ID，获取菜单ID列表
     */
    @ResponseBody
    @RequestMapping(value ="/queryMenuIdList", method = RequestMethod.GET)
    public List<Integer> queryMenuIdList(@RequestParam("roleId") Integer roleId){
        return sysRoleMenuService.queryMenuIdList(roleId);
    }

    @ResponseBody
    @RequestMapping(value ="/delete", method = RequestMethod.GET)
    public int delete(@RequestParam("id") Integer id){
        return sysRoleMenuService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public void save(@RequestBody Map<String, Object> map){
        sysRoleMenuService.save(map);
    }
}

