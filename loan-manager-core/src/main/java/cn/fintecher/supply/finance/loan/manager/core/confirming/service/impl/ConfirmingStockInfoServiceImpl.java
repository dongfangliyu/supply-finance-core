package cn.fintecher.supply.finance.loan.manager.core.confirming.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.core.confirming.dao.ConfirmingStockInfoDao;
import cn.fintecher.supply.finance.loan.manager.core.confirming.service.ConfirmingStockInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyajun
 * @email
 * @date 2018-08-28 16:02:16
 */
@Service("confirmingStockInfoService")
public class ConfirmingStockInfoServiceImpl implements ConfirmingStockInfoService {

   private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmingStockInfoServiceImpl.class);

   @Autowired
   private ConfirmingStockInfoDao confirmingstockInfoDao;

   @Override
   public Integer insertStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity) {
      return confirmingstockInfoDao.insertStockInfo(confirmingStockInfoEntity);
   }

   @Override
   public List<ConfirmingStockInfoEntity> selectByStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity) {
      return confirmingstockInfoDao.selectByStockInfo(confirmingStockInfoEntity);
   }

   @Override
   public Integer updateStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity) {
      // TODO Auto-generated method stub
      return confirmingstockInfoDao.updateStockInfo(confirmingStockInfoEntity);
   }

   @Override
   public ConfirmingStockInfoEntity queryStockInfoByPid(String pid) {
      // TODO Auto-generated method stub
      return confirmingstockInfoDao.queryStockInfoByPid(pid);
   }

   @Override
   public Message<List<ConfirmingStockList>> searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit) {
      Message msg = new Message();
      try
      {
         List confirmingStockList = confirmingstockInfoDao.searchDebitAndCreditList(searchDebitAndCredit);
         if (ChkUtil.isEmpty(confirmingStockList))
         {
            msg.setCode(MessageType.MSG_SUCCESS);
            msg.setMessage(null);
            return msg;
         }
         msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
         msg.setMessage(confirmingStockList);
         msg.setCode(0);
      } catch (Exception e)
      {
         msg.setCode(-1);
         LOGGER.error("查询借贷方签约列表失败");
         e.printStackTrace();
      }
      return msg;
   }


   public Message<Integer> qureyPageCount(SearchDebitAndCredit searchDebitAndCredit) {

      Message msg = new Message();
      try
      {
         Integer integer = confirmingstockInfoDao.qureyPageCount(searchDebitAndCredit);
         if (ChkUtil.isEmpty(integer))
         {
            msg.setCode(MessageType.MSG_ERROR);
            msg.setMessage(null);
            return msg;
         }
         msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
         msg.setMessage(integer);
         msg.setCode(0);
      } catch (Exception e)
      {
         msg.setCode(-1);
         LOGGER.error("查询借贷方签约列表总数失败");
         e.printStackTrace();
      }
      return msg;
   }

   /**
    * @param pid 主键查找
    * @return
    */
   @Override
   public Message<ConfirmingStockInfoEntity> queryOrderInfoByPid(String pid) {

      Message msg = new Message();
      try
      {
         ConfirmingStockInfoEntity confirmingStockInfoEntity = confirmingstockInfoDao.queryStockInfoByPid(pid);
         if (ChkUtil.isEmpty(confirmingStockInfoEntity))
         {
            msg.setCode(MessageType.MSG_ERROR);
            msg.setMessage(null);
            return msg;
         }
         msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
         msg.setMessage(confirmingStockInfoEntity);
         msg.setCode(MessageType.MSG_SUCCESS);
      } catch (Exception e)
      {
         msg.setCode(MessageType.MSG_ERROR);
         LOGGER.error("查询借贷方签约列表详情失败");
         e.printStackTrace();
      }
      return msg;
   }


   @Override
   public BaseBankInfoEntity queryConfirmingBankInfo(Long companyDealerId, String objectId) {

      return confirmingstockInfoDao.queryCongirmingBankInfo(companyDealerId, objectId);
   }

   @Override
   public List<BusinessFileEntity> searchAllFileByOwnerId(Long pid) {
      return confirmingstockInfoDao.searchAllFileByOwnerId(pid);
   }

   @Override
   public Message<SubmitDebitAndCredit> searchMaiginAccount(Long companyDealerId, String objectType) {
      try
      {
         List<SubmitDebitAndCredit> submitDebitAndCredit = confirmingstockInfoDao.searchMaiginAccount(companyDealerId, objectType);
         if (submitDebitAndCredit.size() > 1)
         {
            return new Message(MessageType.MSG_ERROR, "base_core", "查询失败");
         }
         return new Message(MessageType.MSG_SUCCESS, "base_core", submitDebitAndCredit.get(0));
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "base_core", e.getMessage());
      }
   }

   @Override
   public List<ConfirmingStockInfoListResponse> loanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest) {
      return confirmingstockInfoDao.loanConfirmingStockInfoList(confirmingStockInfoResquest);
   }

   @Override
   public Message<Integer> countLoanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest) {
      try {
         return new Message<Integer>(MessageType.MSG_SUCCESS, "confirming_core", confirmingstockInfoDao.countLoanConfirmingStockInfoList(confirmingStockInfoResquest));
      }catch (Exception e){
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR,"confirming_core",e.getMessage());
      }
   }

   @Override
   public ConfirmingStockInfoEntity getConfirmingStockInfoById(Long pid) {
      return confirmingstockInfoDao.queryStockInfoByPid(pid.toString());
   }

   @Override
   public Message<ConfirmingStockInfoEntity> saveOrUpdateConfirmingStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity) {
      if(confirmingStockInfoEntity.getPid()!=null && confirmingStockInfoEntity.getPid()>0){
         confirmingstockInfoDao.updateStockInfo(confirmingStockInfoEntity);
      }else{
         confirmingstockInfoDao.insertStockInfo(confirmingStockInfoEntity);
         Long pid =confirmingStockInfoEntity.getPid();

         String bussineNo = "YW"+new SimpleDateFormat("yyyyMMdd").format(new Date())+243;
         if(pid>0&&pid<10){
            bussineNo +="000"+pid;
         }else if(pid>=10&&pid<100){
            bussineNo +="00"+pid;
         }else if(pid>=100&&pid<1000) {
            bussineNo +="0"+pid;
         }else{
            bussineNo +=pid;
         }
         confirmingStockInfoEntity.setBussineNo(bussineNo);
         confirmingstockInfoDao.updateStockInfo(confirmingStockInfoEntity);
      }
     return new Message<ConfirmingStockInfoEntity>(MessageType.MSG_SUCCESS, "confirming_core", confirmingStockInfoEntity);
   }
}
