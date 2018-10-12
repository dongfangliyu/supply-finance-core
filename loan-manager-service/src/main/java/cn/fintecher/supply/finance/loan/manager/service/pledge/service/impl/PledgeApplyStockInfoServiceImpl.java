package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeApplyStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyStockInfoService;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@Service("pledgeApplyStockInfoService")
public class PledgeApplyStockInfoServiceImpl implements PledgeApplyStockInfoService {

	@Autowired
    private PledgeApplyStockInfoCore pledgeApplyStockInfoCore;
    
    @Override
	public Message insertApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity){
		return new Message<>(MessageType.MSG_SUCCESS,"pledgeApply",
				pledgeApplyStockInfoCore.insertApplyStockInfo(pledgeApplyStockInfoEntity));
	}
	
	@Override
	public Message<List<PledgeApplyStockInfoEntity>> selectByApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity) {
		return new Message<>(MessageType.MSG_SUCCESS,"pledgeApply", pledgeApplyStockInfoCore.selectByApplyStockInfo(pledgeApplyStockInfoEntity));
	}
	
	@Override
	public Message updateApplyStockInfo(PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity) {
		return new Message<>(MessageType.MSG_SUCCESS,"pledgeApply",pledgeApplyStockInfoCore.updateApplyStockInfo(pledgeApplyStockInfoEntity));
	}
    
	@Override
	public Message<PledgeApplyStockInfoEntity> queryApplyStockInfoByPid(String pid) {
		return new Message<>(MessageType.MSG_SUCCESS,"pledgeApply", pledgeApplyStockInfoCore.queryApplyStockInfoByPid(pid));
	}

}

