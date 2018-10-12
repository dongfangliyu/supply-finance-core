package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleUserEntity;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysUserRoleService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public List<String> findRolesByUsername(String userName) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUserRole/findRolesByUsername?userName={userName}",List.class,userName);
    }

    @Override
    public List<String> findFunctionByUsername(String userName) {
        return this.restTemplate.getForObject(managerCorePath+"/sysUserRole/findFunctionByUsername?userName={userName}",List.class,userName);
    }

    @Override
    public void saveOrUpdate(SysRoleUserEntity sysRoleUserEntity) {
        Integer userId = sysRoleUserEntity.getUserId();
        this.restTemplate.getForObject(managerCorePath+"/sysUserRole/deleteByUserId?userId={userId}",Integer.class,userId);
        Map<String,Object> map = new HashMap<>();
        map.put("userId", sysRoleUserEntity.getUserId());
        map.put("roleIdList", sysRoleUserEntity.getRoleIdList());
        this.restTemplate.postForObject(managerCorePath+"/sysUserRole/save",map,Void.class);
    }

	@Override
	public Message getUsersByRoleId(Long roleId) {
		return this.restTemplate.getForObject(managerCorePath+"/sysUserRole/getUsersByRoleId?roleId={roleId}",Message.class,roleId);
	}

	@Override
	public Message getRolesByUserId(Long userId) {
		return this.restTemplate.getForObject(managerCorePath+"/sysUserRole/getRolesByUserId?userId={userId}",Message.class,userId);
	}
}