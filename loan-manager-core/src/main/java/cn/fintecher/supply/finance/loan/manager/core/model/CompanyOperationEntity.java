package cn.fintecher.supply.finance.loan.manager.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class CompanyOperationEntity implements Serializable {
    
    
	/**
	*人员id
	*/
    private Long pid;
	/**
	*人员姓名
	*/
    private String name;
	/**
	*手机号码
	*/
    private String phone;
	/**
	*证件类型 1:身份证 2: 港澳通行证 3: 其他
	*/
    private String cardType;
	/**
	*证件号码
	*/
    private String cardNum;
	/**
	*性别
	*/
    private String sex;
	/**
	*电子邮箱
	*/
    private String mail;
	/**
	*归属地
	*/
    private String address;
	/**
	*企业id
	*/
    private Long enpsId;
	/**
	*agent代理人、legal法人等
	*/
    private String type;
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

