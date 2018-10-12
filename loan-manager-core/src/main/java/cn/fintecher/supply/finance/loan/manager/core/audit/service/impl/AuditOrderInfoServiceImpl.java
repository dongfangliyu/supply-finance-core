package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditOrderInfoDao;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditOrderInfoService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@Service("auditOrderInfoService")
public class AuditOrderInfoServiceImpl implements AuditOrderInfoService {

   	@Autowired
	private AuditOrderInfoDao auditorderInfoDao;
	
	@Override
	public Integer insertOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity){
		 auditorderInfoDao.insertOrderInfo(auditOrderInfoEntity);
		 return auditOrderInfoEntity.getPid().intValue();
	}
	
	@Override
	public List<AuditOrderInfoEntity> selectByOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity) {
		return auditorderInfoDao.selectByOrderInfo(auditOrderInfoEntity);
	}
	
	@Override
	public Integer updateOrderInfo(AuditOrderInfoEntity auditOrderInfoEntity) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.updateOrderInfo(auditOrderInfoEntity);
	}
	
	@Override
	public AuditOrderInfoEntity queryOrderInfoByPid(String pid) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.queryOrderInfoByPid(pid);
	}

	@Override
	public Integer selectFristCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectFristCount(auditOrderInfoFrom);
	}

	@Override
	public List<AuditOrderInfoEntity> selectFristList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectFristList(auditOrderInfoFrom);
	}

	@Override
	public Integer selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectSecondCount(auditOrderInfoFrom);
	}

	@Override
	public List<AuditOrderInfoEntity> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectSecondList(auditOrderInfoFrom);
	}

	@Override
	public Integer selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectThirdCount(auditOrderInfoFrom);
	}

	@Override
	public List<AuditOrderInfoEntity> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom) {
		// TODO Auto-generated method stub
		return auditorderInfoDao.selectThirdList(auditOrderInfoFrom);
	}

	@Override
	public List<AuditOrderInfoEntity> searchSigningList(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchSigningList(auditSigningRequest);
	}

	@Override
	public Integer searchSigningListCount(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchSigningListCount(auditSigningRequest);
	}

	@Override
	public List<AuditOrderInfoEntity> getOverdueOrderInfo() {
		// TODO Auto-generated method stub
		return auditorderInfoDao.getOverdueOrderInfo();
	}

	@Override
	public List<AuditOrderInfoEntity> searchEnterSigningList(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchEnterSigningList(auditSigningRequest);
	}

	@Override
	public Integer searchEnterSigningListCount(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchEnterSigningListCount(auditSigningRequest);
	}

	@Override
	public List<AuditOrderInfoEntity> searchPlatformSigningList(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchPlatformSigningList(auditSigningRequest);
	}

	@Override
	public Integer searchPlatformSigningListCount(AuditSigningRequest auditSigningRequest) {
		return auditorderInfoDao.searchPlatformSigningListCount(auditSigningRequest);
	}

    @Override
    public int searchRemindListCount(AuditRemindForm auditRemindForm) {
        return auditorderInfoDao.searchRemindListCount(auditRemindForm);
    }

    @Override
    public List<AuditOrderInfoEntity> searchRemindList(AuditRemindForm auditRemindForm) {
        return auditorderInfoDao.searchRemindList(auditRemindForm);
    }

	@Override
	public int searchReviewListCount(AuditRemindForm auditRemindForm) {
		return auditorderInfoDao.searchReviewListCount(auditRemindForm);
	}

	@Override
	public List<AuditOrderInfoEntity> searchReviewList(AuditRemindForm auditRemindForm) {
		return auditorderInfoDao.searchReviewList(auditRemindForm);
	}

	@Override
	public List<AuditOrderInfoEntity> searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
		return auditorderInfoDao.searchSuppReviewList(auditSuppReviewRequest);
	}

	@Override
	public Integer searchSuppReviewListCount(AuditSuppReviewRequest auditSuppReviewRequest) {
		return auditorderInfoDao.searchSuppReviewListCount(auditSuppReviewRequest);
	}

	@Override
	public int searchEntryListCount(AuditRemindForm auditRemindForm) {
		return auditorderInfoDao.searchEntryListCount(auditRemindForm);
	}

	@Override
	public List<AuditOrderInfoEntity> searchEntryList(AuditRemindForm auditRemindForm) {
		return auditorderInfoDao.searchEntryList(auditRemindForm);
	}
}
