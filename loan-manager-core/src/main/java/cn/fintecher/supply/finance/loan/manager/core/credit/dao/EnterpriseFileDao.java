package cn.fintecher.supply.finance.loan.manager.core.credit.dao;

import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.form.EnterpriseFileForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:21 2018/6/21
 */

@Mapper
public interface EnterpriseFileDao {
    /**
     * 添加
     * @param companyFileEntity  数据实例
     * @return
     */
    boolean insert(CompanyFileEntity companyFileEntity);

    /**
     * 查询财务报表信息
      */
    List<CompanyFileEntity> searchAccountingStatementInfo(Long pid);

    /**
     * 查询所有文件
     * @param pid
     * @return
     */
    List<CompanyFileEntity> searchAllFile(Long pid);

    /**
     * 通过文件id查询所有信息
     * @param pid
     * @return
     */
    CompanyFileEntity searchAllFileByFid(Long pid);

    /**
     * 删除财务报表信息
     * @param entity
     * @return
     */
    Boolean deleteCompanyCreditDoc(CompanyFileEntity entity);


    CompanyFileEntity searchAllFileInfo(@Param(value="ownerId")Long pid,@Param(value="category")String type,@Param(value="year") String year);

    void updateCompanyFile(CompanyFileEntity companyFileEntity);
}
