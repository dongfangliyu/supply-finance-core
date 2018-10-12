package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditTaskRemindDao;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskRemindService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:19
 */
@Service("auditTaskRemindService")
public class AuditTaskRemindServiceImpl implements AuditTaskRemindService {

   	@Autowired
	private AuditTaskRemindDao audittaskRemindDao;
	
	@Override
	public Integer insertTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity){
		return audittaskRemindDao.insertTaskRemind(auditTaskRemindEntity);
	}
	
	@Override
	public List<AuditTaskRemindEntity> selectByTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity) {
		return audittaskRemindDao.selectByTaskRemind(auditTaskRemindEntity);
	}
	
	@Override
	public Integer updateTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity) {
		// TODO Auto-generated method stub
		return audittaskRemindDao.updateTaskRemind(auditTaskRemindEntity);
	}
	
	@Override
	public AuditTaskRemindEntity queryTaskRemindByPid(String pid) {
		// TODO Auto-generated method stub
		return audittaskRemindDao.queryTaskRemindByPid(pid);
	}

	@Override
	public int searchRemindCount(AuditTaskRemindForm auditTaskRemindForm) {
		return audittaskRemindDao.searchRemindCount(auditTaskRemindForm);
	}

	@Override
	public List<AuditTaskRemindEntity> searchRemind(AuditTaskRemindForm auditTaskRemindForm) {
		return audittaskRemindDao.searchRemind(auditTaskRemindForm);
	}

	@Override
	public List<AuditTaskRemindEntity> selectAllRemind() {
		return audittaskRemindDao.selectAllRemind();
	}

}
