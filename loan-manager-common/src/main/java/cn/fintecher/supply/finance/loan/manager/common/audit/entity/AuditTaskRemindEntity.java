package cn.fintecher.supply.finance.loan.manager.common.audit.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author gonghebin
 * @date 2018-07-21 17:17:19
 */
@Data
public class AuditTaskRemindEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*pid
	*/
	@ApiModelProperty(value="pid")
	private Long pid;
	
	/**
	*order_info_id
	*/
	@ApiModelProperty(value="order_info_id")
	private Long orderInfoId;
	
	/**
	*提前天数
	*/
	@ApiModelProperty(value="提前天数")
	private Long advanceDay;
	
	/**
	*提醒日期
	*/
	@ApiModelProperty(value="提醒日期")
	private Date remindDate;
	
	/**
	*提醒形式
	*/
	@ApiModelProperty(value="提醒形式")
	private String remindForm;
	
	/**
	*提醒时间点
	*/
	@ApiModelProperty(value="提醒时间点")
	private String remindTime;
	
	/**
	*发送状态  0未发送  1已发送  -1发送失败
	*/
	@ApiModelProperty(value="发送状态  0未发送  1已发送  -1发送失败")
	private String state;
	
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