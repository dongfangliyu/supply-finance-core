package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 17:12 2018/7/5
 */
@Data
public class AuditSurveyInfoEntity implements Serializable {
    /**
     *pid
     */
    private Long pid;
    /**
     * 现场调查时间
     */
    private String surveyTime;

    /**
     * 额度
     */
    private String quota;

    /**
     * 主营业务
     */
    private String mainBusiness;

    /**
     * 历史沿革
     */
    private String historicalEvolution;

    /**
     * 经营模式及发展战略
     */
    private String modelStrategy;

    /**
     * 行业情况
     */
    private String industrySituation;

    /**
     * 关联企业情况
     */
    private String affiliatedCompany;

    /**
     *真实性声明
     */
    private String authenticityStatement;
    /**
     *信用评价
     */
    private String creditRating;
    /**
     *股权情况
     */
    private String equitySituation;
    /**
     *担保信息
     */
    private String warrantyInformation;
    /**
     *财务预测
     */
    private String financePrediction;
    /**
     *财务分析
     */
    private String financialAnalysis;
    /**
     *应收账款情况
     */
    private String accountsReceivable;
    /**
     *重点或异常
     */
    private String keyAbnormal;
    /**
     *审核id
     */
    private String auditId;
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
     *status
     */
    private String status;
    /**
     *备注
     */
    private String remark;
}
