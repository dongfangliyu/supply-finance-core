package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockFirstTrialService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 10:04
 */
@RestController
@RequestMapping("/confirming/firstTrial")
public class ConfirmingStockFirstTrialController extends BaseSysUserController {

    @Autowired
    private ConfirmingStockFirstTrialService confirmingStockFirstTrialService;

    @ResponseBody
    @RequestMapping(value = "/getTrialHistoryList", method = RequestMethod.GET)
    public Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(@RequestParam(value = "pid")Long pid){
        try {
            return confirmingStockFirstTrialService.getTrialHistoryList(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getTrialResult", method = RequestMethod.GET)
    public Message<AuditTaskHistoryEntity> getTrialResult(@RequestParam(value = "pid")Long pid, @RequestParam(value = "node")Integer node){
        try {
            return confirmingStockFirstTrialService.getTrialResult(pid,node);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/searchPageList", method = RequestMethod.POST)
    public Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest){
        try {
            return confirmingStockFirstTrialService.searchPageList(confirmingStockInfoApprovalResquest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(@RequestParam(value = "userName") String userName){
        try {
            return confirmingStockFirstTrialService.receiveTask(getSysUser(userName));
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value ="/countTask", method = RequestMethod.GET)
    public Message<AuditExamineResponse> countTask(@RequestParam(value = "userName") String userName, @RequestParam(value = "node")Integer node){
        try {
            return confirmingStockFirstTrialService.countTask(getSysUser(userName).getUserId(),node);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submitContent", method = RequestMethod.POST)
    public Message submitContent(@RequestBody ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm){
        try {
            return confirmingStockFirstTrialService.submitContent(confirmingStockInfoApprovalForm,getSysUser(confirmingStockInfoApprovalForm.getCurrentUserName()));
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

}
