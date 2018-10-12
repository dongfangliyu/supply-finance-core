package cn.fintecher.supply.finance.loan.manager.core.sys.entity;


import lombok.Data;

/**
 * @author hhh
 * @version 1.0.0
 * @date 2016-5-17 17:36:28
 * @since 1.0.0
 */
@Data
public class SysRole implements java.io.Serializable {
    private Integer roleId;
    private String roleName;
    private String remark;
    private Integer deptId;
    private String createTime;
    private String status;
    private String updateTime;
    private Integer createUserId;
    private Integer updateUserId;
    private String roleCode;
}

