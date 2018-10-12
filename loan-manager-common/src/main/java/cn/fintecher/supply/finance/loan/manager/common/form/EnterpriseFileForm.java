package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 16:17 2018/6/22
 */
@Data
public class EnterpriseFileForm implements Serializable {

    private Long pid;

    @ApiModelProperty(value = "类型 如资产负载表")
    private String category;

    @ApiModelProperty(value = "年份")
    private String year;

}
