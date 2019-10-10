package com.aaron.api.client.fsh.house;


import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import com.aaron.api.client.fsh.house.dto.HouseInfo;
import com.aaron.api.client.fsh.house.dto.HouseInfoDto;
import com.aaron.api.client.fsh.house.dto.HouseListDto;

@Component
public class HouseRemoteClientFallbackFactory implements FallbackFactory<HouseRemoteClient> {
    @Override
    public HouseRemoteClient create(final Throwable cause) {
        return new HouseRemoteClient() {

            @Override
            public HouseListDto hosueList(Long eid, String uid) {
                return null;
            }

            @Override
            public HouseInfoDto hosueInfo(Long houseId) {
                HouseInfoDto info = new HouseInfoDto();
                info.setData(new HouseInfo(1L, "", "", cause.getMessage()));
                return info;
            }

            @Override
            public String hello() {
                return null;
            }
        };
    }
}
