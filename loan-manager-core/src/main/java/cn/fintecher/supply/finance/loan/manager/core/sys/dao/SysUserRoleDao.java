package cn.fintecher.supply.finance.loan.manager.core.sys.dao;


import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Mapper
public interface SysUserRoleDao {
	List<SysUserAdminEntity> getUsersByRoleId(Long roleId);
    int deleteByUserId(Integer userId);
    void save(Map<String, Object> map);
    List<String> findRolesByUsername(String userName);
    List<String> findFunctionByRoleIds(List<String> roleIds);
    List<String> findRoleIdByUsername(String userName);
	List<SysRoleEntity> getRolesByUserId(Long userId);
}
