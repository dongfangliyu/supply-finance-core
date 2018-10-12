package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PledgeTrialExamineResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 待处理任务
	 */
	@ApiModelProperty(value="待处理任务")
	private String waitNum ;
	/**
	 * 已处理任务
	 */
	@ApiModelProperty(value="待处理任务")
	private String alreadyNum  ;
	
	/**
	 * 任务列表
	 */
	@ApiModelProperty(value="任务列表")
	List<PledgeTrialListResponse> list;
}
