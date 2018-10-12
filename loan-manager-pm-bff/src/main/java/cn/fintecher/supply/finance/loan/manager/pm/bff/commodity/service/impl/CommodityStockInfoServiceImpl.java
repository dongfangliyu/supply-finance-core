package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.feign.CommodityStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.CommodityStockInfoService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Service("commodityStockInfoService")
public class CommodityStockInfoServiceImpl implements CommodityStockInfoService {

	@Autowired
    private CommodityStockInfoCore commodityStockInfoCore;
	

	@Override
	public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(
			CommodityStockForm commdityStockForm) {
		return commodityStockInfoCore.searchStockList(commdityStockForm);
	}

	@Override
	public Message<Object> submitStock(CommodityStockInfoSubmit commodityStockInfoSubmit) {
		return commodityStockInfoCore.submitStock(commodityStockInfoSubmit);
	}

	@Override
	public Message<CommodityStockInfoResponse> searchStockDetail(Long pid, String userName) {
		return commodityStockInfoCore.searchStockDetail(pid,userName);
	};

	@Override
	public Message selectStockList(CommodityStockForm commodityStockForm) {
		return commodityStockInfoCore.selectStockList(commodityStockForm);
	}

	@Override
	public Message submitState(Long pid, String time) {
		return commodityStockInfoCore.submitState(pid, time);
	}

	@Override
	public Message selectCommodityStockInfo(Long pid) {
		return commodityStockInfoCore.selectCommodityStockInfo(pid);
	}
}
