package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditTaskCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@Service("auditTaskService")
public class AuditTaskServiceImpl implements AuditTaskService {

	@Autowired
    private AuditTaskCore auditTaskCore;
    
    @Override
	public Message insertTask(AuditTaskEntity auditTaskEntity){
		return auditTaskCore.insertTask(auditTaskEntity);
	}
	
	@Override
	public Message<List<AuditTaskEntity>> selectByTask(AuditTaskEntity auditTaskEntity) {
		return auditTaskCore.selectByTask(auditTaskEntity);
	}
	
	@Override
	public Message updateTask(AuditTaskEntity auditTaskEntity) {
		return auditTaskCore.updateTask(auditTaskEntity);
	}
    
	@Override
	public Message<AuditTaskEntity> queryTaskByPid(String pid) {
		return auditTaskCore.queryTaskByPid(pid);
	}

}

