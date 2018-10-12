package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 10:48 2018/6/21
 */
   public interface EnterpriseFinancialService {
    /**
     * 上传授信文档
     */
    Message uploadCompanyCreditDoc(MultipartFile file, String type, String year,Long pid);

    /**
    * 下载授信文檔
    */
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid,String type,String year);

    /**
     * 刪除授信文檔
    */
    Message deleteCompanyCreditDoc(Long pid);

    /**
     * 提交财务报表信息
     */
    Message  submitAccountingStatementInfo(Long pid,String code);

    /**
     * 提交授信影像信息
     */
    Message submitCompanyCreditImageInfo(Long pid,String code);


    /**
     * 查询财务报表信息
     */
    List<CompanyFileEntity> searchAccountingStatementInfo(Long pid);

   /**
    * 查询注册文件信息
    */
    List<RegisterFileEntity> searchRegisterFile(Long pid);

    ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(Long pid);

    Message deletePledgeEnterpriseFile(Long pid);

    Message uploadPledgeEnterpriseFile(MultipartFile file, String type, Long enterpriseId);
}
