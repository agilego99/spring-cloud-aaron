package com.aaron.cache_data_redis.controller;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.cache_data_redis.po.Person;
import com.aaron.cache_data_redis.repository.PersonRepository;
import com.aaron.cache_data_redis.service.CacheService;
import com.aaron.cache_data_redis.service.Closure;
import com.aaron.cache_data_redis.service.PersonService;
@RestController
public class CacheController {
	
	// 注入 StringRedisTemplate
 	@Autowired
 	private StringRedisTemplate stringRedisTemplate;
 	
 	@Autowired
 	PersonRepository repo;
 	@Autowired
 	private PersonService personService;
 	
 	// 緩存回調
 	@Autowired
 	private CacheService cacheService;
 	
 	@Autowired
 	private RedissonClient redissonClient;
 	
 	@GetMapping("/lock")
 	public String lock() throws InterruptedException {
 		/**
 		 *  Redisson 是一個在 Redis 基礎上所實現的 Java 駐記憶體的數據網格（In-Memory Data Grid）
 		 *  與 Jedis 差不多，都是用來操作 Redis 的誇架
 		 *  Redisson 提供了多個封裝，有 BloomFilter 等
 		 */
 		RLock lock = redissonClient.getLock("anyLock");
 		/**
 		 * 最常見的使用方法 lock.lock();
 		 * 支持過期自動解鎖功能
 		 * 1.10秒後自動解鎖
 		 * 2.10秒後自動解鎖，並嘗試 100秒
 		 * 3.無需調用 uplock 方法手動解鎖，若需手動加入，可作為雙保險
 		 */
 		// 1.加鎖，10秒後自動解鎖
 		lock.lock(10, TimeUnit.SECONDS);
 		// 2. 嘗試加鎖，最多等待 100秒，上鎖之後 10秒自動解鎖
 	    boolean res = lock.tryLock(100,10,TimeUnit.SECONDS);
 	    // 3.加入手動解鎖，避免自動解鎖失敗
 	    lock.unlock();
 		return "success";
 	}
 	
 	@GetMapping("/test2")
 	public void basicCrudOperations() {
 		Person person = new Person();
 		person.setFirstname("Aaron");
 		person.setLastname("Chu");
 		repo.save(person);
 		Optional<Person> personObj = repo.findById(person.getId());
 		System.err.println(personObj.get().getFirstname());
 		System.err.println(repo.count());
// 		repo.delete(person); // 若需要在 Redis 工具上觀察，需註解本行程式碼
 	}
 	
 	@GetMapping("/test")
 	public String test() {
 		// 設置緩存
 		stringRedisTemplate.opsForValue().set("key", " Gordianknot ", 1, TimeUnit.HOURS);
 		
 		// 獲取緩存
 		String value = stringRedisTemplate.opsForValue().get("key");
 		System.out.println(value);
 		
 		// 刪除緩存
 		stringRedisTemplate.delete("key");
 		boolean exists = stringRedisTemplate.hasKey("key");
 		System.out.println(exists);
 		
 		// 除了使用封裝好的方法，也可以用底層的方法操作，通過 StrinngRedisTemplate 可以拿到 RedisConnection
 		RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
 		connection.set("name".getBytes(), "aaronn".getBytes());
 		System.out.println(new String(connection.get("name".getBytes())));
 		return "success";
 	}
 	

 	@GetMapping("/get")
 	public Person get() {
 		return personService.get("1001");
 	}
 	
 	@GetMapping("/get2")
 	public Person get2() {
 		return personService.get2("1002");
 	}
 	
 	
 	// 緩存回調接口
 	@GetMapping("/getCallback")
 	public String getCallback() {
 		String cacheKey = "1001";
 		// 緩存存在資料
 		return cacheService.getCache(cacheKey, new Closure<String, String>() {
 			// 緩存不存在資料
 			@Override
 			public String execute(String id) {
 				System.err.println("緩存不存在，改以其他方法獲取資料");
 				// 執行你的業務邏輯
 				return id+"hello";
 			}
 		});
 	}
}
