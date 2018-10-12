package cn.fintecher.supply.finance.loan.manager.common.response;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**

 * @Author WuTianJuan
 * @Date Created in 14:10 2018/7/21
 */
@Data
public class AuditGuaranteeResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * order_info信息
     */
    private AuditOrderInfoEntity auditOrderInfo;
    /**
     * 订单信息
     */
    private BusinessOrderEntity businessOrder;

    /**
     * 供应商文件信息
     */
    private List<BusinessFileEntity> supplylist;

    /**
     * 核心企业文件信息
     */
    private List<BusinessFileEntity> certerlist;

    /**
     * 审批历史
     */
    private List<AuditTaskHistoryEntity> resultlist;

    /**
     * 审批结果
     *
     */
    private AuditTaskHistoryEntity taskResult;

    /**
     * 供应商联系人信息
     */
    private CompanyOperationEntity supplyOperation;

    /**
     * 核心企业联系人信息
     */
    private CompanyOperationEntity certerOperation;

    @JsonIgnore
    private Integer flag;
}
