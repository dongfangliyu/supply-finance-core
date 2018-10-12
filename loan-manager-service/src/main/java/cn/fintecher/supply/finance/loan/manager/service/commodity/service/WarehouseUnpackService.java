package cn.fintecher.supply.finance.loan.manager.service.commodity.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WuTianJuan
 * @Date Created in 11:16 2018/8/23
 */
public interface WarehouseUnpackService {
    Message searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm, CompanyUserEntity user);

    Message searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    Message searchAdminWarehouseUnpackDetail(Long pid);

    Message submitWarehouseUnpack(Long pid);

    Message submitFrontWarehouseUnpack(Long pid, String time);

    Message searchWarehouseUnpackDetail(Long pid, CompanyUserEntity user);
}
