package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCConfirmingStockApplyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:08
 */
@Service
public class ConfirmingStockApplyImpl implements ConfirmingStockApplyService {

    @Autowired
    private FCConfirmingStockApplyService fcConfirmingStockApplyService;

    @Override
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(ConfirmingStockInfoResquest confirmingStockInfoResquest) {
        return fcConfirmingStockApplyService.confirmingStockPageList(confirmingStockInfoResquest);
    }

    @Override
    public Message saveConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm) {
        return fcConfirmingStockApplyService.saveConfirmingStockinfo(confirmingStockInfoForm);
    }

    @Override
    public Message submitConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm) {
        return fcConfirmingStockApplyService.submitConfirmingStockinfo(confirmingStockInfoForm);
    }

    @Override
    public Message upload(MultipartFile file, String type, String code) {
        return fcConfirmingStockApplyService.upload( file,  type,  code);
    }

    @Override
    public Message<ConfirmingStockInfoDetailResponse> getDetailById(Long pid) {
        return fcConfirmingStockApplyService.getDetailById(pid);
    }

    @Override
    public Message<CompanyEnterpriseEntity> getCompany(String name) {
        return fcConfirmingStockApplyService.getCompany(name);
    }


}
