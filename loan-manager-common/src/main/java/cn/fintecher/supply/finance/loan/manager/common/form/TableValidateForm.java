package cn.fintecher.supply.finance.loan.manager.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 10:24 2018/6/26
 */
@Data
public class TableValidateForm implements Serializable {

    private Long pid;

    private List years;

    private List<String> category;
}
