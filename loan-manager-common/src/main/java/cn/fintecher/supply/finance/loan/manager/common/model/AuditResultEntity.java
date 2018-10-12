package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 6:50
 */
@Data
public class AuditResultEntity implements Serializable{
    /**
     *pid
     */
    private Long pid;
    /**
     *审核结果  1通过  0拒绝
     */
    private String result;
    /**
     *审核意见
     */
    private String content;
    /**
     *object_id
     */
    private String objectId;
    /**
     *object_type 0注册审核  1尽调审核
     */
    private String objectType;

    /**
     *审核类型  1企业信息  2财务信息 3音像材料  4审核结果
     */
    private String type;
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
     *更新时间
     */
    private Date updateAt;
    /**
     *状态 DEL删除，STP停用，NOL正常
     */
    private String status;
    /**
     *备注
     */
    private String remark;
}
