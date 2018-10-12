package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

/**
 * 审批交易订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
public interface AuditOrderInfoService{

    /**
	 * 新增审批交易订单
	 * @param 
	 * @return
	 */
	Message insertOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 查询审批交易订单
	 * @param quotaApply
	 * @return
	 */
	Message<List<AuditOrderInfoEntity>> selectByOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 修改审批交易订单
	 * @param quotaApply
	 */
	Message updateOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 根据主键查询审批交易订单
	 * @param quotaApply
	 */
	Message<AuditOrderInfoEntity> queryOrderInfoByPid(String pid);

	/**
	 * 初审列表查询总数
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<Integer> selectFristCount(AuditOrderInfoFrom auditOrderInfoFrom);

	/**
	 * 初审列表查询list
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<List<AuditOrderInfoEntity>> selectFristList(AuditOrderInfoFrom auditOrderInfoFrom);
	
	/**
	 * 初审列表查询总数
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<Integer> selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom);
	
	/**
	 * 初审列表查询list
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<List<AuditOrderInfoEntity>> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom);
	
	/**
	 * 初审列表查询总数
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<Integer> selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom);
	
	/**
	 * 初审列表查询list
	 * @param auditOrderInfoFrom
	 * @return
	 */
	Message<List<AuditOrderInfoEntity>> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom);
 
   


   

}