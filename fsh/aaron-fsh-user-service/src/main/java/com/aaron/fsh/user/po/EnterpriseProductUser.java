package com.aaron.fsh.user.po;
import java.io.Serializable;
import java.util.Date;
import org.gordianknot.jdbc.annotation.Field;
import org.gordianknot.jdbc.annotation.TableName;
@TableName(value = "enterprise_product_user", author = "aaron", desc = "企業產品中的用戶表")
public class EnterpriseProductUser implements Serializable {
 	private static final long serialVersionUID = -5813079687876757814L;
 	@Field(value="uid", desc="用戶ID")
 	private String uid;
 	
 	@Field(value="eid", desc="企業ID")
 	private Long eid;
 	
 	@Field(value="add_time", desc="保存時間")
 	private Date addTime;
 	@Field(value="remarks", desc="備注")
 	private String remarks;
 	@Field(value="city", desc="城市")
 	private String city;
 	
 	@Field(value="region", desc="區域")
 	private String region;
 	
 	public String getUid() {
 		return uid;
 	}
 	public void setUid(String uid) {
 		this.uid = uid;
 	}
 	public Long getEid() {
 		return eid;
 	}
 	public void setEid(Long eid) {
 		this.eid = eid;
 	}
 	public Date getAddTime() {
 		return addTime;
 	}
 	public void setAddTime(Date addTime) {
 		this.addTime = addTime;
 	}
 	public String getRemarks() {
 		return remarks;
 	}
 	public void setRemarks(String remarks) {
 		this.remarks = remarks;
 	}
 	public String getCity() {
 		return city;
 	}
 	public void setCity(String city) {
 		this.city = city;
 	}
 	public String getRegion() {
 		return region;
 	}
 	public void setRegion(String region) {
 		this.region = region;
 	}
 	
}
