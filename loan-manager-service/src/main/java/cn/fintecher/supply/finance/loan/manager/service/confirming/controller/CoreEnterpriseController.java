package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.CoreEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.DebitAndCreditService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseController
 * @ProjectName supply-finance
 * @Description: 核心企业 业务控制层
 * @date 2018/8/30 0030下午 15:02
 */

@RestController
@RequestMapping("/confirming/CoreEnterpriseService")
public class CoreEnterpriseController {

   @Autowired
   private CoreEnterpriseService coreEnterpriseService;

   @Autowired
   private DebitAndCreditService debitAndCreditService;

   /**
    * 核心企业列表
    *
    * @param searchDebitAndCredit
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchCoreEnterpriseList", method = RequestMethod.POST)
   public Message searchCoreEnterpriseList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {
      return coreEnterpriseService.searchCoreEnterpriseList(searchDebitAndCredit);
   }

   /**
    * 点击列表页的签约 展示信息
    *
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchCoreEnterpriseDetail", method = RequestMethod.GET)
   public Message<AuditAndDetailResponse> searchCoreEnterpriseDetail(@RequestParam(value = "pid") Long pid) {
      return debitAndCreditService.queryOrderInfoByPid(pid.toString());
   }


   @ResponseBody
   @RequestMapping(value = "/submitCoreEnterpriseInfo", method = RequestMethod.GET)
   public Message submitCoreEnterpriseInfo(@RequestParam(value = "pid") Long pid) {
      final String STATUS = "2"; //  修改的状态
      return coreEnterpriseService.submitDebitAndCreditInfo(pid, STATUS);
   }


   @ResponseBody
   @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
   public Message isUpLoadContract(@RequestParam(value = "pid") Long pid) {
      return coreEnterpriseService.isUpLoadContract(pid);
   }


   @ResponseBody
   @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   Message upload(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "registerCode") String registerCode, @RequestParam(value = "id") Long id) {

      Message msg = new Message(MessageType.MSG_SUCCESS, "coreEnterprise", null);
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

   /**
    * 查看纸质合同
    *
    * @param pid
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/searchCoreEnterpriseMoreType", method = RequestMethod.GET)
   public Message<List<BusinessFileEntity>> searchCoreEnterpriseMoreType(@RequestParam(value = "pid") String pid) {

      try
      {
         return debitAndCreditService.searchAllAuditFileByOwnerId(pid);
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage());
      }
   }


}
