package cn.fintecher.supply.finance.loan.manager.common.form.guarantee;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 11:12 2018/7/18
 */
@Data
public class AuditGuaranteeListForm extends PageInfo implements Serializable {

    /**
     *核心企业名称
     */
    @ApiModelProperty(value="核心企业名称")
    private String enterpriseName;

    /**
     *供应商名称
     */
    @ApiModelProperty(value="供应商名称")
    private String supplierName;

    /**
     *应收账款编号
     */
    @ApiModelProperty(value="应收账款编号")
    private String accountno;

    /**
     *担保开始时间
     */
    @ApiModelProperty(value = "担保开始时间")
    private String startTime;

    /**
     *担保结束时间
     */
    @ApiModelProperty(value = "担保结束时间")
    private String endTime;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态： 30终审/待担保 31平台拒绝 32平台通过/供应商待审核   40：全部狀態")
    private String state;

}
