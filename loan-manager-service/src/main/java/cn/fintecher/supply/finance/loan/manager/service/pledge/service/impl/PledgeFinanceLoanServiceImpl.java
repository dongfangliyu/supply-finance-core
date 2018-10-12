package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.form.PledgeFinanceLoanForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceLoanResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeFinanceResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseBankInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.commodity.feign.CommodityStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.FCFinanceStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.FCPledgeFinanceLoanCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import com.google.common.base.Strings;
import freemarker.core.StringArraySequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cn.fintecher.supply.finance.loan.manager.common.util.DateUtil.STYLE_8;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 2:36
 */
@Service
public class PledgeFinanceLoanServiceImpl implements PledgeFinanceLoanService {

    @Autowired
    private FCPledgeFinanceLoanCore fcPledgeFinanceLoanCore;

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

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private FCProProductCore fcProProductCore;


    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> searchFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm, CompanyUserEntity user) {
        Message<PagedResponse<List<PledgeFinanceLoanResponse>>> message = new Message<>(MessageType.MSG_SUCCESS,"financeLoan",null);
        if (ChkUtil.isEmpty(user)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            return message;
        }

        PagedResponse<List<PledgeFinanceLoanResponse>> pageResponse = new PagedResponse<>();
        pledgeFinanceLoanForm.setCompanyId(user.getEnterpriseId());
        if (!(Strings.isNullOrEmpty(pledgeFinanceLoanForm.getStartTime()) && Strings.isNullOrEmpty(pledgeFinanceLoanForm.getEndTime()))){
            // 转换日期
            try {
                pledgeFinanceLoanForm.setStartTime(DateUtil.TransformatStartTime(pledgeFinanceLoanForm.getStartTime()));
                pledgeFinanceLoanForm.setEndTime(DateUtil.TransformatEndTime(pledgeFinanceLoanForm.getEndTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // 查询列表条数
        int total = 0;
        if(pledgeFinanceLoanForm.getPageNo() != 0){
            total = fcPledgeFinanceLoanCore.searchFinanceLoanListCount(pledgeFinanceLoanForm);
        }
        // 查询列表
        List<PledgeFinanceLoanResponse> list = fcPledgeFinanceLoanCore.searchFinanceLoanList(pledgeFinanceLoanForm);
        if (null != list){
            pageResponse.setPageNo(pledgeFinanceLoanForm.getPageNo());
            pageResponse.setPageSize(pledgeFinanceLoanForm.getPageSize());
            pageResponse.setTotal(total);
            pageResponse.setData(list);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        Message<PagedResponse<List<PledgeFinanceLoanResponse>>> message = new Message<>(MessageType.MSG_SUCCESS,"financeLoan",null);
        PagedResponse<List<PledgeFinanceLoanResponse>> pageResponse = new PagedResponse<>();
        // 转换日期
        if (!(Strings.isNullOrEmpty(pledgeFinanceLoanForm.getStartTime()) && Strings.isNullOrEmpty(pledgeFinanceLoanForm.getEndTime()))){
            try {
                pledgeFinanceLoanForm.setEndTime(DateUtil.TransformatEndTime(pledgeFinanceLoanForm.getEndTime()));
                pledgeFinanceLoanForm.setStartTime(DateUtil.TransformatStartTime(pledgeFinanceLoanForm.getStartTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // 查询列表条数
        int total = 0;
        if(pledgeFinanceLoanForm.getPageNo() != 0){
            total = fcPledgeFinanceLoanCore.selectFinanceLoanListCount(pledgeFinanceLoanForm);
        }
        // 查询列表
        List<PledgeFinanceLoanResponse> list = fcPledgeFinanceLoanCore.selectFinanceLoanList(pledgeFinanceLoanForm);
        if (null != list){
            pageResponse.setPageSize(pledgeFinanceLoanForm.getPageSize());
            pageResponse.setPageNo(pledgeFinanceLoanForm.getPageNo());
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message selectFinanceLoanDetail(Long pid) {
        Message message = new Message();
        PledgeFinanceResponse financeResponse = new PledgeFinanceResponse();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();

        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }

        CompanyEnterpriseEntity companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(commodityStockInfoEntity.getCompanyId());
        financeResponse.setCompanyEnterpriseEntity(companyEnterpriseEntity);

        // 查询仓单质押申请信息表
        PledgeStockInfoEntity infoEntity = new PledgeStockInfoEntity();
        infoEntity.setCommodityId(commodityStockInfoEntity.getPid());
        infoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<PledgeStockInfoEntity> pledgeStockInfoEntities = pledgeStockInfoCore.selectByStockInfo(infoEntity);
        if (pledgeStockInfoEntities.size() > 0){
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeStockInfoEntities.get(0);
            financeResponse.setPledgeStockInfoEntity(pledgeStockInfoEntity);
            // 查询产品
            ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pledgeStockInfoEntity.getProRoductId().toString());
            financeResponse.setProProductEntity(proProductEntity);
        }

        // 查询仓单放款申请信息表
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(pid);
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
        businessFileEntity.setCommodityId(pid);
        businessFileEntity.setCategory("loanVoucher");
        businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
        Message message1 = businessFileCore.selectByFile(businessFileEntity);
        List<BusinessFileEntity> businessFileEntities = JSONUtil.toList(message1.getMessage(), BusinessFileEntity.class);
        if (businessFileEntities.size() > 0){
            BusinessFileEntity businessFileEntity1 = businessFileEntities.get(0);
            financeResponse.setBusinessFileEntity(businessFileEntity1);
        }


        financeResponse.setCommodityStockInfoEntity(commodityStockInfoEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(financeResponse);
        return message;
    }

    @Override
    public Message financeLoanPass(Long pid) {
        Message message = new Message();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();
        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(pid);
        financeStockInfoEntity.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0){
            // 加入判断
            FinanceStockInfoEntity financeStockInfoEntity1 = financeStockInfoEntities.get(0);
            if (financeStockInfoEntity1.getFinanceFirstState().equals("0")){
                financeStockInfoEntity1.setFinanceFirstTime(new Date());
                financeStockInfoEntity1.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT_PASS);
                fcFinanceStockInfoCore.updateStockInfo(financeStockInfoEntity1);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;
            }else {
                message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                message.setMessage("您已经审核,请勿重复审核");
                return message;
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
    }

    @Override
    public Message financeLoanDefault(Long pid) {
        Message message = new Message();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();
        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(pid);
        financeStockInfoEntity.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0){
            FinanceStockInfoEntity financeStockInfoEntity1 = financeStockInfoEntities.get(0);
            if (financeStockInfoEntity1.getFinanceFirstState().equals("0")){
                financeStockInfoEntity1.setFinanceFirstTime(new Date());
                financeStockInfoEntity1.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT_DEFAULT);
                fcFinanceStockInfoCore.updateStockInfo(financeStockInfoEntity1);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;
            }else {
                message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                message.setMessage("您已经审核,请勿重复审核");
                return message;
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
    }

    @Override
    public Message<PagedResponse<List<PledgeFinanceLoanResponse>>> selectFinanceReviewLoanList(PledgeFinanceLoanForm pledgeFinanceLoanForm) {
        Message<PagedResponse<List<PledgeFinanceLoanResponse>>> message = new Message<>(MessageType.MSG_SUCCESS,"financeLoan",null);
        PagedResponse<List<PledgeFinanceLoanResponse>> pageResponse = new PagedResponse<>();
        // 转换日期
        if (!(Strings.isNullOrEmpty(pledgeFinanceLoanForm.getStartTime()) && Strings.isNullOrEmpty(pledgeFinanceLoanForm.getEndTime()))){
            try {
                pledgeFinanceLoanForm.setEndTime(DateUtil.TransformatEndTime(pledgeFinanceLoanForm.getEndTime()));
                pledgeFinanceLoanForm.setStartTime(DateUtil.TransformatStartTime(pledgeFinanceLoanForm.getStartTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // 查询列表条数
        int total = 0;
        if(pledgeFinanceLoanForm.getPageNo() != 0){
            total = fcPledgeFinanceLoanCore.selectFinanceReviewLoanListCount(pledgeFinanceLoanForm);
        }
        // 查询列表
        List<PledgeFinanceLoanResponse> list = fcPledgeFinanceLoanCore.selectFinanceReviewLoanList(pledgeFinanceLoanForm);
        if (null != list){
            pageResponse.setPageSize(pledgeFinanceLoanForm.getPageSize());
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(pledgeFinanceLoanForm.getPageNo());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message financeReviewLoanPass(Long pid,String loanTime) {
        Message message = new Message();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();
        if (ChkUtil.isEmpty(commodityStockInfoEntity) || Strings.isNullOrEmpty(loanTime)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        // 查询仓单质押申请信息表
        PledgeStockInfoEntity infoEntity = new PledgeStockInfoEntity();
        infoEntity.setCommodityId(pid);
        infoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<PledgeStockInfoEntity> pledgeStockInfoEntities = pledgeStockInfoCore.selectByStockInfo(infoEntity);
        // 查询放款信息表
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(pid);
        financeStockInfoEntity.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT_PASS);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0 && financeStockInfoEntities.size() < 2){
            FinanceStockInfoEntity stockInfoEntity = financeStockInfoEntities.get(0);
            if (stockInfoEntity.getFinanceRehearState().equals("0")){
                stockInfoEntity.setFinanceRehearTime(new Date());
                stockInfoEntity.setFinanceRehearState(Constants.PLEDGE_WAIT_AUDIT_PASS);
                stockInfoEntity.setUpdateAt(new Date());
                // 计算放款周期时间
                if (pledgeStockInfoEntities.size() > 0 && pledgeStockInfoEntities.size() < 2){
                    infoEntity = pledgeStockInfoEntities.get(0);
                    int i = infoEntity.getContractApplyTerm().intValue();
                    try {
                        String date = DateUtil.getLastTime(loanTime,1,i,null);
                        stockInfoEntity.setLoanTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loanTime));
                        stockInfoEntity.setLoanStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(loanTime));
                        stockInfoEntity.setLoanEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // 修改状态
                    infoEntity.setFinanceState("1");
                    infoEntity.setReleasePledge("0");
                    pledgeStockInfoCore.updateStockInfo(infoEntity);

                }else {
                    message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                    message.setMessage("数据异常!");
                    return message;
                }

                fcFinanceStockInfoCore.updateStockInfo(stockInfoEntity);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;

            }else {
                message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                message.setMessage("您已经审核,请勿重复审核");
                return message;
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
    }

    @Override
    public Message financeReviewLoanDefault(Long pid) {
        Message message = new Message();
        // 查询入库信息表
        Message<CommodityStockInfoEntity> commodityStockInfoEntityMessage = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityStockInfoEntityMessage.getMessage();
        if (ChkUtil.isEmpty(commodityStockInfoEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        FinanceStockInfoEntity financeStockInfoEntity = new FinanceStockInfoEntity();
        financeStockInfoEntity.setCommodityId(pid);
        financeStockInfoEntity.setFinanceFirstState(Constants.PLEDGE_WAIT_AUDIT_PASS);
        financeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
        List<FinanceStockInfoEntity> financeStockInfoEntities = fcFinanceStockInfoCore.selectByStockInfo(financeStockInfoEntity);
        if (financeStockInfoEntities.size() > 0 && financeStockInfoEntities.size() < 2){
            FinanceStockInfoEntity stockInfoEntity = financeStockInfoEntities.get(0);
            if (stockInfoEntity.getFinanceRehearState().equals("0")){

                stockInfoEntity.setFinanceRehearTime(new Date());
                stockInfoEntity.setUpdateAt(new Date());
                stockInfoEntity.setFinanceRehearState(Constants.PLEDGE_WAIT_AUDIT_DEFAULT);
                fcFinanceStockInfoCore.updateStockInfo(stockInfoEntity);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;
            }else {
                message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                message.setMessage("您已经审核,请勿重复审核");
                return message;
            }
        }else {
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
    }

    @Override
    public Message selectLoanByTermAndTime(Long contractApplyTerm, String loanTime) {
        Message message = new Message();
        if (contractApplyTerm <0 || Strings.isNullOrEmpty(loanTime)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        int intValue = contractApplyTerm.intValue();
        try {
            String lastTime = DateUtil.getLastTime(loanTime, 1, intValue, null);
            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);
            lastTime = new SimpleDateFormat("yyyy年MM年dd日").format(parse);
            Date parse1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loanTime);
            String format = new SimpleDateFormat("yyyy年MM年dd日").format(parse1);
            String forback = format +"-"+ lastTime;
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(forback);
            return message;
        } catch (ParseException e) {
            e.printStackTrace();
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            return message;
        }
    }
}
