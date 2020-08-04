# apache-atlas＠
##### Apache Atlas 是 Hadoop 社區為解決 Hadoop生態系統的元數據治理問題而產生的開源項目，它為 Hadoop集群提供了包括數據分類、集中策略引擎、數據血緣、安全和生命週期管理在內的元數據治理核心能力。

## 參數
- Ubuntu 18.04
- apache-atlas-2.1.0
- Hbase
- Solr
- Zookeeper
- Kafka
- 服務依賴
    - Python 2
        - [研究｜應用｜Python＠Ubuntu 18.04]
    - Maven
        - [研究｜應用｜Maven]
    - JDK   
    - Hbase
    - Solr

## 開始

### 準備
```
$ mkdir -p ~/gordianknot/project/apache-atlas/apache-atlas-2.1.0
```

#### 下載
```
$ wget -P ~/gordianknot/resource
"https://www.apache.org/dyn/closer.cgi/atlas/2.1.0/apache-atlas-2.1.0-sources.tar.gz"
```

### 安裝
#### 解壓縮至指定目錄
```
$ tar -zxf apache-atlas-2.1.0-sources.tar.gz -C ~/gordianknot/project/apache-atlas
```

#### 編譯（兩種方式；本例採用第二種方式）
```
$ cd ~/gordianknot/project/apache-atlas/apache-atlas-sources-2.1.0
```
##### 一、編譯不會內嵌 HBase 和 Solr 
```
$ export MAVEN_OPTS="-Xms2g -Xmx2g"
$ mvn clean -DskipTests package -Pdist
```
##### 二、編譯方式會內嵌 HBase 和 Solr（測試用這種方式)
```
$ export MAVEN_OPTS="-Xms2g -Xmx2g"
$ mvn clean -DskipTests package -Pdist,embedded-hbase-solr 
```

編譯完成
![4c4ed6666af555d39f39db53977b4710](imgs/5F873060-149D-4F21-B24A-F2E4258DC168.png)

#### 部署
```
# 切換至 distro/target 目錄
$ cd ~/gordianknot/project/apache-atlas/apache-atlas-sources-2.1.0/distro/target

# 編譯完成後，產生 apache-atlas-2.1.0-bin.tar.gz 檔案
# 解壓縮至指定目錄
$ tar -zxvf apache-atlas-2.1.0-bin.tar.gz -C ~/gordianknot/project/apache-atlas/apache-atlas-2.1.0
```

### 配置

- 修改配置文件
```
$ cd ~/gordianknot/project/apache-atlas/apache-atlas-2.1.0
$ nano conf/atlas-env.sh
export MANAGE_LOCAL_HBASE=true  (如果要使用外部的zk和hbase，則改為false)
export MANAGE_LOCAL_SOLR=true  （如果要是用外部的solr，則改為false）
export MANAGE_LOCAL_ELASTICSEARCH=false
export MANAGE_EMBEDDED_CASSANDRA=false
```
![bfb6c188397a97acae7b7d8b84bb789d](imgs/A0DA0B53-68AE-4059-901F-8FE655D932AF.png)

- 修改 atlas-application.properties（安裝後保留預設值）
```
# Hbase地址（對應的zk地址）配置（自帶hbase會根據此端口啓動一個zk實例）
atlas.graph.storage.hostname=localhost:2181 # 如果使用外部hbase，則填寫外部zookeeper地址

# Solr地址配置
atlas.graph.index.search.solr.http-urls=http://localhost:8984/solr（solr服務地址）

# Kafka相關配置
atlas.notification.embedded=true # 如果要使用外部的kafka，則改為false
# 內嵌kafka會根據此端口啓動一個zk實例
atlas.kafka.zookeeper.connect=localhost:9026 # 如果使用外部kafka，則填寫外部zookeeper地址
atlas.kafka.bootstrap.servers=localhost:9027 # 如果使用外部kafka，則填寫外部broker server地址
```

## 測試


## 維運
```
$ cd ~/gordianknot/project/apache-atlas/apache-atlas-2.1.0
# 啟動
$ bin/atlas_start.py 

#檢查啟動結果
$ jps -m

# 停止
$ bin/atlas_stop.py 

# 登入
http://gordianknot:21000
名稱：admin 
密碼：admin

# 監聽端口
$ netstat -nltp
# Hbase 61530
# atlas.kafka.zookeeper.connect=localhost:9026
# atlas.kafka.bootstrap.servers=localhost:9027
# atlas web 21000
```

![1fd0571c5e43ee6ce48bd59682d25a53](imgs/ADE6828B-CED6-4A58-8026-FB835B8367D8.png)
![f149f1e17ba6cd969a62004681de69c4](imgs/D9524C8C-28EA-4423-9428-F9BE1041547C.png)
![28fe6670f4df5d2b8b6699093ce3a3fc](imgs/EEA9B959-1818-495B-8557-C7E1D04CF9E9.png)