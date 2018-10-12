package cn.fintecher.supply.finance.loan.manager.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28
 */
@Data
public class ProProductEntity implements Serializable {

	/**
	*产品pid
	*/
    private Long pid;
	/**
	*产品名称
	*/
	@ApiModelProperty(value = "产品名称")
    private String name;
	/**
	*产品类型
	*/
	@ApiModelProperty(value = "产品类型")
    private String category;
	/**
	*产品状态  0禁用  1启用
	*/
	@ApiModelProperty(value = "产品状态")
    private String state;
	/**
	*利率
	*/
	@ApiModelProperty(value = "产品利率")
    private Long interestRate;
	/**
	*返佣比率
	*/
	@ApiModelProperty(value = "返佣比率")
    private Long rebateRatio;
	/**
	*平台服务费率
	*/
	@ApiModelProperty(value = "平台服务费率")
    private Long platformServiceFee;
	/**
	 * 服务费率
	 */
	@ApiModelProperty(value = "服务费率")
    private Long serviceFee;
	/**
	*产品代号
	*/
	@ApiModelProperty(value = "产品代号")
    private String productNo;
	/**
	 * 业务类型
	 */
	@ApiModelProperty(value = "业务类型")
	private String businessCategory;
	/**
	 * 核心企业id
	 */
	@ApiModelProperty(value = "核心企业id")
	private String companyId;
	/**
	*创建人账号
	*/
    private String createBy;
	/**
	*创建时间
	*/
    private java.util.Date createAt;
	/**
	*更新人账号
	*/
    private String updateBy;
	/**
	*更新时间
	*/
    private java.util.Date updateAt;
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
	@ApiModelProperty(value = "状态")
    private String status;
	/**
	*备注
	*/
	@ApiModelProperty(value = "备注")
    private String remark;

}

