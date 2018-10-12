package cn.fintecher.supply.finance.loan.manager.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:30 2018/6/25
 */
@Data
public class AccountStatementTimeResponse implements Serializable {

    @ApiModelProperty(value = "财务报表期间")
    private List<String> yearsList;
}
