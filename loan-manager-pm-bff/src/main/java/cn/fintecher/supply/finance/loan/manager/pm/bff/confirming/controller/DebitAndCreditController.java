package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.DebitAndCreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;


/**
 * zhang  yajun
 */
@RestController
@RequestMapping("/confirming/debitAndCreditManage")
@Api(tags = "保兑仓--借贷企业签约管理")
public class DebitAndCreditController extends BaseController {

   @Autowired
   private DebitAndCreditService debitAndCreditService;

   /**
    * 查询借贷企业签约列表
    */
   @ApiOperation(value = "借贷企业签约列表", notes = "借贷企业签约列表")
   @ResponseBody
   @RequestMapping(value = "/searchDebitAndCreditList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public Message searchDebitAndCreditList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {

      return debitAndCreditService.searchDebitAndCreditList(searchDebitAndCredit);

   }


   @ApiOperation(value = "借贷方点击签约", notes = "借贷方点击签约")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchDebitAndCreditDetail", method = RequestMethod.GET)
   public Message<AuditAndDetailResponse> searchSigningDetail(@RequestParam("pid") Long pid) {

      return debitAndCreditService.searchSigningDetail(pid);
   }


   @ApiOperation(value = "签约", notes = "签约")
   @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
   @RequestMapping(value = "/submitDebitAndCredit", method = RequestMethod.POST)
   public Message submitDebitAndCredit(@RequestBody SubmitDebitAndCredit submitDebitAndCredit, Principal principal) {
      if (principal.getName().equals(null))
      {
         return new Message(MessageType.MSG_ERROR, "", "获取用户失败");
      }
      submitDebitAndCredit.setCurrentUserName(principal.getName());
      return debitAndCreditService.submitDebitAndCredit(submitDebitAndCredit);
   }


   @ApiOperation(value = "点击电子签约修改状态", notes = "点击电子签约修改状态")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/submitDebitAndCreditInfo", method = RequestMethod.GET)
   public Message submitDebitAndCreditInfo(@RequestParam(value = "pid") Long pid) {
      //  submitDebitAndCredit.setCurrentUserName(principal.getName());
      return debitAndCreditService.submitDebitAndCreditInfo(pid);
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
         Message message = debitAndCreditService.upload(file, type, code, id);
         msg.setCode(message.getCode());
         msg.setMessage(message.getMessage());
      } catch (Exception e)
      {
         e.printStackTrace();
         msg.setCode(MessageType.MSG_ERROR);
      }
      return msg;

   }


   @ApiOperation(value = "判断是否上传纸质合同", notes = "判断是否上传纸质合同")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
   public Message isUpLoadContract(@RequestParam("pid") Long pid) {
      try
      {
         return debitAndCreditService.isUpLoadContract(pid);
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
         return debitAndCreditService.searchDebitAndCreditMoreType(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }


   /**
    * 查找保證金账号
    */
   @ApiOperation(value = "查找保证金账号", notes = "查找保證金账号")
   @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
   @RequestMapping(value = "/searchMaiginAccount", method = RequestMethod.GET)
   public Message searchMaiginAccount(@RequestParam("pid") Long pid) {
      try
      {
         return debitAndCreditService.searchMaiginAccount(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage());
      }
   }

}