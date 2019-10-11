# Zipkin：監控數據的分佈式與跟蹤系統
![272cd37294fe3ca3b51b676dc0ffb094](imgs/D5C902C9-DB99-417A-B9F0-8430DFD5C1E2.png)
###### Zipkin 是 Twitter 的開源專案，專注在收集所有服務的監控數據的分佈式與跟蹤系統。主要提供了收集數據和查詢數據兩大 API 介面。

## 參數

## 開始

### 安裝
- 下載 Zipkin `curl -ssl https://zipkin.io/quickstart.sh | bash -s`

## 配置
###### 安裝路徑 `/Users/aaron/develop/workspace/eclipse/spring-cloud-aaron/server/zipkin`

## 測試
- 訪問 Zipkin `http://localhost:9411/zipkin/`
![e2ca453ba7f04ea3858891f9ae4661ad](imgs/2F61777C-2863-441D-9072-36F66A6C7FEA.png)
![1393eb2c01c26306468d4a47aee28f3f](imgs/F2C43451-1E0A-4B62-BC90-010BDF880D1A.png)

## 維運
- 啟動 Zipkin
```
$ cd /Users/aaron/develop/workspace/eclipse/spring-cloud-aaron/server/zipkin

# 使用 RabbitMQ 作為調用鏈數據
$ java -DRABBIT_ADDRESSES=gordianknot:5672 -DRABBIT_USER=aaron -DRABBIT_PASSWORD=999999 -jar zipkin.jar

# 使用 Elasticsearch 作為調用鏈數據
$ java -DSTORAGE_TYPE=elasticsearch -DES_HOSTS=http://gordianknot:9200  -DRABBIT_ADDRESSES=gordianknot:5672 -DRABBIT_USER=aaron -DRABBIT_PASSWORD=999999 -jar zipkin.jar

$ lsof -i:9411 |grep LISTSEN
```

