package cn.fintecher.supply.finance.loan.manager.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class RegisterUserInfoEntity implements Serializable {
    
	/**
	*id
	*/
    private Long pid;
	/**
	*企业名称
	*/
    private String name;
	/**
	*1:营业执照注册号  2:统一社会信用代码
	*/
    private String type;
	/**
	*营业执照注册号
	*/
    private String enpsLicense;
	/**
	*统一社会信用代码
	*/
    private String enpsCredit;
	/**
	*实际控制人身份  1:法定代表人 2:非法定代表人
	*/
    private String controller;
	/**
	*填写人身份 1:法定代表人  2:代理人
	*/
    private String fillPersion;
	/**
	*成立时间
	*/
    private Date establishTime;
	/**
	*注册资本金
	*/
    private String registeredPrincipal;
	/**
	*实缴注册资本金
	*/
    private String realRegisteredPrincipal;
	/**
	*注册（登记）地址详细地址
	*/
    private String registeredAddressDetail;
	/**
	*实际经营地址详细地址
	*/
    private String operatingAddressDetail;
	/**
	*经营范围
	*/
    private String scopeOperation;
	/**
	*企业贷款卡编号
	*/
    private String loanNum;
	/**
	*状态:  -1:注册认证失败 0:注册信息未提交 1注册待审核 2:到账金额待认证 3:授信审核通过
	*/
    private String state;
	/**
	*提交授信申请时间
	*/
    private Date submitTime;
	/**
	*申请授信编号
	*/
    private String creditNumber;
	/**
	*注册(登记)地（省）
	*/
    private Long registeredAddressProvince;
	/**
	*注册(登记)地（省）名称
	*/
    private String registeredAddressProvinceName;
	/**
	*注册(登记)地（市）
	*/
    private Long registeredAddressCity;
	/**
	*注册(登记)地（市）名称
	*/
    private String registeredAddressCityName;
	/**
	*注册(登记)地（县、区）
	*/
    private Long registeredAddressCounty;
	/**
	*注册(登记)地（县、区）名称
	*/
    private String registeredAddressCountyName;
	/**
	*实际营业地址(省)
	*/
    private Long operatingAddressProvince;
	/**
	*实际营业地址(省)名称
	*/
    private String operatingAddressProvinceName;
	/**
	*实际营业地址(市)
	*/
    private Long operatingAddressCity;
	/**
	*实际营业地址(市)名称
	*/
    private String operatingAddressCityName;
	/**
	*实际营业地址(县、区)
	*/
    private Long operatingAddressCounty;
	/**
	*实际营业地址(县、区)名称
	*/
    private String operatingAddressCountyName;
	/**
	*到账金额
	*/
    private Long accountAmount;
	/**
	*到账金额尝试次数
	*/
    private Long attemptsNum;
	/**
	*上游供应商规模
	*/
    private String upstreamSize;
	/**
	*下游经销商规模
	*/
    private String downstreamSize;
	/**
	*法人姓名
	*/
    private String legalName;
	/**
	*法人手机号码
	*/
    private String legalPhone;
	/**
	*法人证件类型 1:身份证 2: 港澳通行证 3: 其他
	*/
    private String legalCardType;
	/**
	*法人证件号码
	*/
    private String legalCardNum;
	/**
	*法人性别
	*/
    private String legalSex;
	/**
	*法人归属地
	*/
    private String legalAddress;
	/**
	*代理人姓名
	*/
    private String agentName;
	/**
	*代理人手机号码
	*/
    private String agentPhone;
	/**
	*代理人证件类型 1:身份证 2: 港澳通行证 3: 其他
	*/
    private String agentCardType;
	/**
	*代理人证件号码
	*/
    private String agentCardNum;
	/**
	*代理人性别
	*/
    private String agentSex;
	/**
	*代理人邮箱
	*/
    private String agentEmail;
	/**
	*开户行
	*/
    private String bankOpen;
	/**
	*支行
	*/
    private String bankBranch;
	/**
	*银行卡号
	*/
    private String bankCard;
	/**
	*静默注册生成的code码  作为唯一标示
	*/
    private String registerCode;
	/**
	*打款时间
	*/
    private Date paymentTime;
	/**
	*认证链接发送时间
	*/
    private Date urlTime;
	/**
	*认证通过时间
	*/
    private Date successTime;
	/**
	*预计打款时间
	*/
    private Date moneyTime;
	/**
	*创建人账号
	*/
    private String createBy;
	/**
	*创建时间
	*/
    private Date createAt;
	/**
	*更新人账号
	*/
    private String updateBy;
	/**
	*更新时间
	*/
    private Date updateAt;
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
    private String status;
	/**
	*备注
	*/
    private String remark;
}

