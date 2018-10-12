package cn.fintecher.supply.finance.loan.manager.common.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author huanglei
 *
 */
public class Constants {
	
	/**
	 * task 审批节点 1初审 2复审 3终审
	 */
	public static  String TASK_FIRST_NODE ="1";
	public static  String TASK_SECOND_NODE ="2";
	public static  String TASK_THIRD_NODE ="3";
	/**
	 * 逾期是否结清 0未结清 1已结清
	 */
	public static  String OVERDUE_SETTLE_NO ="0";
	public static  String OVERDUE_SETTLE_YES ="1";
	
	/**
	 * 
	 * 	0待领取 1待初审 2拒绝 3取消 4回退  
	 *	10初审通过/待领取 11待复审 12拒绝 13取消 14回退 
	 *	20复审通过/待领取 21待终审  22拒绝 23取消 24回退 
	 *	30终审/待担保
	 */
	public static  String APPROVAL_FIRST_STAY ="0";
	public static  String APPROVAL_FIRST_ALREADY ="1";
	public static  String APPROVAL_FIRST_REFUSE ="2";
	public static  String APPROVAL_FIRST_CANCEL ="3";
	public static  String APPROVAL_FIRST_BACK ="4";
	
	public static  String APPROVAL_SECOND_STAY ="10";
	public static  String APPROVAL_SECOND_ALREADY ="11";
	public static  String APPROVAL_SECOND_REFUSE ="12";
	public static  String APPROVAL_SECOND_CANCEL ="13";
	public static  String APPROVAL_SECOND_BACK ="14";
	
	public static  String APPROVAL_THIRD_STAY ="20";
	public static  String APPROVAL_THIRD_ALREADY ="21";
	public static  String APPROVAL_THIRD_REFUSE ="22";
	public static  String APPROVAL_THIRD_CANCEL ="23";
	public static  String APPROVAL_THIRD_BACK ="24";
	public static  String APPROVAL_THIRD_ADOPT ="30";
	
	
	/**
	 * 仓单质押状态
	 *	0待领取 1待审核 2拒绝 3取消 4通过  
	 *	
	 */
	public static  String PLEDGE_TRIAL_STAY ="0";
	public static  String PLEDGE_TRIAL_ALREADY ="1";
	public static  String PLEDGE_TRIAL_REFUSE ="2";
	public static  String PLEDGE_TRIAL_CANCEL ="3";
	public static  String PLEDGE_TRIAL_ADOPT ="4";
	
	/**
	 * 供应商担保通过
	 */
	public static  String SUPP_GUAR_ADOPT ="40";
	
	/**
	 * 是否需要担保 1需要 0不需要
	 */
	public static  String GUARANTEE_NEED ="1";
	public static  String GUARANTEE_NO_NEED ="0";
	
    /**
     * 琏属注册状态 0未注册 1已注册 ,
     */
    public static  String COMPANY_CHAIN_STATE_NOT ="0";
    public static  String COMPANY_CHAIN_STATE_YES ="1";
    /**
     * 琏属类型 0供应商 1经销商 ,
     */
    public static  String COMPANY_CHAIN_TYPE_SUP ="0";
    public static  String COMPANY_CHAIN_TYPE_DIS ="1";


	public final static String VERIFICATION_CODE_SESSION_KEY_F = "eqmt-plm:verificationCodeSession";

	public final static String VERIFICATION_CODE_SESSION_KEY_M = "eqmt-plm:verificationCodeSession";

	/**
	 * 数据状态 ，DEL删除，STP停用，NOL正常
	 */
	public static final String DATA_STATUS_DEL = "DEL";
	public static final String DATA_STATUS_STP = "STP";
	public static final String DATA_STATUS_NOL = "NOL";

	/**
	 * 数据类型 1反向保理 2仓单质押
	 */ 
	 public static final String TASK_HISTORY_FACTORING = "1";
	 public static final String TASK_HISTORY_PLEDGE = "2";
	
	/**
	 * Message 返回code 0成功，1失败
	 */

	public static final Integer MSG_STATUS_SUCCESS = 0;
	public static final Integer MSG_STATUS_FAIL = 1;


