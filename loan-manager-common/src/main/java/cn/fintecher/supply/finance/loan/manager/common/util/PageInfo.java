package cn.fintecher.supply.finance.loan.manager.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 分页信息，分页查询时使用
 * @author huanglei
 *
 */
@JsonIgnoreProperties
public class PageInfo implements Serializable{
	
    private static final Logger logger = LoggerFactory.getLogger(PageInfo.class);

	private static final long serialVersionUID = 3607626990775704759L;
	
	public static final Pattern SQL_ORDER_BY = Pattern.compile("^[a-zA-Z0-9\\.\\s,_]+$");
	
	// 排序字段映射
	public static Map<String, String> sortMap = new HashMap<String, String>();
	static{
		sortMap.put("loginName", "login_name");
		sortMap.put("realName", "real_name");
	}
	
	@ApiModelProperty(value="当前页")
	private int pageNo = 1;
	@ApiModelProperty(value="每页多少条")
	private int pageSize  = PageConfig.DEFAULT.getDefaultPageSize();
	@ApiModelProperty(value="判断字段",hidden=true)
	private String orderBy;
	@ApiModelProperty(value="启始位置",hidden=true)
	private int startIndex;
	@ApiModelProperty(value="排序字段",hidden=true)
    private String sort = "createdate";// 排序字段
	@ApiModelProperty(value="排序顺序",hidden=true)
    private String order = "desc";// asc，desc mybatis Order 关键字
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo <= 0 ){
			this.pageNo = 1;
		}else{
			this.pageNo = pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <=0 || pageSize > PageConfig.DEFAULT.getMaxPageSize()){
			logger.error("The pageSize param is too large, default page size will be used.");
			return;
		}
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		if(! StringUtils.isEmpty(orderBy) && !SQL_ORDER_BY.matcher(orderBy).matches()){
			logger.warn("Dangerouw order by pattern detected. It will be ignore.");
			this.orderBy = "1";
			return;
		}
		this.orderBy = orderBy;
	}
	
	public int getStartIndex() {
		return startIndex == 0 ? (pageNo -1)*pageSize: startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getSort() {
		// 排序字段名跟数据库字段名不一致的情况，把排序字段名映射成数据库字段名
		if(sortMap.containsKey(sort)){
			sort = sortMap.get(sort);
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
