package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/7/10 0010下午 2:22
 */
@Data
public class ProContractForm extends PageInfo implements Serializable {


    private String contractNo;

    private String category;

    private String timeStart;

    private String timeEnd;

    private String state;

    @ApiModelProperty(value = "产品代号")
    private String productNo;

    @ApiModelProperty(value = "业务类型")
    private String businessCategory;
}