	/**
	 * 任务领取 最大数量，单次最大数量
	 */
	public static final Integer RECEIVE_MAX_NUM = 20;
	public static final Integer RECEIVE_SINGLE_NUM = 10;

	/**
	 * 角色 初审角色id，复审角色id，终审角色id
	 */
	public static final Integer FIRST_ROLE_ID = 1;
	public static final Integer SECOND_ROLE_ID = 2;
	public static final Integer THIRD_ROLE_ID = 3;
	
	/**
	 * 仓单角色 初审角色id，复审角色id，终审角色id
	 */
	public static final Integer PLEDGE_FIRST_ROLE_ID = 5;
	public static final Integer PLEDGE_SECOND_ROLE_ID = 6;
	public static final Integer PLEDGE_THIRD_ROLE_ID = 7;
	
	/**
	 * 罚息费率(%)
	 */
	public static final Integer PENALTY_FEE = 2;
	
	/**
	 * 入库管理 1已入库 0 未入库 2申请中 3 申请通过 4 申请未通过
	 */
	public static  String COMMODITY_STOCK_YES ="1";
	public static  String COMMODITY_STOCK_NOT ="0";
	public static  String COMMODITY_APPLY_GO ="2";
	public static  String COMMODITY_APPLY_YES ="3";
	public static  String COMMODITY_APPLY_NOT ="4";

	/********************************************此处以上为消费金融新增*******************************************/

	/**
	 * 系统管理员id
	 */

	/**
	 * token----------------------------------------------------------------
	 */
	public static final int TOKEN_TTL = 30 * 60 * 1000; // redis缓存前缀

	public final static String SIGNKEY = "signKey";

	public final static String TOKENKEY = "accessToken";

	public final static String AUTHORZATION = "Authorization";

	public final static String TOKENKEY_ANDROID = "accessToken-android";
	public final static String TOKENKEY_ANDROID_PAD = "accessToken-android-pad";
	public final static String TOKENKEY_IOS = "accessToken-ios";

	public final static String TOKENKEY_ANDROID_KEY = "ph-android";
	public final static String TOKENKEY_ANDROID_PAD_KEY = "ph-android-pad";
	public final static String TOKENKEY_IOS_KEY = "ph-ios";

	/**
	 * 融资等审核流程
	 */
	public final static String PROCESS_KEY="applyProcess";


	/**
	 * 系统超级管理员----------------------------------------------------------------
	 */
	public static final Integer SYSTEM_ADMIN = 1;

	public static final String REDIS_KEY_PREFIX = "consumer-finance:"; // redis缓存前缀

	/**
	 * 系统编码----------------------------------------------------------------
	 */
	public static final String SYSTEM_ERROR = "x00000";
	public static final String SYSTEM_ERROR_MSG = "系统错误,请稍后重试或者联系管理员";
	public static final String STATUS_SUCCESS = "1";
	public static final String STATUS_SUCCESS_MSG = "操作成功";
	public static final String STATUS_NO_SUCCESS = "0";
	public static final String STATUS_NO_SUCCESS_MSG = "操作失败";

	public static final int STATUS_SUCCESSFUL = 0;
	public static final int STATUS_FAILURE = 1;

	/**
	 * 登录错误码----------------------------------------------------------------
	 */
	public static final String LOGIN_ERROR_CODE = "x00001";
	public static final String LOGIN_ERROR_CODE_MSG = "验证码不正确";
	public static final String LOGIN_ERROR_USERNAME = "x00002";
	public static final String LOGIN_ERROR_USERNAME_MSG = "用户名不能为空";
	public static final String LOGIN_ERROR_PASSWORD = "x00003";
	public static final String LOGIN_ERROR_PASSWORD_MSG = "密码不能为空";
	public static final String LOGIN_ERROR_UNKOWNACCOUNT = "x00004";
	public static final String LOGIN_ERROR_UNKOWNACCOUNT_MSG = "账号或密码错误";
	public static final String LOGIN_ERROR_DISABLEACCOUNT = "x00005";
	public static final String LOGIN_ERROR_DISABLEACCOUNT_MSG = "账号未启用";
	public static final String LOGIN_ERROR_INCORRECTCREDENTIAL = "x00006";
	public static final String LOGIN_ERROR_INCORRECTCREDENTIAL_MSG = "密码错误";
	public static final String LOGIN_KAPTCHA_ERROR_CODE = "x00007";
	public static final String LOGIN_KAPTCHA_ERROR_CODE_MSG = "验证码已失效";
	public static final String LOGIN_ERROR_UUID = "x00008";
	public static final String LOGIN_ERROR_UUID_MSG = "uuid不能为空";
	public static final String LOGIN_ERROR_PHONE = "x00009";
	public static final String LOGIN_ERROR_PHONE_MSG = "手机号码不能为空";

