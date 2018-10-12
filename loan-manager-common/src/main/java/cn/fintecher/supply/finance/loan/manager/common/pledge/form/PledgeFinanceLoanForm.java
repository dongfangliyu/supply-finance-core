package cn.fintecher.supply.finance.loan.manager.common.pledge.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:48
 */
@Data
public class PledgeFinanceLoanForm extends PageInfo implements Serializable{

    @ApiModelProperty(value="客户名称")
    private String companyName;

    @ApiModelProperty(value="仓单编号")
    private String applyNumber;

    @ApiModelProperty(value="开始日期")
    private String startTime;

    @ApiModelProperty(value="结束日期")
    private String endTime;

    @ApiModelProperty(value="状态")
    private String state;

    /**
     * 当前登录人
     */
    @ApiModelProperty(value="当前登录人",hidden = true)
    private String userName;

    /**
     *企业id  来源 (企业信息表)
     */
    @ApiModelProperty(value="企业id",hidden = true)
    private Long companyId;

}
