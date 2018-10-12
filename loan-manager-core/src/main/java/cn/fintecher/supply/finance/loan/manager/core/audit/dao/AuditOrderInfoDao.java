package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;

import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 审批交易订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@Mapper
public interface AuditOrderInfoDao{
	
	Integer insertOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	List<AuditOrderInfoEntity> selectByOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	Integer updateOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity);
	
	AuditOrderInfoEntity queryOrderInfoByPid(String pid);

	List<AuditOrderInfoEntity> searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm);

	int searchGuaranteeListCount(AuditGuaranteeListForm auditGuaranteeListForm);

	Integer selectFristCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectFristList(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

	int searchFrontGuaranteeListCount(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    List<AuditOrderInfoEntity> searchFinanceList(AuditFinanceForm auditFinanceForm);

	int searchFinanceListCount(AuditFinanceForm auditFinanceForm);

	Integer selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom);

	Integer selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom);

	List<AuditOrderInfoEntity> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom);

    int searchLoanListCount(AuditFinanceForm auditFinanceForm);

	List<AuditOrderInfoEntity> searchLoanList(AuditFinanceForm auditFinanceForm);

    List<AuditOrderInfoEntity> searchSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchSigningListCount(AuditSigningRequest auditSigningRequest);

	List<AuditOrderInfoEntity> getOverdueOrderInfo();

	List<AuditOrderInfoEntity> searchEnterSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchEnterSigningListCount(AuditSigningRequest auditSigningRequest);

    List<AuditOrderInfoEntity> searchPlatformSigningList(AuditSigningRequest auditSigningRequest);

	Integer searchPlatformSigningListCount(AuditSigningRequest auditSigningRequest);

	List<AuditOrderInfoEntity> searchSignList(AuditSigningListForm auditSigningListForm);

	int searchSignListCount(AuditSigningListForm auditSigningListForm);

	int searchRemindListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchRemindList(AuditRemindForm auditRemindForm);

    int searchReviewListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchReviewList(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

	int searchSuppReviewListCount(AuditSuppReviewRequest auditSuppReviewRequest);

    int searchEntryListCount(AuditRemindForm auditRemindForm);

	List<AuditOrderInfoEntity> searchEntryList(AuditRemindForm auditRemindForm);
}
