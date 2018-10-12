package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 14:00 2018/7/10
 */
@Data
public class AuditCompanyEntryForm extends PageInfo implements Serializable {

    @ApiModelProperty(value = "企业名称")
    private String name;

    @ApiModelProperty(value = "申请授信日期开始日期")
    private Date submitStartTime;

    @ApiModelProperty(value = "申请授信日期结束日期")
    private Date submitEndTime;

    @ApiModelProperty(value = "授信录入开始日期t")
    private Date entryStartTime;

    @ApiModelProperty(value = "授信录入结束日期")
    private Date entryEndTime;

    @ApiModelProperty(value = "审核状态")
    private String state;
}
