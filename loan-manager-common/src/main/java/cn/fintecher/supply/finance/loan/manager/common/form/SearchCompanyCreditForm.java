package cn.fintecher.supply.finance.loan.manager.common.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 20:11 2018/6/27
 */
@Data
public class SearchCompanyCreditForm implements Serializable {

    private Long pid;

    private String name;
}
