package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/21 0021下午 2:24
 */
@Data
public class RegisterCompanyForm implements Serializable{

    private String registerCode;

    private RegisterUserInfoEntity registerUserInfoEntity;

}
