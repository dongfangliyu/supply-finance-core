package cn.fintecher.supply.finance.loan.manager.service.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCConfirmingStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockCoreService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeApplyStockInfoCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author wuxiaoxing
 * @time 2018/9/6 13:51
 */
@Service
public class ConfirmingStockCoreServiceImpl implements ConfirmingStockCoreService {

    @Autowired
    private FCConfirmingStockInfoService fcConfirmingStockInfoService;

    @Autowired
    private PledgeApplyStockInfoCore pledgeApplyStockInfoCore;

    @Override
    public Message confirmFinancing(Long pid) {
        try {
            Message<ConfirmingStockInfoEntity> result = fcConfirmingStockInfoService.getConfirmingStockInfoById(pid);
            if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                ConfirmingStockInfoEntity confirmingStockInfoEntity = result.getMessage();
                if("1".equals(confirmingStockInfoEntity.getConfirmCorecompanyState())){
                    return new Message(MessageType.MSG_ERROR,"confirming_service","融资已确认!");
                }else{
                    confirmingStockInfoEntity.setConfirmCorecompanyState("1");
                    confirmingStockInfoEntity.setConfirmCorecompanyTime(new Date());
                    Message<ConfirmingStockInfoEntity> message = fcConfirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);
                    if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                        PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity = new PledgeApplyStockInfoEntity();
                        pledgeApplyStockInfoEntity.setPledgeId(confirmingStockInfoEntity.getPid());
                        pledgeApplyStockInfoEntity.setApplyFirstState("0");
                        pledgeApplyStockInfoEntity.setApplyRehearState("0");
                        pledgeApplyStockInfoEntity.setApplyFinalState("0");
                        pledgeApplyStockInfoEntity.setApplyType("3");
                        pledgeApplyStockInfoEntity.setCreateAt(new Date());
                        pledgeApplyStockInfoEntity.setUpdateAt(new Date());
                        pledgeApplyStockInfoEntity.setStatus("NOL");
                        pledgeApplyStockInfoCore.insertApplyStockInfo(pledgeApplyStockInfoEntity);
                    }
                    return message;
                }
            }else {
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "confirming_service", e.getMessage());
        }
    }

    @Override
    public Message refuseFinancing(Long pid) {
        try {
            Message<ConfirmingStockInfoEntity> result = fcConfirmingStockInfoService.getConfirmingStockInfoById(pid);
            if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                ConfirmingStockInfoEntity confirmingStockInfoEntity = result.getMessage();
                if("1".equals(confirmingStockInfoEntity.getConfirmCorecompanyState())){
                    return new Message(MessageType.MSG_ERROR,"confirming_service","融资已确认!");
                }else{
                    confirmingStockInfoEntity.setConfirmCorecompanyState("2");
                    confirmingStockInfoEntity.setConfirmCorecompanyTime(new Date());
                    return fcConfirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);
                }
            }else {
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "confirming_service", e.getMessage());
        }
    }
}
