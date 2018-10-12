package cn.fintecher.supply.finance.loan.manager.core.sys.dao;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Mapper
public interface SysDeptDao {

    /**
     * 选择部门(添加、修改菜单)
     */
    List<SysDeptEntity> queryListDept();

    /**
     * 查询所有一二级部门
     * @return
     */
    List<SysDeptEntity> getOneAndTwoDepts();

    SysDeptEntity getDeptById(Integer deptId);

    /**
     * 查询子部门列表
     */
    List<SysDeptEntity> querySubList(Integer deptId);

    List<SysDeptEntity> queryListByCompId(Integer compId);

    void update(SysDeptEntity sysDept);

    void delete(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Integer> queryDetpIdList(Integer parentId);

    void save(SysDeptEntity sysDept);

    /**
     *
     * 根据id查询部门信息
     */
    SysDeptEntity queryDeptById(int deptId);

    /**
     * 查询所有部门接口
     */
    List<SysDeptEntity> getAllDepts(@Param("vo") DeptSearchForm deptSearchForm);
}
