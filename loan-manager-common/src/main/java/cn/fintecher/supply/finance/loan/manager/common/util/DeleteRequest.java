package cn.fintecher.supply.finance.loan.manager.common.util;

/**
 * @author hujinbao
 *
 */
public class DeleteRequest {
	/**
	 * 用户账户
	 */
	private String userName;
	
	/**
	 * 用户id
	 */
	private Integer userId;
	
	/**
	 * 要删除的id
	 */
	private Integer id;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
