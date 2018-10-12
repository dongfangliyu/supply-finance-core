package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.PlatformSigningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningController
 * @ProjectName supply-finance
 * @Description: 资方签约管理
 * @date 2018/8/30 0030下午 15:34
 */
@RestController
@RequestMapping("/confirming/platformSigningManage")
@Api(tags = "保兑仓--资方签约管理")
public class PlatformSigningController {

   @Autowired
   private PlatformSigningService platformSigningService;


   @ApiOperation(value = "资方签约列表", notes = "资方签约列表")
   @ResponseBody
   @RequestMapping(value = "/searchPlatformSigningList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public Message searchPlatformSigningList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {
      return platformSigningService.searchPlatformSigningList(searchDebitAndCredit);
   }


   @ApiOperation(value = "资方点击签约", notes = "资方点击签约")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchPlatformSigningDetail", method = RequestMethod.GET)
   public Message<AuditAndDetailResponse> searchPlatformSigningDetail(@RequestParam("pid") Long pid) {

      return platformSigningService.searchPlatformSigningDetail(pid);
   }

   @ApiOperation(value = "查找保证金账号", notes = "查找保證金账号")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchMaiginAccount", method = RequestMethod.GET)
   public Message<SubmitDebitAndCredit> searchMaiginAccount(@RequestParam("pid") Long pid) {
      try
      {
         return platformSigningService.searchMaiginAccount(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }


   @ApiOperation(value = "点击电子签约修改状态", notes = "点击电子签约修改状态")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/submitPlatformSigningInfo", method = RequestMethod.GET)
   public Message submitPlatformSigningInfo(@RequestParam(value = "pid") Long pid) {
      //  submitDebitAndCredit.setCurrentUserName(principal.getName());
      return platformSigningService.submitPlatformSigningInfo(pid);
   }


   @ApiOperation(value = "判断是否上传纸质合同", notes = "判断是否上传纸质合同")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
   public Message isUpLoadContract(@RequestParam("pid") Long pid) {
      try
      {
         return platformSigningService.isUpLoadContract(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }




   /**
    * 查看纸质合同(多类型)
    */
   @ApiOperation(value = "查看纸质合同(多类型)", notes = "查看纸质合同(多类型)")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchDebitAndCreditMoreType", method = RequestMethod.GET)
   public Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(@RequestParam("pid") String pid) {
      try
      {
         return platformSigningService.searchDebitAndCreditMoreType(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }


}
