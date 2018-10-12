package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:57
 */
public interface ProContractService {
    Message searchListContract(ProContractForm proContractForm);

    Message submitContract(ProContractEntity proContractEntity);

    Message searchContract(String pid);

    Message deleteContract(String pid);

    Message updateContract(ProContractEntity proContractEntity);

    Message disableContract(String pid);

    Message searchProductListContract(String pid);

    ResponseEntity<byte[]> downContract(String pid);

    Message uploadContractDoc(MultipartFile file, String type, String resourceCode);


    Message deleteContractFile(String type, String resourceCode);

    Message searchContractListByCategory(String category);

    Message searchContractListByCompanyId(Long companyId);
}
