package cn.fintecher.supply.finance.loan.manager.service.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFastFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.confirming.feign.FCConfirmingStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.confirming.service.ConfirmingStockApplyService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:31
 */
@Service
public class ConfirmingStockApplyServiceImpl implements ConfirmingStockApplyService {

    @Autowired
    private FCConfirmingStockInfoService fcConfirmingStockInfoService;

    @Autowired
    private BusinessFileCore businessFileCore;

    @Autowired
    private BusinessFastFileCore businessFastFileCore;

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private FCProProductCore fcProProductCore;

    @Autowired
    private CompanyChainCore companyChainCore;

    @Override
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(ConfirmingStockInfoResquest confirmingStockInfoResquest) {
        //查询总数
        Integer count = null;
        Message<Integer> msgCount = fcConfirmingStockInfoService.countLoanConfirmingStockInfoList(confirmingStockInfoResquest);
        List<ConfirmingStockInfoListResponse> list =null;
        if (MessageType.MSG_SUCCESS.equals(msgCount.getCode())) {
            count = msgCount.getMessage();
            if(count>0){
                //查询列表
                list = fcConfirmingStockInfoService.loanConfirmingStockInfoList(confirmingStockInfoResquest);
            }
        } else {
            return new Message(MessageType.MSG_ERROR, "confirming_service", msgCount.getMessage());
        }

        PagedResponse<List<ConfirmingStockInfoListResponse>> response = new PagedResponse<List<ConfirmingStockInfoListResponse>>();
        response.setData(list);
        response.setTotal(count);
        response.setPageNo(confirmingStockInfoResquest.getPageNo());
        response.setPageSize(confirmingStockInfoResquest.getPageSize());
        return new Message(MessageType.MSG_SUCCESS, "confirming_service", response);
    }

