package  cn.fintecher.supply.finance.loan.manager.common.commodity.entity;


import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 * 入库信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Data
public class CommodityStockInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*企业id  来源 (企业信息表)
	*/
	@ApiModelProperty(value="企业id  来源 (企业信息表)")
	private Long companyId;
	
	/**
	*申请编号
	*/
	@ApiModelProperty(value="申请编号")
	private String applyNumber;
	
	/**
	*供货商名称
	*/
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
	/**
	*品牌
	*/
	@ApiModelProperty(value="品牌")
	private String productBrand;
	
	/**
	*品名
	*/
	@ApiModelProperty(value="品名")
	private String productType;
	
	/**
	*数量
	*/
	@ApiModelProperty(value="数量")
	private BigDecimal productNumber;
	
	/**
	*单位
	*/
	@ApiModelProperty(value="单位")
	private Long productUnit;
	
	/**
	*单价
	*/
	@ApiModelProperty(value="单价")
	private BigDecimal unitPrice;
	
	/**
	*总价值
	*/
	@ApiModelProperty(value="总价值")
	private BigDecimal totalPrice;
	
	/**
	*产地省份
	*/
	@ApiModelProperty(value="产地省份")
	private String productAddressProvince;
	
	/**
	*产地市
	*/
	@ApiModelProperty(value="产地市")
	private String productAddressCity;
	
	/**
	*型号
	*/
	@ApiModelProperty(value="型号")
	private String productModel;
	
	/**
	*等级
	*/
	@ApiModelProperty(value="等级")
	private String productGrade;
	
	/**
	*质量标准
	*/
	@ApiModelProperty(value="质量标准")
	private String productStandard;
	
	/**
	*大小
	*/
	@ApiModelProperty(value="大小")
	private String productSize;
	
	/**
	*有效期开始时间
	*/
	@ApiModelProperty(value="有效期开始时间")
	private Date effectiveStartTime;
	
	/**
	*有效期结束时间
	*/
	@ApiModelProperty(value="有效期结束时间")
	private Date effectiveEndTime;
	
	/**
	*生产日期
	*/
	@ApiModelProperty(value="生产日期")
	private Date productionDate;
	
	/**
	*申请入库时间
	*/
	@ApiModelProperty(value="申请入库时间")
	private Date applyStockTime;
	
	/**
	*状态 1:已入库  0：未入库
	*/
	@ApiModelProperty(value="状态 1:已入库  0：未入库")
	private String state;
	
	/**
	*实际入库日期
	*/
	@ApiModelProperty(value="实际入库日期")
	private Date actualStockDate;
	
	/**
	*颜色
	*/
	@ApiModelProperty(value="颜色")
	private String productColour;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*更新时间
	*/
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
	@ApiModelProperty(value="状态 DEL删除，STP停用，NOL正常")
	private String status;
	
	/**
	*备注
	*/
	@ApiModelProperty(value="备注")
	private String remark;
	
    private PledgeStockInfoEntity pledgeStockInfoEntity;
}