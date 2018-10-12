package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.RoleSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.RolesResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleDeptService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleMenuService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysRoleService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * SysRoleService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysRoleEntity> queryAllRoles() {
        return this.restTemplate.getForObject(managerCorePath+"/sysRole/queryAllRoles",List.class);
    }

    @Override
    public SysRoleEntity queryObject(Integer roleId) {
        SysRoleEntity role = this.restTemplate.getForObject(managerCorePath+"/sysRole/queryObject?roleId={roleId}",SysRoleEntity.class,roleId);
        role.setMenuIdList(sysRoleMenuService.queryMenuIdList(roleId));
        return role;
    }

    @Override
    public PagedResponse<RolesResponse> findRolePage(RoleSearchForm roleSearchForm) {
        List<SysRoleEntity> roleList = this.restTemplate.postForObject(managerCorePath+"/sysRole/findRolePage",roleSearchForm,List.class);
        int total=this.restTemplate.postForObject(managerCorePath+"/sysRole/findRolePageCount",roleSearchForm,Integer.class);
        PagedResponse<RolesResponse> pagedResponse = new PagedResponse<RolesResponse>();
        RolesResponse rolesResponse = new RolesResponse();
        rolesResponse.setRoles(roleList);
        pagedResponse.setTotal(total);
        pagedResponse.setData(rolesResponse);
        pagedResponse.setPageNo(roleSearchForm.getPageNo());
        pagedResponse.setPageSize(roleSearchForm.getPageSize());
        return pagedResponse;
    }

    @Override
    public Message save(SysRoleEntity role) {
        Message message = this.restTemplate.postForObject(managerCorePath+"/sysRole/save",role,Message.class);
        if(MessageType.MSG_SUCCESS.equals(message.getCode())){
            role = JSONUtil.toBean(message.getMessage(),SysRoleEntity.class);
            if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                //保存角色与菜单关系
                sysRoleMenuService.saveOrUpdate(role);
                //保存角色与部门关系
                sysRoleDeptService.saveOrUpdate(role);
            }
        }
        return message;
    }

    @Override
    public Message update(SysRoleEntity role) {
        Message message = this.restTemplate.postForObject(managerCorePath+"/sysRole/update",role,Message.class);

        if(MessageType.MSG_SUCCESS.equals(message.getCode())){
            role = JSONUtil.toBean(message.getMessage(),SysRoleEntity.class);
            //保存角色与菜单关系
            sysRoleMenuService.saveOrUpdate(role);
            //保存角色与部门关系
            sysRoleDeptService.saveOrUpdate(role);
        }
        return  message;
    }

    @Override
    public Boolean queryUserByRoleId(Integer roleId) {
        List<SysUserAdminEntity> userList = sysUserService.queryUserByRoleId(roleId);
        if (userList.size() == 0) {
            return false;
        }
        return true;
    }
}