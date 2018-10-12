package cn.fintecher.supply.finance.loan.manager.common.business.response;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/17 16:16
 */
@Data
public class BusinessFinancingResponse implements Serializable {
    private BusinessOrderEntity businessOrder;
    private List<BusinessFileEntity> enterfileList;
    private List<BusinessFileEntity> suppfileList;
}
