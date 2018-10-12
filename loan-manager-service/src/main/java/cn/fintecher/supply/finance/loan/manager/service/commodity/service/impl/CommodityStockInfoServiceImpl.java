package cn.fintecher.supply.finance.loan.manager.service.commodity.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockInfoSubmit;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.commodity.response.CommodityStockListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseDictionaryCore;
import cn.fintecher.supply.finance.loan.manager.service.commodity.feign.CommodityStockInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.CommodityStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Service("commodityStockInfoService")
public class CommodityStockInfoServiceImpl implements CommodityStockInfoService {

	@Autowired
    private CommodityStockInfoCore commodityStockInfoCore;
	
	@Autowired
	private FCBaseDictionaryCore baseDictionaryCore;
	@Autowired
	private IRedisService redisService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfNotHM = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfCh = new SimpleDateFormat("yyyy年MM年dd日 HH:mm:ss");
	private SimpleDateFormat sdfNotHMCh = new SimpleDateFormat("yyyy年MM年dd日");
    
    @Override
	public Message insertStockInfo(CommodityStockInfoEntity commodityStockInfoEntity){
		return this.commodityStockInfoCore.insertStockInfo(commodityStockInfoEntity);
	}
	
	@Override
	public Message<List<CommodityStockInfoEntity>> selectByStockInfo(CommodityStockInfoEntity commodityStockInfoEntity) {
		return this.commodityStockInfoCore.selectByStockInfo(commodityStockInfoEntity);
	}
	
	@Override
	public Message updateStockInfo(CommodityStockInfoEntity commodityStockInfoEntity) {
		return this.commodityStockInfoCore.updateStockInfo(commodityStockInfoEntity);
	}
    
	@Override
	public Message<CommodityStockInfoEntity> queryStockInfoByPid(String pid) {
		return this.commodityStockInfoCore.queryStockInfoByPid(pid);
	}
	
	
	@Override
	public Message<PagedResponse<List<CommodityStockListResponse>>> searchStockList(CommodityStockForm commdityStockForm,
			CompanyUserEntity user) throws ParseException {
		if (StringUtils.isNotBlank(commdityStockForm.getActualStockDateStart())) {
			commdityStockForm.setActualStockDateStart(DateUtil.TransformatStartTime(commdityStockForm.getActualStockDateStart()));
		}
		if (StringUtils.isNotBlank(commdityStockForm.getActualStockDateEnd())) {
			commdityStockForm.setActualStockDateEnd(DateUtil.TransformatEndTime(commdityStockForm.getActualStockDateEnd()));
		}
		if (StringUtils.isNotBlank(commdityStockForm.getApplyStockTimeStart())) {
			commdityStockForm.setApplyStockTimeStart(DateUtil.TransformatStartTime(commdityStockForm.getApplyStockTimeStart()));
		}
		if (StringUtils.isNotBlank(commdityStockForm.getApplyStockTimeEnd())) {
			commdityStockForm.setApplyStockTimeEnd(DateUtil.TransformatEndTime(commdityStockForm.getApplyStockTimeEnd()));
		}
		Message<PagedResponse<List<CommodityStockListResponse>>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
		PagedResponse<List<CommodityStockListResponse>> response = new PagedResponse<>();
		commdityStockForm.setCompanyId(user.getEnterpriseId());
		//查询列表
		Message<List<CommodityStockInfoEntity>> resultList = this.commodityStockInfoCore.queryPageList(commdityStockForm);
		if (resultList.getCode() == MessageType.MSG_ERROR) {
			return new Message<>(MessageType.MSG_ERROR,"commodity",null);
		}
		List<CommodityStockInfoEntity> list = resultList.getMessage();
		
		//查询总数
		Message<Integer> resultCount = this.commodityStockInfoCore.queryPageCount(commdityStockForm);
		if (resultCount.getCode() == MessageType.MSG_ERROR) {
			return new Message<>(MessageType.MSG_ERROR,"commodity",null);
		}
		Integer count = resultCount.getMessage();
		
		response.setData(TransStockInfoEntity(list));
		response.setTotal(count);
		response.setPageNo(commdityStockForm.getPageNo());
		response.setPageSize(commdityStockForm.getPageSize());
		msg.setMessage(response);
		return msg;
	}

	@Override
	public Message<Object> submitStock(CommodityStockInfoSubmit commodityStockInfoSubmit, CompanyUserEntity user) throws ParseException {
		// TODO Auto-generated method stub
		CommodityStockInfoEntity commodityStockInfoEntity = TransStockInfoEntity(commodityStockInfoSubmit);
		commodityStockInfoEntity.setCompanyId(user.getEnterpriseId());
		commodityStockInfoEntity.setCreateAt(new Date());
		commodityStockInfoEntity.setUpdateAt(new Date());
		commodityStockInfoEntity.setUpdateBy(user.getUserName());
		commodityStockInfoEntity.setCreateBy(user.getUserName());
		commodityStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
		commodityStockInfoEntity.setState(Constants.COMMODITY_STOCK_NOT);
		commodityStockInfoEntity.setApplyNumber(getNewWarehousingNo());
		return this.insertStockInfo(commodityStockInfoEntity);
	}

