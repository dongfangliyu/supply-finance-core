package cn.fintecher.supply.finance.loan.manager.common.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/6/5 18:38
 */
@Data
public class PageResponse<T> implements Serializable {
    // 总条数
    @ApiModelProperty(value="总条数")
    private long total;

    // 记录
    @ApiModelProperty(value="数据集合")
    private T data;

    @ApiModelProperty(value="当前第几页")
    private int pageNo;

    @ApiModelProperty(value="每页显示多少条")
    private int pageSize;
}
