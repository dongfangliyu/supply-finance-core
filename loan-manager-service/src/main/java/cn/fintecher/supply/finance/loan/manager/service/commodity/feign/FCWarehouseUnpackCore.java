package cn.fintecher.supply.finance.loan.manager.service.commodity.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:34 2018/8/23
 */
@FeignClient(name = "loan-manager-core")
public interface FCWarehouseUnpackCore {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchWarehouseUnpackList", method = RequestMethod.POST)
    List<WarehouseUnpackListResponse>  searchWarehouseUnpackList(@RequestBody WarehouseUnpackForm warehouseUnpackForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchWarehouseUnpackListCount", method = RequestMethod.POST)
    int searchWarehouseUnpackListCount(@RequestBody WarehouseUnpackForm warehouseUnpackForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchAdminWarehouseUnpackList", method = RequestMethod.POST)
    List<WarehouseUnpackListResponse> searchAdminWarehouseUnpackList(@RequestBody  WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchAdminWarehouseUnpackListCount", method = RequestMethod.POST)
    Integer searchAdminWarehouseUnpackListCount(@RequestBody  WarehouseUnpackAdminForm warehouseUnpackAdminForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchPledgeInfoByCommodityId", method = RequestMethod.GET)
    Message<List<PledgeStockInfoEntity>> searchPledgeInfoByCommodityId(@RequestParam(value = "pid") String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/selectByStockInfoByCommodityId", method = RequestMethod.GET)
    FinanceStockInfoEntity selectByStockInfoByCommodityId(@RequestParam(value = "pid") String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/warehouse/pledge/searchFileByCommodityId", method = RequestMethod.GET)
    List<BusinessFileEntity> searchFileByCommodityId(@RequestParam(value = "pid")String pid);
}
