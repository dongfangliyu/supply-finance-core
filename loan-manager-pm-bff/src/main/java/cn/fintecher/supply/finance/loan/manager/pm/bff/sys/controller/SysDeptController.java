package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptsResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sysDept")
@Api(tags="部门接口")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 选择部门(添加、修改菜单)
     */
    @ApiOperation(value="选择部门(添加、修改菜单) ", notes="选择部门(添加、修改菜单) ）")
    @RequestMapping(value ="/select", method = RequestMethod.GET)
    public CommonResponse<List<SysDeptEntity>> select(Principal principal){
        CommonResponse<List<SysDeptEntity>> response = sysDeptService.select(principal.getName());
        return response;
    }


    /**
     * 查询所有一二级部门接口
     */
    @ApiOperation(value="查询所有一二级部门接口", notes="查询所有一二级部门接口")
    @RequestMapping(value ="/getOneAndTwoDepts",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getOneAndTwoDepts(){
        CommonResponse<DeptsResponse> response = sysDeptService.getOneAndTwoDepts();
        return response;
    }

    /**
     * 根据部门Id查询部门信息
     */
    @ApiOperation(value="根据部门Id查询部门信息", notes="根据部门Id查询部门信息")
    @RequestMapping(value ="/getDeptById/{deptId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptResponse> getDeptById(@PathVariable("deptId") Integer deptId){
        CommonResponse<DeptResponse> response = sysDeptService.getDeptById(deptId);
        return response;
    }


    /**
     * 根据部门/公司ID查询下面子部门信息
     */
    @ApiOperation(value="根据部门/公司ID查询下面子部门信息", notes="根据部门/公司ID查询下面子部门信息")
    @RequestMapping(value ="/getDeptByDeptId/{deptId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getDeptByDeptId(@PathVariable("deptId") Integer deptId){
        CommonResponse<DeptsResponse> response = sysDeptService.getDeptByDeptId(deptId);
        return response;
    }

    /**
     * 根据公司ID查询下面所有部门信息
     */
    @ApiOperation(value="根据公司ID查询下面所有部门信息", notes="根据公司ID查询下面所有部门信息")
    @RequestMapping(value ="/getDeptByCompId/{compId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getDeptByCompId(@PathVariable("compId") Integer compId){
        CommonResponse<DeptsResponse> response = sysDeptService.getDeptByCompId(compId);
        return response;
    }

    /**
     * 部门信息添加/修改／停用
     */
    @ApiOperation(value="部门信息添加/修改／停用", notes="部门信息添加/修改／停用")
    @RequestMapping(value ="/addOrUpdateDept", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<Object> addOrUpdateDept(@RequestBody SysDeptEntity dept,Principal principal){
        dept.setOperateByName(principal.getName());
        CommonResponse<Object> response = sysDeptService.addOrUpdateDept(dept);
        return response;
    }

    /**
     * 查询所有部门接口
     */

    @ApiOperation(value = "查询所有部门接口", notes = "查询所有部门接口")
    @RequestMapping(value = "/getAllDepts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    public CommonResponse<DeptsResponse> getAllDepts(@RequestBody DeptSearchForm deptSearchForm) {
        CommonResponse<DeptsResponse> response = sysDeptService.getAllDepts(deptSearchForm);
        return response;
    }
}

