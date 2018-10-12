package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
public interface PledgeSecondTrialService {    
	
    public Message receiveTask(String userName);
	
    public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom);
	
    public Message searchDetail(Long id, String userName);
	
    public Message submitContnet(PledgeSumbitTrialRequest pledgeApplyInfoResponse);
   

}
