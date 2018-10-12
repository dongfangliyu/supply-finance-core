package cn.fintecher.supply.finance.loan.manager.core.pledge.service;


import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;

import java.util.List;

/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 17:33:07
 */
public interface FinanceStockInfoService{

    /**
	 * 新增
	 * @param 
	 * @return
	 */
	Integer insertStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	List<FinanceStockInfoEntity> selectByStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	/**
	 * 修改
	 * @param 
	 */
	Integer updateStockInfo(FinanceStockInfoEntity financeStockInfoEntity);
	
	/**
	 * 根据主键查询
	 * @param 
	 * @return
	 */
	FinanceStockInfoEntity queryStockInfoByPid(String pid);
}
