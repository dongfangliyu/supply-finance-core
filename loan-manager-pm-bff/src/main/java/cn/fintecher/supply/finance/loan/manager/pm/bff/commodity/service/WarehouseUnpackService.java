package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;

/**
 * @Author WuTianJuan
 * @Date Created in 10:01 2018/8/23
 */
public interface WarehouseUnpackService {
    /**
     * 查询前台仓单解押列表
     * @param warehouseUnpackForm
     * @return
     */
    Message searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm);

    Message searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    Message searchAdminWarehouseUnpackDetail(Long pid);

    Message submitWarehouseUnpack(Long pid);

    Message submitFrontWarehouseUnpack(Long pid,String time);

    Message searchWarehouseUnpackDetail(Long pid, String userName);

}
