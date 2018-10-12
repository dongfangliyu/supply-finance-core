package cn.fintecher.supply.finance.loan.manager.common.commodity.query;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
public class CommodityStockInfoSubmit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*供货商名称
	*/
	@NotBlank(message = "供货商名称不能为空")
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
	/**
	*品牌
	*/
	@NotBlank(message = "品牌不能为空")
	@ApiModelProperty(value="品牌")
	private String productBrand;
	
	/**
	*品名
	*/
	@NotBlank(message = "品名不能为空")
	@ApiModelProperty(value="品名")
	private String productType;
	
	/**
	*数量
	*/
	@NotNull(message="数量不能为空")
	@ApiModelProperty(value="数量")
	private BigDecimal productNumber;
	
	/**
	*单位
	*/
	@NotNull(message = "单位不能为空")
	@ApiModelProperty(value="单位")
	private Long productUnit;
	
	/**
	*单价
	*/
	@NotNull(message = "单价不能为空")
	@ApiModelProperty(value="单价")
	private BigDecimal unitPrice;
	
	/**
	*总价值
	*/
	@NotNull(message = "总价值不能为空")
	@ApiModelProperty(value="总价值")
	private BigDecimal totalPrice;
	
	/**
	*产地省份
	*/
	@NotBlank(message = "产地省份不能为空")
	@ApiModelProperty(value="产地省份")
	private String productAddressProvince;
	
	/**
	*产地市
	*/
	@NotBlank(message = "产地市不能为空")
	@ApiModelProperty(value="产地市")
	private String productAddressCity;
	
	/**
	*型号
	*/
	@NotBlank(message = "型号不能为空")
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
	*颜色
	*/
	@ApiModelProperty(value="颜色")
	private String productColour;
	
	/**
	 * 当前登录人
	 */
	@ApiModelProperty(value="当前登录人",hidden = true)
	private String userName;
	

}