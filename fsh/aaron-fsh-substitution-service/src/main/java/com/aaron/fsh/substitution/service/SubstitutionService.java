package com.aaron.fsh.substitution.service;
import com.aaron.fsh.substitution.dto.SubstitutionDto;
/**
 * 房產置換業務
 * @author aaron
 */
public interface SubstitutionService {
 	
 	/**
 	* 獲取置換信息
 	* @param sid 置換編號
 	* @return
 	*/
 	SubstitutionDto getSubstitutionInfo(Long sid);
 	
}
