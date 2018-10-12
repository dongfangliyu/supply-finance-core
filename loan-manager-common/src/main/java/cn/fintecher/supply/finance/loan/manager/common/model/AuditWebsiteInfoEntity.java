package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author WuTianJuan
 * @Date Created in 17:29 2018/7/5
 */
@Data
public class AuditWebsiteInfoEntity implements Serializable {
    /**
     *pid
     */
     private Long pid;
    /**
     *国家企业信用信息公示系统反馈
     */
    private String gsxt;
    /**
     *中登网反馈
     */
    private String zhongdengwang;
    /**
     *中国执行信息公开网反馈
     */
    private String xinxiwang;
    /**
     *人民法院公告网反馈
     */
    private String fayuan;
    /**
     *网贷黑名单反馈
     */
    private String wangdai;
    /**
     *审核id
     */
    private String auditId;
    /**
     *创建人账号
     */
    private String createBy;
    /**
     *创建人时间
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
