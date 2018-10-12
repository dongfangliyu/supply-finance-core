package cn.fintecher.supply.finance.loan.manager.core.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.PledgeApplyStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.pledge.service.PledgeApplyStockInfoService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@Service("pledgeApplyStockInfoService")
public class PledgeApplyStockInfoServiceImpl implements PledgeApplyStockInfoService {

   	@Autowired
	private PledgeApplyStockInfoDao pledgeapplyStockInfoDao;
	
	@Override
	public Integer insertApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity){
		return pledgeapplyStockInfoDao.insertApplyStockInfo(pledgeApplyStockInfoEntity);
	}
	
	@Override
	public List<PledgeApplyStockInfoEntity> selectByApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity) {
		return pledgeapplyStockInfoDao.selectByApplyStockInfo(pledgeApplyStockInfoEntity);
	}
	
	@Override
	public Integer updateApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity) {
		// TODO Auto-generated method stub
		return pledgeapplyStockInfoDao.updateApplyStockInfo(pledgeApplyStockInfoEntity);
	}
	
	@Override
	public PledgeApplyStockInfoEntity queryApplyStockInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return pledgeapplyStockInfoDao.queryApplyStockInfoByPid(pid);
	}

	@Override
	public Message<Integer> countConfirmingTaskCanReceiveNum(Integer node) {
		if(node == 1){
			return new Message<Integer>(MessageType.MSG_SUCCESS,"applyStockInfo_core",pledgeapplyStockInfoDao.countConfirmingTaskFirstCanReceiveNum());
		}else if (node == 2){
			return new Message<Integer>(MessageType.MSG_SUCCESS,"applyStockInfo_core",pledgeapplyStockInfoDao.countConfirmingTaskSecondCanReceiveNum());
		}else if (node == 3){
			return new Message<Integer>(MessageType.MSG_SUCCESS,"applyStockInfo_core",pledgeapplyStockInfoDao.countConfirmingTaskThirdCanReceiveNum());
		}else{
			return new Message(MessageType.MSG_ERROR,"applyStockInfo_core","传参错误");
		}
	}
}
