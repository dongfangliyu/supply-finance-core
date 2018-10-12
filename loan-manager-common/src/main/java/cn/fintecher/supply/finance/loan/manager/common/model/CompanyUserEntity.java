package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class CompanyUserEntity implements Serializable {
    
	/**
	*用户id
	*/
    private Long pid;
	/**
	*企业名称
	*/
    private String name;
	/**
	*手机号
	*/
    private String userName;
	/**
	*密码
	*/
    private String password;
	/**
	*企业id
	*/
    private Long enterpriseId;
	/**
	*1：核心企业；2供应商
	*/
    private String type;
	/**
	*盐
	*/
    private String salt;
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

