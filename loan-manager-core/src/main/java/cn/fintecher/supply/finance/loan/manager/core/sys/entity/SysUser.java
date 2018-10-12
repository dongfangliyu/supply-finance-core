package cn.fintecher.supply.finance.loan.manager.core.sys.entity;

import lombok.Data;

/**
 * @author hhh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2016-5-17 17:36:28 
 */
@Data
public class SysUser implements java.io.Serializable {
    private Integer id;
    private String name;
    private String password;
    private String iphone;
    private String state;
    private String type;
    private Integer enpsId;
    private Integer supId;
    private String updateTime;
    private String createTime;
    private Integer updateUserId;
    private Integer deptId;
    private String salt;
    private String username;
    private String realname;
    private String email;
    private String mobile;
    private String status;
    private Integer compId;
    private String position;
    private String isRepairPerson;
    private Integer createUserId;
}

