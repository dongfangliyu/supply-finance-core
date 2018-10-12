package cn.fintecher.supply.finance.loan.manager.core.pledge.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.FinanceStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.FinanceStockInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 17:33:07
 */
@Service("financeStockInfoService")
public class FinanceStockInfoServiceImpl implements FinanceStockInfoService {

   	@Autowired
	private FinanceStockInfoDao financestockInfoDao;
	
	@Override
	public Integer insertStockInfo(FinanceStockInfoEntity financeStockInfoEntity){
		return financestockInfoDao.insertStockInfo(financeStockInfoEntity);
	}
	
	@Override
	public List<FinanceStockInfoEntity> selectByStockInfo(FinanceStockInfoEntity financeStockInfoEntity) {
		return financestockInfoDao.selectByStockInfo(financeStockInfoEntity);
	}
	
	@Override
	public Integer updateStockInfo(FinanceStockInfoEntity financeStockInfoEntity) {
		// TODO Auto-generated method stub
		return financestockInfoDao.updateStockInfo(financeStockInfoEntity);
	}
	
	@Override
	public FinanceStockInfoEntity queryStockInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return financestockInfoDao.queryStockInfoByPid(pid);
	}

}
