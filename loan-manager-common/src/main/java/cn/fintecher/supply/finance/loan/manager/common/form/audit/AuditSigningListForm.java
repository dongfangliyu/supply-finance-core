package cn.fintecher.supply.finance.loan.manager.common.form.audit;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 13:21 2018/7/24
 */
@Data
public class AuditSigningListForm extends PageInfo implements Serializable {

    private String enterpriseName;

    private String startTime;

    private String endTime;

    @ApiModelProperty(value="当前登录用户名",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前登录用户企业ID",hidden = true)
    private Long currentCompanyId;

}
