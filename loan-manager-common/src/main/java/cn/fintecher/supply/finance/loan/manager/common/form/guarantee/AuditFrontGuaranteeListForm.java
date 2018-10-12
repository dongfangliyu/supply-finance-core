package cn.fintecher.supply.finance.loan.manager.common.form.guarantee;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 14:21 2018/7/19
 */
@Data
public class AuditFrontGuaranteeListForm extends PageInfo implements Serializable {

    private String enterpriseName;

    private String guarnteeCompany;

    private String state;

    @ApiModelProperty(value="当前登录用户名",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前登录用户企业ID",hidden = true)
    private Long currentCompanyId;

}
