# Docker


### 參數
- Ubuntu 18.04   
- Docker version 19.03.5, build 633a0ea838

## 開始


### 準備

```
# 移除較老的版本
$ sudo apt-get remove docker docker-engine docker.io containerd runc
$ sudo apt-get update
```

#### 下載

##### 下載依賴工具
```
sudo apt-get install \
apt-transport-https \
ca-certificates \
curl \
gnupg-agent \
software-properties-common
```

##### 官方 GPG 秘鑰操作
```
# 添加
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# 校驗
$ sudo apt-key fingerprint 0EBFCD88
```

##### 設置倉庫類型（採用穩定版）
```
$ sudo add-apt-repository \
"deb [arch=amd64] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) \
stable"
```

### 安裝
#### Docker 與 Container

```
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
# 驗證是否成功安裝
$ sudo docker run hello-world
```


### 配置

### 添加非 root 用戶
```
# 需要登出再登入才會生效
$ sudo usermod -aG docker $USER
```

 
## 測試

### 通過 hello-world 鏡像驗證 Docker 是否成功安裝
```
$ sudo docker run hello-world
```


## 維運
```
# List Docker CLI commands
$ docker
$ docker container --help

## Display Docker version and info
$ docker --version
$ docker version
$ docker info

## Execute Docker image
$ docker run hello-world

## List Docker images
$ docker image ls

## List Docker containers (running, all, all in quiet mode)
$ docker container ls
$ docker container ls --all
$ docker container ls -aq
```
