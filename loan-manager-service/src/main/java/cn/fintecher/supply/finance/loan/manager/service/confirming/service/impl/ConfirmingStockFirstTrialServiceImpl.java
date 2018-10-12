package cn.fintecher.supply.finance.loan.manager.service.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditSysUserCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditTaskCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskHistoryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCConfirmingStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockFirstTrialService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeApplyStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 15:43
 */
@Service
public class ConfirmingStockFirstTrialServiceImpl implements ConfirmingStockFirstTrialService {

    @Autowired
    private AuditTaskCore auditTaskCore;

    @Autowired
    private PledgeApplyStockInfoCore pledgeApplyStockInfoCore;

    @Autowired
    private AuditSysUserCore auditSysUserCore;

    @Autowired
    private AuditTaskService auditTaskService;

    @Autowired
    private PledgeApplyStockInfoService applyStockInfoService;

    @Autowired
    private FCConfirmingStockInfoService fcConfirmingStockInfoService;

    @Autowired
    private AuditTaskHistoryService auditTaskHistoryService;

    @Override
    public Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(Long pid) {
        AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
        auditTaskHistoryEntity.setOrderId(pid);
        auditTaskHistoryEntity.setType("3");
        auditTaskHistoryEntity.setStatus("NOL");
        return auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity);
    }

    @Override
    public Message<AuditTaskHistoryEntity> getTrialResult(Long pid, Integer node) {
        AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
        if(node==1){
            auditTaskHistoryEntity.setNode("初审");
        }else if(node==2){
            auditTaskHistoryEntity.setNode("复审");
        }else if(node==3){
            auditTaskHistoryEntity.setNode("终审");
        }else{
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","传参错误");
        }
        auditTaskHistoryEntity.setOrderId(pid);
        auditTaskHistoryEntity.setType("3");
        auditTaskHistoryEntity.setStatus("NOL");
        Message<List<AuditTaskHistoryEntity>> listMessage = auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity);
        if(MessageType.MSG_SUCCESS.equals(listMessage.getCode()) && listMessage.getMessage().size()>0){
            return new Message<AuditTaskHistoryEntity>(MessageType.MSG_SUCCESS,"ConfirmingStockTrial_service",listMessage.getMessage().get(0));
        }else{
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",listMessage.getMessage());
        }
    }

    @Override
    public Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm, SysUserAdminEntity user) {

        if (confirmingStockInfoApprovalForm.getResult()==null||confirmingStockInfoApprovalForm.getResult().equals("")) {
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","审核结果不能为空");
        }
        if (!(confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_REFUSE)||
                confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_CANCEL)||
                confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT))) {
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","审核结果不正确");
        }

        AuditTaskEntity auditTaskEntity =new AuditTaskEntity();
        PledgeApplyStockInfoEntity applyStockInfoEntity = new PledgeApplyStockInfoEntity();
        ConfirmingStockInfoEntity confirmingStockInfoEntity = new ConfirmingStockInfoEntity();

        Message<AuditTaskEntity> auditTaskEntityMessage = auditTaskService.queryTaskByPid(confirmingStockInfoApprovalForm.getTaskId());
        if(!MessageType.MSG_SUCCESS.equals(auditTaskEntityMessage.getCode())){
            return  auditTaskEntityMessage;
        }
        auditTaskEntity = auditTaskEntityMessage.getMessage();

        Message<PledgeApplyStockInfoEntity> pledgeApplyStockInfoEntityMessage = applyStockInfoService.queryApplyStockInfoByPid(auditTaskEntity.getOrderInfoId().toString());
        if(! MessageType.MSG_SUCCESS.equals(pledgeApplyStockInfoEntityMessage.getCode())){
            return  pledgeApplyStockInfoEntityMessage;
        }
        applyStockInfoEntity = pledgeApplyStockInfoEntityMessage.getMessage();

        if(!"1".equals(applyStockInfoEntity.getApplyFirstState())){
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","当前数据状态不正确");
        }

        Message<ConfirmingStockInfoEntity> confirmingStockInfoEntityMessage =fcConfirmingStockInfoService.getConfirmingStockInfoById(applyStockInfoEntity.getPledgeId());
        if(! MessageType.MSG_SUCCESS.equals(confirmingStockInfoEntityMessage.getCode())){
            return  confirmingStockInfoEntityMessage;
        }
        confirmingStockInfoEntity =confirmingStockInfoEntityMessage.getMessage();

        applyStockInfoEntity.setApplyFirstState(confirmingStockInfoApprovalForm.getResult());
        applyStockInfoEntity.setUpdateAt(new Date());
        applyStockInfoEntity.setUpdateBy(user.getUsername());
        applyStockInfoEntity.setApplyFirstTime(new Date());
        if (confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT)) {
            applyStockInfoEntity.setApplyRehearState(Constants.PLEDGE_TRIAL_STAY);
            confirmingStockInfoEntity.setConfirmApplyState("2");
        }else {
            confirmingStockInfoEntity.setConfirmApplyState("5");
        }

        applyStockInfoService.updateApplyStockInfo(applyStockInfoEntity);
        fcConfirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);

        //新增审核记录信息
        AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
        auditTaskHistoryEntity.setNode("初审");
        auditTaskHistoryEntity.setApprovalTime(new Date());
        if (confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_REFUSE)) {
            auditTaskHistoryEntity.setResult("拒绝");
        }else if (confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_CANCEL)){
            auditTaskHistoryEntity.setResult("取消");
        }else if (confirmingStockInfoApprovalForm.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT)){
            auditTaskHistoryEntity.setResult("通过");
        }
        auditTaskHistoryEntity.setResultStatus(confirmingStockInfoApprovalForm.getResult());
        auditTaskHistoryEntity.setApprovalMan(user.getRealname());
        auditTaskHistoryEntity.setOrderId(confirmingStockInfoEntity.getPid());
        auditTaskHistoryEntity.setContent(confirmingStockInfoApprovalForm.getContent());
        auditTaskHistoryEntity.setCreateAt(new Date());
        auditTaskHistoryEntity.setUpdateAt(new Date());
        auditTaskHistoryEntity.setUpdateBy(user.getUsername());
        auditTaskHistoryEntity.setCreateBy(user.getUsername());
        auditTaskHistoryEntity.setStatus(Constants.DATA_STATUS_NOL);
        auditTaskHistoryEntity.setType("3");
        Message thMsg =auditTaskHistoryService.insertTaskHistory(auditTaskHistoryEntity);
        if (!MessageType.MSG_SUCCESS.equals(thMsg.getCode())) {
            return new Message<>(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",ReturnMsg.FAILED_CURRENCY);
        }
        //修改task关联表信息
        auditTaskEntity.setUpdateAt(new Date());
        auditTaskEntity.setUpdateBy(user.getUsername());
        auditTaskEntity.setStatus(Constants.DATA_STATUS_STP);//修改审核人状态为已审核的状态，目前使用的为停用
        Message tsakMsg =auditTaskService.updateTask(auditTaskEntity);
        if (!MessageType.MSG_SUCCESS.equals(tsakMsg.getCode())) {
            return new Message<>(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",ReturnMsg.FAILED_CURRENCY);
        }

        return new Message(MessageType.MSG_SUCCESS, "ConfirmingStockTrial_service", "审批成功！");
    }

    @Override
    public Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest) throws ParseException {
        Integer count = 0;
        Message<Integer> msgCount = auditTaskCore.countConfirmingApprovalList(confirmingStockInfoApprovalResquest);
        if(MessageType.MSG_SUCCESS.equals(msgCount.getCode())){
            count = msgCount.getMessage();
            if(count>0){
                if(!ChkUtil.isEmpty(confirmingStockInfoApprovalResquest.getApplyFirstTimeEnd())){
                    String applyFirstTimeEnd = DateUtil.formatDate(confirmingStockInfoApprovalResquest.getApplyFirstTimeEnd(),DateUtil.STYLE_1,DateUtil.STYLE_2);
                    confirmingStockInfoApprovalResquest.setApplyFirstTimeEnd(applyFirstTimeEnd+" 23:59:59");
                }
                if(!ChkUtil.isEmpty(confirmingStockInfoApprovalResquest.getApplyRehearTimeTimeEnd())){
                    String applyRehearTimeTimeEnd = DateUtil.formatDate(confirmingStockInfoApprovalResquest.getApplyRehearTimeTimeEnd(),DateUtil.STYLE_1,DateUtil.STYLE_2);
                    confirmingStockInfoApprovalResquest.setApplyRehearTimeTimeEnd(applyRehearTimeTimeEnd+" 23:59:59");
                }
                if(!ChkUtil.isEmpty(confirmingStockInfoApprovalResquest.getApplyFinalTimeEnd())){
                    String applyFinalTimeEnd = DateUtil.formatDate(confirmingStockInfoApprovalResquest.getApplyFinalTimeEnd(),DateUtil.STYLE_1,DateUtil.STYLE_2);
                    confirmingStockInfoApprovalResquest.setApplyFinalTimeEnd(applyFinalTimeEnd+" 23:59:59");
                }

                Message<List<ConfirmingStockInfoListResponse>> listMessage =  auditTaskCore.confirmingApprovalList(confirmingStockInfoApprovalResquest);
                if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
                    PagedResponse<List<ConfirmingStockInfoListResponse>> response = new PagedResponse<List<ConfirmingStockInfoListResponse>>();
                    response.setData(listMessage.getMessage());
                    response.setTotal(count);
                    response.setPageNo(confirmingStockInfoApprovalResquest.getPageNo());
                    response.setPageSize(confirmingStockInfoApprovalResquest.getPageSize());
                    return new Message(MessageType.MSG_SUCCESS, "ConfirmingStockTrial_service", response);
                }else{
                    return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",listMessage.getMessage());
                }
            }else{
                return new Message(MessageType.MSG_SUCCESS,"ConfirmingStockTrial_service",null);
            }
        }else{
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",msgCount.getMessage());
        }
    }

    @Override
    public Message<AuditExamineResponse> countTask(Integer userId, Integer node) {
        AuditExamineResponse auditExamineResponse = new AuditExamineResponse();
        Message<Integer> waitNum = auditTaskCore.countConfirmingTaskWaitNum(userId,node);
        if(MessageType.MSG_SUCCESS.equals(waitNum.getCode())){
            auditExamineResponse.setWaitNum(waitNum.getMessage().toString());
        }
        Message<Integer> canReceiveNum = pledgeApplyStockInfoCore.countConfirmingTaskCanReceiveNum(node);
        if(MessageType.MSG_SUCCESS.equals(canReceiveNum.getCode())){
            auditExamineResponse.setAlreadyNum(canReceiveNum.getMessage().toString());
        }

        return new Message<AuditExamineResponse>(MessageType.MSG_SUCCESS,"ConfirmingStockFirstTrial_service",auditExamineResponse);
    }

    @Override
    public Message receiveTask(SysUserAdminEntity user) {
        //判断是否有审核权限
        if (!IsExamineAuth(new Long(user.getUserId()), Constants.FIRST_ROLE_ID)) {
          return new Message (MessageType.MSG_ERROR,"ConfirmingStockTrial_service","无领取权限");
        }
        String userName = user.getUsername();
        PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity = new PledgeApplyStockInfoEntity();
        pledgeApplyStockInfoEntity.setStatus("NOL");
        pledgeApplyStockInfoEntity.setApplyFirstState("0");
        pledgeApplyStockInfoEntity.setApplyType("3");
        List<PledgeApplyStockInfoEntity> list = pledgeApplyStockInfoCore.selectByApplyStockInfo(pledgeApplyStockInfoEntity);
        if(ChkUtil.isEmpty(list)){
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","暂无可领取任务，请稍后重试。");
        }

        //查询已领取任务
        AuditTaskEntity auditTaskEntity = new AuditTaskEntity();
        auditTaskEntity.setNode(Constants.TASK_FIRST_NODE);
        auditTaskEntity.setOwerId(new Long(user.getId()));
        auditTaskEntity.setStatus(Constants.DATA_STATUS_NOL);
        auditTaskEntity.setType("3");
        Message<List<AuditTaskEntity>> msgAlready = auditTaskService.selectByTask(auditTaskEntity);

        List<AuditTaskEntity> listAlready = msgAlready.getMessage();
        //已领取任务总数
        Integer conut = listAlready.size();

        //可领取任务的数量
        Integer sureNum = Constants.RECEIVE_MAX_NUM - conut;
        if (sureNum <= 0) {
            //返回当前待处理任务已达上限
            return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service","当前可领取任务已达上限");
        }

        //修改apply_stock_info信息,新增AuditTask表信息
        for (int i = 0; i < list.size(); i++) {
            PledgeApplyStockInfoEntity resultEntity = list.get(i);
            PledgeApplyStockInfoEntity updateEntity = new PledgeApplyStockInfoEntity();
            updateEntity.setPid(resultEntity.getPid());
            updateEntity.setApplyFirstState(Constants.PLEDGE_TRIAL_ALREADY); //改为初审待审核
            updateEntity.setUpdateAt(new Date());
            updateEntity.setUpdateBy(userName);
            //修改apply_stock_info状态为已领取
            Message updateMsg = applyStockInfoService.updateApplyStockInfo(updateEntity);
            if (!MessageType.MSG_SUCCESS.equals(updateMsg.getCode())) {
                return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",ReturnMsg.FAILED_CURRENCY);
            }

            //新增task记录信息
            AuditTaskEntity taskEntity = new AuditTaskEntity();
            taskEntity.setOwerId(new Long(user.getId()));
            taskEntity.setStatus(Constants.DATA_STATUS_NOL);
            taskEntity.setNode(Constants.TASK_FIRST_NODE);
            taskEntity.setOrderInfoId(resultEntity.getPid());
            taskEntity.setCreateBy(userName);
            taskEntity.setUpdateBy(userName);
            taskEntity.setCreateAt(new Date());
            taskEntity.setUpdateAt(new Date());
            taskEntity.setType("3");
            Message taskMsg = auditTaskService.insertTask(taskEntity);
            if (!MessageType.MSG_SUCCESS.equals(taskMsg.getCode())) {
                return new Message(MessageType.MSG_ERROR,"ConfirmingStockTrial_service",ReturnMsg.FAILED_CURRENCY);
            }
            if (i == sureNum - 1) {
                break;
            }
        }
        return new Message(MessageType.MSG_SUCCESS,"ConfirmingStockTrial_service",null);
    }

    private Boolean  IsExamineAuth(Long userId,Integer roleId){
        if (userId.intValue() == Constants.SYSTEM_ADMIN) {
            return true;
        }
        Message<List<SysRoleEntity>> msg = auditSysUserCore.getRolesByUserId(userId);
        List<SysRoleEntity> list = msg.getMessage();
        for (SysRoleEntity sysRoleEntity : list) {
            if (roleId == sysRoleEntity.getRoleId()) {
                return true;
            }
        }
        return false;
    }
}
