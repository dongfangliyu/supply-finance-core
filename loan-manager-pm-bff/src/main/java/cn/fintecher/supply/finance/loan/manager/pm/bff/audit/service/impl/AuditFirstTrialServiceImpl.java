package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.core.AuditFirstTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFirstTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@Service("auditFirstTrialService")
public class AuditFirstTrialServiceImpl implements AuditFirstTrialService {

	@Autowired
    private AuditFirstTrialCore auditFirstTrialCore;
	
    public Message receiveTask(String userName){
    	return auditFirstTrialCore.receiveTask(userName);
    };
	
    public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom){
    	return auditFirstTrialCore.searchOrderList(auditOrderInfoFrom);
    };
	
    public Message searchDetail(Long id, String userName){
    	return auditFirstTrialCore.searchDetail(id,userName);
    };
	
    public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest){
    	return auditFirstTrialCore.submitContnet(auditSumbitContentRequest);
    };
	
    public Message searchDetailResult(Long id, String userName){
    	return auditFirstTrialCore.searchDetailResult(id,userName);
    };

}
