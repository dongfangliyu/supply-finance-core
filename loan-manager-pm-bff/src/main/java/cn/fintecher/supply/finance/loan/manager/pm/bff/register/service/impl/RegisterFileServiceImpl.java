package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign.FCRegisterFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.RegisterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/6/27 0027上午 11:45
 */
@Service
public class RegisterFileServiceImpl implements RegisterFileService {

    @Autowired
    private FCRegisterFileService fcRegisterFileService;

    @Override
    public Message uploadIdentityFile(MultipartFile file, String type,String registerCode) {
        return fcRegisterFileService.uploadIdentityFile(file,type,registerCode);
    }

    @Override
    public Message searchRegisterFile(String type, String registerCode) {
        return fcRegisterFileService.searchRegisterFile(type,registerCode);
    }

    @Override
    public Message deleteRegisterFile(String type, String registerCode) {
        return fcRegisterFileService.deleteRegisterFile(type,registerCode);
    }

    @Override
    public ResponseEntity<byte[]> donwloadEntrustment(String category) {
        return fcRegisterFileService.donwloadEntrustment(category);
    }

    @Override
    public ResponseEntity<byte[]> donwloadRegisterIDCard(Long pid) {
        return fcRegisterFileService.donwloadRegisterIDCard(pid);
    }

    @Override
    public BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(String category) {
        return fcRegisterFileService.searchBaseTemplateDictionaryByCode(category);
    }
}
