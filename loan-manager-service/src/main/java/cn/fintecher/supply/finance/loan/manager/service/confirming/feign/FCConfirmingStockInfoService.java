package cn.fintecher.supply.finance.loan.manager.service.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 14:46
 */
@FeignClient(name = "loan-manager-core")
public interface FCConfirmingStockInfoService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/stockInfo/loanConfirmingStockInfoList", method = RequestMethod.POST)
    List<ConfirmingStockInfoListResponse> loanConfirmingStockInfoList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/stockInfo/countLoanConfirmingStockInfoList", method = RequestMethod.POST)
    Message<Integer> countLoanConfirmingStockInfoList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/stockInfo/getConfirmingStockInfoById", method = RequestMethod.GET)
    Message<ConfirmingStockInfoEntity> getConfirmingStockInfoById(@RequestParam("pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/stockInfo/saveOrUpdateConfirmingStockInfo", method = RequestMethod.POST)
    Message<ConfirmingStockInfoEntity> saveOrUpdateConfirmingStockInfo(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity);
}
