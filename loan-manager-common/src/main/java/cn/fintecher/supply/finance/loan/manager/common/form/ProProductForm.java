package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/9 0009下午 1:35
 */
@Data
public class ProProductForm extends PageInfo implements Serializable {

    @ApiModelProperty(value = "产品代号")
    private String productNo;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品类型（pledge 存货  confirming 预付   reverseNoRecourse 应收）")
    private String category;

    private String timeStart;

    private String timeEnd;

    @ApiModelProperty(value = "产品状态")
    private String state;

    private String businessCategory;

    private String companyId;
}
