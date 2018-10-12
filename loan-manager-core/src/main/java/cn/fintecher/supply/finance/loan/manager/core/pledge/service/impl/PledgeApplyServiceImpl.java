package cn.fintecher.supply.finance.loan.manager.core.pledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialListResponse;
import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.PledgeApplyDao;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeApplyService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@Service("pledgeApplyService")
public class PledgeApplyServiceImpl implements PledgeApplyService {

   	@Autowired
	private PledgeApplyDao pledgeApplyDao;

	@Override
	public List<PledgeApplyListResponse> queryPageListByPledgeApply(PledgeApplyForm pledgeApplyForm) {
		// TODO Auto-generated method stub
		return pledgeApplyDao.queryPageListByPledgeApply(pledgeApplyForm);
	}

	@Override
	public Integer queryPageCountByPledgeApply(PledgeApplyForm pledgeApplyForm) {
		// TODO Auto-generated method stub
		return pledgeApplyDao.queryPageCountByPledgeApply(pledgeApplyForm);
	}

	@Override
	public List<PledgeApplyStockInfoEntity> sureReceiveTask(String task) {
		// TODO Auto-generated method stub
		return pledgeApplyDao.sureReceiveTask(task);
	}

	@Override
	public Integer selectTrialCount(PledgeStockTrialFrom pledgeStockTrialFrom) {
		// TODO Auto-generated method stub
		return pledgeApplyDao.selectTrialCount(pledgeStockTrialFrom);
	}

	@Override
	public List<PledgeTrialListResponse> selectTrialList(PledgeStockTrialFrom pledgeStockTrialFrom) {
		// TODO Auto-generated method stub
		return pledgeApplyDao.selectTrialList(pledgeStockTrialFrom);
	}
	

}
