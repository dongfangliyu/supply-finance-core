package cn.fintecher.supply.finance.loan.manager.service.audit.core;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@FeignClient(name = "loan-manager-core")
public interface AuditOrderInfoCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/insertOrderInfo", method = RequestMethod.POST)
    public Message insertOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/updateOrderInfo", method = RequestMethod.POST)
    public Message updateOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectByOrderInfo", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectByOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/queryOrderInfoByPid", method = RequestMethod.GET)
    public Message<AuditOrderInfoEntity> queryOrderInfoByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectFristCount", method = RequestMethod.POST)
	public Message<Integer> selectFristCount(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectFristList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> selectFristList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchSigningList", method = RequestMethod.POST)
    Message<List<AuditOrderInfoEntity>> searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchSigningListCount", method = RequestMethod.POST)
    Message<Integer> searchSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectSecondCount", method = RequestMethod.POST)
	public Message<Integer> selectSecondCount(AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectSecondList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> selectSecondList(AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectThirdCount", method = RequestMethod.POST)
	public Message<Integer> selectThirdCount(AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectThirdList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> selectThirdList(AuditOrderInfoFrom auditOrderInfoFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchEnterSigningList", method = RequestMethod.POST)
    Message<List<AuditOrderInfoEntity>> searchEnterSigningList(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchEnterSigningListCount", method = RequestMethod.POST)
    Message<Integer> searchEnterSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchPlatformSigningList", method = RequestMethod.POST)
    Message<List<AuditOrderInfoEntity>> searchPlatformSigningList(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchPlatformSigningListCount", method = RequestMethod.POST)
    Message<Integer> searchPlatformSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchRemindListCount", method = RequestMethod.POST)
    int searchRemindListCount(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchRemindList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchRemindList(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchReviewListCount", method = RequestMethod.POST)
    int searchReviewListCount(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchReviewList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchReviewList(@RequestBody AuditRemindForm auditRemindForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchSuppReviewList", method = RequestMethod.POST)
    Message<List<AuditOrderInfoEntity>> searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchSuppReviewListCount", method = RequestMethod.POST)
    Message<Integer> searchSuppReviewListCount(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchEntryListCount", method = RequestMethod.POST)
    int searchEntryListCount(@RequestBody AuditRemindForm auditRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/searchEntryList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchEntryList(@RequestBody AuditRemindForm auditRemindForm);
}
