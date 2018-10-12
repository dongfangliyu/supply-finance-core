package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockApplyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockFirstTrialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockSecondTrialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/12 15:17
 */
@RestController
@RequestMapping("/confirming/secondTrial")
@Api(tags = "保兑仓--复审")
public class ConfirmingStockSecondTrialController {

    @Autowired
    private ConfirmingStockSecondTrialService confirmingStockSecondTrialService;

    @Autowired
    private ConfirmingStockFirstTrialService confirmingStockFirstTrialService;

    @Autowired
    private ConfirmingStockApplyService confirmingStockApplyService;

    @ApiOperation(value="提交审批", notes="提交审批")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitContent", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized  Message submitContent(@RequestBody ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm, Principal principal){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            confirmingStockInfoApprovalForm.setCurrentUserName(principal.getName());
            return  confirmingStockSecondTrialService.submitContent(confirmingStockInfoApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_bff",e.getMessage());
        }

    }


    @ApiOperation(value="获取审批历史", notes="获取审批历史")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/getTrialHistoryList", method = RequestMethod.GET)
    public Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(@RequestParam(value="pid") Long pid){
        try {
            return confirmingStockFirstTrialService.getTrialHistoryList(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_bff",e.getMessage());
        }
    }

    @ApiOperation(value="获取复审结果", notes="获取复审结果")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/getSecondTrialResult", method = RequestMethod.GET)
    public Message<AuditTaskHistoryEntity> getSecondTrialResult(@RequestParam(value="pid") Long pid){
        try {
            return confirmingStockFirstTrialService.getTrialResult(pid,2);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_bff",e.getMessage());
        }
    }

    @ApiOperation(value="获取订单详情", notes="获取订单详情")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/getOrderDetailById", method = RequestMethod.GET)
    public Message<ConfirmingStockInfoDetailResponse> getDetailById(@RequestParam(value="pid") Long pid){
        try {
            return  confirmingStockApplyService.getDetailById(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_bff",e.getMessage());
        }
    }

    @ApiOperation(value="审批列表", notes="审批列表")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchPageList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(@RequestBody ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest, Principal principal){
        try {
            confirmingStockInfoApprovalResquest.setCurrentUserName(principal.getName());
            confirmingStockInfoApprovalResquest.setNode("2");
            return  confirmingStockFirstTrialService.searchPageList(confirmingStockInfoApprovalResquest);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_bff",e.getMessage());
        }

    }

    @ApiOperation(value="领取任务", notes="领取任务")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(Principal principal){
        try {
            return confirmingStockSecondTrialService.receiveTask(principal.getName());
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_bff",e.getMessage());
        }
    }

    @ApiOperation(value="统计任务数", notes="统计任务数")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/countTask", method = RequestMethod.GET)
    public Message<AuditExamineResponse> countTask(Principal principal){
        try {
            return confirmingStockFirstTrialService.countTask(principal.getName(),2);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockFirstTrial_bff",e.getMessage());
        }
    }

}
