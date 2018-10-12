package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockApplyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:19
 */
@RestController
@RequestMapping("/confirming/loan")
public class ConfirmingStockApplyController extends BaseCompanyUserController {

    @Autowired
    private ConfirmingStockApplyService confirmingStockApplyService;

    @ResponseBody
    @RequestMapping(value = "/getCompany", method = RequestMethod.GET)
    Message<CompanyEnterpriseEntity> getCompany(@RequestParam(value = "name")String name){
        try {
            return confirmingStockApplyService.getCompany(getCompanyUser(name).getEnterpriseId());
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/confirmingStockPageList", method = RequestMethod.POST)
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest){
        try {
            confirmingStockInfoResquest.setCompanyDealerId(getCompanyUser(confirmingStockInfoResquest.getCompanyDealerUserName()).getEnterpriseId());
            return confirmingStockApplyService.confirmingStockPageList(confirmingStockInfoResquest);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveConfirmingStockinfo", method = RequestMethod.POST)
    public Message saveConfirmingStockinfo(@RequestBody ConfirmingStockInfoForm confirmingStockInfoForm){
        try {
            confirmingStockInfoForm.setCompanyDealerId(getCompanyUser(confirmingStockInfoForm.getCompanyDealerUserName()).getEnterpriseId());
            return confirmingStockApplyService.saveConfirmingStockinfo(confirmingStockInfoForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/submitConfirmingStockinfo", method = RequestMethod.POST)
    public Message submitConfirmingStockinfo(@RequestBody ConfirmingStockInfoForm confirmingStockInfoForm){
        try {
            confirmingStockInfoForm.setCompanyDealerId(getCompanyUser(confirmingStockInfoForm.getCompanyDealerUserName()).getEnterpriseId());
            return confirmingStockApplyService.submitConfirmingStockinfo(confirmingStockInfoForm);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value ="/upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message upload(@RequestPart("file")MultipartFile file, @RequestParam(value = "type")String type, @RequestParam(value = "code",required = false)String code){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =confirmingStockApplyService.upload(file,type,code);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailById", method = RequestMethod.GET)
    public Message<ConfirmingStockInfoDetailResponse> getDetailById(@RequestParam(value = "pid")Long pid){
        try {
            return confirmingStockApplyService.getDetailById(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }
}
