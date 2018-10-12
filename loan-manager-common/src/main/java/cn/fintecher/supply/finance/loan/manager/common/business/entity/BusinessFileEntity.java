package  cn.fintecher.supply.finance.loan.manager.common.business.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Data
public class BusinessFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;
	
	/**
	*文件名称
	*/
	@ApiModelProperty(value="文件名称")
	private String fileName;
	
	/**
	*文件类型   取自字典表相关code
	*/
	@ApiModelProperty(value="文件类型   取自字典表相关code")
	private String category;
	
	/**
	*完整路径
	*/
	@ApiModelProperty(value="完整路径")
	private String fullPath;
	
	/**
	*文件保存地址
	*/
	@ApiModelProperty(value="文件保存地址")
	private String path;
	
	/**
	*组
	*/
	@ApiModelProperty(value="组")
	private String group;
	
	/**
	*资源拥有者 order_code
	*/
	@ApiModelProperty(value="资源拥有者 order_code")
	private String ownerId;
	
	/**
	 *质押入库id
	 */
	@ApiModelProperty(value="质押入库id")
	private Long commodityId;
	
	/**
	 *确认状态   0未确认  1已确认
	 */
	@ApiModelProperty(value="确认状态   0未确认  1已确认")
	private String confirm;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="")
	private Date updateAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
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