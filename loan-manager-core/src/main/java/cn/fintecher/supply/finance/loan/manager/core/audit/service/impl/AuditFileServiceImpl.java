package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditFileDao;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 12:20 2018/7/11
 */
@Service
public class AuditFileServiceImpl implements AuditFileService {

    @Autowired
    private  AuditFileDao auditFileDao;

    @Override
    public Message uploadAuditFile(AuditFileEntity auditFileEntity) {
        Message message = new Message();
        Map result = new HashMap();

        try {
            auditFileDao.uploadAuditFile(auditFileEntity);
            result.put("fileName",auditFileEntity.getFileName());
            result.put("pid",auditFileEntity.getPid());
            message.setMessage(result);
            message.setCode(MessageType.MSG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            message.setCode(MessageType.MSG_ERROR);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public AuditFileEntity searchAllFileByFid(Long pid) {
        return auditFileDao.searchAllFileByFid(pid);
    }


    @Override
    public List<AuditFileEntity> searchAllFileByOwnerId(Long pid) {

        return auditFileDao.searchAllFileByOwnerId(pid.toString());
    }

    @Override
    public List<AuditFileEntity> searchAllAuditFileByOwnerIdAndCategory(String ownerId, String category) {
        return auditFileDao.searchAllAuditFileByOwnerIdAndCategory(ownerId,category);
    }

    @Override
    public Message update(AuditFileEntity auditFileEntity) {
        try {
            auditFileDao.update(auditFileEntity);
            return  new Message(MessageType.MSG_SUCCESS,"audit_core",null);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
        }
    }

    @Override
    public Boolean deleteAuditFileByOwnerId(String ownerId, String type) {
        return auditFileDao.deleteAuditFileByOwnerId(ownerId,type);
    }
}
