package cn.fintecher.supply.finance.loan.manager.common.business.response;

import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import lombok.Data;
@Data
public class BusinessReceivableResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BusinessReceivableEntity businessReceivable;
	
	private List<BusinessFileEntity> fileList;

}
