package cn.fintecher.supply.finance.loan.manager.core.pro.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:26
 */
@Mapper
public interface ProFileDao {

    ProFileEntity searchProFileById(String pid);

    List<ProFileEntity> searchProFile(String resourceCode);

    void saveProFile(ProFileEntity proFileEntity);

    void updateContractFile(ProFileEntity proFileEntity);

    ProFileEntity searchContractFile(@Param("category") String type, @Param("ownerId") String resourceCode);
}
