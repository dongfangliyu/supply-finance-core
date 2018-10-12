package cn.fintecher.supply.finance.loan.manager.core.confirming.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.confirming.service.ConfirmingStockInfoService;


/**
 * 保兑仓表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-28 16:02:16
 */
@RestController
@RequestMapping("/confirming/stockInfo")
public class ConfirmingStockInfoController {
   @Autowired
   private ConfirmingStockInfoService confirmingStockInfoService;


   /**
    * 列表
    *
    * @param searchDebitAndCredit
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/searchDebitAndCreditList", method = RequestMethod.POST)
   public Message<List<ConfirmingStockList>> searchDebitAndCreditList(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {
      return confirmingStockInfoService.searchDebitAndCreditList(searchDebitAndCredit);
   }


   /**
    * 总数
    *
    * @param searchDebitAndCredit
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/qureyPageCount", method = RequestMethod.POST)
   public Message<Integer> qureyPageCount(@RequestBody SearchDebitAndCredit searchDebitAndCredit) {
      return confirmingStockInfoService.qureyPageCount(searchDebitAndCredit);
   }


   /**
    * 根据主键查询
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/queryOrderInfoByPid", method = RequestMethod.GET)
   public Message<ConfirmingStockInfoEntity> queryOrderInfoByPid(@RequestParam("pid") String pid) {

      return confirmingStockInfoService.queryOrderInfoByPid(pid);
   }


   /**
    * 添加
    */
   @ResponseBody
   @RequestMapping(value = "/insertStockInfo", method = RequestMethod.POST)
   public Integer insertStockInfo(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity) {

      return confirmingStockInfoService.insertStockInfo(confirmingStockInfoEntity);
   }

   /**
    * 修改
    */
   @ResponseBody
   @RequestMapping(value = "/updateStockInfo", method = RequestMethod.POST)
   public Integer updateStockInfo(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity) {

      return confirmingStockInfoService.updateStockInfo(confirmingStockInfoEntity);
   }


   /**
    * 查询
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/selectByStockInfo", method = RequestMethod.POST)
   public List<ConfirmingStockInfoEntity> selectByStockInfo(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity) {

      return confirmingStockInfoService.selectByStockInfo(confirmingStockInfoEntity);
   }

   /**
    * 根据主键查询
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/queryStockInfoByPid", method = RequestMethod.GET)
   public ConfirmingStockInfoEntity queryStockInfoByPid(@RequestParam("pid") String pid) {

      return confirmingStockInfoService.queryStockInfoByPid(pid);

   }


   /**
    * 查询经销商基本户
    *
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/queryConfirmingBankInfo", method = RequestMethod.GET)
   public Message<BaseBankInfoEntity> queryConfirmingBankInfo(@RequestParam("companyDealerId") Long companyDealerId, @RequestParam("objectId") String objectId) {
      Message message = new Message();
      try
      {
         BaseBankInfoEntity baseBankInfoEntity = confirmingStockInfoService.queryConfirmingBankInfo(companyDealerId, objectId);
         message.setMessage(baseBankInfoEntity);
         message.setCode(MessageType.MSG_SUCCESS);
         return message;
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "confirming", e.getMessage());
      }
   }

   @ResponseBody
   @RequestMapping(value = "/submitDebitAndCredit", method = RequestMethod.POST)
   public Message submitDebitAndCredit(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity) {

      try
      {
         Integer integer = this.updateStockInfo(confirmingStockInfoEntity);
         if (integer > 0)
         {
            return new Message(MessageType.MSG_SUCCESS, "core_confirming", "");
         }
         return new Message(MessageType.MSG_ERROR, "core_confirming", "");
      } catch (Exception e)
      {
         return new Message(MessageType.MSG_ERROR, "core_confirming", "异常");
      }
   }

   /**
    * 查看纸质合同
    *
    * @param pid
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/searchAllFileByOwnerId", method = RequestMethod.GET)
   public List<BusinessFileEntity> searchAllFileByOwnerId(@RequestParam(value = "pid") Long pid) {

      return confirmingStockInfoService.searchAllFileByOwnerId(pid);
   }


   /**
    * 查询 保证金信息
    *
    * @param companyDealerId
    * @param objectType
    * @return
    */

   @ResponseBody
   @RequestMapping(value = "/searchMaiginAccount", method = RequestMethod.GET)
   public Message<SubmitDebitAndCredit> searchMaiginAccount(@RequestParam("companyDealerId") Long companyDealerId, @RequestParam("objectType") String objectType) {
      return confirmingStockInfoService.searchMaiginAccount(companyDealerId, objectType);
   }


   /**
    * 获取借贷企业业务列list
    * */
   @ResponseBody
   @RequestMapping(value = "/loanConfirmingStockInfoList", method = RequestMethod.POST)
   public List<ConfirmingStockInfoListResponse> loanConfirmingStockInfoList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest){
      return confirmingStockInfoService.loanConfirmingStockInfoList(confirmingStockInfoResquest);
   }

   /**
    * 获取借贷企业业务列list数量
    * */
   @ResponseBody
   @RequestMapping(value = "/countLoanConfirmingStockInfoList", method = RequestMethod.POST)
   public Message<Integer> countLoanConfirmingStockInfoList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest){
      try {
         return  confirmingStockInfoService.countLoanConfirmingStockInfoList(confirmingStockInfoResquest);
      }catch (Exception e){
          e.printStackTrace();
          return new Message(MessageType.MSG_ERROR,"confirming_core",e.getMessage());
      }
   }


   @ResponseBody
   @RequestMapping(value = "/getConfirmingStockInfoById", method = RequestMethod.GET)
    public Message<ConfirmingStockInfoEntity> getConfirmingStockInfoById(@RequestParam("pid") Long pid){
      try {
         ConfirmingStockInfoEntity confirmingStockInfoEntity = confirmingStockInfoService.getConfirmingStockInfoById(pid);
         return new Message<ConfirmingStockInfoEntity>(MessageType.MSG_SUCCESS,"confirming_core",confirmingStockInfoEntity);
      }catch (Exception e){
         return new Message(MessageType.MSG_ERROR,"confirming_core",e.getMessage());
      }
   }


   @ResponseBody
   @RequestMapping(value = "/saveOrUpdateConfirmingStockInfo", method = RequestMethod.POST)
   public Message<ConfirmingStockInfoEntity> saveOrUpdateConfirmingStockInfo(@RequestBody ConfirmingStockInfoEntity confirmingStockInfoEntity){
      try {
         return confirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);
      }catch (Exception e){
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR,"confirming_core",e.getMessage());
      }
   }
}