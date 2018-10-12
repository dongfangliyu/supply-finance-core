package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 17:13 2018/7/11
 */
@Data
public class AuditCreditListForm extends PageInfo implements Serializable {

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "申请授信日期开始日期")
    private Date submitStartTime;

    @ApiModelProperty(value = "申请授信日期结束日期")
    private Date submitEndTime;

    @ApiModelProperty(value = "授信審批开始日期")
    private Date surveyStartTime;

    @ApiModelProperty(value = "授信審批结束日期")
    private Date surveyEndTime;

    @ApiModelProperty(value = "审核状态")
    private String state;
}
