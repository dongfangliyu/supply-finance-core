package cn.fintecher.supply.finance.loan.manager.common.commodity.response;


import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 提交入库信息
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Data
public class CommodityStockInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*申请编号
	*/
	@ApiModelProperty(value="申请编号")
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
	*数量
	*/
	@ApiModelProperty(value="数量")
	private BigDecimal productNumber;
	
	/**
	*单位
	*/
	@ApiModelProperty(value="单位")
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
	*产地省份
	*/
	@ApiModelProperty(value="产地省份")
	private String productAddressProvince;
	
	/**
	*产地市
	*/
	@ApiModelProperty(value="产地市")
	private String productAddressCity;
	
	/**
	*型号
	*/
	@ApiModelProperty(value="型号")
	private String productModel;
	
	/**
	*等级
	*/
	@ApiModelProperty(value="等级")
	private String productGrade;
	
	/**
	*质量标准
	*/
	@ApiModelProperty(value="质量标准")
	private String productStandard;
	
	/**
	*大小
	*/
	@ApiModelProperty(value="大小")
	private String productSize;
	
	/**
	*有效期开始时间
	*/
	@ApiModelProperty(value="有效期开始时间")
	private String effectiveStartTime;
	
	/**
	*有效期结束时间
	*/
	@ApiModelProperty(value="有效期结束时间")
	private String effectiveEndTime;
	
	/**
	*生产日期
	*/
	@ApiModelProperty(value="生产日期")
	private String productionDate;
	
	/**
	*申请入库时间
	*/
	@ApiModelProperty(value="申请入库时间")
	private String applyStockTime;
	
	/**
	*状态 1:已入库  0：未入库
	*/
	@ApiModelProperty(value="状态 1:已入库  0：未入库")
	private String state;
	
	/**
	*实际入库日期
	*/
	@ApiModelProperty(value="实际入库日期")
	private String actualStockDate;
	
	/**
	*颜色
	*/
	@ApiModelProperty(value="颜色")
	private String productColour;
	

}