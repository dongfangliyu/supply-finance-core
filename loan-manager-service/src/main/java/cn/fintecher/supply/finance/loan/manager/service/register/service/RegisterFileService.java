package cn.fintecher.supply.finance.loan.manager.service.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 1:25
 */
public interface RegisterFileService {

    Message uploadIdentityFile(MultipartFile file, String type, String registerCode);

    Message searchRegisterFile(String type, String registerCode);

    Message deleteRegisterFile(String type, String registerCode);

    ResponseEntity<byte[]> donwloadEntrustment(String category);

    ResponseEntity<byte[]> donwloadRegisterIDCard(Long pid);

    BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(String category);
}
