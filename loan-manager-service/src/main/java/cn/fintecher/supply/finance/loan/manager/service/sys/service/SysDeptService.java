package cn.fintecher.supply.finance.loan.manager.service.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * SysDeptService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysDeptService {
    /**
     * 选择部门(添加、修改菜单)
     */
    List<SysDeptEntity> queryListDept();

    /**
     * 查询所有一二级部门
     * @return
     */
    List<SysDeptEntity> queryDeptByPId();
    /**
     * 根据部门ID查询部门信息
     */
    SysDeptEntity getDeptById(Integer deptId);

    /**
     * 根据部门/公司ID查询下面子部门信息
     */
    List<SysDeptEntity> querySubDeptsInfo(Integer deptId);

    /**
     *
     * 根据公司id获取下面全部部门信息
     */
    List<SysDeptEntity> queryDeptsInfo(Integer compId);

    /**
     * 更新部门
     * @param sysDept
     */
    void update(SysDeptEntity sysDept);

    /**
     * 停用部门及其所有的子部门
     */
    void disable(Map<String, Object> map);

    /**
     * 根据部门ID 判断该部门和其子部门下是否有客户
     *
     */
    boolean querySubUserByDeptId(Integer deptId);
    /**
     * 查询所有部门接口
     */
    List<SysDeptEntity> getAllDepts(DeptSearchForm deptSearchForm);
    /**
     * 保存部门
     */
    void save(SysDeptEntity sysDept);

}