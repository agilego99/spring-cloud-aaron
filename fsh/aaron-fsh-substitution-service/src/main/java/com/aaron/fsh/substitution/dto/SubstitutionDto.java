package com.aaron.fsh.substitution.dto;

import com.aaron.fsh.substitution.po.Substitution;

public class SubstitutionDto extends Substitution {
	
	private HouseInfo hosue;
	
	public void setHosue(HouseInfo hosue) {
		this.hosue = hosue;
	}
	
	public HouseInfo getHosue() {
		return hosue;
	}
}

