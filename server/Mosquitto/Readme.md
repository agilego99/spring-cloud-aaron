# Mosquitto MQTT：

## 參數
- Ubuntu 18.04
- mosquitto version 1.4.15

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


## 測試
![5ba413f9da2f310c037446bae4549053](imgs/157EE2AB-CB81-4DF9-8C91-F1A979A26EE6.png)
```
$ mosquitto_sub -h 192.168.56.101 -t Sensor/Temperature/Room1
$ mosquitto_pub -h 192.168.56.101 -t Sensor/Temperature/Room1 -m "hello world"
```
![90177bcd38d66ee87b6afba1c82b05fa](imgs/04CA6687-67C4-4980-8921-4A1BF90014C1.png)

## 維運





