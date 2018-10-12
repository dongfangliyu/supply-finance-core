package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 19:48 2018/7/10
 */
@Data
public class AuditFileEntity implements Serializable {
    /**
     *pid
     */
    private Long pid;
    /**
     *文件名称
     */
    private String fileName;
    /**
     *文件类型
     */
    private String category;
    /**
     *完整路径
     */
    private String fullPath;
    /**
     *文件保存地址
     */
    private String path;
    /**
     *组
     */
    private String fileGroup;
    /**
     *资源拥有者   审批id
     */
    private String ownerId;
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
