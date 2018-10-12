package cn.fintecher.supply.finance.loan.manager.service.credit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCBaseDictionaryService;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.BaseTemplateDictionaryService;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCFileCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author WuTianJuan
 * @Date Created in 20:31 2018/7/2
 */


 @Service
 public class BaseTemplateDictionaryServiceImpl implements BaseTemplateDictionaryService {

     @Autowired
     private FCBaseDictionaryService fcBaseDictionaryCore;

     @Autowired
     private FCFileCore fcFileCore;

     public ResponseEntity<byte[]> downloadDocTemplate(String code){
         BaseTemplateDictionaryEntity entity = fcBaseDictionaryCore.downloadDocTemplate(code);
         String url = entity.getUrl();
         ResponseEntity<byte[]> responseEntity = fileDownLoad(url, "image");
         HttpHeaders headers = new HttpHeaders();
         headers.set(HttpHeaders.CONTENT_TYPE, getContentType(url));
         return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }

    /**
     * 文件下载
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    public ResponseEntity<byte[]> fileDownLoad(String fullPath, String fileName) {
        return fcFileCore.fileDownLoad(fullPath, fileName);
    }

    /**
     获取文件MIME类型
     @param fileName
     @return*/

    public static String getContentType(String fileName){
        String type = null;
        Path path = Paths.get(fileName);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

 }
