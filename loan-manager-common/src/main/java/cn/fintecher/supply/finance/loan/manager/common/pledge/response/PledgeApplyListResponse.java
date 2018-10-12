package cn.fintecher.supply.finance.loan.manager.common.pledge.response;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 * 入库信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Data
public class PledgeApplyListResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	
	/**
	*申请编号
	*/
	@ApiModelProperty(value="仓单编号")
	private String applyNumber;
	
	/**
	*供货商名称
	*/
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
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
	*型号
	*/
	@ApiModelProperty(value="型号")
	private String productModel;
	
	/**
	*数量
	*/
	@ApiModelProperty(value="数量")
	private BigDecimal productNumber;
	
	/**
	*单位
	*/
	@ApiModelProperty(value="数量单位")
	private String productUnit;
	
	/**
	*单价
	*/
	@ApiModelProperty(value="单价")
	private BigDecimal unitPrice;
	
	/**
	 *单价单位
	 */
	@ApiModelProperty(value="单价单位")
	private String unitPriceUnit;
	
	/**
	*总价值
	*/
	@ApiModelProperty(value="总价值")
	private BigDecimal totalPrice;
	
	
	/**
	*申请入库时间
	*/
	@ApiModelProperty(value="申请时间")
	private String pledgeApplyTime;
	
	/**
	*实际入库日期
	*/
	@ApiModelProperty(value="申请通过/未通过时间")
	private String pledgeNoPassTime;
	
	/**
	*状态 1：未申请,2:申请中,3:申请通过,4:申请不通过
	*/
	@ApiModelProperty(value="状态 1：未申请,2:申请中,3:申请通过,4:申请不通过")
	private String state;
	

}