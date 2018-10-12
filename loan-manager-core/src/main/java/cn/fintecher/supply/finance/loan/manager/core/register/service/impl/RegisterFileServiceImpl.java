package cn.fintecher.supply.finance.loan.manager.core.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.dao.BaseTemplateDictionaryDao;
import cn.fintecher.supply.finance.loan.manager.core.register.dao.RegisterFileDao;
import cn.fintecher.supply.finance.loan.manager.core.register.service.RegisterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 3:10
 */
@Service
public class RegisterFileServiceImpl implements RegisterFileService {

    @Autowired
    private RegisterFileDao registerFileDao;

    @Autowired
    private BaseTemplateDictionaryDao baseTemplateDictionaryDao;

    @Override
    public Message saveRegisterFile(RegisterFileEntity registerFileEntity) {
        Message message = new Message();
        try {
            registerFileDao.saveRegisterFile(registerFileEntity);
            message.setMessage(registerFileEntity.getPid());
        } catch (Exception e) {
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public RegisterFileEntity searchRegisterFile(String type, String ownerId) {
        return registerFileDao.searchRegisterFile(type,ownerId);
    }

    @Override
    public List<RegisterFileEntity> searchRegisterFileByOwnerId(String ownerId) {
        return registerFileDao.searchRegisterFileByOwnerId(ownerId);
    }

    @Override
    public void updateRegisterFile(RegisterFileEntity fileEntity) {
        registerFileDao.updateRegisterFile(fileEntity);
    }

    @Override
    public RegisterFileEntity searchRegisterFileByPid(Long pid) {
        return registerFileDao.searchRegisterFileByPid(pid);
    }

    @Override
    public BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(String category) {
        return baseTemplateDictionaryDao.downloadDocTemplate(category);
    }
}
