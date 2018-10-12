package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysUserRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    public List<String> findRolesByUsername(String userName) {
        return  sysUserRoleDao.findRolesByUsername(userName);
    }


    public List<String> findFunctionByUsername(String userName) {
        return null;
    }


    public void save(Map<String, Object> map){
        sysUserRoleDao.save(map);
    }

    public int deleteByUserId(Integer userId){
        return sysUserRoleDao.deleteByUserId(userId);
    }


	public Message getUsersByRoleId(Long roleId) {
		List<SysUserAdminEntity> list = sysUserRoleDao.getUsersByRoleId(roleId);
		return new Message(MessageType.MSG_SUCCESS,"sys_core",list);
	}


	public Message getRolesByUserId(Long userId) {
		List<SysRoleEntity> list = sysUserRoleDao.getRolesByUserId(userId);
		return new Message(MessageType.MSG_SUCCESS,"sys_core",list);
	}
}