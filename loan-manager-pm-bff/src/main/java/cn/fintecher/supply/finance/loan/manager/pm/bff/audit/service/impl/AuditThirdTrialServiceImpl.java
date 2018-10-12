package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.core.AuditThirdTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditThirdTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 13:35:36
 */
@Service("auditThirdTrialService")
public class AuditThirdTrialServiceImpl implements AuditThirdTrialService {

	@Autowired
    private AuditThirdTrialCore auditThirdTrialCore;
	
	public Message receiveTask(String userName){
		return auditThirdTrialCore.receiveTask(userName);
	};

	public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom){
		return auditThirdTrialCore.searchOrderList(auditOrderInfoFrom);
	};

	public Message searchDetail(Long id, String userName){
		return auditThirdTrialCore.searchDetail(id,userName);
	};

	public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest){
		return auditThirdTrialCore.submitContnet(auditSumbitContentRequest);
	};

	public Message searchDetailResult(Long id, String userName){
		return auditThirdTrialCore.searchDetailResult(id,userName);
	};

}
