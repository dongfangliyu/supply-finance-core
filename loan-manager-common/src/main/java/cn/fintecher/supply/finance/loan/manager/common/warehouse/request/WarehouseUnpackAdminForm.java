package cn.fintecher.supply.finance.loan.manager.common.warehouse.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 14:45 2018/8/23
 */
@Data
public class WarehouseUnpackAdminForm extends PageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *仓单解押编号
     */
    @ApiModelProperty(value="仓单编号")
    private String WarehouseNumber;

    /**
     *供货商名称
     */
    @ApiModelProperty(value="客户名称")
    private String customerName;

    /**
     *仓单解押开始时间
     */
    @ApiModelProperty(value="仓单解押开始时间")
    private String WarehouseUnpackTimeStart;

    /**
     *仓单解押结束时间
     */
    @ApiModelProperty(value="仓单解押结束时间")
    private String WarehouseUnpackTimeEnd;

    /**
     *状态 1:已解压  0：未解押  2：申请中
     */
    @ApiModelProperty(value="状态 1:已解压  0：未解押")
    private String state;
}
