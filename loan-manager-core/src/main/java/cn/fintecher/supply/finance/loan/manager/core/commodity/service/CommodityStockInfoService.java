package cn.fintecher.supply.finance.loan.manager.core.commodity.service;

import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;

import java.util.List;

/**
 * 入库信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
public interface CommodityStockInfoService{

    /**
	 * 新增入库信息表
	 * @param 
	 * @return
	 */
	Long insertStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 查询入库信息表
	 * @param 
	 * @return
	 */
	List<CommodityStockInfoEntity> selectByStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 修改入库信息表
	 * @param 
	 */
	Integer updateStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 根据主键查询入库信息表
	 * @param 
	 * @return
	 */
	CommodityStockInfoEntity queryStockInfoByPid(String pid);

	/**
	 * 入库管理客户端查询分页总数
	 * @param commdityStockForm
	 * @return
	 */
	Integer queryPageCount(CommodityStockForm commdityStockForm);
	
	/**
	 * 入库管理客户端查询分页列表
	 * @param commdityStockForm
	 * @return
	 */
	List<CommodityStockInfoEntity> queryPageList(CommodityStockForm commdityStockForm);

	/**
	 * 获取最新当前入库管理编号
	 * @return
	 */
	String queryNewWarehousingNo();


	/**
	 * 管理端查询入库列表
	 * @param commodityStockForm
	 * @return
	 */
	List<CommodityStockInfoEntity> selectStockList(CommodityStockForm commodityStockForm);

	/**
	 * 管理端查询入库总条数
	 * @param commodityStockForm
	 * @return
	 */
	Integer selectStockListCount(CommodityStockForm commodityStockForm);


	/**
	 * 管理端根据pid查询（含客户名称）
	 * @param pid
	 * @return
	 */
	CommodityStockInfoEntity selectCommodityStockInfo(Long pid);
}
