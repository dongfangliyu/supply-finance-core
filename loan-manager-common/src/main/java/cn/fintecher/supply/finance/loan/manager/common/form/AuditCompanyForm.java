package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 17:43 2018/7/5
 */
@Data
public class AuditCompanyForm extends PageInfo implements Serializable {

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "申请授信日期开始日期")
    private Date submitStartTime;

    @ApiModelProperty(value = "申请授信日期结束日期")
    private Date submitEndTime;

    @ApiModelProperty(value = "尽调审核开始日期")
    private Date surveyStartTime;

    @ApiModelProperty(value = "尽调审核结束日期")
    private Date surveyEndTime;

    @ApiModelProperty(value = "审核状态")
    private String state;

    @ApiModelProperty(value = "审核id")
    private String id;

    @ApiModelProperty(value = "检查类型")
    private String type;
}
