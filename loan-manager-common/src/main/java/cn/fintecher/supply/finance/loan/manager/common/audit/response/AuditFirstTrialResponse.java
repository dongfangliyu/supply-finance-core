package cn.fintecher.supply.finance.loan.manager.common.audit.response;

import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import lombok.Data;

@Data
public class AuditFirstTrialResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * order_info信息
	 */
	private AuditOrderInfoEntity auditOrderInfo;
	/**
	 * 订单信息
	 */
	private BusinessOrderEntity businessOrder;
	
	/**
	 * 供应商文件信息
	 */
	private List<BusinessFileEntity> supplist;
	
	/**
	 * 核心企业文件信息
	 */
	private List<BusinessFileEntity> certlist;
	
	/**
	 * 审批历史
	 */
	private List<AuditTaskHistoryEntity> resultlist;
	
	/**
	 * 审批结果
	 * 
	 */
	private AuditTaskHistoryEntity taskResult;
	
	/**
	 * 供应商联系人信息
	 */
	private CompanyOperationEntity suppOperation;
	
	/**
	 * 核心企业联系人信息
	 */
	private CompanyOperationEntity certOperation;

	 @JsonIgnore
	private Integer flag;
	/**
	 * 担保审核意见
	 */
	private AuditResultEntity auditResultEntity;
	
	/**
	 * 账款时间
	 */
	private Integer day;
}
