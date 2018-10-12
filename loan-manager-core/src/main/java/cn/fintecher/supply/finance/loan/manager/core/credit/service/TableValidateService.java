package cn.fintecher.supply.finance.loan.manager.core.credit.service;

import cn.fintecher.supply.finance.loan.manager.core.credit.dao.EnterpriseFileDao;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 14:09 2018/6/26
 */
@Service
public class TableValidateService {
    @Autowired
    private EnterpriseFileDao enterpriseFileDao;

    public List<CompanyFileEntity> searchAllFile(Long pid){
        return  enterpriseFileDao.searchAllFile(pid);
    }

}
