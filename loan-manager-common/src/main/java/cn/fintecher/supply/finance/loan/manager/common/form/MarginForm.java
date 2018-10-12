package cn.fintecher.supply.finance.loan.manager.common.form;

import java.io.Serializable;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;
/**
 * @author lss
 * @date 2018-8-28
 */
@Data
public class MarginForm  extends PageInfo implements Serializable {
	
	 private String bussineNo;

	 private String productNo;

}
