package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import java.util.List;

/**
 * 审批历史
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
public interface AuditTaskHistoryService{

    /**
	 * 新增审批历史
	 * @param 
	 * @return
	 */
	Integer insertTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 查询审批历史
	 * @param 
	 * @return
	 */
	List<AuditTaskHistoryEntity> selectByTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 修改审批历史
	 * @param 
	 */
	Integer updateTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 根据主键查询审批历史
	 * @param 
	 * @return
	 */
	AuditTaskHistoryEntity queryTaskHistoryByPid(String pid);
}
