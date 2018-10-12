package cn.fintecher.supply.finance.loan.manager.core.commodity.service;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:57 2018/8/23
 */
public interface WarehouseUnpackService {
    List<WarehouseUnpackListResponse> searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm);

    Integer searchWarehouseUnpackListCount(WarehouseUnpackForm warehouseUnpackForm);

    List<WarehouseUnpackListResponse> searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    Integer searchAdminWarehouseUnpackListCount(WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    List<PledgeStockInfoEntity> searchPledgeInfoByCommodityId(String pid);

    FinanceStockInfoEntity selectByStockInfoByCommodityId(String pid);

    List<BusinessFileEntity> searchFileByCommodityId(String pid);
}
