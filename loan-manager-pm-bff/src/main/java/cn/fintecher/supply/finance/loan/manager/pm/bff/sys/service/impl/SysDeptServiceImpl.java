package cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.impl;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.DeptsResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * SysDeptService 接口实现类
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service("SysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public CommonResponse<List<SysDeptEntity>> select(String userName) {
        return this.restTemplate.getForObject(userServicePath+"/sysDept/select?userName={userName}",CommonResponse.class,userName);
    }

    @Override
    public CommonResponse<DeptsResponse> getOneAndTwoDepts() {
        return this.restTemplate.getForObject(userServicePath+"/sysDept/getOneAndTwoDepts",CommonResponse.class);
    }

    @Override
    public CommonResponse<DeptResponse> getDeptById(Integer deptId) {
        return this.restTemplate.getForObject(userServicePath+"/sysDept/getDeptById/{deptId}",CommonResponse.class,deptId);
    }

    @Override
    public CommonResponse<DeptsResponse> getDeptByDeptId(Integer deptId) {
        return this.restTemplate.getForObject(userServicePath+"/sysDept/getDeptByDeptId/{deptId}",CommonResponse.class,deptId);
    }

    @Override
    public CommonResponse<DeptsResponse> getDeptByCompId(Integer compId){
        return this.restTemplate.getForObject(userServicePath+"/sysDept/getDeptByCompId/{compId}",CommonResponse.class,compId);
    }

    @Override
    public CommonResponse<Object> addOrUpdateDept(SysDeptEntity dept) {
        return this.restTemplate.postForObject(userServicePath+"/sysDept/addOrUpdateDept",dept,CommonResponse.class);
    }

    @Override
    public CommonResponse<DeptsResponse> getAllDepts(DeptSearchForm deptSearchForm) {
        return this.restTemplate.postForObject(userServicePath+"/sysDept/getAllDepts",deptSearchForm,CommonResponse.class);
    }
}