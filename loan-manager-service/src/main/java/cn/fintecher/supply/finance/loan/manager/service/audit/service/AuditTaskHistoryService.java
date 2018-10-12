package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
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
	Message insertTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 查询审批历史
	 * @param quotaApply
	 * @return
	 */
	Message<List<AuditTaskHistoryEntity>> selectByTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 修改审批历史
	 * @param quotaApply
	 */
	Message updateTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	/**
	 * 根据主键查询审批历史
	 * @param quotaApply
	 */
	Message<AuditTaskHistoryEntity> queryTaskHistoryByPid(String pid);
 
   


   

}