package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.util.Date;


/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Data
public class ProFileEntity implements java.io.Serializable {
    
	/**
	*产品合同文件
	*/
    private Long pid;
	/**
	*文件名称
	*/
    private String fileName;
	/**
	*文件类型   取自字典表相关code
	*/
    private String category;
	/**
	*完整路径
	*/
    private String fullPath;
	/**
	*文件保存地址
	*/
    private String path;
	/**
	*组
	*/
    private String fileGroup;
	/**
	*资源拥有者 resource_code
	*/
    private String ownerId;
	/**
	*文件后缀
	*/
    private String fileSuffix;
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

