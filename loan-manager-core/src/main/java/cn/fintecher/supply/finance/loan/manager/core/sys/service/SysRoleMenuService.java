package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysRoleMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysRoleMenuService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    public List<Integer> queryMenuIdList(Integer roleId){
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }

    public int delete(Integer id){
        return sysRoleMenuDao.delete(id);
    }

    public void save(Map<String, Object> map){
        sysRoleMenuDao.save(map);
    }
}