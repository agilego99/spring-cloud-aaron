# Redis：記憶體資料庫

## 參數
- Ubuntu 18.04
- redis-cli 4.0.9

## 開始

### 準備
```
$ sudo apt update
$ sudo apt-get upgrade
```

### 安裝
`$ sudo apt install redis-server`

### 配置

```
$ sudo nano /etc/redis/redis.conf
bind 192.168.56.101
protected-mode no
daemonize yes
requirepass 999999
supervised systemdimgs
\wq

# 重啟
```

## 測試
`$ redis-cli -a 999999`

## 維運
```
$ sudo systemctl stop redis
$ sudo systemctl start redis
$ sudo systemctl restart redis
$ sudo systemctl status redis
$ sudo systemctl disable redis
$ netstat -apn | grep 6379
```
![aca3f69bc7ecd656648b6605ad6d8f31](imgs/54E2F62D-D9DB-4331-B8DF-52A4EA505D07.png)
![91940305c814d9ff9fbacbbb668ebb44](imgs/0C928B83-1B24-445F-AF55-507FB07C1C8B.png)




