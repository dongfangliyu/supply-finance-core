package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: FCPlatformSigningCore
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:36
 */

@FeignClient(name = "loan-manager-service")
public interface FCPlatformSigningCore {


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/searchPlatformSigningList", method = RequestMethod.POST)
   Message searchPlatformSigningList(@RequestBody SearchDebitAndCredit searchDebitAndCredit);


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/searchPlatformSigningDetail", method = RequestMethod.GET)
   Message searchPlatformSigningDetail(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/searchMaiginAccount", method = RequestMethod.GET)
   Message searchMaiginAccount(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/submitPlatformSigningInfo", method = RequestMethod.GET)
   Message submitPlatformSigningInfo(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/isUpLoadContract", method = RequestMethod.GET)
   Message isUpLoadContract(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/PlatformSigningService/searchDebitAndCreditMoreType", method = RequestMethod.GET)
   Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(@RequestParam(value = "pid") String pid);
}
