package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign.FCProContractService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ProContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:58
 */
@Service
public class ProContractServiceImpl implements ProContractService {

    @Autowired
    private FCProContractService fcProContractService;

    @Override
    public Message searchListContract(ProContractForm proContractForm) {
        return fcProContractService.searchListContract(proContractForm);
    }

    @Override
    public Message submitContract(ProContractEntity proContractEntity) {
        return fcProContractService.submitContract(proContractEntity);
    }

    @Override
    public Message searchContract(String pid) {
        return fcProContractService.searchContract(pid);
    }

    @Override
    public Message deleteContract(String pid) {
        return fcProContractService.deleteContract(pid);
    }

    @Override
    public Message updateContract(ProContractEntity proContractEntity) {
        return fcProContractService.updateContract(proContractEntity);
    }

    @Override
    public Message disableContract(String pid) {
        return fcProContractService.disableContract(pid);
    }

    @Override
    public Message searchProductListContract(String pid) {
        return fcProContractService.searchProductListContract(pid);
    }

    @Override
    public ResponseEntity<byte[]> downContract(String pid) {
        return fcProContractService.downContract(pid);
    }

    @Override
    public Message uploadContractDoc(MultipartFile file, String type, String resourceCode) {
        return fcProContractService.uploadContractDoc(file,type,resourceCode);
    }

    @Override
    public Message deleteContractFile(String type, String resourceCode) {
        return fcProContractService.deleteContractFile(type,resourceCode);
    }

    @Override
    public Message searchContractListByCategory(String category) {
        return fcProContractService.searchContractListByCategory(category);
    }

    @Override
    public Message searchContractListByCompanyId(Long companyId) {
        return fcProContractService.searchContractListByCompanyId(companyId);
    }
}
