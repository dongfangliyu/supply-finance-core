package cn.fintecher.supply.finance.loan.manager.service.pledge.service;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

/**
 * 仓单质押申请信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
public interface PledgeApplyStockInfoService{

    /**
	 * 新增仓单质押申请信息表
	 * @param 
	 * @return
	 */
	Message insertApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 查询仓单质押申请信息表
	 * @param quotaApply
	 * @return
	 */
	Message<List<PledgeApplyStockInfoEntity>> selectByApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 修改仓单质押申请信息表
	 * @param quotaApply
	 */
	Message updateApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 根据主键查询仓单质押申请信息表
	 * @param quotaApply
	 */
	Message<PledgeApplyStockInfoEntity> queryApplyStockInfoByPid(String pid);
 
   


   

}