package cn.fintecher.supply.finance.loan.manager.core.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.pro.dao.ProCategoryDao;
import cn.fintecher.supply.finance.loan.manager.core.pro.dao.ProContractDao;
import cn.fintecher.supply.finance.loan.manager.core.pro.dao.ProFileDao;
import cn.fintecher.supply.finance.loan.manager.core.pro.service.ProContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:25
 */
@Service
public class ProContractServiceImpl implements ProContractService{

    @Autowired
    private ProCategoryDao proCategoryDao;

    @Autowired
    private ProFileDao proFileDao;

    @Autowired
    private ProContractDao proContractDao;

    @Override
    public int searchListContractCount(ProContractForm proContractForm) {
        return proContractDao.searchListContractCount(proContractForm);
    }

    @Override
    public List<ProContractEntity> searchListContract(ProContractForm proContractForm) {
        return proContractDao.searchListContract(proContractForm);
    }

    @Override
    public void submitContract(ProContractEntity proContractEntity) {
        proContractDao.submitContract(proContractEntity);
    }

    @Override
    public ProContractEntity searchContract(String pid) {
        return proContractDao.searchContract(pid);
    }

    @Override
    public List<ProFileEntity> searchProFile(String resourceCode) {
        return proFileDao.searchProFile(resourceCode);
    }

    @Override
    public void updateProContractEntity(ProContractEntity proContractEntity) {
        proContractDao.updateProContractEntity(proContractEntity);
    }

    @Override
    public List<ProCategoryEntity> searchCategoryListByParentId(String parentId) {
        return proCategoryDao.searchCategoryListByParentId(parentId);
    }

    @Override
    public ProFileEntity searchProFileById(String pid) {
        return proFileDao.searchProFileById(pid);
    }

    @Override
    public Message saveProFile(ProFileEntity proFileEntity) {
        Message message = new Message();
        proFileDao.saveProFile(proFileEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(proFileEntity);
        return message;
    }

    @Override
    public long searchCountOfContract() {
        return proContractDao.searchCountOfContract();
    }

    @Override
    public ProCategoryEntity searchCategoryByPid(String pid) {
        return proCategoryDao.searchCategoryByPid(pid);
    }

    @Override
    public ProFileEntity searchContractFile(String type, String resourceCode) {
        return proFileDao.searchContractFile(type,resourceCode);
    }

    @Override
    public void updateContractFile(ProFileEntity proFileEntity) {
        proFileDao.updateContractFile(proFileEntity);
    }

    @Override
    public ProCategoryEntity searchCategoryByCategory(String category) {
        return proCategoryDao.searchCategoryByCategory(category);
    }

    @Override
    public List<ProContractEntity> searchContractListByCompanyId(Long companyId) {
        return proContractDao.searchContractListByCompanyId(companyId);
    }
}
