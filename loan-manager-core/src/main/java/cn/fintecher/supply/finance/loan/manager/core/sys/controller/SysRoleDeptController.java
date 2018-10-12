package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysRoleDept")
public class SysRoleDeptController {
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @ResponseBody
    @RequestMapping(value ="/delete", method = RequestMethod.GET)
    public int delete(@RequestParam("id") Integer id){
        return sysRoleDeptService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public void save(@RequestBody Map<String, Object> map){
        sysRoleDeptService.save(map);
    }
}

