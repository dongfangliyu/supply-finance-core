package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * SysUserRoleService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public List<String> findRolesByUsername(String userName) {
        return this.restTemplate.getForObject(userServicePath+"/sysUserRole/findRolesByUsername?userName={userName}",List.class,userName);
    }

    @Override
    public List<String> findFunctionByUsername(String userName) {
        return this.restTemplate.getForObject(userServicePath+"/sysUserRole/findFunctionByUsername?userName={userName}",List.class,userName);
    }
}