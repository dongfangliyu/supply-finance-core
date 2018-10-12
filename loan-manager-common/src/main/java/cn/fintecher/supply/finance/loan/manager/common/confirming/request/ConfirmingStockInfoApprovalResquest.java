package cn.fintecher.supply.finance.loan.manager.common.confirming.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/9/10 15:03
 */
@Data
public class ConfirmingStockInfoApprovalResquest extends PageInfo implements Serializable {

    @ApiModelProperty(value = "核心企业名称")
    private String companyName;

    @ApiModelProperty(value = "经销商名称")
    private String companyDealerName;

    @ApiModelProperty(value = "业务编号")
    private String bussineNo;

    @ApiModelProperty(value = "初审开始时间")
    private  String applyFirstTimeStart;

    @ApiModelProperty(value = "初审结束时间")
    private  String applyFirstTimeEnd;

    @ApiModelProperty(value = "复审开始时间")
    private  String applyRehearTimeStart;

    @ApiModelProperty(value = "复审结束时间")
    private  String applyRehearTimeTimeEnd;

    @ApiModelProperty(value = "终审开始时间")
    private  String applyFinalTimeStart;

    @ApiModelProperty(value = "终审结束时间")
    private  String applyFinalTimeEnd;

    @ApiModelProperty(value="初核状态0待领取 1待初审 2拒绝 3取消 4初审通过")
    private String applyFirstState;

    @ApiModelProperty(value="复核状态： 0待领取 1待初审 2拒绝 3取消  4复审通过")
    private String applyRehearState;

    @ApiModelProperty(value="终核状态： 0待领取 1待初审 2拒绝 3取消  4终审通过")
    private String applyFinalState;

    @ApiModelProperty(value="当前用户名称",hidden = true)
    private String currentUserName;

    @ApiModelProperty(value="当前用户id",hidden = true)
    private Integer currentUserId;

    @ApiModelProperty(value = "审批节点 1 初审 2复审 3终审",hidden = true)
    private  String node;

}
