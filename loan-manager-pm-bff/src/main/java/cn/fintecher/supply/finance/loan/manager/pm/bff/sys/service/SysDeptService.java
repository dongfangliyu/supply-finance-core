package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptsResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;

import java.util.List;

/**
 * SysDeptService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
public interface SysDeptService {
    CommonResponse<List<SysDeptEntity>> select(String userName);

    CommonResponse<DeptsResponse> getOneAndTwoDepts();

    CommonResponse<DeptResponse> getDeptById(Integer deptId);

    CommonResponse<DeptsResponse> getDeptByDeptId(Integer deptId);

    CommonResponse<DeptsResponse> getDeptByCompId(Integer compId);

    CommonResponse<DeptsResponse> getAllDepts(DeptSearchForm deptSearchForm);

    CommonResponse<Object> addOrUpdateDept(SysDeptEntity dept);
}