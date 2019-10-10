package com.aaron.fsh.house.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.gordianknot.common.util.JsonUtils;
import com.aaron.fsh.house.dto.UpdateHouseNameDto;
import com.aaron.fsh.house.mq.HouseProcessor;
import com.aaron.fsh.house.po.HouseInfo;
import com.aaron.mqclient.TransactionMqRemoteClient;
import com.aaron.mqclient.dto.TransactionMessage;
import org.gordianknot.jdbc.EntityService;
@Service
public class HouseServiceImpl extends EntityService<HouseInfo> implements HouseService {
 	private Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);
 	@Autowired
 	private HouseProcessor houseProcessor;
 	@Autowired
 	private TransactionMqRemoteClient transactionMqRemoteClient;
 	
 	@Override
 	public List<HouseInfo> queryAll(Long eid, String uid) {
 		List<HouseInfo> houses = new ArrayList<HouseInfo>();
 		houses.add(new HouseInfo(1L, "上海", "虹口", "玉田新村"));
 		houses.add(new HouseInfo(2L, "上海", "虹口", "東體小區"));
 		return houses;
 	}
 	@Override
 	public HouseInfo getHouseInfo(Long houseId) {
 		//houseProcessor.addHouseOutput().send(MessageBuilder.withPayload("hello").build());
 		logger.info("查詢房產信息");
 		//List<HouseInfo> list = super.list();
 		//for (HouseInfo house : list) {
 			//System.err.println(house.getName());
 		//}
 		try {
 			Thread.sleep(100);
 		} catch (Exception e){}
 		return new HouseInfo(1L, "上海", "虹口", "玉田新村");
 	}
 	@Override
 	@Transactional
 	public boolean update(HouseInfo info) {
 		HouseInfo old = super.getById("id", info.getId());
 		super.updateByContainsFields(info, "id", new String[]{ "name" });
 		// 修改之後發送消息給置換服務進行名稱修改操作，最終一致性
 		TransactionMessage message = new TransactionMessage();
 	    message.setQueue("house_queue");
 	    message.setCreateDate(new Date());
 	    message.setSendSystem("house-service");
 	    message.setMessage(JsonUtils.toJson(
 			    new UpdateHouseNameDto(old.getCity(), old.getRegion(), old.getName(), info.getName())
 	    ));
 	  
 	    boolean result = transactionMqRemoteClient.sendMessage(message);
 	    if (!result) {
 		    throw new RuntimeException("回滾事務");
 	    }
 	    return result;
 	}
}
