package cn.fintecher.supply.finance.loan.manager.common.pledge.response;


import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
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
public class PledgeApplyInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*仓库商品信息
	*/
	@ApiModelProperty(value="仓库商品信息")
	private CommodityStockInfoEntity commodityStockInfo;
	
	/**
	*仓库商品信息
	*/
	@ApiModelProperty(value="仓库商品信息")
	private List<BusinessFileEntity> files;
	
	/**
	 *申请信息
	 */
	@ApiModelProperty(value="申请信息")
	private PledgeStockInfoEntity stockInfo;
	
	

}