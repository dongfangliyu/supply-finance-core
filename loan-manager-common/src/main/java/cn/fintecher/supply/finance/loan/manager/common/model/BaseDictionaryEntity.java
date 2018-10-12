package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class BaseDictionaryEntity implements Serializable {
    
    
	/**
	*id
	*/
    private Long pid;
	/**
	*名称  营业执照 法人、代理人身份证等
	*/
    private String name;
	/**
	*code值    businessLicense() idCare（法人、代理人身份证）等
	*/
    private String code;
	/**
	*值
	*/
    private String value;
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

