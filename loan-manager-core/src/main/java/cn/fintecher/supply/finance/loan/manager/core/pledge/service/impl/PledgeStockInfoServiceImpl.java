package cn.fintecher.supply.finance.loan.manager.core.pledge.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.PledgeStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeStockInfoService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@Service("pledgeStockInfoService")
public class PledgeStockInfoServiceImpl implements PledgeStockInfoService {

   	@Autowired
	private PledgeStockInfoDao pledgestockInfoDao;
	
	@Override
	public Integer insertStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity){
		 pledgestockInfoDao.insertStockInfo(pledgeStockInfoEntity);
		 return pledgeStockInfoEntity.getPid().intValue();
	}
	
	@Override
	public List<PledgeStockInfoEntity> selectByStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity) {
		return pledgestockInfoDao.selectByStockInfo(pledgeStockInfoEntity);
	}
	
	@Override
	public Integer updateStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity) {
		// TODO Auto-generated method stub
		return pledgestockInfoDao.updateStockInfo(pledgeStockInfoEntity);
	}
	
	@Override
	public PledgeStockInfoEntity queryStockInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return pledgestockInfoDao.queryStockInfoByPid(pid);
	}

	@Override
	public List<PledgeSigningResponse> selectAdminSigningList(PledgeStockFrom pledgeStockFrom) {
		return pledgestockInfoDao.selectAdminSigningList(pledgeStockFrom);
	}

	@Override
	public List<PledgeSigningResponse> selectWebSigningList(PledgeStockFrom pledgeStockFrom) {
		return pledgestockInfoDao.selectWebSigningList(pledgeStockFrom);
	}

	@Override
	public Integer selectAdminSigningListCount(PledgeStockFrom pledgeStockFrom) {
		return pledgestockInfoDao.selectAdminSigningListCount(pledgeStockFrom);
	}

	@Override
	public Integer selectWebSigningListCount(PledgeStockFrom pledgeStockFrom) {
		return pledgestockInfoDao.selectWebSigningListCount(pledgeStockFrom);
	}
}
