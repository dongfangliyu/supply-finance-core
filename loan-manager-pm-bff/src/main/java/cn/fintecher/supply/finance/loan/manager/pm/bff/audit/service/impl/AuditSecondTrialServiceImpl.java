package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.core.AuditSecondTrialCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSecondTrialService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 10:01:21
 */
@Service("auditSecondTrialService")
public class AuditSecondTrialServiceImpl implements AuditSecondTrialService {

	@Autowired
	private AuditSecondTrialCore auditSecondTrialCore;

	public Message receiveTask(String userName){
		return auditSecondTrialCore.receiveTask(userName);
	};

	public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom){
		return auditSecondTrialCore.searchOrderList(auditOrderInfoFrom);
	};

	public Message searchDetail(Long id, String userName){
		return auditSecondTrialCore.searchDetail(id,userName);
	};

	public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest){
		return auditSecondTrialCore.submitContnet(auditSumbitContentRequest);
	};

	public Message searchDetailResult(Long id, String userName){
		return auditSecondTrialCore.searchDetailResult(id,userName);
	};

}
