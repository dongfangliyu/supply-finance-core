package cn.fintecher.supply.finance.loan.manager.service.commodity.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackListResponse;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackDetailResponse;
import cn.fintecher.supply.finance.loan.manager.service.commodity.feign.CommodityStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.commodity.feign.FCWarehouseUnpackCore;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.WarehouseUnpackService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseCreditCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:31 2018/8/23
 */
@Service("WarehouseUnpackService")
public class WarehouseUnpackServiceImpl implements WarehouseUnpackService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdfNotHM = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    private FCWarehouseUnpackCore fcWarehouseUnpackCore;

    @Autowired
    private CommodityStockInfoCore commodityStockInfoCore;

    @Autowired
    private PledgeStockInfoCore pledgeStockInfoCore;

    @Autowired
    private FCProProductCore fcProProductCore;

    @Autowired
    private FCEnterpriseCreditCore fcEnterpriseCreditCore;

    @Override
    public Message searchWarehouseUnpackList(WarehouseUnpackForm warehouseUnpackForm, CompanyUserEntity user) {
        PageResponse response = new PageResponse();
        if(StringUtils.isNotBlank(warehouseUnpackForm.getWarehouseUnpackTimeStart())){
            warehouseUnpackForm.setWarehouseUnpackTimeStart(warehouseUnpackForm.getWarehouseUnpackTimeStart() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(warehouseUnpackForm.getWarehouseUnpackTimeEnd())){
            warehouseUnpackForm.setWarehouseUnpackTimeEnd(warehouseUnpackForm.getWarehouseUnpackTimeEnd() + " 23:59:59");
        }
        warehouseUnpackForm.setCompanyId(user.getEnterpriseId());
        List<WarehouseUnpackListResponse> list =fcWarehouseUnpackCore.searchWarehouseUnpackList(warehouseUnpackForm);
        int total = fcWarehouseUnpackCore.searchWarehouseUnpackListCount(warehouseUnpackForm);
        response.setData(list);
        response.setTotal(total);
        response.setPageSize(warehouseUnpackForm.getPageSize());
        response.setPageNo(warehouseUnpackForm.getPageNo());
        return new Message(MessageType.MSG_SUCCESS,"commodityStockInfo",response);
    }

    @Override
    public Message searchAdminWarehouseUnpackList(WarehouseUnpackAdminForm warehouseUnpackAdminForm) {
        PageResponse response = new PageResponse();
        if(StringUtils.isNotBlank(warehouseUnpackAdminForm.getWarehouseUnpackTimeStart())){
            warehouseUnpackAdminForm.setWarehouseUnpackTimeStart(warehouseUnpackAdminForm.getWarehouseUnpackTimeStart() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(warehouseUnpackAdminForm.getWarehouseUnpackTimeEnd())){
            warehouseUnpackAdminForm.setWarehouseUnpackTimeEnd(warehouseUnpackAdminForm.getWarehouseUnpackTimeEnd() + " 23:59:59");
        }
        List<WarehouseUnpackListResponse> list = fcWarehouseUnpackCore.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
        int total = fcWarehouseUnpackCore.searchAdminWarehouseUnpackListCount(warehouseUnpackAdminForm);
        response.setData(list);
        response.setTotal(total);
        response.setPageSize(warehouseUnpackAdminForm.getPageSize());
        response.setPageNo(warehouseUnpackAdminForm.getPageNo());
        return new Message(MessageType.MSG_SUCCESS,"commodityStockInfo",response);
    }

    @Override
    public Message searchAdminWarehouseUnpackDetail(Long pid) {
        WarehouseUnpackDetailResponse response = new WarehouseUnpackDetailResponse();
        Message<CommodityStockInfoEntity> commodityEntity = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityEntity.getMessage();
        if(MessageType.MSG_SUCCESS.equals(commodityEntity.getCode())) {
            if (null != commodityStockInfoEntity) {
                response.setApplyNumber(commodityStockInfoEntity.getApplyNumber());
                response.setSupplierName(commodityStockInfoEntity.getSupplierName());
                response.setProductBrand(commodityStockInfoEntity.getProductBrand());
                response.setProductType(commodityStockInfoEntity.getProductType());
                response.setProductNumber(commodityStockInfoEntity.getProductNumber());
                response.setUnitPrice(commodityStockInfoEntity.getUnitPrice());
                response.setTotalPrice(commodityStockInfoEntity.getTotalPrice());
                response.setProductAddressProvince(commodityStockInfoEntity.getProductAddressProvince());
                response.setProductAddressCity(commodityStockInfoEntity.getProductAddressCity());
                response.setProductModel(commodityStockInfoEntity.getProductModel());
                response.setProductGrade(commodityStockInfoEntity.getProductGrade());
                response.setProductStandard(commodityStockInfoEntity.getProductStandard());
                response.setProductSize(commodityStockInfoEntity.getProductSize());
                response.setEffectiveStartTime(this.sdf.format(commodityStockInfoEntity.getEffectiveStartTime()));
                response.setEffectiveEndTime(this.sdf.format(commodityStockInfoEntity.getEffectiveEndTime()));
                response.setProductionDate(this.sdf.format(commodityStockInfoEntity.getProductionDate()));
                response.setProductColour(commodityStockInfoEntity.getProductColour());
                response.setProductUnit(commodityStockInfoEntity.getProductUnit().toString());
                //返回客户信息
                if (null != commodityStockInfoEntity.getCompanyId()) {
                    CompanyEnterpriseEntity enterpriseEntity = fcEnterpriseCreditCore.searchCompanyInfo(commodityStockInfoEntity.getCompanyId());
                    if (null != enterpriseEntity) {
                        response.setCustomerName(enterpriseEntity.getName());
                    }
                }
            }
            Message<List<PledgeStockInfoEntity>> pledgeEntity = fcWarehouseUnpackCore.searchPledgeInfoByCommodityId(pid.toString());
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeEntity.getMessage().get(0);
            if (MessageType.MSG_SUCCESS.equals(pledgeEntity.getCode())) {
                if(null != pledgeEntity.getMessage().get(0)) {
                    response.setInterest(pledgeStockInfoEntity.getContractApplyInterest());
                    response.setServiceFee(pledgeStockInfoEntity.getContractApplyServiceFee());
                    response.setPid(pledgeStockInfoEntity.getPid());
                    response.setPeriod(pledgeStockInfoEntity.getContractApplyTerm());
                    if (null != pledgeStockInfoEntity.getReleaseApplTime()) {
                        response.setReleaseApplyTime(this.sdf.format(pledgeStockInfoEntity.getReleaseApplTime()));
                    }
                    if (null != pledgeStockInfoEntity.getReleaseTime()) {
                        response.setReleaseTime(this.sdf.format(pledgeStockInfoEntity.getReleaseTime()));

                    }
                    response.setLoanMoney(pledgeStockInfoEntity.getContractApplyAmount());
                    response.setRepaymentMoney((pledgeStockInfoEntity.getContractApplyAmount()));
                    if (null != pledgeStockInfoEntity.getReleaseRepaymentTime()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        response.setReleaseRepaymentTime(sdf.format(pledgeStockInfoEntity.getReleaseRepaymentTime()));
                    }
                }
            }

            //返回产品信息
            if (null != pledgeStockInfoEntity.getProRoductId()) {
                ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pledgeStockInfoEntity.getProRoductId().toString());
                response.setInterestRate(proProductEntity.getInterestRate().toString());
                response.setServiceFeeRate(proProductEntity.getServiceFee().toString());
            }

            FinanceStockInfoEntity financeStockInfoEntity = fcWarehouseUnpackCore.selectByStockInfoByCommodityId(pledgeStockInfoEntity.getCommodityId().toString());
            if (null != financeStockInfoEntity) {
                if (null != financeStockInfoEntity.getLoanTime()) {
                    response.setLoanTime(this.sdf.format(financeStockInfoEntity.getLoanTime()));
                }
                if (null != financeStockInfoEntity.getLoanStartTime()) {
                    response.setLoanStartTime(this.sdf.format(financeStockInfoEntity.getLoanStartTime()));
                }
                if (null != financeStockInfoEntity.getLoanStartTime()) {
                    response.setLoanEndTime(this.sdf.format(financeStockInfoEntity.getLoanEndTime()));
                }
            }

            if(null !=pledgeStockInfoEntity.getPid()){
                List<BusinessFileEntity> list = fcWarehouseUnpackCore.searchFileByCommodityId(pid.toString());
                if(null != list){
                    response.setBusinessFileEntities(list);
                }
            }

            return  new Message(MessageType.MSG_SUCCESS,"audit_service",response);

        } else {
            return  new Message(MessageType.MSG_ERROR,"audit_service",null);
        }
    }

    @Override
    public Message submitWarehouseUnpack(Long pid) {
        PledgeStockInfoEntity pledgeStockInfoEntity = pledgeStockInfoCore.queryStockInfoByPid(pid.toString());
        pledgeStockInfoEntity.setReleasePledge("2");//后台已解押
        pledgeStockInfoEntity.setReleaseTime(new Date());
        pledgeStockInfoCore.updateStockInfo(pledgeStockInfoEntity);
        return  new Message(MessageType.MSG_SUCCESS,"audit_service",null);
    }

    @Override
    public Message submitFrontWarehouseUnpack(Long pid, String time) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeStockInfoCore.queryStockInfoByPid(pid.toString());
            pledgeStockInfoEntity.setReleaseRepaymentTime(sdf.parse(time + " 00:00:00"));
            pledgeStockInfoEntity.setReleasePledge("1");//前台已解押 解压中
            pledgeStockInfoEntity.setReleaseApplTime(new Date());
            pledgeStockInfoCore.updateStockInfo(pledgeStockInfoEntity);
            return  new Message(MessageType.MSG_SUCCESS,"audit_service",null);
        }catch (Exception e){
            return  new Message(MessageType.MSG_ERROR,"audit_service",null);
        }

    }

    @Override
    public Message searchWarehouseUnpackDetail(Long pid, CompanyUserEntity user) {
        WarehouseUnpackDetailResponse response = new WarehouseUnpackDetailResponse();
        Message<CommodityStockInfoEntity> commodityEntity = commodityStockInfoCore.queryStockInfoByPid(pid.toString());
        CommodityStockInfoEntity commodityStockInfoEntity = commodityEntity.getMessage();
        if((long)user.getEnterpriseId() != (long)commodityStockInfoEntity.getCompanyId()){
            return new Message<>(MessageType.MSG_SUCCESS,"pledge","无查询权限");
        }
        if(MessageType.MSG_SUCCESS.equals(commodityEntity.getCode())){
            if(null != commodityStockInfoEntity){
                response.setApplyNumber(commodityStockInfoEntity.getApplyNumber());
                response.setSupplierName(commodityStockInfoEntity.getSupplierName());
                response.setProductBrand(commodityStockInfoEntity.getProductBrand());
                response.setProductType(commodityStockInfoEntity.getProductType());
                response.setProductNumber(commodityStockInfoEntity.getProductNumber());
                response.setUnitPrice(commodityStockInfoEntity.getUnitPrice());
                response.setTotalPrice(commodityStockInfoEntity.getTotalPrice());
                response.setProductAddressProvince(commodityStockInfoEntity.getProductAddressProvince());
                response.setProductAddressCity(commodityStockInfoEntity.getProductAddressCity());
                response.setProductModel(commodityStockInfoEntity.getProductModel());
                response.setProductGrade(commodityStockInfoEntity.getProductGrade());
                response.setProductStandard(commodityStockInfoEntity.getProductStandard());
                response.setProductSize(commodityStockInfoEntity.getProductSize());
                response.setEffectiveEndTime(this.sdf.format(commodityStockInfoEntity.getEffectiveEndTime()));
                response.setEffectiveStartTime(this.sdf.format(commodityStockInfoEntity.getEffectiveStartTime()));
                response.setProductionDate(this.sdf.format(commodityStockInfoEntity.getProductionDate()));
                response.setProductColour(commodityStockInfoEntity.getProductColour());
                response.setProductUnit(commodityStockInfoEntity.getProductUnit().toString());
                //返回客户信息
                if(null != commodityStockInfoEntity.getCompanyId()){
                    CompanyEnterpriseEntity enterpriseEntity = fcEnterpriseCreditCore.searchCompanyInfo(commodityStockInfoEntity.getCompanyId());
                    if(null != enterpriseEntity){
                        response.setCustomerName(enterpriseEntity.getName());
                    }
                }
            }
            Message<List<PledgeStockInfoEntity>> pledgeEntity = fcWarehouseUnpackCore.searchPledgeInfoByCommodityId(pid.toString());
            PledgeStockInfoEntity pledgeStockInfoEntity = pledgeEntity.getMessage().get(0);
            if(MessageType.MSG_SUCCESS.equals(pledgeEntity.getCode())){
                if(null != pledgeEntity.getMessage().get(0)) {
                    response.setInterest(pledgeStockInfoEntity.getContractApplyInterest());
                    response.setServiceFee(pledgeStockInfoEntity.getContractApplyServiceFee());
                    response.setPid(pledgeStockInfoEntity.getPid());
                    response.setPeriod(pledgeStockInfoEntity.getContractApplyTerm());
                    if (null != pledgeStockInfoEntity.getReleaseTime()) {
                        response.setReleaseTime(this.sdf.format(pledgeStockInfoEntity.getReleaseTime()));
                    }
                    if (null != pledgeStockInfoEntity.getReleaseApplTime()) {
                        response.setReleaseApplyTime(this.sdf.format(pledgeStockInfoEntity.getReleaseApplTime()));
                    }
                    response.setLoanMoney(pledgeStockInfoEntity.getContractApplyAmount());
                    response.setRepaymentMoney((pledgeStockInfoEntity.getContractApplyAmount()));
                    if(pledgeStockInfoEntity.getReleaseRepaymentTime()!=null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        response.setReleaseRepaymentTime(sdf.format(pledgeStockInfoEntity.getReleaseRepaymentTime()));
                    }
                    if (null != pledgeStockInfoEntity.getContractApplyInterest() && null != pledgeStockInfoEntity.getContractApplyServiceFee() && null != pledgeStockInfoEntity.getContractApplyAmount()) {
                        response.setApprovalMoney(pledgeStockInfoEntity.getContractApplyInterest().add(pledgeStockInfoEntity.getContractApplyServiceFee()).add(pledgeStockInfoEntity.getContractApplyAmount()).doubleValue());
                    }
                }
            }


            FinanceStockInfoEntity financeStockInfoEntity = fcWarehouseUnpackCore.selectByStockInfoByCommodityId(pledgeStockInfoEntity.getCommodityId().toString());
            if(null != financeStockInfoEntity){
                if(null != financeStockInfoEntity.getLoanTime()){
                    response.setLoanTime(this.sdf.format(financeStockInfoEntity.getLoanTime()));
                }
                if(null != financeStockInfoEntity.getLoanEndTime() ){
                    response.setLoanEndTime(this.sdf.format(financeStockInfoEntity.getLoanEndTime()));
                    response.setRepaymentTime(this.sdf.format(financeStockInfoEntity.getLoanEndTime()));//应还日期
                }
                if(null != financeStockInfoEntity.getLoanStartTime() ){
                    response.setLoanStartTime(this.sdf.format(financeStockInfoEntity.getLoanStartTime()));
                }
            }
            //返回产品信息
            if (null != pledgeStockInfoEntity.getProRoductId()) {
                ProProductEntity proProductEntity = fcProProductCore.searchProductDetail(pledgeStockInfoEntity.getProRoductId().toString());
                response.setInterestRate(proProductEntity.getInterestRate().toString());
                response.setServiceFeeRate(proProductEntity.getServiceFee().toString());
            }

            if(null !=pledgeStockInfoEntity.getPid()){
                List<BusinessFileEntity> list = fcWarehouseUnpackCore.searchFileByCommodityId(pid.toString());
                if(null != list){
                    response.setBusinessFileEntities(list);
                }
            }
            return  new Message(MessageType.MSG_SUCCESS,"audit_service",response);

        } else {
            return  new Message(MessageType.MSG_ERROR,"audit_service",null);
        }
    }

}
