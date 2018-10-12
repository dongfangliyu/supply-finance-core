package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class ProCategoryEntity implements Serializable {
    
	/**
	*产品类型
	*/
    private Long pid;
	/**
	*产品类型名称
	*/
    private String name;
	/**
	 * 父id
	 */
    private Long parentId;
	/**
	 * 值
	 */
    private String value;
	/**
	 * 字典code
	 */
    private String category;
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

