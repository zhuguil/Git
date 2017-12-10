package com.atguigu.crud.bean;

import java.util.Hashtable;
import java.util.Map;

public class Msg {
	//×´Ì¬Âë   100  200
	private int code;
	private String status;
	private Map<String, Object> extend = new Hashtable<>();
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	public static Msg success(){
		Msg msg = new Msg();
		msg.setCode(100);
		msg.setStatus("success");
		return msg;
	}
	public static Msg fail(){
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setStatus("fail");
		return msg;
	}
	
	public  Msg addExtend(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}
}
