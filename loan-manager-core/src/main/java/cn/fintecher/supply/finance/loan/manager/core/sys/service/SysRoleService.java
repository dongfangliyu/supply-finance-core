package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.RoleSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public List<SysRoleEntity> queryAllRoles(){
        return sysRoleDao.queryAllRoles();
    }

    public SysRoleEntity queryObject(Integer roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    /**
     * 分页查询
     */
    public List<SysRoleEntity> findRolePage(RoleSearchForm roleSearchForm){
        return sysRoleDao.findRolePage(roleSearchForm);
    }
    /**
     * 分页查询总数
     */
    public int findRolePageCount(RoleSearchForm roleSearchForm){
        return  sysRoleDao.findRolePageCount(roleSearchForm);
    }

    public Message save(SysRoleEntity role) {
        try {
            List<SysRoleEntity> list =sysRoleDao.getByRoleCode(role.getRoleCode());
            if(list!=null && list.size()>0){
                return  new Message(MessageType.MSG_ERROR,"sys_role","角色code重复！");
            }else{
                sysRoleDao.save(role);
                return  new Message(MessageType.MSG_SUCCESS,"sys_role",role);
            }
        }catch (Exception e){
            return  new Message(MessageType.MSG_ERROR,"sys_role",e.getMessage());
        }
    }

    public Message update(SysRoleEntity role) {
        try {
            List<SysRoleEntity> list =sysRoleDao.getByRoleCode(role.getRoleCode());
            if(list.size() >= 2){
                return  new Message(MessageType.MSG_ERROR,"sys_role","角色code重复！");
            }else if(list.size() == 1){
                if(!role.getRoleId() .equals(list.get(0).getRoleId()) ){
                    return  new Message(MessageType.MSG_ERROR,"sys_role","角色code重复！");
                }
            }
            sysRoleDao.update(role);
            return  new Message(MessageType.MSG_SUCCESS,"sys_role",role);
        }catch (Exception e){
            return  new Message(MessageType.MSG_ERROR,"sys_role",e.getMessage());
        }

    }

}