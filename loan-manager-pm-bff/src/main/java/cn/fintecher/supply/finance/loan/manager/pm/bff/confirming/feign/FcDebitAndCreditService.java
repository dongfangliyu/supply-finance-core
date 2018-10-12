package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: FcDebitAndCreditService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 15:19
 */
@FeignClient(name = "loan-manager-service")
public interface FcDebitAndCreditService {

   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/searchDebitAndCreditList", method = RequestMethod.POST)
   Message searchDebitAndCreditList(@RequestBody SearchDebitAndCredit searchDebitAndCredit);


   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/queryOrderInfoByPid", method = RequestMethod.GET)
   Message queryOrderInfoByPid(@RequestParam("pid") String pid);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/submitDebitAndCredit", method = RequestMethod.POST)
   Message submitDebitAndCredit(@RequestBody SubmitDebitAndCredit submitDebitAndCredit);


   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   Message upload(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "registerCode") String registerCode
           , @RequestParam(value = "id") Long id);


   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/isUpLoadContract", method = RequestMethod.GET)
   Message isUpLoadContract(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/searchAllAuditFileByOwnerId", method = RequestMethod.GET)
   Message<List<BusinessFileEntity>> searchAllAuditFileByOwnerId(@RequestParam(value = "pid") String pid);

   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/searchMaiginAccount", method = RequestMethod.GET)
   Message searchMaiginAccount(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/debitAndCreditService/submitDebitAndCreditInfo", method = RequestMethod.GET)
   Message submitDebitAndCreditInfo(@RequestParam(value = "pid") Long pid);
}
