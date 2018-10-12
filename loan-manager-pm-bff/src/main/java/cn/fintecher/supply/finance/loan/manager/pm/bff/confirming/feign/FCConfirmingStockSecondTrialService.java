package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/9/12 15:25
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmingStockSecondTrialService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/secondTrial/receiveTask", method = RequestMethod.GET)
    Message receiveTask(@RequestParam(value = "userName") String userName);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/secondTrial/submitContent", method = RequestMethod.POST)
    Message submitContent(@RequestBody ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm);
}
