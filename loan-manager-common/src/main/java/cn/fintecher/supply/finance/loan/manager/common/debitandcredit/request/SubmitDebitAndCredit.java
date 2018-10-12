package cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangYaJun
 * @Title: SubmitDebitAndCredit
 * @ProjectName supply-finance
 * @Description: 签约提交数据
 * @date 2018/8/29 0029下午 17:45
 */

@Data
public class SubmitDebitAndCredit implements Serializable {


   @ApiModelProperty(value = "签约主键id")
   private Long pid;

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
    * 银行卡号
    */
   @ApiModelProperty(value = "银行卡号")
   private String bankCard;

   @ApiModelProperty(value = "当前操作人", hidden = true)
   private String currentUserName;

   @ApiModelProperty(value = "户名")
   private String accountName;
}
