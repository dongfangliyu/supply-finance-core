package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 3:53
 */
@Data
public class PledgeRegisterResponse implements Serializable{

    private RegisterUserInfoEntity userInfoEntity;

    private String userName;

    private List<RegisterFileEntity> registerFileEntityList;

}
