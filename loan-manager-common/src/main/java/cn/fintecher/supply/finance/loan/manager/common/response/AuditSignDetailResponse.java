package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 15:17 2018/7/24
 */
@Data
public class AuditSignDetailResponse implements Serializable {
    private Long pid;

    //审批历史
    @ApiModelProperty(value = "节点  “初审”“复审”“终审” ")
    private String node;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "审批时间")
    private Date approvalTime;

    @ApiModelProperty(value = "审批结果  “初审通过”“初审拒绝”等 ")
    private String result;

    @ApiModelProperty(value = "审批状态  1通过  2拒绝  3取消  4回退 ")
    private String resultStatus;

    @ApiModelProperty(value = "审批人 ")
    private String approvalMan;

    @ApiModelProperty(value = "审批意见 ")
    private String content;

    //核心企业信息
    @ApiModelProperty(value = "核心企业名称 ")
    private String enterName;

    @ApiModelProperty(value = "1:营业执照注册号  2:统一社会信用代码 ")
    private String entertype;

    @ApiModelProperty(value = "营业执照注册号 ")
    private String enterEnpsLicense;

    @ApiModelProperty(value = "统一社会信用代码 ")
    private String enterEnpsCredit;

    @ApiModelProperty(value = "法定代表人归属地 ")
    private String enterLegalAddress;

    @ApiModelProperty(value = "实际控制人身份  1:法定代表人 2:非法定代表人 ")
    private String enterController;

    @ApiModelProperty(value = "填写人身份 1:法定代表人  2:代理人 ")
    private String enterFillPersion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "成立时间 ")
    private Date enterEstablishTime;

    @ApiModelProperty(value = "注册资本金 ")
    private String enterRegisteredPrincipal;

    @ApiModelProperty(value = "实缴注册资本金 ")
    private String enterRealRegisteredPrincipal;

    @ApiModelProperty(value = "注册（登记）地址详细地址 ")
    private String enterRegisteredAddressDetail;

    @ApiModelProperty(value = "实际经营地址详细地址 ")
    private String enterOperatingAddressDetail;

    @ApiModelProperty(value = "企业贷款卡编号 ")
    private String enterLoanNum;

    //核心企业代表人信息
    @ApiModelProperty(value = "核心企业代表人姓名 ")
    private String enterOperationName;

    @ApiModelProperty(value = "手机号码 ")
    private String enterPhone;

    @ApiModelProperty(value = "证件类型 1:身份证 2: 港澳通行证 3: 其他 ")
    private String enterCardType;

    @ApiModelProperty(value = "证件号码 ")
    private String enterCardNum;

    @ApiModelProperty(value = "性别 ")
    private String enterSex;

    @ApiModelProperty(value = "电子邮箱 ")
    private String enterMail;

    @ApiModelProperty(value = "归属地 ")
    private String enterAddress;

    //供应商信息
    @ApiModelProperty(value = "供应商企业名称 ")
    private String suppName;

    @ApiModelProperty(value = "1:营业执照注册号  2:统一社会信用代码 ")
    private String suppType;

    @ApiModelProperty(value = "营业执照注册号 ")
    private String suppEnpsLicense;

    @ApiModelProperty(value = "统一社会信用代码 ")
    private String suppEnpsredit;

    @ApiModelProperty(value = "法定代表人归属地 ")
    private String suppLegalAddress;

    @ApiModelProperty(value = "实际控制人身份  1:法定代表人 2:非法定代表人 ")
    private String suppController;

    @ApiModelProperty(value = "填写人身份 1:法定代表人  2:代理人 ")
    private String suppFillPersion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "成立时间 ")
    private Date suppEstablishTime;

    @ApiModelProperty(value = "注册资本金 ")
    private String suppRegisteredPrincipal;

    @ApiModelProperty(value = "实缴注册资本金 ")
    private String suppRealRegisteredPrincipal;

    @ApiModelProperty(value = "注册（登记）地址详细地址 ")
    private String suppRegisteredAddressDetail;

    @ApiModelProperty(value = "实际经营地址详细地址 ")
    private String suppOperatingAddressDetail;

    @ApiModelProperty(value = "企业贷款卡编号 ")
    private String suppLoanNum;

    //供应商代表人信息
    @ApiModelProperty(value = "核心企业代表人姓名 ")
    private String suppOperationName;

    @ApiModelProperty(value = "手机号码 ")
    private String suppPhone;

    @ApiModelProperty(value = "证件类型 1:身份证 2: 港澳通行证 3: 其他 ")
    private String suppCardType;

    @ApiModelProperty(value = "证件号码 ")
    private String suppCardNum;

    @ApiModelProperty(value = "性别 ")
    private String suppSex;

    @ApiModelProperty(value = "电子邮箱 ")
    private String suppMail;

    @ApiModelProperty(value = "归属地 ")
    private String suppAddress;

    //放款账户信息
    @ApiModelProperty(value = "开户行 ")
    private String loanBankOpen;

    @ApiModelProperty(value = "支行 ")
    private String loanBankBranch;

    @ApiModelProperty(value = "账户类型 ")
    private String loanAccountType;

    @ApiModelProperty(value = "企业,注册企业等 ")
    private String loanObjectType;

    @ApiModelProperty(value = "企业id,注册企业id等 ")
    private String loanObjectId;

    @ApiModelProperty(value = "户名 ")
    private String loanAccountName;

    @ApiModelProperty(value = "账户用途（放款账户为1；还款账户为2） ")
    private String loanAccountUse;

    @ApiModelProperty(value = "银行卡号 ")
    private String loanBankCard;

    //还款账户信息
    @ApiModelProperty(value = "还款账户id ")
    private Long  repayBankId;

    @ApiModelProperty(value = "开户行 ")
    private String repayBankOpen;

    @ApiModelProperty(value = "支行 ")
    private String repayBankBranch;

    @ApiModelProperty(value = "账户类型 ")
    private String repayAccountType;

    @ApiModelProperty(value = "企业,注册企业等 ")
    private String repayObjectType;

    @ApiModelProperty(value = "企业id,注册企业id等 ")
    private String repayObjectId;

    @ApiModelProperty(value = "户名 ")
    private String repayAccountName;

    @ApiModelProperty(value = "账户用途（放款账户为1；还款账户为2） ")
    private String repayAccountUse;

    @ApiModelProperty(value = "银行卡号 ")
    private String repayBankCard;

    //交易信息表
    @ApiModelProperty(value = "应收账款编号 ")
    private String accountNo;

    @ApiModelProperty(value = "商务合同编号 ")
    private String contractNo;

    @ApiModelProperty(value = "申请金额")
    private Integer applicationAmount;

    @ApiModelProperty(value = "申请期限")
    private Integer applicationTerm;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="账款起日")
    private Date accountStartTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="账款止日")
    private Date accountEndTime;

    @ApiModelProperty(value = "利息")
    private String interest;

    @ApiModelProperty(value="利率")
    private Integer interestRate;

    @ApiModelProperty(value = "服务费")
    private Integer serviceFee;

    @ApiModelProperty(value = "审批金额 ")
    private Integer approvalAmount;

    @ApiModelProperty(value = "审批期限 ")
    private Integer approvalTerm;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="签约时间")
    private Date signingTime;

    @ApiModelProperty(value = "产品类型")
    private String productTypeName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value="担保时间")
    private Date guaranteeTime;


    private AuditFileEntity auditFileEntity;
}
