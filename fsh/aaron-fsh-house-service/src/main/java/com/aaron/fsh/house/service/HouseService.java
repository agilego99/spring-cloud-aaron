package com.aaron.fsh.house.service;
import java.util.List;
import com.aaron.fsh.house.po.HouseInfo;
/**
 * 房產信息業務接口
 * @author aaron
 **/
public interface HouseService {
 	
 	/**
 	* 查詢企業下某個用戶的所有有效的房產信息
 	* @param eid	企業編號
 	* @param uid	用戶編號
 	* @return
 	*/
 	List<HouseInfo> queryAll(Long eid, String uid);
 	
 	/**
 	* 根據房產編號查詢房產信息
 	* @param houseId 房產編號
 	* @return
 	*/
 	HouseInfo getHouseInfo(Long houseId);
 	
 	/**
 	* 修改信息
 	*/
 	boolean update(HouseInfo info);
}
