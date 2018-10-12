package cn.fintecher.supply.finance.loan.manager.common.business.response;

import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import lombok.Data;
@Data
public class BusinessOrderResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BusinessOrderEntity businessOrder;
	
	private List<BusinessFileEntity> fileList;

}
