package cn.fintecher.supply.finance.loan.manager.core.commodity.dao;

import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;

import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 入库信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Mapper
public interface CommodityStockInfoDao{
	
	/**
	 * 插入
	 * @param commodityStockInfoEntity
	 * @return
	 */
	Integer insertStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 根据entity查询List
	 * @param commodityStockInfoEntity
	 * @return
	 */
	List<CommodityStockInfoEntity> selectByStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 更新
	 * @param commodityStockInfoEntity
	 * @return
	 */
	Integer updateStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 根据主键查询
	 * @param pid
	 * @return
	 */
	CommodityStockInfoEntity queryStockInfoByPid(String pid);

	/**
	 * 查询客户端分页总数
	 * @param commdityStockForm
	 * @return
	 */
	Integer queryPageCount(CommodityStockForm commdityStockForm);

	/**
	 * 查询客户端分页列表
	 * @param commdityStockForm
	 * @return
	 */
	List<CommodityStockInfoEntity> queryPageList(CommodityStockForm commdityStockForm);

	String queryNewWarehousingNo();

	/**
	 * 查询管理端分页列表
	 * @param commodityStockForm
	 * @return
	 */
	List<CommodityStockInfoEntity> selectStockList(CommodityStockForm commodityStockForm);

	/**
	 * 查询管理端分页总数
	 * @param commodityStockForm
	 * @return
	 */
	Integer selectStockListCount(CommodityStockForm commodityStockForm);


	/**
	 * 管理端查询
	 * @param pid
	 * @return
	 */
	CommodityStockInfoEntity selectCommodityStockInfo(Long pid);



	List<WarehouseUnpackListResponse> searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm);

	Integer searchWarehouseUnpackListCount(WarehouseUnpackForm warehouseUnpackForm);

	List<WarehouseUnpackListResponse> searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm);

	Integer searchAdminWarehouseUnpackListCount(WarehouseUnpackAdminForm warehouseUnpackAdminForm);
}
