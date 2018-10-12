package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 审批历史
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@Mapper
public interface AuditTaskHistoryDao{
	
	Integer insertTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	List<AuditTaskHistoryEntity> selectByTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	Integer updateTaskHistory(AuditTaskHistoryEntity auditTaskHistoryEntity);
	
	AuditTaskHistoryEntity queryTaskHistoryByPid(String pid);
}
