package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 6:45
 */
@Data
public class AuditCompanyEntity implements Serializable {
    /**
     *pid
     */
    private Long pid;
    /**
     *企业名称
     */
    private String name;
    /**
     *申请授信时间
     */
    private Date submitTime;
    /**
     *尽调审核时间
     */
    private Date successTime;
    /**
     *审核状态
     */
    private String state;

    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     *财务信息检查状态
     */
    private String financeStatus;
    /**
     *影像材料检查状态
     */
    private String materialStatus;
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

    /**
     * 1企业信息检查 2现场调查录入 3外部网站查询 4输出尽调结果 5已提交
     */
    private String type;

    /**
     * 1尽调  2授信
     */
    private String audit;

    /**
     * 录入状态  0未录入  1已录入
     */
    private String entryStatus;

    /**
     * 录入时间
     */
    private Date entryTime;

    /**
     * 授信审核状态  0未审核  1已审核
     */
    private String auditStatus;

    /**
     * 授信审核时间
     */
    private Date auditTime;


    private CompanyEnterpriseEntity companyEnterpriseEntity;
}
