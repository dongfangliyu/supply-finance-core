package cn.fintecher.supply.finance.loan.manager.core.pledge.dao;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 仓单质押申请信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
@Mapper
public interface PledgeStockInfoDao{
	
	Integer insertStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	List<PledgeStockInfoEntity> selectByStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	Integer updateStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	PledgeStockInfoEntity queryStockInfoByPid(String pid);

	int searchFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	List<PledgeFinanceLoanResponse> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	int selectFinanceLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	List<PledgeFinanceLoanResponse> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	List<PledgeStockInfoEntity> searchPledgeInfoByCommodityId(String pid);

    int selectFinanceReviewLoanListCount(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	List<PledgeFinanceLoanResponse> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm);

	List<PledgeSigningResponse> selectWebSigningList(PledgeStockFrom pledgeStockFrom);

	Integer selectWebSigningListCount(PledgeStockFrom pledgeStockFrom);

	List<PledgeSigningResponse> selectAdminSigningList(PledgeStockFrom pledgeStockFrom);

	Integer selectAdminSigningListCount(PledgeStockFrom pledgeStockFrom);
}
