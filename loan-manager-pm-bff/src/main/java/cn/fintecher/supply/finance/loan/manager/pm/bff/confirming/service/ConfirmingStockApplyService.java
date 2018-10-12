package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:08
 */
public interface ConfirmingStockApplyService {
    Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(ConfirmingStockInfoResquest confirmingStockInfoResquest);

    Message saveConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm);

    Message submitConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm);

    Message upload(MultipartFile file, String type, String code);

    Message<ConfirmingStockInfoDetailResponse> getDetailById(Long pid);

    Message<CompanyEnterpriseEntity> getCompany(String name);

}
