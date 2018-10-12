package cn.fintecher.supply.finance.loan.manager.core.pro.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:24
 */
@Mapper
public interface ProContractDao {

    void updateProContractEntity(ProContractEntity proContractEntity);

    ProContractEntity searchContract(String pid);

    void submitContract(ProContractEntity proContractEntity);

    List<ProContractEntity> searchListContract(ProContractForm proContractForm);

    int searchListContractCount(ProContractForm proContractForm);

    long searchCountOfContract();

    List<ProContractEntity> searchContractListByCompanyId(Long companyId);
}
