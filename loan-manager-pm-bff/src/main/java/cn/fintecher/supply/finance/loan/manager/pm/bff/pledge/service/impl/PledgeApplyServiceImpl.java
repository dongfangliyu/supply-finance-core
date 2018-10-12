package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.PledgeApplyCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeApplyService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
@Service("pledgeApplyService")
public class PledgeApplyServiceImpl implements PledgeApplyService {

	@Autowired
    private PledgeApplyCore pledgeApplyCore;

	@Override
	public Message searchApplyList(PledgeApplyForm pledgeApplyForm) {
		// TODO Auto-generated method stub
		return pledgeApplyCore.searchApplyList(pledgeApplyForm);
	}

	@Override
	public Message searchApplyDetail(Long id, String userName) {
		// TODO Auto-generated method stub
		return pledgeApplyCore.searchApplyDetail( id,  userName);
	}

	@Override
	public Message submitApply(PledgeApplySubmit pledgeApplySubmit) {
		// TODO Auto-generated method stub
		return pledgeApplyCore.submitApply(pledgeApplySubmit);
	};

}
