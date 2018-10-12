package cn.fintecher.supply.finance.loan.manager.core.register.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 3:13
 */
@Mapper
public interface RegisterFileDao {

    void saveRegisterFile(RegisterFileEntity registerFileEntity);

    RegisterFileEntity searchRegisterFile(@Param("category") String type, @Param("ownerId") String ownerId);

    List<RegisterFileEntity> searchRegisterFileByOwnerId(String ownerId);

    void updateRegisterFile(RegisterFileEntity fileEntity);

    RegisterFileEntity searchRegisterFileByPid(Long pid);
}
