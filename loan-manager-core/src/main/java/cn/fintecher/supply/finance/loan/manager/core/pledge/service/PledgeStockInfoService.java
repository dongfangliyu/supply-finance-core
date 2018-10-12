package cn.fintecher.supply.finance.loan.manager.core.pledge.service;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;

import java.util.List;

/**
 * 仓单质押申请信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 14:00:11
 */
public interface PledgeStockInfoService{

    /**
	 * 新增仓单质押申请信息表
	 * @param 
	 * @return
	 */
	Integer insertStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 查询仓单质押申请信息表
	 * @param 
	 * @return
	 */
	List<PledgeStockInfoEntity> selectByStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 修改仓单质押申请信息表
	 * @param 
	 */
	Integer updateStockInfo(PledgeStockInfoEntity pledgeStockInfoEntity);
	
	/**
	 * 根据主键查询仓单质押申请信息表
	 * @param 
	 * @return
	 */
	PledgeStockInfoEntity queryStockInfoByPid(String pid);

	/**
	 * admin端查询签约列表
	 * @param pledgeStockFrom
	 * @return
	 */
	List<PledgeSigningResponse> selectAdminSigningList(PledgeStockFrom pledgeStockFrom);

	/**
	 * admin端查询签约列表count
	 * @param pledgeStockFrom
	 * @return
	 */
	Integer selectAdminSigningListCount(PledgeStockFrom pledgeStockFrom);

	/**
	 * web查询签约列表
	 * @param pledgeStockFrom
	 * @return
	 */
	List<PledgeSigningResponse> selectWebSigningList(PledgeStockFrom pledgeStockFrom);


	/**
	 * web查询签约列表count
	 * @param pledgeStockFrom
	 * @return
	 */
	Integer selectWebSigningListCount(PledgeStockFrom pledgeStockFrom);
}
