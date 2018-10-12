package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 9:45 2018/7/6
 */
@Data
public class AuditExaminationForm implements Serializable {

    @ApiModelProperty(value = "审核id")
    private Long id;

    @ApiModelProperty(value = "审核类型")
    private String type;

    private String remark;
}
