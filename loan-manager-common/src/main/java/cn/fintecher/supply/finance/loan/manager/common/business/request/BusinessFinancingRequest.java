package cn.fintecher.supply.finance.loan.manager.common.business.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/16 14:45
 */
@Data
public class BusinessFinancingRequest extends PageInfo implements Serializable {

    @ApiModelProperty(value="核心企业名称")
    private String enterpriseName;

    @ApiModelProperty(value="账款起日")
    private String accountStartTime;

    @ApiModelProperty(value="账款止日")
    private String accountEndTime;

    @ApiModelProperty(value="账款凭证类型")
    private String certificateCategory;

    @ApiModelProperty(value="融资申请状态 0 待申请，1申请中，2申请通过， 3申请不通过")
    private String financingStatus;

    @ApiModelProperty(value="合同编号")
    private String contractNo;

    @ApiModelProperty(value="当前登录用户名",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前登录用户企业ID",hidden = true)
    private Long currentCompanyId;
}
