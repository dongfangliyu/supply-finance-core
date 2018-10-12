package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * SysRoleService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public CommonResponse relRoleAndUser(SysRoleUserEntity sysRoleUserEntity) {
        return  this.restTemplate.postForObject(userServicePath+"/sysRole/relRoleAndUser",sysRoleUserEntity,CommonResponse.class);
    }

    @Override
    public CommonResponse<RolesResponse> queryAllRoles() {
        return  this.restTemplate.getForObject(userServicePath+"/sysRole/queryAllRoles",CommonResponse.class);
    }

    @Override
    public CommonResponse<RoleResponse> getRoleById(Integer roleId) {
        return  this.restTemplate.getForObject(userServicePath+"/sysRole/getRoleById/{roleId}",CommonResponse.class,roleId);
    }

    @Override
    public PagedResponse<RolesResponse> getAllRoles(RoleSearchForm roleSearchForm) {
        return  this.restTemplate.postForObject(userServicePath+"/sysRole/getAllRoles",roleSearchForm,PagedResponse.class);
    }

    @Override
    public CommonResponse addOrUpdateRole(SysRoleEntity role) {
        CommonResponse response = this.restTemplate.postForObject(userServicePath+"/sysRole/addOrUpdateRole",role,CommonResponse.class);
        return response;
    }
}