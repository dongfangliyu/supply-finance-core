package cn.fintecher.supply.finance.loan.manager.service.commodity.service;

import java.text.ParseException;
import java.util.List;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

/**
 * 入库信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
public interface CommodityStockInfoService{

    /**
	 * 新增入库信息表
	 * @param 
	 * @return
	 */
	Message insertStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 查询入库信息表
	 * @param quotaApply
	 * @return
	 */
	Message<List<CommodityStockInfoEntity>> selectByStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 修改入库信息表
	 * @param quotaApply
	 */
	Message updateStockInfo(CommodityStockInfoEntity commodityStockInfoEntity);
	
	/**
	 * 根据主键查询入库信息表
	 * @param quotaApply
	 */
	Message<CommodityStockInfoEntity> queryStockInfoByPid(String pid);
 
	/**
	 * 查询客户端申请入库分页列表
	 * @param commdityStockForm  查询条件
	 * @param user	当前操作用户
	 * @return
	 */
    Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(CommodityStockForm commdityStockForm, CompanyUserEntity user) throws ParseException;
    
    /**
     * 提交申请入库
     * @param commodityStockInfoSubmit 提交参数
     * @param user	当前操作用户
     * @return
     */
    Message<Object> submitStock(CommodityStockInfoSubmit commodityStockInfoSubmit, CompanyUserEntity user) throws ParseException;
	
    /**
     * 查询申请入库详情
     * @param pid 详情id
     * @param user	当前操作用户
     * @return
     */
    Message<CommodityStockInfoResponse> searchStockDetail(Long pid, CompanyUserEntity user);


	/**
	 * 管理端查询入库列表
	 * @param commodityStockForm
	 * @return
	 */
    Message selectStockList(CommodityStockForm commodityStockForm);

	/**
	 * 管理端提交入库
	 * @param id
	 * @return
	 */
	Message submitState(Long id, String time);

	/**
	 * 管理端查看详情
	 * @param pid
	 * @return
	 */
	Message selectCommodityStockInfo(Long pid);

}