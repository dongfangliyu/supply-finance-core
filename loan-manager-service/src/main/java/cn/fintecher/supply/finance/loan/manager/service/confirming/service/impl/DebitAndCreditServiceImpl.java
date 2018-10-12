package cn.fintecher.supply.finance.loan.manager.service.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseBankInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFastFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCDebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.DebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: DebitAndCreditServiceImpl
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 15:35
 */
@Service
public class DebitAndCreditServiceImpl implements DebitAndCreditService {

   private static final Logger LOGGER = LoggerFactory.getLogger(DebitAndCreditServiceImpl.class);

   @Autowired
   private FCDebitAndCreditService fCDebitAndCreditService;

   @Autowired
   private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

   @Autowired
   private FCProProductCore fCProProductCore;

   @Autowired
   private FCBaseBankInfoCore fCBaseBankInfoCore;

   @Autowired
   private BusinessFastFileCore businessFastFileCore;


   @Autowired
   private BusinessFileCore businessFileCore;

   @Override
   public Message<PagedResponse<List<ConfirmingStockList>>> searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit) {
	  
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

   /**
    * 详情
    *
    * @param pid
    * @return
    */

   @Override
   public Message queryOrderInfoByPid(String pid) {
      AuditAndDetailResponse auditAndDetailResponse = new AuditAndDetailResponse();
      ConfirmingStockInfoEntity confirmingStockInfoEntity = null;
      Message<ConfirmingStockInfoEntity> confirmingStockInfoEntityMessage = fCDebitAndCreditService.queryOrderInfoByPid(pid);
      if (MessageType.MSG_SUCCESS.equals(confirmingStockInfoEntityMessage.getCode()))
      {
         confirmingStockInfoEntity = confirmingStockInfoEntityMessage.getMessage();
         auditAndDetailResponse.setBussineNo(confirmingStockInfoEntity.getBussineNo());
         auditAndDetailResponse.setApprovalAmount(confirmingStockInfoEntity.getContractApplyPrice());
         auditAndDetailResponse.setPledgeFinanceAmount(confirmingStockInfoEntity.getConfirmNotAmount());
         auditAndDetailResponse.setContractNo(confirmingStockInfoEntity.getContractNo());
         auditAndDetailResponse.setApprovalTerm(confirmingStockInfoEntity.getContractApprovalTerm());
         auditAndDetailResponse.setContractApplyTerm(confirmingStockInfoEntity.getContractApplyTerm());
         // 核心企业信息
         CompanyEnterpriseEntity companyEnterpriseEntity = null;
         if (null != confirmingStockInfoEntity.getCompanyId())
         {
            companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(confirmingStockInfoEntity.getCompanyId());
         }

         if (!ChkUtil.isEmpty(companyEnterpriseEntity))
         {
            auditAndDetailResponse.setEnterName(companyEnterpriseEntity.getName());
            auditAndDetailResponse.setEnterEnpsLicense(companyEnterpriseEntity.getEnpsLicense());
            auditAndDetailResponse.setEnterLegalAddress(companyEnterpriseEntity.getLegalAddress());
            CompanyOperationEntity companyOperationEntity = fcCompanyEnterpriseCore.searchCompanyLegal(companyEnterpriseEntity.getPid());
            if (ChkUtil.isEmpty(companyOperationEntity))
            {
               auditAndDetailResponse.setEnterOperationName(companyOperationEntity.getName());
               auditAndDetailResponse.setEnterCardNum(companyOperationEntity.getCardNum());
            }

         }
         //产品信息
         String proRoductId = confirmingStockInfoEntity.getProProductId().toString();
         ProProductEntity proProductEntity = null;
         if (!ChkUtil.isEmpty(proRoductId))
         {
            proProductEntity = fCProProductCore.searchProductDetail(proRoductId);
         }
         if (!ChkUtil.isEmpty(proProductEntity))
         {
            auditAndDetailResponse.setProductTypeName(proProductEntity.getCategory());
         }
         //  计算 利息
         int i = 0;
         if (proProductEntity.getInterestRate() != null && proProductEntity.getInterestRate() > 0
                 && confirmingStockInfoEntity.getContractApplyPrice() != null && confirmingStockInfoEntity.getContractApprovalTerm() != null && confirmingStockInfoEntity.getContractApprovalTerm() > 0)
         {

            String interest = confirmingStockInfoEntity.getContractApplyPrice()
                    .multiply(new BigDecimal(proProductEntity.getInterestRate()))
                    .divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(confirmingStockInfoEntity.getContractApprovalTerm()))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            auditAndDetailResponse.setInterest(interest);
         } else
         {
            auditAndDetailResponse.setInterest("0");
         }

         //  计算 服务费
         if (proProductEntity.getPlatformServiceFee() != null && confirmingStockInfoEntity.getContractApplyPrice() != null && confirmingStockInfoEntity.getContractApplyTerm() != null)
         {
            i = serviceFeeRate(proProductEntity.getPlatformServiceFee(), confirmingStockInfoEntity.getContractApplyPrice(), confirmingStockInfoEntity.getContractApplyTerm());
            auditAndDetailResponse.setServiceFee(i);
         } else
         {
            auditAndDetailResponse.setServiceFee(0);
         }

         //  供应商信息
         Long companyDealerId = confirmingStockInfoEntity.getCompanyDealerId();
         CompanyEnterpriseEntity entity = null;
         if (!ChkUtil.isEmpty(companyDealerId))
         {
            entity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(companyDealerId);
            if (!ChkUtil.isEmpty(entity) && entity.getPid() > 0)
            {


               auditAndDetailResponse.setSuppName(entity.getName());
               auditAndDetailResponse.setSuppEnpsAddress(entity.getLegalAddress());
               auditAndDetailResponse.setSuppEnpsLicense(entity.getEnpsLicense());

               CompanyOperationEntity legal = fcCompanyEnterpriseCore.searchCompanyLegal(entity.getPid());
               if (!ChkUtil.isEmpty(legal))
               {
                  auditAndDetailResponse.setSuppCardNum(legal.getCardNum());
                  auditAndDetailResponse.setSuppOperationName(legal.getName());
               }
            }
         }

         //  经销商的基本户信息
         Message<BaseBankInfoEntity> baseBankInfoEntity = fCDebitAndCreditService.queryConfirmingBankInfo(companyDealerId, "4");
         if (MessageType.MSG_SUCCESS.equals(baseBankInfoEntity.getCode()))
         {
            BaseBankInfoEntity message = baseBankInfoEntity.getMessage();
            if (!ChkUtil.isEmpty(message))
            {
               auditAndDetailResponse.setBankOpen(message.getBankOpen());
               auditAndDetailResponse.setBankBranch(message.getBankBranch());
               auditAndDetailResponse.setAccountType(message.getAccountType());
               auditAndDetailResponse.setAccountName(message.getAccountName());
               auditAndDetailResponse.setBankCard(message.getBankCard());
            }
         }
         return new Message(MessageType.MSG_SUCCESS, "confirming", auditAndDetailResponse);
      } else
      {
         return confirmingStockInfoEntityMessage;
      }
   }


   /**
    * @param submitDebitAndCredit
    * @return
    */
   @Override
   public Message submitDebitAndCredit(SubmitDebitAndCredit submitDebitAndCredit) {

      try
      {
         BaseBankInfoEntity baseBankInfoEntity = new BaseBankInfoEntity();
         baseBankInfoEntity.setBankOpen(submitDebitAndCredit.getBankOpen());
         baseBankInfoEntity.setBankCard(submitDebitAndCredit.getBankCard());
         baseBankInfoEntity.setBankBranch(submitDebitAndCredit.getBankBranch());
         baseBankInfoEntity.setAccountName(submitDebitAndCredit.getAccountName());
         baseBankInfoEntity.setAccountType(submitDebitAndCredit.getAccountType());
         baseBankInfoEntity.setAccountUse("3");
         baseBankInfoEntity.setCreateAt(new Date());
         baseBankInfoEntity.setCreateBy(submitDebitAndCredit.getCurrentUserName());
         baseBankInfoEntity.setStatus("NOL");
         baseBankInfoEntity.setObjectType("4");
         Message<ConfirmingStockInfoEntity> confirmingStockInfoEntityMessage = fCDebitAndCreditService.queryOrderInfoByPid(submitDebitAndCredit.getPid().toString());
         if (!ChkUtil.isEmpty(confirmingStockInfoEntityMessage.getMessage()))
         {
            Long companyDealerId = confirmingStockInfoEntityMessage.getMessage().getCompanyDealerId();
            baseBankInfoEntity.setObjectId(companyDealerId);
            Long ccompanyDealerId = confirmingStockInfoEntityMessage.getMessage().getCompanyDealerId();
            baseBankInfoEntity.setObjectId(ccompanyDealerId);
         }
         Message insert = fCBaseBankInfoCore.insert(baseBankInfoEntity);

      } catch (Exception e)
      {

         return new Message(MessageType.MSG_ERROR, "confrirming", "插入失败");
      }
      return new Message(MessageType.MSG_SUCCESS, "confrirming", "插入保证金账户成功");
   }

   @Override
   public Message submitDebitAndCreditInfo(Long pid, String STATUS) {

      try
      {
         ConfirmingStockInfoEntity confirmingStockInfoEntity = new ConfirmingStockInfoEntity();
         confirmingStockInfoEntity.setPid(pid);
         confirmingStockInfoEntity.setContractState(STATUS);
         confirmingStockInfoEntity.setContractPlatformTime(new Date());
         fCDebitAndCreditService.submitDebitAndCredit(confirmingStockInfoEntity);
         return new Message(MessageType.MSG_SUCCESS, "confrirming", "签约成功");
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "confrirming", "签约状态修改失败");
      }
   }


   @Override
   public Message upload(MultipartFile file, String type, String registerCode, Long id) {

      ResponseEntity<Message> entity = businessFastFileCore.fileUpload(file);
      Message message = new Message<>();
      message = entity.getBody();
      HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
      if (message.getCode() == 0)
      {
         BusinessFileEntity fileEntity = new BusinessFileEntity();
         fileEntity.setFileName(file.getOriginalFilename());
         fileEntity.setCategory(type);
         fileEntity.setCommodityId(null);
         fileEntity.setOwnerId(id.toString());
         fileEntity.setPath(params.get("path"));
         fileEntity.setFullPath(params.get("fullPath"));
         fileEntity.setGroup(params.get("group"));
         fileEntity.setStatus(Constants.DATA_STATUS_NOL);
         fileEntity.setCreateAt(new Date());
         fileEntity.setUpdateAt(new Date());
         try
         {
            message = businessFileCore.insertFile(fileEntity);
         } catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return message;
   }

   /**
    * 判断合同是否上传
    *
    * @param pid
    * @return
    */
   @Override
   public Message isUpLoadContract(Long pid) {
      List<BusinessFileEntity> list = fCDebitAndCreditService.searchAllFileByOwnerId(pid);
      if (list != null && list.size() > 0)
      {
         int type1 = 0;
         int type2 = 0;
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
         }
         if (type1 == 1 && type2 == 1)
         {
            return new Message(MessageType.MSG_SUCCESS, "confirming_service", null);
         } else
         {
            return new Message(MessageType.MSG_ERROR, "confirming_service", "合同不完整！");
         }
      }
         return new Message(MessageType.MSG_ERROR, "confirming_service", "请先上传纸质合同！");

   }

   @Override
   public Message<List<BusinessFileEntity>> searchAllAuditFileByOwnerId(String pid) {
      return businessFileCore.queryEnterFileList(pid);
   }

   @Override
   public Message searchMaiginAccount(Long pid) {
      try
      {
         Message<ConfirmingStockInfoEntity> confirmingStockInfoEntityMessage = fCDebitAndCreditService.queryOrderInfoByPid(pid.toString());
         if (MessageType.MSG_SUCCESS.equals(confirmingStockInfoEntityMessage.getCode()))
         {
            ConfirmingStockInfoEntity message = confirmingStockInfoEntityMessage.getMessage();
            if (message.getCompanyDealerId() != null)
            {
               Message msS = fCDebitAndCreditService.searchMaiginAccount(message.getCompanyDealerId(), "4");
               return msS;
            }
         }
         return new Message(MessageType.MSG_ERROR, "service", "null");
      } catch (Exception e)
      {
         e.printStackTrace();
         return new Message(MessageType.MSG_ERROR, "service", "null");
      }
   }


   private int serviceFeeRate(Long serviceFee, BigDecimal approvalAmount, Long applicationTerm) {
      BigDecimal b2 = new BigDecimal(serviceFee);
      BigDecimal b5 = new BigDecimal(100);
      b2 = b2.divide(b5, 2, RoundingMode.HALF_UP);
      BigDecimal b3 = new BigDecimal(360);
      BigDecimal b4 = new BigDecimal(applicationTerm);
      BigDecimal divide = approvalAmount.multiply(b2).multiply(b4).divide(b3, 2, RoundingMode.HALF_UP);
      return divide.intValue();
   }
}