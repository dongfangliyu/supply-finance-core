package cn.fintecher.supply.finance.loan.manager.core.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 3:09
 */
public interface RegisterFileService {
    Message saveRegisterFile(RegisterFileEntity registerFileEntity);

    RegisterFileEntity searchRegisterFile(String type, String ownerId);

    List<RegisterFileEntity> searchRegisterFileByOwnerId(String ownerId);

    void updateRegisterFile(RegisterFileEntity fileEntity);

    RegisterFileEntity searchRegisterFileByPid(Long pid);

    BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(String category);
}
