package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * SysRoleDeptService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public void saveOrUpdate(SysRoleEntity role) {
        //先删除角色与菜单关系
        if (role.getDeptIdList() == null) {
            return;
        }

        Integer roleId = role.getRoleId();
       this.restTemplate.getForObject(managerCorePath+"/sysRoleDept/delete?id={roleId}",Integer.class,roleId);

        if( role.getDeptIdList().size() == 0){
            return ;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", role.getRoleId());
        map.put("deptIdList", role.getDeptIdList());
        this.restTemplate.postForObject(managerCorePath+"/sysRoleDept/save",map,Void.class);

    }
}