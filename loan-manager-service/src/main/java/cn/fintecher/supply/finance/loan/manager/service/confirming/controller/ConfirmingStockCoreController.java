package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockApplyService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockCoreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/5 17:24
 */
@RestController
@RequestMapping("/confirming/core")
public class ConfirmingStockCoreController extends BaseCompanyUserController {

    @Autowired
    private ConfirmingStockApplyService confirmingStockApplyService;

    @Autowired
    private ConfirmingStockCoreService confirmingStockCoreService;

    @ResponseBody
    @RequestMapping(value = "/refuseFinancing", method = RequestMethod.GET)
    public Message refuseFinancing(@RequestParam("pid") Long pid){
        try {
            return  confirmingStockCoreService.refuseFinancing(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/confirmFinancing", method = RequestMethod.GET)
    public Message confirmFinancing(@RequestParam("pid") Long pid){
        try {
            return  confirmingStockCoreService.confirmFinancing(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value ="/confirmingStockPageList", method = RequestMethod.POST)
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest){
        try {
            confirmingStockInfoResquest.setCompanyId(getCompanyUser(confirmingStockInfoResquest.getCompanyUserName()).getEnterpriseId());
            return  confirmingStockApplyService.confirmingStockPageList(confirmingStockInfoResquest);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }
}
