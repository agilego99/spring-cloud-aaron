# ELK(docker-compose)
##### Elastic static 架構

### 參數
- CentOS 7.5
- Elasticsearch 6.5.4
- Kibana 7.5.0
- Logstash 7.5.0
- Filebeat 7.5.0

### 安裝
#### 建立工作目錄
```
$ mkdir -p $(pwd)/elk
```
#### docker-compose（如果已安裝則可跳過）
```
$sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
# 對二進位制檔案應用可執行許可權
$ sudo chmod  +x /usr/local/bin/docker-compose
# 測試安裝
$ docker-compose --version
```
#### ELK
```
$ cd $(pwd)/elk
$ git clone https://github.com/deviantony/docker-elk.git
$ cd $(pwd)/elk/docker-elk
$ docker-compose up -d
```


### 配置
#### logstash
```
$ nano /home/aaron/elk/docker-elk/logstash/pipeline/logstash.conf
# 內容如下：
input{
      	tcp {
             	mode => "server"
                port => 5000
                codec => json_lines
                tags => ["java-springboot"]
        }
}
filter{
    json{
	source => "message"
        remove_field => ["message"]
    }
}
output{
    if "java-springboot" in [tags]{
        elasticsearch{
                hosts=> ["elasticsearch:9200"] 
                index => "java-springboot-%{+YYYY.MM.dd}"
                }
        stdout{codec => rubydebug}
    }
}
# 修改後需要重啟
$ docker-compose restart
```
#### 關閉 xpack ：設為 false
```
$ nano /home/aaron/elk/docker-elk/elasticsearch/config/elasticsearch.yml
$ nano /home/aaron/elk/docker-elk/kibana/config/kibana.yml
$ nano /home/aaron/elk/docker-elk/logstash/config/logstash.yml
```

### 維運

#### Elasticsearch
##### 測試是否成功運行
```
$ curl -X GET "localhost:9200"
```
![b23ed2720cb13d5db490cbfda52f2f8f](imgs/4EB1AF28-4E6C-49CF-955A-1D563C2098AC.png)
#### Logstash
```
# 等相關服務重啟後，可透過指令確認與 Elasticsearch是否連線正常
$ docker logs -f docker-elk_logstash_1
```
#### Kibana
##### 測試是否成功運行
`http://ip:5601`

#### docke 指令
```
# 顯示 docker network inspect 虛擬網路
$ docker network inspect elastic_stack [虛擬私有網路名稱]

# 顯示 docker 的 images 清單
docker images 

# 透過 iamge 執行並產生一個新的 container
$ docker run [Image 名稱]:[Image 版本] [執行指令]

# 查看正在執行的 containers
$ docker ps

# 查看所有的 containers
$ docker ps -a

# 查詢正在執行的 container
$ docker ps
$ docker exec -i -t [Container ID] bash
$ exit

# 移除這個「tomcat8080」容器
$ docker rm containner-id

##將所有 Container 殺掉
$ docker kill $(docker ps -q)

# 將所有 Container 移除
$ docker rm $(docker ps -a -q)

# 删除所有的镜像
$ docker rmi $(docker images -q)
```
