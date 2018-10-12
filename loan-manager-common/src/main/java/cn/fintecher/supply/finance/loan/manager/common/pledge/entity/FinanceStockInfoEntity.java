package cn.fintecher.supply.finance.loan.manager.common.pledge.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 17:33:07
 */
@Data
public class FinanceStockInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*来源 commodity_stock_info(入库信息表) 的pid 
	*/
	@ApiModelProperty(value="来源 commodity_stock_info(入库信息表) 的pid ")
	private Long commodityId;
	
	/**
	*初审状态：待审 0 ，通过 1，拒绝 2
	*/
	@ApiModelProperty(value="初审状态：待审 0 ，通过 1，拒绝 2")
	private String financeFirstState;
	
	/**
	*复核状态：待审 0 ，通过 1，拒绝 2
	*/
	@ApiModelProperty(value="复核状态：待审 0 ，通过 1，拒绝 2")
	private String financeRehearState;
	
	/**
	*初核时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="初核时间")
	private Date financeFirstTime;
	
	/**
	*复核时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="复核时间")
	private Date financeRehearTime;
	
	/**
	*放款时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="放款时间")
	private Date loanTime;
	
	/**
	*放款周期开始时间
	*/
	@ApiModelProperty(value="放款周期开始时间")
	private Date loanStartTime;
	
	/**
	*放款周期结束时间
	*/
	@ApiModelProperty(value="放款周期结束时间")
	private Date loanEndTime;
	
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
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*更新时间
	*/
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
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