package cn.fintecher.supply.finance.loan.manager.service.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.EnterpriseFileForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:48 2018/6/21
 */
   public interface EnterpriseFinancialService {
    /**
     * 上传授信文档
     */
    Message uploadCompanyCreditDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "year") String year,@RequestParam(value = "pid") Long pid);

    /**
     * 下载授信文档
     */
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") Long pid,@RequestParam(value="type") String type,@RequestParam(value="year",required = false) String year);


    ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(@RequestParam(value = "pid") Long pid);


    /**
     * 提交财务报表信息
     */
    Message  submitAccountingStatementInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value = "code") String code);

    /**
     * 提交授信影像信息
     */
    Message  submitCompanyCreditImageInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value = "code") String code);

    /**
     * 查询财务报表信息
     */
    List<CompanyFileEntity> searchAccountingStatementInfo(@RequestParam(value = "pid") Long pid);

    /**
     *根据文件id删除所有文件
     */
    Message deleteCompanyCreditDoc(@RequestParam(value = "pid") Long pid);

    /**
     * 查询注册文件
     */
    List<RegisterFileEntity> searchRegisterFile(@RequestParam(value ="pid") Long pid);

    Message deletePledgeEnterpriseFile(Long pid);

    Message uploadPledgeEnterpriseFile(MultipartFile file, String type, Long enterpriseId);
}
