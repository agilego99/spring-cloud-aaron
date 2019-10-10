package com.aaron.mysql.po;

import java.io.Serializable;
import org.gordianknot.jdbc.Orders;
import org.gordianknot.jdbc.annotation.Field;
import org.gordianknot.jdbc.annotation.TableName;
import lombok.Data;

/**
 * 定義資料對應的 PO 類、表、欄位等名稱，以註解中的 value 為準
 * @author Aaron
 */
@Data
@TableName(value = "loudong", desc = " 樓棟表 ", author = "aaron")
public class LouDong implements Serializable {

	private static final long serialVersionUID = -6690784263770712827L;
	@Field(value = "id", desc = " 主鍵 ID")
	private String id;
	@Field(value = "name", desc = " 社區名稱 ")
	private String name;
	@Field(value = "city", desc = " 城市 ")
	private String city;
	@Field(value = "region", desc = " 區域 ")
	private String region;
	@Field(value = "ld_num", desc = " 樓棟號 ")
	private String ldNum;
	@Field(value = "unit_num", desc = " 單元號 ")
	private String unitNum;
	public final static String[] SHOW_FIELDS = new String[] { "city", "region", "name", "ld_num" };
	public final static String[] QUERRY_FIELDS = new String[] { "city", "region", "name" };
	public final static Orders[] ORDER_FIELDS = new Orders[] { new Orders("id", Orders.OrderyType.ASC) };
}
