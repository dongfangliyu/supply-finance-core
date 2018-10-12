package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 20:27 2018/7/5
 */
@Data
public class AuditCompanyInfoResponse implements Serializable {

    @ApiModelProperty("授信审核类")
    private AuditCompanyEntity entity;

    private boolean flag;
}
