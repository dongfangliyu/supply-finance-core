package  cn.fintecher.supply.finance.loan.manager.common.overdue.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 逾期还款记录表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@Data
public class OverdueRepaymentRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*还款时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="还款时间")
	private Date repaymentTime;
	
	/**
	*已还本金
	*/
	@ApiModelProperty(value="已还本金")
	private Long returnedPrincipal;
	
	/**
	*已付利息
	*/
	@ApiModelProperty(value="已付利息")
	private Long returnedInterest;
	
	/**
	*已付罚息
	*/
	@ApiModelProperty(value="已付罚息")
	private Long returnedPenalty;
	
	/**
	 *未付罚息
	 */
	@ApiModelProperty(value="未付罚息")
	private Long unpaidPenalty;
	
	/**
	*未还本金
	*/
	@ApiModelProperty(value="未还本金")
	private Long unpaidPrincipal;
	/**
	 *未还服务费
	 */
	@ApiModelProperty(value="未还服务费")
	private Long unpaidService;
	/**
	 *已还服务费
	 */
	@ApiModelProperty(value="已还服务费")
	private Long returnedService;
	
	/**
	*未付利息
	*/
	@ApiModelProperty(value="未付利息")
	private Long unpaidInterest;
	
	/**
	*罚息费率（日）
	*/
	@ApiModelProperty(value="罚息费率（日）")
	private Integer penaltyFee;
	
	/**
	*逾期天数
	*/
	@ApiModelProperty(value="逾期天数")
	private Integer overdueDay;
	
	/**
	*逾期订单id
	*/
	@ApiModelProperty(value="逾期订单id")
	private Long orderId;
	
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
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*更新时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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