package cn.fintecher.supply.finance.loan.manager.common.constant;

public class MsgCodeConstant {

    public static int ERR_MSG_SUCCESS = 0;
    public static int ERR_MSG_DEFAULT = -1;

    private static int ERR_MSG_BASE = 0x80000000;

    /*
     * 领域（示例）
     */
    private static int LOAN_SYS = 0x00010000;

    /**
     * register
     */
    private static int LOAN_REGISTER = 0x00020000;

    /**
     * BASICS
     */
    private static int LOAN_BASICS = 0x00030000;

    /**
     * AUDIT
     */
    private static int LOAN_AUDIT = 0x00040000;

    /**
     * PRO
     */
    private static int LOAN_PRO = 0x00050000;

    /**
     * LOGIN
     */
    private static int LOAN_LOGIN = 0x00060000;


    /**
     * register 模块通用异常
     */
    public static int ERR_REGISTER_EXCEPTION = ERR_MSG_BASE | LOAN_REGISTER | 0;

    /**
     * 注册用户添加失败
     */
    public static int ERR_REGISTER_USER_ADD = ERR_MSG_BASE | LOAN_REGISTER | 1;

    /**
     * 注册用户更新失败
     */
    public static int ERR_REGISTER_USER_UPDATE = ERR_MSG_BASE | LOAN_REGISTER | 2;

    /**
     * 注册用户查询失败
     */
    public static int ERR_REGISTER_USER_SEARCH = ERR_MSG_BASE | LOAN_REGISTER | 4;

    /**
     * 注册用户查询没有该用户
     */
    public static int ERR_REGISTER_USER_NOT_FOUND = ERR_MSG_BASE | LOAN_REGISTER | 5;

    /**
     * 注册用户已经存在
     */
    public static int ERR_REGISTER_USER_EXIST = ERR_MSG_BASE | LOAN_REGISTER | 6;

    /**
     * 注册用户已经注册成功
     */
    public static int ERR_REGISTER_USER_SUCCESS = ERR_MSG_BASE | LOAN_REGISTER | 8;


    // company
    /**
     * 注册公司添加失败
     */
    public static int ERR_REGISTER_COMPANY_ADD = ERR_MSG_BASE | LOAN_REGISTER | 8;
    /**
     * 注册公司更新失败
     */
    public static int ERR_REGISTER_COMPANY_UPDATE = ERR_MSG_BASE | LOAN_REGISTER | 9;
    /**
     * 查询步骤失败
     */
    public static int ERR_REGISTER_CURRENT_STEP = ERR_MSG_BASE | LOAN_REGISTER | 10;

    public static int ERR_DATUM_BANK_CARD_ADD = ERR_MSG_BASE | LOAN_REGISTER | 11;

    public static int ERR_ENTERPRISE_CREDIT_UPDATE = ERR_MSG_BASE | LOAN_REGISTER | 12;

    /**
     * 文件上传失败
     */
    public static int ERR_REGISTER_FILE_UPLOAD = ERR_MSG_BASE | LOAN_REGISTER | 13;


    public static int ERR_CREATE_COMPANY_USER = ERR_MSG_BASE | LOAN_REGISTER | 14;


    public static int ERR_CREATE_COMPANY_ENTERPRISE = ERR_MSG_BASE | LOAN_REGISTER | 15;


    /**
     * 查询审核失败
     */
    public static int ERR_SEARCH_AUDIT_REGISTER = ERR_MSG_BASE | LOAN_AUDIT | 1;

    /**
     * 查询审核结果失败
     */
    public static int ERR_SEARCH_AUDIT_REGISTER_RESULT = ERR_MSG_BASE | LOAN_AUDIT | 2;

    /**
     * 查询待处理数据失败
     */
    public static int ERR_SEARCH_AUDIT_CREDIT_STATUS = ERR_MSG_BASE | LOAN_AUDIT | 3;

    /**
     * 保存审核失败
     */
    public static int ERR_ADD_AUDIT_REGISTER = ERR_MSG_BASE | LOAN_AUDIT | 4;
    /**
     * 保存审核结果失败
     */
    public static int ERR_ADD_AUDIT_REGISTER_RESULT = ERR_MSG_BASE | LOAN_AUDIT | 5;
    /**
     * 提交发送链接失败
     */
    public static int ERR_SUBMIT_SAND_LINK = ERR_MSG_BASE | LOAN_AUDIT | 6;
    /**
     * 财务审核失败
     */
    public static int ERR_AUDIT_ORDER_INFO = ERR_MSG_BASE | LOAN_AUDIT | 7;

    /**
     * 查询产品列表失败
     */
    public static int ERR_SEARCH_PRO_LIST = ERR_MSG_BASE | LOAN_PRO | 1;

    /**
     * 查询产品详情失败
     */
    public static int ERR_SEARCH_PRODUCT_DETAIL = ERR_MSG_BASE | LOAN_PRO | 2;

    /**
     * 删除产品失败
     */
    public static int ERR_DELETE_PRODUCT = ERR_MSG_BASE | LOAN_PRO | 3;

    /**
     * 查询合同列表失败
     */
    public static int ERR_SEARCH_CONTRACT_LIST = ERR_MSG_BASE | LOAN_PRO | 4;

    /**
     * 查询合同详情失败
     */
    public static int ERR_SEARCH_CONTRACT_DETAIL = ERR_MSG_BASE | LOAN_PRO | 5;

    /**
     * 删除合同失败
     */
    public static int ERR_DELETE_CONTRACT = ERR_MSG_BASE | LOAN_PRO | 6;

    /**
     * 添加合同失败
     */
    public static int ERR_ADD_CONTRACT = ERR_MSG_BASE | LOAN_PRO | 7;

    /**
     * 查询合同失败
     */
    public static int ERR_SEARCH_PRO_FILE = ERR_MSG_BASE | LOAN_PRO | 8;

    /**
     * 查询合同失败
     */
    public static int ERR_UPLOAD_PRO_FILE = ERR_MSG_BASE | LOAN_PRO | 9;

    /**
     * 添加产品失败
     */
    public static int ERR_SUBMIT_PRO_PRODUCT = ERR_MSG_BASE | LOAN_PRO | 10;

    /**
     * 查询产品列表失败
     */
    public static int ERR_LOGIN_IN = ERR_MSG_BASE | LOAN_LOGIN | 1;


}