	/**
	 * 用户错误码----------------------------------------------------------------
	 */
	public static final String USER_ERROR_DUPLICATEDACCOUNT = "x01001";
	public static final String USER_ERROR_DUPLICATEDACCOUNT_MSG = "登录用户名已存在";
	public static final String USER_ERROR_NOPERMISSION = "x01002";
	public static final String USER_ERROR_NOPERMISSION_MSG = "没有权限";
	public static final String USER_ERROR_TOKRNMISSION = "x01003";
	public static final String USER_ERROR_TOKENMISSION_MSG = "token已过期";
	public static final String USER_ERROR_IPHONEMISSION = "x01004";
	public static final String USER_ERROR_IPHONEMISSION_MSG = "重新登陆";
	public static final String USER_ERROR_TOKENMISSIONS = "x01005";
	public static final String USER_ERROR_TOKENMISSIONS_MSG = "token不能为空";

	/**
	 * 校验----------------------------------------------------------------
	 */
	public static final String VALIDATOR_BLANK = "x02001";
	public static final String VALIDATOR_BLANK_MSG = "字段不能为空";
	public static final String VALIDATOR_CLASS = "x02002";
	public static final String VALIDATOR_CLASS_MSG = "对象校验失败";
	public static final String VALIDATOR_STRING = "x02003";
	public static final String VALIDATOR_STRING_MSG = "非法字符";

	/**
	 *
	 */
	public static final String WORK_FLOW_STATUS = "x03001";
	public static final String WORK_FLOW_STATUS_MSG = "流程参数不能为空！";

	/**
	 * 领取流程code
	 */
	public static final String RECEIVE_SUCCESS = "11";
	public static final String RECEIVE_SUCCESS_MSG = "领取成功";
	public static final String RECEIVE_FAIL = "00";
	public static final String RECEIVE_FAIL_MSG = "没有可以领取的任务";
	public static final String NEXT_SUCCESS = "111";
	public static final String NEXT_SUCCESS_MSG = "审核成功";
	public static final String NEXT_FAIL = "000";
	public static final String NEXT_FAIL_MSG = "审核失败";

	/**
	 * 计划编号生成
	 * 1:巡检，2：保养，3：大修
	 */
	public static final String PLAN_XUNJIAN = "1";
	public static final String PLAN_XUNJIAN_MSG = "XJ";
	public static final String PLAN_BAOYANG = "2";
	public static final String PLAN_BAOYANG_MSG = "BY";
	public static final String PLAN_DAXIU = "3";
	public static final String PLAN_DAXIU_MSG = "DX";

	/**
	 * 正常
	 */
	public static final String ISDELETE_NO = "0";

	/**
	 * 标记删除
	 */
	public static final String ISDELETE_YES = "1";

	/**
	 * 币种
	 */
	public static final String CATEGORY_MONEY = "categoryMoney";

	/**
	 *
	 * 规模
	 */
	public static final String CATEGORY_SCALE = "categoryScale";

	/**
	 * 证件类型
	 */
	public static final String CATEGORY_DOCUMENT = "categoryDocument";

	/**
	 * 上传资源类型
	 */
	public static final String CATEGORY_RESOURCE = "categoryResource";

	/**
	 * 供应商模板下载
	 */
	public static final String EXL_SUPPLIER_FILENAME="template-supplier.xlsx";//配件
	/**
	 * 合同服务模板下载
	 */
	public static final String PDF_ACCOUNT_FILENAME="account_transfer_template.pdf";//应收账款转让通知书
	public static final String PDF_CONFIRM_FILENAME="confirm_template.pdf";//应收账款确认函
	public static final String PDF_FACTORING_FILENAME="factoring_loan_template.pdf";//保理借款协议
	public static final String PDF_SERVICE_FILENAME="service_agreement_template.pdf";//咨询管理服务协议

