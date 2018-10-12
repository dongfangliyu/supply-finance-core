package cn.fintecher.supply.finance.loan.manager.service.audit.core;

import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
@FeignClient(name = "loan-manager-core")
public interface AuditTaskCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/insertTask", method = RequestMethod.POST)
    public Message insertTask(@RequestBody AuditTaskEntity auditTaskEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/updateTask", method = RequestMethod.POST)
    public Message updateTask(@RequestBody AuditTaskEntity auditTaskEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/selectByTask", method = RequestMethod.POST)
    public Message<List<AuditTaskEntity>> selectByTask(@RequestBody AuditTaskEntity auditTaskEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/queryTaskByPid", method = RequestMethod.GET)
    public Message<AuditTaskEntity> queryTaskByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/countConfirmingTaskWaitNum", method = RequestMethod.GET)
    Message<Integer> countConfirmingTaskWaitNum(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "node")Integer node);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/task/countConfirmingApprovalList", method = RequestMethod.POST)
    Message<Integer> countConfirmingApprovalList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);

    @ResponseBody
    @RequestMapping(value = "/audit/task/confirmingApprovalList", method = RequestMethod.POST)
    Message<List<ConfirmingStockInfoListResponse>> confirmingApprovalList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);
}
