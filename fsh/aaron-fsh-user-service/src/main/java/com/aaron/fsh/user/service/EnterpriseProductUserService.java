package com.aaron.fsh.user.service;
/**
 * 企業用戶業務
 * @author aaron
 **/
public interface EnterpriseProductUserService {
    /**
     * 用戶登錄
     * @param eid   企業編號
     * @param uid   用戶編號
     * @return  認證成功返回token，失敗返回null
     */
    String login(Long eid, String uid);
}
