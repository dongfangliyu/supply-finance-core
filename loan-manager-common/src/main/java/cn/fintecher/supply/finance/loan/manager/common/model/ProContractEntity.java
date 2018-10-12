package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class ProContractEntity implements java.io.Serializable {
    
	/**
	*产品合同id
	*/
    private Long pid;

	/**
	 *合同名称
	 */
	private String name;

	/**
	*合同文本代号
	*/
    private String contractNo;
	/**
	*产品类型
	*/
    private String category;
	/**
	*产品类型名称
	*/
    private String categoryName;
	/**
	*状态
	*/
    private String state;
	/**
	*唯一资源码
	*/
    private String resourceCode;
	/**
	 * 产品编号
	 */
	private String productNo;
	/**
	 * 业务类型
	 */
	private String businessCategory;
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

