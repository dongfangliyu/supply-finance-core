package cn.fintecher.supply.finance.loan.manager.core.pledge.dao;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 17:33:07
 */
@Mapper
public interface FinanceStockInfoDao{
	
	Integer insertStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	List<FinanceStockInfoEntity> selectByStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	Integer updateStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	FinanceStockInfoEntity queryStockInfoByPid(String pid);

	FinanceStockInfoEntity selectByStockInfoByCommodityId(String pid);
}
