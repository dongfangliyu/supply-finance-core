package cn.fintecher.supply.finance.loan.manager.common.company.response;

import cn.fintecher.supply.finance.loan.manager.common.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerResponse implements Serializable {

    @ApiModelProperty(value = "客户基本信息")
    private CompanyEnterpriseEntity companyEnterpriseEntity;

    @ApiModelProperty(value = "客户详细信息")
    private CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity;

    @ApiModelProperty(value = "客户联系人集合")
    private List<CompanyOperationEntity> list;

    @ApiModelProperty(value = "银行信息")
    private BaseBankInfoEntity baseBankInfoEntity;

    @ApiModelProperty(value = "文件集合")
    private List<CompanyFileEntity> filelist;
}
