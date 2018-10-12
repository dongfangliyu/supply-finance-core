package cn.fintecher.supply.finance.loan.manager.service.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.EnterpriseFinancialService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:48 2018/6/21
 */
@RestController
@RequestMapping("/enterprise/financial")
public class EnterpriseFinancialController {
    @Autowired
    private EnterpriseFinancialService  enterpriseFinancialService;
    /**
     * 上传授信文档
     */
    @RequestMapping(value = "/uploadCompanyCreditDoc", method = RequestMethod.POST)
    public Message uploadCompanyCreditDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value="year") String year,Long pid) {
        return enterpriseFinancialService.uploadCompanyCreditDoc(file, type, year,pid);
    }

    /**
     * 下载授信文档
     */
    @RequestMapping(value = "/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value="pid") Long pid,@RequestParam(value = "type") String type,@RequestParam(value="year",required = false) String year){
        return enterpriseFinancialService.donwloadCompanyCreditDoc(pid,type,year);
    }


    @RequestMapping(value = "/donwloadCompanyCreditDocByFid", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(@RequestParam(value ="pid") Long pid){
        return enterpriseFinancialService.donwloadCompanyCreditDocByFid(pid);
    }


    /**x
     * 查询财务报表信息
     */
    @ResponseBody
    @RequestMapping(value ="/searchAccountingStatementInfo", method = RequestMethod.GET)
    public List<CompanyFileEntity> searchAccountingStatementInfo(@RequestParam(value="pid") Long pid){
        return enterpriseFinancialService.searchAccountingStatementInfo(pid);
    }

    /**
     * 提交财务报表信息
     */
    @ResponseBody
    @RequestMapping(value = "/submitAccountingStatementInfo", method = RequestMethod.POST)
    public Message submitAccountingStatementInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value = "code") String code) {
        return enterpriseFinancialService.submitAccountingStatementInfo(pid,code);
    }

    /**
     * 提交授信影象信息
     */
    @ResponseBody
    @RequestMapping(value = "/submitCompanyCreditImageInfo", method = RequestMethod.POST)
    public Message  submitCompanyCreditImageInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value = "code") String code) {
        return enterpriseFinancialService.submitCompanyCreditImageInfo(pid,code);
    }

    /**
     * 删除文件根据文件id
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCompanyCreditDoc", method = RequestMethod.GET)
    public Message deleteCompanyCreditDoc(@RequestParam(value = "pid") Long pid){
        return enterpriseFinancialService.deleteCompanyCreditDoc(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/searchRegisterFile", method = RequestMethod.GET)
    public List<RegisterFileEntity> searchRegisterFile(@RequestParam(value ="pid") Long pid){
        return enterpriseFinancialService.searchRegisterFile(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/deletePledgeEnterpriseFile", method = RequestMethod.GET)
    public Message deletePledgeEnterpriseFile(@RequestParam(value = "pid") Long pid){
        return enterpriseFinancialService.deletePledgeEnterpriseFile(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/uploadPledgeEnterpriseFile", method = RequestMethod.POST)
    public Message uploadPledgeEnterpriseFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "enterpriseId") Long enterpriseId){
        return enterpriseFinancialService.uploadPledgeEnterpriseFile(file,type,enterpriseId);
    }

}
