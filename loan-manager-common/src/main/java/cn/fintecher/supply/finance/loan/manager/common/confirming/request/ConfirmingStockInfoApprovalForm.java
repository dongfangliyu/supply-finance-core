package cn.fintecher.supply.finance.loan.manager.common.confirming.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wuxiaoxing
 * @time 2018/9/10 16:40
 */
@Data
public class ConfirmingStockInfoApprovalForm implements Serializable {

    @ApiModelProperty(value = "审批状态: 2拒绝 3取消 4通过")
    private String result;

    @ApiModelProperty(value = "审批意见（原因）")
    private String content;

    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "审批金额")
    private BigDecimal contractApplyPrice;

    @ApiModelProperty(value = "审批期限")
    private Integer contractApprovalTerm;

    @ApiModelProperty(value="当前用户名称",hidden = true)
    private String currentUserName;

}
