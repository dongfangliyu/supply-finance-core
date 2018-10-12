package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:36
 */
@Data
public class AuditSuppReviewRequest extends PageInfo implements Serializable {

    @ApiModelProperty(value = "核心企业名称")
    private  String enterpriseName;

    @ApiModelProperty(value="供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "供应商id",hidden = true)
    private  Long supplierId;

    @ApiModelProperty(value="核心企业id",hidden = true)
    private Long enterpriseId;

    @ApiModelProperty(value = "放款开始时间")
    private String startTime;

    @ApiModelProperty(value = "放款结束时间")
    private  String endTime;

    @ApiModelProperty(value = "应还开始时间")
    private  String shouldStartTime;

    @ApiModelProperty(value = "应还结束时间")
    private  String shouldEndTime;

    @ApiModelProperty(value = "状态（1：未还款 2：已还款（未结清）3：已还款（已结清）4：未还款（已逾期）5：已还款（未结清、已逾期））")
    private  String state;

    @ApiModelProperty(value="当前登录用户名",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前登录用户企业ID",hidden = true)
    private Long currentCompanyId;

}
