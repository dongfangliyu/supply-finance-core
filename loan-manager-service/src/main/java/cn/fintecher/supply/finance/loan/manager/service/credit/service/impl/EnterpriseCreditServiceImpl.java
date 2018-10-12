package cn.fintecher.supply.finance.loan.manager.service.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCGuaranteeManagerCore;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseCreditCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.EnterpriseCreditService;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author WuTianJuan
 * @Date Created in 13:30 2018/6/15
 */
@Service("enterpriseCreditService")
public class EnterpriseCreditServiceImpl implements EnterpriseCreditService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseCreditServiceImpl.class);

    @Autowired
    private IRedisService redisService;

    @Autowired
    private FCEnterpriseCreditCore fcEnterpriseCreditCore;

    @Autowired
    private FCGuaranteeManagerCore fcGuaranteeManagerCore;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    public Message searchCompanyCreditStatus(SearchCompanyCreditForm searchCompanyCreditForm){
        Message msg = new Message();
        Map result = new HashMap();
        try {
            CompanyEnterpriseEntity entity = fcEnterpriseCreditCore.searchCompanyCreditStatus(searchCompanyCreditForm);
            if(ChkUtil.isEmpty(entity)){
                msg.setCode(-1);
            }
            String state = entity.getState();
            Long creditStatus = entity.getCreditStatus();
            if(!ChkUtil.isEmpty(state)){
                result.put("state",state);//10未开始 0信息填写中 20正在审核中 30调用审核未通过  40调用审核通过
                result.put("creditStatus",creditStatus);
            }
            msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            msg.setMessage(result);
        } catch (Exception e){
            msg.setCode(-1);
            LOGGER.error("查询企业授信状态失败");
            e.printStackTrace();
        }
      return msg;
    }

   public Message startCompanyCredit(Long pid,String code){
       Message msg =  new Message();
       if(code.equals("1")){
           msg.setCode(0);
           return msg;
       }
       CompanyEnterpriseEntity entity = fcEnterpriseCreditCore.searchCompanyInfo(pid);
       Long  status = entity.getCreditStatus();
       String name  = entity.getName();
       String creditNumber = getCreditNumber();
       if(status==0){
           entity.setCreditStatus(10l);//开始授信
           entity.setState("0");//信息填写中
       }
       if(status==10)//  提交财务信息
           entity.setCreditStatus(20l);
       if(status==20)   //提交影像资料信息
           entity.setCreditStatus(30l);//
       if(status==30){//申请授信
           entity.setState("20");       //授信审核中
           entity.setCreditNumber(creditNumber);
           entity.setSubmitTime(new Date());
           //新增授信审核表
           AuditCompanyEntity en =  fcGuaranteeManagerCore.searchAuditCompanyId(pid.toString());
           AuditCompanyEntity auditEntity = new AuditCompanyEntity();
           if(null==en){
               auditEntity.setState("0");
               auditEntity.setType("0");
               auditEntity.setName(name);
               auditEntity.setSubmitTime(new Date());
               auditEntity.setFinanceStatus("0");
               auditEntity.setMaterialStatus("0");
               auditEntity.setEnterpriseId(pid+"");
               auditEntity.setAudit("1");//待尽调
               auditEntity.setEntryStatus("0");//未录入
               auditEntity.setAuditStatus("0");//未授信
               auditEntity.setCreateAt(new Date());
               auditEntity.setStatus(Constants.DATA_STATUS_NOL);
           } else{
               msg.setMessage("该企业已存在，不可重复提交");
               msg.setCode(-1);
               msg.setType("credit-enterprise-service");
               return msg;
           }
           fcEnterpriseCreditCore.applyCompanyCredit(auditEntity);
       }
       fcEnterpriseCreditCore.startCompanyCredit(entity);
       msg.setCode(0);
       msg.setType("credit-enterprise-service");
       return msg;
   }

   //授信申请编号
   public String getCreditNumber(){
       String creditNo = redisService.get("creditNumber");
       if(Strings.isNullOrEmpty(creditNo)){
           long aLong = fcEnterpriseCreditCore.searchCountOfCredit();
           long newCount = aLong + 1l;
           creditNo = String.valueOf(newCount);
           redisService.set("creditNumber",creditNo);
       }else {
            long aLong = Long.valueOf(creditNo);
            long newLong = aLong + 1l;
            creditNo = String.valueOf(newLong);
            redisService.set("creditNumber",creditNo);
        }
       Date d = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String dateNowStr = sdf.format(d);
       dateNowStr = dateNowStr.replace("-", "");

       if(creditNo.length() == 1){
           creditNo = "HX"+ dateNowStr +"000" + creditNo;
       } else if(creditNo.length() == 2){
           creditNo = "HX"+ dateNowStr +"00" + creditNo;
       } else if(creditNo.length() == 3){
           creditNo = "HX"+ dateNowStr +"0" + creditNo;
       } else if(creditNo.length() == 4){
           creditNo = "HX"+ dateNowStr + creditNo;
       }
       return creditNo;
   }

    public Message searchAccountingStatementTime(){
        Message msg = new Message();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy");
        String year = format.format(cal.getTime());
        msg = fcEnterpriseCreditCore.searchAccountingStatementTime(year);
        msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return msg;
    }

    public Message searchCompanyCreditResult(Long pid) {

        return fcEnterpriseCreditCore.searchCompanyCreditResult(pid);
    }

    @Override
    public Message searchSupplierList(String pid) {
        Message msg = new Message();
        CompanyEnterpriseEntity entity = fcEnterpriseCreditCore.searchCompanyInfo(Long.valueOf(pid));
        if(entity.getState().equals("40")){
            entity.setState("41");
            fcEnterpriseCreditCore.updateCompanyCreditStep(entity);
            msg.setCode(0);
        }
        return msg;
    }
}
