# Druid-Metabase：支持資料庫種類多，啓動方便，支持 JSON 查詢。圖形化查詢，只能有一個聚合字段，兩個維度。

##### 支援多種類型資料庫
- 

### 參數
- Ubuntu 18.04
- Metabase v.19.3
    - 限制
        - 需要 jdk1.6 以上環境

## 開始


### 準備

#### 新增 metabase 工作目錄
```
$ mkdir -p ~/gordianknot/project/metabase
```

#### 下載 Metabase
`$ wget ~/gordianknot/resource "http://downloads.metabase.com/v0.19.3/metabase.jar"
`

### 安裝


##### Metabase
`$ cp ~/gordianknot/resource/metabase.jar ~/gordianknot/project/metabase`


### 配置

##### Druid 連線配置
```
http://gordianknot:3000
```
###### 輸入基本資訊
![b759cefebf1e459e368ff8b13b87932c](imgs/679E6F2E-94DE-45DB-ADF4-48360D73E1DD.png)
![ed0ed60ee083962cb419e7baaf1ba9f6](imgs/F18426FA-1306-4E86-834C-9ABC377CE506.png)
![da4737bbe904d7d9aa44492f2baa7ee4](imgs/7EB573D3-7C2C-4714-93E4-4C87BD56691C.png)
###### 查詢結果
![3d0a90e800bb6c2bf16bddbeb1f3ddc6](imgs/0B93CF57-1974-452D-BC3B-1EF7A9A048CE.png)


## 測試



## 維運

##### 啟動
```
$ java -jar /home/aaron/gordianknot/project/metabase/metabase.jar
```

###### 管理頁面
`http://gordianknot:3000`

