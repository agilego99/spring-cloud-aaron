package com.aaron.api.client.fsh.house;

import org.springframework.stereotype.Component;

import com.aaron.api.client.fsh.house.dto.HouseInfoDto;
import com.aaron.api.client.fsh.house.dto.HouseListDto;

/**
 *  房產服務調用熔斷默認返回處理
 **/
@Component
public class HouseRemoteClientHystrix implements HouseRemoteClient {

    @Override
    public HouseListDto hosueList(Long eid, String uid) {
        return new HouseListDto();
    }

    @Override
    public HouseInfoDto hosueInfo(Long houseId) {
        return new HouseInfoDto();
    }

    @Override
    public String hello() {
        return null;
    }
}
