package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.DebitAndCreditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * zhang  yajun
 */
@RestController
@RequestMapping("/confirming/debitAndCreditService")
@Api(tags = "借贷企业签约列表")
public class DebitAndCreditController {

   @Autowired
   private DebitAndCreditService debitAndCreditService;

   /**
    * 查询借贷企业签约列表(业务层)
    */
   @ApiOperation(value = "借贷企业签约列表", notes = "借贷企业签约列表")
   @ResponseBody
   @RequestMapping(value = "/searchDebitAndCreditList", method = RequestMethod.POST)
   public Message searchDebitAndCreditList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {

      return debitAndCreditService.searchDebitAndCreditList(searchDebitAndCredit);

   }

   /**
    * 点击签约的列表详情
    * 根据主键查询
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/queryOrderInfoByPid", method = RequestMethod.GET)
   public Message queryOrderInfoByPid(@RequestParam("pid") String pid) {

      return debitAndCreditService.queryOrderInfoByPid(pid);
   }


   /**
    * 签约
    *
    * @param
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/submitDebitAndCredit", method = RequestMethod.POST)
   public Message submitDebitAndCredit(@RequestBody SubmitDebitAndCredit submitDebitAndCredit) {

      return debitAndCreditService.submitDebitAndCredit(submitDebitAndCredit);
   }

   @ResponseBody
   @RequestMapping(value = "/submitDebitAndCreditInfo", method = RequestMethod.GET)
   public Message submitDebitAndCreditInfo(@RequestParam(value = "pid") Long pid) {
      final String STATUS = "1";
      return debitAndCreditService.submitDebitAndCreditInfo(pid, STATUS);
   }

   @ResponseBody
   @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   Message upload(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "registerCode") String registerCode
           , @RequestParam(value = "id") Long id) {
      Message msg = new Message(MessageType.MSG_SUCCESS, "business", null);
      try
      {
         Message message = debitAndCreditService.upload(file, type, registerCode, id);
         msg.setCode(message.getCode());
         msg.setMessage(message.getMessage());
      } catch (Exception e)
      {
         e.printStackTrace();
         msg.setCode(MessageType.MSG_ERROR);
      }
      return msg;
   }


   @ResponseBody
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
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchAllAuditFileByOwnerId", method = RequestMethod.GET)
   public Message<List<BusinessFileEntity>> searchAllAuditFileByOwnerId(@RequestParam(value = "pid") String pid) {
      try
      {
         return debitAndCreditService.searchAllAuditFileByOwnerId(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "confirming_service", e.getMessage());
      }
   }

   /**
    * 查询保证金账号
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchMaiginAccount", method = RequestMethod.GET)
   public Message<SubmitDebitAndCredit> searchMaiginAccount(@RequestParam(value = "pid") Long pid) {
      try
      {
         return debitAndCreditService.searchMaiginAccount(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage());
      }
   }
}