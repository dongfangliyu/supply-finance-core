package cn.fintecher.supply.finance.loan.manager.core.sys.entity;

import lombok.Data;

/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@Data
public class SysDept implements java.io.Serializable {
    private Integer deptId;
    private Integer parantId;
    private String name;
    private Integer orderNum;
    private String status;
    private String deptType;
    private String address;
    private String tel;
    private String email;
    private String custodianName;
    private String number;
    private String mobile;
    private String createTime;
    private String updateTime;
    private Integer compId;
    private Integer createUserId;
    private Integer updateUserId;
}

