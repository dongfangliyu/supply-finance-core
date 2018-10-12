package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service;

import java.util.List;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
public interface CommodityStockInfoService {    
	
	/**
	 * 查询客户端申请入库分页列表
	 * @param commdityStockForm
	 * @return
	 */
    public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(CommodityStockForm commdityStockForm);
	
    /**
     * 提交申请入库
     * @param commodityStockInfoSubmit
     * @return
     */
    public Message<Object> submitStock(CommodityStockInfoSubmit commodityStockInfoSubmit);
	
    /**
     * 查询申请入库详情
     * @param pid 详情id
     * @param userName 用户账号
     * @return
     */
    public Message<CommodityStockInfoResponse> searchStockDetail(Long pid, String userName);


    /**
     * 管理端查询入库列表
     * @param commodityStockForm
     * @return
     */
    public Message selectStockList(CommodityStockForm commodityStockForm);

    /**
     * 管理端提交入库
     * @param pid
     * @return
     */
    public Message submitState(Long pid, String time);

    /**
     * 管理端入库查看详情
     * @param pid
     * @return
     */
    public Message selectCommodityStockInfo(Long pid);
}
