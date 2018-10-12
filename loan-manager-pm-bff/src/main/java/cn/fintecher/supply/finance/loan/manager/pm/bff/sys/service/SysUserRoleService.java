package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service;

import java.util.List;

/**
 * SysUserRoleService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysUserRoleService {

    List<String> findRolesByUsername(String userName);
    List<String> findFunctionByUsername(String userName);
}