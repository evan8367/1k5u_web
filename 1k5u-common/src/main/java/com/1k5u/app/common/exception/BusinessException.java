package com.zhenyulaw.jf.common.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	protected BusinessError errorMessage;
	
	public BusinessException(BusinessError businessError) {
		super(businessError.getErrorMessage());
		this.errorMessage = businessError;
	}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException() {
		super(BusinessError.ERROR_000.getErrorMessage());
		this.errorMessage = BusinessError.ERROR_000;
	}
	
	public BusinessError getBusinessError() {
		return errorMessage;
	}
	
	public String getErrorMessage() {
		return this.getMessage();
	}
}
