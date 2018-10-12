package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.PledgeThirdTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeThirdTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Service("pledgeThirdTrialService")
public class PledgeThirdTrialServiceImpl implements PledgeThirdTrialService {

	@Autowired
    private PledgeThirdTrialCore pledgeThirdTrialCore;
	
	@Override
	public Message receiveTask(String userName) {
		// TODO Auto-generated method stub
		return pledgeThirdTrialCore.receiveTask(userName);
	}

	@Override
	public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom) {
		// TODO Auto-generated method stub
		return pledgeThirdTrialCore.searchPledgeList(pledgeStockTrialFrom);
	}

	@Override
	public Message searchDetail(Long id, String userName) {
		// TODO Auto-generated method stub
		return pledgeThirdTrialCore.searchDetail(id,userName);
	}

	@Override
	public Message submitContnet(PledgeSumbitTrialRequest pledgeApplyInfoResponse) {
		// TODO Auto-generated method stub
		return pledgeThirdTrialCore.submitContnet(pledgeApplyInfoResponse);
	};
}
