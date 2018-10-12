package cn.fintecher.supply.finance.loan.manager.core.base.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.core.base.dao.BaseDictionaryDao;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 4:07
 */
@Service
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

    @Autowired
    private BaseDictionaryDao baseDictionaryDao;

    @Override
    public List<BaseDictionaryEntity> getAllList() {
        return baseDictionaryDao.getAllList();
    }

    @Override
    public List<BaseDictionaryEntity> getListByCode(String code) {
        return baseDictionaryDao.getListByCode(code);
    }

	@Override
	public BaseDictionaryEntity getEntityByPid(Long pid) {
		// TODO Auto-generated method stub
		return baseDictionaryDao.getEntityByPid(pid);
	}
}
