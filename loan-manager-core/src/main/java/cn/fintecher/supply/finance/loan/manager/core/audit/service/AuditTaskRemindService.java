package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;

import java.util.List;

/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:19
 */
public interface AuditTaskRemindService{

    /**
	 * 新增
	 * @param 
	 * @return
	 */
	Integer insertTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	List<AuditTaskRemindEntity> selectByTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	/**
	 * 修改
	 * @param 
	 */
	Integer updateTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	/**
	 * 根据主键查询
	 * @param 
	 * @return
	 */
	AuditTaskRemindEntity queryTaskRemindByPid(String pid);

    int searchRemindCount(AuditTaskRemindForm auditTaskRemindForm);

	List<AuditTaskRemindEntity> searchRemind(AuditTaskRemindForm auditTaskRemindForm);

    List<AuditTaskRemindEntity> selectAllRemind();
}
