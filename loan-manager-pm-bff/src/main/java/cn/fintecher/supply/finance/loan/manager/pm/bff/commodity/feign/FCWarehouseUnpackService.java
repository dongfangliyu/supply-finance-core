package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:02 2018/8/23
 */
@FeignClient(name = "loan-manager-service")
public interface FCWarehouseUnpackService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchWarehouseUnpackList", method = RequestMethod.POST)
    Message searchWarehouseUnpackList(@RequestBody WarehouseUnpackForm warehouseUnpackForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchAdminWarehouseUnpackList", method = RequestMethod.POST)
    Message searchAdminWarehouseUnpackList(@RequestBody WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchAdminWarehouseUnpackDetail", method = RequestMethod.GET)
    Message searchAdminWarehouseUnpackDetail(@RequestParam(value = "pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/submitWarehouseUnpack", method = RequestMethod.GET)
    Message submitWarehouseUnpack(@RequestParam(value = "pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/submitFrontWarehouseUnpack", method = RequestMethod.GET)
    Message submitFrontWarehouseUnpack(@RequestParam(value = "pid") Long pid, @RequestParam(value="time") String time);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchWarehouseUnpackDetail", method = RequestMethod.GET)
    Message searchWarehouseUnpackDetail(@RequestParam(value = "pid") Long pid, @RequestParam(value = "userName") String userName);
}
