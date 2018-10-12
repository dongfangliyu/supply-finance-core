package cn.fintecher.supply.finance.loan.manager.core.pledge.dao;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 仓单质押申请信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@Mapper
public interface PledgeApplyStockInfoDao{
	
	Integer insertApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	List<PledgeApplyStockInfoEntity> selectByApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	Integer updateApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
	
	PledgeApplyStockInfoEntity queryApplyStockInfoByPid(String pid);

    Integer countConfirmingTaskFirstCanReceiveNum();

	Integer countConfirmingTaskSecondCanReceiveNum();

	Integer countConfirmingTaskThirdCanReceiveNum();
}
