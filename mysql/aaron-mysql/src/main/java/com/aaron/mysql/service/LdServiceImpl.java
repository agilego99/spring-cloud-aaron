package com.aaron.mysql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.mysql.po.LouDong;
import org.gordianknot.jdbc.EntityService;
import org.gordianknot.jdbc.PageQueryParam;

/**
 * EntityService 中所方法皆是根據 GordianknotJdbcTemplate，只不過多包裝了一層
 * 所以除了可以使用 GordianknotJdbcTemplate，也可以使用原生的 JdbcTemplate
 * @author Aaron
 *
 */
@Service
public class LdServiceImpl extends EntityService<LouDong> implements LdService {

	// 查詢總數量
	public long count() {
		return super.count();
	}

	// 查詢所有資料並排序
	public List<LouDong> findAll() {
		return super.list(LouDong.ORDER_FIELDS);
	}

    // 根據城市區域查詢
	public List<LouDong> find(String city) {
		return super.list("city", city);
	}

	// 根據城市區域查詢
	public List<LouDong> find(String city, String region) {
		return super.list(new String[] { "city", "region" }, new Object[] { city, region });
	}

	// 根據程式區域名稱查詢、顯示指定欄位
	public List<LouDong> find(String city, String region, String name) {
		return super.list(LouDong.SHOW_FIELDS, LouDong.QUERRY_FIELDS, new Object[] { city, region, name });
	}

	// 分頁排序查詢所有
	public List<LouDong> findAll(PageQueryParam page) {
		return super.listForPage(page.getStart(), page.getLimit(), LouDong.ORDER_FIELDS);
	}

	// 根據程式判斷是否存在資料
	public boolean exists(String city) {
		return super.exists("city", city);
	}

	// 根據名稱 in 查詢
	public List<LouDong> in(String[] names) {
		return super.in(new String[] { "city", "region" }, "name", names);
	}

	// 根據 ID 獲得資料
	public LouDong get(String id) {
		return super.getById("id", id);
	}

	// 根據名稱刪除資料
	@Transactional
	public void delete(String name) {
		super.deleteById("name", name);
	}

	// 保存資料
	public void save(LouDong louDong) {
		super.save(louDong);
	}
	
    // 批次保留資料
	public void saveList(List<LouDong> list) {
		super.batchSave(list);
	}

	// 更新資料
	public void update(LouDong louDong) {
		super.update(louDong, "id");
	}

	// 批次更新資料
	public void updateList(List<LouDong> list) {
		super.batchUpdateByContainsFields(list, "id", "city");
	}
	
	// 生成 PO 類
	public void generatePo(String pack,String author,String savePath) {
		super.getJdbcTemplate().generatePoClass(pack, author, savePath);
	}
}
