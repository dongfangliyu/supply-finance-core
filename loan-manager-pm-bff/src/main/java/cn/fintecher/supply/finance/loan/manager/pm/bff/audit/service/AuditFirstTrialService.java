package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
public interface AuditFirstTrialService {    
	
    public Message receiveTask(String userName);
	
    public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom);
	
    public Message searchDetail(Long id, String userName);
	
    public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest);
	
    public Message searchDetailResult(Long id, String userName);
   

}
