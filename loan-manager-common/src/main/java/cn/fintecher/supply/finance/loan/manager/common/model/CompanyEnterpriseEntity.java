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
public class CompanyEnterpriseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;

	/**
	*企业id
	*/
	@ApiModelProperty(value="企业id")
	private Long pid;
	
	/**
	*企业名称
	*/
	@ApiModelProperty(value="企业名称")
	private String name;
	
	/**
	*1:营业执照注册号  2:统一社会信用代码
	*/
	@ApiModelProperty(value="1:营业执照注册号  2:统一社会信用代码")
	private String type;
	
	/**
	*营业执照注册号
	*/
	@ApiModelProperty(value="营业执照注册号")
	private String enpsLicense;
	
	/**
	*统一社会信用代码
	*/
	@ApiModelProperty(value="统一社会信用代码")
	private String enpsCredit;
	
	/**
	*法定代表人归属地
	*/
	@ApiModelProperty(value="法定代表人归属地")
	private String legalAddress;
	
	/**
	*实际控制人身份  1:法定代表人 2:非法定代表人
	*/
	@ApiModelProperty(value="实际控制人身份  1:法定代表人 2:非法定代表人")
	private String controller;
	
	/**
	*填写人身份 1:法定代表人  2:代理人
	*/
	@ApiModelProperty(value="填写人身份 1:法定代表人  2:代理人")
	private String fillPersion;
	
	/**
	*成立时间
	*/
	@ApiModelProperty(value="成立时间")
	private Date establishTime;
	
	/**
	*注册资本金
	*/
	@ApiModelProperty(value="注册资本金")
	private String registeredPrincipal;
	
	/**
	*实缴注册资本金
	*/
	@ApiModelProperty(value="实缴注册资本金")
	private String realRegisteredPrincipal;
	
	/**
	*注册（登记）地址详细地址
	*/
	@ApiModelProperty(value="注册（登记）地址详细地址")
	private String registeredAddressDetail;
	
	/**
	*实际经营地址详细地址
	*/
	@ApiModelProperty(value="实际经营地址详细地址")
	private String operatingAddressDetail;
	
	/**
	*企业贷款卡编号
	*/
	@ApiModelProperty(value="企业贷款卡编号")
	private String loanNum;
	
	/**
	*状态
	*/
	@ApiModelProperty(value="状态")
	private String state;
	
	/**
	*提交授信申请时间
	*/
	@ApiModelProperty(value="提交授信申请时间")
	private Date submitTime;
	
	/**
	*申请授信编号
	*/
	@ApiModelProperty(value="申请授信编号")
	private String creditNumber;
	
	/**
	*授信状态 0未开始 1上传财务  2完善影像材料 3申请授信
	*/
	@ApiModelProperty(value="授信状态 0未开始 1上传财务  2完善影像材料 3申请授信")
	private Long creditStatus;
	
	/**
	*授信金额
	*/
	@ApiModelProperty(value="授信金额")
	private Long creditPrice;
	
	/**
	*认证链接发送时间
	*/
	@ApiModelProperty(value="认证链接发送时间")
	private Date urlTime;
	
	/**
	*认证通过时间
	*/
	@ApiModelProperty(value="认证通过时间")
	private Date successTime;
	
	/**
	*1：核心企业；2供应商
	*/
	@ApiModelProperty(value="1：核心企业；2供应商")
	private String enterpriseType;
	
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
	
	/**
	*添加黑名单时间
	*/
	@ApiModelProperty(value="添加黑名单时间")
	private Date blacklistTime;
	
	/**
	*默认 0 ，黑名单 1，
	*/
	@ApiModelProperty(value="默认 0 ，黑名单 1，")
	private String blacklist;
	

}

