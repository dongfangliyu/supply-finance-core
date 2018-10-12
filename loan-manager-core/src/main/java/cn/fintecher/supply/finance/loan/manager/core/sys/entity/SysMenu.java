package cn.fintecher.supply.finance.loan.manager.core.sys.entity;

import lombok.Data;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Data
public class SysMenu implements java.io.Serializable {
    private Integer menuId;
    private Integer parantId;
    private String name;
    private String url;
    private String perms;
    private String type;
    private Integer orderNum;
    private String status;
    private Integer createUserId;
    private String createTime;
    private String updateTime;
    private Integer updateUserId;
    private String icon;
    private String code;
}

