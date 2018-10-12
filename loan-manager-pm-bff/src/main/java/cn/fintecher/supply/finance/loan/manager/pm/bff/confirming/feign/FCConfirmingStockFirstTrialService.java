package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 15:37
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmingStockFirstTrialService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/countTask", method = RequestMethod.GET)
    Message<AuditExamineResponse> countTask(@RequestParam(value = "userName") String userName, @RequestParam(value = "node") Integer node);


    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/receiveTask", method = RequestMethod.GET)
    Message receiveTask(@RequestParam(value = "userName") String userName);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/searchPageList", method = RequestMethod.POST)
    Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/submitContent", method = RequestMethod.POST)
    Message submitContent(@RequestBody ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/getTrialResult", method = RequestMethod.GET)
    Message<AuditTaskHistoryEntity> getTrialResult(@RequestParam(value = "pid")Long pid, @RequestParam(value = "node")Integer node);


    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/firstTrial/getTrialHistoryList", method = RequestMethod.GET)
    Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(@RequestParam(value = "pid")Long pid);
}
