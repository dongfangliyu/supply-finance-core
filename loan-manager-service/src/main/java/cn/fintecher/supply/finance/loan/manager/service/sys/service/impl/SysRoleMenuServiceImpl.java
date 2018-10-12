package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRoleMenuService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public List<Integer> queryMenuIdList(Integer roleId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysRoleMenu/queryMenuIdList?roleId={roleId}",List.class,roleId);
    }

    @Override
    public void saveOrUpdate(SysRoleEntity role) {
        //先删除角色与菜单关系
        if(role.getMenuIdList()==null){
            return;
        }
        Integer roleId = role.getRoleId();
        this.restTemplate.getForObject(managerCorePath+"/sysRoleMenu/delete?id={roleId}",Integer.class,roleId);
        if(role.getMenuIdList().size()==0){
            return;
        }
        Integer userId ;
        if (role.getCreateUserId() != null) {
            userId = role.getCreateUserId();
        }else{
            userId = role.getUpdateUserId();
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", role.getRoleId());
        map.put("menuIdList", role.getMenuIdList());
        map.put("userId", userId);
        this.restTemplate.postForObject(managerCorePath+"/sysRoleMenu/save",map,Void.class);
    }
}