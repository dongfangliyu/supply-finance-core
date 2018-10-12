package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 11:24 2018/6/23
 */
@Data
public class CompanyCreditStepForm implements Serializable {
    @ApiModelProperty(value = "企业id")
    private Long id;

    @ApiModelProperty(value = "1：开始授信  2：提交财务报表信息 3：提交授信影像信息 4：申请授信",  dataType = "List")
    private String status;
}
