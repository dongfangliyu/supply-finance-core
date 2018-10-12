package cn.fintecher.supply.finance.loan.manager.service.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCDebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.PlatformSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningServiceImpl
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:43
 */

@Service
public class PlatformSigningServiceImpl implements PlatformSigningService {

   @Autowired
   private FCDebitAndCreditService fCDebitAndCreditService;

   @Override
   public Message searchPlatformSigningList(SearchDebitAndCredit searchDebitAndCredit) {
	   if (searchDebitAndCredit.getFirstTime() != null)
	      {
	         try
	         {
	            searchDebitAndCredit.setFirstTime(DateUtil.TransformatStartTime(searchDebitAndCredit.getFirstTime()));
	         } catch (ParseException e)
	         {
	            e.printStackTrace();
	         }
	      }
	      if (searchDebitAndCredit.getLastTime() != null)
	      {
	         try
	         {
	            searchDebitAndCredit.setLastTime(DateUtil.TransformatEndTime(searchDebitAndCredit.getLastTime()));
	         } catch (ParseException e)
	         {
	            e.printStackTrace();
	         }
	      }
	   Message<PagedResponse<List<ConfirmingStockList>>> msg = new Message<>(MessageType.MSG_SUCCESS, "confirming", null);
      PagedResponse<List<ConfirmingStockList>> response = new PagedResponse<>();
      //默认未签约 0 ，经销商签约 1，核心企业签约 2 ，平台签约 3
      Message<List<ConfirmingStockList>> confirmingStockListMessage = fCDebitAndCreditService.searchDebitAndCreditList(searchDebitAndCredit);
      if (MessageType.MSG_ERROR.equals(confirmingStockListMessage.getCode()))
      {
         return new Message<>(MessageType.MSG_ERROR, "confirming", null);
      }
      List<ConfirmingStockList> message = confirmingStockListMessage.getMessage();
      Message<Integer> integerMessage = fCDebitAndCreditService.qureyPageCount(searchDebitAndCredit);
      if (integerMessage.getCode() == MessageType.MSG_ERROR)
      {
         return new Message<>(MessageType.MSG_ERROR, "confirming", null);
      }
      Integer message1 = integerMessage.getMessage();
      response.setData(message);
      response.setTotal(message1);
      response.setPageNo(searchDebitAndCredit.getPageNo());
      response.setPageSize(searchDebitAndCredit.getPageSize());
      msg.setMessage(response);
      return msg;
   }

   @Override
   public Message searchPlatformSigningDetail(Long pid) {

      try
      {
         return fCDebitAndCreditService.queryOrderInfoByPid(pid.toString());
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "", "");
      }

   }

   @Override
   public Message isUpLoadContract(Long pid) {
      List<BusinessFileEntity> list = fCDebitAndCreditService.searchAllFileByOwnerId(pid);
      if (list != null && list.size() > 0)
      {
         int type1 = 0;
         int type2 = 0;
         int type3 = 0;
         for (BusinessFileEntity auditFileEntity : list)
         {
            // 自定义保兑仓融资协议上传code
            if ("bdcrzxy".equals(auditFileEntity.getCategory()))
            {
               type1 = 1;
            }
            // 自定义动产质押上传code
            if ("dczyxy".equals(auditFileEntity.getCategory()))
            {
               type2 = 1;
            }
            if ("sphgxy".equals(auditFileEntity.getCategory()))
            {
               type3 = 1;
            }
         }
         if (type1 == 1 && type2 == 1 && type3 == 1)
         {
            return new Message(MessageType.MSG_SUCCESS, "confirming_service", null);
         } else
         {
            return new Message(MessageType.MSG_ERROR, "confirming_service", "合同不完整！");
         }
      }
      return new Message(MessageType.MSG_ERROR, "confirming_service", "请先上传纸质合同！");

   }

}
