package cn.fintecher.supply.finance.loan.manager.core.sys.service;


import cn.fintecher.supply.finance.loan.manager.common.sys.DeptSearchForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysDeptEntity;
import cn.fintecher.supply.finance.loan.manager.core.sys.dao.SysDeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysDeptService 接口
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-13 10:36:28 
 */
@Service
public class SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    public List<SysDeptEntity> queryListDept(){
        return sysDeptDao.queryListDept();
    }

    public List<SysDeptEntity> getOneAndTwoDepts(){
        return sysDeptDao.getOneAndTwoDepts();
    }

    public SysDeptEntity getDeptById(Integer deptId) {
        return  sysDeptDao.getDeptById(deptId);
    }

    public List<SysDeptEntity> querySubDeptsInfo(Integer deptId) {
        return sysDeptDao.querySubList(deptId);
    }

    public List<SysDeptEntity> queryListByCompId(Integer compId){
        return sysDeptDao.queryListByCompId(compId);
    }

    public void update(SysDeptEntity sysDept){
        sysDeptDao.update(sysDept);
    }

    public void delete(Map<String,Object> map){
        sysDeptDao.delete(map);
    }

    public List<Integer> queryDetpIdList(Integer parentId){
        return  sysDeptDao.queryDetpIdList(parentId);
    }

    public void save(SysDeptEntity sysDept) {
        sysDeptDao.save(sysDept);
    }

    public List<SysDeptEntity> getAllDepts(DeptSearchForm deptSearchForm){
        return sysDeptDao.getAllDepts(deptSearchForm);
    }

    public SysDeptEntity queryDeptById(int deptId){
        return  sysDeptDao.queryDeptById(deptId);
    }
}