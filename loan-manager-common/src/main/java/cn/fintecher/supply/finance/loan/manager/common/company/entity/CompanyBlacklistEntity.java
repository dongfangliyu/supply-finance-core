package cn.fintecher.supply.finance.loan.manager.common.company.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 黑名单历史记录
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@Data
public class CompanyBlacklistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*id
	*/
	@ApiModelProperty(value="id")
	private Long pid;
	
	/**
	*企业id
	*/
	@ApiModelProperty(value="企业id")
	private Long companyId;
	
	/**
	*1:添加黑名单，2：移除黑名单
	*/
	@ApiModelProperty(value="1:添加黑名单，2：移除黑名单")
	private String event;
	
	/**
	*原因
	*/
	@ApiModelProperty(value="原因")
	private String causationInfo;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*更新时间
	*/
	@ApiModelProperty(value="更新时间")
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