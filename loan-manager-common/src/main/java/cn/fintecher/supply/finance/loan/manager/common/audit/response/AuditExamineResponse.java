package cn.fintecher.supply.finance.loan.manager.common.audit.response;

import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuditExamineResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 待处理任务
	 */
	@ApiModelProperty(value = "待处理任务")
	private String waitNum ;
	/**
	 * 已处理任务
	 */
	@ApiModelProperty(value = "可领取任务")
	private String alreadyNum  ;
	
	/**
	 * 任务列表
	 */
	List<AuditOrderInfoEntity> list;
}
