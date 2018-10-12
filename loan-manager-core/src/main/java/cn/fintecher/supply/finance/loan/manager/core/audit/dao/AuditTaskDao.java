package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 审批交易订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@Mapper
public interface AuditTaskDao{
	
	Integer insertTask(AuditTaskEntity auditTaskEntity);
	
	List<AuditTaskEntity> selectByTask(AuditTaskEntity auditTaskEntity);
	
	Integer updateTask(AuditTaskEntity auditTaskEntity);
	
	AuditTaskEntity queryTaskByPid(String pid);

    Integer countConfirmingTaskWaitNum(Map<String,Object> map);

    Integer countConfirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);

	List<ConfirmingStockInfoListResponse> confirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);
}
