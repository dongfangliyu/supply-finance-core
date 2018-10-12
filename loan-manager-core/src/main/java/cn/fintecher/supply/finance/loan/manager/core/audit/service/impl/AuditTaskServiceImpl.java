package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditTaskDao;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@Service("auditTaskService")
public class AuditTaskServiceImpl implements AuditTaskService {

   	@Autowired
	private AuditTaskDao audittaskDao;
	
	@Override
	public Integer insertTask(AuditTaskEntity auditTaskEntity){
		 audittaskDao.insertTask(auditTaskEntity);
		 return auditTaskEntity.getPid().intValue();
	}
	
	@Override
	public List<AuditTaskEntity> selectByTask(AuditTaskEntity auditTaskEntity) {
		return audittaskDao.selectByTask(auditTaskEntity);
	}
	
	@Override
	public Integer updateTask(AuditTaskEntity auditTaskEntity) {
		// TODO Auto-generated method stub
		return audittaskDao.updateTask(auditTaskEntity);
	}
	
	@Override
	public AuditTaskEntity queryTaskByPid(String pid) {
		// TODO Auto-generated method stub
		return audittaskDao.queryTaskByPid(pid);
	}

	@Override
	public Integer countConfirmingTaskWaitNum(Integer userId,Integer node) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("node",node);
		return audittaskDao.countConfirmingTaskWaitNum(map);
	}

	@Override
	public Integer countConfirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest) {
		return audittaskDao.countConfirmingApprovalList( confirmingStockInfoApprovalResquest);
	}

	@Override
	public List<ConfirmingStockInfoListResponse> confirmingApprovalList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest) {
		return audittaskDao.confirmingApprovalList(confirmingStockInfoApprovalResquest);
	}
}
