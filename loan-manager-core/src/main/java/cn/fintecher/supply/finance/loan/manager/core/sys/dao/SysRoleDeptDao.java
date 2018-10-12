package cn.fintecher.supply.finance.loan.manager.core.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Mapper
public interface SysRoleDeptDao {
    int delete(Integer id);
    void save(Map<String, Object> map);
}
