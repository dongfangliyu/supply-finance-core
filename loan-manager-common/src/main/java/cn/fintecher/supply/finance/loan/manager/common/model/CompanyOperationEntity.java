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
public class CompanyOperationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*人员id
	*/
	@ApiModelProperty(value="人员id")
	private Long pid;
	
	/**
	*人员姓名
	*/
	@ApiModelProperty(value="人员姓名")
	private String name;
	
	/**
	*手机号码
	*/
	@ApiModelProperty(value="手机号码")
	private String phone;
	
	/**
	*证件类型 1:身份证 2: 港澳通行证 3: 其他
	*/
	@ApiModelProperty(value="证件类型 1:身份证 2: 港澳通行证 3: 其他")
	private String cardType;
	
	/**
	*证件号码
	*/
	@ApiModelProperty(value="证件号码")
	private String cardNum;
	
	/**
	*性别
	*/
	@ApiModelProperty(value="性别")
	private String sex;
	
	/**
	*电子邮箱
	*/
	@ApiModelProperty(value="电子邮箱")
	private String mail;
	
	/**
	*归属地
	*/
	@ApiModelProperty(value="归属地")
	private String address;
	
	/**
	*企业id
	*/
	@ApiModelProperty(value="企业id")
	private Long enpsId;
	
	/**
	*agent代理人、legal法人等
	*/
	@ApiModelProperty(value="agent代理人、legal法人等")
	private String type;
	
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

