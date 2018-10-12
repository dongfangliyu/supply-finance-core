package cn.fintecher.supply.finance.loan.manager.pm.bff.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.RegisterFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.DownLoadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author gonghebin
 * @date 2018/6/27 0027上午 11:39
 */
@RestController
@RequestMapping("/registerUser/file")
@Api(tags = "注册文件接口")
public class RegisterFileController {

    @Autowired
    private RegisterFileService registerFileService;

    /**
     * 上传文件
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ResponseBody
    @RequestMapping(value = "/uploadCompanyCreditDoc", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadCompanyCreditDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.uploadIdentityFile(file,type,registerCode);
    }

    /**
     * 查看文件
     */
    @ApiOperation(value = "查看文件", notes = "查看文件")
    @ResponseBody
    @RequestMapping(value = "/searchRegisterFile", method = RequestMethod.GET)
    public Message searchRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.searchRegisterFile(type,registerCode);
    }


    /**
     * 下载委托书模板
     */
    @ApiOperation(value = "下载委托书模板", notes = "下载委托书模板")
    @RequestMapping(value = "/donwloadEntrustment", method = RequestMethod.GET)
    public void donwloadCompanyCreditDoc(@RequestParam(value = "category") String category, HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException {
        ResponseEntity<byte[]> resp = registerFileService.donwloadEntrustment(category);
        BaseTemplateDictionaryEntity baseTemplateDictionaryEntity = registerFileService.searchBaseTemplateDictionaryByCode(category);
        InputStream inStream = new ByteArrayInputStream(resp.getBody());
        DownLoadUtil.downLoadByInputStream(response, request, baseTemplateDictionaryEntity.getName(), inStream);
    }

    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ResponseBody
    @RequestMapping(value = "/deleteRegisterFile", method = RequestMethod.GET)
    public Message deleteRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.deleteRegisterFile(type,registerCode);
    }

    @ResponseBody
    @RequestMapping(value ="/donwloadRegisterIDCard", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadRegisterIDCard(@RequestParam(value = "pid") Long pid){
        return registerFileService.donwloadRegisterIDCard(pid);
    }


}
