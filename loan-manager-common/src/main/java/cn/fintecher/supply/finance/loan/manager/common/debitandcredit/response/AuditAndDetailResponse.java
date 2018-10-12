package cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: AuditAndDetailResponse
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/29 0029上午 11:04
 */
@Data
public class AuditAndDetailResponse implements Serializable {


   //核心企业信息
   @ApiModelProperty(value = "核心企业名称 ")
   private String enterName;


   @ApiModelProperty(value = "营业执照注册号 ")
   private String enterEnpsLicense;


   @ApiModelProperty(value = "归属地 ")
   private String enterLegalAddress;


   //核心企业代表人信息
   @ApiModelProperty(value = "核心企业代表人姓名 ")
   private String enterOperationName;

   @ApiModelProperty(value = "证件号码 ")
   private String enterCardNum;


   //供应商信息
   @ApiModelProperty(value = "供应商企业名称 ")
   private String suppName;

   @ApiModelProperty(value = "营业执照注册号 ")
   private String suppEnpsLicense;

   @ApiModelProperty(value = "归属地 ")
   private String suppEnpsAddress;

   //供应商代表人信息
   @ApiModelProperty(value = "核心企业代表人姓名 ")
   private String suppOperationName;


   @ApiModelProperty(value = "证件号码 ")
   private String suppCardNum;


   //交易信息表


   @ApiModelProperty(value = "利息")
   private String interest;


   @ApiModelProperty(value = "服务费")
   private Integer serviceFee;


   @ApiModelProperty(value = "审批金额 ")
   private BigDecimal approvalAmount;

   @ApiModelProperty(value = "审批期限")
   private Long approvalTerm;

   @ApiModelProperty(value = "申请期限 ")
   private Long contractApplyTerm;

   @ApiModelProperty(value = "商务合同编号 ")
   private String contractNo;

   @ApiModelProperty(value = "申请金额")
   private BigDecimal pledgeFinanceAmount;

   @ApiModelProperty(value = "产品类型")
   private String productTypeName;


   /**
    * 开户行
    */
   @ApiModelProperty(value = "开户行")
   private String bankOpen;

   /**
    * 支行
    */
   @ApiModelProperty(value = "支行")
   private String bankBranch;

   /**
    * 账户类型
    */
   @ApiModelProperty(value = "账户类型")
   private String accountType;


   /**
    * 户名
    */
   @ApiModelProperty(value = "户名")
   private String accountName;


   /**
    * 银行卡号
    */
   @ApiModelProperty(value = "银行卡号")
   private String bankCard;


   @ApiModelProperty(value = "业务编号")
   private String bussineNo;

}
