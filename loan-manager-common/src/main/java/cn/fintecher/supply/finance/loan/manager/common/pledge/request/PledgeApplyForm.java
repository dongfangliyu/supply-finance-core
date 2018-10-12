package cn.fintecher.supply.finance.loan.manager.common.pledge.request;

import java.io.Serializable;
import java.util.Date;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PledgeApplyForm extends PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	*供货商名称
	*/
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
	/**
	*申请编号
	*/
	@ApiModelProperty(value="仓单编号")
	private String applyNumber;
	
	/**
	*品牌
	*/
	@ApiModelProperty(value="品牌")
	private String productBrand;
	
	/**
	*品名
	*/
	@ApiModelProperty(value="品名")
	private String productType;
	
	/**
	*申请时间
	*/
	@ApiModelProperty(value="申请开始时间")
	private String pledgeApplyStratTime;
	
	/**
	 *申请入库结束时间
	 */
	@ApiModelProperty(value="申请结束时间")
	private String pledgeApplyEndTime;
	
	/**
	*申请通过开始时间
	*/
	@ApiModelProperty(value="申请通过开始时间")
	private String pledgePassStartTime;
	
	/**
	 *申请通过结束时间
	 */
	@ApiModelProperty(value="申请通过结束时间")
	private String pledgePassEndTime;
	
	/**
	*申请不通过开始时间
	*/
	@ApiModelProperty(value="申请不通过开始时间")
	private String pledgeNoPassStartTime;
	
	/**
	 *申请不通过结束时间
	 */
	@ApiModelProperty(value="申请不通过结束时间")
	private String pledgeNoPassEndTime;
	
	/**
	*状态 1：未申请,2:申请中,3:申请通过,4:申请不通过
	*/
	@ApiModelProperty(value="状态 1：未申请,2:申请中,3:申请通过,4:申请不通过")
	private String state;
	
	/**
	 * 当前登录人
	 */
	@ApiModelProperty(value="当前登录人",hidden = true)
	private String userName;
	
	/**
	*企业id  来源 (企业信息表)
	*/
	@ApiModelProperty(value="企业id",hidden = true)
	private Long companyId;


	
}
