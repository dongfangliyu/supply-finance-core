package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/6 9:32
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmingStockCoreService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/core/confirmingStockPageList", method = RequestMethod.POST)
    Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/core/confirmFinancing", method = RequestMethod.GET)
    Message confirmFinancing(@RequestParam("pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/core/refuseFinancing", method = RequestMethod.GET)
    Message refuseFinancing(@RequestParam("pid") Long pid);
}
