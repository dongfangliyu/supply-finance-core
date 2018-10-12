package cn.fintecher.supply.finance.loan.manager.service.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysDeptService;
import cn.fintecher.supply.finance.loan.manager.service.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysDeptService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Value("${loan.manager.core.url}")
    private String managerCorePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysDeptEntity> queryListDept() {
        return this.restTemplate.getForObject(managerCorePath+"/sysDept/queryListDept",List.class);
    }

    @Override
    public List<SysDeptEntity> queryDeptByPId() {
        return this.restTemplate.getForObject(managerCorePath+"/sysDept/getOneAndTwoDepts",List.class);
    }

    @Override
    public SysDeptEntity getDeptById(Integer deptId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysDept/getDeptById?deptId={deptId}",SysDeptEntity.class,deptId);
    }

    @Override
    public List<SysDeptEntity> querySubDeptsInfo(Integer deptId) {
        return this.restTemplate.getForObject(managerCorePath+"/sysDept/querySubDeptsInfo?deptId={deptId}",List.class,deptId);
    }

    @Override
    public List<SysDeptEntity> queryDeptsInfo(Integer compId) {
        // 获取公司下属子部门的信息
        List<SysDeptEntity> deptList=this.restTemplate.getForObject(managerCorePath+"/sysDept/queryListByCompId?compId={compId}",List.class,compId);
        return deptList;
    }

    @Override
    public void update(SysDeptEntity sysDept) {
        this.restTemplate.postForObject(managerCorePath+"/sysDept/update",sysDept,Void.class);
    }

    @Override
    public void disable(Map<String, Object> map) {
        Integer parentId = (Integer)map.get("deptId");
        List<Integer> deptIdList = this.restTemplate.getForObject(managerCorePath+"/sysDept/queryDetpIdList?parentId={parentId}",List.class,parentId);
        //所有要停用的部门id都放入list中 （一维数组，容易遍历）
        List<Integer> subDeptIdList = new ArrayList<>();
        subDeptIdList.add((Integer)map.get("deptId"));
        querySubDetpIdList(deptIdList,subDeptIdList);

        map.put("DeptIdList", subDeptIdList);
        this.restTemplate.postForObject(managerCorePath+"/sysDept/delete",map,Void.class);
    }

    /**
     * 查询所有的子部门
     * @param deptIdList 要查询部门列表
     * @param subDeptIdList 接收查询结果的所有子部门集合
     */
    private void querySubDetpIdList(List<Integer> deptIdList,List<Integer> subDeptIdList){
        if (deptIdList.size() > 0) {
            for (int i = 0; i < deptIdList.size(); i++) {
                subDeptIdList.add(deptIdList.get(i));
                Integer parentId = deptIdList.get(i);
                List<Integer> deptList = this.restTemplate.getForObject(managerCorePath+"/sysDept/queryDetpIdList?parentId={parentId}",List.class,parentId);
                querySubDetpIdList(deptList,subDeptIdList);
            }
        }
    }

    @Override
    public boolean querySubUserByDeptId(Integer deptId) {
        List<SysUserAdminEntity> userList = sysUserService.queryUserByDeptId(deptId);
        if (userList.size() > 0) {
            return true;
        }
        List<Integer> deptList = this.restTemplate.getForObject(managerCorePath+"/sysDept/queryDetpIdList?parentId={deptId}",List.class,deptId);
        if (deptList.size() > 0) {
            return querySubUserByDeptIdList(deptList);
        }
        return false;
    }

    public boolean querySubUserByDeptIdList(List<Integer> deptList){
        boolean flag=false;
        for (int i = 0; i < deptList.size(); i++) {
            Integer deptId=deptList.get(i);
            if (querySubUserByDeptId(deptId)) {
                flag=true;
                break;
            }
        }
        return flag;
    }

    @Override
    public void save(SysDeptEntity sysDept) {
        this.restTemplate.postForObject(managerCorePath+"/sysDept/save",sysDept,Void.class);
    }

    @Override
    public List<SysDeptEntity> getAllDepts(DeptSearchForm deptSearchForm) {
        return this.restTemplate.postForObject(managerCorePath+"/sysDept/getAllDepts",deptSearchForm,List.class);
    }

}