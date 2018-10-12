package cn.fintecher.supply.finance.loan.manager.core.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:25
 */
public interface ProContractService {
    int searchListContractCount(ProContractForm proContractForm);

    List<ProContractEntity> searchListContract(ProContractForm proContractForm);

    void submitContract(ProContractEntity proContractEntity);

    ProContractEntity searchContract(String pid);

    List<ProFileEntity> searchProFile(String resourceCode);

    void updateProContractEntity(ProContractEntity proContractEntity);

    List<ProCategoryEntity> searchCategoryListByParentId(String parentId);

    ProFileEntity searchProFileById(String pid);

    Message saveProFile(ProFileEntity proFileEntity);

    long searchCountOfContract();

    ProCategoryEntity searchCategoryByPid(String pid);

    ProFileEntity searchContractFile(String type, String resourceCode);

    void updateContractFile(ProFileEntity proFileEntity);

    ProCategoryEntity searchCategoryByCategory(String category);

    List<ProContractEntity> searchContractListByCompanyId(Long companyId);
}
