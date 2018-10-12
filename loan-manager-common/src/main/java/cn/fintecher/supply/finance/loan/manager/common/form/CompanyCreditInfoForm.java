package cn.fintecher.supply.finance.loan.manager.common.form;

import lombok.Data;

import java.io.Serializable;

/**

 * @Author WuTianJuan
 * @Date Created in 21:05 2018/6/27
 */
@Data
public class CompanyCreditInfoForm implements Serializable {
    private Long pid;
    private String type;
    private String year;
}
