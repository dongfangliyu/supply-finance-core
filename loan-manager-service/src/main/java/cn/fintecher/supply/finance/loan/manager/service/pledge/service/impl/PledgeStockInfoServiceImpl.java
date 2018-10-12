package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeStockInfoService;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@Service("pledgeStockInfoService")
public class PledgeStockInfoServiceImpl implements PledgeStockInfoService {

	@Autowired
    private PledgeStockInfoCore pledgeStockInfoCore;
    
    @Override
	public Message insertStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
    	msg.setMessage(pledgeStockInfoCore.insertStockInfo(pledgeStockInfoEntity));
		return msg;
	}
	
	@Override
	public Message<List<PledgeStockInfoEntity>> selectByStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity) {
		Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
    	msg.setMessage(pledgeStockInfoCore.selectByStockInfo(pledgeStockInfoEntity));
		return msg;
	}
	
	@Override
	public Message updateStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity) {
		Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
    	msg.setMessage(pledgeStockInfoCore.updateStockInfo(pledgeStockInfoEntity));
		return msg;
	}
    
	@Override
	public Message<PledgeStockInfoEntity> queryStockInfoByPid(String pid) {
		Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
    	msg.setMessage(pledgeStockInfoCore.queryStockInfoByPid(pid));
		return msg;
	}

}

