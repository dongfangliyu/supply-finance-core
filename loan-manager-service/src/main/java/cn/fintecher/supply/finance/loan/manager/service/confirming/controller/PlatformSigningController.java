package cn.fintecher.supply.finance.loan.manager.service.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCDebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.DebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.PlatformSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningController
 * @ProjectName supply-finance
 * @Description: 资方业务控制层
 * @date 2018/8/30 0030下午 15:41
 */

@RestController
@RequestMapping("/confirming/PlatformSigningService")
public class PlatformSigningController {

   @Autowired
   private PlatformSigningService platformSigningService;

   @Autowired
   private DebitAndCreditService debitAndCreditService;

   /**
    * 列表
    *
    * @param searchDebitAndCredit
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchPlatformSigningList", method = RequestMethod.POST)
   Message searchPlatformSigningList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {
      return platformSigningService.searchPlatformSigningList(searchDebitAndCredit);
   }

   /**
    * 查询 签约详情页
    *
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchPlatformSigningDetail", method = RequestMethod.GET)
   public Message searchPlatformSigningDetail(@RequestParam(value = "pid") Long pid) {
      return platformSigningService.searchPlatformSigningDetail(pid);
   }

   /**
    * 查询保证金账户
    *
    * @param pid
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/searchMaiginAccount", method = RequestMethod.GET)
   public Message searchMaiginAccount(@RequestParam(value = "pid") Long pid) {
      return debitAndCreditService.searchMaiginAccount(pid);
   }

   /**
    * 修复签约状态
    *
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/submitPlatformSigningInfo", method = RequestMethod.GET)
   public Message submitPlatformSigningInfo(@RequestParam(value = "pid") Long pid) {
      final String STATUS = "3";
      return debitAndCreditService.submitDebitAndCreditInfo(pid, STATUS);
   }

   /**
    * 判断是否上传合同
    *
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/isUpLoadContract", method = RequestMethod.GET)
   public Message isUpLoadContract(@RequestParam(value = "pid") Long pid) {
      return platformSigningService.isUpLoadContract(pid);
   }

   /**
    * 查看合同
    *
    * @param pid
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/searchDebitAndCreditMoreType", method = RequestMethod.GET)
   public Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(@RequestParam(value = "pid") String pid) {
      return debitAndCreditService.searchAllAuditFileByOwnerId(pid);
   }

}
