package cn.fintecher.supply.finance.loan.manager.core.commodity.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.commodity.dao.CommodityStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.core.commodity.service.CommodityStockInfoService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Service("commodityStockInfoService")
public class CommodityStockInfoServiceImpl implements CommodityStockInfoService {

   	@Autowired
	private CommodityStockInfoDao commoditystockInfoDao;
	
	@Override
	public Long insertStockInfo(CommodityStockInfoEntity commodityStockInfoEntity){
		 commoditystockInfoDao.insertStockInfo(commodityStockInfoEntity);
		 return commodityStockInfoEntity.getPid();
	}
	
	@Override
	public List<CommodityStockInfoEntity> selectByStockInfo(CommodityStockInfoEntity commodityStockInfoEntity) {
		return commoditystockInfoDao.selectByStockInfo(commodityStockInfoEntity);
	}
	
	@Override
	public Integer updateStockInfo(CommodityStockInfoEntity commodityStockInfoEntity) {
		// TODO Auto-generated method stub
		return commoditystockInfoDao.updateStockInfo(commodityStockInfoEntity);
	}
	
	@Override
	public CommodityStockInfoEntity queryStockInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return commoditystockInfoDao.queryStockInfoByPid(pid);
	}

	@Override
	public Integer queryPageCount(CommodityStockForm commdityStockForm) {
		// TODO Auto-generated method stub
		return commoditystockInfoDao.queryPageCount(commdityStockForm);
	}

	@Override
	public List<CommodityStockInfoEntity> queryPageList(CommodityStockForm commdityStockForm) {
		// TODO Auto-generated method stub
		return commoditystockInfoDao.queryPageList(commdityStockForm);
	}

	@Override
	public String queryNewWarehousingNo() {
		// TODO Auto-generated method stub
		return commoditystockInfoDao.queryNewWarehousingNo();
	}

	@Override
	public List<CommodityStockInfoEntity> selectStockList(CommodityStockForm commodityStockForm) {
		return commoditystockInfoDao.selectStockList(commodityStockForm);
	}

	@Override
	public Integer selectStockListCount(CommodityStockForm commodityStockForm) {
		return commoditystockInfoDao.selectStockListCount(commodityStockForm);
	}

	@Override
	public CommodityStockInfoEntity selectCommodityStockInfo(Long pid) {
		return commoditystockInfoDao.selectCommodityStockInfo(pid);
	}
}
