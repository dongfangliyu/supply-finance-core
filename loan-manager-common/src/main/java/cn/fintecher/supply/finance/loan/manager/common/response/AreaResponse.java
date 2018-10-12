package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseAreaEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class AreaResponse implements Serializable {


	@ApiModelProperty("区域返回集合")
	List<BaseAreaEntity> list;
}