	@Override
	public Message<CommodityStockInfoResponse> searchStockDetail(Long pid, CompanyUserEntity user) {
		// TODO Auto-generated method stub
		Message<CommodityStockInfoEntity> msg = this.queryStockInfoByPid(pid.toString());
		if (msg.getCode() == MessageType.MSG_ERROR || (long)msg.getMessage().getCompanyId() != (long)user.getEnterpriseId()) {
			return new Message<>(MessageType.MSG_ERROR,"commodity",null);
		}
		return new Message<CommodityStockInfoResponse>(MessageType.MSG_SUCCESS, "commodity", TransStockInfoEntity(msg.getMessage()));
	};
	/**
	 * 客户端入库管理列表List<CommodityStockInfoEntity>转换List<CommodityStockListResponse>
	 * @param list
	 * @return
	 */
	private List<CommodityStockListResponse> TransStockInfoEntity(List<CommodityStockInfoEntity> list){
		List<CommodityStockListResponse> resultList = new ArrayList<>();
		for (CommodityStockInfoEntity infoEntity : list) {
			CommodityStockListResponse listEntity = new CommodityStockListResponse();
			listEntity.setActualStockDate(infoEntity.getActualStockDate() == null? null:this.sdfNotHM.format(infoEntity.getActualStockDate()));
			listEntity.setApplyNumber(infoEntity.getApplyNumber());
			listEntity.setApplyStockTime(this.sdf.format(infoEntity.getApplyStockTime()));
			listEntity.setPid(infoEntity.getPid());
			listEntity.setProductAddress(infoEntity.getProductAddressProvince()+infoEntity.getProductAddressCity());
			listEntity.setProductBrand(infoEntity.getProductBrand());
			listEntity.setProductModel(infoEntity.getProductModel());
			listEntity.setProductNumber(infoEntity.getProductNumber());
			listEntity.setProductType(infoEntity.getProductType());
			String dic = baseDictionaryCore.getEntityByPid(infoEntity.getProductUnit()).getValue();
			listEntity.setProductUnit(dic);
			listEntity.setState(infoEntity.getState());
			listEntity.setSupplierName(infoEntity.getSupplierName());
			listEntity.setTotalPrice(infoEntity.getTotalPrice());
			listEntity.setUnitPrice(infoEntity.getUnitPrice());
			listEntity.setUnitPriceUnit("元/"+dic);
			resultList.add(listEntity);
		}
		return resultList;
	}


	/**
	 * 客户端入库管理提交申请stockInfoSubmit转换stockInfoSubmit
	 * @param stockInfoSubmit
	 * @return
	 * @throws ParseException 
	 */
	private  CommodityStockInfoEntity TransStockInfoEntity(CommodityStockInfoSubmit stockInfoSubmit) throws ParseException{
		CommodityStockInfoEntity infoEntity = new CommodityStockInfoEntity();
		infoEntity.setSupplierName(stockInfoSubmit.getSupplierName());
		infoEntity.setProductBrand(stockInfoSubmit.getProductBrand());
		infoEntity.setProductType(stockInfoSubmit.getProductType());
		infoEntity.setProductNumber(stockInfoSubmit.getProductNumber());
		infoEntity.setProductUnit(stockInfoSubmit.getProductUnit());
		infoEntity.setUnitPrice(stockInfoSubmit.getUnitPrice());
		infoEntity.setTotalPrice(stockInfoSubmit.getProductNumber().multiply(stockInfoSubmit.getUnitPrice()));
		infoEntity.setProductAddressProvince(stockInfoSubmit.getProductAddressProvince());
		infoEntity.setProductAddressCity(stockInfoSubmit.getProductAddressCity());
		infoEntity.setProductModel(stockInfoSubmit.getProductModel());
		infoEntity.setProductGrade(stockInfoSubmit.getProductGrade());
		infoEntity.setProductStandard(stockInfoSubmit.getProductStandard());
		infoEntity.setProductSize(stockInfoSubmit.getProductSize());
		infoEntity.setEffectiveStartTime(this.sdfNotHM.parse(stockInfoSubmit.getEffectiveStartTime()));
		infoEntity.setEffectiveEndTime(this.sdfNotHM.parse(stockInfoSubmit.getEffectiveEndTime()));
		infoEntity.setProductionDate(this.sdfNotHM.parse(stockInfoSubmit.getProductionDate()));
		infoEntity.setProductColour(stockInfoSubmit.getProductColour());
		infoEntity.setApplyStockTime(new Date());
		return infoEntity;
	}
	
