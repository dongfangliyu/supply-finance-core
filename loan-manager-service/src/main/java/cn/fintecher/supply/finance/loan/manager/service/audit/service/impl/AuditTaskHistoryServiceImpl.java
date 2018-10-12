package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditTaskHistoryCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskHistoryService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@Service("auditTaskHistoryService")
public class AuditTaskHistoryServiceImpl implements AuditTaskHistoryService {

	@Autowired
    private AuditTaskHistoryCore auditTaskHistoryCore;
    
    @Override
	public Message insertTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity){
		return auditTaskHistoryCore.insertTaskHistory(auditTaskHistoryEntity);
	}
	
	@Override
	public Message<List<AuditTaskHistoryEntity>> selectByTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity) {
		return auditTaskHistoryCore.selectByTaskHistory(auditTaskHistoryEntity);
	}
	
	@Override
	public Message updateTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity) {
		return auditTaskHistoryCore.updateTaskHistory(auditTaskHistoryEntity);
	}
    
	@Override
	public Message<AuditTaskHistoryEntity> queryTaskHistoryByPid(String pid) {
		return auditTaskHistoryCore.queryTaskHistoryByPid(pid);
	}

}

