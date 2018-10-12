package cn.fintecher.supply.finance.loan.manager.common.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserIdResponse implements Serializable{

	@ApiModelProperty(value="用户ID")
	private Integer userId;

}
