package cn.fintecher.supply.finance.loan.manager.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class CompanyEnterpriseEntity implements Serializable {
    
	/**
	*企业id
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
	*法定代表人归属地
	*/
    private String legalAddress;
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
	*企业贷款卡编号
	*/
    private String loanNum;
	/**
	*状态
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
	*授信状态 0未开始 1上传财务  2完善影像材料 3申请授信
	*/
    private Long creditStatus;
	/**
	*授信金额
	*/
    private Long creditPrice;
	/**
	*认证链接发送时间
	*/
    private Date urlTime;
	/**
	*认证通过时间
	*/
    private Date successTime;
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

