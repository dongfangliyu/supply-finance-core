package cn.fintecher.supply.finance.loan.manager.core.commodity.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;
import cn.fintecher.supply.finance.loan.manager.core.commodity.service.WarehouseUnpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:56 2018/8/23
 */
@RestController
@RequestMapping("/warehouse/pledge")
public class WarehouseUnpackController {
    @Autowired
    private WarehouseUnpackService warehouseUnpackService;

    @ResponseBody
    @RequestMapping(value = "/searchWarehouseUnpackList", method = RequestMethod.POST)
    public List<WarehouseUnpackListResponse> searchWarehouseUnpackList(@RequestBody WarehouseUnpackForm warehouseUnpackForm){
        return warehouseUnpackService.searchWarehouseUnpackList(warehouseUnpackForm);
    }


    @ResponseBody
    @RequestMapping(value = "/searchWarehouseUnpackListCount", method = RequestMethod.POST)
    public int searchWarehouseUnpackListCount(@RequestBody WarehouseUnpackForm warehouseUnpackForm){
        return warehouseUnpackService.searchWarehouseUnpackListCount(warehouseUnpackForm);
    }


    @ResponseBody
    @RequestMapping(value = "/searchAdminWarehouseUnpackList", method = RequestMethod.POST)
    public List<WarehouseUnpackListResponse> searchAdminWarehouseUnpackList(@RequestBody WarehouseUnpackAdminForm warehouseUnpackAdminForm){
        return warehouseUnpackService.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAdminWarehouseUnpackListCount", method = RequestMethod.POST)
    public Integer searchAdminWarehouseUnpackListCount(@RequestBody WarehouseUnpackAdminForm warehouseUnpackAdminForm){
        return warehouseUnpackService.searchAdminWarehouseUnpackListCount(warehouseUnpackAdminForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchPledgeInfoByCommodityId", method = RequestMethod.GET)
    public Message<List<PledgeStockInfoEntity>> searchPledgeInfoByCommodityId(@RequestParam(value = "pid") String pid){
        Message<List<PledgeStockInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
        try {
            msg.setMessage(warehouseUnpackService.searchPledgeInfoByCommodityId(pid));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByStockInfoByCommodityId", method = RequestMethod.GET)
    public FinanceStockInfoEntity selectByStockInfoByCommodityId(@RequestParam(value = "pid") String pid){
       return warehouseUnpackService.selectByStockInfoByCommodityId(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchFileByCommodityId", method = RequestMethod.GET)
    public List<BusinessFileEntity> searchFileByCommodityId(String pid){
        return warehouseUnpackService.searchFileByCommodityId(pid);
    }

}
