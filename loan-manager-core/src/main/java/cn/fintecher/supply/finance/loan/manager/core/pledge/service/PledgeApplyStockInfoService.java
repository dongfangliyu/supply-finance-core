package cn.fintecher.supply.finance.loan.manager.core.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
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
	Integer insertApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 查询仓单质押申请信息表
	 * @param 
	 * @return
	 */
	List<PledgeApplyStockInfoEntity> selectByApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 修改仓单质押申请信息表
	 * @param 
	 */
	Integer updateApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	/**
	 * 根据主键查询仓单质押申请信息表
	 * @param 
	 * @return
	 */
	PledgeApplyStockInfoEntity queryApplyStockInfoByPid(String pid);

    Message<Integer> countConfirmingTaskCanReceiveNum(Integer node);
}
