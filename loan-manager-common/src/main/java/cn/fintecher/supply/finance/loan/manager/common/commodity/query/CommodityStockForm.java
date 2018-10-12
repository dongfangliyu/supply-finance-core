package cn.fintecher.supply.finance.loan.manager.common.commodity.query;

import java.io.Serializable;
import java.util.Date;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommodityStockForm extends PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	*供货商名称
	*/
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
	/**
	*申请编号
	*/
	@ApiModelProperty(value="申请编号")
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
	*申请入库开始时间
	*/
	@ApiModelProperty(value="申请入库开始时间")
	private String applyStockTimeStart;
	
	/**
	 *申请入库结束时间
	 */
	@ApiModelProperty(value="申请入库结束时间")
	private String applyStockTimeEnd;
	
	/**
	*实际入库开始日期
	*/
	@ApiModelProperty(value="实际入库开始日期")
	private String actualStockDateStart;
	
	/**
	 *实际入库结束日期
	 */
	@ApiModelProperty(value="实际入库结束日期")
	private String actualStockDateEnd;
	
	/**
	*状态 1:已入库  0：未入库
	*/
	@ApiModelProperty(value="状态 1:已入库  0：未入库")
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


	@ApiModelProperty(value="企业名称（模糊查询）")
	private String companyName;
}
