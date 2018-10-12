package cn.fintecher.supply.finance.loan.manager.service.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.TableValidateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:43 2018/6/26
 */
@Service
public class TableValidateServiceImpl implements TableValidateService {

    @Autowired
    private  FCEnterpriseFinancialCore enterpriseFinancialCore;

    public Message fileTable(TableValidateForm tableValidateForm){
        Message msg = new Message();
        long pid = tableValidateForm.getPid();
        List<String> years = tableValidateForm.getYears();
        List<CompanyFileEntity> list =enterpriseFinancialCore.searchAllFile(pid);
        for (String year : years) {
            if(!fileCheck("assetsTable",year,list)){
               // msg.setMessage("资产负载表"+year+"年上传为为空,请重新上传");
                msg.setMessage("请继续上传文件");
                msg.setCode(-1);
                return msg;
            }
        }

        for (String year : years) {
            if(!fileCheck("profitsTable",year,list)){
               // msg.setMessage("利润表"+year+"年上传为为空,请重新上传");
                msg.setMessage("请继续上传文件");
                msg.setCode(-1);
                return msg;
            }
        }

        for (String year : years) {
            if(!fileCheck("cashTable",year,list)){
                msg.setMessage("请继续上传文件");
              //  msg.setMessage("现金流量表"+year+"年上传为空,请重新上传");
                msg.setCode(-1);
                return msg;
            }
        }
        msg.setCode(0);
        return msg;
    }

    public  Message fileTableValidate(TableValidateForm tableValidateForm){
        Message msg = new Message();
        long pid = tableValidateForm.getPid();
        List<String> years = tableValidateForm.getYears();
        List<CompanyFileEntity> list =enterpriseFinancialCore.searchAllFile(pid);
        if(!fileCheck("organizeTable",null,list)){
         //   msg.setMessage("组织机构代码证为空,请重新上传");
            msg.setMessage("请继续上传文件");
            msg.setCode(-1);
            return msg;
        }

        if(!fileCheck("taxTable",null,list)){
           // msg.setMessage("税务登记证为空,请重新上传");
            msg.setMessage("请继续上传文件");
            msg.setCode(-1);
            return msg;
        }

        if(!fileCheck("openAccountTable",null,list)){
           // msg.setMessage("开户许可证为空,请重新上传");
            msg.setMessage("请继续上传文件");
            msg.setCode(-1);
            return msg;
        }

        for (String year : years) {
            if(!fileCheck("assetsTableImage",year,list)){
                msg.setMessage("请继续上传文件");
              //  msg.setMessage("资产负债表"+year+"年上传为为空,请重新上传");
                msg.setCode(-1);
                return msg;
            }
        }

        for (String year : years) {
            if(!fileCheck("profitsTableImage",year,list)){
                msg.setMessage("请继续上传文件");
              //  msg.setMessage("利润表"+year+"年上传为为空,请重新上传");
                msg.setCode(-1);
                return msg;
            }
        }

        for (String year : years) {
            if(!fileCheck("cashTableImage",year,list)){
            //    msg.setMessage("现金流量表"+year+"年上传为空,请重新上传");
                msg.setMessage("请继续上传文件");
                msg.setCode(-1);
                return msg;
            }
        }
           for(String year : years){
                if(!fileCheck("taxReturnTable",year,list)){
            //    msg.setMessage("纳税申报表为空,请重新上传");
                    msg.setMessage("请继续上传文件");
                msg.setCode(-1);
                return msg;
            }
        }
        msg.setCode(0);
        return msg;
    }

    public  boolean fileCheck(String category, String year, List<CompanyFileEntity> list){
        boolean flag = false;
        boolean flagyear = false;
          if(StringUtils.isBlank(year)){
            for(CompanyFileEntity entity : list){
                if(entity.getCategory().equals(category)){
                    flagyear = true;
                    break;
                }
            }
            return flagyear;

        }
        for (CompanyFileEntity entity : list){
            if(entity.getCategory().equals(category) && entity.getYear().equals(year)){
                flag = true;
                break;
            }
        }
        return  flag;
    }
}
