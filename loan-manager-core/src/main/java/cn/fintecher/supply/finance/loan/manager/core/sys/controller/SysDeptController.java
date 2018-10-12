package cn.fintecher.supply.finance.loan.manager.core.sys.controller;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 选择部门(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping(value = "queryListDept", method = RequestMethod.GET)
    public List<SysDeptEntity> queryListDept(){
        return  sysDeptService.queryListDept();
    }

    /**
     * 查询所有一二级部门
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOneAndTwoDepts", method = RequestMethod.GET)
    public List<SysDeptEntity> getOneAndTwoDepts(){
        return sysDeptService.getOneAndTwoDepts();
    }

    @ResponseBody
    @RequestMapping(value = "getDeptById", method = RequestMethod.GET)
    public SysDeptEntity getDeptById(@RequestParam("deptId") Integer deptId) {
        return sysDeptService.getDeptById(deptId);
    }

    @ResponseBody
    @RequestMapping(value = "querySubDeptsInfo", method = RequestMethod.GET)
    public List<SysDeptEntity> querySubDeptsInfo(@RequestParam("deptId")Integer deptId) {
        return sysDeptService.querySubDeptsInfo(deptId);
    }

    @ResponseBody
    @RequestMapping(value = "queryListByCompId", method = RequestMethod.GET)
    public List<SysDeptEntity> queryListByCompId(@RequestParam("compId")Integer compId){
        return sysDeptService.queryListByCompId(compId);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody SysDeptEntity sysDept){
        sysDeptService.update(sysDept);
    }


    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody Map<String,Object> map){
        sysDeptService.delete(map);
    }

    @ResponseBody
    @RequestMapping(value = "queryDetpIdList", method = RequestMethod.GET)
    public List<Integer> queryDetpIdList(@RequestParam("parentId")Integer parentId){
        return  sysDeptService.queryDetpIdList(parentId);
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(@RequestBody SysDeptEntity sysDept) {
        sysDeptService.save(sysDept);
    }

    /**
     * 查询所有部门接口
     */
    @ResponseBody
    @RequestMapping(value = "getAllDepts", method = RequestMethod.POST)
    public List<SysDeptEntity> getAllDepts(@RequestBody DeptSearchForm deptSearchForm){
        return sysDeptService.getAllDepts(deptSearchForm);
    }

    /**
     *
     * 根据id查询部门信息
     */
    @ResponseBody
    @RequestMapping(value = "queryDeptById", method = RequestMethod.GET)
    public SysDeptEntity queryDeptById(@RequestParam("deptId") Integer deptId){
        return  sysDeptService.queryDeptById(deptId);
    }
}

