package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:19
 */
@Mapper
public interface AuditTaskRemindDao{
	
	Integer insertTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	List<AuditTaskRemindEntity> selectByTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	Integer updateTaskRemind(AuditTaskRemindEntity auditTaskRemindEntity);
	
	AuditTaskRemindEntity queryTaskRemindByPid(String pid);

	int searchRemindCount(AuditTaskRemindForm auditTaskRemindForm);

    List<AuditTaskRemindEntity> searchRemind(AuditTaskRemindForm auditTaskRemindForm);

    List<AuditTaskRemindEntity> selectAllRemind();
}
