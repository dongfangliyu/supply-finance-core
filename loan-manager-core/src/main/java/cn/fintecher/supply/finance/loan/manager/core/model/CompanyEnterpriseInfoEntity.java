package cn.fintecher.supply.finance.loan.manager.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class CompanyEnterpriseInfoEntity implements Serializable {
    
	/**
	*唯一标记
	*/
    private Long pid;
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
	*企业id
	*/
    private String enterpriseId;
	/**
	*经营范围
	*/
    private String scopeOperation;
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

