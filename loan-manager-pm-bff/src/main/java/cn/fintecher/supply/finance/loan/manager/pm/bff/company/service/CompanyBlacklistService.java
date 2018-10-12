package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
public interface CompanyBlacklistService {


    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */

    Message selectBlackList(BlackListFrom blackListFrom);
}
