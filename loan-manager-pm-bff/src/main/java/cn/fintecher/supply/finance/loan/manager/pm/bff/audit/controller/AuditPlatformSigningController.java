package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditPlatformSigningService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditSuppSigningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 14:00
 */
@RestController
@RequestMapping("/audit/auditPlatformSigning")
@Api(tags = "平台签约管理")
public class AuditPlatformSigningController {

    @Autowired
    private AuditSuppSigningService auditSuppSigningService;

    @Autowired
    private AuditPlatformSigningService auditPlatformSigningService;

    /**
     * 平台签约管理列表
     */
    @ApiOperation(value = "平台签约管理列表", notes = "平台签约管理列表")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
    public Message searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest, Principal principal){
        try {
            return auditPlatformSigningService.searchPlatformSigningList(auditSigningRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }

    }

    /**
     * 平台签约详情
     */
    @ApiOperation(value = "平台签约详情", notes = "平台签约详情")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchSigningDetail", method = RequestMethod.GET)
    public Message<AuditSigningDetailResponse> searchSigningDetail(@RequestParam("pid") Long pid){
        try {
            return auditSuppSigningService.searchSigningDetail(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     *合同下载
     **/
    @ApiOperation(value = "合同下载", notes = "合同下载",produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/downloadContract", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadContract(@RequestParam("pid") Long pid){
        HttpHeaders headers = new HttpHeaders();
        try {
            return auditSuppSigningService.downloadContract(pid);
        }catch (Exception e){
            e.printStackTrace();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(new Message(MessageType.MSG_ERROR, "audit_bff", e.getMessage()), headers, HttpStatus.OK);
        }
    }

    /**
     * 签约
     * */
    @ApiOperation(value = "签约提交", notes = "签约提交")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/submitSigning", method = RequestMethod.GET)
    public Message submitSigning(@RequestParam("pid") Long pid,Principal principal){
        try {
            return auditPlatformSigningService.submitSigning(pid,principal.getName());
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 查看纸质合同(多类型)
     * */
    @ApiOperation(value = "查看纸质合同(多类型)", notes = "查看纸质合同(多类型)")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchContractMoreType", method = RequestMethod.GET)
    public Message<List<AuditFileEntity>> searchContractMoreType(@RequestParam("pid") String pid){
        try {
            return auditSuppSigningService.searchContractMoreType(pid);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 查看纸质合同
     * */
    @ApiOperation(value = "查看纸质合同", notes = "查看纸质合同")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value = "/searchContract", method = RequestMethod.GET)
    public Message<List<AuditFileEntity>> searchContract(@RequestParam("pid") String pid, @RequestParam("type") String type){
        try {
            return auditSuppSigningService.searchContract(pid,type);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }
}
