# Mosquitto MQTT：

## 參數
- Ubuntu 18.04
- mosquitto version 1.4.15
- MQTT.fx 1.7.1

## 開始

### 準備
```
$ sudo apt update
$ sudo apt-get upgrade
```

### 安裝
```
$ sudo apt install -y mosquitto
$ sudo apt install -y mosquitto-clients
```

### 配置

##### 圖型化 MQTT.fx 工具
![8673dd8fd1061ef9012dfcf917c28738](imgs/2E74C796-9A5B-463A-A88B-B51727106685.png)

## 測試
![5ba413f9da2f310c037446bae4549053](imgs/CBFD914F-143A-4DB2-9158-84A0AD83D1D8.png)
```
$ mosquitto_sub -h 192.168.56.101 -t Sensor/Temperature/Room1
$ mosquitto_pub -h 192.168.56.101 -t Sensor/Temperature/Room1 -m "hello world"
```
![90177bcd38d66ee87b6afba1c82b05fa](imgs/FAB30A50-A5D0-4653-9F7B-7E9E793AD935.png)

## 維運





