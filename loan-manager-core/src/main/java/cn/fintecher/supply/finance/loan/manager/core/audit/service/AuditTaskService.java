package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;

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
	Integer insertTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 查询审批交易订单
	 * @param 
	 * @return
	 */
	List<AuditTaskEntity> selectByTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 修改审批交易订单
	 * @param 
	 */
	Integer updateTask(AuditTaskEntity auditTaskEntity);
	
	/**
	 * 根据主键查询审批交易订单
	 * @param 
	 * @return
	 */
	AuditTaskEntity queryTaskByPid(String pid);

  	Integer countConfirmingTaskWaitNum(Integer userId,Integer node);

    Integer countConfirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);

	List<ConfirmingStockInfoListResponse> confirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);
}
