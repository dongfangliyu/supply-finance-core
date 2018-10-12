package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCBaseBankInfoService;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCCompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import cn.fintecher.supply.finance.loan.manager.service.utils.pdf.pdfUtil;
import com.google.common.base.Strings;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:48
 */
@Service("AuditSuppSigningService")
public class AuditSuppSigningServiceImpl implements AuditSuppSigningService {

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private FCCompanyEnterpriseService fcCompanyEnterpriseService;

    @Autowired
    private FCBaseBankInfoService fcBaseBankInfoService;

    @Autowired
    private FCProProductCore fcProProductCore;

    @Autowired
    private FCAuditCompanyCore fcAuditCompanyCore;

    @Autowired
    private FCAuditFileCore fcAuditFileCore;

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Override
    public Message searchSigningList(AuditSigningRequest auditSigningRequest) {
        PagedResponse response = new PagedResponse();
        try {
            if (!Strings.isNullOrEmpty(auditSigningRequest.getStartTime())){
                // 转换日期
                auditSigningRequest.setStartTime(DateUtil.TransformatStartTime(auditSigningRequest.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditSigningRequest.getEndTime())){
                // 转换日期
                auditSigningRequest.setEndTime(DateUtil.TransformatEndTime(auditSigningRequest.getEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Message<List<AuditOrderInfoEntity>> listMessage =auditOrderInfoCore.searchSigningList(auditSigningRequest);
        if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
            response.setData(listMessage.getMessage());
        }

        Message<Integer> countMessage = auditOrderInfoCore.searchSigningListCount(auditSigningRequest);
        if(MessageType.MSG_SUCCESS.equals(countMessage.getCode())){
            response.setTotal(Integer.parseInt(countMessage.getMessage().toString()));
        }
        response.setPageNo(auditSigningRequest.getPageNo());
        response.setPageSize(auditSigningRequest.getPageSize());
        return new Message(MessageType.MSG_SUCCESS, "audit_service", response);
    }

    @Override
    public Message searchSigningDetail(Long pid) {
        AuditSigningDetailResponse auditSigningDetailResponse = new AuditSigningDetailResponse();
        AuditOrderInfoEntity auditOrderInfoEntity =new AuditOrderInfoEntity();
        Message<AuditOrderInfoEntity> orderInfoMessage= auditOrderInfoCore.queryOrderInfoByPid(pid.toString());
        if(MessageType.MSG_SUCCESS.equals(orderInfoMessage.getCode())){
            auditOrderInfoEntity =orderInfoMessage.getMessage();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String loanTime ="";
            if(null!=auditOrderInfoEntity.getLoanTime()) {
                loanTime = sdf.format(auditOrderInfoEntity.getLoanTime());
            }
            String signingTime ="";
            if(null!=auditOrderInfoEntity.getSigningTime()){
                signingTime = sdf.format(auditOrderInfoEntity.getSigningTime());
            }
            int serviceFeeRate =0;
            if(null!=auditOrderInfoEntity.getServiceFee() && null!=auditOrderInfoEntity.getApprovalAmount() && null!=auditOrderInfoEntity.getApplicationTerm()){
                serviceFeeRate = serviceFeeRate(auditOrderInfoEntity.getServiceFee(),Double.parseDouble(auditOrderInfoEntity.getApprovalAmount()),auditOrderInfoEntity.getApplicationTerm());
            }
            auditSigningDetailResponse.setServiceFeeRate(serviceFeeRate);
            auditSigningDetailResponse.setLoanTime(loanTime);
            auditSigningDetailResponse.setLoanPeriod(loanTime+"--"+signingTime);
            auditSigningDetailResponse.setPid(pid);
            auditSigningDetailResponse.setAccountNo(auditOrderInfoEntity.getAccountno());
            auditSigningDetailResponse.setSigningTime(auditOrderInfoEntity.getSigningTime());
            auditSigningDetailResponse.setApprovalAmount(auditOrderInfoEntity.getApprovalAmount());
            auditSigningDetailResponse.setApprovalTerm(auditOrderInfoEntity.getApprovalTerm());
            auditSigningDetailResponse.setApplicationAmount(auditOrderInfoEntity.getApplicationAmount());
            auditSigningDetailResponse.setApplicationTerm(auditOrderInfoEntity.getApplicationTerm());

            //计算利息
            if(auditOrderInfoEntity.getInterestRate() > 0 && Double.parseDouble(auditOrderInfoEntity.getApprovalAmount()) > 0 && auditOrderInfoEntity.getApprovalTerm()>0){
                String a = auditOrderInfoEntity.getApprovalAmount();
                Integer b = auditOrderInfoEntity.getApprovalTerm();
                Integer c = auditOrderInfoEntity.getInterestRate();
                String interest = new BigDecimal(a)
                        .multiply(new BigDecimal(c))
                        .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(b))
                        .setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                auditSigningDetailResponse.setInterest(interest);
            }else{
                auditSigningDetailResponse.setInterest(auditOrderInfoEntity.getInterest());
            }

            auditSigningDetailResponse.setInterestRate(auditOrderInfoEntity.getInterestRate());
            auditSigningDetailResponse.setServiceFee(auditOrderInfoEntity.getServiceFee());
            auditSigningDetailResponse.setContractNo(auditOrderInfoEntity.getContractNo());
            auditSigningDetailResponse.setAccountStartTime(auditOrderInfoEntity.getAccountStartTime());
            auditSigningDetailResponse.setAccountEndTime(auditOrderInfoEntity.getAccountEndTime());
            auditSigningDetailResponse.setGuaranteeTime(auditOrderInfoEntity.getGuaranteeTime());
            auditSigningDetailResponse.setGuarantee(auditOrderInfoEntity.getGuarantee());
            auditSigningDetailResponse.setFinanceFirstTime(auditOrderInfoEntity.getFinanceFirstTime());
            auditSigningDetailResponse.setFinancialReviewTime(auditOrderInfoEntity.getFinancialReviewTime());

            //计算服务费
            if(auditOrderInfoEntity.getServiceFee() > 0 && Double.parseDouble(auditOrderInfoEntity.getApprovalAmount()) > 0 && auditOrderInfoEntity.getApprovalTerm()>0){
                Integer a = auditOrderInfoEntity.getServiceFee();
                String  b = auditOrderInfoEntity.getApprovalAmount();
                Integer C = auditOrderInfoEntity.getApprovalTerm();
                String serviceMoney = new BigDecimal(a)
                        .multiply(new BigDecimal(b))
                        .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(C))
                        .setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                auditSigningDetailResponse.setServiceMoney(serviceMoney);
            }

            //获取核心企业
            if(auditOrderInfoEntity.getEnterpriseId()!=null && auditOrderInfoEntity.getEnterpriseId()>0){
                CompanyEnterpriseEntity companyEnterpriseEntity =fcCompanyEnterpriseService.selectCompanyEnterpriseByPid(auditOrderInfoEntity.getEnterpriseId());

                if(companyEnterpriseEntity != null){
                    auditSigningDetailResponse.setEnterName(companyEnterpriseEntity.getName());
                    auditSigningDetailResponse.setEnterEnpsLicense(companyEnterpriseEntity.getEnpsLicense());
                    auditSigningDetailResponse.setEnterOperatingAddressDetail(companyEnterpriseEntity.getOperatingAddressDetail());
                    CompanyOperationEntity companyOperationEntity = fcCompanyEnterpriseService.searchCompanyLegal(companyEnterpriseEntity.getPid());
                    if(companyOperationEntity!=null){
                        auditSigningDetailResponse.setEnterOperationName(companyOperationEntity.getName());
                        auditSigningDetailResponse.setEnterCardNum(companyOperationEntity.getCardNum());
                    }
                    CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity =fcCompanyEnterpriseService.selectCompanyEnterpriseInfoByEnterpriseId(auditOrderInfoEntity.getEnterpriseId());
                    if(companyEnterpriseInfoEntity!=null){
                        auditSigningDetailResponse.setEnterOperatingProvinceName(companyEnterpriseInfoEntity.getOperatingAddressProvinceName());
                        auditSigningDetailResponse.setEnterOperatingCityName(companyEnterpriseInfoEntity.getOperatingAddressCityName());
                        auditSigningDetailResponse.setEnterOperatingCountyName(companyEnterpriseInfoEntity.getOperatingAddressCountyName());
                    }
                }

                AuditCreditInfoEntity creditInfo = fcAuditCompanyCore.searchEntityByEnterpriseId(auditOrderInfoEntity.getEnterpriseId());
                if(creditInfo!=null){
                    ProProductForm proProductForm = new ProProductForm();
                    proProductForm.setCategory(creditInfo.getProductType());
                    List<ProProductEntity> list = fcProProductCore.searchProductList(proProductForm);
                    if(list!= null && list.size()>0 && list.get(0) != null){
                        auditSigningDetailResponse.setProductTypeName(list.get(0).getName());
                    }
                }
            }

            //获取供应商
            if(auditOrderInfoEntity.getSupplierId()!=null && auditOrderInfoEntity.getSupplierId()>0){
                CompanyEnterpriseEntity companyEnterpriseEntity =fcCompanyEnterpriseService.selectCompanyEnterpriseByPid(auditOrderInfoEntity.getSupplierId());
                if(companyEnterpriseEntity != null && companyEnterpriseEntity.getPid()>0){
                    auditSigningDetailResponse.setSuppName(companyEnterpriseEntity.getName());
                    auditSigningDetailResponse.setSuppEnpsLicense(companyEnterpriseEntity.getEnpsLicense());
                    auditSigningDetailResponse.setSuppOperatingAddressDetail(companyEnterpriseEntity.getOperatingAddressDetail());
                    CompanyOperationEntity companyOperationEntity = fcCompanyEnterpriseService.searchCompanyLegal(companyEnterpriseEntity.getPid());
                    if(companyOperationEntity!=null){
                        auditSigningDetailResponse.setSuppOperationName(companyOperationEntity.getName());
                        auditSigningDetailResponse.setSuppCardNum(companyOperationEntity.getCardNum());
                    }

                    CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity =fcCompanyEnterpriseService.selectCompanyEnterpriseInfoByEnterpriseId(auditOrderInfoEntity.getSupplierId());
                    if(companyEnterpriseInfoEntity!=null){
                        auditSigningDetailResponse.setSuppOperatingProvinceName(companyEnterpriseInfoEntity.getOperatingAddressProvinceName());
                        auditSigningDetailResponse.setSuppOperatingCityName(companyEnterpriseInfoEntity.getOperatingAddressCityName());
                        auditSigningDetailResponse.setSuppOperatingCountyName(companyEnterpriseInfoEntity.getOperatingAddressCountyName());
                    }

                    //放款账户
                    BaseBankInfoEntity baseBankInfoEntity = fcBaseBankInfoService.getLoanBankByCompanyId(companyEnterpriseEntity.getPid());
                    if(baseBankInfoEntity!=null && baseBankInfoEntity.getPid()>0){
                        auditSigningDetailResponse.setLoanBankOpen(baseBankInfoEntity.getBankOpen());
                        auditSigningDetailResponse.setLoanBankBranch(baseBankInfoEntity.getBankBranch());
                        auditSigningDetailResponse.setLoanAccountName(baseBankInfoEntity.getAccountName());
                        auditSigningDetailResponse.setLoanAccountType(baseBankInfoEntity.getAccountType());
                        auditSigningDetailResponse.setLoanAccountUse(baseBankInfoEntity.getAccountUse());
                        auditSigningDetailResponse.setLoanBankCard(baseBankInfoEntity.getBankCard());
                    }
                }
            }

            //还款账号
            BaseBankInfoEntity baseBankInfoEntity = fcBaseBankInfoService.getRepayBankBySignId(auditOrderInfoEntity.getPid());
            if(baseBankInfoEntity!=null && baseBankInfoEntity.getPid()>0){
                auditSigningDetailResponse.setRepayBankId(baseBankInfoEntity.getPid());
                auditSigningDetailResponse.setRepayBankOpen(baseBankInfoEntity.getBankOpen());
                auditSigningDetailResponse.setRepayBankBranch(baseBankInfoEntity.getBankBranch());
                auditSigningDetailResponse.setRepayAccountName(baseBankInfoEntity.getAccountName());
                auditSigningDetailResponse.setRepayAccountType(baseBankInfoEntity.getAccountType());
                auditSigningDetailResponse.setRepayAccountUse(baseBankInfoEntity.getAccountUse());
                auditSigningDetailResponse.setRepayBankCard(baseBankInfoEntity.getBankCard());
            }

            return new Message(MessageType.MSG_SUCCESS,"audit_service",auditSigningDetailResponse);
        }else{
            return orderInfoMessage;
        }
    }

    private int serviceFeeRate(int serviceFee, Double approvalAmount,int applicationTerm){
        BigDecimal b1 =new BigDecimal(approvalAmount);
        BigDecimal b2 =new BigDecimal(serviceFee);
        BigDecimal b5 =new BigDecimal(100);
        b2 = b2.divide(b5,2, RoundingMode.HALF_UP);
        BigDecimal b3 =new BigDecimal(360);
        BigDecimal b4 =new BigDecimal(applicationTerm);
        BigDecimal divide = b1.multiply(b2).multiply(b4).divide(b3, 2, RoundingMode.HALF_UP);
        return divide.intValue();
    }

    @Override
    public Message submitSigningAccount(AuditRepayBankInfoForm auditRepayBankInfoForm) {
        BaseBankInfoEntity baseBankInfoEntity = new BaseBankInfoEntity();
        baseBankInfoEntity.setBankOpen(auditRepayBankInfoForm.getBankOpen());
        baseBankInfoEntity.setBankBranch(auditRepayBankInfoForm.getBankBranch());
        baseBankInfoEntity.setAccountType(auditRepayBankInfoForm.getAccountType());
        baseBankInfoEntity.setObjectId(auditRepayBankInfoForm.getId());
        baseBankInfoEntity.setObjectType("2");
        baseBankInfoEntity.setAccountName(auditRepayBankInfoForm.getAccountName());
        baseBankInfoEntity.setAccountUse(auditRepayBankInfoForm.getAccountUse());
        baseBankInfoEntity.setBankCard(auditRepayBankInfoForm.getBankCard());
        baseBankInfoEntity.setStatus("NOL");
        baseBankInfoEntity.setUpdateBy(auditRepayBankInfoForm.getCurrentUserName());
        baseBankInfoEntity.setUpdateAt(new Date());
        if(auditRepayBankInfoForm.getBankId()!=null && auditRepayBankInfoForm.getBankId()>0){
            baseBankInfoEntity.setPid(auditRepayBankInfoForm.getBankId());
            return fcBaseBankInfoService.update(baseBankInfoEntity);
        }else{
            baseBankInfoEntity.setCreateAt(new Date());
            baseBankInfoEntity.setCreateBy(auditRepayBankInfoForm.getCurrentUserName());
            return fcBaseBankInfoService.insert(baseBankInfoEntity);
        }
    }

    @Override
    public Message submitSigning(Long pid, String name) {
        try {
            Message<AuditOrderInfoEntity> infoEntityMessage =  auditOrderInfoCore.queryOrderInfoByPid(pid.toString());
            if(MessageType.MSG_SUCCESS.equals(infoEntityMessage.getCode())){
                AuditOrderInfoEntity auditOrderInfoEntity =infoEntityMessage.getMessage();
                BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
                Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
               if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                   businessOrderEntity = message.getMessage();
                   businessOrderEntity.setProcessStatus("41");
                   businessOrderEntity.setUpdateAt(new Date());
                   businessOrderEntity.setUpdateBy(name);
                   Message result = businessOrderCore.updateOrder(businessOrderEntity);
                   if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                       auditOrderInfoEntity.setState("41");
                       auditOrderInfoEntity.setUpdateBy(name);
                       auditOrderInfoEntity.setUpdateAt(new Date());
                       auditOrderInfoEntity.setSigningTime(new Date());
                       return auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                   }else{
                       return result;
                   }
               }else{
                   return message;
               }
            }else{
                return infoEntityMessage;
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
        }

    }

    @Override
    public ResponseEntity<byte[]> downloadContract(Long pid)throws IOException, TemplateException {
        HttpHeaders headers = new HttpHeaders();
        String factoringOutPath = File.separator + System.currentTimeMillis()+".pdf";
        Map<String,Object> params = new HashMap<>();
        pdfUtil.generator(FlagTool.Proxy_PATH +File.separator+factoringOutPath, "signContract.ftl", params);
        //System.out.println("======>>>>>>"+FlagTool.Proxy_PATH +File.separator+factoringOutPath);
        String fileName = FlagTool.Proxy_PATH +File.separator+factoringOutPath;
        InputStream in = new FileInputStream(fileName);
        byte[] data = toByteArray(in);
        in.close();
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            Boolean succeedDelete = file.delete();
        }
        return new ResponseEntity(data, headers, HttpStatus.CREATED);
    }


    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    @Override
    public Message isUpLoadContract(Long pid) {
        List<AuditFileEntity> list =  fcAuditFileCore.searchAllFileByOwnerId(pid.toString());
        if(list!=null && list.size()>=4){
            int type1 = 0;
            int type2 = 0;
            int type3 = 0;
            int type4 = 0;
            for(AuditFileEntity auditFileEntity : list){
                if("factorFinancingAgreement".equals(auditFileEntity.getCategory())){
                    type1=1;
                }
                if("transferAccountsReceivable".equals(auditFileEntity.getCategory())){
                    type2=1;
                }
                if("confirmationAccountsReceivable".equals(auditFileEntity.getCategory())){
                    type3=1;
                }
                if("managementServiceAgreement".equals(auditFileEntity.getCategory())){
                    type4=1;
                }
            }

            if(type1==1&&type2==1&&type3==1&&type4==1){
                return new Message(MessageType.MSG_SUCCESS,"audit_service",null);
            }else{
                return new Message(MessageType.MSG_ERROR,"audit_service","请先上传纸质合同！");
            }

        }else{
            return new Message(MessageType.MSG_ERROR,"audit_service","请先上传纸质合同！");
        }
    }
}
