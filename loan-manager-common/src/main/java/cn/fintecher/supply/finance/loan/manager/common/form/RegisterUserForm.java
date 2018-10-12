package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/6/28 0028下午 4:00
 */
@Data
public class RegisterUserForm implements Serializable{

    private RegisterUserEntity registerUserEntity;

    private String verifyCode;

}
