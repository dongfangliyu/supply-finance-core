package cn.fintecher.supply.finance.loan.manager.service.pledge.service;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

/**
 * 仓单质押申请信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
public interface PledgeStockInfoService{

    /**
	 * 新增仓单质押申请信息表
	 * @param 
	 * @return
	 */
	Message insertStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 查询仓单质押申请信息表
	 * @param quotaApply
	 * @return
	 */
	Message<List<PledgeStockInfoEntity>> selectByStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 修改仓单质押申请信息表
	 * @param quotaApply
	 */
	Message updateStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 根据主键查询仓单质押申请信息表
	 * @param quotaApply
	 */
	Message<PledgeStockInfoEntity> queryStockInfoByPid(String pid);
 
   


   

}