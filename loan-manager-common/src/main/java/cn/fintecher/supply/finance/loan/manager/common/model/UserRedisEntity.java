package cn.fintecher.supply.finance.loan.manager.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author WuTianJuan
 * @Date Created in 15:02 2018/6/26
 */
@Data
public class UserRedisEntity implements Serializable {
    private static final long serialVersionUID = 4906169567482766231L;

    // 用户登录名
    private String id;

    private String iphone;

    private String name;

    private String enpsId;

    private String type;

}
