package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockSecondTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/9/12 15:17
 */
@RestController
@RequestMapping("/confirming/secondTrial")
public class ConfirmingStockSecondTrialController extends BaseSysUserController {

    @Autowired
    private ConfirmingStockSecondTrialService confirmingStockSecondTrialService;

    @ResponseBody
    @RequestMapping(value = "/submitContent", method = RequestMethod.POST)
    public Message submitContent(@RequestBody ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm){
        try {
            return confirmingStockSecondTrialService.submitContent(confirmingStockInfoApprovalForm,getSysUser(confirmingStockInfoApprovalForm.getCurrentUserName()));
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(@RequestParam(value = "userName") String userName){
        try {
            return confirmingStockSecondTrialService.receiveTask(getSysUser(userName));
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",e.getMessage());
        }
    }

}
