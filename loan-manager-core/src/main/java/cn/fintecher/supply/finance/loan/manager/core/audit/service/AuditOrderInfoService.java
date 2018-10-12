package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;

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
	Integer insertOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 查询审批交易订单
	 * @param 
	 * @return
	 */
	List<AuditOrderInfoEntity> selectByOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 修改审批交易订单
	 * @param 
	 */
	Integer updateOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	/**
	 * 根据主键查询审批交易订单
	 * @param 
	 * @return
	 */
	AuditOrderInfoEntity queryOrderInfoByPid(String pid);

	Integer selectFristCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectFristList(AuditOrderInfoFrom auditOrderInfoFrom);

	Integer selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom);

	Integer selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom);

    List<AuditOrderInfoEntity> searchSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchSigningListCount(AuditSigningRequest auditSigningRequest);

	List<AuditOrderInfoEntity> getOverdueOrderInfo();

    List<AuditOrderInfoEntity> searchEnterSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchEnterSigningListCount(AuditSigningRequest auditSigningRequest);

    List<AuditOrderInfoEntity> searchPlatformSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchPlatformSigningListCount(AuditSigningRequest auditSigningRequest);

	int searchRemindListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchRemindList(AuditRemindForm auditRemindForm);

    int searchReviewListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchReviewList(AuditRemindForm auditRemindForm);

    List<AuditOrderInfoEntity> searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

	Integer searchSuppReviewListCount(AuditSuppReviewRequest auditSuppReviewRequest);

    int searchEntryListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchEntryList(AuditRemindForm auditRemindForm);
}
