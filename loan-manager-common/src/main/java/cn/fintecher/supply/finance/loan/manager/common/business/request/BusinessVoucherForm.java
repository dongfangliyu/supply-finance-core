package cn.fintecher.supply.finance.loan.manager.common.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/17 14:34
 */
@Data
public class BusinessVoucherForm implements Serializable {

    @ApiModelProperty(value="账款凭证类型")
    private String suppCategory;

    @ApiModelProperty(value="账款凭证类型名称")
    private String suppCategoryValue;

    @ApiModelProperty(value="订单id")
    private String orderId;

    @ApiModelProperty(value="供应商文件code")
    private String suppCode;
}
