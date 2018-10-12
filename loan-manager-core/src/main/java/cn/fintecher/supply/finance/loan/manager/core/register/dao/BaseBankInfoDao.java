package cn.fintecher.supply.finance.loan.manager.core.register.dao;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/25 0025下午 2:41
 */
@Mapper
public interface BaseBankInfoDao {
    void saveBaseInfoEntity(BaseBankInfoEntity baseBankInfoEntity);

    BaseBankInfoEntity searchBaseBankInfo(Long enpsId);

    BaseBankInfoEntity getLoanBankByCompanyId(Long companyId);

    BaseBankInfoEntity getRepayBankBySignId(Long signId);

    void update(BaseBankInfoEntity baseBankInfoEntity);

}
