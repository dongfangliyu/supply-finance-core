package cn.fintecher.supply.finance.loan.manager.core.commodity.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;
import cn.fintecher.supply.finance.loan.manager.core.business.dao.BusinessFileDao;
import cn.fintecher.supply.finance.loan.manager.core.commodity.dao.CommodityStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.commodity.service.WarehouseUnpackService;
import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.FinanceStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.pledge.dao.PledgeStockInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
   @Author WuTianJuan
 * @Date Created in 14:04 2018/8/23
 */
@Service("WarehouseUnpackService")
public class WarehouseUnpackServiceImpl implements WarehouseUnpackService {
    @Autowired
    private CommodityStockInfoDao commodityStockInfoDao;

    @Autowired
    private PledgeStockInfoDao pledgeStockInfoDao;

    @Autowired
    private FinanceStockInfoDao financeStockInfoDao;

    @Autowired
    private BusinessFileDao businessFileDao;

    @Override
    public List<WarehouseUnpackListResponse> searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm) {
        return commodityStockInfoDao.searchWarehouseUnpackList(warehouseUnpackForm);
    }

    @Override
    public Integer searchWarehouseUnpackListCount(WarehouseUnpackForm warehouseUnpackForm) {
        return commodityStockInfoDao.searchWarehouseUnpackListCount(warehouseUnpackForm);
    }

    @Override
    public List<WarehouseUnpackListResponse> searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm) {
        return commodityStockInfoDao.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
    }

    @Override
    public Integer searchAdminWarehouseUnpackListCount(WarehouseUnpackAdminForm warehouseUnpackAdminForm) {
        return commodityStockInfoDao.searchAdminWarehouseUnpackListCount(warehouseUnpackAdminForm);
    }

    @Override
    public List<PledgeStockInfoEntity> searchPledgeInfoByCommodityId(String pid) {
        return pledgeStockInfoDao.searchPledgeInfoByCommodityId(pid);
    }

    @Override
    public FinanceStockInfoEntity selectByStockInfoByCommodityId(String pid) {
        return financeStockInfoDao.selectByStockInfoByCommodityId(pid);
    }

    @Override
    public List<BusinessFileEntity> searchFileByCommodityId(String pid) {
        return businessFileDao.searchFileByCommodityId(pid);
    }

}
