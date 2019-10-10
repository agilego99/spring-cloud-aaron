package com.aaron.fsh.user.query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 登錄參數
 * @author aaron
 **/
@ApiModel(value = "com.aaron.fsh.user.query.LoginQuery", description = "登錄參數")
public class LoginQuery {
    @ApiModelProperty(value = "企業編號", required = true)
    private Long eid;
    @ApiModelProperty(value = "用戶編號", required = true)
    private String uid;
    public Long getEid() {
        return eid;
    }
    public void setEid(Long eid) {
        this.eid = eid;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
