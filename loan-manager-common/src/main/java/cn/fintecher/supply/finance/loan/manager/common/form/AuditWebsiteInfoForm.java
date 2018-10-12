package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditWebsiteInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 18:02 2018/7/6
 */
@Data
public class AuditWebsiteInfoForm implements Serializable {

    @ApiModelProperty(value="外部网站查询")
    private AuditWebsiteInfoEntity auditWebsiteInfoEntity;

    @ApiModelProperty(value = "审核id")
    private Long id;

    @ApiModelProperty(value = "审核意见")
    private String remark;

    @ApiModelProperty(value = "审核类型")
    private String type;
}
