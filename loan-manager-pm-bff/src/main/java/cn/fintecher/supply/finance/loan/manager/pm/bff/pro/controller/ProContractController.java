package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ProContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:57
 */
@RestController
@RequestMapping("/pro/proContract")
@Api(tags = "合同")
public class ProContractController {

    @Autowired
    private ProContractService proContractService;

    /**
     * 查询合同列表
     * @param proContractForm
     * @return
     */
    @ApiOperation(value="查询合同列表 ", notes="查询合同列表")
    @RequestMapping(value ="/searchListContract", method = RequestMethod.POST)
        public Message searchListProduct(@RequestBody ProContractForm proContractForm){
        Message message = proContractService.searchListContract(proContractForm);
        return message;
    }

    /**
     * 新增合同
     * @param proContractEntity
     * @return
     */
    @ApiOperation(value="新增合同 ", notes="新增合同")
    @RequestMapping(value ="/submitContract", method = RequestMethod.POST)
        public Message submitContract(@RequestBody ProContractEntity proContractEntity){
        Message message = proContractService.submitContract(proContractEntity);
        return message;
    }

    /**
     * 编辑合同
     * @param pid
     * @return
     */
    @ApiOperation(value="编辑合同 ", notes="编辑合同")
    @RequestMapping(value ="/searchContract", method = RequestMethod.GET)
        public Message searchContract(@RequestParam(value = "pid") String pid){
        Message message = proContractService.searchContract(pid);
        return message;
    }

    /**
     * 保存编辑的合同
     * @param proContractEntity
     * @return
     */
    @ApiOperation(value="保存编辑的合同 ", notes="保存编辑的合同")
    @RequestMapping(value ="/updateContract", method = RequestMethod.POST)
        public Message updateContract(@RequestBody ProContractEntity proContractEntity){
        Message message = proContractService.updateContract(proContractEntity);
        return message;
    }

    /**
     * 删除合同
     * @param pid
     * @return
     */
    @ApiOperation(value="删除合同 ", notes="删除合同")
    @RequestMapping(value ="/deleteContract", method = RequestMethod.GET)
        public Message deleteContract(@RequestParam(value = "pid") String pid){
        Message message = proContractService.deleteContract(pid);
        return message;
    }

    /**
     * 禁用/启用合同
     * @param pid
     * @return
     */
    @ApiOperation(value="禁用/启用合同 ", notes="禁用/启用合同")
    @RequestMapping(value ="/disableContract", method = RequestMethod.GET)
        public Message disableContract(@RequestParam(value = "pid") String pid){
        Message message = proContractService.disableContract(pid);
        return message;
    }

    /**
     * 查询产品类型对应合同
     * @param pid
     * @return
     */
    @ApiOperation(value="查询产品类型对应合同", notes="查询产品类型对应合同")
    @RequestMapping(value ="/searchProductListContract", method = RequestMethod.GET)
        public Message searchProductListContract(@RequestParam(value = "pid") String pid){
        Message message = proContractService.searchProductListContract(pid);
        return message;
    }

    /**
     * 下载合同
     * @param pid
     * @return
     */
    @ApiOperation(value="下载合同", notes="下载合同")
    @RequestMapping(value ="/downContract", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downContract(@RequestParam(value = "pid") String pid){
        return proContractService.downContract(pid);
    }

    /**
     * 上传文件
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ResponseBody
    @RequestMapping(value = "/uploadContractDoc", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadContractDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.uploadContractDoc(file,type,resourceCode);
    }

    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ResponseBody
    @RequestMapping(value = "/deleteContractFile", method = RequestMethod.GET)
    public Message deleteContractFile(@RequestParam(value = "type") String type,@RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.deleteContractFile(type,resourceCode);
    }


    /**
     * 根据产品类型查询合同list
     * @param category
     * @return
     */
    @ApiOperation(value="查询产品类型对应合同", notes="查询产品类型对应合同")
    @RequestMapping(value ="/searchContractListByCategory", method = RequestMethod.GET)
    public Message searchContractListByCategory(@RequestParam(value = "category") String category){
        Message message = proContractService.searchContractListByCategory(category);
        return message;
    }


    /**
     * 查询企业合同
     * */
    @ApiOperation(value="查询企业合同", notes="查询企业合同")
    @RequestMapping(value ="/searchContractListByCompanyId", method = RequestMethod.GET)
    public Message searchContractListByCompanyId(@RequestParam(value = "companyId") Long companyId){
        Message message = proContractService.searchContractListByCompanyId(companyId);
        return message;
    }
}
