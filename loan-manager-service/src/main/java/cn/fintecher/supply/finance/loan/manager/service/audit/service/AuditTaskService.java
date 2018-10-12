package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

/**
 * 审批交易订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
public interface AuditTaskService{

    /**
	 * 新增审批交易订单
	 * @param 
	 * @return
	 */
	Message insertTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 查询审批交易订单
	 * @param quotaApply
	 * @return
	 */
	Message<List<AuditTaskEntity>> selectByTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 修改审批交易订单
	 * @param quotaApply
	 */
	Message updateTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 根据主键查询审批交易订单
	 * @param quotaApply
	 */
	Message<AuditTaskEntity> queryTaskByPid(String pid);
 
   


   

}