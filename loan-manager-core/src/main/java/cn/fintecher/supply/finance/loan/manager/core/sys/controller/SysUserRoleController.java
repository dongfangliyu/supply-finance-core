package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysUserRoleService;
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
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @ResponseBody
    @RequestMapping(value="getRolesByUserId",method = RequestMethod.GET)
    public Message getRolesByUserId(@RequestParam("userId")Long userId){
        return sysUserRoleService.getRolesByUserId(userId);
    }
    
    @ResponseBody
    @RequestMapping(value="getUsersByRoleId",method = RequestMethod.GET)
    public Message getUsersByRoleId(@RequestParam("roleId")Long roleId){
        return sysUserRoleService.getUsersByRoleId(roleId);
    }
    

    @ResponseBody
    @RequestMapping(value ="/findRolesByUsername", method = RequestMethod.GET)
    public List<String> findRolesByUsername(@RequestParam("userName")String userName) {
        return sysUserRoleService.findRolesByUsername(userName);
    }

    @ResponseBody
    @RequestMapping(value ="/findFunctionByUsername", method = RequestMethod.GET)
    public List<String> findFunctionByUsername(@RequestParam("userName")String userName) {
        return sysUserRoleService.findFunctionByUsername(userName);
    }

    @ResponseBody
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public void save(@RequestBody Map<String, Object> map){
        sysUserRoleService.save(map);
    }

    @ResponseBody
    @RequestMapping(value ="/deleteByUserId", method = RequestMethod.GET)
    public int deleteByUserId(@RequestParam("userId")Integer userId){
        return sysUserRoleService.deleteByUserId(userId);
    }
}

