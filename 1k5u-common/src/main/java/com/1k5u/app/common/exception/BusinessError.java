package com.zhenyulaw.jf.common.exception;

public enum BusinessError {
	
	/**
	 * User required
	 */
	ERROR_REQUIRED_USER_NAME("10000", "用户名不能为空"),
	ERROR_REQUIRED_USER_PASSWORD("10001", "密码不能为空"),
	ERROR_REQUIRED_USER_CATEGORY("10002", "请选择用户类型"),
	ERROR_REQUIRED_USER_PHONE("10003", "手机号不能为空"),
	ERROR_EXIST_USER_PHONEEXIST("10004", "该手机号码已经被注册，请用其他号码注册"),
	ERROR_EXIST_USER_PHONENOTEXIST("10005", "该手机号码不存在"),
	ERROR_EXIST_USER_NAMEEXIST("10006", "用户名已存在"),
	ERROR_EXIST_USER_NAMENOTEXIST("10007", "用户名不存在"),
	ERROR_WRONG_USER_PASSWORD("10008", "密码错误"),
	
	
	
	/**
	 * Ship required
	 */
	ERROR_REQUIRED_SHIP_NAME("10101", "船名不能为空"),
	ERROR_REQUIRED_SHIP_DEADWEIGHT("10102", "载重量不能为空"),
	ERROR_REQUIRED_SHIP_OWNER("10103", "必须选择或新增一个船东，如果是新增船东，请填写姓名和联系电话"),
	
	/**
	 * Freight required
	 */
	ERROR_REQUIRED_FREIGHT_ID("10301", "货盘不能为空"),
	ERROR_REQUIRED_LOADING_DATE("10201", "装货日期不能为空"),
	ERROR_REQUIRED_GOODS_NAME("10202", "货物名称不能为空"),
	ERROR_REQUIRED_GOODS_WEIGHT("10203", "货物重量不能为空，且必须大于零"),
	ERROR_REQUIRED_ORIGIN("10204", "出发地不能为空"),
	ERROR_REQUIRED_DESTINATION("10205", "目的地不能为空"),
	
	/**
	 * SMS Task required
	 */
	
	
	/**
	 * Unknown Error
	 */
	ERROR_000("000", "Unknown Error"),
	
	//User Profile
	/**
	 * Need Login
	 */
	ERROR_001("001", "Need Login"),
	
	/**
	 * Login Failed
	 */
	ERROR_002("002", "Login Failed"),
	
	/**
	 * Wrong File Type
	 */
	ERROR_003("003", "Wrong File Type, allowed file type are \"Jpg, Jpeg, Png, Gif\"");
	
	private String errorCode;
	private String errorMessage;
	
	private BusinessError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}