    @Override
    public Message<ConfirmingStockInfoEntity> saveConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm) {
        ConfirmingStockInfoEntity confirmingStockInfoEntity = new ConfirmingStockInfoEntity();
        if(confirmingStockInfoForm.getPid()!=null && confirmingStockInfoForm.getPid()>0){
            Message<ConfirmingStockInfoEntity> result = fcConfirmingStockInfoService.getConfirmingStockInfoById(confirmingStockInfoForm.getPid());
            if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                confirmingStockInfoEntity=result.getMessage();
                if("1".equals(confirmingStockInfoEntity.getConfirmDealerState())){
                    return new Message(MessageType.MSG_ERROR,"confirming_service","融资已确认，不能修改信息！");
                }
            }
        }
        confirmingStockInfoEntity.setBussineNo(confirmingStockInfoForm.getBussineNo());
        confirmingStockInfoEntity.setCompanyDealerId(confirmingStockInfoForm.getCompanyDealerId());
        confirmingStockInfoEntity.setCompanyId(confirmingStockInfoForm.getCompanyId());
        confirmingStockInfoEntity.setContractState("0");
        confirmingStockInfoEntity.setConfirmApplyState("0");
        confirmingStockInfoEntity.setContractApplyAmount(confirmingStockInfoForm.getContractApplyAmount());
        confirmingStockInfoEntity.setContractApplyTerm(confirmingStockInfoForm.getContractApplyTerm());
        confirmingStockInfoEntity.setConfirmDealerState("0");
        confirmingStockInfoEntity.setConfirmCorecompanyState("0");
        confirmingStockInfoEntity.setContractNo(confirmingStockInfoForm.getContractNo());
        confirmingStockInfoEntity.setContractType(confirmingStockInfoForm.getContractType());
        confirmingStockInfoEntity.setProProductId(confirmingStockInfoForm.getProRoductId());
        confirmingStockInfoEntity.setContractAmount(confirmingStockInfoForm.getContractAmount());
        confirmingStockInfoEntity.setFileCode(confirmingStockInfoForm.getFileCode());
        return fcConfirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);
    }

    @Override
    public Message submitConfirmingStockinfo(ConfirmingStockInfoForm confirmingStockInfoForm) {
        ConfirmingStockInfoEntity confirmingStockInfoEntity = new ConfirmingStockInfoEntity();
        if(confirmingStockInfoForm.getPid()!=null && confirmingStockInfoForm.getPid()>0){
            Message<ConfirmingStockInfoEntity> result = fcConfirmingStockInfoService.getConfirmingStockInfoById(confirmingStockInfoForm.getPid());
            if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                confirmingStockInfoEntity=result.getMessage();
                if("1".equals(confirmingStockInfoEntity.getConfirmDealerState())){
                    return new Message(MessageType.MSG_ERROR,"confirming_service","融资已确认，不能修改信息！");
                }
            }
        }
        confirmingStockInfoEntity.setBussineNo(confirmingStockInfoForm.getBussineNo());
        confirmingStockInfoEntity.setCompanyDealerId(confirmingStockInfoForm.getCompanyDealerId());
        confirmingStockInfoEntity.setCompanyId(confirmingStockInfoForm.getCompanyId());
        confirmingStockInfoEntity.setContractState("0");
        confirmingStockInfoEntity.setConfirmApplyState("1");
        confirmingStockInfoEntity.setContractApplyAmount(confirmingStockInfoForm.getContractApplyAmount());
        confirmingStockInfoEntity.setContractApplyTerm(confirmingStockInfoForm.getContractApplyTerm());
        confirmingStockInfoEntity.setConfirmDealerState("1");
        confirmingStockInfoEntity.setConfirmtDealerTime(new Date());
        confirmingStockInfoEntity.setContractNo(confirmingStockInfoForm.getContractNo());
        confirmingStockInfoEntity.setContractType(confirmingStockInfoForm.getContractType());
        confirmingStockInfoEntity.setProProductId(confirmingStockInfoForm.getProRoductId());
        confirmingStockInfoEntity.setFileCode(confirmingStockInfoForm.getFileCode());
        return fcConfirmingStockInfoService.saveOrUpdateConfirmingStockInfo(confirmingStockInfoEntity);
    }

    @Override
    public Message upload(MultipartFile file, String type, String code) {
        Message message = new Message(MessageType.MSG_SUCCESS,"business",ReturnMsg.SSUCCESS_CURRENCY);
        ResponseEntity<Message> entity = businessFastFileCore.fileUpload(file);
        message = entity.getBody();
        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
        if (message.getCode() == 0){
            if("confirmationPurchaseDetails".equals(type)){
                Message<List<BusinessFileEntity>> listMessage = businessFileCore.getListByCodeAndType("confirmationPurchaseDetails",code);
                if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
                    List<BusinessFileEntity> list = listMessage.getMessage();
                    if(list!=null && list.size()>0){
                        for (BusinessFileEntity businessFileEntity :list){
                            businessFileEntity.setStatus("DEL");
                            businessFileCore.updateFile(businessFileEntity);
                        }
                    }
                }
            }
            BusinessFileEntity fileEntity = new BusinessFileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setCategory(type);
            fileEntity.setOwnerId(code);
            fileEntity.setPath(params.get("path"));
            fileEntity.setFullPath(params.get("fullPath"));
            fileEntity.setGroup(params.get("group"));
            fileEntity.setStatus(Constants.DATA_STATUS_NOL);
            fileEntity.setCreateAt(new Date());
            fileEntity.setUpdateAt(new Date());
            message = businessFileCore.insertFile(fileEntity);

        }
        return message;
    }

    @Override
    public Message<ConfirmingStockInfoDetailResponse> getDetailById(Long pid) {
        Message<ConfirmingStockInfoEntity> confirmingStockInfoEntityMessage =fcConfirmingStockInfoService.getConfirmingStockInfoById(pid);
        if(MessageType.MSG_SUCCESS.equals(confirmingStockInfoEntityMessage.getCode())){
            ConfirmingStockInfoDetailResponse confirmingStockInfoDetailResponse = new ConfirmingStockInfoDetailResponse();
            ConfirmingStockInfoEntity confirmingStockInfoEntity = confirmingStockInfoEntityMessage.getMessage();
            confirmingStockInfoDetailResponse.setConfirmingStockInfoEntity(confirmingStockInfoEntity);
            if(confirmingStockInfoEntity.getCompanyId()!=null &&confirmingStockInfoEntity.getCompanyId()>0){
                CompanyEnterpriseEntity companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(confirmingStockInfoEntity.getCompanyId());
                confirmingStockInfoDetailResponse.setCompanyEnterpriseEntity(companyEnterpriseEntity);
            }
            if(confirmingStockInfoEntity.getCompanyDealerId()!=null &&confirmingStockInfoEntity.getCompanyDealerId()>0){
                CompanyEnterpriseEntity companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(confirmingStockInfoEntity.getCompanyDealerId());
                confirmingStockInfoDetailResponse.setDealerEntity(companyEnterpriseEntity);
            }
            if(confirmingStockInfoEntity.getProProductId()!=null &&confirmingStockInfoEntity.getProProductId()>0){
                ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(confirmingStockInfoEntity.getProProductId().toString());
                confirmingStockInfoDetailResponse.setProProductEntity(proProductEntity);
            }
            if(!ChkUtil.isEmpty(confirmingStockInfoEntity.getFileCode())){
                Message<List<BusinessFileEntity>> result1 =  businessFileCore.getListByCodeAndType("confirmationPurchaseDetails",confirmingStockInfoEntity.getFileCode());
                if(MessageType.MSG_SUCCESS.equals(result1.getCode()) && result1.getMessage()!=null && result1.getMessage().size()>0){
                    confirmingStockInfoDetailResponse.setPurchaseDetails(result1.getMessage().get(0));
                }

                Message<List<BusinessFileEntity>> result2 =  businessFileCore.getListByCodeAndType("confirmationLoanContact",confirmingStockInfoEntity.getFileCode());
                if(MessageType.MSG_SUCCESS.equals(result2.getCode())){
                    confirmingStockInfoDetailResponse.setLoanContactFile(result2.getMessage());
                }

                Message<List<BusinessFileEntity>> result3 =  businessFileCore.getListByCodeAndType("confirmationCoreContact",confirmingStockInfoEntity.getFileCode());
                if(MessageType.MSG_SUCCESS.equals(result3.getCode())){
                    confirmingStockInfoDetailResponse.setCoreContactFile(result3.getMessage());
                }
            }
            return  new Message<ConfirmingStockInfoDetailResponse>(MessageType.MSG_SUCCESS, "confirming_service", confirmingStockInfoDetailResponse);
        }else{
            return  new Message(confirmingStockInfoEntityMessage.getCode(),"confirming_service",confirmingStockInfoEntityMessage.getMessage());
        }
    }

    @Override
    public Message<CompanyEnterpriseEntity> getCompany(Long enterpriseId) {
        try {
            CompanyEnterpriseEntity companyEnterpriseEntity =fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(enterpriseId);
            Message<CompanyChainEntity> result = companyChainCore.getByCompanyName(companyEnterpriseEntity.getName());
            if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                CompanyChainEntity companyChainEntity =result.getMessage();
                return new Message(MessageType.MSG_SUCCESS,"confirming_service",fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(companyChainEntity.getOwerId()));
            }else{
                return new Message(MessageType.MSG_ERROR,"confirming_service",result.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new Message(MessageType.MSG_ERROR,"confirming_service",e.getMessage());
        }
    }
}
