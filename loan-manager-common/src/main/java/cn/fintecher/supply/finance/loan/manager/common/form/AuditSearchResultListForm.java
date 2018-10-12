package cn.fintecher.supply.finance.loan.manager.common.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 14:28 2018/7/8
 */
@Data
public class AuditSearchResultListForm  implements Serializable {
    private String id;

    private String type;//0全部审核 1企业信息  2财务信息 3音像材料  4审核结果
}
