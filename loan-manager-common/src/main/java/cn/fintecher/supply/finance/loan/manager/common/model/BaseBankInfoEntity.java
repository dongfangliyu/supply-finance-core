package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class BaseBankInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	*银行id
	*/
	@ApiModelProperty(value="银行id")
	private Long pid;
	
	/**
	*开户行
	*/
	@ApiModelProperty(value="开户行")
	private String bankOpen;
	
	/**
	*支行
	*/
	@ApiModelProperty(value="支行")
	private String bankBranch;
	
	/**
	*账户类型
	*/
	@ApiModelProperty(value="账户类型")
	private String accountType;
	
	/**
	*企业id,注册企业id等
	*/
	@ApiModelProperty(value="企业id,注册企业id等")
	private Long objectId;
	
	/**
	*0 核心企业  1 供应商  2签约审核
	*/
	@ApiModelProperty(value="0 核心企业  1 供应商  2签约审核")
	private String objectType;
	
	/**
	*户名
	*/
	@ApiModelProperty(value="户名")
	private String accountName;
	
	/**
	*账户用途（放款账户为1；还款账户为2）
	*/
	@ApiModelProperty(value="账户用途（放款账户为1；还款账户为2）")
	private String accountUse;
	
	/**
	*银行卡号
	*/
	@ApiModelProperty(value="银行卡号")
	private String bankCard;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*更新时间
	*/
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
	@ApiModelProperty(value="状态 DEL删除，STP停用，NOL正常")
	private String status;
	
	/**
	*备注
	*/
	@ApiModelProperty(value="备注")
	private String remark;
	
}

