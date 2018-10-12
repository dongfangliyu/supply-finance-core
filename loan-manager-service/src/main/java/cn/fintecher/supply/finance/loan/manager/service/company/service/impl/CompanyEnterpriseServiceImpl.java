package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CustomerEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CustomerFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.response.CustomerResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyEnterpriseResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.CompanyBlacklistCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseInfoService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyUserService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004上午 10:26
 */
@Service
public class CompanyEnterpriseServiceImpl implements CompanyEnterpriseService{

    @Autowired
    private IRedisService redisService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private CompanyEnterpriseInfoService companyEnterpriseInfoService;

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    @Autowired
    private CompanyEnterpriseService companyEnterpriseService;

    @Autowired
    private CompanyBlacklistCore companyBlacklistCore;

    @Override
    public Message selectCompanyEnterprise(String name) {
        Message message = new Message();
        CompanyUserEntity companyUserEntity = redisService.get(name, CompanyUserEntity.class);
        if (null == companyUserEntity){
            companyUserEntity = companyUserService.findCompanyUserByName(name);
        }
        CompanyEnterpriseResponse response = new CompanyEnterpriseResponse();
        CompanyEnterpriseEntity companyEnterpriseEntity = searchCompanyEnterpriseEntity(companyUserEntity.getEnterpriseId());
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = companyEnterpriseInfoService.searchCompanyEnterpriseInfoEntity(companyUserEntity.getEnterpriseId());
        BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(companyUserEntity.getEnterpriseId());
        List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(companyUserEntity.getEnterpriseId());
        List<RegisterFileEntity> registerFileEntities = fcRegisterFileCore.searchRegisterFileByOwnerId(companyUserEntity.getEnterpriseId().toString());
        if (null != registerFileEntities){
            response.setRegisterFileEntities(registerFileEntities);
        }
        List<CompanyFileEntity> companyFileEntities = fcEnterpriseFinancialCore.searchAccountingStatementInfo(companyUserEntity.getEnterpriseId());
        if(null != companyFileEntities){
            response.setCompanyFileEntities(companyFileEntities);
        }
        if (null != companyEnterpriseEntity && null != companyEnterpriseInfoEntity && null != list && null != baseBankInfoEntity){
            response.setCompanyEnterpriseEntity(companyEnterpriseEntity);
            response.setCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
            response.setCompanyOperationEntity(list);
            response.setBaseBankInfoEntity(baseBankInfoEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(response);
        }else {
            message.setCode(MsgCodeConstant.ERR_CREATE_COMPANY_ENTERPRISE);
        }
        return message;
    }

    @Override
    public CompanyEnterpriseEntity searchCompanyEnterpriseEntity(Long enterpriseId) {
        return fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(enterpriseId);
    }

    @Override
    public CompanyEnterpriseEntity selectCompanyEnterpriseByPid(Long pid) {
        return fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(pid);
    }

    @Override
    public CompanyEnterpriseInfoEntity selectCompanyEnterpriseInfoByEnterpriseId(Long enterpriseId) {
        return fcCompanyEnterpriseCore.searchCompanyEnterpriseInfoEntity(enterpriseId);
    }

    @Override
    public CompanyOperationEntity searchCompanyLegal(Long enterpriseId) {
        return fcCompanyEnterpriseCore.searchCompanyLegal(enterpriseId);
    }



    /***
     * 线上为前端接口代码
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为后端接口代码
     */



    @Override
    public Message selectCustomerManagementList(CustomerFrom customerFrom) {
        if(StringUtils.isNotBlank(customerFrom.getStartTime())){
            customerFrom.setStartTime(customerFrom.getStartTime() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(customerFrom.getEndTime())){
            customerFrom.setEndTime(customerFrom.getEndTime() + " 23:59:59");
        }
        List<CustomerEntity> list =  fcCompanyEnterpriseCore.selectCustomerManagementList(customerFrom);
        int total = fcCompanyEnterpriseCore.selectCustomerManagemenCount(customerFrom);
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setData(list);
        pagedResponse.setTotal(total);
        pagedResponse.setPageNo(customerFrom.getPageNo());
        pagedResponse.setPageSize(customerFrom.getPageSize());
        return new Message(MessageType.MSG_SUCCESS,"company",pagedResponse);
    }

    @Override
    public Message selectCustomerManagementDetail(Long id) {
        CustomerResponse response = new CustomerResponse();
        //客户基本信息
        CompanyEnterpriseEntity companyEnterpriseEntity = companyEnterpriseService.searchCompanyEnterpriseEntity(id);
        //客户详细信息
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = companyEnterpriseInfoService.searchCompanyEnterpriseInfoEntity(id);
        //客户联系人集合
        List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(id);
        //客户银行信息
        BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(id);
        //客户上传的文件集合
        List<CompanyFileEntity> filelist = fcEnterpriseFinancialCore.searchAllFile(id);
        response.setCompanyEnterpriseEntity(companyEnterpriseEntity);
        response.setCompanyEnterpriseInfoEntity(companyEnterpriseInfoEntity);
        response.setList(list);
        response.setFilelist(filelist);
        response.setBaseBankInfoEntity(baseBankInfoEntity);
        return new Message(MessageType.MSG_SUCCESS,"company",response);
    }

    @Override
    public Message selectCustomerBlackList(BlackListFrom blackListFrom) {
        if(StringUtils.isNotBlank(blackListFrom.getBlackStartTime())){
            blackListFrom.setBlackStartTime(blackListFrom.getBlackStartTime() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(blackListFrom.getBlackEndTime())){
            blackListFrom.setBlackEndTime(blackListFrom.getBlackEndTime() + " 23:59:59");
        }
        if(StringUtils.isNotBlank(blackListFrom.getStartTime())){
            blackListFrom.setStartTime(blackListFrom.getStartTime() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(blackListFrom.getEndTime())){
            blackListFrom.setEndTime(blackListFrom.getEndTime() + " 23:59:59");
        }
        List<CustomerEntity> list =  fcCompanyEnterpriseCore.selectCustomerBlackList(blackListFrom);
        int total = fcCompanyEnterpriseCore.selectCustomerBlackListCount(blackListFrom);
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setData(list);
        pagedResponse.setTotal(total);
        pagedResponse.setPageNo(blackListFrom.getPageNo());
        pagedResponse.setPageSize(blackListFrom.getPageSize());
        return new Message(MessageType.MSG_SUCCESS,"company",pagedResponse);
    }

    public Message deleteBlackList(Long id, String causationInfo) {
        //客户基本信息
        CompanyEnterpriseEntity companyEnterpriseEntity = companyEnterpriseService.searchCompanyEnterpriseEntity(id);
        if(companyEnterpriseEntity==null){
            return new Message(MessageType.MSG_ERROR,"company","客户不存在");
        }
        companyEnterpriseEntity.setBlacklist("0");
        //保存移除黑名单
        fcCompanyEnterpriseCore.updateCompanyEnterpriseEntity(companyEnterpriseEntity);
        //添加移除黑名单记录
        CompanyBlacklistEntity blacklistEntity = new CompanyBlacklistEntity();
        blacklistEntity.setCompanyId(id);
        blacklistEntity.setCausationInfo(causationInfo);
        blacklistEntity.setEvent("2");
        blacklistEntity.setCreateAt(new Date());
        companyBlacklistCore.insertBlacklist(blacklistEntity);

        return new Message(MessageType.MSG_SUCCESS,"company","操作成功");
    }



    public Message submitBlackList(Long id, String causationInfo) {
        //客户基本信息
        CompanyEnterpriseEntity companyEnterpriseEntity = companyEnterpriseService.searchCompanyEnterpriseEntity(id);
        if(companyEnterpriseEntity==null){
            return new Message(MessageType.MSG_ERROR,"company","客户不存在");
        }
        companyEnterpriseEntity.setBlacklist("1");
        companyEnterpriseEntity.setBlacklistTime(new Date());
        //保存添加黑名单黑名单
        fcCompanyEnterpriseCore.updateCompanyEnterpriseEntity(companyEnterpriseEntity);
        //添加移除黑名单记录
        CompanyBlacklistEntity blacklistEntity = new CompanyBlacklistEntity();
        blacklistEntity.setCompanyId(id);
        blacklistEntity.setCausationInfo(causationInfo);
        blacklistEntity.setEvent("1");
        blacklistEntity.setCreateAt(new Date());
        companyBlacklistCore.insertBlacklist(blacklistEntity);

        return new Message(MessageType.MSG_SUCCESS,"company","操作成功");
    }

    @Override
    public Message<List<CompanyEnterpriseEntity>> searchListConfirmationCompany() {
        return fcCompanyEnterpriseCore.searchListConfirmationCompany();
    }
    @Override
    public CompanyEnterpriseEntity selectByName(String name) {
        return fcCompanyEnterpriseCore.selectByName(name) ;
    }


}
