package com.aaron.design.solid.domains;

import com.sun.org.apache.xpath.internal.operations.String;
import lombok.Data;

/**
 * 貧血模型程式碼設計
 * 只有資料
 */
@Data
public class order {
    private int quantity;
    private String seatNo;
    private boolean drinkHere;
    private int price;
    private String itemName;
    private Strint establishTime;
    private int drinktemperature;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // ... 略 get and set method
}