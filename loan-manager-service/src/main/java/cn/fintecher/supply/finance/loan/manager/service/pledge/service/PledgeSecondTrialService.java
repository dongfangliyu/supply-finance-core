package cn.fintecher.supply.finance.loan.manager.service.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
public interface PledgeSecondTrialService {    
	
    public Message receiveTask(SysUserAdminEntity sysUserAdminEntity);
	
    public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom, SysUserAdminEntity sysUserAdminEntity);
	
    public Message searchDetail(Long id, SysUserAdminEntity sysUserAdminEntity);
	
    public Message submitContnet(PledgeSumbitTrialRequest pledgeApplyInfoResponse, SysUserAdminEntity sysUserAdminEntity);
   

}
