package  cn.fintecher.supply.finance.loan.manager.common.pledge.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 仓单质押申请信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@Data
public class PledgeApplyStockInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*来源 pledge_stock_info(入库信息表) 的pid 
	*/
	@ApiModelProperty(value="来源 pledge_stock_info(入库信息表) 的pid ")
	private Long pledgeId;
	
	/**
	*初核状态0待领取 1待初审 2拒绝 3取消 4初审通过
	*/
	@ApiModelProperty(value="初核状态0待领取 1待初审 2拒绝 3取消 4初审通过")
	private String applyFirstState;
	
	/**
	*复核状态： 0待领取 1待初审 2拒绝 3取消  4复审通过
	*/
	@ApiModelProperty(value="复核状态： 0待领取 1待初审 2拒绝 3取消  4复审通过")
	private String applyRehearState;
	
	/**
	*终核状态： 0待领取 1待初审 2拒绝 3取消  4终审通过
	*/
	@ApiModelProperty(value="终核状态： 0待领取 1待初审 2拒绝 3取消  4终审通过")
	private String applyFinalState;
	
	/**
	*初核时间
	*/
	@ApiModelProperty(value="初核时间")
	private Date applyFirstTime;
	
	/**
	*复核时间
	*/
	@ApiModelProperty(value="复核时间")
	private Date applyRehearTime;
	
	/**
	*终核时间
	*/
	@ApiModelProperty(value="终核时间")
	private Date applyFinalTime;
	
	/**
	*类型 1仓单质押 2保兑仓
	*/
	@ApiModelProperty(value="类型 2仓单质押 3保兑仓")
	private String applyType;
	
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