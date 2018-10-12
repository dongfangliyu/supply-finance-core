package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;

public interface PledgeSigningService {

    /**
     * 后端签约列表
     * @param pledgeStockFrom
     * @return
     */
    public Message selectAdminSigningList(PledgeStockFrom pledgeStockFrom);


    /**
     * 前端签约列表
     * @param pledgeStockFrom
     * @return
     */
    public Message selectWebSigningList(PledgeStockFrom pledgeStockFrom);


    /**
     * 后端签约详情
     * @param id
     * @return
     */
    public Message selectAdminSigningDetail(Long id);

    /**
     * 前端签约详情
     * @param id
     * @return
     */
    public Message selectWebSigningDetail(Long id);


    /**
     * 后端提交签约
     * @param id
     * @return
     */
    public Message submitWebSigning(Long id);

    /**
     * 前端提交签约
     * @param id
     * @return
     */
    public Message submitAdminSigning(Long id);


    /**
     * 查看纸质合同
     * @param id
     * @return
     */
    public Message selectPaperContract(Long id);

    public Message selectContractMoreType(Long id);
}
