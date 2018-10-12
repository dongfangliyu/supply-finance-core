package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 13:35:36
 */
public interface AuditThirdTrialService {    
	
	public Message receiveTask(String userName);

	public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom);

	public Message searchDetail(Long id, String userName);

	public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest);

	public Message searchDetailResult(Long id, String userName);
   

}
