package cn.fintecher.supply.finance.loan.manager.common.pledge.request;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 提交入库信息
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Data
public class PledgeApplySubmit implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "申请id不能为空")
	@ApiModelProperty(value="申请id")
	private Long id;
	
	@NotNull(message = "期望融资金额不能为空")
	@ApiModelProperty(value="期望融资金额")
	private BigDecimal pledgeFinanceAmount;
	
	
	@NotNull(message="预计使用期限不能为空")
	@ApiModelProperty(value="预计使用期限")
	private Long pledgeTerm;
	
	@NotBlank(message="资金用途不能为空")
	@ApiModelProperty(value="资金用途")
	private String pledgeEarmarking;
	
	
	/**
	 * 当前登录人
	 */
	@ApiModelProperty(value="当前登录人",hidden = true)
	private String userName;
	

}