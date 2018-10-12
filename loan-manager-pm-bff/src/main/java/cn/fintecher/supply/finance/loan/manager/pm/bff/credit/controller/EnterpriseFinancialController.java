package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.EnterpriseCreditService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.EnterpriseFinancialService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 10:48 2018/6/21
 */
@RestController
@RequestMapping("/enterprise/financial")
@Api(tags ="企业文件相关接口")
public class EnterpriseFinancialController extends BaseController {
    @Autowired
    private EnterpriseFinancialService enterpriseFinancialService;

    @Autowired
    private EnterpriseCreditService enterpriseCreditService;

    /**
     * 上传授信文档
     */
    @ApiOperation(value = "上传授信文档", notes = "上传授信文档")
    @ResponseBody
    @RequestMapping(value = "/uploadCompanyCreditDoc", method = RequestMethod.POST)
    public Message uploadCompanyCreditDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="year") String year,Principal principal) {
        Long pid = getEnterpriseId(principal);
        return enterpriseFinancialService.uploadCompanyCreditDoc(file, type, year,pid);
    }

    /**
     * 下载授信文档
     */
    @ApiOperation(value = "下载授信文档", notes = "下载授信文档",produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping(value = "/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") String pid,@RequestParam(value="type") String type,@RequestParam(value="year",required = false) String year) {
        return  enterpriseFinancialService.donwloadCompanyCreditDoc(Long.valueOf(pid),type,year);
    }


    @ApiOperation(value = "通过文件id下载授信文档", notes = "通过文件id下载授信文档",produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping(value = "/donwloadCompanyCreditDocByFid", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(@RequestParam(value = "pid") Long pid) {
        return  enterpriseFinancialService.donwloadCompanyCreditDocByFid(pid);
    }



    /**
     * 删除授信文档
     */
    @ApiOperation(value = "删除授信文档", notes = "删除授信文档")
    @ResponseBody
    @RequestMapping(value = "/deleteCompanyCreditDoc", method = RequestMethod.GET)
    public Message deleteCompanyCreditDoc(@RequestParam(value="pid") Long pid) {
        return enterpriseFinancialService.deleteCompanyCreditDoc(pid);
    }

    /**
     * 查询财务报表信息
     */
    @ApiOperation(value = "查询财务报表信息", notes = "查询财务报表信息")
    @ResponseBody
    @RequestMapping(value = "/searchAccountingStatementInfo", method = RequestMethod.GET)
    public Message searchAccountingStatementInfo(Principal principal) {
        Message msg = new Message();
        Long pid = getEnterpriseId(principal);
        List<CompanyFileEntity> list = enterpriseFinancialService.searchAccountingStatementInfo(pid);
        List<CompanyFileEntity> companyFileEntities = new ArrayList<>();
        List yearTime= (List)enterpriseCreditService.searchAccountingStatementTime().getMessage();
        String year1 =  yearTime.get(0)+"";
        String year2 = yearTime.get(1)+"";
        String year3 = yearTime.get(2)+"";
        for (CompanyFileEntity entity : list) {
            if (entity.getCategory().equals("assetsTable") || entity.getCategory().equals("profitsTable")  || entity.getCategory().equals("cashTable")){
                if (entity.getYear().equals(year1) ||entity.getYear().equals(year2) || entity.getYear().equals(year3)){
                    companyFileEntities.add(entity);
                }
            }
        }
        msg.setMessage(companyFileEntities);
        msg.setCode(0);
        return msg;
    }

    /**
     * 查询影像资料信息
     * @return
     */
    @ApiOperation(value = "查询影像资料信息", notes = "查询影像资料信息")
    @ResponseBody
    @RequestMapping(value = "/searchImageDataInfo", method = RequestMethod.GET)
    public Message searchImageDataInfo(Principal principal){
        Message msg = new Message();
        Long pid = getEnterpriseId(principal);
        List<CompanyFileEntity> list = enterpriseFinancialService.searchAccountingStatementInfo(pid);
        List yearTime= (List)enterpriseCreditService.searchAccountingStatementTime().getMessage();
        String year1 =  yearTime.get(0)+"";
        String year2 = yearTime.get(1)+"";
        String year3 = yearTime.get(2)+"";
        List fileEntities = new ArrayList<>();
        Map result = new HashMap();
        boolean flag = false;
        for(CompanyFileEntity entity : list){
            if(!(entity.getCategory().equals("assetsTable") || entity.getCategory().equals("profitsTable")  || entity.getCategory().equals("cashTable"))) {
                    if(Strings.isNullOrEmpty(entity.getYear())){
                        fileEntities.add(entity);
                    } else if (entity.getYear().equals(year1) || entity.getYear().equals(year2) || entity.getYear().equals(year3)) {
                        fileEntities.add(entity);
                    }

                    if(entity.getCategory().equals("agentFacade")){
                        flag = true;
            }
            }
        }
        result.put("fileEntities",fileEntities);
        result.put("flag",flag);
        msg.setMessage(result);
        msg.setCode(0);
        return msg;
    }


    /**
     * 提交财务报表信息
     */
    @ApiOperation(value = "提交财务报表信息", notes = "提交财务报表信息")
    @ResponseBody
    @RequestMapping(value = "/submitAccountingStatementInfo", method = RequestMethod.GET)
    public Message submitAccountingStatementInfo(Principal principal,String code) {
        Long pid = principal==null?37l:getEnterpriseId(principal);
        return enterpriseFinancialService.submitAccountingStatementInfo(pid,code);
    }

    /**
     * 提交授信影像信息
     * @return
     */
    @ApiOperation(value = "提交授信影像信息", notes = "提交授信影像信息")
    @ResponseBody
    @RequestMapping(value = "/submitCompanyCreditImageInfo", method = RequestMethod.GET)
    public Message submitCompanyCreditImageInfo(@RequestParam(value = "code") String code,Principal principal) {
        Long pid = getEnterpriseId(principal);
        return enterpriseFinancialService.submitCompanyCreditImageInfo(pid,code);
    }

    /***
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为仓单质押企业文件接口
     */

    /**
     * 删除质押注册图片
     */
    @ApiOperation(value = "删除质押注册图片", notes = "删除质押注册图片")
    @ResponseBody
    @RequestMapping(value = "/deletePledgeEnterpriseFile", method = RequestMethod.GET)
    public Message deletePledgeEnterpriseFile(@RequestParam(value="pid") Long pid) {
        return enterpriseFinancialService.deletePledgeEnterpriseFile(pid);
    }

    /**
     * 上传质押注册图片
     */
    @ApiOperation(value = "上传质押注册图片", notes = "上传质押注册图片")
    @ResponseBody
    @RequestMapping(value = "/uploadPledgeEnterpriseFile", method = RequestMethod.POST)
    public Message uploadPledgeEnterpriseFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,Principal principal) {
        Long enterpriseId = getEnterpriseId(principal);
        return enterpriseFinancialService.uploadPledgeEnterpriseFile(file,type,enterpriseId);
    }



}
