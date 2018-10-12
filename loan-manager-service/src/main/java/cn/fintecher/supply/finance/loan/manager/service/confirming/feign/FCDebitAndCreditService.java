package cn.fintecher.supply.finance.loan.manager.service.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: FCDebitAndCreditService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 15:36
 */
@FeignClient(name = "loan-manager-core")
public interface FCDebitAndCreditService {

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "confirming/stockInfo/searchDebitAndCreditList", method = RequestMethod.POST)
   Message<List<ConfirmingStockList>> searchDebitAndCreditList(@RequestBody SearchDebitAndCredit searchDebitAndCredit);


   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/qureyPageCount", method = RequestMethod.POST)
   Message<Integer> qureyPageCount(@RequestBody SearchDebitAndCredit searchDebitAndCredit);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/queryOrderInfoByPid", method = RequestMethod.GET)
   Message<ConfirmingStockInfoEntity> queryOrderInfoByPid(@RequestParam(value = "pid") String pid);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/queryConfirmingBankInfo", method = RequestMethod.GET)
   Message<BaseBankInfoEntity> queryConfirmingBankInfo(@RequestParam(value = "companyDealerId") Long companyDealerId, @RequestParam("objectId") String objectId);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/submitDebitAndCredit", method = RequestMethod.POST)
   Message submitDebitAndCredit(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/searchAllFileByOwnerId", method = RequestMethod.GET)
   List<BusinessFileEntity> searchAllFileByOwnerId(@RequestParam(value = "pid") Long pid);


   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/stockInfo/searchMaiginAccount", method = RequestMethod.GET)
   Message searchMaiginAccount(@RequestParam(value = "companyDealerId") Long companyDealerId, @RequestParam(value = "objectType") String objectType);
}
