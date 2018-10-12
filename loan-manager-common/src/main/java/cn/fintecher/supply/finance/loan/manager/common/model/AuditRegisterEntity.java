package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 6:49
 */
@Data
public class AuditRegisterEntity implements Serializable{
    /**
     *pid
     */
    private Long pid;
    /**
     *企业名称
     */
    private String name;
    /**
     *提交认证时间
     */
    private Date submitTime;
    /**
     *认证时间
     */
    private Date successTime;
    /**
     *认证状态（-10认证不通过 0待认证  10认证中  20认证通过）
     */
    private String state;
    /**
     *注册用户详细信息id
     */
    private String registerId;
    /**
     * 0 核心企业 1 供应商
     */
    private String enterpriseType;
    /**
     *创建人账号
     */
    private String createBy;
    /**
     *创建时间
     */
    private Date createAt;
    /**
     *更新人账号
     */
    private String updateBy;
    /**
     *状态 DEL删除，STP停用，NOL正常
     */
    private String status;
    /**
     *备注
     */
    private String remark;
    /**
     *更新时间
     */
    private Date updateAt;


}
