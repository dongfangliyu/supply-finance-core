package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseBankInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.commodity.feign.CommodityStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.FCFinanceStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeSigningCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeSigningService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("pledgeSigningService")
public class PledgeSigningServiceImpl implements PledgeSigningService {

    @Autowired
    private PledgeSigningCore pledgeSigningCore;

    @Autowired
    private PledgeStockInfoCore pledgeStockInfoCore;

    @Autowired
    private CommodityStockInfoCore commodityStockInfoCore;

    @Autowired
    private FCFinanceStockInfoCore fcFinanceStockInfoCore;

    @Autowired
    private FCBaseBankInfoCore fcBaseBankInfoCore;

    @Autowired
    private BusinessFileCore businessFileCore;

    @Override
    public Message selectAdminSigningList(PledgeStockFrom pledgeStockFrom) {
        if(StringUtils.isNotBlank(pledgeStockFrom.getSigningStartTime())){
            pledgeStockFrom.setSigningStartTime(pledgeStockFrom.getSigningStartTime() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(pledgeStockFrom.getSigningEndTime())){
            pledgeStockFrom.setSigningEndTime(pledgeStockFrom.getSigningEndTime() + " 23:59:59");
        }
        List<PledgeSigningResponse> list = pledgeSigningCore.selectAdminSigningList(pledgeStockFrom);
        int tatol =pledgeSigningCore.selectAdminSigningListCount(pledgeStockFrom);
        PageResponse response = new PageResponse();
        response.setData(list);
        response.setTotal(tatol);
        response.setPageNo(pledgeStockFrom.getPageNo());
        response.setPageSize(pledgeStockFrom.getPageSize());
        return new Message(MessageType.MSG_SUCCESS,"pledgeSigning",response);
    }

    @Override
    public Message selectWebSigningList(PledgeStockFrom pledgeStockFrom) {
        if(StringUtils.isNotBlank(pledgeStockFrom.getSigningStartTime())){
            pledgeStockFrom.setSigningStartTime(pledgeStockFrom.getSigningStartTime() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(pledgeStockFrom.getSigningEndTime())){
            pledgeStockFrom.setSigningEndTime(pledgeStockFrom.getSigningEndTime() + " 23:59:59");
        }
        List<PledgeSigningResponse> list = pledgeSigningCore.selectWebSigningList(pledgeStockFrom);
        int tatol =pledgeSigningCore.selectWebSigningListCount(pledgeStockFrom);
        PageResponse response = new PageResponse();
        response.setData(list);
        response.setTotal(tatol);
        response.setPageNo(pledgeStockFrom.getPageNo());
        response.setPageSize(pledgeStockFrom.getPageSize());
        return new Message(MessageType.MSG_SUCCESS,"pledgeSigning",response);
    }

    @Override
    public Message selectAdminSigningDetail(Long id) {
        Message message = new Message();
        PledgeFinanceResponse financeResponse = new PledgeFinanceResponse();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(id.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();

        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }else{
            financeResponse.setCommodityStockInfoEntity(commodityStockInfoEntity);
        }
        // 查询仓单质押申请信息表
        PledgeStockInfoEntity infoEntity = new PledgeStockInfoEntity();
        infoEntity.setCommodityId(commodityStockInfoEntity.getPid());
        infoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<PledgeStockInfoEntity> pledgeStockInfoEntities = pledgeStockInfoCore.selectByStockInfo(infoEntity);
        if (pledgeStockInfoEntities.size() > 0){
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeStockInfoEntities.get(0);
            financeResponse.setPledgeStockInfoEntity(pledgeStockInfoEntity);
        }

        // 查询仓单放款申请信息表
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(id);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0){
            FinanceStockInfoEntity stockInfoEntity = financeStockInfoEntities.get(0);
            financeResponse.setFinanceStockInfoEntity(stockInfoEntity);
        }
        // 查询放款账户信息
        BaseBankInfoEntity baseBankInfoEntity = fcBaseBankInfoCore.getLoanBankByCompanyId(commodityStockInfoEntity.getCompanyId());
        if (!ChkUtil.isEmpty(baseBankInfoEntity)){
            financeResponse.setBaseBankInfoEntity(baseBankInfoEntity);
        }
        // 查询放款凭证
        BusinessFileEntity businessFileEntity = new BusinessFileEntity();
        businessFileEntity.setCommodityId(id);
        businessFileEntity.setCategory("loanVoucher");
        businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
        Message message1 = businessFileCore.selectByFile(businessFileEntity);
        List<BusinessFileEntity> businessFileEntities = JSONUtil.toList(message1.getMessage(), BusinessFileEntity.class);
        if (businessFileEntities.size() > 0){
            BusinessFileEntity businessFileEntity1 = businessFileEntities.get(0);
            financeResponse.setBusinessFileEntity(businessFileEntity1);
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(financeResponse);
        return message;

    }

    @Override
    public Message selectWebSigningDetail(Long id) {
        Message message = new Message();
        PledgeFinanceResponse financeResponse = new PledgeFinanceResponse();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(id.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();

        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }else{
            financeResponse.setCommodityStockInfoEntity(commodityStockInfoEntity);
        }
        // 查询仓单质押申请信息表
        PledgeStockInfoEntity infoEntity = new PledgeStockInfoEntity();
        infoEntity.setCommodityId(commodityStockInfoEntity.getPid());
        infoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<PledgeStockInfoEntity> pledgeStockInfoEntities = pledgeStockInfoCore.selectByStockInfo(infoEntity);
        if (pledgeStockInfoEntities.size() > 0){
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeStockInfoEntities.get(0);
            financeResponse.setPledgeStockInfoEntity(pledgeStockInfoEntity);
        }

        // 查询仓单放款申请信息表
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(id);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0){
            FinanceStockInfoEntity stockInfoEntity = financeStockInfoEntities.get(0);
            financeResponse.setFinanceStockInfoEntity(stockInfoEntity);
        }
        // 查询放款账户信息
        BaseBankInfoEntity baseBankInfoEntity = fcBaseBankInfoCore.getLoanBankByCompanyId(commodityStockInfoEntity.getCompanyId());
        if (!ChkUtil.isEmpty(baseBankInfoEntity)){
            financeResponse.setBaseBankInfoEntity(baseBankInfoEntity);
        }
        // 查询放款凭证
        BusinessFileEntity businessFileEntity = new BusinessFileEntity();
        businessFileEntity.setCommodityId(id);
        businessFileEntity.setCategory("loanVoucher");
        businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
        Message message1 = businessFileCore.selectByFile(businessFileEntity);
        List<BusinessFileEntity> businessFileEntities = JSONUtil.toList(message1.getMessage(), BusinessFileEntity.class);
        if (businessFileEntities.size() > 0){
            BusinessFileEntity businessFileEntity1 = businessFileEntities.get(0);
            financeResponse.setBusinessFileEntity(businessFileEntity1);
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(financeResponse);
        return message;
    }

    @Override
    public Message submitWebSigning(Long id) {
        PledgeStockInfoEntity entity = pledgeStockInfoCore.queryStockInfoByPid(id.toString());
        entity.setContractApplyState("1");
        entity.setContractUserTime(new Date());
        pledgeStockInfoCore.updateStockInfo(entity);
        return new Message(MessageType.MSG_SUCCESS,"pledgeSigning","操作成功");
    }

    @Override
    public synchronized Message submitAdminSigning(Long id) {
        PledgeStockInfoEntity entity = pledgeStockInfoCore.queryStockInfoByPid(id.toString());
        if("1".equals(entity.getContractElectronics())){
            return new Message(MessageType.MSG_ERROR,"pledgeSigning","已经签约，请不要重复签约！");
        }
        entity.setContractElectronics("1");
        entity.setContractPlatformTime(new Date());
        entity.setFinanceState(Constants.PLEDGE_WAIT_AUDIT);
        pledgeStockInfoCore.updateStockInfo(entity);
        // 插入财务审核
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(entity.getCommodityId());
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0){
            FinanceStockInfoEntity financeStockInfoEntity1 = financeStockInfoEntities.get(0);
            financeStockInfoEntity1.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT);
            financeStockInfoEntity1.setFinanceRehearState(Constants.PLEDGE_WAIT_AUDIT);
            fcFinanceStockInfoCore.updateStockInfo(financeStockInfoEntity1);
        }
        financeStockInfoEntity.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT);
        financeStockInfoEntity.setFinanceRehearState(Constants.PLEDGE_WAIT_AUDIT);
        financeStockInfoEntity.setCreateAt(new Date());
        fcFinanceStockInfoCore.insertStockInfo(financeStockInfoEntity);
        return new Message(MessageType.MSG_SUCCESS,"pledgeSigning","操作成功");
    }

    @Override
    public Message selectPaperContract(Long id) {
        BusinessFileEntity entity = new BusinessFileEntity();
        entity.setCategory("pledgeContract");
        entity.setCommodityId(id);
        entity.setStatus(Constants.DATA_STATUS_NOL);
        return businessFileCore.selectByFile(entity);
    }

    @Override
    public Message selectContractMoreType(Long id) {
        BusinessFileEntity entity = new BusinessFileEntity();
        entity.setCategory("pledgeContract");
        entity.setCommodityId(id);
        entity.setStatus(Constants.DATA_STATUS_NOL);
        Message message = businessFileCore.selectByFile(entity);
        List<BusinessFileEntity> list = JSONUtil.toList(message.getMessage(),BusinessFileEntity.class);
        if(list.size()!=0){
           return new Message(MessageType.MSG_SUCCESS,"pledgeSigning","1");
        }
        return  new Message(MessageType.MSG_SUCCESS,"pledgeSigning","0");
    }
}
