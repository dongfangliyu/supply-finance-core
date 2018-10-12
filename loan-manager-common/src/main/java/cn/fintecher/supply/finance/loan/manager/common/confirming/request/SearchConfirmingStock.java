package cn.fintecher.supply.finance.loan.manager.common.confirming.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tym
 *  * @date 2018/8/31
 */
@Data
public class SearchConfirmingStock extends PageInfo implements Serializable {

   @ApiModelProperty(value = "经销商企业名称")
   private String companyName;

   @ApiModelProperty(value = "合同类型")
   private String contractType;

   @ApiModelProperty(value = "确认状态")
   private int confirm_corecompany_state;

   @ApiModelProperty(value = "商务合同编号")
   private  String ContractNo;

   @ApiModelProperty(value = "融资状态")
   private  String confirm_apply_state;
}