	/**
	 * 合同服务模板生成
	 */
	public static final String PDF_ACCOUNT_LIV="account.docx";//应收账款转让通知书
	public static final String PDF_CONFIRM_LIV="confirm.docx";//应收账款确认函
	public static final String PDF_FACTORING_LIV="factoring.docx";//保理借款协议
	public static final String PDF_SERVICE_LIV="service.docx";//咨询管理服务协议

	/**
	 * 还款
	 */
	public static final String REMIND_TIME="14:00:00";//提醒时间点
	public static final String REMIND_FORM="自动短信";//提醒形式
	public static final String REMIND_SET="1";//0未设置
	public static final String REMIND_NOSET="0";//1已设置
	public static final String ENTRY_STATE_NOSET="0";//0未复核
	public static final String ENTRY_STATE_YES="1";//1已复核
	public static final String ENTRY_STATE_NO="2";//2已驳回
	public static final String ENTRY_TYPE_NO="0";//0平台
	public static final String ENTRY_TYPE_YES="1";//1核心企业

	/**
	 * 审核
	 */
	public static final String OBJECT_REGISTER_NO="0";//注册审核类型


	/**
	 * 银行用途
	 */
	public static final String BANK_ACCOUNT_TYPE="1";//放款账户

	public static final String BANK_ACCOUNT="2";//还款账户

	/**
	 * 仓单放款审核状态
	 */
	public static final String PLEDGE_WAIT_AUDIT="0";//待审核
	public static final String PLEDGE_WAIT_AUDIT_PASS="1";//通过
	public static final String PLEDGE_WAIT_AUDIT_DEFAULT="2";//拒绝




	public static Map<String, String> statusMap = new HashMap<String, String>();

	static {
		statusMap.put(SYSTEM_ERROR, SYSTEM_ERROR_MSG);
		statusMap.put(STATUS_SUCCESS, STATUS_SUCCESS_MSG);

		statusMap.put(LOGIN_ERROR_CODE, LOGIN_ERROR_CODE_MSG);
		statusMap.put(LOGIN_ERROR_USERNAME, LOGIN_ERROR_USERNAME_MSG);
		statusMap.put(LOGIN_ERROR_PASSWORD, LOGIN_ERROR_PASSWORD_MSG);
		statusMap.put(LOGIN_ERROR_UNKOWNACCOUNT, LOGIN_ERROR_UNKOWNACCOUNT_MSG);
		statusMap.put(LOGIN_ERROR_DISABLEACCOUNT, LOGIN_ERROR_DISABLEACCOUNT_MSG);
		statusMap.put(LOGIN_ERROR_INCORRECTCREDENTIAL, LOGIN_ERROR_INCORRECTCREDENTIAL_MSG);

		statusMap.put(USER_ERROR_DUPLICATEDACCOUNT, USER_ERROR_DUPLICATEDACCOUNT_MSG);
		statusMap.put(USER_ERROR_NOPERMISSION, USER_ERROR_NOPERMISSION_MSG);
		statusMap.put(LOGIN_ERROR_UUID,LOGIN_ERROR_UUID_MSG);
		statusMap.put(LOGIN_ERROR_PHONE,LOGIN_ERROR_PHONE_MSG);
		statusMap.put(USER_ERROR_TOKRNMISSION,USER_ERROR_TOKENMISSION_MSG);
		statusMap.put(USER_ERROR_IPHONEMISSION,USER_ERROR_IPHONEMISSION_MSG);
		statusMap.put(USER_ERROR_TOKENMISSIONS,USER_ERROR_TOKENMISSIONS_MSG);

		statusMap.put(RECEIVE_SUCCESS, RECEIVE_SUCCESS_MSG);
		statusMap.put(RECEIVE_FAIL, RECEIVE_FAIL_MSG);
		statusMap.put(NEXT_SUCCESS, NEXT_SUCCESS_MSG);
		statusMap.put(NEXT_FAIL, NEXT_FAIL_MSG);
	}
}
