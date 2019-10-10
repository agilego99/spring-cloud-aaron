CREATE TABLE `house_info` (
  `id` bigint(64) NOT NULL,
  `city` varchar(20) NOT NULL COMMENT '城市',
  `region` varchar(20) NOT NULL COMMENT '區域',
  `name` varchar(20) NOT NULL COMMENT '名稱',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `lng` double NOT NULL COMMENT '經度',
  `lat` double NOT NULL COMMENT '緯度',
  `estate_type` varchar(10) NOT NULL COMMENT '物業類型',
  `house_num` varchar(10) NOT NULL COMMENT '樓棟',
  `room_num` varchar(10) NOT NULL COMMENT '室號',
  `floor` int(4) NOT NULL COMMENT '當前樓層',
  `height` int(4) NOT NULL COMMENT '總共層數',
  `area` double NOT NULL COMMENT '面積',
  `room_count` int(4) NOT NULL COMMENT '幾室',
  `hall_count` int(4) NOT NULL COMMENT '幾廳',
  `rent` int(4) NOT NULL COMMENT '租金,單位元',
  `purchase_date` datetime DEFAULT NULL COMMENT '購入時間',
  `purchase_price` int(4) NOT NULL COMMENT '購入價格,單位元',
  `price` int(4) NOT NULL COMMENT '評估單價,單位元',
  `total_price` int(4) NOT NULL COMMENT '評估總價,單位元',
  `eval_date` datetime NOT NULL COMMENT '評估時間',
  `live_status` varchar(10) NOT NULL COMMENT '居住狀態（自住,父母住,子女住,出租,空置）',
  `remove_status` int(4) NOT NULL COMMENT '是否刪除 0正常  1刪除',
  `uid` varchar(50) NOT NULL COMMENT '用戶ID',
  `eid` bigint(64) NOT NULL COMMENT '企業ID',
  `level` int(4) NOT NULL COMMENT '房產等級（1:豪宅級、2:公寓級、3:平房級、4:草屋級、5:山洞級）',
  `growth_rate` double NOT NULL COMMENT '復合增長率',
  `appreciation` int(4) NOT NULL COMMENT '房屋已升值',
  `rose_ranking` double NOT NULL COMMENT '均價排名漲幅',
  `avgprice_ranking` double NOT NULL COMMENT '均價排名均價',
  `rental_ratio` varchar(10) NOT NULL COMMENT '租售比（1:650）',
  `max_loan_money` double NOT NULL COMMENT '最高可貸,單位萬元',
  `tag` varchar(50) NOT NULL COMMENT '小區標籤,下環線分割',
  `liquidity_rating` int(4) NOT NULL COMMENT '流動性評級(0:0-20很差, 1:20-40較差, 2:40-60一般, 3:60-80良好 4:80-100很好)',
  `one_year_total_price` int(4) NOT NULL COMMENT '一年前的總價',
  `house_room_num` varchar(30) NOT NULL COMMENT '樓棟室號聯合字段',
  `lease_start_date` date DEFAULT NULL COMMENT '起租日期',
  `lease_end_date` date DEFAULT NULL COMMENT '到期日期',
  `rent_cycle` int(4) DEFAULT NULL COMMENT '收租週期',
  `rent_cycle_unit` char(1) DEFAULT NULL COMMENT '收租週期單位(年，季，月)',
  `last_rent_date` date DEFAULT NULL COMMENT '下次收租日期',
  `min_sell_price` int(4) DEFAULT NULL COMMENT '城市相對最低均價',
  `max_sell_price` int(4) DEFAULT NULL COMMENT '城市相對最高均價',
  `total_score` double DEFAULT NULL COMMENT '流動性評級得分',
  `min_advice_value` double DEFAULT NULL COMMENT '掛牌建議總價下限',
  `max_advice_value` double DEFAULT NULL COMMENT '掛牌建議總價上限',
  `add_date` datetime NOT NULL COMMENT '添加時間',
  PRIMARY KEY (`id`,`remove_status`),
  KEY `eid_uid_status_idx` (`eid`,`uid`,`remove_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的房產信息表'
