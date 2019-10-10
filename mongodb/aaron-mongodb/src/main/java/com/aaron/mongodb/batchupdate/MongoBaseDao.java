package com.aaron.mongodb.batchupdate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
public class MongoBaseDao {
 	
 	/**
 	* 批量更新
 	* @param ordered 如果為true,一條語句更新失敗，剩下的語句將不再執。如果為false,一條語句更新失敗，剩下的將繼續執行。默認為true。
 	* @return
 	*/
 	public static int bathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options, boolean ordered) {
 		DBObject command = new BasicDBObject();
 		command.put("update", collName);
 		List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
 		for (BathUpdateOptions option : options) {
 			BasicDBObject update = new BasicDBObject();
 			update.put("q", option.getQuery().getQueryObject());
 			update.put("u", option.getUpdate().getUpdateObject());
 			update.put("upsert", option.isUpsert());
 			update.put("multi", option.isMulti());
 			updateList.add(update);
 		}
 		command.put("updates", updateList);
 		command.put("ordered", ordered);
 		
 		Document commandResult = mongoTemplate.getDb().runCommand((Bson) command);
 		return Integer.parseInt(commandResult.get("n").toString());
 	}
 	
}
