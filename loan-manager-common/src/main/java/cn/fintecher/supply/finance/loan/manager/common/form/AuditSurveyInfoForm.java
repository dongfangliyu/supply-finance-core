package cn.fintecher.supply.finance.loan.manager.common.form;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditSurveyInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WuTianJuan
 * @Date Created in 15:46 2018/7/6
 */

@Data
public class AuditSurveyInfoForm implements Serializable {

      @ApiModelProperty(value="现场调查录入")
      private AuditSurveyInfoEntity auditSurveyInfoEntity;

      @ApiModelProperty(value = "审核id")
      private Long id;

      @ApiModelProperty(value = "审核意见")
      private String remark;

      @ApiModelProperty(value = "审核类型")
      private String type;
}
