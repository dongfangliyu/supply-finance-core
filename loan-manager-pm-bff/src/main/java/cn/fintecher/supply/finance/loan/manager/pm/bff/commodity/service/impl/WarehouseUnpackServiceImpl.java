package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.feign.FCWarehouseUnpackService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.WarehouseUnpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author WuTianJuan
 * @Date Created in 10:01 2018/8/23
 */
@Service
public class WarehouseUnpackServiceImpl implements WarehouseUnpackService {
    @Autowired
    private FCWarehouseUnpackService fcWarehouseUnpackService;

    @Override
    public Message searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm) {
        return fcWarehouseUnpackService.searchWarehouseUnpackList(warehouseUnpackForm);
    }

    @Override
    public Message searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm) {
        return fcWarehouseUnpackService.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
    }

    @Override
    public Message searchAdminWarehouseUnpackDetail(Long pid) {
        return fcWarehouseUnpackService.searchAdminWarehouseUnpackDetail(pid);
    }

    @Override
    public Message submitWarehouseUnpack(Long pid) {
        return fcWarehouseUnpackService.submitWarehouseUnpack(pid);
    }

    @Override
    public Message submitFrontWarehouseUnpack(Long pid, String time) {
        return fcWarehouseUnpackService.submitFrontWarehouseUnpack(pid,time);
    }

    @Override
    public Message searchWarehouseUnpackDetail(Long pid, String userName) {
        return fcWarehouseUnpackService.searchWarehouseUnpackDetail(pid,userName);
    }
}
