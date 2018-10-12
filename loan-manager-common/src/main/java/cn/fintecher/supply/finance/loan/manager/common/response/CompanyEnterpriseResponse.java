package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.model.*;
import lombok.Data;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:50
 */
@Data
public class CompanyEnterpriseResponse {

    private CompanyEnterpriseEntity companyEnterpriseEntity;

    private CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity;

    private List<CompanyOperationEntity> companyOperationEntity;

    private BaseBankInfoEntity baseBankInfoEntity;

    private List<RegisterFileEntity> registerFileEntities;

    private List<CompanyFileEntity> companyFileEntities;

}
