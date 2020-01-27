# Docker-compose
##### 關於 Docker-Compose 的部份，之前有介紹過使用 docker run 指令就可以把 Docker Container 啟動起來，但是如果我們要啟動很多個 Docker Container 時，就需要輸入很多次 docker run 指令，另外 container 和 container 之間要做關聯的話也要記得它們之間要如何的連結(link) Container，這樣在要啟動多個 Container 的情況下，就會顯得比較麻煩。


### 參數
- Ubuntu 18.04   
- docker-compose version 1.24.0, build 0aa59064
    - 服務依賴
        - Docker
            - [安裝 Docker](../server/Docker)

## 開始


### 準備

#### 確認是否已安裝
```
$ docker-compose --version
```


#### 下載

##### Docker Compose
```
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```


### 配置

##### 修改權限 
```
$ /usr/local/bin
$ chmod 755 docker-compose
```

##### 驗證是否成功安裝
```
$ docker-compose --version
```

 
## 測試



## 維運



