package cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ZhangYaJun
 * @Title: SearchDebitAndCredit
 *  * @ProjectName supply-finance
 *  * @Description:
 *  * @date 2018/8/28 0028下午 15:05
 */
@Data
public class SearchDebitAndCredit extends PageInfo implements Serializable {

   @ApiModelProperty(value = "核心企业名称")
   private String dcName;
   
   @ApiModelProperty(value = "供应商名称")
   private String supName;

   @ApiModelProperty(value = "签约日期(开始)")
   private String firstTime;

   @ApiModelProperty(value = "签约日期(截止)")
   private String lastTime;

   @ApiModelProperty(value = "(签约状态")
   private String dcStatus;

}
