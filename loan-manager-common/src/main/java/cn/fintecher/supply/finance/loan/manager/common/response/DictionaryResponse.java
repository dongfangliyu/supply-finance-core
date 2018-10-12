package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DictionaryResponse implements Serializable {

	@ApiModelProperty("字典返回集合")
	List<BaseDictionaryEntity> list;
}
