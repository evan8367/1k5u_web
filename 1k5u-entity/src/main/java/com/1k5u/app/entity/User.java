package com.zhenyulaw.jf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name="user")
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="JDBC")
	private Long id;
	private String name;
	private String phone;
	private String password;
	private Long companyId;
	@Transient
	private BigDecimal account;
	@Transient
	private String companyLogo;
	@Transient
	private String companyName;
	
	

	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	@OrderBy("desc")
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setCompanyId(Long companyId){
		this.companyId=companyId;
	}
	public Long getCompanyId(){
		return companyId;
	}
}

