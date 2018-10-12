package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 4:02
 */
@Data
public class AutoRegisterForm implements Serializable {

    @ApiModelProperty(value = "用户临时唯一code")
    private String code;

    @ApiModelProperty(value = "用户登录使用token")
    private String token;

}
