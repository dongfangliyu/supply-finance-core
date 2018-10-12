package cn.fintecher.supply.finance.loan.manager.common.audit.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 18:00
 */
@Data
public class AuditSigningRequest extends PageInfo implements Serializable {

    @ApiModelProperty(value="核心企业名称")
    private String enterpriseName;

    @ApiModelProperty(value="供应商名称")
    private String supplierName;

    @ApiModelProperty(value="应收账款编号",hidden = true)
    private String accountNo;

    @ApiModelProperty(value="签约开始时间")
    private String startTime;

    @ApiModelProperty(value="签约结束时间")
    private String endTime;

    @ApiModelProperty(value="审核状态(0:待签约 1:已签约)")
    private String state;

    @ApiModelProperty(value ="是否同意担保（0：不同意  1：同意）")
    private String guarantee;

    @ApiModelProperty(value="当前登录用户名",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前登录用户企业ID",hidden = true)
    private Long currentCompanyId;
}
