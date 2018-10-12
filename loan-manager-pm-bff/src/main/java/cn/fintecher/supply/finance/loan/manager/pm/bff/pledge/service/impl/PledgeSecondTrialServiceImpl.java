package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.PledgeSecondTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeSecondTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Service("pledgeSecondTrialService")
public class PledgeSecondTrialServiceImpl implements PledgeSecondTrialService {

	@Autowired
    private PledgeSecondTrialCore pledgeSecondTrialCore;
	
	@Override
	public Message receiveTask(String userName) {
		// TODO Auto-generated method stub
		return pledgeSecondTrialCore.receiveTask(userName);
	}

	@Override
	public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom) {
		// TODO Auto-generated method stub
		return pledgeSecondTrialCore.searchPledgeList(pledgeStockTrialFrom);
	}

	@Override
	public Message searchDetail(Long id, String userName) {
		// TODO Auto-generated method stub
		return pledgeSecondTrialCore.searchDetail(id,userName);
	}

	@Override
	public Message submitContnet(PledgeSumbitTrialRequest pledgeApplyInfoResponse) {
		// TODO Auto-generated method stub
		return pledgeSecondTrialCore.submitContnet(pledgeApplyInfoResponse);
	};
}
