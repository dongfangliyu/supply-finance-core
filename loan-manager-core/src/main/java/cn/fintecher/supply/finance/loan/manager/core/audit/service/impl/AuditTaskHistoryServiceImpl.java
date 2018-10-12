package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditTaskHistoryDao;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskHistoryService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@Service("auditTaskHistoryService")
public class AuditTaskHistoryServiceImpl implements AuditTaskHistoryService {

   	@Autowired
	private AuditTaskHistoryDao audittaskHistoryDao;
	
	@Override
	public Integer insertTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity){
		 audittaskHistoryDao.insertTaskHistory(auditTaskHistoryEntity);
		 return auditTaskHistoryEntity.getPid().intValue();
	}
	
	@Override
	public List<AuditTaskHistoryEntity> selectByTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity) {
		return audittaskHistoryDao.selectByTaskHistory(auditTaskHistoryEntity);
	}
	
	@Override
	public Integer updateTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity) {
		// TODO Auto-generated method stub
		return audittaskHistoryDao.updateTaskHistory(auditTaskHistoryEntity);
	}
	
	@Override
	public AuditTaskHistoryEntity queryTaskHistoryByPid(String pid) {
		// TODO Auto-generated method stub
		return audittaskHistoryDao.queryTaskHistoryByPid(pid);
	}

}
