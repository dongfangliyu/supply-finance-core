package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.MenuResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenuSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.MenusResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysMenuEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * SysMenuService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public CommonResponse<List<SysMenuEntity>> select() {
        return this.restTemplate.getForObject(userServicePath+"/sysMenu/select",CommonResponse.class);
    }

    @Override
    public CommonResponse<List<SysMenuEntity>> navigation(String userName) {
        return this.restTemplate.getForObject(userServicePath+"/sysMenu/navigation?userName={userName}",CommonResponse.class,userName);
    }
    @Override
    public CommonResponse<MenuResponse> getMenuById(Integer menuId) {
        return this.restTemplate.getForObject(userServicePath+"/sysMenu/getMenuById/{menuId}",CommonResponse.class,menuId);
    }

    @Override
    public CommonResponse<MenusResponse> getAllMenus(MenuSearchForm menuSearchForm) {
        return this.restTemplate.postForObject(userServicePath+"/sysMenu/getAllMenus",menuSearchForm,CommonResponse.class);
    }

    @Override
    public CommonResponse addOrUpdateMenu(SysMenuEntity menu) {
        CommonResponse response =   this.restTemplate.postForObject(userServicePath+"/sysMenu/addOrUpdateMenu",menu,CommonResponse.class);
        return  response;
    }

    @Override
    public CommonResponse<List<SysMenuEntity>> getAuthMenus(String userName) {
        return this.restTemplate.getForObject(userServicePath+"/sysMenu/getAuthMenus?userName={userName}",CommonResponse.class,userName);
    }
}