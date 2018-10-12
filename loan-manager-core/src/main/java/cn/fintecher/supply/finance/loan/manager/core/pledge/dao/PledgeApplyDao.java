package cn.fintecher.supply.finance.loan.manager.core.pledge.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialListResponse;

/**
 * 仓单质押申请信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@Mapper
public interface PledgeApplyDao{
	

	List<PledgeApplyListResponse> queryPageListByPledgeApply(PledgeApplyForm pledgeApplyForm);

	Integer queryPageCountByPledgeApply(PledgeApplyForm pledgeApplyForm);

	List<PledgeApplyStockInfoEntity> sureReceiveTask(@Param("task")String task);

	Integer selectTrialCount(PledgeStockTrialFrom pledgeStockTrialFrom);

	List<PledgeTrialListResponse> selectTrialList(PledgeStockTrialFrom pledgeStockTrialFrom);
}
