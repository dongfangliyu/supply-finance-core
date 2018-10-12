package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditOrderInfoService;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@Service("auditOrderInfoService")
public class AuditOrderInfoServiceImpl implements AuditOrderInfoService {

	@Autowired
    private AuditOrderInfoCore auditOrderInfoCore;
    
    @Override
	public Message insertOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity){
		return auditOrderInfoCore.insertOrderInfo(auditOrderInfoEntity);
	}
	
	@Override
	public Message<List<AuditOrderInfoEntity>> selectByOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity) {
		return auditOrderInfoCore.selectByOrderInfo(auditOrderInfoEntity);
	}
	
	@Override
	public Message updateOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity) {
		return auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
	}
    
	@Override
	public Message<AuditOrderInfoEntity> queryOrderInfoByPid(String pid) {
		return auditOrderInfoCore.queryOrderInfoByPid(pid);
	}

	@Override
	public Message<Integer> selectFristCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectFristCount(auditOrderInfoFrom);
	}

	@Override
	public Message<List<AuditOrderInfoEntity>> selectFristList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectFristList(auditOrderInfoFrom);
	}

	@Override
	public Message<Integer> selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectSecondCount(auditOrderInfoFrom);
	}

	@Override
	public Message<List<AuditOrderInfoEntity>> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectSecondList(auditOrderInfoFrom);
	}

	@Override
	public Message<Integer> selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectThirdCount(auditOrderInfoFrom);
	}

	@Override
	public Message<List<AuditOrderInfoEntity>> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditOrderInfoCore.selectThirdList(auditOrderInfoFrom);
	}

}

