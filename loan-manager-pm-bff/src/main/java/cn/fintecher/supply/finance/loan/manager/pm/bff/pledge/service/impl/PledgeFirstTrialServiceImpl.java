package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.PledgeFirstTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeFirstTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Service("pledgeFirstTrialService")
public class PledgeFirstTrialServiceImpl implements PledgeFirstTrialService {

	@Autowired
    private PledgeFirstTrialCore pledgeFirstTrialCore;
	
	@Override
	public Message receiveTask(String userName) {
		// TODO Auto-generated method stub
		return pledgeFirstTrialCore.receiveTask(userName);
	}

	@Override
	public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom) {
		// TODO Auto-generated method stub
		return pledgeFirstTrialCore.searchPledgeList(pledgeStockTrialFrom);
	}

	@Override
	public Message searchDetail(Long id, String userName) {
		// TODO Auto-generated method stub
		return pledgeFirstTrialCore.searchDetail(id,userName);
	}

	@Override
	public Message submitContnet(PledgeSumbitTrialRequest pledgeApplyInfoResponse) {
		// TODO Auto-generated method stub
		return pledgeFirstTrialCore.submitContnet(pledgeApplyInfoResponse);
	};

}
