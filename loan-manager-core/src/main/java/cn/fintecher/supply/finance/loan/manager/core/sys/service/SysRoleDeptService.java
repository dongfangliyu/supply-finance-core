package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysRoleDeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SysRoleDeptService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysRoleDeptService {
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;
    public int delete(Integer id){
        return sysRoleDeptDao.delete(id);
    }

    public void save(Map<String, Object> map){
        sysRoleDeptDao.save(map);
    }
}