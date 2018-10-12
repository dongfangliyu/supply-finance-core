package cn.fintecher.supply.finance.loan.manager.service.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.*;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import cn.fintecher.supply.finance.loan.manager.common.util.group.UpdateGroup;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController extends BaseSysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDeptController.class);

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 选择部门(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping(value ="/select", method = RequestMethod.GET)
    public CommonResponse<List<SysDeptEntity>> select(@RequestParam("userName") String userName){
        CommonResponse<List<SysDeptEntity>> response = new CommonResponse<>();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(userName);
            List<SysDeptEntity> deptList = sysDeptService.queryListDept();
            //添加一级部门
            if(sysUserAdminEntity.getUserId()== 0){
                SysDeptEntity root = new SysDeptEntity();
                root.setDeptId(0);
                root.setName("一级部门");
                root.setParantId(-1);
                deptList.add(root);
            }
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptList);
        } catch (RuntimeException e) {
            LOGGER.error("选择部门(添加、修改菜单) 失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }

    /**
     * 查询所有一二级部门接口
     */
    @ResponseBody
    @RequestMapping(value = "/getOneAndTwoDepts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getOneAndTwoDepts() {
        CommonResponse<DeptsResponse> response = new CommonResponse<>();

        try {
            //父级菜单集合
            List<SysDeptEntity> depts = sysDeptService.queryDeptByPId();
            DeptsResponse deptsResponse = new DeptsResponse();
            deptsResponse.setDepts(depts);

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptsResponse);
        } catch (RuntimeException e) {
            LOGGER.error("查询所有一二级部门接口失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }


    /**
     * 根据部门Id查询部门信息
     */
    @ResponseBody
    @RequestMapping(value = "/getDeptById/{deptId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptResponse> getDeptById(@PathVariable("deptId") Integer deptId) {

        CommonResponse<DeptResponse> response = new CommonResponse<>();

        try {
            if (ChkUtil.isEmpty(deptId)) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("部门id不能为空！");
                return response;
            }
            SysDeptEntity dept = sysDeptService.getDeptById(deptId);
            if (dept == null) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("部门不存在！");
                return response;
            }
            DeptResponse deptResponse = new DeptResponse();
            deptResponse.setDept(dept);

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptResponse);
        } catch (RuntimeException e) {
            LOGGER.error("根据部门Id查询部门信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 根据部门/公司ID查询下面子部门信息
     */
    @ResponseBody
    @RequestMapping(value = "/getDeptByDeptId/{deptId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getDeptByDeptId(@PathVariable("deptId") Integer deptId) {
        CommonResponse<DeptsResponse> response = new CommonResponse<DeptsResponse>();
        try {
            if (ChkUtil.isEmpty(deptId)) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("部门/公司id不能为空！");
                return response;
            }
            List<SysDeptEntity> depts = sysDeptService.querySubDeptsInfo(deptId);
            DeptsResponse deptsResponse = new DeptsResponse();
            deptsResponse.setDepts(depts);

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptsResponse);
        } catch (RuntimeException e) {
            LOGGER.error("根据部门/公司ID查询下面子部门信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 根据公司ID查询下面所有部门信息
     */
    @ResponseBody
    @RequestMapping(value = "/getDeptByCompId/{compId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getDeptByCompId(@PathVariable("compId") Integer compId) {

        CommonResponse<DeptsResponse> response = new CommonResponse<>();

        try {
            if (ChkUtil.isEmpty(compId)) {
                response.setSuccess(false);
                response.setCode(Constants.STATUS_NO_SUCCESS);
                response.setMsg("公司id不能为空！");
                return response;
            }
            List<SysDeptEntity> depts = sysDeptService.queryDeptsInfo(compId);
            DeptsResponse deptsResponse = new DeptsResponse();
            deptsResponse.setDepts(depts);
            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptsResponse);
        } catch (RuntimeException e) {
            LOGGER.error(" 根据公司ID查询下面所有部门信息失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }
        return response;
    }

    /**
     * 部门信息添加/修改／停用
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateDept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<Object> addOrUpdateDept(@RequestBody SysDeptEntity dept) {
        CommonResponse<Object> response = new CommonResponse<>();
        try {
            SysUserAdminEntity sysUserAdminEntity = getSysUser(dept.getOperateByName());
            if (ChkUtil.isEmpty(dept.getDeptId())) {   //新增
                ValidatorUtils.validateEntity(dept, AddGroup.class);
                try {

                    dept.setStatus("1");
                    dept.setCreateTime(DateUtil.getCurrentTime());
                    dept.setCreateUserId(sysUserAdminEntity.getUserId());
                    dept.setUpdateTime(null);
                    dept.setUpdateUserId(null);
                    sysDeptService.save(dept);

                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
                } catch (RuntimeException e) {
                    LOGGER.error("新增部门失败：{}", e);
                    response.setCode(Constants.SYSTEM_ERROR);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                }
            } else {   //修改
                try {
                    if (null != dept.getStatus() && "0".equals(dept.getStatus())) {
                        //判断是否有可用客户
                        if (sysDeptService.querySubUserByDeptId(dept.getDeptId())) {
                            response.setCode(Constants.SYSTEM_ERROR);
                            response.setMsg("当前部门或其子部门有可用客户，不可删除");
                            return response;
                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("deptId", dept.getDeptId());
                        map.put("updateTime", DateUtil.getCurrentTime());
                        map.put("updateUserId", sysUserAdminEntity.getUserId());
                        map.put("status", 0);
                        //停用其部门和所以的子部门
                        sysDeptService.disable(map);

                        response.setSuccess(true);
                        response.setCode(Constants.STATUS_SUCCESS);
                        response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

                    } else {
                        dept.setStatus("1");
                    }
                    ValidatorUtils.validateEntity(dept, UpdateGroup.class);
                    dept.setUpdateTime(DateUtil.getCurrentTime());
                    dept.setUpdateUserId(sysUserAdminEntity.getUserId());
                    dept.setCreateTime(null);
                    dept.setCreateUserId(null);
                    sysDeptService.update(dept);
                    response.setSuccess(true);
                    response.setCode(Constants.STATUS_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));

                } catch (RuntimeException e) {
                    LOGGER.error("修改部门信息失败：{}", e);
                    response.setSuccess(false);
                    response.setCode(Constants.STATUS_NO_SUCCESS);
                    response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
                }
            }
        }catch (Exception e){
            response.setSuccess(false);
            response.setCode(Constants.STATUS_NO_SUCCESS);
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * 查询所有部门接口
     */
    @ResponseBody
    @RequestMapping(value = "/getAllDepts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<DeptsResponse> getAllDepts(@RequestBody DeptSearchForm deptSearchForm) {
        CommonResponse<DeptsResponse> response = new CommonResponse<>();

        try {
            List<SysDeptEntity> depts = sysDeptService.getAllDepts(deptSearchForm);
            DeptsResponse deptsResponse = new DeptsResponse();
            deptsResponse.setDepts(depts);

            response.setSuccess(true);
            response.setCode(Constants.STATUS_SUCCESS);
            response.setMsg(Constants.statusMap.get(Constants.STATUS_SUCCESS));
            response.setData(deptsResponse);
        } catch (RuntimeException e) {
            LOGGER.error("查询所有部门接口（提供树形菜单）失败：{}", e);
            response.setCode(Constants.SYSTEM_ERROR);
            response.setMsg(Constants.statusMap.get(Constants.SYSTEM_ERROR));
        }

        return response;
    }
}

