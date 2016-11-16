package com.bit2016.dto;

public class JSONResult {
	private String result;
	private String message;
	private Object data;
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
	public static JSONResult success(Object data) {
		return new JSONResult(true, data, null);
	}
	
	public static JSONResult error(String message) {
		return new JSONResult(false, null, message);
	}
	
	private JSONResult() {
	}
	
	private JSONResult(boolean isSuccess, Object data, String message) {
		result = isSuccess ? "success" : "fail";
		this.data = data;
		this.message = message;
	}
}