	/**
	 * 客户端入库管理查看详情CommodityStockInfoEntity转换CommodityStockInfoResponse
	 * @param infoEntity
	 * @return
	 */
	private CommodityStockInfoResponse TransStockInfoEntity(CommodityStockInfoEntity infoEntity){
		CommodityStockInfoResponse resp = new CommodityStockInfoResponse();
		resp.setPid(infoEntity.getPid());
		resp.setApplyNumber(infoEntity.getApplyNumber());
		resp.setSupplierName(infoEntity.getSupplierName());
		resp.setProductBrand(infoEntity.getProductBrand());
		resp.setProductType(infoEntity.getProductType());
		resp.setProductNumber(infoEntity.getProductNumber());
		String dic = baseDictionaryCore.getEntityByPid(infoEntity.getProductUnit()).getValue();
		resp.setProductUnit(dic);
		resp.setUnitPriceUnit("元/"+dic);
		resp.setUnitPrice(infoEntity.getUnitPrice());
		resp.setTotalPrice(infoEntity.getTotalPrice());
		resp.setProductAddressProvince(infoEntity.getProductAddressProvince());
		resp.setProductAddressCity(infoEntity.getProductAddressCity());
		resp.setProductModel(infoEntity.getProductModel());
		resp.setProductGrade(infoEntity.getProductGrade());
		resp.setProductStandard(infoEntity.getProductStandard());
		resp.setProductSize(infoEntity.getProductSize());
		resp.setEffectiveStartTime(this.sdfNotHM.format(infoEntity.getEffectiveStartTime()));
		resp.setEffectiveEndTime(this.sdfNotHM.format(infoEntity.getEffectiveEndTime()));
		resp.setProductionDate(this.sdfNotHM.format(infoEntity.getProductionDate()));
		resp.setApplyStockTime(this.sdfCh.format(infoEntity.getApplyStockTime()));
		resp.setState(infoEntity.getState());
		resp.setActualStockDate(infoEntity.getActualStockDate() == null? null:this.sdfNotHMCh.format(infoEntity.getActualStockDate()));
		resp.setProductColour(infoEntity.getProductColour());
		
		return resp;
	}

	/**
	 * 获取入库编号
	 * 生成规则 SQRK yyyyMMdd 000001
	 * 000001 按照当日数量从1开始,依次递增
	 * @return
	 */
	private synchronized String getNewWarehousingNo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String warehousingNoDate = sdf.format(new Date());
    	String warehousingNumber=redisService.get(warehousingNoDate+"_warehousingNo");
    	String warehousingNo = "SQRK"+warehousingNoDate;
    	Integer number = 0;
    	if (!ChkUtil.isEmpty(warehousingNumber)) {
			 number = Integer.parseInt(warehousingNumber)+1;
		}else {
			String newWarehousingNo = commodityStockInfoCore.queryNewWarehousingNo();
			if (newWarehousingNo==null) {
				number = 1;
			}else {
				number = Integer.parseInt(newWarehousingNo.substring(newWarehousingNo.length()-1, newWarehousingNo.length()))+1;
			}
			
		}
    	redisService.set(warehousingNoDate+"_warehousingNo",number);
    	String numberStr = number.toString();
    	switch (numberStr.length()) {
			case 1:
				numberStr = "00000"+numberStr;
				break;
			case 2:
				numberStr = "0000"+numberStr;
				break;
			case 3:
				numberStr = "000"+numberStr;
				break;
			case 4:
				numberStr = "00"+numberStr;
				break;
			case 5:
				numberStr = "0"+numberStr;
				break;
			default:
				break;
		}
    	
    	return warehousingNo+numberStr;
	}


	@Override
	public Message selectStockList(CommodityStockForm commodityStockForm) {
		if(StringUtils.isNotBlank(commodityStockForm.getApplyStockTimeStart())){
			commodityStockForm.setApplyStockTimeStart(commodityStockForm.getApplyStockTimeStart() + " 00:00:00");
		}
		if(StringUtils.isNotBlank(commodityStockForm.getApplyStockTimeEnd())){
			commodityStockForm.setApplyStockTimeEnd(commodityStockForm.getApplyStockTimeEnd() + " 23:59:59");
		}
		PageResponse  response = new PageResponse();
		List<CommodityStockInfoEntity> list = commodityStockInfoCore.selectStockList(commodityStockForm);
		int total = commodityStockInfoCore.selectStockListCount(commodityStockForm);
		response.setData(list);
		response.setTotal(total);
		response.setPageSize(commodityStockForm.getPageSize());
		response.setPageNo(commodityStockForm.getPageNo());
		return new Message(MessageType.MSG_SUCCESS,"commodityStockInfo",response);
	}

	@Override
	public Message submitState(Long id, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommodityStockInfoEntity info = commodityStockInfoCore.queryStockInfoByPid(id.toString()).getMessage();
		if(info == null){
			return new Message(MessageType.MSG_ERROR,"commodityStockInfo","入库数据不存在");
		}
		if("0".equals(info.getState())){
			try{
				info.setState("1");
				info.setActualStockDate(sdf.parse(time+" 00:00:00"));
				commodityStockInfoCore.updateStockInfo(info);
			}catch (Exception e){
				return new Message(MessageType.MSG_ERROR,"commodityStockInfo","操作异常");
			}
		}
		return new Message(MessageType.MSG_SUCCESS,"commodityStockInfo","操作成功");
	}


	@Override
	public Message selectCommodityStockInfo(Long pid) {
		return commodityStockInfoCore.selectCommodityStockInfo(pid);
	}
}


