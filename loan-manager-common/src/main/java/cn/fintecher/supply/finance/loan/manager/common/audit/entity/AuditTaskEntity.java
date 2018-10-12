package  cn.fintecher.supply.finance.loan.manager.common.audit.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 审批交易订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:54:43
 */
@Data
public class AuditTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;
	
	/**
	*订单信息id
	*/
	@ApiModelProperty(value="订单信息id")
	private Long orderInfoId;
	
	/**
	*拥有者id
	*/
	@ApiModelProperty(value="拥有者id")
	private Long owerId;
	
	/**
	*审批节点 1 初审 2复审 3终审
	*/
	@ApiModelProperty(value="审批节点 1 初审 2复审 3终审")
	private String node;
	
	/**
	*类型 1反向保理 2 仓单质押
	*/
	@ApiModelProperty(value="类型 1反向保理 2 仓单质押")
	private String type;
	
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