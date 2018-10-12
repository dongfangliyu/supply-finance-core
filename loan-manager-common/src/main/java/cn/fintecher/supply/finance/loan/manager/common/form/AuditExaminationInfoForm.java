package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 10:59 2018/7/6
 */
@Data
public class AuditExaminationInfoForm implements Serializable {

    @ApiModelProperty(value = "审核pid")
    private Long pid;

    @ApiModelProperty(value = "审核意见")
    private String remark;

    @ApiModelProperty(value = "类型 5企业信息检查 6现场调查录入 7外部网站查询 8输出尽调结果")
    private String type;
}
