package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.CoreEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseController
 * @ProjectName supply-finance
 * @Description: 核心企业控制层
 * @date 2018/8/30 0030下午 14:44
 */
@RestController
@RequestMapping("/confirming/coreEnterpriseMange")
@Api(tags = "保兑仓--核心企业签约管理")
public class CoreEnterpriseController extends BaseController {


   @Autowired
   private CoreEnterpriseService coreEnterpriseService;

   @ApiOperation(value = "核心企业签约列表", notes = "核心企业签约列表")
   @ResponseBody
   @RequestMapping(value = "/searchCoreEnterpriseList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public Message searchCoreEnterpriseList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {

      return coreEnterpriseService.searchCoreEnterpriseList(searchDebitAndCredit);

   }


   @ApiOperation(value = "核心企业点击签约", notes = "核心企业点击签约")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchCoreEnterpriseDetail", method = RequestMethod.GET)
   public Message<AuditAndDetailResponse> searchCoreEnterpriseDetail(@RequestParam("pid") Long pid) {

      return coreEnterpriseService.searchCoreEnterpriseDetail(pid);
   }

   @ApiOperation(value = "点击电子签约修改状态", notes = "点击电子签约修改状态")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/submitCoreEnterpriseInfo", method = RequestMethod.GET)
   public Message submitDebitAndCreditInfo(@RequestParam(value = "pid") Long pid) {
      //  submitDebitAndCredit.setCurrentUserName(principal.getName());
      return coreEnterpriseService.submitCoreEnterpriseInfo(pid);
   }


   @ApiOperation(value = "判断是否上传纸质合同", notes = "判断是否上传纸质合同")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
   public Message isUpLoadContract(@RequestParam("pid") Long pid) {
      try
      {
         return coreEnterpriseService.isUpLoadContract(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }

   @ApiOperation(value = "合同上传", notes = "合同上传")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public Message upload(@RequestPart("file") MultipartFile file,
                         @RequestParam(value = "type") String type,
                         @RequestParam(value = "code", required = false) String code,
                         @RequestParam(value = "id", required = false) Long id) {
      Message msg = new Message(MessageType.MSG_SUCCESS, "business", null);
      if (code == null && id == null)
      {
         return new Message(MessageType.MSG_ERROR, "business", "拥有者不能为空");
      }
      try
      {
         Message message = coreEnterpriseService.upload(file, type, code, id);
         msg.setCode(message.getCode());
         msg.setMessage(message.getMessage());
      } catch (Exception e)
      {
         e.printStackTrace();
         msg.setCode(MessageType.MSG_ERROR);
      }
      return msg;

   }


   /**
    * 查看纸质合同(多类型)
    */
   @ApiOperation(value = "查看纸质合同(多类型)", notes = "查看纸质合同(多类型)")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchCoreEnterpriseMoreType", method = RequestMethod.GET)
   public Message<List<BusinessFileEntity>> searchCoreEnterpriseMoreType(@RequestParam("pid") String pid) {
      try
      {
         return coreEnterpriseService.searchCoreEnterpriseMoreType(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }
}
