package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:46
 */
@Data
public class PledgeClientForm implements Serializable{

    private CompanyEnterpriseEntity companyEnterpriseEntity;

    private CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity;

    private List<CompanyOperationEntity> companyOperationEntity;

    private BaseBankInfoEntity baseBankInfoEntity;

}
