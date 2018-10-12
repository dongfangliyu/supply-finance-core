package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/5 0005下午 3:11
 */
@Data
public class AuditRegisterCompanyInfoResponse implements Serializable{

    private RegisterUserInfoEntity registerUserInfoEntity;

    private List<RegisterFileEntity> registerFileEntities;

    private AuditResultEntity auditResultEntity;

    private String state;

    private String successTime;

    private String submitTime;


}
