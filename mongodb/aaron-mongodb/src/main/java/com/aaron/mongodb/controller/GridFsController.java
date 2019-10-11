package com.aaron.mongodb.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
@RestController
public class GridFsController {
 	@Autowired
 	private GridFsTemplate gridFsTemplate;
 	@Autowired
 	private MongoTemplate mongoTemplate;
 	
 	/**
 	* 上傳文件
 	*
 	* @author Aaron
 	* @throws Exception
 	*/
 	@GetMapping("/image/upload")
 	public String uploadFile() throws Exception {
 		File file = new File("/Users/aaron/Downloads/logo.png");
 		InputStream content = new FileInputStream(file);
 		// 存儲文件的額外信息，比如用戶ID,後面要查詢某個用戶的所有文件時就可以直接查詢
 		DBObject metadata = new BasicDBObject("userId", "1001");
 		ObjectId fileId = gridFsTemplate.store(content, file.getName(), "image/png", metadata);
 		System.out.println(fileId.toString());
 		return "success";
 	}
 	
 	/**
 	* 根據文件ID查詢文件
 	*
 	* @author Aaron
 	* @param fileId
 	* @return
 	* @throws Exception
 	*/
 	@GetMapping("/image/get")
 	public String getFile(String fileId) throws Exception {
 		GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
 		System.err.println(file.getFilename());
 		return "success";
 	}
 	/**
 	* 根據文件ID刪除文件
 	*
 	* @author Aaron
 	* @param fileId
 	* @throws Exception
 	*/
 	@GetMapping("/image/remove")
 	public String removeFile(String fileId) throws Exception {
 		gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
 		return "success";
 	}
 	/**
 	* 下載文件
 	* @param fileId
 	* @param response
 	*/
 	@GetMapping("/image/{fileId}")
 	public void getImage(@PathVariable String fileId, HttpServletResponse response) {
 		try {
 			GridFSFile gridfs = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
 			response.setHeader("Content-Disposition", "attachment;filename=\"" + gridfs.getFilename() + "\"");
 			GridFSBucket gridFSBucket = GridFSBuckets.create(mongoTemplate.getDb());
 			GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridfs.getObjectId());
 			GridFsResource resource = new GridFsResource(gridfs, gridFSDownloadStream);
 			InputStream inp = resource.getInputStream();
 			OutputStream out = response.getOutputStream();
 			IOUtils.copy(inp, out);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
}
