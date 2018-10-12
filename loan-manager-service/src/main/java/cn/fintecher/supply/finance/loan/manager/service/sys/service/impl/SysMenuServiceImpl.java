package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysMenuService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return this.restTemplate.getForObject(managerCorePath+"/sysMenu/queryNotButtonList",List.class);
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Integer userId) {
        if(userId == Constants.SYSTEM_ADMIN){
            return this.restTemplate.getForObject(managerCorePath+"/sysMenu/getAllMenuListBySuper",List.class,userId);
        }
        return this.restTemplate.getForObject(managerCorePath+"/sysMenu/getAllMenuList?userId={userId}",List.class,userId);
    }

    @Override
    public SysMenuEntity queryInfo(Integer menuId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysMenu/queryInfo?menuId={menuId}",SysMenuEntity.class,menuId);
    }

    @Override
    public List<SysMenuEntity> getAllMenus(MenuSearchForm menuSearchForm) {
        return this.restTemplate.postForObject(managerCorePath+"/sysMenu/getAllMenus",menuSearchForm,List.class);
    }

    @Override
    public void disable(Map<String, Object> map) {
        Integer  menuId= (Integer)map.get("menuId");
        List<Integer> menuList = this.restTemplate.getForObject(managerCorePath+"/sysMenu/queryMenuIdList?menuId={menuId}",List.class,menuId);
        List<Integer> subMenuList = new ArrayList<>();
        subMenuList.add((Integer)map.get("menuId"));
        if (menuList.size() > 0) {
            querySubMenuIdList(menuList,subMenuList);
        }
        map.put("subMenuList", subMenuList);
        this.restTemplate.postForObject(managerCorePath+"/sysMenu/delete",map,Void.class);
    }

    /**
     * 查询所有的子菜单
     */
    private void querySubMenuIdList(List<Integer> menuList,List<Integer> subMenuList){
        if (menuList.size() > 0) {
            for (int i = 0; i < menuList.size(); i++) {
                subMenuList.add(menuList.get(i));
                Integer menuId =menuList.get(i);
                List<Integer> menuIds =this.restTemplate.getForObject(managerCorePath+"/sysMenu/queryMenuIdList?menuId={menuId}",List.class,menuId);
                querySubMenuIdList(menuIds,subMenuList);
            }
        }
    }

    @Override
    public void save(SysMenuEntity menu) {
        this.restTemplate.postForObject(managerCorePath+"/sysMenu/save",menu,Void.class);
    }

    @Override
    public void update(SysMenuEntity menu) {
        this.restTemplate.postForObject(managerCorePath+"/sysMenu/update",menu,Void.class);
    }

    @Override
    public List<SysMenuEntity> getAuthMenus(Integer userId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysMenu/getAuthMenus?userId={userId}",List.class,userId);
    }
}