ECK_WiWis_API_specification
===

# 目錄
<!-- TOC -->

- [目錄](#目錄)
- [共用HTTP狀態碼](#共用http狀態碼)
- [分頁規則](#分頁規則)
    - [1.offset-limit base (Web用)](#1offset-limit-base-web用)
    - [2.cursored base (App用)](#2cursored-base-app用)
- [APIs](#apis)
    - [1. list](#1-list)
        - [1.1. GET /list/divisions](#11-get-listdivisions)
        - [1.2. GET /list/divisions/{division-no}/visiting-staffs](#12-get-listdivisionsdivision-novisiting-staffs)
        - [1.3. GET /list/health-behavior-histories](#13-get-listhealth-behavior-histories)
        - [1.4. GET /list/medical-histories](#14-get-listmedical-histories)
        - [1.5. GET /list/work-item-sort-items](#15-get-listwork-item-sort-items)
        - [1.6. GET /list/care-item-sort-items](#16-get-listcare-item-sort-items)
        - [1.7. GET /list/patient-status](#17-get-listpatient-status)
        - [1.8. GET /list/organizations](#18-get-listorganizations)
        - [1.9. GET /list/ointments](#19-get-listointments)
        - [1.10. GET /list/dressings](#110-get-listdressings)
        - [1.11. GET /list/medical-materials](#111-get-listmedical-materials)
        - [1.12. GET /list/wound-treatments](#112-get-listwound-treatments)
        - [1.13. GET /list/positions](#113-get-listpositions)
        - [1.14. GET /list/employees](#114-get-listemployees)
        - [1.15. GET /list/wound-sub-parts](#115-get-listwound-sub-parts)
        - [1.16. GET /list/divisions/current-consultant-level/{current-consultant-level}](#116-get-listdivisionscurrent-consultant-levelcurrent-consultant-level)
        - [1.17. GET /list/positions/current-consultant-level/{current-consultant-level}](#117-get-listpositionscurrent-consultant-levelcurrent-consultant-level)
        - [1.18. GET /list/employees/current-consultant-level/{current-consultant-level}](#118-get-listemployeescurrent-consultant-levelcurrent-consultant-level)
        - [1.19. GET /list/consultation-sort-items/be-consulted](#119-get-listconsultation-sort-itemsbe-consulted)
        - [1.20. GET /list/consultation-sort-items/consult](#120-get-listconsultation-sort-itemsconsult)
        - [1.21. GET /list/his/employees](#121-get-listhisemployees)
        - [1.22. GET /list/consultants/consultation-window](#122-get-listconsultantsconsultation-window)
        - [1.23. GET /list/long-term-care-institutions/{organization-id}/dressings/data-source](#123-get-listlong-term-care-institutionsorganization-iddressingsdata-source)
    - [2. charts](#2-charts)
        - [2.1. GET /organizations/{organization-id}/charts/web](#21-get-organizationsorganization-idchartsweb)
        - [2.2. GET /organizations/{organization-id}/charts/app](#22-get-organizationsorganization-idchartsapp)
        - [2.3. GET /his/charts/app](#23-get-hischartsapp)
        - [2.4. POST /organizations/{organization-id}/charts](#24-post-organizationsorganization-idcharts)
        - [2.5. GET /organizations/{organization-id}/charts/{chart-id}](#25-get-organizationsorganization-idchartschart-id)
        - [2.6. PUT /organizations/{organization-id}/charts/{chart-id}](#26-put-organizationsorganization-idchartschart-id)
        - [2.7. POST /organizations/{organization-id}/charts/unknown](#27-post-organizationsorganization-idchartsunknown)
        - [2.8. PUT /organizations/{organization-id}/charts/{charts-id}/unknown](#28-put-organizationsorganization-idchartscharts-idunknown)
        - [2.9. GET /organizations/{organization-id}/charts/patient-id-no/{patient-id-no}](#29-get-organizationsorganization-idchartspatient-id-nopatient-id-no)
        - [2.10. GET /organizations/{organization-id}/charts/chart-no/{chart-no}](#210-get-organizationsorganization-idchartschart-nochart-no)
        - [2.11. PUT /organizations/{organization-id}/charts/{chart-id}/is_delete](#211-put-organizationsorganization-idchartschart-idis_delete)
        - [2.12. POST /organizations/{organization-id}/charts/import](#212-post-organizationsorganization-idchartsimport)
        - [2.13. GET /organizations/{organization-id}/charts/export](#213-get-organizationsorganization-idchartsexport)
        - [2.14. GET /organizations/{organization-id}/charts/chart-no/{chart-no}/scan](#214-get-organizationsorganization-idchartschart-nochart-noscan)
    - [3. work-items](#3-work-items)
        - [3.1. GET /users/{user-id}/work-items](#31-get-usersuser-idwork-items)
        - [3.2. POST /users/{user-id}/work-items](#32-post-usersuser-idwork-items)
        - [3.3. DELETE /users/{user-id}/work-items/{work-item-id}](#33-delete-usersuser-idwork-itemswork-item-id)
    - [4. care-items](#4-care-items)
        - [4.1. GET /users/{user-id}/care-items](#41-get-usersuser-idcare-items)
        - [4.2. POST /users/{user-id}/care-items](#42-post-usersuser-idcare-items)
        - [4.3. DELETE /users/{user-id}/care-items/{care-item-id}](#43-delete-usersuser-idcare-itemscare-item-id)
    - [5. wounds](#5-wounds)
        - [5.1. GET /users/{user-id}/wounds/unfinished-count](#51-get-usersuser-idwoundsunfinished-count)
        - [5.2. GET /organizations/{organization-id}/charts/{chart-id}/wounds](#52-get-organizationsorganization-idchartschart-idwounds)
        - [5.3. GET /organizations/{organization-id}/charts/{chart-id}/wounds/part-types](#53-get-organizationsorganization-idchartschart-idwoundspart-types)
        - [5.4. GET /organizations/{organization-id}/charts/{chart-id}/wounds/{chart-wounds-id}](#54-get-organizationsorganization-idchartschart-idwoundschart-wounds-id)
        - [5.5. POST /organizations/{organization-id}/charts/{chart-id}/wounds](#55-post-organizationsorganization-idchartschart-idwounds)
        - [5.6. PUT /wounds/{chart-wounds-id}/size](#56-put-woundschart-wounds-idsize)
        - [5.7. DELETE /wounds/{chart-wounds-id}](#57-delete-woundschart-wounds-id)
        - [5.8. PUT /wounds/{chart-wounds-id}/part-and-change-dressing](#58-put-woundschart-wounds-idpart-and-change-dressing)
        - [5.9. GET /organizations/{organization-id}/charts/{chart-id}/wounds/chart-wound-sub-part-combination/{chart-wound-sub-part-combination}](#59-get-organizationsorganization-idchartschart-idwoundschart-wound-sub-part-combinationchart-wound-sub-part-combination)
        - [5.10. GET /organizations/{organization-id}/charts/{chart-id}/wounds/chart-wound-sub-part-combination/{chart-wound-sub-part-combination}/newest-wounds-dressing-record](#510-get-organizationsorganization-idchartschart-idwoundschart-wound-sub-part-combinationchart-wound-sub-part-combinationnewest-wounds-dressing-record)
        - [5.11. GET /organizations/{organization-id}/charts/{chart-id}/wounds/exist-parts](#511-get-organizationsorganization-idchartschart-idwoundsexist-parts)
        - [5.12. POST /organizations/{organization-id}/charts/{chart-id}/wounds/image](#512-post-organizationsorganization-idchartschart-idwoundsimage)
    - [6. health-behavior-histories](#6-health-behavior-histories)
        - [6.1. GET /health-behavior-histories](#61-get-health-behavior-histories)
        - [6.2. POST /health-behavior-histories](#62-post-health-behavior-histories)
        - [6.3. PUT /health-behavior-histories/{health-behavior-history-id}](#63-put-health-behavior-historieshealth-behavior-history-id)
        - [6.4. PUT /organizations/{organization-id}/health-behavior-histories/index](#64-put-organizationsorganization-idhealth-behavior-historiesindex)
        - [6.5. PUT /health-behavior-histories/{health-behavior-history-id}/is-delete](#65-put-health-behavior-historieshealth-behavior-history-idis-delete)
    - [7. medical-histories](#7-medical-histories)
        - [7.1. GET /medical-histories](#71-get-medical-histories)
        - [7.2. POST /medical-histories](#72-post-medical-histories)
        - [7.3. PUT /medical-histories/{medical-history-id}](#73-put-medical-historiesmedical-history-id)
        - [7.4. PUT /organizations/{organization-id}/medical-histories/index](#74-put-organizationsorganization-idmedical-historiesindex)
        - [7.5. PUT /medical-histories/{medical-history-id}/is-delete](#75-put-medical-historiesmedical-history-idis-delete)
    - [8. ointments](#8-ointments)
        - [8.1. GET /ointments](#81-get-ointments)
        - [8.2. POST /ointments](#82-post-ointments)
        - [8.3. PUT /ointments/{ointment-id}](#83-put-ointmentsointment-id)
        - [8.4. PUT /organizations/{organization-id}/ointments/index](#84-put-organizationsorganization-idointmentsindex)
        - [8.5. PUT /ointments/{ointment-id}/is-delete](#85-put-ointmentsointment-idis-delete)
    - [9. dressings](#9-dressings)
        - [9.1. GET /dressings](#91-get-dressings)
        - [9.2. POST /dressings](#92-post-dressings)
        - [9.3. PUT /dressings/{dressings-id}](#93-put-dressingsdressings-id)
        - [9.4. PUT /organizations/{organization-id}/dressings/index](#94-put-organizationsorganization-iddressingsindex)
        - [9.5. PUT /dressings/{dressings-id}/is-delete](#95-put-dressingsdressings-idis-delete)
        - [9.6. POST /organizations/{organization-id}/dressings/import](#96-post-organizationsorganization-iddressingsimport)
        - [9.7. GET /organizations/{organization-id}/dressings/export](#97-get-organizationsorganization-iddressingsexport)
    - [10. medical-materials](#10-medical-materials)
        - [10.1. GET /medical-materials](#101-get-medical-materials)
        - [10.2. POST /medical-materials](#102-post-medical-materials)
        - [10.3. PUT /medical-materials/{medical-material-id}](#103-put-medical-materialsmedical-material-id)
        - [10.4. PUT /organizations/{organization-id}/medical-materials/index](#104-put-organizationsorganization-idmedical-materialsindex)
        - [10.5. PUT /medical-materials/{medical-material-id}/is-delete](#105-put-medical-materialsmedical-material-idis-delete)
    - [11. wound-treatments](#11-wound-treatments)
        - [11.1. GET /wound-treatments](#111-get-wound-treatments)
        - [11.2. POST /wound-treatments](#112-post-wound-treatments)
        - [11.3. PUT /wound-treatments/{wound-treatment-id}](#113-put-wound-treatmentswound-treatment-id)
        - [11.4. PUT /organizations/{organization-id}/wound-treatments/index](#114-put-organizationsorganization-idwound-treatmentsindex)
        - [11.5. PUT /wound-treatments/{wound-treatment-id}/is-delete](#115-put-wound-treatmentswound-treatment-idis-delete)
    - [12. consultants](#12-consultants)
        - [12.1. GET /consultants](#121-get-consultants)
        - [12.2. POST /consultants](#122-post-consultants)
        - [12.3. DELETE /consultants/{consultant-id}](#123-delete-consultantsconsultant-id)
        - [12.4. GET /users/{user-id}/consultants](#124-get-usersuser-idconsultants)
    - [13. consultations](#13-consultations)
        - [13.1. GET /users/{user-id}/consultations/be-consulted](#131-get-usersuser-idconsultationsbe-consulted)
        - [13.2. PUT /users/{user-id}/consultations/{consultation-id}](#132-put-usersuser-idconsultationsconsultation-id)
        - [13.3. GET /wounds/{chart-wound-id}/consultations](#133-get-woundschart-wound-idconsultations)
        - [13.4. GET /wounds/{chart-wound-id}/consultations/consultants](#134-get-woundschart-wound-idconsultationsconsultants)
        - [13.5. POST /wounds/{chart-wound-id}/consultations/visiting-staff](#135-post-woundschart-wound-idconsultationsvisiting-staff)
        - [13.6. POST /wounds/{chart-wound-id}/consultations/other-consultants](#136-post-woundschart-wound-idconsultationsother-consultants)
        - [13.7. POST /users/{user-id}/consultation_board_members](#137-post-usersuser-idconsultation_board_members)
        - [13.8. POST /consultations/{consultation-id}/consultation_dialogs](#138-post-consultationsconsultation-idconsultation_dialogs)
        - [13.9. GET /users/{user-id}/consultations/consult](#139-get-usersuser-idconsultationsconsult)
        - [13.10. POST /wounds/{chart-wound-id}/consultations/consultation-window](#140-post-woundschart-wound-idconsultationsconsultation-window)
    - [14. content-service](#14-content-service)
        - [14.1. GET /content-service/upload-url/wound-image](#141-get-content-serviceupload-urlwound-image)
        - [14.2. PUT {url of 14.1's result}](#142-put-url-of-141s-result)
        - [14.3. GET /content-service/upload-url/dressing-image](#143-get-content-serviceupload-urldressing-image)
        - [14.4. DELETE /content-service](#144-delete-content-service)
        - [14.5. GET /content-service](#145-get-content-service)
    - [15. frequently-sentences](#15-frequently-sentences)
        - [15.1. GET /users/{user-id}/frequently-sentences](#151-get-usersuser-idfrequently-sentences)
        - [15.2. POST /users/{user-id}/frequently-sentences](#152-post-usersuser-idfrequently-sentences)
    - [16. users](#16-users)
        - [16.1. GET /users](#161-get-users)
        - [16.2. POST /users](#162-post-users)
        - [16.3. DELETE /users/{user-id}/roles/{role-code}/mapping](#163-delete-usersuser-idrolesrole-codemapping)
        - [16.4. GET /organizations/{organization-id}/users](#164-get-organizationsorganization-idusers)
        - [16.5. POST /organizations/{organization-id}/users](#165-post-organizationsorganization-idusers)
        - [16.6. DELETE /organizations/{organization-id}/users/{user-id}/roles/{role-code}/mapping](#166-delete-organizationsorganization-idusersuser-idrolesrole-codemapping)
        - [16.7. PUT /organizations/{organization-id}/users/{user-id}/password](#167-put-organizationsorganization-idusersuser-idpassword)
    - [17. system](#17-system)
        - [17.1. POST /system/log-in](#171-post-systemlog-in)
        - [17.2. POST /system/log-out](#172-post-systemlog-out)
    - [18. daypass-patients](#18-daypass-patients)
        - [18.1. GET /daypass-patients](#181-get-daypass-patients)
        - [18.2. POST /daypass-patients](#182-post-daypass-patients)
        - [18.3. PUT /daypass-patients/{daypass-patient-id}/effective-period](#183-put-daypass-patientsdaypass-patient-ideffective-period)
        - [18.4. PUT /daypass-patients/{daypass-patient-id}/password](#184-put-daypass-patientsdaypass-patient-idpassword)
        - [18.5. PUT /daypass-patients/{daypass-patient-id}/is_delete](#185-put-daypass-patientsdaypass-patient-idis_delete)
    - [19. long-term-care-institutions](#19-long-term-care-institutions)
        - [19.1. GET /long-term-care-institutions](#191-get-long-term-care-institutions)
        - [19.2. POST /long-term-care-institutions](#192-post-long-term-care-institutions)
        - [19.3. PUT /long-term-care-institutions/{long-term-care-institution-id}](#193-put-long-term-care-institutionslong-term-care-institution-id)
        - [19.4. PUT /long-term-care-institutions/{long-term-care-institution-id}/password](#194-put-long-term-care-institutionslong-term-care-institution-idpassword)
        - [19.5. PUT /long-term-care-institutions/{long-term-care-institution-id}/is_delete](#195-put-long-term-care-institutionslong-term-care-institution-idis_delete)
        - [19.6. PUT /long-term-care-institutions/{long-term-care-institution-id}/is-using-eck-dressing](#196-put-long-term-care-institutionslong-term-care-institution-idis-using-eck-dressing)
    - [20. care-item-follow-ups](#20-care-item-follow-ups)
        - [20.1. GET /users/{user-id}/care-item-follow-ups](#201-get-usersuser-idcare-item-follow-ups)

<!-- /TOC -->

<br>
<br>
<br>
<br>

# 共用HTTP狀態碼

|HTTP狀態碼|說明|
|:-|:-|
|200|1. 查詢成功<br>2.修改成功|
|201|新增成功|
|204|1. 查詢請求成功但是沒任何資料可以返回<br>2. 刪除成功|
|400|1. 資料驗證錯誤 (e.g. 要int給string)(錯誤參數格式導致SQL會error)<br>2. 新增失敗 (e.g. duplicate)<br>3. 修改失敗 (e.g. key找不到)<br>4. 刪除失敗 (e.g. key找不到)<br>5. 已知並可以處理的exception (任何可以預先想到的錯誤)|
|401|Authorization驗證失敗|
|403|Authorization驗證成功但沒有存取權限|
|404|1. API URL找不到<br>2. 檔案找不到 (直接存取檔案位置的API)|
|412|API Header/Body 格式錯誤 (e.g. 參數名稱/數量錯誤)|
|500|伺服器內部錯誤 (包含所有沒catch到的exception)|

<br>
<br>
<br>
<br>

# 分頁規則

## 1.offset-limit base (Web用)

**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "data_column1": "xxx",
      "data_column2": "ooo"
    },
    {
      "index": 1,
      "data_column1": "aaa",
      "data_column2": "bbb"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)||
|data_column1||原始資料1||
|data_column2||原始資料2||

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

## 2.cursored base (App用)

**Request URL Parameters**

first 搭配 after
last 搭配 before
兩組不會同時出現

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "data_column1": "xxx",
      "data_column2": "ooo"
    },
    {
      "cursor": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "data_column1": "aaa",
      "data_column2": "bbb"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor||
|data_column1||資料1||
|data_column2||資料2||

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

# APIs

## 1. list

### 1.1. GET /list/divisions

|欄位|描述|
|:---|:---|
|Description|查詢科別下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->新增病患->從HIS新增病患|

**Note**

查詢HIS系統

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "division_id": "0d5db08a-897d-4399-83aa-499fb6001218",
    "division_no": "S001",
    "division_name": "一般外科"
  },
  {
    "division_id": "6c245172-329f-4ed7-bdf3-f77498afd72e",
    "division_no": "S002",
    "division_name": "骨科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|division_id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|

<br>
<br>
<br>
<br>

### 1.2. GET /list/divisions/{division-no}/visiting-staffs

|欄位|描述|
|:---|:---|
|Description|查詢主治醫師下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->新增病患->從HIS新增病患|

**Note**

查詢HIS系統

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|division-no|string|科別|S001|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "visiting_staff_no": "00123",
    "visiting_staff_name": "吳Ｏ明"
  },
  {
    "visiting_staff_no": "00234",
    "visiting_staff_name": "陳Ｏ誠"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|

<br>
<br>
<br>
<br>

### 1.3. GET /list/health-behavior-histories

|欄位|描述|
|:---|:---|
|Description|查詢個人史下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->新增病患->掃碼新增<br>App->工作清單/關懷清單->新增病患->手動新增|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "health_behavior_history_id": 1,
    "health_behavior_history_description": "抽煙"
  },
  {
    "health_behavior_history_id": 2,
    "health_behavior_history_description": "喝酒"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

<br>
<br>
<br>
<br>

### 1.4. GET /list/medical-histories

|欄位|描述|
|:---|:---|
|Description|查詢疾病史下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->新增病患->掃碼新增<br>App->工作清單/關懷清單->新增病患->手動新增|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_history_id": 1,
    "medical_history_description": "心臟病"
  },
  {
    "medical_history_id": 2,
    "medical_history_description": "高血壓"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|

<br>
<br>
<br>
<br>

### 1.5. GET /list/work-item-sort-items

|欄位|描述|
|:---|:---|
|Description|查詢工作清單排序下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "work_item_sort_item_value": "hospital_bed_no",
    "work_item_sort_item_description": "床號"
  },
  {
    "work_item_sort_item_value": "unfinished",
    "work_item_sort_item_description": "未完成"
  },
  {
    "work_item_sort_item_value": "last_update_time",
    "work_item_sort_item_description": "更新時間"
  },
  {
    "work_item_sort_item_value": "care_items",
    "work_item_sort_item_description": "關懷清單"
  },
  {
    "work_item_sort_item_value": "admission",
    "work_item_sort_item_description": "住院"
  },
  {
    "work_item_sort_item_value": "day_pass",
    "work_item_sort_item_description": "DayPass"
  },
  {
    "work_item_sort_item_value": "home_care",
    "work_item_sort_item_description": "居家照護"
  },
  {
    "work_item_sort_item_value": "discharge",
    "work_item_sort_item_description": "出院"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|work_item_sort_item_value|string|工作清單下拉選單代碼|hospital_bed_no<br>unfinished<br>last_update_time<br>care_items<br>admission<br>day_pass<br>home_care<br>discharge|
|work_item_sort_item_description|string|工作清單下拉選單說明|床號<br>未完成<br>更新時間<br>關懷清單<br>住院<br>DayPass<br>居家照護<br>出院|

<br>
<br>
<br>
<br>

### 1.6. GET /list/care-item-sort-items

|欄位|描述|
|:---|:---|
|Description|查詢關懷清單排序下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->關懷清單|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "care_item_sort_item_value": "hospital_bed_no",
    "care_item_sort_item_description": "床號"
  },
  {
    "care_item_sort_item_value": "unfinished",
    "care_item_sort_item_description": "未完成"
  },
  {
    "care_item_sort_item_value": "last_update_time",
    "care_item_sort_item_description": "更新時間"
  },
  {
    "care_item_sort_item_value": "work_items",
    "care_item_sort_item_description": "工作清單"
  },
  {
    "care_item_sort_item_value": "admission",
    "care_item_sort_item_description": "住院"
  },
  {
    "care_item_sort_item_value": "day_pass",
    "care_item_sort_item_description": "DayPass"
  },
  {
    "care_item_sort_item_value": "home_care",
    "care_item_sort_item_description": "居家照護"
  },
  {
    "care_item_sort_item_value": "discharge",
    "care_item_sort_item_description": "出院"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|care_item_sort_item_value|string|關懷清單下拉選單代碼|hospital_bed_no<br>unfinished<br>last_update_time<br>work_items<br>admission<br>day_pass<br>home_care<br>discharge|
|care_item_sort_item_description|string|關懷清單下拉選單說明|床號<br>未完成<br>更新時間<br>工作清單<br>住院<br>DayPass<br>居家照護<br>出院|

<br>
<br>
<br>
<br>

### 1.7. GET /list/patient-status

|欄位|描述|
|:---|:---|
|Description|查詢病患狀態下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->新增病患->手動新增|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "patient_status_value": "admission",
    "patient_status_description": "住院中"
  },
  {
    "patient_status_value": "day_pass",
    "patient_status_description": "DayPass"
  },
  {
    "patient_status_value": "home_care",
    "patient_status_description": "居家照護"
  },
  {
    "patient_status_value": "discharge",
    "patient_status_description": "出院"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|patient_status_value|string|病患狀態下拉選單代碼|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態下拉選單說明|住院中<br>DayPass<br>居家照護<br>出院|

<br>
<br>
<br>
<br>

### 1.8. GET /list/organizations

|欄位|描述|
|:---|:---|
|Description|查詢組織下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|Web->病患管理|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "organization_id": "eck",
    "organization_description": "本院"
  },
  {
    "organization_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
    "organization_description": "安心照護長照機構"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|organization_id|string|組織下拉選單ID<br>(恩主公本院資料用eck表示)|eck<br>98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|organization_description|string|組織下拉選單說明|本院<br>安心照護長照機構|
<br>
<br>
<br>
<br>

### 1.9. GET /list/ointments

|欄位|描述|
|:---|:---|
|Description|查詢藥膏下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口換藥紀錄|

**Note**

- 查詢is_delete=false的
- 按照index排序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "ointment_id": 1,
    "ointment_name": "除疤凝膠"
  },
  {
    "ointment_id": 2,
    "ointment_name": "抗生素藥膏"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

<br>
<br>
<br>
<br>

### 1.10. GET /list/dressings

|欄位|描述|
|:---|:---|
|Description|查詢敷料下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口換藥紀錄|

**Note**

- 查詢is_show_description=true的
- 查詢is_delete=false的
- 按照index排序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "dressing_id": 1,
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "images": [
      {
        "image_url": "http://xxx/yy",
        "image_thumbnail_url": "http://xxx/yyy"
      },
      {
        "image_url": "http://xxx/yyyy",
        "image_thumbnail_url": "http://xxx/yyyyy"
      }
    ],
    "note_1": "",
    "note_2": ""
  },
  {
    "dressing_id": 2,
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "images": [
      {
        "image_url": "http://xxx/zz",
        "image_thumbnail_url": "http://xxx/zzz"
      },
      {
        "image_url": "http://xxx/zzzz",
        "image_thumbnail_url": "http://xxx/zzzzzz"
      }
    ],
    "note_1": "",
    "note_2": ""
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料名稱|抗菌紗布|
|dressing_advantage|string|敷料適用時機與優點|適用於各種傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|note_1|string|備註一||
|note_2|string|備註二||

*image*
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|image_url|string|原始圖片URL|http://xxx/yy|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|

<br>
<br>
<br>
<br>

### 1.11. GET /list/medical-materials

|欄位|描述|
|:---|:---|
|Description|查詢特殊器材下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口換藥紀錄|

**Note**

- 查詢is_delete=false的
- 按照index排序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_material_id": 1,
    "medical_material_name": "Vacuum"
  },
  {
    "medical_material_id": 2,
    "medical_material_name": "注射器"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|Vacuum|

<br>
<br>
<br>
<br>

### 1.12. GET /list/wound-treatments

|欄位|描述|
|:---|:---|
|Description|查詢現行處置下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口換藥紀錄|

**Note**

- 查詢is_delete=false的
- 按照index排序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_treatment_id": 1,
    "wound_treatment_description": "吸收治療"
  },
  {
    "wound_treatment_id": 2,
    "wound_treatment_description": "電燒燒灼"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置名稱|吸收治療|

<br>
<br>
<br>
<br>

### 1.13. GET /list/positions

|欄位|描述|
|:---|:---|
|Description|查詢職稱下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|Web->諮詢設定管理->新增|

**Note**

- 查詢職稱資料表(ECK案共用)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "position_id": "b0a4e5d0-3fc1-4e17-a18e-673e94056501",
    "position_description": "醫師"
  },
  {
    "position_id": "c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e",
    "position_description": "護理師"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|position_id|uuid|職稱|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|
|position_description|string|職稱說明|護理師|

<br>
<br>
<br>
<br>

### 1.14. GET /list/employees

|欄位|描述|
|:---|:---|
|Description|查詢姓名下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|Web->諮詢設定管理->新增|

**Note**

- 查詢職稱資料表(ECK案共用)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|division-id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|option|position-id|uuid|職稱ID|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "user_id": "91284ee6-a610-4fa5-807c-f4db8638060a",
    "employee_id": "1986a375-6019-491e-8477-d36f7a232651",
    "employee_name": "陳Ｏ翔",
    "employee_description": "陳Ｏ翔 醫師 心臟外科"
  },
  {
    "user_id": "e4eda82b-4ddc-432c-bffd-86aac5e63b23",
    "employee_id": "16353161-5da3-4d14-8aab-e888dbe8a2bc",
    "employee_name": "鄭Ｏ河",
    "employee_description": "鄭Ｏ河 醫師 心臟外科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|user_id|uuid|使用者ID|91284ee6-a610-4fa5-807c-f4db8638060a|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|employee_name|string|員工姓名|陳Ｏ翔|
|employee_description|string|員工姓名|陳Ｏ翔 醫師 心臟外科|

<br>
<br>
<br>
<br>

### 1.15. GET /list/wound-sub-parts

|欄位|描述|
|:---|:---|
|Description|查詢傷口子部位(鄰近部位)|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->工作清單/關懷清單->傷口列表->傷口詳細資訊->傷口部位|

**Note**


***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_part_id": 1,
    "wound_part_code": "hb_f_head_c",
    "wound_part_description": "頭部",
    "wound_sub_parts": [
      {
        "wound_sub_part_id": 1,
        "wound_sub_part_code": "hb_f_head_c_1",
        "wound_sub_part_description": "右上頭頂",
        "equal_wound_sub_part_code": "hb_b_head_c_1",
        "near_wound_parts": [
          {
            "near_wound_part_code": "hb_b_head_c"
          }
        ],
        "near_wound_sub_parts": [
          {
            "near_wound_sub_part_code": "hb_b_head_c_2"
          },
          {
            "near_wound_sub_part_code": "hb_b_head_c_4"
          }
        ]
      },
      {
        "wound_sub_part_id": 2,
        "wound_sub_part_code": "hb_f_head_c_2",
        "wound_sub_part_description": "左上頭頂",
        "equal_wound_sub_part_code": "hb_b_head_c_2",
        "near_wound_parts": [
          {
            "near_wound_part_code": "hb_b_head_c"
          }
        ],
        "near_wound_sub_parts": [
          {
            "near_wound_sub_part_code": "hb_b_head_c_4"
          },
          {
            "near_wound_sub_part_code": "hb_b_head_c_3"
          }
        ]
      }
    ]
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_part_id|int|傷口部位ID|1|
|wound_part_code|string|傷口部位代碼|hb_f_head_c|
|wound_part_description|string|傷口部位說明|頭部|
|wound_sub_parts|array of wound_sub_part|傷口子部位||

*wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_sub_part_id|int|傷口子部位ID|1|
|wound_sub_part_code|string|傷口子部位代碼|hb_f_head_c_1|
|wound_sub_part_description|string|傷口子部位說明|右上頭頂|
|equal_wound_sub_part_code|string|相等的傷口子部位代碼|hb_b_head_c_1|
|near_wound_parts|array of near_wound_part|鄰近傷口部位||
|near_wound_sub_parts|array of near_wound_sub_part|鄰近傷口子部位||

*near_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|near_wound_part_code|string|鄰近傷口部位代碼|hb_b_head_c|

*near_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_sub_part_code|string|鄰近傷口子部位代碼|hb_b_head_c_2|

<br>
<br>
<br>
<br>

### 1.16. GET /list/divisions/current-consultant-level/{current-consultant-level}

|欄位|描述|
|:---|:---|
|Description|查詢向上諮詢科別下拉選單|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|App->工作清單/關懷清單->新增病患->從HIS新增病患|

**Note**
- 取得當前諮詢層級上一階已被設定的諮詢對象所包含的科別

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|current-consultant-level|string|當前增加的使用者的諮詢層級|doctor<br>case_manager|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "division_id": "0d5db08a-897d-4399-83aa-499fb6001218",
    "division_no": "S001",
    "division_name": "一般外科"
  },
  {
    "division_id": "6c245172-329f-4ed7-bdf3-f77498afd72e",
    "division_no": "S002",
    "division_name": "骨科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|division_id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|

<br>
<br>
<br>
<br>

### 1.17. GET /list/positions/current-consultant-level/{current-consultant-level}

|欄位|描述|
|:---|:---|
|Description|查詢向上諮詢職稱下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|Web->諮詢設定管理->新增|

**Note**
- 取得當前諮詢層級上一階已被設定的諮詢對象所包含的職稱

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|current-consultant-level|string|當前增加的使用者的諮詢層級|doctor<br>case_manager|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "position_id": "b0a4e5d0-3fc1-4e17-a18e-673e94056501",
    "position_description": "醫師"
  },
  {
    "position_id": "c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e",
    "position_description": "護理師"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|position_id|uuid|職稱|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|
|position_description|string|職稱說明|護理師|

<br>
<br>
<br>
<br>

### 1.18. GET /list/employees/current-consultant-level/{current-consultant-level}

|欄位|描述|
|:---|:---|
|Description|查詢向上諮詢姓名下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|Web->諮詢設定管理->新增|

**Note**
- 取得當前諮詢層級上一階已被設定的諮詢對象

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|current-consultant-level|string|當前增加的使用者的諮詢層級|doctor<br>case_manager|
|option|division-id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|option|position-id|uuid|職稱ID|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "user_id": "91284ee6-a610-4fa5-807c-f4db8638060a",
    "employee_id": "1986a375-6019-491e-8477-d36f7a232651",
    "employee_name": "陳Ｏ翔",
    "employee_description": "陳Ｏ翔 醫師 心臟外科"
  },
  {
    "user_id": "e4eda82b-4ddc-432c-bffd-86aac5e63b23",
    "employee_id": "16353161-5da3-4d14-8aab-e888dbe8a2bc",
    "employee_name": "鄭Ｏ河",
    "employee_description": "鄭Ｏ河 醫師 心臟外科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|user_id|uuid|使用者ID|91284ee6-a610-4fa5-807c-f4db8638060a|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|employee_name|string|員工姓名|陳Ｏ翔|
|employee_description|string|員工姓名|陳Ｏ翔 醫師 心臟外科|

<br>
<br>
<br>
<br>

### 1.19. GET /list/consultation-sort-items/be-consulted

|欄位|描述|
|:---|:---|
|Description|查詢諮詢回覆管理清單排序下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->諮詢回覆管理|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "consultation_sort_item_value": "last_update_time",
    "consultation_sort_item_description": "更新時間"
  },
  {
    "consultation_sort_item_value": "unreply",
    "consultation_sort_item_description": "未回覆"
  },
  {
    "consultation_sort_item_value": "asked_higher_consultant",
    "consultation_sort_item_description": "已向上諮詢"
  },
  {
    "consultation_sort_item_value": "admission",
    "consultation_sort_item_description": "住院"
  },
  {
    "consultation_sort_item_value": "home_care",
    "consultation_sort_item_description": "居家照護"
  },
  {
    "consultation_sort_item_value": "day_pass",
    "consultation_sort_item_description": "DayPass"
  },
  {
    "consultation_sort_item_value": "long_term_care_institution",
    "consultation_sort_item_description": "長照機構"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_sort_item_value|string|諮詢回覆排序下拉選單代碼|last_update_time<br>unreply<br>asked_higher_consultant<br>admission<br>home_care<br>day_pass<br>long_term_care_institution|
|consultation_sort_item_description|string|諮詢回覆排序下拉選單說明|更新時間<br>未回覆<br>已向上諮詢<br>住院<br>居家照護<br>DayPass<br>長照機構|

<br>
<br>
<br>
<br>

### 1.20. GET /list/consultation-sort-items/consult

|欄位|描述|
|:---|:---|
|Description|查詢我提出的諮詢排序下拉選單|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|App->諮詢回覆管理|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "consultation_sort_item_value": "last_update_time",
    "consultation_sort_item_description": "更新時間"
  },
  {
    "consultation_sort_item_value": "unreply",
    "consultation_sort_item_description": "未回覆"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_sort_item_value|string|諮詢回覆排序下拉選單代碼|last_update_time<br>unreply|
|consultation_sort_item_description|string|諮詢回覆排序下拉選單說明|更新時間<br>未回覆|

<br>
<br>
<br>
<br>

### 1.21. GET /list/his/employees

|欄位|描述|
|:---|:---|
|Description|抓取院內(HIS)員工的姓名下拉選單|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->帳號管理|

**Note**

- 查詢員工資料表(ECK案共用)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|division-id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|option|position-id|uuid|職稱ID|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "employee_id": "1986a375-6019-491e-8477-d36f7a232651",
    "employee_name": "陳Ｏ翔",
    "employee_description": "陳Ｏ翔 醫師 心臟外科"
  },
  {
    "employee_id": "16353161-5da3-4d14-8aab-e888dbe8a2bc",
    "employee_name": "鄭Ｏ河",
    "employee_description": "鄭Ｏ河 醫師 心臟外科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|employee_name|string|員工姓名|陳Ｏ翔|
|employee_description|string|員工姓名|陳Ｏ翔 醫師 心臟外科|

<br>
<br>
<br>
<br>

### 1.22. GET /list/consultants/consultation-window

|欄位|描述|
|:---|:---|
|Description|諮詢窗口下拉選單|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->帳號管理|

**Note**

- 查詢consultants資料表

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "consultant_id": "1986a375-6019-491e-8477-d36f7a232651",
    "employee_name": "陳Ｏ翔",
    "employee_description": "陳Ｏ翔 醫師 心臟外科"
  },
  {
    "consultant_id": "16353161-5da3-4d14-8aab-e888dbe8a2bc",
    "employee_name": "鄭Ｏ河",
    "employee_description": "鄭Ｏ河 醫師 心臟外科"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultant_id|uuid|諮詢對象ID|1986a375-6019-491e-8477-d36f7a232651|
|employee_name|string|員工姓名|陳Ｏ翔|
|employee_description|string|員工姓名|陳Ｏ翔 醫師 心臟外科|

<br>
<br>
<br>
<br>

### 1.23. GET /list/long-term-care-institutions/{organization-id}/dressings/data-source

|欄位|描述|
|:---|:---|
|Description|長照機構敷料資料來源下拉選單|
|Pagination|N/A|
|Access Control|長照機構高級管理者、長照機構一般管理者|
|Using|Web->長照機構後台->選單管理->換藥紀錄選單->敷料|

**Note**
- 當恩主公醫院未開放敷料引用權限時
  - 資料來源下拉項目只有【本院+"(APP預設)"字樣】，並預設已選取
- 當恩主公醫院開放敷料引用權限時
  - 資料來源下拉項目有【恩主公醫院+"(APP預設)"字樣】、【本院】，並預設已選取【恩主公醫院+"(APP預設)"字樣】，此時，使用者無法新增、匯入匯出EXCEL、刪除，使用者僅能觀看燈箱、詳細資料

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "organization_id": "f6fb3610-ed21-4818-83b5-8262415846b9",
    "description": "本院(APP預設)"
  },
  {
    "organization_id": "bdd23b27-9f42-490c-aa86-99746a84ece5",
    "description": "恩主公醫院(APP預設)"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|organization_id|uuid|組織ID|1986a375-6019-491e-8477-d36f7a232651|
|description|string|顯示文字|本院(APP預設)<br>恩主公醫院(APP預設)|

<br>
<br>
<br>
<br>

## 2. charts
### 2.1. GET /organizations/{organization-id}/charts/web

|欄位|描述|
|:---|:---|
|Description|依據組織查詢病患資料 (for Web)|
|Pagination|offset-limit base|
|Access Control|恩主公高級管理者, 恩主公一般管理者，長照機構高級管理者，長照機構一般管理者|
|Using|Web->病患管理|

**Note**


***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|查詢組織(uuid)|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|admission|bool|查詢住院中的病患資料|true<br>false|
|option|day-pass|bool|查詢DayPass的病患資料|true<br>false|
|option|home-care|bool|查詢居家照護的病患資料|true<br>false|
|option|discharge|bool|查詢出院的病患資料|true<br>false|
|option|unknown|bool|查詢未知(病患狀態)的病患資料|true<br>false|
|option|query|string|模糊查詢(病患姓名、身分證字號、性別、生日、電話、床號、主治醫師、病患狀態)||
|option|sortby|string|按照哪個屬性排序|patient_name<br>patient_id_no<br>patient_sex<br>patient_birthday<br>patient_telephone<br>hospital_bed_no<br>visiting_staff_name<br>patient_status<br>last_update_time|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "organization_name": "本院",
      "chart_no": "093948234",
      "patient_name": "王Ｏ明",
      "patient_sex_description": "男",
      "patient_id_no": "A123456789",
      "patient_age": 30,
      "patient_birthday": "1980-01-01",
      "patient_telephone": "0987654321",
      "hospital_bed_no": "1003-3",
      "visiting_staff": {
        "employee_name": "章Ｏ龍",
        "position_description": "主治醫師",
        "division_name": "心臟外科"
      },
      "patient_status_description": "住院",
      "health_behavior_histories": [
        {
          "health_behavior_history_id": 1,
          "health_behavior_history_description": "抽煙"
        },
        {
          "health_behavior_history_id": 2,
          "health_behavior_history_description": "喝酒"
        }
      ],
      "medical_histories": [
        {
          "medical_history_id": 1,
          "medical_history_description": "心臟病"
        },
        {
          "medical_history_id": 2,
          "medical_history_description": "高血壓"
        }
      ],
      "operation_history": "骨折開刀",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "index": 1,
      "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
      "organization": "本院",
      "chart_no": "04820392",
      "patient_name": "李Ｏ龍",
      "patient_sex_description": "男",
      "patient_id_no": "A123454321",
      "patient_age": 50,
      "patient_birthday": "1980-01-01",
      "patient_telephone": "0912345678",
      "hospital_bed_no": "0902-1",
      "visiting_staff": {
        "employee_name": "章Ｏ龍",
        "position_description": "主治醫師",
        "division_name": "心臟外科"
      },
      "patient_status_description": "住院",
      "health_behavior_histories": [
        {
          "health_behavior_history_id": 1,
          "health_behavior_history_description": "抽煙"
        },
        {
          "health_behavior_history_id": 2,
          "health_behavior_history_description": "喝酒"
        }
      ],
      "medical_histories": [
        {
          "medical_history_id": 1,
          "medical_history_description": "心臟病"
        },
        {
          "medical_history_id": 2,
          "medical_history_description": "高血壓"
        }
      ],
      "operation_history": "骨折開刀",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)|0|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|organization_name|string|組織名稱<br>恩主公轉換成"本院"<br>長照機構不用轉換|本院<br>關心長照機構<br>安心長照機構|
|chart_no|string|病歷號碼|0000192837|
|patient_name|string|姓名|王Ｏ明|
|patient_sex_description|string|性別|男|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|timestamp|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|visiting_staff|object of visiting_staff|主治醫師||
|patient_status_description|string|病患狀態|住院<br>DayPass<br>居家照護<br>出院<br>空白(未知)(畫面顯示--)|
|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|operation_history|string|手術史||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|

*visiting_staff*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|employee_name|string|諮詢對象姓名|章Ｏ龍|
|position_description|string|諮詢對象職稱|主任醫師|
|division_name|string|諮詢對象科別|一般外科|

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|


<br>
<br>
<br>
<br>

### 2.2. GET /organizations/{organization-id}/charts/app

|欄位|描述|
|:---|:---|
|Description|依據組織查詢病患資料 (for App)|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作清單/關懷清單->新增病患|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|查詢組織(uuid)<br>(恩主公本院資料用eck表示)|eck<br>98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|query|string|模糊查詢病患資料|王O明<br>A123456789|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "chart_no": "093948234",
      "patient_name": "王Ｏ明",
      "patient_sex": "male",
      "patient_id_no": "A123456789",
      "patient_age": 30,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "1003-3",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "is_work_item": true,
      "is_care_item": true
    },
    {
      "cursor": "152ba30d-434f-123d-aca1-92aefad12987",
      "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
      "chart_no": "04820392",
      "patient_name": "李Ｏ龍",
      "patient_sex": "male",
      "patient_id_no": "A123454321",
      "patient_age": 50,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "902-1",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "is_work_item": true,
      "is_care_item": true
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|hospital_bed_no|string|病床號碼|1003-3|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|is_work_item|bool|是否在工作清單|true<br>false|
|is_care_item|bool|是否在關懷清單|true<br>false|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 2.3. GET /his/charts/app

|欄位|描述|
|:---|:---|
|Description|查詢HIS病患資料 (for App)|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者|
|Using|App->新增病患->從HIS新增/掃碼新增/手動輸入|

**Note**

同時提供給從HIS新增、掃碼新增、手動輸入
條件(從HIS新增)：
- 科別
- 主治醫師
- 模糊查詢所有畫面上看得到的欄位，順序如下：
    - 床號
    - 身份證字號
    - 姓名
    - 住院、居家、DayPass、出院
    - 男、女

條件(掃碼新增)：
- 病歷號碼

條件(手動輸入)：
- 身分證字號

(查完後需比對是否已加入工作/關懷清單，並提供欄位給前端判斷)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|division-no|string|科別編號|S001|
|option|visiting-staff-no|string|主治醫師員工編號|123456789|
|option|chart-no|string|病歷號碼|0002000227|
|option|patient-id-no|string|身分證字號|A123456789|
|option|query|string|模糊查詢病患資料(床號，身份證字號，姓名，住院、居家、DayPass、出院，男、女)|王O明<br>A123456789|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "0123456789",
      "chart_no": "0123456789",
      "patient_name": "王Ｏ明",
      "patient_sex": "male",
      "patient_id_no": "A123456789",
      "patient_age": 30,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "1003-3",
      "division_name": "骨科",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "is_chart": true,
      "is_work_item": true,
      "is_care_item": true
    },
    {
      "cursor": "0482039212",
      "chart_no": "0482039212",
      "patient_name": "李Ｏ龍",
      "patient_sex": "male",
      "patient_id_no": "A123454321",
      "patient_age": 50,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "0902-1",
      "division_name": "骨科",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "is_chart": true,
      "is_work_item": true,
      "is_care_item": true
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor|0938304312|
|chart_no|string|病歷號碼|0938304312|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|hospital_bed_no|string|病床號碼|1003-3|
|division_name|string|科別名稱|一般外科|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|is_chart|bool|是否在病患清單|true<br>false|
|is_work_item|bool|是否在工作清單|true<br>false|
|is_care_item|bool|是否在關懷清單|true<br>false|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 2.4. POST /organizations/{organization-id}/charts

|欄位|描述|
|:---|:---|
|Description|新增HIS/掃碼/手動輸入病患至病患清單|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構高級管理者、長照機構一般管理者|
|Using|App->新增病患->從HIS新增/掃碼新增/手動輸入|

**Note**
- 病床號碼不檢查格式
- is_delete預設給false

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

*恩主公*
```json
[
  {
    "chart_no": "0123456789",
    "patient_name": "王Ｏ明",
    "patient_sex": "male",
    "patient_id_no": "A123456789",
    "patient_age": 30,
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0987654321",
    "hospital_bed_no": "1003-3",
    "division_no": "S001",
    "visiting_staff_no": "00234",
    "patient_status": "admission",
    "data_source": "his",
    "note": "對XX藥物過敏",
    "health_behavior_histories": [
      {
        "health_behavior_history_id": "1"
      },
      {
        "health_behavior_history_id": "2"
      }
    ],
    "medical_histories": [
      {
        "medical_history_id": "1"
      },
      {
        "medical_history_id": "2"
      }
    ],
    "operation_history": "骨折開刀"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|chart_no|string|病歷號碼|0027002277<br>無資料時回傳空字串|
|require|patient_name|string|姓名|王Ｏ明|
|option|patient_sex|string|性別|male|
|require|patient_id_no|string|身分證字號|A123456789|
|option|patient_age|int|年齡|30<br>無資料時回傳null|
|require|patient_birthday|date|生日|1980-01-01|
|option|patient_telephone|string|電話|0987654321|
|option|hospital_bed_no|string|病床號碼|1003-3|
|option|division_no|string|科別編號|S001|
|option|visiting_staff_no|string|主治醫師編號|00234|
|option|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge<br>null|
|option|data_source|string|資料來源|his<br>scan<br>user_input|
|option|note|string|備註||
|option|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|option|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|option|operation_history|string|手術史||

*health_behavior_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_id|int|疾病史ID|1|


*長照機構*
```json
[
  {
    "chart_no": "",
    "patient_name": "王Ｏ明",
    "patient_sex": "male",
    "patient_id_no": "A123456789",
    "patient_age": 30,
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0987654321",
    "hospital_bed_no": "1003-3",
    "division_no": "",
    "visiting_staff_no": "",
    "patient_status": "admission",
    "data_source": "",
    "note": "對XX藥物過敏",
    "health_behavior_histories": [
      {
        "health_behavior_history_id": "1"
      },
      {
        "health_behavior_history_id": "2"
      }
    ],
    "medical_histories": [
      {
        "medical_history_id": "1"
      },
      {
        "medical_history_id": "2"
      }
    ],
    "operation_history": "骨折開刀"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|chart_no|string|病歷號碼|長照機構不用填病歷號碼|
|require|patient_name|string|姓名|王Ｏ明|
|option|patient_sex|string|性別|male|
|require|patient_id_no|string|身分證字號|A123456789|
|option|patient_age|int|年齡|30<br>無資料時回傳null|
|require|patient_birthday|date|生日|1980-01-01|
|option|patient_telephone|string|電話|0987654321|
|option|hospital_bed_no|string|病床號碼|1003-3|
|option|division_no|string|科別編號|長照機構不用填科別編號|
|option|visiting_staff_no|string|主治醫師編號|長照機構不用填主治醫師編號|
|option|patient_status|string|病患狀態|admission<br>discharge(長照機構只有這兩種狀態)<br>null|
|option|data_source|string|資料來源|長照機構不用填資料來源|
|option|note|string|備註||
|option|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|option|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|option|operation_history|string|手術史||

*health_behavior_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_id|int|疾病史ID|1|


***
**Response Body**

*HTTP 201*
```json
[
  {
    "chart_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
    "chart_no": "0123456789",
    "patient_name": "王Ｏ明",
    "patient_sex": "male",
    "patient_id_no": "A123456789",
    "patient_age": 30,
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0987654321",
    "hospital_bed_no": "1003-3",
    "division_no": "S001",
    "visiting_staff_no": "00234",
    "patient_status": "admission",
    "data_source": "his",
    "note": "對XX藥物過敏",
    "health_behavior_histories": [
      {
        "health_behavior_history_id": "1"
      },
      {
        "health_behavior_history_id": "2"
      }
    ],
    "medical_histories": [
      {
        "medical_history_id": "1"
      },
      {
        "medical_history_id": "2"
      }
    ],
    "operation_history": "骨折開刀"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_no|string|科別編號|S001|
|visiting_staff_no|string|主治醫師編號|00234|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|data_source|string|資料來源|his<br>user_input|
|note|string|備註||
|health_behavior_histories|array of health_behavior_history|個人史||
|medical_histories|array of medical_history|疾病史||
|operation_history|string|手術史||

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|

<br>
<br>
<br>
<br>

### 2.5. GET /organizations/{organization-id}/charts/{chart-id}

|欄位|描述|
|:---|:---|
|Description|依據組織、病患ID查詢病患資料 (for App)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表->基本資料|

**Note**

先select傷口系統DB內的病患清單資料，然後用病歷號碼or身分證字號搜尋HIS內的資料，若有搜尋到，則自動更新病患清單的資料，然後再把最新的資料回傳給前端。搜HIS必須設定連線時間上限為1秒，若超過上限則視為無法連線HIS，這時候強制斷線避免此API回應時間過久，並且此時就不更新最新資料，直接回傳病患清單內的資料即可。

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "division_no": "0032",
  "division_name": "一般外科",
  "employee_id": "d44d8269-228c-4870-8d0e-9890dc73a919",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1,
      "health_behavior_history_description": "抽煙"
    },
    {
      "health_behavior_history_id": 2,
      "health_behavior_history_description": "喝酒"
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1,
      "medical_history_description": "心臟病"
    },
    {
      "medical_history_id": 2,
      "medical_history_description": "高血壓"
    }
  ],
  "operation_history": "骨折開刀",
  "data_source": "user_input",
  "is_work_item": true,
  "work_item_id": "607e0f4a-1b9b-4c21-b6b7-e50e05a1b00b",
  "is_care_item": true,
  "care_item_id": "c24b44a5-cbd1-4a27-a0bc-fb96775ca016"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|
|note|string|備註||
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|operation_history|string|手術史||
|data_source|string|資料來源|his<br>user_input|
|is_work_item|bool|是否在工作清單|true<br>false|
|work_item_id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|is_care_item|bool|是否在關懷清單|true<br>false|
|care_item_id|string|關懷清單ID|2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5|

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|

<br>
<br>
<br>
<br>

### 2.6. PUT /organizations/{organization-id}/charts/{chart-id}

|欄位|描述|
|:---|:---|
|Description|修改病患基本資料|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表->基本資料|

**Note**
- 恩主公
  - 修改病患時，檢查原始資料的data_source，若來自HIS的資料，只能修改"備註"、"個人史"、"疾病史"、"手術史"，手動新增的才能修改全部資訊。
- 長照機構
  - 除了身分證字號外其餘資訊皆可修改
- 需驗證token中的組織ID與URL參數中的一致。

**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

*恩主公*
```json
{
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "visiting_staff_no": "00234",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1
    },
    {
      "health_behavior_history_id": 2
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1
    },
    {
      "medical_history_id": 2
    }
  ],
  "operation_history": "骨折開刀"
}
```
|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|patient_name|string|姓名|王Ｏ明|
|option|patient_sex|string|性別|male|
|option|patient_age|int|年齡|30<br>無資料時回傳null|
|require|patient_birthday|date|生日|1980-01-01|
|option|patient_telephone|string|電話|0987654321|
|option|hospital_bed_no|string|病床號碼|1003-3|
|option|division_no|string|科別編號|0032|
|option|visiting_staff_no|string|主治醫師編號|00234|
|option|note|string|備註||
|option|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|option|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|option|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|option|operation_history|string|手術史||

*health_behavior_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_id|int|疾病史ID|1|

*長照機構*
```json
{
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "",
  "visiting_staff_no": "",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1
    },
    {
      "health_behavior_history_id": 2
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1
    },
    {
      "medical_history_id": 2
    }
  ],
  "operation_history": "骨折開刀"
}
```
|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|patient_name|string|姓名|王Ｏ明|
|option|patient_sex|string|性別|male|
|option|patient_age|int|年齡|30<br>無資料時回傳null|
|require|patient_birthday|date|生日|1980-01-01|
|option|patient_telephone|string|電話|0987654321|
|option|hospital_bed_no|string|病床號碼|1003-3|
|option|division_no|string|長照機構不用填科別編號||
|option|visiting_staff_no|string|長照機構不用填主治醫師編號||
|option|note|string|備註||
|option|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|option|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|option|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|option|operation_history|string|手術史||

*health_behavior_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_id|int|疾病史ID|1|


***
**Response Body**

*HTTP 200*
```json
{
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "visiting_staff_no": "00293",
  "patient_status": "admission",
  "data_source": "his",
  "note": "對xx藥物過敏",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1
    },
    {
      "health_behavior_history_id": 2
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1
    },
    {
      "medical_history_id": 2
    }
  ],
  "operation_history": "骨折開刀",
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_no|string|科別編號|S001|
|visiting_staff_no|string|主治醫師編號|00234|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|data_source|string|資料來源|his<br>user_input|
|note|string|備註||
|health_behavior_histories|array of health_behavior_history|個人史||
|medical_histories|array of medical_history|疾病史||
|operation_history|string|手術史||

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|

<br>
<br>
<br>
<br>

### 2.7. POST /organizations/{organization-id}/charts/unknown
### 2.8. PUT /organizations/{organization-id}/charts/{charts-id}/unknown

### 2.9. GET /organizations/{organization-id}/charts/patient-id-no/{patient-id-no}

|欄位|描述|
|:---|:---|
|Description|依據身分證字號查詢病患資料 (for App)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->新增病患->手動新增|

**Note**

分別查詢病患清單資料和HIS資料，分以下三種狀況回傳資料
- 有存在病患清單，但不存在HIS
  - 回傳病患清單資料，此時資料來源應為user_input(手動新增)
- 有存在HIS，但不存在病患清單
  - 回傳HIS資料，備註、個人史、疾病史、手術史留空白，此時資料來源應為his(從HIS新增)
- 同時存在病患清單及HIS
  - 備註、個人史、疾病史、手術史使用病患清單資料，其餘欄位使用HIS資料，此時資料來源應為his(從HIS新增)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|patient-id-no|string|身分證字號|A123456789|

***
**Response Body**

*HTTP 204 沒有查到資料*
```json
```

*HTTP 200 HIS*
```json
{
  "chart_id": "",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "division_name": "一般外科",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [],
  "medical_histories": [],
  "operation_history": "",
  "data_source": "his",
  "is_chart": false,
  "is_work_item": true,
  "is_care_item": true
}
```

*HTTP 200 charts/charts+HIS*
```json
{
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "division_name": "一般外科",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1,
      "health_behavior_history_description": "抽煙"
    },
    {
      "health_behavior_history_id": 2,
      "health_behavior_history_description": "喝酒"
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1,
      "medical_history_description": "心臟病"
    },
    {
      "medical_history_id": 2,
      "medical_history_description": "高血壓"
    }
  ],
  "operation_history": "骨折開刀",
  "data_source": "user_input",
  "is_chart": true,
  "is_work_item": true,
  "is_care_item": true
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|
|note|string|備註||
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|operation_history|string|手術史||
|data_source|string|資料來源|his<br>user_input|
|is_chart|bool|是否在病患清單|true<br>false|
|is_work_item|bool|是否在工作清單|true<br>false|
|is_care_item|bool|是否在關懷清單|true<br>false|

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|

<br>
<br>
<br>
<br>

### 2.10. GET /organizations/{organization-id}/charts/chart-no/{chart-no}

|欄位|描述|
|:---|:---|
|Description|依據病歷號碼查詢病患資料 (for App)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->新增病患->掃碼新增|

**Note**

分別查詢病患清單資料和HIS資料，分以下三種狀況回傳資料
- 有存在病患清單，但不存在HIS
  - 回傳病患清單資料，此時資料來源應為user_input(手動新增)
- 有存在HIS，但不存在病患清單
  - 回傳HIS資料，備註、個人史、疾病史、手術史留空白，此時資料來源應為his(從HIS新增)
- 同時存在病患清單及HIS
  - 備註、個人史、疾病史、手術史使用病患清單資料，其餘欄位使用HIS資料，此時資料來源應為his(從HIS新增)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-no|string|病歷號碼|0000123456|

***
**Response Body**

*HTTP 204 沒有查到資料*
```json
```

*HTTP 200 HIS*
```json
{
  "chart_id": "",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "division_name": "一般外科",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [],
  "medical_histories": [],
  "operation_history": "",
  "data_source": "his",
  "is_chart": false,
  "is_work_item": true,
  "is_care_item": true
}
```

*HTTP 200 charts/charts+HIS*
```json
{
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_no": "0032",
  "division_name": "一般外科",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1,
      "health_behavior_history_description": "抽煙"
    },
    {
      "health_behavior_history_id": 2,
      "health_behavior_history_description": "喝酒"
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1,
      "medical_history_description": "心臟病"
    },
    {
      "medical_history_id": 2,
      "medical_history_description": "高血壓"
    }
  ],
  "operation_history": "骨折開刀",
  "data_source": "user_input",
  "is_chart": true,
  "is_work_item": true,
  "is_care_item": true
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|
|note|string|備註||
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|operation_history|string|手術史||
|data_source|string|資料來源|his<br>user_input|
|is_chart|bool|是否在病患清單|true<br>false|
|is_work_item|bool|是否在工作清單|true<br>false|
|is_care_item|bool|是否在關懷清單|true<br>false|

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|

<br>
<br>
<br>
<br>


### 2.11. PUT /organizations/{organization-id}/charts/{chart-id}/is_delete

|欄位|描述|
|:---|:---|
|Description|刪除病歷資料 (for Web)|
|Pagination|N/A|
|Access Control|長照機構高級管理者、長照機構一般管理者|
|Using|Web->長照機構後台->病患資料管理|

**Note**
- 只有長照機構可以邏輯刪除機構內的病患資料，恩主公的不可刪除
- 

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病歷ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
N/A
```

<br>
<br>
<br>
<br>

### 2.12. POST /organizations/{organization-id}/charts/import

|欄位|描述|
|:---|:---|
|Description|匯入病患(長照機構)|
|Pagination|N/A|
|Access Control|長照機構高級管理者, 長照機構一般管理者|
|Using|Web->長照機構後台->長照機構病患管理|

**Note**
- 判斷病患姓名為"範例"時，不匯入此筆資料
- 身分證字號若有對應為修改
- 身分證字號若無對應為新增
- 病患姓名、身分證字號、生日為必填
- 格式檢查
  - 身分證字號需檢核為正確格式
  - 生日需檢核為正確格式(yyyy/mm/dd)
  - 若檢查錯誤回傳錯誤訊息："匯入失敗：請檢查是否有必填項目未填或是內容格式錯誤"
- 性別值為"男"，"女"，非此二格式則當作沒輸入(空白)
- 病患狀態值為"住院"，"出院"，非此二狀態則當作沒輸入(空白)
- 若中間有一筆錯誤，則全部不執行SQL新增，但需把錯誤的項目列在error_row裡面

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
[
  {
    "patient_name": "王Ｏ明",
    "patient_sex_description": "男",
    "patient_id_no": "A123456789",
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0987654321",
    "hospital_bed_no": "1003-3",
    "patient_status_description": "住院"
  },
  {
    "patient_name": "李Ｏ龍",
    "patient_sex_description": "男",
    "patient_id_no": "A123454321",
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0912345678",
    "hospital_bed_no": "0902-1",
    "patient_status_description": "住院"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|patient_name|string|姓名|王Ｏ明|
|require|patient_sex_description|string|性別|男<br>女|
|require|patient_id_no|string|身分證字號|A123456789|
|require|patient_birthday|timestamp|生日|1980-01-01|
|require|patient_telephone|string|電話|0987654321|
|require|hospital_bed_no|string|病床號碼|1003-3|
|require|patient_status_description|string|病患狀態|住院<br>出院<br>空白(未知)|

***
**Response Body**

*HTTP 200*
```json
{
  "insert_id": [
    "A123456789",
    "B234567891"
  ],
  "update_id": [
    "C123456789",
    "D987654321"
  ],
  "error_row": [
    {
      "patient_name": "李Ｏ龍",
      "patient_sex_description": "男",
      "patient_id_no": "A123454321",
      "patient_birthday": "1980-01-01",
      "patient_telephone": "0912345678",
      "hospital_bed_no": "0902-1",
      "patient_status_description": "住院"
    }
  ]
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|insert_id|array of string|新增項目的身分證字號||
|update|array of string|修改項目的身分證字號||
|error_row|arror of dressing|有問題的資料列||

*dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|patient_name|string|姓名|王Ｏ明|
|patient_sex_description|string|性別|男|
|patient_id_no|string|身分證字號|A123456789|
|patient_birthday|timestamp|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|patient_status_description|string|病患狀態|住院<br>出院<br>空白(未知)|


<br>
<br>
<br>
<br>

### 2.13. GET /organizations/{organization-id}/charts/export

|欄位|描述|
|:---|:---|
|Description|匯出病患(長照機構)|
|Pagination|N/A|
|Access Control|長照機構高級管理者, 長照機構一般管理者|
|Using|Web->長照機構後台->長照機構病患管理|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "patient_name": "王Ｏ明",
    "patient_sex_description": "男",
    "patient_id_no": "A123456789",
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0987654321",
    "hospital_bed_no": "1003-3",
    "patient_status_description": "住院"
  },
  {
    "patient_name": "李Ｏ龍",
    "patient_sex_description": "男",
    "patient_id_no": "A123454321",
    "patient_birthday": "1980-01-01",
    "patient_telephone": "0912345678",
    "hospital_bed_no": "0902-1",
    "patient_status_description": "住院"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|patient_name|string|姓名|王Ｏ明|
|patient_sex_description|string|性別|男|
|patient_id_no|string|身分證字號|A123456789|
|patient_birthday|timestamp|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|patient_status_description|string|病患狀態|住院<br>出院<br>空白(未知)|

<br>
<br>
<br>
<br>

### 2.14. GET /organizations/{organization-id}/charts/chart-no/{chart-no}/scan

|欄位|描述|
|:---|:---|
|Description|掃描病患(同時做新增)(for App)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者|
|Using|App->掃描病患|

**Note**

掃描病歷號碼，查詢病患清單資料和HIS資料
- 有存在HIS，但不存在病患清單
  - 新增一筆病患清單資料，把HIS的資料填入，備註、個人史、疾病史、手術史留空白
- 同時存在病患清單及HIS
  - 用HIS資料更新病患清單資料
- 若此病患不存在於工作清單，則加入工作清單，並且is_new_work_item=true
- 回傳新增/更新後的病患清單資料，回傳格式同2.5.

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-no|string|病歷號碼|0000123456|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_id_no": "A123456789",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "patient_telephone": "0987654321",
  "hospital_bed_no": "1003-3",
  "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "division_no": "0032",
  "division_name": "一般外科",
  "employee_id": "d44d8269-228c-4870-8d0e-9890dc73a919",
  "visiting_staff_no": "00293",
  "visiting_staff_name": "陳Ｏ銘",
  "note": "對xx藥物過敏",
  "patient_status": "admission",
  "patient_status_description": "住院",
  "health_behavior_histories": [
    {
      "health_behavior_history_id": 1,
      "health_behavior_history_description": "抽煙"
    },
    {
      "health_behavior_history_id": 2,
      "health_behavior_history_description": "喝酒"
    }
  ],
  "medical_histories": [
    {
      "medical_history_id": 1,
      "medical_history_description": "心臟病"
    },
    {
      "medical_history_id": 2,
      "medical_history_description": "高血壓"
    }
  ],
  "operation_history": "骨折開刀",
  "data_source": "user_input",
  "is_work_item": true,
  "work_item_id": "607e0f4a-1b9b-4c21-b6b7-e50e05a1b00b",
  "is_new_work_item": true,
  "is_care_item": true,
  "care_item_id": "c24b44a5-cbd1-4a27-a0bc-fb96775ca016"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_id|string|病歷ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|hospital_bed_no|string|病床號碼|1003-3|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|
|note|string|備註||
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|health_behavior_histories|array of health_behavior_history|個人史|無資料時回傳空array|
|medical_histories|array of medical_history|疾病史|無資料時回傳空array|
|operation_history|string|手術史||
|data_source|string|資料來源|his<br>user_input|
|is_work_item|bool|是否在工作清單|true<br>false|
|work_item_id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|is_new_work_item|bool|是否為新加入的工作清單|true<br>false|
|is_care_item|bool|是否在關懷清單|true<br>false|
|care_item_id|string|關懷清單ID|2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5|

*health_behavior_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|health_behavior_history_description|string|個人史說明|抽煙|

*medical_history*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|medical_history_description|string|疾病史說明|高血壓|

<br>
<br>
<br>
<br>


## 3. work-items
### 3.1. GET /users/{user-id}/work-items

|欄位|描述|
|:---|:---|
|Description|依據使用者查詢工作清單|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->工作清單|

**Note**
- 是否已完成is_finished欄位要看該病患90天內的傷口紀錄，只要有一筆未完成就算是未完成

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|query|string|模糊查詢病患資料|王O明<br>A123456789|
|option|sortby|string|按照哪個屬性排序|hospital_bed_no<br>unfinished<br>last_update_time<br>care_items<br>admission<br>day_pass<br>home_care<br>discharge|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "work_item_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "chart_no": "093948234",
      "patient_name": "王Ｏ明",
      "patient_sex": "male",
      "patient_id_no": "A123456789",
      "patient_age": 30,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "1003-3",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_care_item": true,
      "care_item_id": "723b1a3f-1837-a8d7-12fb-211abf2498a2",
      "is_new": true,
      "is_finished": true
    },
    {
      "cursor": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "work_item_id": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
      "chart_no": "04820392",
      "patient_name": "李Ｏ龍",
      "patient_sex": "female",
      "patient_id_no": "A123454321",
      "patient_age": 50,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "0902-1",
      "patient_status": "day_pass",
      "patient_status_description": "DayPass",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_care_item": true,
      "care_item_id": "2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5",
      "is_new": false,
      "is_finished": true
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2,
    "new_count": 1,
    "old_count": 1
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|work_item_id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_id|string|病歷ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male<br>female|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|hospital_bed_no|string|病床號碼|1003-3|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|
|is_care_item|bool|是否在關懷清單|true<br>false|
|care_item_id|string|關懷清單ID|2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5|
|is_new|bool|是否為新加入病患|true<br>false|
|is_finished|bool|是否完成編輯|true<br>false|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|20|
|new_count|int|新加入病患筆數|3|
|old_count|int|舊病患筆數|17|


<br>
<br>
<br>
<br>

### 3.2. POST /users/{user-id}/work-items

|欄位|描述|
|:---|:---|
|Description|新增病患至工作清單|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->工作清單->新增病患<br>App->工作清單->新增病患->從HIS新增/掃碼新增/手動輸入|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Request Body**

```json
[
  {
    "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121"
  },
  {
    "chart_id": "152ba30d-434f-123d-aca1-92aefad12987"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|

***
**Response Body**

*HTTP 201*
```json
[
  {
    "work_item_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
    "user_id": "89ea130a-2bc2-4ea6-9b77-719e23279435",
    "chart_id": "152ba30d-434f-123d-aca1-92aefad12987"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|work_item_id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|user_id|string|使用者ID|89ea130a-2bc2-4ea6-9b77-719e23279435|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|

<br>
<br>
<br>
<br>

### 3.3. DELETE /users/{user-id}/work-items/{work-item-id}

|欄位|描述|
|:---|:---|
|Description|依據"工作清單ID"刪除工作清單|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->工作清單|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|0293849301|
|require|work-item-id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 204*
```json
N/A
```

<br>
<br>
<br>
<br>

## 4. care-items
### 4.1. GET /users/{user-id}/care-items

|欄位|描述|
|:---|:---|
|Description|依據使用者查詢關懷清單|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->關懷清單|

**Note**
- 是否已完成is_finished欄位要看該病患90天內的傷口紀錄，只要有一筆未完成就算是未完成

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|query|string|模糊查詢病患資料|王O明<br>A123456789|
|option|sortby|string|按照哪個屬性排序|hospital_bed_no<br>unfinished<br>last_update_time<br>work_items<br>admission<br>day_pass<br>home_care<br>discharge|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "care_item_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "chart_no": "093948234",
      "patient_name": "王Ｏ明",
      "patient_sex": "male",
      "patient_id_no": "A123456789",
      "patient_age": 30,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "1003-3",
      "patient_status": "admission",
      "patient_status_description": "住院",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_work_item": true,
      "work_item_id": "723b1a3f-1837-a8d7-12fb-211abf2498a2",
      "is_new": true,
      "is_finished": true
    },
    {
      "cursor": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "care_item_id": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
      "chart_no": "04820392",
      "patient_name": "李Ｏ龍",
      "patient_sex": "female",
      "patient_id_no": "A123454321",
      "patient_age": 50,
      "patient_birthday": "1980-01-01",
      "hospital_bed_no": "0902-1",
      "patient_status": "day_pass",
      "patient_status_description": "DayPass",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_word_item": true,
      "work_item_id": "2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5",
      "is_new": true,
      "is_finished": true
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2,
    "new_count": 1,
    "old_count": 1
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|care_item_id|string|關懷清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|chart_id|string|病歷ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male<br>female|
|patient_id_no|string|身分證字號|A123456789|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|hospital_bed_no|string|病床號碼|1003-3|
|patient_status|string|病患狀態|admission<br>day_pass<br>home_care<br>discharge|
|patient_status_description|string|病患狀態說明|住院<br>DayPass<br>居家照護<br>出院|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|
|is_work_item|bool|是否在工作清單|true<br>false|
|work_item_id|string|工作清單ID|2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5|
|is_new|bool|是否為新加入病患|true<br>false|
|is_finished|bool|是否完成編輯|true<br>false|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|20|
|new_count|int|新加入病患筆數|3|
|old_count|int|舊病患筆數|17|

<br>
<br>
<br>
<br>

### 4.2. POST /users/{user-id}/care-items

|欄位|描述|
|:---|:---|
|Description|新增病患至關懷清單|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->關懷清單->新增病患<br>App->關懷清單->新增病患->從HIS新增/掃碼新增/手動輸入|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Request Body**

```json
[
  {
    "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121"
  },
  {
    "chart_id": "152ba30d-434f-123d-aca1-92aefad12987"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|

***
**Response Body**

*HTTP 201*
```json
[
  {
    "care_item_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
    "user_id": "89ea130a-2bc2-4ea6-9b77-719e23279435",
    "chart_id": "152ba30d-434f-123d-aca1-92aefad12987"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|care_item_id|string|關懷清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|user_id|string|使用者ID|89ea130a-2bc2-4ea6-9b77-719e23279435|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|

<br>
<br>
<br>
<br>

### 4.3. DELETE /users/{user-id}/care-items/{care-item-id}

|欄位|描述|
|:---|:---|
|Description|依據"關懷清單ID"刪除關懷清單|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->關懷清單|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|0293849301|
|require|care-item-id|string|關懷清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 204*
```json
N/A
```

<br>
<br>
<br>
<br>

## 5. wounds
### 5.1. GET /users/{user-id}/wounds/unfinished-count

|欄位|描述|
|:---|:---|
|Description|查詢"未完成傷口紀錄"筆數|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。<br>額外驗證：{user-id}只能為自己(用token驗證)|
|Using|App->工作清單<br>App->傷口紀錄|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Response Body**

*HTTP 200*
```json
{
  "wounds_unfinished_count": 5,
  "work_item_unfinished_count": 3,
  "care_item_unfinished_count": 1,
  "consult_unread": 10,
  "be_consulted_unread": 20,
  "notification_unread": 2
}
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wounds_unfinished_count|int|傷口未完成個數(自己建立的且存在工作/關懷清單的才統計)|5|
|work_item_unfinished_count|int|工作清單傷口未完成個數|3|
|care_item_unfinished_count|int|關懷清單傷口未完成個數|1|
|consult_unread|int|我提出的諮詢未讀個數<br>依據角色不同，此欄位與be_consulted_unread則一出現|10|
|be_consulted_unread|int|諮詢回覆管理(人家提出給我的)未讀個數<br>依據角色不同，此欄位與consult_unread則一出現|20|
|notification_unread"|int|通知未讀個數|2|

<br>
<br>
<br>
<br>

### 5.2. GET /organizations/{organization-id}/charts/{chart-id}/wounds

|欄位|描述|
|:---|:---|
|Description|查詢傷口紀錄列表|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患|
|Using|App->工作/關懷清單->傷口列表|

**Note**
- 未分類的資料chart_wound_sub_part_combination=0，chart_wound_sub_part_combination_description=""
- 組合資料時，chart_wound_sub_part_combination將所有子部位組合起來，但是chart_wound_sub_part_combination_description會將中文名稱重複的過濾掉，例如"右大拇指"有分前後的ID，但是description都是"右大拇指"，這時候只要顯示一次，不要顯示成"右大拇指+右大拇指"
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序
- DayPass病患查詢時，chart_wonds內creater不是DayPass病患本人的資料不顯示，外層分類資料夾不受此影響，只要該資料夾有資料，就會有資料夾的結構。

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|require|time-span|string|時間區間|all<br>today<br>last2day(昨天)<br>last3day(前天、三天內)<br>last4day(大前天)<br>last7day<br>last30day<br>last90day(預設)|
|option|chart-wound-sub-part-combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|option|is-replied|bool|查詢已被回覆的傷口紀錄(for傷口建議頁面)|true=已被回覆<br>false=未被回覆<br>省略 is-replied = 全部（包含回覆及未被回覆）|
|option|query|string|模糊查詢資料夾標題欄位的中文字(部位)|腹股溝|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "chart_wound_sub_part_combination": "0",
    "chart_wound_sub_part_combination_description": "未分類",
    "chart_wound_sub_parts": [],
    "chart_wounds": [
      {
        "chart_wound_id": "9622c569-98b8-48e0-bdcc-e902754dba51",
        "image_url": "http://xxx/aa",
        "image_thumbnail_url": "http://xxx/aaa",
        "is_finished": false,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      },
      {
        "chart_wound_id": "3d9c13dc-b548-45dd-a99a-243c57999746",
        "image_url": "http://xxx/bb",
        "image_thumbnail_url": "http://xxx/bbb",
        "is_finished": false,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      }
    ],
    "chart_wound_finished_count": 0,
    "chart_wound_unfinished_count": 2,
    "chart_wound_last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "chart_wound_sub_part_combination": "28",
    "chart_wound_sub_part_combination_description": "右上腹",
    "chart_wound_sub_parts": [
      {
        "wound_sub_part_id": 28,
        "wound_sub_part_code": "hb_f_abdomen_c_1",
        "wound_sub_part_description": "右上腹"
      }
    ],
    "chart_wounds": [
      {
        "chart_wound_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
        "image_url": "http://xxx/xx",
        "image_thumbnail_url": "http://xxx/xxx",
        "is_finished": true,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      },
      {
        "chart_wound_id": "f885e28c-44a5-4cf3-a16f-d66e726f16be",
        "image_url": "http://xxx/oo",
        "image_thumbnail_url": "http://xxx/ooo",
        "is_finished": true,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      }
    ],
    "chart_wound_finished_count": 2,
    "chart_wound_unfinished_count": 0,
    "chart_wound_last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "chart_wound_sub_part_combination": "28-30",
    "chart_wound_sub_part_combination_description": "右上腹+右下腹",
    "chart_wound_sub_parts": [
      {
        "wound_sub_part_id": 28,
        "wound_sub_part_code": "hb_f_abdomen_c_1",
        "wound_sub_part_description": "右上腹"
      },
      {
        "wound_sub_part_id": 30,
        "wound_sub_part_code": "hb_f_abdomen_c_3",
        "wound_sub_part_description": "右下腹"
      }
    ],
    "chart_wounds": [
      {
        "chart_wound_id": "d61b61f6-efa2-4a2a-9417-3eb92510c584",
        "image_url": "http://xxx/yy",
        "image_thumbnail_url": "http://xxx/yyy",
        "is_finished": true,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      },
      {
        "chart_wound_id": "dd7e9ef9-2716-49cf-bfad-575168b786c6",
        "image_url": "http://xxx/zz",
        "image_thumbnail_url": "http://xxx/zzz",
        "is_finished": true,
        "last_update_time": "2019-07-18T13:54:37.917+0000"
      }
    ],
    "chart_wound_finished_count": 2,
    "chart_wound_unfinished_count": 0,
    "chart_wound_last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||
|chart_wounds|array of chart_wound|傷口||
|chart_wound_finished_count|int|傷口紀錄已完成編輯總數||
|chart_wound_unfinished_count|int|傷口紀錄未完成編輯總數||
|chart_wound_last_update_time|datetime|更新日期<br>(此傷口部位底下的所有傷口的最後的更新時間)|2019-07-18T13:54:37.917+0000|

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_sub_part_id|int|傷口子部位ID|28|
|wound_sub_part_code|string|傷口子部位代碼|hb_f_abdomen_c_1|
|wound_sub_part_description|string|傷口子部位說明|右上腹|

*chart_wound*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_thumbnail_url|string|縮圖URL|http://xxx/zzz|
|is_finished|bool|是否完成編輯|true<br>false|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 5.3. GET /organizations/{organization-id}/charts/{chart-id}/wounds/part-types

|欄位|描述|
|:---|:---|
|Description|查詢現存傷口分類API(進階搜尋)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表->進階搜尋|

**Note**

- select該病患chart_wound_sub_parts內distinct過後的部位資料
- 並於第一筆增加未分類項目
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_sub_part_id": 0,
    "wound_sub_part_code": "",
    "wound_sub_part_description": "未分類",
    "index": 0
  },
  {
    "wound_sub_part_id": 28,
    "wound_sub_part_code": "hb_f_abdomen_c_1",
    "wound_sub_part_description": "右上腹",
    "index": 10
  },
  {
    "wound_sub_part_id": 30,
    "wound_sub_part_code": "hb_f_abdomen_c_3",
    "wound_sub_part_description": "右下腹",
    "index": 32
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_sub_part_id|int|傷口子部位ID|28|
|wound_sub_part_code|string|傷口子部位代碼|hb_f_abdomen_c_1|
|wound_sub_part_description|string|傷口子部位說明|右上腹|
|index|int|傷口子部位排序|未分類=0<br>其他依據資料表顯示|

<br>
<br>
<br>
<br>

### 5.4. GET /organizations/{organization-id}/charts/{chart-id}/wounds/{chart-wounds-id}

|欄位|描述|
|:---|:---|
|Description|查詢傷口詳細資料|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表|

**Note**
- is_consultation及is_reply查詢consultations是否有相關資料
- unread_count查詢consultations對應的討論版有多少則訊息未讀
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|require|chart-wounds-id|string|病患傷口ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "chart_wound_parts": [
    {
      "chart_wound_part_id": "ea01d48c-5cff-4608-abd9-afd80ac2ddc0",
      "wound_part_id": 4,
      "wound_part_code": "abdoment",
      "wound_part_description": "腹部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "hb_f_groin_c_4",
          "wound_sub_part_description": "會陰區"
        }
      ]
    },
    {
      "chart_wound_part_id": "35237797-aab9-4d92-aa86-ba03448fdc2f",
      "wound_part_id": 8,
      "wound_part_code": "groin",
      "wound_part_description": "腹股溝及髖部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 2,
          "wound_sub_part_code": "hb_f_groin_c_5",
          "wound_sub_part_description": "左腹股溝"
        },
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 3,
          "wound_sub_part_code": "hb_f_groin_c_6",
          "wound_sub_part_description": "左髖部"
        }
      ]
    }
  ],
  "chart_wound_ointments": [
    {
      "chart_wound_ointment_id": "be69f16c-98a4-4bf7-917b-bfb7a77b2e81",
      "ointment_id": 1,
      "ointment_name": "除疤凝膠"
    },
    {
      "chart_wound_ointment_id": "26b6963e-e027-4e39-9baa-f08e399c3053",
      "ointment_id": 2,
      "ointment_name": "抗生素藥膏"
    }
  ],
  "chart_wound_dressings": [
    {
      "chart_wound_dressing_id": "0c80481b-4d89-4601-9b83-3c20d78b720a",
      "dressing_id": 1,
      "dressing_name": "紗布"
    }
  ],
  "chart_wound_medical_materials": [
    {
      "chart_wound_medical_material_id": "18e7f43a-9582-43fd-9ee6-d52071f47950",
      "medical_material_id": 1,
      "medical_material_name": "特殊器材A"
    }
  ],
  "chart_wound_wound_treatments": [
    {
      "chart_wound_wound_treatment_id": "f08f1233-bf27-4c2a-96ab-af86ed20a7bc",
      "wound_treatment_id": 2,
      "wound_treatment_description": "切開排膿"
    }
  ],
  "change_dressing_note": "病患對xx藥膏過敏，建議改用oo藥膏",
  "is_finished": true,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "is_consultation": true,
  "is_reply": true,
  "unread_count": 5,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067",
  "create_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_width|int|原始圖片寬|1920|
|image_height|int|原始圖片高|1080|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|image_thumbnail_width|int|縮圖寬|192|
|image_thumbnail_height|int|縮圖高|108|
|wound_length|numeric|傷口長(mm)|105|
|wound_width|numeric|傷口寬(mm)|50|
|wound_depth|numeric|傷口深(mm)|5|
|bounding_box_position|string|範圍框左上角位置|203,89|
|bounding_box_width|int|範圍框寬|310|
|bounding_box_height|int|範圍框高|150|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_parts|array of chart_wound_part|傷口部位||
|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|change_dressing_note|string|病患傷口換藥備註||
|is_finished|bool|是否完成編輯|true<br>false|
|is_finished_chart|bool|是否完成傷口紀錄之基本資料|true<br>false|
|is_finished_part|bool|是否完成傷口紀錄之部位資料|true<br>false|
|is_finished_change_dressing|bool|是否完成傷口紀錄之換藥資料|true<br>false|
|is_consultation|bool|是否提出諮詢|true<br>false|
|is_reply|bool|是否已回覆(若沒有提出諮詢則此欄位null)|true<br>false|
|unread_count|int|未讀訊息數(若沒有提出諮詢或使用者是被諮詢者則此欄位null)|5|
|creator|string|建立者|81583525-9153-435d-b531-f8d128af8fb0|
|create_time|datetime|建立日期|2019-07-18T13:54:37.917+0000|

*chart_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_part_id|uuid|病患傷口部位ID|35237797-aab9-4d92-aa86-ba03448fdc2f|
|wound_part_id|int|傷口部位ID|8|
|wound_part_code|string|傷口部位代碼|abdoment|
|wound_part_description|string|傷口部位說明|腹股溝|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_id|uuid|病患傷口子部位ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_sub_part_id|int|傷口子部位ID|8|
|wound_sub_part_code|string|傷口子部位代碼|hb_f_abdomen_c_1|
|wound_sub_part_description|string|傷口子部位說明|右上腹|

*chart_wound_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_ointment_id|uuid|病患傷口換藥藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*chart_wound_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_dressing_id|uuid|病患傷口換藥敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*chart_wound_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_medical_material_id|uuid|病患傷口換藥特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*chart_wound_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_wound_treatment_id|uuid|病患傷口換藥傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

<br>
<br>
<br>
<br>

### 5.5. POST /organizations/{organization-id}/charts/{chart-id}/wounds

|欄位|描述|
|:---|:---|
|Description|新增傷口紀錄(儲存原圖/縮圖的pase/name+儲存傷口尺寸)|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患。|
|Using|App->工作/關懷清單->傷口列表->新增傷口紀錄->調整傷口尺寸|

**Note**

- 新增傷口紀錄。
- 須先呼叫上傳傷口照片原圖/縮圖API，這邊只儲存原圖/縮圖的path和name。
- 是否已完成is_finished固定存入false
- 是否已完成病患基本資料is_finished_chart固定存入true
- 若有傳入部位資料is_finished_part存入true，否則存入false
- is_finished_change_dressing存入false
- 若有傳入部位資料chart_wound_sub_part_combination要存入組合好的資料，若沒有部位資料，則chart_wound_sub_part_combination存入0
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

```json
{
  "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/",
  "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/",
  "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_parts": [
    {
      "wound_part_id": 4,
      "chart_wound_sub_parts": [
        {
          "wound_sub_part_id": 1
        }
      ]
    },
    {
      "wound_part_id": 8,
      "chart_wound_sub_parts": [
        {
          "wound_sub_part_id": 1
        },
        {
          "wound_sub_part_id": 1
        }
      ]
    }
  ]
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/|
|require|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|require|image_width|numeric|原始圖片寬|1920|
|require|image_height|numeric|原始圖片高|1080|
|require|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/|
|require|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|require|image_thumbnail_width|numeric|縮圖寬|192|
|require|image_thumbnail_height|numeric|縮圖高|108|
|require|wound_length|numeric|傷口長(mm)|105|
|require|wound_width|numeric|傷口寬(mm)|50|
|require|wound_depth|numeric|傷口深(mm)|5|
|require|bounding_box_position|string|範圍框左上角位置|203,89|
|require|bounding_box_width|int|範圍框寬|310|
|require|bounding_box_height|int|範圍框高|150|
|require|chart_wound_parts|array of chart_wound_part|傷口部位|沒有選傷口部位時可以傳入空陣列|

*chart_wound_part*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_part_id|int|傷口部位ID|8|
|require|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_sub_part_id|int|傷口子部位ID|8|

***
**Response Body**

*HTTP 201 沒有部位的狀態*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "0",
  "chart_wound_sub_part_combination_description": "",
  "chart_wound_parts": [],
  "chart_wound_ointments": [],
  "chart_wound_dressings": [],
  "chart_wound_medical_materials": [],
  "chart_wound_wound_treatments": [],
  "change_dressing_note": "",
  "is_finished": false,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067"
}
```

*HTTP 201 有部位的狀態*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "chart_wound_parts": [
    {
      "chart_wound_part_id": "ea01d48c-5cff-4608-abd9-afd80ac2ddc0",
      "wound_part_id": 4,
      "wound_part_code": "abdoment",
      "wound_part_description": "腹部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-2",
          "wound_sub_part_description": "會陰區"
        }
      ]
    },
    {
      "chart_wound_part_id": "35237797-aab9-4d92-aa86-ba03448fdc2f",
      "wound_part_id": 8,
      "wound_part_code": "groin",
      "wound_part_description": "腹股溝及髖部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-4",
          "wound_sub_part_description": "左腹股溝"
        },
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-6",
          "wound_sub_part_description": "左髖部"
        }
      ]
    }
  ],
  "chart_wound_ointments": [],
  "chart_wound_dressings": [],
  "chart_wound_medical_materials": [],
  "chart_wound_wound_treatments": [],
  "change_dressing_note": "",
  "is_finished": false,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_width|int|原始圖片寬|1920|
|image_height|int|原始圖片高|1080|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|image_thumbnail_width|int|縮圖寬|192|
|image_thumbnail_height|int|縮圖高|108|
|wound_length|numeric|傷口長(mm)|105|
|wound_width|numeric|傷口寬(mm)|50|
|wound_depth|numeric|傷口深(mm)|5|
|bounding_box_position|string|範圍框左上角位置|203,89|
|bounding_box_width|int|範圍框寬|310|
|bounding_box_height|int|範圍框高|150|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_parts|array of chart_wound_part|傷口部位||
|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|change_dressing_note|string|病患傷口換藥備註||
|is_finished|bool|是否完成編輯|true<br>false|
|is_finished_chart|bool|是否完成傷口紀錄之基本資料|true<br>false|
|is_finished_part|bool|是否完成傷口紀錄之部位資料|true<br>false|
|is_finished_change_dressing|bool|是否完成傷口紀錄之換藥資料|true<br>false|
|creator|string|建立者|81583525-9153-435d-b531-f8d128af8fb0|


*chart_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_part_id|uuid|病患傷口部位ID|35237797-aab9-4d92-aa86-ba03448fdc2f|
|wound_part_id|int|傷口部位ID|8|
|wound_part_code|string|傷口部位代碼|abdoment|
|wound_part_description|string|傷口部位說明|腹股溝|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_id|uuid|病患傷口子部位ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_sub_part_id|int|傷口子部位ID|8|
|wound_sub_part_code|string|傷口子部位代碼|abdoment|
|wound_sub_part_description|string|傷口子部位說明|腹股溝|

*chart_wound_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_ointment_id|uuid|病患傷口換藥藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*chart_wound_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_dressing_id|uuid|病患傷口換藥敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*chart_wound_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_medical_material_id|uuid|病患傷口換藥特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*chart_wound_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_wound_treatment_id|uuid|病患傷口換藥傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

<br>
<br>
<br>
<br>

### 5.6. PUT /wounds/{chart-wounds-id}/size

|欄位|描述|
|:---|:---|
|Description|修改傷口尺寸|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄<br>App->工作/關懷清單->傷口列表->圈選傷口尺寸|

**Note**
- 需用token檢查，只有creator為該user的時候才可修改。
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wounds-id|uuid|病患傷口ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

```json
{
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_length|numeric|傷口長(mm)|105|
|require|wound_width|numeric|傷口寬(mm)|50|
|require|wound_depth|numeric|傷口深(mm)|5|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "chart_wound_parts": [
    {
      "chart_wound_part_id": "ea01d48c-5cff-4608-abd9-afd80ac2ddc0",
      "wound_part_id": 4,
      "wound_part_code": "abdoment",
      "wound_part_description": "腹部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-2",
          "wound_sub_part_description": "會陰區"
        }
      ]
    },
    {
      "chart_wound_part_id": "35237797-aab9-4d92-aa86-ba03448fdc2f",
      "wound_part_id": 8,
      "wound_part_code": "groin",
      "wound_part_description": "腹股溝及髖部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-4",
          "wound_sub_part_description": "左腹股溝"
        },
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-6",
          "wound_sub_part_description": "左髖部"
        }
      ]
    }
  ],
  "chart_wound_ointments": [
    {
      "chart_wound_ointment_id": "be69f16c-98a4-4bf7-917b-bfb7a77b2e81",
      "ointment_id": 1,
      "ointment_name": "除疤凝膠"
    },
    {
      "chart_wound_ointment_id": "26b6963e-e027-4e39-9baa-f08e399c3053",
      "ointment_id": 2,
      "ointment_name": "抗生素藥膏"
    }
  ],
  "chart_wound_dressings": [
    {
      "chart_wound_dressing_id": "0c80481b-4d89-4601-9b83-3c20d78b720a",
      "dressing_id": 1,
      "dressing_name": "紗布"
    }
  ],
  "chart_wound_medical_materials": [
    {
      "chart_wound_medical_material_id": "18e7f43a-9582-43fd-9ee6-d52071f47950",
      "medical_material_id": 1,
      "medical_material_name": "特殊器材A"
    }
  ],
  "chart_wound_wound_treatments": [
    {
      "chart_wound_wound_treatment_id": "f08f1233-bf27-4c2a-96ab-af86ed20a7bc",
      "wound_treatment_id": 2,
      "wound_treatment_description": "切開排膿"
    }
  ],
  "change_dressing_note": "病患對xx藥膏過敏，建議改用oo藥膏",
  "is_finished": true,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_width|int|原始圖片寬|1920|
|image_height|int|原始圖片高|1080|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|image_thumbnail_width|int|縮圖寬|192|
|image_thumbnail_height|int|縮圖高|108|
|wound_length|numeric|傷口長(mm)|105|
|wound_width|numeric|傷口寬(mm)|50|
|wound_depth|numeric|傷口深(mm)|5|
|bounding_box_position|string|範圍框左上角位置|203,89|
|bounding_box_width|int|範圍框寬|310|
|bounding_box_height|int|範圍框高|150|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_parts|array of chart_wound_part|傷口部位||
|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|change_dressing_note|string|病患傷口換藥備註||
|is_finished|bool|是否完成編輯|true<br>false|
|is_finished_chart|bool|是否完成傷口紀錄之基本資料|true<br>false|
|is_finished_part|bool|是否完成傷口紀錄之部位資料|true<br>false|
|is_finished_change_dressing|bool|是否完成傷口紀錄之換藥資料|true<br>false|
|creator|string|建立者|81583525-9153-435d-b531-f8d128af8fb0|


*chart_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_part_id|uuid|病患傷口部位ID|35237797-aab9-4d92-aa86-ba03448fdc2f|
|wound_part_id|int|傷口部位ID|8|
|wound_part_code|string|傷口部位代碼|abdoment|
|wound_part_description|string|傷口部位說明|腹股溝|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_id|uuid|病患傷口子部位ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_sub_part_id|int|傷口子部位ID|8|
|wound_sub_part_code|string|傷口子部位代碼|abdoment|
|wound_sub_part_description|string|傷口子部位說明|腹股溝|

*chart_wound_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_ointment_id|uuid|病患傷口換藥藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*chart_wound_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_dressing_id|uuid|病患傷口換藥敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*chart_wound_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_medical_material_id|uuid|病患傷口換藥特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*chart_wound_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_wound_treatment_id|uuid|病患傷口換藥傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

<br>
<br>
<br>
<br>

### 5.7. DELETE /wounds/{chart-wounds-id}

|欄位|描述|
|:---|:---|
|Description|刪除病患傷口資料|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄<br>App->工作/關懷清單->傷口列表->圈選傷口尺寸|

**Note**
- 需用token檢查，只有creator為該user的時候才可刪除。
- 若已提出諮詢則不可刪除

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wounds-id|string|病患傷口ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 204*
```json
N/A
```

*HTTP 400*
```json
{
  "error_message": "非傷口紀錄建立者，無法刪除此筆資料"
}
```

<br>
<br>
<br>
<br>

### 5.8. PUT /wounds/{chart-wounds-id}/part-and-change-dressing

|欄位|描述|
|:---|:---|
|Description|修改傷口部位和換藥紀錄|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口部位/換藥紀錄|

**Note**

- 需用token檢查，只有creator為該user的時候才可修改。
- 儲存完"病患傷口部位(第一層)資料表"和"病患傷口子部位(第二層)資料表"後，要回寫"病患傷口資料"的"chart_wound_sub_part_combination"欄位，有多個組合時用"-"連接，ex:28-30。
- is_finished_part判斷邏輯
  - chart_wound_sub_parts(子部位)有資料時填入true
  - 否則填入false
- is_finished_change_dressing判斷邏輯
  - chart_wound_ointments和chart_wound_dressings都有資料時填入true
  - 否則填入false
- is_finished判斷邏輯
  - 若is_finished_chart, is_finished_part, is_finished_change_dressing三者都為true，則is_finished回填true
  - 否則回填false
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序
- DayPass病患只會修改部位，不會修改換藥紀錄，換藥紀錄都給空陣列即可。

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wounds-id|uuid|病患傷口ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

```json
{
  "chart_wound_parts": [
    {
      "wound_part_id": 4,
      "chart_wound_sub_parts": [
        {
          "wound_sub_part_id": 1
        }
      ]
    },
    {
      "wound_part_id": 8,
      "chart_wound_sub_parts": [
        {
          "wound_sub_part_id": 1
        },
        {
          "wound_sub_part_id": 1
        }
      ]
    }
  ],
  "chart_wound_ointments": [
    {
      "ointment_id": 1
    },
    {
      "ointment_id": 2
    }
  ],
  "chart_wound_dressings": [
    {
      "dressing_id": 1
    }
  ],
  "chart_wound_medical_materials": [
    {
      "medical_material_id": 1
    }
  ],
  "chart_wound_wound_treatments": [
    {
      "wound_treatment_id": 2
    }
  ],
  "change_dressing_note": "病患對xx藥膏過敏，建議改用oo藥膏"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart_wound_parts|array of chart_wound_part|傷口部位||
|require|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|require|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|require|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|require|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|option|change_dressing_note|string|病患傷口換藥備註|

*chart_wound_part*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_part_id|int|傷口部位ID|8|
|require|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_sub_part_id|int|傷口子部位ID|8|

*chart_wound_ointment*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|ointment_id|int|藥膏ID|1|

*chart_wound_dressing*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressing_id|int|敷料ID|1|

*chart_wound_medical_material*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_material_id|int|特殊器材ID|1|

*chart_wound_wound_treatment*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_treatment_id|int|現行處置ID|1|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "chart_wound_parts": [
    {
      "chart_wound_part_id": "ea01d48c-5cff-4608-abd9-afd80ac2ddc0",
      "wound_part_id": 4,
      "wound_part_code": "abdoment",
      "wound_part_description": "腹部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-2",
          "wound_sub_part_description": "會陰區"
        }
      ]
    },
    {
      "chart_wound_part_id": "35237797-aab9-4d92-aa86-ba03448fdc2f",
      "wound_part_id": 8,
      "wound_part_code": "groin",
      "wound_part_description": "腹股溝及髖部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-4",
          "wound_sub_part_description": "左腹股溝"
        },
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-6",
          "wound_sub_part_description": "左髖部"
        }
      ]
    }
  ],
  "chart_wound_ointments": [
    {
      "chart_wound_ointment_id": "be69f16c-98a4-4bf7-917b-bfb7a77b2e81",
      "ointment_id": 1,
      "ointment_name": "除疤凝膠"
    },
    {
      "chart_wound_ointment_id": "26b6963e-e027-4e39-9baa-f08e399c3053",
      "ointment_id": 2,
      "ointment_name": "抗生素藥膏"
    }
  ],
  "chart_wound_dressings": [
    {
      "chart_wound_dressing_id": "0c80481b-4d89-4601-9b83-3c20d78b720a",
      "dressing_id": 1,
      "dressing_name": "紗布"
    }
  ],
  "chart_wound_medical_materials": [
    {
      "chart_wound_medical_material_id": "18e7f43a-9582-43fd-9ee6-d52071f47950",
      "medical_material_id": 1,
      "medical_material_name": "特殊器材A"
    }
  ],
  "chart_wound_wound_treatments": [
    {
      "chart_wound_wound_treatment_id": "f08f1233-bf27-4c2a-96ab-af86ed20a7bc",
      "wound_treatment_id": 2,
      "wound_treatment_description": "切開排膿"
    }
  ],
  "change_dressing_note": "病患對xx藥膏過敏，建議改用oo藥膏",
  "is_finished": true,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_width|int|原始圖片寬|1920|
|image_height|int|原始圖片高|1080|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|image_thumbnail_width|int|縮圖寬|192|
|image_thumbnail_height|int|縮圖高|108|
|wound_length|numeric|傷口長(mm)|105|
|wound_width|numeric|傷口寬(mm)|50|
|wound_depth|numeric|傷口深(mm)|5|
|bounding_box_position|string|範圍框左上角位置|203,89|
|bounding_box_width|int|範圍框寬|310|
|bounding_box_height|int|範圍框高|150|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_parts|array of chart_wound_part|傷口部位||
|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|change_dressing_note|string|病患傷口換藥備註||
|is_finished|bool|是否完成編輯|true<br>false|
|is_finished_chart|bool|是否完成傷口紀錄之基本資料|true<br>false|
|is_finished_part|bool|是否完成傷口紀錄之部位資料|true<br>false|
|is_finished_change_dressing|bool|是否完成傷口紀錄之換藥資料|true<br>false|
|creator|string|建立者|81583525-9153-435d-b531-f8d128af8fb0|

*chart_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_part_id|uuid|病患傷口部位ID|35237797-aab9-4d92-aa86-ba03448fdc2f|
|wound_part_id|int|傷口部位ID|8|
|wound_part_code|string|傷口部位代碼|abdoment|
|wound_part_description|string|傷口部位說明|腹股溝|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_id|uuid|病患傷口子部位ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_sub_part_id|int|傷口子部位ID|8|
|wound_sub_part_code|string|傷口子部位代碼|abdoment|
|wound_sub_part_description|string|傷口子部位說明|腹股溝|

*chart_wound_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_ointment_id|uuid|病患傷口換藥藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*chart_wound_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_dressing_id|uuid|病患傷口換藥敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*chart_wound_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_medical_material_id|uuid|病患傷口換藥特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*chart_wound_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_wound_treatment_id|uuid|病患傷口換藥傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

*HTTP 400*
```json
{
  "error_message": "非傷口紀錄建立者，無法刪除此筆資料"
}
```

<br>
<br>
<br>
<br>

### 5.9. GET /organizations/{organization-id}/charts/{chart-id}/wounds/chart-wound-sub-part-combination/{chart-wound-sub-part-combination}

|欄位|描述|
|:---|:---|
|Description|查詢傷口紀錄之該部位傷口列表|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄|

**Note**
- 查詢傳入參數chart-wound-sub-part-combination同部位(完全相同)的傷口清單
- 組合Response
  - 未分類的資料chart_wound_sub_part_combination=0，chart_wound_sub_part_combination_description=""
  - 組合資料時，chart_wound_sub_part_combination將所有子部位組合起來，但是chart_wound_sub_part_combination_description會將中文名稱重複的過濾掉，例如"右大拇指"有分前後的ID，但是description都是"右大拇指"，這時候只要顯示一次，不要顯示成"右大拇指+右大拇指"
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序
- DayPass病患查詢時，chart_wonds內creater不是DayPass病患本人的資料不顯示

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|require|chart-wound-sub-part-combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_wound_sub_part_combination": "28-30",
  "chart_wound_sub_part_combination_description": "右上腹+右下腹",
  "chart_wound_sub_parts": [
    {
      "wound_sub_part_id": 28,
      "wound_sub_part_code": "hb_f_abdomen_c_1",
      "wound_sub_part_description": "右上腹"
    },
    {
      "wound_sub_part_id": 30,
      "wound_sub_part_code": "hb_f_abdomen_c_3",
      "wound_sub_part_description": "右下腹"
    }
  ],
  "chart_wounds": [
    {
      "chart_wound_id": "d61b61f6-efa2-4a2a-9417-3eb92510c584",
      "image_url": "http://xxx/yy",
      "image_thumbnail_url": "http://xxx/yyy",
      "is_finished": true,
      "create_time": "2019-07-18T13:54:37.917+0000",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "chart_wound_id": "dd7e9ef9-2716-49cf-bfad-575168b786c6",
      "image_url": "http://xxx/zz",
      "image_thumbnail_url": "http://xxx/zzz",
      "is_finished": true,
      "create_time": "2019-07-18T13:54:37.917+0000",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "chart_wound_finished_count": 2,
  "chart_wound_unfinished_count": 0,
  "chart_wound_last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||
|chart_wounds|array of chart_wound|傷口||
|chart_wound_finished_count|int|傷口紀錄已完成編輯總數||
|chart_wound_unfinished_count|int|傷口紀錄未完成編輯總數||
|chart_wound_last_update_time|datetime|更新日期<br>(此傷口部位底下的所有傷口的最後的更新時間)|2019-07-18T13:54:37.917+0000|

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_sub_part_id|int|傷口子部位ID|28|
|wound_sub_part_code|string|傷口子部位代碼|hb_f_abdomen_c_1|
|wound_sub_part_description|string|傷口子部位說明|右上腹|

*chart_wound*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_thumbnail_url|string|縮圖URL|http://xxx/zzz|
|is_finished|bool|是否完成編輯|true<br>false|
|create_time|datetime|建立日期|2019-07-18T13:54:37.917+0000|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 5.10. GET /organizations/{organization-id}/charts/{chart-id}/wounds/chart-wound-sub-part-combination/{chart-wound-sub-part-combination}/newest-wounds-dressing-record

|欄位|描述|
|:---|:---|
|Description|查詢最新換藥紀錄|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口換藥紀錄|

**Note**

- 查詢該病患同個傷口部位分類的最新一筆換藥紀錄(的傷口詳細資料)
- 回傳完整傷口詳細資料，前端自行擷取換藥資訊部分欄位
- 要查詢已完成的
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|require|chart-wound-sub-part-combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|

***
**Response Body**

*HTTP 200*
```json
{
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "chart_id": "0bedc5a2-1b70-481e-bf51-7b1af426e1ce",
  "image_url": "http://xxx/yy",
  "image_width": 1920,
  "image_height": 1080,
  "image_thumbnail_url": "http://xxx/yyy",
  "image_thumbnail_width": 192,
  "image_thumbnail_height": 108,
  "wound_length": 105,
  "wound_width": 50,
  "wound_depth": 5,
  "bounding_box_position": "203,89",
  "bounding_box_width": 310,
  "bounding_box_height": 150,
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "chart_wound_parts": [
    {
      "chart_wound_part_id": "ea01d48c-5cff-4608-abd9-afd80ac2ddc0",
      "wound_part_id": 4,
      "wound_part_code": "abdoment",
      "wound_part_description": "腹部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-2",
          "wound_sub_part_description": "會陰區"
        }
      ]
    },
    {
      "chart_wound_part_id": "35237797-aab9-4d92-aa86-ba03448fdc2f",
      "wound_part_id": 8,
      "wound_part_code": "groin",
      "wound_part_description": "腹股溝及髖部",
      "chart_wound_sub_parts": [
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-4",
          "wound_sub_part_description": "左腹股溝"
        },
        {
          "chart_wound_sub_part_id": "9f2456d3-18ca-4645-9c22-81b056766308",
          "wound_sub_part_id": 1,
          "wound_sub_part_code": "0-6",
          "wound_sub_part_description": "左髖部"
        }
      ]
    }
  ],
  "chart_wound_ointments": [
    {
      "chart_wound_ointment_id": "be69f16c-98a4-4bf7-917b-bfb7a77b2e81",
      "ointment_id": 1,
      "ointment_name": "除疤凝膠"
    },
    {
      "chart_wound_ointment_id": "26b6963e-e027-4e39-9baa-f08e399c3053",
      "ointment_id": 2,
      "ointment_name": "抗生素藥膏"
    }
  ],
  "chart_wound_dressings": [
    {
      "chart_wound_dressing_id": "0c80481b-4d89-4601-9b83-3c20d78b720a",
      "dressing_id": 1,
      "dressing_name": "紗布"
    }
  ],
  "chart_wound_medical_materials": [
    {
      "chart_wound_medical_material_id": "18e7f43a-9582-43fd-9ee6-d52071f47950",
      "medical_material_id": 1,
      "medical_material_name": "特殊器材A"
    }
  ],
  "chart_wound_wound_treatments": [
    {
      "chart_wound_wound_treatment_id": "f08f1233-bf27-4c2a-96ab-af86ed20a7bc",
      "wound_treatment_id": 2,
      "wound_treatment_description": "切開排膿"
    }
  ],
  "change_dressing_note": "病患對xx藥膏過敏，建議改用oo藥膏",
  "is_finished": true,
  "is_finished_chart": true,
  "is_finished_part": true,
  "is_finished_change_dressing": true,
  "creator": "60722a28-7485-4fae-84dd-5be8ba003067"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_id|uuid|病患傷口ID|dd7e9ef9-2716-49cf-bfad-575168b786c6|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|image_url|string|原始圖片URL|http://xxx/zz|
|image_width|int|原始圖片寬|1920|
|image_height|int|原始圖片高|1080|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|image_thumbnail_width|int|縮圖寬|192|
|image_thumbnail_height|int|縮圖高|108|
|wound_length|numeric|傷口長(mm)|105|
|wound_width|numeric|傷口寬(mm)|50|
|wound_depth|numeric|傷口深(mm)|5|
|bounding_box_position|string|範圍框左上角位置|203,89|
|bounding_box_width|int|範圍框寬|310|
|bounding_box_height|int|範圍框高|150|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|chart_wound_parts|array of chart_wound_part|傷口部位||
|chart_wound_ointments|array of chart_wound_ointment|病患傷口換藥藥膏||
|chart_wound_dressings|array of chart_wound_dressing|病患傷口換藥敷料||
|chart_wound_medical_materials|array of chart_wound_medical_material|病患傷口換藥特殊器材||
|chart_wound_wound_treatments|array of chart_wound_wound_treatment|病患傷口換藥傷口處置||
|change_dressing_note|string|病患傷口換藥備註||
|is_finished|bool|是否完成編輯|true<br>false|
|is_finished_chart|bool|是否完成傷口紀錄之基本資料|true<br>false|
|is_finished_part|bool|是否完成傷口紀錄之部位資料|true<br>false|
|is_finished_change_dressing|bool|是否完成傷口紀錄之換藥資料|true<br>false|
|creator|string|建立者|81583525-9153-435d-b531-f8d128af8fb0|

*chart_wound_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_part_id|uuid|病患傷口部位ID|35237797-aab9-4d92-aa86-ba03448fdc2f|
|wound_part_id|int|傷口部位ID|8|
|wound_part_code|string|傷口部位代碼|abdoment|
|wound_part_description|string|傷口部位說明|腹股溝|
|chart_wound_sub_parts|array of chart_wound_sub_part|傷口子部位||

*chart_wound_sub_part*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_id|uuid|病患傷口子部位ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_sub_part_id|int|傷口子部位ID|8|
|wound_sub_part_code|string|傷口子部位代碼|abdoment|
|wound_sub_part_description|string|傷口子部位說明|腹股溝|

*chart_wound_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_ointment_id|uuid|病患傷口換藥藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*chart_wound_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_dressing_id|uuid|病患傷口換藥敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*chart_wound_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_medical_material_id|uuid|病患傷口換藥特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*chart_wound_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_wound_treatment_id|uuid|病患傷口換藥傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

<br>
<br>
<br>
<br>

### 5.11. GET /organizations/{organization-id}/charts/{chart-id}/wounds/exist-parts

|欄位|描述|
|:---|:---|
|Description|查詢已建立部位|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->傷口部位->從已建立部位挑選|

**Note**

- 病患所有傷口的部位組合分類
- 組合Response
  - 組合資料時，chart_wound_sub_part_combination將所有子部位組合起來，但是chart_wound_sub_part_combination_description會將中文名稱重複的過濾掉，例如"右大拇指"有分前後的ID，但是description都是"右大拇指"，這時候只要顯示一次，不要顯示成"右大拇指+右大拇指"
- 組合資料時，依據wound_sub_parts的index欄位，決定chart_wound_sub_part_combination和chart_wound_sub_part_combination_description的組合順序

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "chart_wound_sub_part_combination": "1-2-3",
    "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部"
  },
  {
    "chart_wound_sub_part_combination": "28-30",
    "chart_wound_sub_part_combination_description": "左小腿+左腳踝"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|

<br>
<br>
<br>
<br>

### 5.12. POST /organizations/{organization-id}/charts/{chart-id}/wounds/image

|欄位|描述|
|:---|:---|
|Description|上傳傷口原始圖片|
|Pagination|N/A|
|Access Control|恩主公醫師、恩主公護理師、恩主公高級管理者、恩主公一般管理者、長照機構護理師、長照機構高級管理者、長照機構一般管理者。|
|Using|App->工作/關懷清單->傷口列表->新增傷口紀錄|

**Note**

- Request Body 型態為 binary
- 一次只能傳一張圖
- content-type = image/jpeg
- API收到圖檔後，依據URL傳入的organization-id和chart-id當作資料夾結構存放至Blob，存放結構為"組織ID/病患ID/wounds/圖片ID.jpg"(圖片ID為自動產生的uuid)
- 回傳圖片URL，Response Body 格式為JSON

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|12b5a2c7-a13d-a98b-123a-44215abda121|

***
**Request Body**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|image_file|image/jpeg|原始圖片檔案||

***
**Response Body**

*HTTP 200*
```json
{
  "image_url": "https://123.222.333.444/e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/wounds/9f2456d3-18ca-4645-9c22-81b056766308.jpg"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|image_url|string|傷口原圖URL|https://123.222.333.444/e08cdc75-380a-4b68-a446-9574197a4653/12b5a2c7-a13d-a98b-123a-44215abda121/wounds/9f2456d3-18ca-4645-9c22-81b056766308.jpg|

<br>
<br>
<br>
<br>

## 6. health-behavior-histories
### 6.1. GET /health-behavior-histories

|欄位|描述|
|:---|:---|
|Description|查詢個人史|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->個人史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "health_behavior_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "抽煙",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "health_behavior_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "喝酒",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|health_behavior_history_description|string|個人史說明|抽煙|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 6.2. POST /health-behavior-histories

|欄位|描述|
|:---|:---|
|Description|新增個人史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->個人史|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "health_behavior_history_description": "抽煙",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|health_behavior_history_description|string|個人史說明|抽煙|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 201*
```json
{
  "health_behavior_history_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "health_behavior_history_description": "抽煙",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|health_behavior_history_description|string|個人史說明|抽煙|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 6.3. PUT /health-behavior-histories/{health-behavior-history-id}

|欄位|描述|
|:---|:---|
|Description|修改個人史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->個人史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health-behavior-history-id|int|個人史ID|1|

***
**Request Body**

```json
{
  "health_behavior_history_description": "抽煙"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_description|string|個人史說明|抽煙|

***
**Response Body**

*HTTP 200*
```json
{
  "health_behavior_history_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "health_behavior_history_description": "抽煙",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|health_behavior_history_description|string|個人史說明|抽煙|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 6.4. PUT /organizations/{organization-id}/health-behavior-histories/index

|欄位|描述|
|:---|:---|
|Description|修改個人史排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->個人史|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "health_behavior_history_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "health_behavior_history_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health_behavior_history_id|int|個人史ID|1|
|require|index|int|排序順序|0|
|require|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "health_behavior_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "抽煙",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "health_behavior_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "喝酒",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|health_behavior_history_description|string|個人史說明|抽煙|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 6.5. PUT /health-behavior-histories/{health-behavior-history-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除個人史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->個人史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|health-behavior-history-id|int|個人史ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "health_behavior_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "抽煙",
    "is_delete": true,
    "index": -1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "health_behavior_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "喝酒",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "health_behavior_history_id": 3,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "health_behavior_history_description": "吃檳榔",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|health_behavior_history_id|int|個人史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|health_behavior_history_description|string|個人史說明|抽煙|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|被刪除的項目index為-1<br>其餘項目重新排序(從0開始)|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

## 7. medical-histories
### 7.1. GET /medical-histories

|欄位|描述|
|:---|:---|
|Description|查詢疾病史|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->疾病史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "心臟病",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "糖尿病",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_history_description|string|疾病史說明|糖尿病|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 7.2. POST /medical-histories

|欄位|描述|
|:---|:---|
|Description|新增疾病史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->疾病史|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_history_description": "抽煙",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|medical_history_description|string|疾病史說明|糖尿病|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 201*
```json
{
  "medical_history_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_history_description": "糖尿病",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_history_description|string|疾病史說明|糖尿病|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 7.3. PUT /medical-histories/{medical-history-id}

|欄位|描述|
|:---|:---|
|Description|修改疾病史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->疾病史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical-history-id|int|疾病史ID|1|

***
**Request Body**

```json
{
  "medical_history_description": "糖尿病"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_description|string|疾病史說明|糖尿病|

***
**Response Body**

*HTTP 200*
```json
{
  "medical_history_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_history_description": "糖尿病",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_history_description|string|疾病史說明|糖尿病|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 7.4. PUT /organizations/{organization-id}/medical-histories/index

|欄位|描述|
|:---|:---|
|Description|修改疾病史排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->疾病史|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "medical_history_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_history_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_history_id|int|疾病史ID|1|
|require|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "心臟病",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "糖尿病",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_history_description|string|疾病史說明|糖尿病|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 7.5. PUT /medical-histories/{medical-history-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除疾病史|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->病患基本資料選單->疾病史|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical-history-id|int|疾病史ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_history_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "心臟病",
    "is_delete": true,
    "index": -1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_history_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "糖尿病",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_history_id": 3,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_history_description": "高血壓",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_history_id|int|疾病史ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_history _description|string|疾病史說明|糖尿病|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|被刪除的項目index為-1<br>其餘項目重新排序(從0開始)|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>


## 8. ointments
### 8.1. GET /ointments

|欄位|描述|
|:---|:---|
|Description|查詢藥膏|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->藥膏|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "ointment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "除疤凝膠",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "ointment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "抗生素藥膏",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|ointment_name|string|藥膏名稱|除疤凝膠|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 8.2. POST /ointments

|欄位|描述|
|:---|:---|
|Description|新增藥膏|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->藥膏|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "ointment_name": "除疤凝膠",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|ointment_name|string|藥膏名稱|除疤凝膠|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 201*
```json
{
  "ointment_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "ointment_name": "除疤凝膠",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|ointment_name|string|藥膏名稱|除疤凝膠|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 8.3. PUT /ointments/{ointment-id}

|欄位|描述|
|:---|:---|
|Description|修改藥膏|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->藥膏|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|ointment-id|int|藥膏ID|1|

***
**Request Body**

```json
{
  "ointment_name": "除疤凝膠"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|ointment_name|string|藥膏名稱|除疤凝膠|

***
**Response Body**

*HTTP 200*
```json
{
  "ointment_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "ointment_name": "除疤凝膠",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|ointment_name|string|藥膏名稱|除疤凝膠|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 8.4. PUT /organizations/{organization-id}/ointments/index

|欄位|描述|
|:---|:---|
|Description|修改藥膏排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->藥膏|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "ointment_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "ointment_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|ointment_id|int|藥膏ID|1|
|require|index|int|排序順序|0|
|require|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "ointment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "除疤凝膠",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "ointment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "抗生素藥膏",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|ointment_name|string|藥膏名稱|除疤凝膠|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 8.5. PUT /ointments/{ointment-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除藥膏|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->藥膏|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|ointment-id|int|藥膏ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "ointment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "除疤凝膠",
    "is_delete": true,
    "index": -1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "ointment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "抗生素藥膏",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "ointment_id": 3,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "ointment_name": "燙傷藥膏",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|ointment_name|string|藥膏名稱|除疤凝膠|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|被刪除的項目index為-1<br>其餘項目重新排序(從0開始)|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>


## 9. dressings
### 9.1. GET /dressings

|欄位|描述|
|:---|:---|
|Description|查詢敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "dressing_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "images": [
      {
        "dressing_image_id": "07b7295f-41ff-4faf-8a9c-de13466b98d6",
        "image_id": "f2799d93-d5d1-4d64-b4d9-ff11deb8d395",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg"
      },
      {
        "dressing_image_id": "5eedbe9d-b47c-4512-8448-c39179a99d76",
        "image_id": "1542530a-0be4-4e49-b804-e85c84fc64ea",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 0,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "dressing_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "images": [
      {
        "dressing_image_id": "63ccbbb5-8020-4e9e-a406-23c33a72f68f",
        "image_id": "d9fb46f6-8b19-40c7-807b-381238210088",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg"
      },
      {
        "dressing_image_id": "ce9359a1-4be5-4f5b-a160-ec669c2a286e",
        "image_id": "ab6b3ae0-b2e1-4b7f-beca-1388524cff71",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fcdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "cdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fbd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "bd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 1,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|note_1|string|備註一||
|note_2|string|備註二||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*image*
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_image_id|string|敷料圖片ID|07b7295f-41ff-4faf-8a9c-de13466b98d6|
|image_id|string|圖片ID|f2799d93-d5d1-4d64-b4d9-ff11deb8d395|
|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|

<br>
<br>
<br>
<br>

### 9.2. POST /dressings

|欄位|描述|
|:---|:---|
|Description|新增敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "dressing_name": "紗布",
  "dressing_description": "抗菌紗布",
  "dressing_advantage": "適用於各類傷口",
  "dressing_price": 220,
  "images": [
    {
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_width": 1920,
      "image_height": 1080,
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
      "image_thumbnail_width": 192,
      "image_thumbnail_height": 108
    },
    {
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_width": 1920,
      "image_height": 1080,
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
      "image_thumbnail_width": 192,
      "image_thumbnail_height": 108
    }
  ],
  "is_show_description": true,
  "note_1": "",
  "note_2": "",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|dressing_name|string|敷料名稱|紗布|
|require|dressing_description|string|敷料介紹|抗菌紗布|
|require|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|require|dressing_price|numeric|敷料價格|220|
|require|images|array of image|敷料圖片||
|require|is_show_description|bool|是否顯示在APP下拉選單上|true<br>false|
|require|note_1|string|備註一||
|require|note_2|string|備註二||
|require|is_delete|bool|是否已刪除|true<br>false|

*image*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|require|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|require|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|require|image_width|numeric|原始圖片寬|1920|
|require|image_height|numeric|原始圖片高|1080|
|require|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|require|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|require|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|require|image_thumbnail_width|numeric|縮圖寬|192|
|require|image_thumbnail_height|numeric|縮圖高|108|

***
**Response Body**

*HTTP 201*
```json
{
  "dressing_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "dressing_name": "紗布",
  "dressing_description": "抗菌紗布",
  "dressing_advantage": "適用於各類傷口",
  "dressing_price": 220,
  "images": [
    {
      "dressing_image_id": "07b7295f-41ff-4faf-8a9c-de13466b98d6",
      "image_id": "f2799d93-d5d1-4d64-b4d9-ff11deb8d395",
      "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg"
    },
    {
      "dressing_image_id": "5eedbe9d-b47c-4512-8448-c39179a99d76",
      "image_id": "1542530a-0be4-4e49-b804-e85c84fc64ea",
      "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg"
    }
  ],
  "is_show_description": true,
  "is_delete": false,
  "index": 0,
  "note_1": "",
  "note_2": "",
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|is_delete|bool|是否已刪除|true<br>false|
|is_show_description|bool|是否顯示在APP下拉選單上|true<br>false|
|index|int|排序順序|0|
|note_1|string|備註一||
|note_2|string|備註二||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*image*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_image_id|string|敷料圖片ID|07b7295f-41ff-4faf-8a9c-de13466b98d6|
|image_id|string|圖片ID|f2799d93-d5d1-4d64-b4d9-ff11deb8d395|
|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|

<br>
<br>
<br>
<br>

### 9.3. PUT /dressings/{dressings-id}

|欄位|描述|
|:---|:---|
|Description|修改敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressings-id|int|敷料ID|1|

***
**Request Body**

```json
{
  "dressing_name": "紗布",
  "dressing_description": "抗菌紗布",
  "dressing_advantage": "適用於各類傷口",
  "dressing_price": 220,
  "images": [
    {
      "dressing_image_id": "18fca0df-e11f-4f97-8291-44b6d98e7afb",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_width": 1920,
      "image_height": 1080,
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
      "image_thumbnail_width": 192,
      "image_thumbnail_height": 108
    },
    {
      "dressing_image_id": "c8186df8-de1c-4329-bb8c-471dd3bfe37a",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_width": 1920,
      "image_height": 1080,
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
      "image_thumbnail_width": 192,
      "image_thumbnail_height": 108
    }
  ],
  "is_show_description": true,
  "note_1": "",
  "note_2": ""
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressing_name|string|敷料名稱|紗布|
|require|dressing_description|string|敷料介紹|抗菌紗布|
|require|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|require|dressing_price|numeric|敷料價格|220|
|require|images|array of image|敷料圖片||
|require|is_show_description|bool|是否顯示在APP下拉選單上|true<br>false|
|require|note_1|string|備註一||
|require|note_2|string|備註二||
|require|is_delete|bool|是否已刪除|true<br>false|

*image*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressing_image_id|string|敷料圖片ID|18fca0df-e11f-4f97-8291-44b6d98e7afb|
|require|is_add|bool|是否為新增的圖片|true<br>false|
|require|is_delete|bool|是否刪除圖片|true<br>false|
|require|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|require|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|require|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|require|image_width|numeric|原始圖片寬|1920|
|require|image_height|numeric|原始圖片高|1080|
|require|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|require|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|require|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|require|image_thumbnail_width|numeric|縮圖寬|192|
|require|image_thumbnail_height|numeric|縮圖高|108|

***
**Response Body**

*HTTP 200*
```json
{
  "dressing_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "dressing_name": "紗布",
  "dressing_description": "抗菌紗布",
  "dressing_advantage": "適用於各類傷口",
  "dressing_price": 220,
  "images": [
    {
      "dressing_image_id": "07b7295f-41ff-4faf-8a9c-de13466b98d6",
      "image_id": "f2799d93-d5d1-4d64-b4d9-ff11deb8d395",
      "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
      "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg"
    },
    {
      "dressing_image_id": "5eedbe9d-b47c-4512-8448-c39179a99d76",
      "image_id": "1542530a-0be4-4e49-b804-e85c84fc64ea",
      "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
      "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
      "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
      "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg"
    }
  ],
  "is_show_description": true,
  "is_delete": false,
  "index": 0,
  "note_1": "",
  "note_2": "",
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|is_delete|bool|是否已刪除|true<br>false|
|is_show_description|bool|是否顯示在APP下拉選單上|true<br>false|
|index|int|排序順序|0|
|note_1|string|備註一||
|note_2|string|備註二||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*image*
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_image_id|string|敷料圖片ID|07b7295f-41ff-4faf-8a9c-de13466b98d6|
|image_id|string|圖片ID|f2799d93-d5d1-4d64-b4d9-ff11deb8d395|
|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|

<br>
<br>
<br>
<br>

### 9.4. PUT /organizations/{organization-id}/dressings/index

|欄位|描述|
|:---|:---|
|Description|修改敷料排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "dressing_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "dressing_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressing_id|int|敷料ID|1|
|require|index|int|排序順序|0|
|require|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "dressing_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "images": [
      {
        "dressing_image_id": "07b7295f-41ff-4faf-8a9c-de13466b98d6",
        "image_id": "f2799d93-d5d1-4d64-b4d9-ff11deb8d395",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg"
      },
      {
        "dressing_image_id": "5eedbe9d-b47c-4512-8448-c39179a99d76",
        "image_id": "1542530a-0be4-4e49-b804-e85c84fc64ea",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 0,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "dressing_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "images": [
      {
        "dressing_image_id": "63ccbbb5-8020-4e9e-a406-23c33a72f68f",
        "image_id": "d9fb46f6-8b19-40c7-807b-381238210088",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg"
      },
      {
        "dressing_image_id": "ce9359a1-4be5-4f5b-a160-ec669c2a286e",
        "image_id": "ab6b3ae0-b2e1-4b7f-beca-1388524cff71",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fcdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "cdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fbd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "bd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 1,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|note_1|string|備註一||
|note_2|string|備註二||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*image*
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_image_id|string|敷料圖片ID|07b7295f-41ff-4faf-8a9c-de13466b98d6|
|image_id|string|圖片ID|f2799d93-d5d1-4d64-b4d9-ff11deb8d395|
|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 9.5. PUT /dressings/{dressings-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|dressings-id|int|敷料ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "dressing_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "images": [
      {
        "dressing_image_id": "07b7295f-41ff-4faf-8a9c-de13466b98d6",
        "image_id": "f2799d93-d5d1-4d64-b4d9-ff11deb8d395",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "71a53c5b-789b-4d2f-bc43-06df99161453.jpg"
      },
      {
        "dressing_image_id": "5eedbe9d-b47c-4512-8448-c39179a99d76",
        "image_id": "1542530a-0be4-4e49-b804-e85c84fc64ea",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "0839d720-652f-463d-9c7e-d32e8ad90b08.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F04fa2f02-488c-421a-8ef3-c631d12f1845.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "04fa2f02-488c-421a-8ef3-c631d12f1845.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 0,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "dressing_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "images": [
      {
        "dressing_image_id": "63ccbbb5-8020-4e9e-a406-23c33a72f68f",
        "image_id": "d9fb46f6-8b19-40c7-807b-381238210088",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "34efe17b-4dbd-4d64-a5fc-48455b2599a7.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "5cc9c9a0-b4ae-4aa1-a0f4-5c71f79bdcb3.jpg"
      },
      {
        "dressing_image_id": "ce9359a1-4be5-4f5b-a160-ec669c2a286e",
        "image_id": "ab6b3ae0-b2e1-4b7f-beca-1388524cff71",
        "image_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fcdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_name": "cdb8f2a3-dd42-4033-88d5-4ec807360733.jpg",
        "image_thumbnail_url": "http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fbd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg",
        "image_thumbnail_path": "e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/",
        "image_thumbnail_name": "bd3cdff8-ae60-4d1c-a981-eb68559c2ef2.jpg"
      }
    ],
    "is_show_description": true,
    "is_delete": false,
    "index": 1,
    "note_1": "",
    "note_2": "",
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|images|array of image|敷料圖片||
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|note_1|string|備註一||
|note_2|string|備註二||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*image*
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_image_id|string|敷料圖片ID|07b7295f-41ff-4faf-8a9c-de13466b98d6|
|image_id|string|圖片ID|f2799d93-d5d1-4d64-b4d9-ff11deb8d395|
|image_url|string|原始圖片URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2Fc1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_path|string|原始圖片檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_name|string|原始圖片檔名|c1ee7f80-5137-414f-9fbc-f6c98b9c9ce6.jpg|
|image_thumbnail_url|string|縮圖URL|http://mydomain/contentService/e08cdc75-380a-4b68-a446-9574197a4653%2Fdressing_images%2F71a53c5b-789b-4d2f-bc43-06df99161453.jpg|
|image_thumbnail_path|string|縮圖檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing_images/|
|image_thumbnail_name|string|縮圖檔名|71a53c5b-789b-4d2f-bc43-06df99161453.jpg|

<br>
<br>
<br>
<br>

### 9.6. POST /organizations/{organization-id}/dressings/import

|欄位|描述|
|:---|:---|
|Description|匯入敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者，恩主公一般管理者，長照機構高級管理者，長照機構一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料<br>Web->長照機構後台->選單管理->換藥紀錄選單->敷料|

**Note**
- dressing_id為空白時，視為新增
- dressing_id不為空白時
  - 若對應到ID且該筆資料為自己組織的，則為修改
  - 若對應到ID但該筆資料為其他組織的，則為錯誤資料
  - 若對應不到ID，也視為錯誤資料
- 新增時index欄位自動塞入最大值+1(最新的排在最後面)
- 因Excel不易夾帶圖片，故匯入不包含圖片

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
[
  {
    "dressing_id": 1,
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "note_1": "",
    "note_2": ""
  },
  {
    "dressing_id": 2,
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "note_1": "",
    "note_2": ""
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|dressing_id|int|敷料ID|空白的視為新增|
|require|dressing_name|string|敷料名稱|紗布|
|require|dressing_description|string|敷料介紹|抗菌紗布|
|require|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|require|dressing_price|numeric|敷料價格|220|
|require|note_1|string|備註一||
|require|note_2|string|備註二||

***
**Response Body**

*HTTP 200*
```json
{
  "insert_id": [
    99,
    100
  ],
  "update_id": [
    80,
    86
  ],
  "error_row": [
    {
      "dressing_id": 1,
      "dressing_name": "紗布",
      "dressing_description": "抗菌紗布",
      "dressing_advantage": "適用於各類傷口",
      "dressing_price": 220,
      "note_1": "",
      "note_2": ""
    }
  ]
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|insert_id|array of int|新增項目的ID||
|update|array of int|修改項目的ID||
|error_row|arror of dressing|有問題的資料列||

*dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|note_1|string|備註一||
|note_2|string|備註二||

<br>
<br>
<br>
<br>

### 9.7. GET /organizations/{organization-id}/dressings/export

|欄位|描述|
|:---|:---|
|Description|匯出敷料|
|Pagination|N/A|
|Access Control|恩主公高級管理者，恩主公一般管理者，長照機構高級管理者，長照機構一般管理者|
|Using|Web->選單管理->換藥紀錄選單->敷料<br>Web->長照機構後台->選單管理->換藥紀錄選單->敷料|

**Note**
- 因Excel不易夾帶圖片，故匯出不包含圖片

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "dressing_id": 1,
    "dressing_name": "紗布",
    "dressing_description": "抗菌紗布",
    "dressing_advantage": "適用於各類傷口",
    "dressing_price": 220,
    "note_1": "",
    "note_2": ""
  },
  {
    "dressing_id": 2,
    "dressing_name": "人工皮",
    "dressing_description": "加速癒合",
    "dressing_advantage": "可以取代紗布，對傷口癒合較好",
    "dressing_price": 310,
    "note_1": "",
    "note_2": ""
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|
|dressing_description|string|敷料介紹|抗菌紗布|
|dressing_advantage|string|敷料適用時機及優點|適用於各類傷口|
|dressing_price|numeric|敷料價格|220|
|note_1|string|備註一||
|note_2|string|備註二||

<br>
<br>
<br>
<br>


## 10. medical-materials
### 10.1. GET /medical-materials

|欄位|描述|
|:---|:---|
|Description|查詢特殊器材|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->特殊器材|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_material_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "Vacuum",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_material_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "注射器",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_material_name|string|特殊器材名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 10.2. POST /medical-materials

|欄位|描述|
|:---|:---|
|Description|新增特殊器材|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->特殊器材|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_material_name": "Vacuum",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|medical_material_name|string|特殊器材名稱|Vacuum|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 201*
```json
{
  "medical_material_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_material_name": "Vacuum",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_material_name|string|特殊器材名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 10.3. PUT /medical-materials/{medical-material-id}

|欄位|描述|
|:---|:---|
|Description|修改特殊器材|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->特殊器材|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical-material-id|int|特殊器材ID|1|

***
**Request Body**

```json
{
  "medical_material_name": "Vacuum"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_material_name|string|特殊器材名稱|Vacuum|

***
**Response Body**

*HTTP 200*
```json
{
  "medical_material_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "medical_material_name": "Vacuum",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_material_name|string|特殊器材名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 10.4. PUT /organizations/{organization-id}/medical-materials/index

|欄位|描述|
|:---|:---|
|Description|修改特殊器材排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->特殊器材|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "medical_material_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_material_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical_material_id|int|特殊器材ID|1|
|require|index|int|排序順序|0|
|require|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_material_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "Vacuum",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_material_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "注射器",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_material_name|string|特殊器材名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 10.5. PUT /medical-materials/{medical-material-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除特殊器材|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->特殊器材|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|medical-material-id|int|特殊器材ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "medical_material_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "Vacuum",
    "is_delete": true,
    "index": -1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_material_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "注射器",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "medical_material_id": 3,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "medical_material_name": "聽診器",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|medical_material_name|string|特殊器材名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|被刪除的項目index為-1<br>其餘項目重新排序(從0開始)|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>


## 11. wound-treatments
### 11.1. GET /wound-treatments

|欄位|描述|
|:---|:---|
|Description|查詢現行處置|
|Pagination|N/A|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->現行處置|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_treatment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "吸收治療",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "wound_treatment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "電燒燒灼",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|wound_treatment_description|string|現行處置名稱|吸收治療|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 11.2. POST /wound-treatments

|欄位|描述|
|:---|:---|
|Description|新增現行處置|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->現行處置|

**Note**

新增時index欄位自動塞入最大值+1(最新的排在最後面)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "wound_treatment_description": "吸收治療",
  "is_delete": false
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|wound_treatment_description|string|現行處置名稱|吸收治療|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 201*
```json
{
  "wound_treatment_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "wound_treatment_description": "吸收治療",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|wound_treatment_description|string|現行處置名稱|吸收治療|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 11.3. PUT /wound-treatments/{wound-treatment-id}

|欄位|描述|
|:---|:---|
|Description|修改現行處置|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->現行處置|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound-treatment-id|int|現行處置ID|1|

***
**Request Body**

```json
{
  "wound_treatment_description": "吸收治療"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_treatment_description|string|現行處置名稱|吸收治療|

***
**Response Body**

*HTTP 200*
```json
{
  "wound_treatment_id": 1,
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "wound_treatment_description": "吸收治療",
  "is_delete": false,
  "index": 0,
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|wound_treatment_description|string|現行處置名稱|吸收治療|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 11.4. PUT /organizations/{organization-id}/wound-treatments/index

|欄位|描述|
|:---|:---|
|Description|修改現行處置排序|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->現行處置|

**Note**

需用last_update_time檢查沒有其他人在同時修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|

***
**Request Body**

```json
[
  {
    "wound_treatment_id": 1,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "wound_treatment_id": 2,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound_treatment_id|int|現行處置ID|1|
|require|index|int|排序順序|0|
|require|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_treatment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "吸收治療",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "wound_treatment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "電燒燒灼",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|wound_treatment_description|string|現行處置名稱|吸收治療|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|0|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*HTTP 400*
```json
{
  "error_message": "該頁面排序已被修改，系統將重新整理頁面"
}
```

<br>
<br>
<br>
<br>

### 11.5. PUT /wound-treatments/{wound-treatment-id}/is-delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除現行處置|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->選單管理->換藥紀錄選單->現行處置|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|wound-treatment-id|int|現行處置ID|1|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "wound_treatment_id": 1,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "吸收治療",
    "is_delete": true,
    "index": -1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "wound_treatment_id": 2,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "電燒燒灼",
    "is_delete": false,
    "index": 0,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "wound_treatment_id": 3,
    "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
    "wound_treatment_description": "切開排膿",
    "is_delete": false,
    "index": 1,
    "last_update_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|wound_treatment_description|string|現行處置名稱|Vacuum|
|is_delete|bool|是否已刪除|true<br>false|
|index|int|排序順序|被刪除的項目index為-1<br>其餘項目重新排序(從0開始)|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

## 12. consultants
### 12.1. GET /consultants

|欄位|描述|
|:---|:---|
|Description|查詢諮詢對象|
|Pagination|offset-limit base|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->諮詢設定管理|

**Note**


***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant-level|string|諮詢層級|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|option|query|string|模糊查詢畫面上欄位|employee_name<br>position_description<br>division_name<br>higher_consultant_employee_name<br>higher_consultant_position_description<br>higher_consultant_division_name|
|option|sortby|string|按照哪個屬性排序|employee_name<br>position_description<br>division_name<br>higher_consultant_employee_name<br>higher_consultant_position_description<br>higher_consultant_division_name<br>last_update_time|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200 諮詢窗口/醫師/傷口個管師*
```json
{
  "edges": [
    {
      "consultant_id": "5d39be91-b76c-4a1a-9238-e4eb3bfc8785",
      "consultant_level": "doctor",
      "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
      "employee_name": "王小明",
      "position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
      "position_description": "醫師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科",
      "higher_consultant_user_id": "d655f0a7-82c6-4a85-8301-c4213bfab8c7",
      "higher_consultant_employee_name": "莊孝維",
      "higher_consultant_position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
      "higher_consultant_position_description": "醫師",
      "higher_consultant_division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "higher_consultant_division_name": "一般外科",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "consultant_id": "64830f1c-0349-4358-aa63-b5e116a8e580",
      "consultant_level": "case_manager",
      "consultant_user_id": "8577f17c-9d7c-4044-9b36-f0fe78d232b7",
      "employee_name": "李大龍",
      "position_id": "7d92c977-6716-48d6-8d12-efbc4e690b7b",
      "position_description": "護理師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科",
      "higher_consultant_user_id": "d655f0a7-82c6-4a85-8301-c4213bfab8c7",
      "higher_consultant_employee_name": "莊孝維",
      "higher_consultant_position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
      "higher_consultant_position_description": "醫師",
      "higher_consultant_division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "higher_consultant_division_name": "一般外科",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

*HTTP 200 傷口專家*
```json
{
  "edges": [
    {
      "consultant_id": "5d39be91-b76c-4a1a-9238-e4eb3bfc8785",
      "consultant_level": "doctor",
      "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
      "employee_name": "王小明",
      "position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
      "position_description": "醫師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科",
      "higher_consultant_user_id": "",
      "higher_consultant_employee_name": "",
      "higher_consultant_position_id": "",
      "higher_consultant_position_description": "",
      "higher_consultant_division_id": "",
      "higher_consultant_division_name": "",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "consultant_id": "64830f1c-0349-4358-aa63-b5e116a8e580",
      "consultant_level": "case_manager",
      "consultant_user_id": "8577f17c-9d7c-4044-9b36-f0fe78d232b7",
      "employee_name": "李大龍",
      "position_id": "7d92c977-6716-48d6-8d12-efbc4e690b7b",
      "position_description": "護理師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科",
      "higher_consultant_user_id": "",
      "higher_consultant_employee_name": "",
      "higher_consultant_position_id": "",
      "higher_consultant_position_description": "",
      "higher_consultant_division_id": "",
      "higher_consultant_division_name": "",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultant_id|uuid|諮詢對象ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|
|consultant_level|string|諮詢層級|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_user_id|uuid|使用者ID|fadf5b27-e9bc-4290-8596-a1a8b3f93923|
|employee_name|string|員工姓名|王小明|
|position_id|uuid|職稱ID|6a8fde9e-ed1d-4af3-b062-0177d30db805|
|position_description|string|職稱說明|醫師|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_name|string|科別名稱|一般外科|
|higher_consultant_user_id|uuid|向上諮詢使用者ID|d655f0a7-82c6-4a85-8301-c4213bfab8c7|
|higher_consultant_employee_name|string|向上諮詢員工姓名|莊孝維|
|higher_consultant_position_id|uuid|向上諮詢職稱ID|6a8fde9e-ed1d-4af3-b062-0177d30db805|
|higher_consultant_position_description|string|向上諮詢職稱說明|醫師|
|higher_consultant_division_id|uuid|向上諮詢科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|higher_consultant_division_name|string|向上諮詢科別名稱|一般外科|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|2|

<br>
<br>
<br>
<br>

### 12.2. POST /consultants

|欄位|描述|
|:---|:---|
|Description|新增諮詢對象|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->諮詢設定管理|

**Note**
- 諮詢窗口/醫師/傷口個管師需要輸入向上諮詢對象的user_id
- 傷口專家則不需要(留空白即可)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

*諮詢窗口/醫師/傷口個管師*
```json
{
  "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
  "consultant_level": "doctor",
  "higher_consultant_user_id": "d655f0a7-82c6-4a85-8301-c4213bfab8c7"
}
```

*傷口專家*
```json
{
  "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
  "consultant_level": "doctor",
  "higher_consultant_user_id": ""
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant_user_id|uuid|使用者ID|fadf5b27-e9bc-4290-8596-a1a8b3f93923|
|require|consultant_level|string|諮詢層級|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|require|higher_consultant_user_id|uuid|向上諮詢使用者ID|d655f0a7-82c6-4a85-8301-c4213bfab8c7|

***
**Response Body**

*HTTP 201 諮詢窗口/醫師/傷口個管師*
```json
{
  "consultant_id": "5d39be91-b76c-4a1a-9238-e4eb3bfc8785",
  "consultant_level": "doctor",
  "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
  "employee_name": "王小明",
  "position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
  "position_description": "醫師",
  "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "division_name": "一般外科",
  "higher_consultant_user_id": "d655f0a7-82c6-4a85-8301-c4213bfab8c7",
  "higher_consultant_employee_name": "莊孝維",
  "higher_consultant_position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
  "higher_consultant_position_description": "醫師",
  "higher_consultant_division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "higher_consultant_division_name": "一般外科",
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

*HTTP 201 傷口專家*
```json
{
  "consultant_id": "5d39be91-b76c-4a1a-9238-e4eb3bfc8785",
  "consultant_level": "doctor",
  "consultant_user_id": "fadf5b27-e9bc-4290-8596-a1a8b3f93923",
  "employee_name": "王小明",
  "position_id": "6a8fde9e-ed1d-4af3-b062-0177d30db805",
  "position_description": "醫師",
  "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "division_name": "一般外科",
  "higher_consultant_user_id": "",
  "higher_consultant_employee_name": "",
  "higher_consultant_position_id": "",
  "higher_consultant_position_description": "",
  "higher_consultant_division_id": "",
  "higher_consultant_division_name": "",
  "last_update_time": "2019-07-18T13:54:37.917+0000"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultant_id|uuid|諮詢對象ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|
|consultant_level|string|諮詢層級|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_user_id|uuid|使用者ID|fadf5b27-e9bc-4290-8596-a1a8b3f93923|
|employee_name|string|員工姓名|王小明|
|position_id|uuid|職稱ID|6a8fde9e-ed1d-4af3-b062-0177d30db805|
|position_description|string|職稱說明|醫師|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_name|string|division_name|一般外科|
|higher_consultant_user_id|uuid|向上諮詢使用者ID|d655f0a7-82c6-4a85-8301-c4213bfab8c7|
|higher_consultant_employee_name|string|向上諮詢員工姓名|莊孝維|
|higher_consultant_position_id|uuid|向上諮詢職稱ID|6a8fde9e-ed1d-4af3-b062-0177d30db805|
|higher_consultant_position_description|string|向上諮詢職稱說明|醫師|
|higher_consultant_division_id|uuid|向上諮詢科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|higher_consultant_division_name|string|向上諮詢科別名稱|一般外科|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 12.3. DELETE /consultants/{consultant-id}

|欄位|描述|
|:---|:---|
|Description|刪除諮詢對象|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->諮詢設定管理|

**Note**

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant|uuid|諮詢對象ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 204*
```json
N/A
```

<br>
<br>
<br>
<br>

### 12.4. GET /users/{user-id}/consultants

|欄位|描述|
|:---|:---|
|Description|查詢其他諮詢對象|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢->諮詢其他對象|

**Note**

- 依據使用者層級，搜尋該使用者可以提交的諮詢對象
- 使用者為恩主公護理師，則提交對象可以是傷口個管師及醫師
- 使用者為傷口個管師，則提交對象只能是傷口專家
- 使用者為諮詢窗口，則提交對象只能是傷口專家
- 使用者為醫師，則提交對象只可是傷口專家
- 其他使用者則不可使用諮詢其他對象功能
- 排序依據科別>職稱>姓名

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|division-id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|option|query|string|模糊查詢可諮詢對象的姓名、職稱||
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "18aa9db1-c334-467f-9e85-a190921f723c",
      "consultant_id": "18aa9db1-c334-467f-9e85-a190921f723c",
      "consultant_level": "case_manager",
      "consultant_level_description": "傷口個管師",
      "consultant_user_id": "c6c2fc4a-0a2b-4efb-9aa2-9205631d3676",
      "employee_name": "林Ｏ河",
      "position_id": "97ec7c4e-2022-477c-b4a8-05da9c3b2f7d",
      "position_description": "專科護理師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科"
    },
    {
      "cursor": "6479a9ee-a55e-4a41-81c7-56ecab832872",
      "consultant_id": "6479a9ee-a55e-4a41-81c7-56ecab832872",
      "consultant_level": "doctor",
      "consultant_level_description": "醫師",
      "consultant_user_id": "24bebb37-66d0-4faf-a2ee-2abc766e94eb",
      "employee_name": "李Ｏ新",
      "position_id": "bf1b82cb-3b70-4f30-a55d-713d990df1b5",
      "position_description": "住院醫師",
      "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
      "division_name": "一般外科"
    }
  ],
  "page_info": {
    "has_next_page": false,
    "has_previous_page": false,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|uuid|定位點(提供下次分頁查詢用)，這邊用諮詢對象ID當作cursor|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|
|consultant_id|uuid|諮詢對象ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|
|consultant_level|string|諮詢層級|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_level_description|string|諮詢層級說明|傷口專家<br>醫師<br>傷口個管師<br>諮詢窗口|
|consultant_user_id|uuid|使用者ID|fadf5b27-e9bc-4290-8596-a1a8b3f93923|
|employee_name|string|員工姓名|王小明|
|position_id|uuid|職稱ID|6a8fde9e-ed1d-4af3-b062-0177d30db805|
|position_description|string|職稱說明|醫師|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_name|string|科別名稱|一般外科|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

## 13. consultations
### 13.1. GET /users/{user-id}/consultations/be-consulted

|欄位|描述|
|:---|:---|
|Description|查詢諮詢回覆管理清單(人家向我提出的諮詢)|
|Pagination|cursored-base|
|Access Control|恩主公醫師(包含傷口專家)、傷口個管師、諮詢窗口|
|Using|App->諮詢回覆管理|

**Note**

- 利用consultation_board_members的user_id為自己且member_type=consultant回推consultations的清單
- 從被諮詢者角度看，已回覆但是回覆者是另一個討論版的時候，需註記"xxx醫師已回覆"，不可進入修改
- 要分"新提出諮詢"和"舊的諮詢清單"(最新的清單在最前面)
  - 最新提出諮詢為24小時內提出，且自己沒有開啟過討論版的諮詢(最新一筆對話為未讀)
- 排序條件第一個為"新提出諮詢"，接下來依據畫面上選取的排序順序排序
- 訊息內容為該留言板最新的訊息
- asker_description
  - 當提出者為恩主公員工時，顯示職稱
  - 當提出者為DayPass病患時，顯示DayPass
  - 當提出者為長照機構護理師時，顯示長照機構名稱

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|require|time-span|string|時間區間|all<br>today<br>last2day(昨天)<br>last3day(前天、三天內)<br>last4day(大前天)<br>last7day<br>last30day<br>last90day(預設)|
|option|query|string|模糊查詢提問者姓名、提問問題||
|option|sortby|string|按照哪個屬性排序|last_update_time<br>unreply<br>asked_higher_consultant<br>admission<br>home_care<br>day_pass<br>long_term_care_institution|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_id": "39df8e65-241e-4372-b31e-8d358de6a903",
      "chart_wound_id": "fa58e816-56f6-4974-bf7c-fe8ccc99a643",
      "asker_user_id": "91a7f200-0af7-4bdf-8b95-e0c95fcbfb4e",
      "asker_employee_name": "李安容",
      "asker_organization_id":"e08cdc75-380a-4b68-a446-9574197a4653",
      "asker_organization_name":"本院",
      "asker_position_id":"1fd28d2a-b755-4a3b-88e7-9693b70a7b2a",
      "asker_position_description":"護理師",
      "asker_description":"護理師",
      "message":"傷口可以碰水嗎",
      "unread_count": 5,
      "is_reply": true,
      "is_ask_higher_consultant":false, 
      "is_new": true,
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "status": "replied"
    },
    {
      "cursor": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_id": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "chart_wound_id": "90aa4f32-d6ad-4799-8550-36b3d81ed44e",
      "asker_user_id": "5844a07c-c74c-450c-a9c5-db691efa6ad9",
      "asker_employee_name": "林美玲",
      "asker_organization_id":"6e061914-5152-48e1-814d-bf64df49a313",
      "asker_organization_name":"真善美老人福利機構",
      "asker_position_id":"",
      "asker_position_description":"",
      "asker_description":"真善美老人福利機構",
      "message":"傷口可以碰水嗎",
      "unread_count": 5,
      "is_reply": true,
      "is_ask_higher_consultant":false, 
      "is_new": true,
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "status": "replied"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2,
    "new_count": 1,
    "old_count": 1,
    "total_unread_count": 10
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|uuid|定位點(提供下次分頁查詢用)，這邊用諮詢ID當作cursor|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_id|uuid|諮詢ID|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|chart_wound_id|uuid|病患傷口ID|90aa4f32-d6ad-4799-8550-36b3d81ed44e|
|asker_user_id|uuid|提出諮詢者使用者ID|5844a07c-c74c-450c-a9c5-db691efa6ad9|
|asker_employee_name|string|提出諮詢者員工姓名|林美玲|
|asker_organization_id|uuidㄆ|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|asker_organization_name|string|組織名稱|真善美老人福利機構|
|asker_position_id|uuid|提出諮詢者職稱ID|1fd28d2a-b755-4a3b-88e7-9693b70a7b2a|
|asker_position_description|string|提出諮詢者職稱說明|護理師|
|asker_description|string|提出諮詢者說明|院內醫護人員顯示職稱 or DayPass or 長照機構名稱|
|message|string|訊息內容|應為XXX狀況，建議改用以下敷料|
|unread_count|bool|未讀訊息數|5|
|is_reply|bool|是否已回覆|true<br>false|
|is_ask_higher_consultant|bool|是否向上諮詢|true<br>false|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|is_new|bool|新提出諮詢|true<br>false|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|
|status|string|狀態|replied:已回覆, unreply:未回覆, asked_higher:向上諮詢, others:他人回覆|





*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|20|
|new_count|int|新提出諮詢筆數|3|
|old_count|int|舊提出諮詢筆數|17|
|total_unread_count|int|總未讀訊息數|10|

### 13.2. PUT /users/{user-id}/consultations/{consultation-id}

|欄位|描述|
|:---|:---|
|Description|更新已讀|
|Pagination|N/A|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->查看諮詢回覆<br>App->諮詢回覆管理->查看諮詢回覆|

**Note**
- 找user在該consultations內所有討論版
- 更新討論版內的每條dialog的已讀(對該使用者的)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|require|consultation-id|uuid|諮詢討論版ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|

***
**Request Body**

```json

```

***
**Response Body**

*HTTP 200*
```json

```

<br>
<br>
<br>
<br>

### 13.3. GET /wounds/{chart-wound-id}/consultations

|欄位|描述|
|:---|:---|
|Description|查詢諮詢內容(諮詢討論版)|
|Pagination|cursored-base|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->查看諮詢回覆<br>App->諮詢回覆管理->查看諮詢回覆|

**Note**

- 根據consultations的asker_user_id判斷使用者是否為提出諮詢的人
- 提出諮詢者的角度觀看，所有討論版的對話會合併顯示
- 被諮詢者的角度觀看，只能看到自己有被加入成員的討論版的對話
- 其他角色(ex:別的醫師把此病患加入工作清單/關懷清單)，當無人回覆時，顯示任一討論版即可，當有人回覆時，只顯示有回覆的那個討論版
- 對話記錄依據時間排序，最新的在最前面
- 同時更新已讀狀態，更新此諮詢內，跟自己有關的未讀改成已讀
- 諮詢對象總數
  - 提出給一個人時，就該討論版的諮詢對象總數
  - 提出給兩個人時，若兩人皆未回覆，則諮詢對象總數為2
  - 提出給兩個人時，若一人已回覆，則諮詢對象總數則為1
  - 提出給兩個人時，若一人向上諮詢，則諮詢對象為向上諮詢者加被向上諮詢者(不包含沒有回覆的人)，總數為2
- 若user本身具備可向上諮詢的身分(醫師、傷口個管師)且尚未在此則諮詢ID做過向上諮詢，則can_ask_higher_consultant為true，否則為false

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wound-id|uuid|病患傷口ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_dialog_id": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_board_id": "fa58e816-56f6-4974-bf7c-fe8ccc99a643",
      "consultation_board_member_id": "91a7f200-0af7-4bdf-8b95-e0c95fcbfb4e",
      "member_type": "asker",
      "consultant_level": "",
      "consultant_id": "",
      "user_id": "66493279-a06a-49d1-a2f8-6408aa5eb6ae",
      "employee_name": "王Ｏ怡",
      "position_description": "護理師",
      "message": "請問傷口周圍潰爛，是否需更換藥膏？",
      "is_read": true,
      "consultation_suggest_ointments": [],
      "consultation_suggest_dressings": [],
      "consultation_suggest_medical_materials": [],
      "consultation_suggest_wound_treatments": [],
      "create_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "cursor": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_dialog_id": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_board_id": "90aa4f32-d6ad-4799-8550-36b3d81ed44e",
      "consultation_board_member_id": "5844a07c-c74c-450c-a9c5-db691efa6ad9",
      "member_type": "consultant",
      "consultant_level": "doctor",
      "consultant_id": "a6e3bdd7-8bf9-44b0-828b-ce88b018fb37",
      "user_id": "27e80f6e-b67b-42a8-9682-dbc4b8da8c96",
      "employee_name": "陳Ｏ成",
      "position_description": "醫師",
      "message": "應為XXX狀況，建議改用以下敷料",
      "is_read": false,
      "consultation_suggest_ointments": [
        {
          "consultation_suggest_ointment_id": "be69f16c-98a4-4bf7-917b-bfb7a77b2e81",
          "ointment_id": 1,
          "ointment_name": "除疤凝膠"
        },
        {
          "consultation_suggest_ointment_id": "26b6963e-e027-4e39-9baa-f08e399c3053",
          "ointment_id": 2,
          "ointment_name": "抗生素藥膏"
        }
      ],
      "consultation_suggest_dressings": [
        {
          "consultation_suggest_dressing_id": "0c80481b-4d89-4601-9b83-3c20d78b720a",
          "dressing_id": 1,
          "dressing_name": "紗布"
        }
      ],
      "consultation_suggest_medical_materials": [
        {
          "consultation_suggest_medical_material_id": "18e7f43a-9582-43fd-9ee6-d52071f47950",
          "medical_material_id": 1,
          "medical_material_name": "特殊器材A"
        }
      ],
      "consultation_suggest_wound_treatments": [
        {
          "consultation_suggest_wound_treatment_id": "f08f1233-bf27-4c2a-96ab-af86ed20a7bc",
          "wound_treatment_id": 2,
          "wound_treatment_description": "切開排膿"
        }
      ],
      "create_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  },
  "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
  "chart_no": "093948234",
  "patient_name": "王Ｏ明",
  "patient_sex": "male",
  "patient_age": 30,
  "patient_birthday": "1980-01-01",
  "is_work_item": true,
  "work_item_id": "607e0f4a-1b9b-4c21-b6b7-e50e05a1b00b",
  "is_care_item": true,
  "care_item_id": "c24b44a5-cbd1-4a27-a0bc-fb96775ca016",
  "chart_wound_id": "fcd6afc6-be21-4608-b92c-2f950880e007",
  "image_thumbnail_url": "http://xxx/yyy",
  "chart_wound_sub_part_combination": "1-2-3",
  "chart_wound_sub_part_combination_description": "會陰區+左腹股溝+左髖部",
  "consultant_count": 2, 
  "consultation_id": "591cf520-6b88-4376-ab55-edf3c62f2e6a",
  "can_ask_higher_consultant": false,
  "creator": "135d684c-23b1-4743-8527-c643d647223f"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||
|chart_id|string|病歷ID|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|patient_sex|string|性別|male|
|patient_age|int|年齡|30|
|patient_birthday|date|生日|1980-01-01|
|is_work_item|bool|是否在工作清單|true<br>false|
|work_item_id|string|工作清單ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|is_care_item|bool|是否在關懷清單|true<br>false|
|care_item_id|string|關懷清單ID|2a41bf3a-afde-1297-2e13-b1e1b8f4a3e5|
|chart_wound_id|uuid|病患傷口ID|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|chart_wound_sub_part_combination|string|傷口子部位組合(第二層)<br>有多個組合時用"-"連接|28-30|
|chart_wound_sub_part_combination_description|string|傷口子部位組合說明|未分類<br>右上腹<br>右上腹+右下腹|
|consultant_count|int|諮詢對象總數|2|
|consultation_id|uuid|諮詢ID|591cf520-6b88-4376-ab55-edf3c62f2e6a|
|can_ask_higher_consultant|bool|是否能夠向上諮詢|true<br>false|
|creator|uuid|傷口資料建立者|135d684c-23b1-4743-8527-c643d647223f|

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|uuid|定位點(提供下次分頁查詢用)，這邊用諮詢對話紀錄ID當作cursor|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_dialog_id|uuid|諮詢對話紀錄ID|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_board_id|uuid|諮詢討論版ID|90aa4f32-d6ad-4799-8550-36b3d81ed44e|
|consultation_board_member_id|uuid|諮詢討論版成員ID|5844a07c-c74c-450c-a9c5-db691efa6ad9|
|member_type|string|諮詢討論版成員種類|asker=提出諮詢的人<br>consultation_window=諮詢窗口(窗口與提出的人相同時，只紀錄一筆為提出諮詢的人)<br>consultant=諮詢對象|
|consultant_level|string|諮詢層級(被設定在諮詢討論版成員的層級)|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_id|uuid|諮詢對象ID|a6e3bdd7-8bf9-44b0-828b-ce88b018fb37|
|user_id|uuid|留言的人的使用者ID|27e80f6e-b67b-42a8-9682-dbc4b8da8c96|
|employee_name|string|員工姓名|王小明|
|position_description|string|職稱說明|醫師|
|message|string|訊息內容|應為XXX狀況，建議改用以下敷料|
|is_read|bool|是否已讀|false|
|consultation_suggest_ointments|array of consultation_suggest_ointment|建議藥膏||
|consultation_suggest_dressings|array of consultation_suggest_dressing|建議敷料||
|consultation_suggest_medical_materials|array of consultation_suggest_medical_material|建議特殊器材||
|consultation_suggest_wound_treatments|array of consultation_suggest_wound_treatment|建議傷口處置||
|create_time|datetime|建立時間(諮詢討論版成員資料表的，這裡代表被諮詢或被向上諮詢的時間)|2019-07-18T13:54:37.917+0000|

*consultation_suggest_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_ointment_id|uuid|諮詢建議藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*consultation_suggest_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_dressing_id|uuid|諮詢建議敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*consultation_suggest_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_medical_material_id|uuid|諮詢建議特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*consultation_suggest_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_wound_treatment_id|uuid|諮詢建議傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 13.4. GET /wounds/{chart-wound-id}/consultations/consultants

|欄位|描述|
|:---|:---|
|Description|查詢已諮詢對象(所有討論版連集)|
|Pagination|N/A|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->查看諮詢回覆<br>App->諮詢回覆管理->查看諮詢回覆|

**Note**
- 當此諮詢還未被回覆，則合併顯示所有討論版的成員
- 當此諮詢已經被回覆，則只顯示被回覆的討論版的成員(諮詢對象)
- 依據諮詢層級排序，層級最小的在最前面

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wound-id|uuid|病患傷口ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|


***
**Response Body**

*HTTP 200*
```json
[
  {
    "consultation_board_member_id": "b8b8df61-3fb7-4ba6-b6fb-565fe5b8021e",
    "consultant_level": "case_manager",
    "user_id": "22babb50-e7e2-4602-83a8-8775b89ad733",
    "employee_name": "李Ｏ華",
    "division_name": "一般外科",
    "position_description": "專科護理師",
    "create_time": "2019-07-18T13:54:37.917+0000"
  },
  {
    "consultation_board_member_id": "2e2ceed9-bf6a-43ee-b6a9-0f7c00b7190f",
    "consultant_level": "professional",
    "user_id": "b0b49fac-3a4d-4591-b959-a89473ca5622",
    "employee_name": "顏Ｏ證",
    "division_name": "一般外科",
    "position_description": "主治醫師",
    "create_time": "2019-07-18T13:54:37.917+0000"
  }
]
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_board_member_id|uuid|諮詢討論版成員ID|9f2456d3-18ca-4645-9c22-81b056766308|
|consultant_level|string|諮詢層級(被設定在諮詢討論版成員的層級)|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|user_id|uuid|使用者ID|27e80f6e-b67b-42a8-9682-dbc4b8da8c96|
|employee_name|string|員工姓名|王小明|
|division_name|string|科別名稱|一般外科|
|position_description|string|職稱說明|醫師|
|create_time|datetime|建立時間(諮詢討論版成員資料表的，這裡代表被諮詢或被向上諮詢的時間)|2019-07-18T13:54:37.917+0000|

<br>
<br>
<br>
<br>

### 13.5. POST /wounds/{chart-wound-id}/consultations/visiting-staff

|欄位|描述|
|:---|:---|
|Description|提出諮詢(諮詢主治醫師)|
|Pagination|N/A|
|Access Control|恩主公護理師、恩主公醫師(不包含主治醫師自己和傷口專家)、DayPass病患|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢|

**Note**
- 需檢查token的使用者身份
- 恩主公護理師可以將傷口紀錄對主治醫師提出諮詢
- DayPass病患可以將傷口紀錄對主治醫師提出諮詢
- 從傷口病患資料取得病患的主治醫師，若病患主治醫師沒有資料或是該主治醫師沒有被加入成諮詢對象，則不可諮詢主治醫師，回傳HTTP400
- 寫入資料表
  - consultations
    - asker_user_id填入提出者的user_id
    - consultation_window_user_id填入null(此情況不會經過諮詢窗口)
    - is_reply填入false
  - consultation_boards(建立一個新的討論版)
  - consultation_board_members
    - 若為護理師提出，則成員會有護理師及主治醫師
    - 若為DayPass病患提出，則成員會有DayPass及主治醫師
    - 護理師及DayPass病患不用填入consultant_level欄位,member_type=asker
    - 主治醫師consultant_level=doctor,member_type=consultant
  - consultation_dialogs
    - 會有一筆資料為提出時護理師/DayPass病患輸入的問題
  - consultation_dialog_read_mapping
    - 會有兩筆資料對應到consultation_dialogs
    - 提出諮詢者is_read=true
    - 主治醫師is_read=false

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wound-id|uuid|病患傷口ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|

***
**Request Body**

```json
{
  "message": "傷口周圍紅腫，請問是否有過敏現象？"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|message|string|訊息內容|傷口周圍紅腫，請問是否有過敏現象？|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

### 13.6. POST /wounds/{chart-wound-id}/consultations/other-consultants

|欄位|描述|
|:---|:---|
|Description|提出諮詢(諮詢其他對象)(複數對象)|
|Pagination|N/A|
|Access Control|恩主公醫師(非傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢|

**Note**
- 需檢查token的使用者身份
- 可以選擇諮詢對象(最多兩個)
- 長照機構護理師只能提出給對應的諮詢窗口(待確認)
- 諮詢窗口收到長照的諮詢後，可以向上諮詢(待確認是向上諮詢還是提出諮詢)，可提交對象同下
- 恩主公護理師提出諮詢只能給傷口個管師或醫師
- 傷口個管師和醫師提出諮詢只能給傷口專家
- 寫入資料表
  - consultations
    - asker_user_id填入提出者的user_id
    - 若有經過諮詢窗口consultation_window_user_id填入諮詢窗口的user_id
    - is_reply填入false
  - consultation_boards
    - 根據對象數量，開啟一或兩個討論板，且以下資料表都分別存入不同討論版
  - consultation_board_members
    - 若來源為長照機構，則成員有長照機構護理師、提出者(諮詢窗口)、諮詢對象(恩主公傷口個管師或醫師)
      - 長照機構member_type=asker，諮詢窗口member_type=consultation_window，諮詢對象member_type=consultant
    - 若為恩主公護理師提出，則成員會有提出者(恩主公護理師)、諮詢對象(恩主公傷口個管師或醫師)
      - 提出者(恩主公護理師)member_type=asker，諮詢對象member_type=consultant
    - 若為恩主公傷口個管師或醫師提出，則成員會有提出者(恩主公傷口個管師或醫師)、諮詢對象(傷口專家)
      - 提出者(恩主公傷口個管師或醫師)member_type=asker，諮詢對象member_type=consultant
    - consultant_level依照諮詢窗口、傷口個管師、醫師、傷口專家填入對應的值，非以上角色則填入null
  - consultation_dialogs
    - 會有一筆資料為提出者輸入的問題
  - consultation_dialog_read_mapping
    - 會有兩筆資料對應到consultation_dialogs
    - 提出諮詢者is_read=true
    - 諮詢對象is_read=false

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wound-id|uuid|病患傷口ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|

***
**Request Body**

```json
{
  "message": "傷口周圍紅腫，請問是否有過敏現象？",
  "consultants": [
    {
      "consultant_id": "7c4b5bf4-4bc3-4f0e-995c-4ee46a109fcb"
    },
    {
      "consultant_id": "fd80ed63-314c-4ff2-8f61-e1d612e55fca"
    }
  ]
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|message|string|訊息內容|傷口周圍紅腫，請問是否有過敏現象？|
|require|consultants|array of consultant|諮詢對象|最多兩筆|

*consultant*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant_id|uuid|諮詢對象ID|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

### 13.7. POST /users/{user-id}/consultation_board_members

|欄位|描述|
|:---|:---|
|Description|向上諮詢|
|Pagination|N/A|
|Access Control|恩主公醫師(不包含傷口專家)、傷口個管師、諮詢窗口(待討論)|
|Using|App->諮詢回覆管理->查看諮詢回覆->向上諮詢<br>App->諮詢回覆管理->向上諮詢|

**Note**
- 先用user-id找到該使用者對應的向上諮詢對象
- 然後用consultation_id找出跟這個使用者有關係的討論版
- 把向上諮詢的對象加入討論版成員資料表
- 在提出向上諮詢的人的consultation_board_members資料表註記is_ask_higher_consultant=true
- 新增一筆consultation_dialogs，message為"已向上諮詢ＸＸＸ醫師，請耐心等候回覆。"，consultation_board_member_id為當前使用者的。
- 向上諮詢完後，須至consultation_dialog_read_mapping填入相對應資訊
- 向上諮詢完後，重新查詢諮詢內容(同13.3.)
- 回填第一個回覆的人

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Request Body**

```json
[
  {
    "consultation_id": "e723ffcb-4f84-4d9e-b44b-747710731ef7"
  },
  {
    "consultation_id": "ae282852-7a0e-4bd8-ad21-6d69928c169b"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|consultation_id|uuid|諮詢ID|591cf520-6b88-4376-ab55-edf3c62f2e6a|

***
**Response Body**

*HTTP 201*
```json
```

<br>
<br>
<br>
<br>

### 13.8. POST /consultations/{consultation-id}/consultation_dialogs

|欄位|描述|
|:---|:---|
|Description|回覆諮詢&諮詢討論版留言(依據層級不同決定是否存入建議換藥)|
|Pagination|N/A|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->查看諮詢回覆->回覆<br>App->諮詢回覆管理->查看諮詢回覆->回覆|

**Note**
- 增加一筆consultation_dialogs
- 若沒有傳入consultation-board-id，則會將訊息發至該consultation-id的所有討論版
- 若有傳入consultation-board-id，則只會將訊息發至該討論版，另外必須檢查consultation-board-id與consultation-id的對應關係
- 提出者及諮詢窗口只可回覆訊息(message)，建議換藥部分都傳入空陣列即可，後端不儲存這幾個欄位。
- 傷口個管師/醫師/傷口專家可回覆訊息+建議換藥
- 每增加一筆回覆，都須至consultation_dialog_read_mapping填入相對應資訊
- 若一開始提出給兩人，一人已回覆，則另一人不可再回覆(用consultations的reply_consultant_id判斷)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultation-id|uuid|諮詢討論版ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|

***
**Request Body**

*諮詢提出者/諮詢窗口*
```json
{
  "message": "請問可碰水嗎？",
  "consultation_suggest_ointments": [],
  "consultation_suggest_dressings": [],
  "consultation_suggest_medical_materials": [],
  "consultation_suggest_wound_treatments": []
}
```

*傷口個管師/醫師/傷口專家*
```json
{
  "message": "請問可碰水嗎？",
  "consultation_suggest_ointments": [
    {
      "ointment_id": 1
    },
    {
      "ointment_id": 2
    }
  ],
  "consultation_suggest_dressings": [
    {
      "dressing_id": 1
    }
  ],
  "consultation_suggest_medical_materials": [
    {
      "medical_material_id": 1
    }
  ],
  "consultation_suggest_wound_treatments": [
    {
      "wound_treatment_id": 2
    }
  ]
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|message|string|訊息內容|應為XXX狀況，建議改用以下敷料|
|require|consultation_suggest_ointments|array of consultation_suggest_ointment|建議藥膏||
|require|consultation_suggest_dressings|array of consultation_suggest_dressing|建議敷料||
|require|consultation_suggest_medical_materials|array of consultation_suggest_medical_material|建議特殊器材||
|require|consultation_suggest_wound_treatments|array of consultation_suggest_wound_treatment|建議傷口處置||

*consultation_suggest_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|ointment_id|int|藥膏ID|1|

*consultation_suggest_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|dressing_id|int|敷料ID|1|

*consultation_suggest_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|medical_material_id|int|特殊器材ID|1|

*consultation_suggest_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|wound_treatment_id|int|現行處置ID|1|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_dialog_id": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_board_id": "fa58e816-56f6-4974-bf7c-fe8ccc99a643",
      "consultation_board_member_id": "91a7f200-0af7-4bdf-8b95-e0c95fcbfb4e",
      "member_type": "asker",
      "consultant_level": "",
      "consultant_id": "",
      "user_id": "66493279-a06a-49d1-a2f8-6408aa5eb6ae",
      "employee_name": "王Ｏ怡",
      "position_description": "護理師",
      "message": "請問傷口周圍潰爛，是否需更換藥膏？",
      "is_read": true,
      "consultation_suggest_ointments": [],
      "consultation_suggest_dressings": [],
      "consultation_suggest_medical_materials": [],
      "consultation_suggest_wound_treatments": [],
      "create_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "cursor": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_dialog_id": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_board_id": "90aa4f32-d6ad-4799-8550-36b3d81ed44e",
      "consultation_board_member_id": "5844a07c-c74c-450c-a9c5-db691efa6ad9",
      "member_type": "consultant",
      "consultant_level": "doctor",
      "consultant_id": "a6e3bdd7-8bf9-44b0-828b-ce88b018fb37",
      "user_id": "27e80f6e-b67b-42a8-9682-dbc4b8da8c96",
      "employee_name": "陳Ｏ成",
      "position_description": "醫師",
      "message": "已向上諮詢林Ｏ翔醫師，請耐心等候回覆。",
      "is_read": true,
      "consultation_suggest_ointments": [],
      "consultation_suggest_dressings": [],
      "consultation_suggest_medical_materials": [],
      "consultation_suggest_wound_treatments": [],
      "create_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|uuid|定位點(提供下次分頁查詢用)，這邊用諮詢對話紀錄ID當作cursor|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_dialog_id|uuid|諮詢對話紀錄ID|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_board_id|uuid|諮詢討論版ID|90aa4f32-d6ad-4799-8550-36b3d81ed44e|
|consultation_board_member_id|uuid|諮詢討論版成員ID|5844a07c-c74c-450c-a9c5-db691efa6ad9|
|member_type|string|諮詢討論版成員種類|asker=提出諮詢的人<br>consultation_window=諮詢窗口(窗口與提出的人相同時，只紀錄一筆為提出諮詢的人)<br>consultant=諮詢對象|
|consultant_level|string|諮詢層級(被設定在諮詢討論版成員的層級)|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_id|uuid|諮詢對象ID|a6e3bdd7-8bf9-44b0-828b-ce88b018fb37|
|user_id|uuid|使用者ID|27e80f6e-b67b-42a8-9682-dbc4b8da8c96|
|employee_name|string|員工姓名|王小明|
|position_description|string|職稱說明|醫師|
|message|string|訊息內容|應為XXX狀況，建議改用以下敷料|
|is_read|bool|是否已讀|false|
|consultation_suggest_ointments|array of consultation_suggest_ointment|建議藥膏||
|consultation_suggest_dressings|array of consultation_suggest_dressing|建議敷料||
|consultation_suggest_medical_materials|array of consultation_suggest_medical_material|建議特殊器材||
|consultation_suggest_wound_treatments|array of consultation_suggest_wound_treatment|建議傷口處置||
|create_time|datetime|建立時間(諮詢討論版成員資料表的，這裡代表被諮詢或被向上諮詢的時間)|2019-07-18T13:54:37.917+0000|

*consultation_suggest_ointment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_ointment_id|uuid|諮詢建議藥膏ID|9f2456d3-18ca-4645-9c22-81b056766308|
|ointment_id|int|藥膏ID|1|
|ointment_name|string|藥膏名稱|除疤凝膠|

*consultation_suggest_dressing*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_dressing_id|uuid|諮詢建議敷料ID|9f2456d3-18ca-4645-9c22-81b056766308|
|dressing_id|int|敷料ID|1|
|dressing_name|string|敷料名稱|紗布|

*consultation_suggest_medical_material*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_medical_material_id|uuid|諮詢建議特殊器材ID|9f2456d3-18ca-4645-9c22-81b056766308|
|medical_material_id|int|特殊器材ID|1|
|medical_material_name|string|特殊器材名稱|特殊器材A|

*consultation_suggest_wound_treatment*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultation_suggest_wound_treatment_id|uuid|諮詢建議傷口處置ID|9f2456d3-18ca-4645-9c22-81b056766308|
|wound_treatment_id|int|現行處置ID|1|
|wound_treatment_description|string|現行處置說明|切開排膿|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 13.9. GET /users/{user-id}/consultations/consult

|欄位|描述|
|:---|:---|
|Description|查詢我提出的諮詢|
|Pagination|cursored-base|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|(傷口個管師/醫師/傷口專家)App->諮詢回覆管理->我提出的諮詢<br>(其他使用者)App->我提出的諮詢|

**Note**

- 利用consultations的asker_user_id判斷
- 訊息內容為該留言板最新的訊息

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|require|time-span|string|時間區間|all<br>today<br>last2day(昨天)<br>last3day(前天、三天內)<br>last4day(大前天)<br>last7day<br>last30day<br>last90day(預設)|
|option|query|string|模糊查詢提問者姓名(待確認)、提問問題||
|option|sortby|string|按照哪個屬性排序|last_update_time<br>unreply|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "39df8e65-241e-4372-b31e-8d358de6a903",
      "consultation_id": "39df8e65-241e-4372-b31e-8d358de6a903",
      "chart_wound_id": "fa58e816-56f6-4974-bf7c-fe8ccc99a643",
      "chart_id": "77d7b022-4225-44b5-be58-5b376cda001d",
      "patient_name": "李Ｏ茹",
      "message": "傷口可以碰水嗎",
      "unread_count": 5,
      "is_reply": true,
      "is_ask_higher_consultant": false,
      "image_thumbnail_url": "http://xxx/yyy",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "cursor": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "consultation_id": "b41110f8-2832-4ca7-bbf3-ef5c788d5d7f",
      "chart_wound_id": "90aa4f32-d6ad-4799-8550-36b3d81ed44e",
      "chart_id": "0762b227-0330-47b4-a04d-db11e19be1a7",
      "patient_name": "張Ｏ仁",
      "message": "傷口可以碰水嗎",
      "unread_count": 5,
      "is_reply": true,
      "is_ask_higher_consultant": false,
      "image_thumbnail_url": "http://xxx/yyy",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|uuid|定位點(提供下次分頁查詢用)，這邊用諮詢ID當作cursor|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|consultation_id|uuid|諮詢ID|b41110f8-2832-4ca7-bbf3-ef5c788d5d7f|
|chart_wound_id|uuid|病患傷口ID|90aa4f32-d6ad-4799-8550-36b3d81ed44e|
|chart_id|string|病歷ID|152ba30d-434f-123d-aca1-92aefad12987|
|patient_name|string|姓名|王Ｏ明|
|message|string|訊息內容|應為XXX狀況，建議改用以下敷料|
|unread_count|bool|未讀訊息數|5|
|is_reply|bool|是否已回覆|true<br>false|
|is_ask_higher_consultant|bool|是否向上諮詢|true<br>false|
|image_thumbnail_url|string|縮圖URL|http://xxx/yyy|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|20|

<br>
<br>
<br>
<br>

### 13.10. POST /wounds/{chart-wound-id}/consultations/consultation-window

|欄位|描述|
|:---|:---|
|Description|長照機構提出諮詢|
|Pagination|N/A|
|Access Control|長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢|

**Note**
- 需檢查token的使用者身份
- 從長照機構表（long_term_care_institutions）找出對應諮詢窗口
- 長照機構護理師可以將傷口紀錄對諮詢窗口提出諮詢
- 寫入資料表
  - consultations
    - asker_user_id填入提出者的user_id
    - consultation_window_user_id填入諮詢窗口user_id
    - is_reply填入false
  - consultation_boards(建立一個新的討論版)
  - consultation_board_members
    - 若為長照機構護理師提出，則成員會有長照機構護理師及諮詢窗口
    - 長照機構護理師不用填入consultant_level欄位,member_type=consultation_window
  - consultation_dialogs
    - 會有一筆資料為提出時長照機構護理師輸入的問題
  - consultation_dialog_read_mapping
    - 會有兩筆資料對應到consultation_dialogs
    - 提出諮詢者is_read=true
    - 諮詢窗口
    - is_read=false
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|chart-wound-id|uuid|病患傷口ID|5d39be91-b76c-4a1a-9238-e4eb3bfc8785|

***
**Request Body**

```json
{
  "message": "傷口周圍紅腫，請問是否有過敏現象？"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|message|string|訊息內容|傷口周圍紅腫，請問是否有過敏現象？|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

## 14. content-service
### 14.1. GET /content-service/upload-url/wound-image

|欄位|描述|
|:---|:---|
|Description|取得上傳傷口照片URL|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|所有用到上傳圖片的地方|

**Note**

- 先呼叫Content Service的getAuth，取得Token。
- 利用取得的Token和組好的{ObjectKey}呼叫Content Service的presignedPutUrl取得上傳檔案URL
- {ObjectKey}組合方式為"/{organization-id}/{chart-id}/{file-name}"

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|chart-id|string|病患ID|6b65eccb-ae2a-44d4-aba4-db8371ea9353|
|require|file-name|string|檔名|ffe80d59-5875-44ca-84b8-41c45f23548d.jpg|

***
**Response Body**

*HTTP 200*
```json
{
  "result": "http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/6b65eccb-ae2a-44d4-aba4-db8371ea9353/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|result|string|上傳檔案URL|http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/6b65eccb-ae2a-44d4-aba4-db8371ea9353/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766|

<br>
<br>
<br>
<br>

### 14.2. PUT {url of 14.1's result}

|欄位|描述|
|:---|:---|
|Description|上傳檔案到Content Service的Storage|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|所有用到上傳圖片的地方|

**Note**

- 這支API為Content Service本身提供的API，不在Back-end另外開發。
- URL來自14.1.的Result

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|Content-Type|string|檔案類型|image/jpeg<br>image/png<br>image/gif<br>...|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|temp_url_sig|string|暫時存取簽名|9c01b9d79221fa14e4f68550f8185dce98bae145|
|require|temp_url_expires|string|暫時存取時限|1566292766|

***
**Request Body**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|file|binary|圖片檔案||

***
**Response Body**

*HTTP 201*
```json

```

<br>
<br>
<br>
<br>

### 14.3. GET /content-service/upload-url/dressing-image

|欄位|描述|
|:---|:---|
|Description|取得上傳敷料照片URL|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|所有用到上傳圖片的地方|

**Note**

- 先呼叫Content Service的getAuth，取得Token。
- 利用取得的Token和組好的{ObjectKey}呼叫Content Service的presignedPutUrl取得上傳檔案URL
- {ObjectKey}組合方式為"/{organization-id}/dressing/{file-name}"

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|file-name|string|檔名|ffe80d59-5875-44ca-84b8-41c45f23548d.jpg|

***
**Response Body**

*HTTP 200*
```json
{
  "result": "http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/6b65eccb-ae2a-44d4-aba4-db8371ea9353/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|result|string|上傳檔案URL|http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/dressing/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766|

<br>
<br>
<br>
<br>

### 14.4. DELETE /content-service

|欄位|描述|
|:---|:---|
|Description|刪除檔案|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|所有可以刪除照片的地方|

**Note**

- 先呼叫Content Service的getAuth，取得Token。
- 利用取得的Token和組好的{ObjectKey}呼叫Content Service的delete刪除檔案API
- {ObjectKey}組合方式為"/{file-path}/{file-name}"

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|file-path|string|檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing/|
|require|file-name|string|檔名|ffe80d59-5875-44ca-84b8-41c45f23548d.jpg|

***
**Response Body**

*HTTP 204*
```json
```

*HTTP 400*
```json
{
  "error_message": "刪除失敗"
}
```

<br>
<br>
<br>
<br>

### 14.5. GET /content-service

|欄位|描述|
|:---|:---|
|Description|下載檔案|
|Pagination|N/A|
|Access Control|系統所有使用者|
|Using|所有用到下載或查看圖片的地方|

**Note**

- 先呼叫Content Service的getAuth，取得Token。
- 利用取得的Token和組好的{ObjectKey}呼叫Content Service的presignedUrl下載檔案URL
- {ObjectKey}組合方式為"/{file-path}/{file-name}"

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|file-path|string|檔案路徑|e08cdc75-380a-4b68-a446-9574197a4653/dressing/|
|require|file-name|string|檔名|ffe80d59-5875-44ca-84b8-41c45f23548d.jpg|

***
**Response Body**

*HTTP 200*
```json
{
  "result": "http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/6b65eccb-ae2a-44d4-aba4-db8371ea9353/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|result|string|下載檔案URL|http://192.168.101.82:12345/v1/AUTH_test/wiwis/e08cdc75-380a-4b68-a446-9574197a4653/6b65eccb-ae2a-44d4-aba4-db8371ea9353/ffe80d59-5875-44ca-84b8-41c45f23548d.jpg?temp_url_sig=9c01b9d79221fa14e4f68550f8185dce98bae145&temp_url_expires=1566292766|

<br>
<br>
<br>
<br>

## 15. frequently-sentences
### 15.1. GET /users/{user-id}/frequently-sentences

|欄位|描述|
|:---|:---|
|Description|查詢常用句子|
|Pagination|N/A|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢<br>App->諮詢回覆管理->回覆諮詢|

**Note**
- 依據index排序，越大的排越前面

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "frequently_sentence_id": "ad1e5c54-71dc-4419-8753-c7510d7c5574",
    "frequently_sentence_content": "傷口可以碰水嗎？",
    "index": 1
  },
  {
    "frequently_sentence_id": "86347a03-8252-4705-a314-0da6006cfcca",
    "frequently_sentence_content": "傷口有惡化嗎？",
    "index": 2
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|frequently_sentence_id|uuid|常用句子ID|ad1e5c54-71dc-4419-8753-c7510d7c5574|
|frequently_sentence_content|string|常用句子內容|傷口可碰水嗎？|
|index|int|排序順序|1|

<br>
<br>
<br>
<br>

### 15.2. POST /users/{user-id}/frequently-sentences

|欄位|描述|
|:---|:---|
|Description|新增/編輯/刪除(多筆維護)常用句子|
|Pagination|N/A|
|Access Control|DayPass病患、恩主公醫師(包含傷口專家)、恩主公護理師(包含諮詢窗口)、長照機構護理師|
|Using|App->工作/關懷清單->傷口列表->傷口紀錄->提出諮詢->編輯常用訊息<br>App->諮詢回覆管理->回覆諮詢->編輯常用訊息|

**Note**
- 陣列資料進來，直接把現有資料清空，然後Insert傳入資料
- 需檢查每個人最多只能新增11筆常用句子

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Request Body**

```json
[
  {
    "frequently_sentence_content": "傷口可以碰水嗎？"
  },
  {
    "frequently_sentence_content": "傷口敷料可以用什麼替代？"
  },
  {
    "frequently_sentence_content": "需要回診嗎？"
  }
]
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|frequently_sentence_content|string|常用句子內容|傷口可碰水嗎？|

***
**Response Body**

*HTTP 200*
```json
[
  {
    "frequently_sentence_id": "ad1e5c54-71dc-4419-8753-c7510d7c5574",
    "frequently_sentence_content": "傷口可以碰水嗎？",
    "index": 1
  },
  {
    "frequently_sentence_id": "86347a03-8252-4705-a314-0da6006cfcca",
    "frequently_sentence_content": "傷口敷料可以用什麼替代？",
    "index": 2
  },
  {
    "frequently_sentence_id": "88553812-7fbb-4b6b-bdb9-5fd4b4c8db51",
    "frequently_sentence_content": "需要回診嗎？",
    "index": 3
  }
]
```
|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|frequently_sentence_id|uuid|常用句子ID|ad1e5c54-71dc-4419-8753-c7510d7c5574|
|frequently_sentence_content|string|常用句子內容|傷口可碰水嗎？|
|index|int|排序順序|1|

## 16. users
### 16.1. GET /users

|欄位|描述|
|:---|:---|
|Description|查詢使用者帳號列表|
|Pagination|offset-limit base|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->帳號管理|

**Note**
- 要分頁

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|role-code|string|角色代號|eck_superadmin = 恩主公高級管理者(Web)<br>eck_admin = 恩主公一般管理者(Web)<br>eck_user = 恩主公一般使用者(App)<br>long_term_care_institution_superadmin = 長照機構高級管理者(Web)<br>long_term_care_institution_admin = 長照機構一般管理者(Web)<br>long_term_care_institution_user = 長照機構一般使用者(App)<br>daypass_user = DayPass一般使用者(App)|
|option|query|string|模糊查詢姓名|王O明|
|option|sortby|string|按照哪個屬性排序|user_name<br>position_description<br>division_name<br>user_account<br>last_update_time|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "user_id": "5dac2691-4c24-4718-a3a3-65b8dddbbbb0",
      "employee_id": "ea3a6d2c-8ca1-48da-9f8b-4c9578e618b3",
      "employee_no": "01232",
      "employee_name": "王小明",
      "position_id": "1f5103cb-8111-4431-bd8c-960786a02f10",
      "position_description": "主治醫師",
      "division_id": "c10001a4-af69-448b-8b6b-c4c21a94c2dd",
      "division_name": "一般外科",
      "user_account": "01232",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "index": 1,
      "user_id": "1b4e65e3-a51f-42ea-83cb-f20adf67775f",
      "employee_id": "d44d8269-228c-4870-8d0e-9890dc73a919",
      "employee_no": "12038",
      "employee_name": "李千千",
      "position_id": "48d53182-7fc9-4b6f-bddf-74866bf78506",
      "position_description": "專科護理師",
      "division_id": "106dbcf9-3134-4563-b125-13bf3ce9b057",
      "division_name": "整形外科",
      "user_account": "12038",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)|0|
|user_id|uuid|使用者ID|91284ee6-a610-4fa5-807c-f4db8638060a|
|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|
|employee_no|string|員工編號(五碼數字)|12038|
|employee_name|string|員工姓名|陳Ｏ翔|
|position_id|uuid|職稱|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|
|position_description|string|職稱說明|護理師|
|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_name|string|科別名稱|一般外科|
|user_account|string|使用者帳號(等於員工編號)|12038|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 16.2. POST /users

|欄位|描述|
|:---|:---|
|Description|新增使用者|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->帳號管理|

**Note**
- 新增使用者時，同時新增常用句子(不同職稱新增的句子不同)
  - 醫師
    - TBD
    - TBD
    - TBD
  - 護理師
    - 有感染發炎，請建議用藥。
    - 傷口壞死嚴重，請建議用藥。
    - 建議濕敷或乾敷？

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|role-code|string|角色代號|eck_superadmin = 恩主公高級管理者(Web)<br>eck_admin = 恩主公一般管理者(Web)<br>eck_user = 恩主公一般使用者(App)<br>long_term_care_institution_superadmin = 長照機構高級管理者(Web)<br>long_term_care_institution_admin = 長照機構一般管理者(Web)<br>long_term_care_institution_user = 長照機構一般使用者(App)<br>daypass_user = DayPass一般使用者(App)|

***
**Request Body**

```json
{
  "employee_id": "ea3a6d2c-8ca1-48da-9f8b-4c9578e618b3"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|division_id|uuid|科別ID|d6db1103-9746-491a-a01f-a0b420bf98be|
|require|position_id|uuid|職稱|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|
|require|employee_id|uuid|員工ID|1986a375-6019-491e-8477-d36f7a232651|

***
**Response Body**

*HTTP 201*
```json

```

<br>
<br>
<br>
<br>

### 16.3. DELETE /users/{user-id}/roles/{role-code}/mapping

|欄位|描述|
|:---|:---|
|Description|移除使用者權限|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->帳號管理|

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|require|role-code|uuid|角色代號|eck_superadmin = 恩主公高級管理者(Web)<br>eck_admin = 恩主公一般管理者(Web)<br>eck_user = 恩主公一般使用者(App)<br>long_term_care_institution_superadmin = 長照機構高級管理者(Web)<br>long_term_care_institution_admin = 長照機構一般管理者(Web)<br>long_term_care_institution_user = 長照機構一般使用者(App)<br>daypass_user = DayPass一般使用者(App)|

***
**Request Body**

```json

```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|

***
**Response Body**

*HTTP 200*
```json

```

<br>
<br>
<br>
<br>

### 16.4. GET /organizations/{organization-id}/users

|欄位|描述|
|:---|:---|
|Description|長照機構後台-查詢使用者帳號列表|
|Pagination|offset-limit base|
|Access Control|長照機構高級管理者|
|Using|Web->長照機構後台->帳號管理|

**Note**
- 需檢查role-code為long_term_care_institution_superadmin才可呼叫此API
- 只能查詢相同organization_id的使用者

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|option|query|string|模糊查詢姓名、電話、帳號|王O明<br>0987654321|
|option|sortby|string|按照哪個屬性排序|user_name<br>telephone<br>user_account<br>last_update_time|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "user_id": "5dac2691-4c24-4718-a3a3-65b8dddbbbb0",
      "user_name": "王小明",
      "email": "aaa@bbb.cc",
      "user_account": "aaa@bbb.cc",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "index": 1,
      "user_id": "1b4e65e3-a51f-42ea-83cb-f20adf67775f",
      "user_name": "李千千",
      "email": "ddd@bbb.cc",
      "user_account": "ddd@bbb.cc",
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)|0|
|user_id|uuid|使用者ID|91284ee6-a610-4fa5-807c-f4db8638060a|
|user_name|string|使用者姓名|陳Ｏ翔|
|email|string|電子郵件|ddd@bbb.cc|
|user_account|string|使用者帳號|0987654321|
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|

<br>
<br>
<br>
<br>

### 16.5. POST /organizations/{organization-id}/users

|欄位|描述|
|:---|:---|
|Description|長照機構後台-新增使用者|
|Pagination|N/A|
|Access Control|長照機構高級管理者|
|Using|Web->長照機構後台->帳號管理|

**Note**
- 需檢查role-code為long_term_care_institution_superadmin才可呼叫此API
- 只能新增相同organization_id的使用者
- 新增使用者時，用電話當作user_account
- 新增的角色role_code=long_term_care_institution_user, application_code=App
- password隨機產生一組6位"小寫英文＆數字"組合的值
- 新增使用者時，同時新增常用句子(不同職稱新增的句子不同)
  - 有感染發炎，請建議用藥。
  - 傷口壞死嚴重，請建議用藥。
  - 建議濕敷或乾敷？
- 若是被刪除權限的使用者(已存在的電話)，則把權限加回來即可，role_code=long_term_care_institution_user, application_code=App

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|


***
**Request Body**

```json
{
  "user_name": "王小明",
  "email": "aaa@bbb.cc"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user_name|string|使用者姓名|王小明|
|require|email|string|電子郵件|aaa@bbb.cc|

***
**Response Body**

*HTTP 201*
```json

```

<br>
<br>
<br>
<br>


### 16.6. DELETE /organizations/{organization-id}/users/{user-id}/roles/{role-code}/mapping

|欄位|描述|
|:---|:---|
|Description|長照機構後台-移除使用者權限|
|Pagination|N/A|
|Access Control|長照機構高級管理者|
|Using|Web->長照機構後台->帳號管理|

**Note**
- 需檢查role-code為long_term_care_institution_superadmin才可呼叫此API
- 只能移除相同organization_id的使用者
- 這邊只做權限移除，不真的把使用者刪除
- is_delete=false(此欄位是給DayPass用的，所以長照機構使用者不須調整成true)

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|require|role-code|uuid|角色代號|eck_superadmin = 恩主公高級管理者(Web)<br>eck_admin = 恩主公一般管理者(Web)<br>eck_user = 恩主公一般使用者(App)<br>long_term_care_institution_superadmin = 長照機構高級管理者(Web)<br>long_term_care_institution_admin = 長照機構一般管理者(Web)<br>long_term_care_institution_user = 長照機構一般使用者(App)<br>daypass_user = DayPass一般使用者(App)|

***
**Request Body**

```json

```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|

***
**Response Body**

*HTTP 200*
```json

```

<br>
<br>
<br>
<br>

### 16.7. PUT /organizations/{organization-id}/users/{user-id}/password

|欄位|描述|
|:---|:---|
|Description|長照機構後台-重新設定使用者密碼|
|Pagination|N/A|
|Access Control|長照機構高級管理者|
|Using|Web->長照機構後台->帳號管理<br>Web->長照機構後台->個人帳號管理|

**Note**
- 需檢查role-code為long_term_care_institution_superadmin才可呼叫此API
- 只能修改相同organization_id的使用者
- 若是幫長照機構一般使用者更改密碼
  - 不使用Request Body內的值
  - password隨機產生一組6位"小寫英文＆數字"組合的值
- 若是幫自己修改密碼
  - 使用Request Body內的值
  - 需檢核舊密碼正確才可修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|organization-id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|require|user-id|uuid|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Request Body**

```json
{
  "old_password": "ABCD1234",
  "new_password": "EFGH5678"
}
```

***
**Response Body**

*HTTP 200*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>


## 17. system
### 17.1. POST /system/log-in

|欄位|描述|
|:---|:---|
|Description|登入|
|Pagination|N/A|
|Access Control|所有使用者|
|Using|App->登入<br>Web->登入|

**Note**
- 帳號判斷
  - 身分證字號 => DayPass病患
  - 5碼員工編號 => 院內醫護人員
  - Email => 長照機構人員
  - 其他 => 不合法帳號，result=false
- 登入流程
  - DayPass病患
    - 檢查依據資料庫使用者帳號密碼做Login
    - 產生傷口案用的Token
  - 長照機構人員
    - 檢查依據資料庫使用者帳號密碼做Login
    - 產生傷口案用的Token
  - 院內醫護人員
    - 呼叫共用SSO，取得院內SSO Token(確認是院內人員)
    - 確認完後，重新產生傷口案用的Token(因為傷口案有包含DayPass病患和長照機構人員)
    - 傷口案用的Token，PAYLOAD裡面包含院內SSO Token

***
**Request Body**

```json
{
  "application_code": "Web",
  "user_account": "10923",
  "user_password": "ABCD1234"
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user_account|string|使用者帳號||
|require|user_password|string|使用者密碼||

***
**Response Body**

*HTTP 200*
```json
{
  "result": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
  "error_message": ""
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|result|bool|登入結果|true=成功<br>false=失敗|
|token|string|token||
|error_message|string|錯誤訊息|登入失敗|

***
**Token**

*HEADER*
```json
{
  "typ": "JWT",
  "alg": "HS512"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|typ|string|Token種類|固定JWT|
|alg|string|簽名製作方式|HS512|

*PAYLOAD*
```json
{
  "iss": "WISTRON.WIWIS",
  "sub": "T_wiwis",
  "aud": "WISTRON.WIWIS",
  "exp": 1568296167,
  "nbf": 1568292267,
  "iat": 1568292567,
  "jti": "8f4b962e-1219-42c8-a89d-cc9d6b423e28",
  "user_account": "12038",
  "user_id": "91284ee6-a610-4fa5-807c-f4db8638060a",
  "user_name": "",
  "roles": [
    {
      "role_id": "3083aa70-5367-48e2-9b6b-baae5b6f018f",
      "role_code": "eck_admin",
      "role_description": "恩主公一般管理者"
    },
    {
      "role_id": "6103af11-5126-45ab-9acf-15bd45b0cc96",
      "role_code": "eck_user",
      "role_description": "恩主公一般使用者"
    }
  ],
  "organization_id": "e08cdc75-380a-4b68-a446-9574197a4653",
  "organization_class": "eck",
  "employee_id": "1986a375-6019-491e-8477-d36f7a232651",
  "employee_no": "12038",
  "employee_name": "陳Ｏ翔",
  "division_id": "d6db1103-9746-491a-a01f-a0b420bf98be",
  "division_name": "一般外科",
  "position_id": "c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e",
  "position_type": "doctor",
  "position_description": "住院醫師",
  "consultant_id": "a6e3bdd7-8bf9-44b0-828b-ce88b018fb37",
  "consultant_level": "case_manager",
  "consultant_level_description": "醫師",
  "higher_consultant_user_id": "d655f0a7-82c6-4a85-8301-c4213bfab8c7",
  "daypass_patient_id": "84d10f61-eb11-494a-93b1-28de88cd3c50",
  "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
  "effective_date": "2019-10-22",
  "expiration_date": "2019-11-06",
  "sso_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|iss|string|JWT簽發者|WISTRON.WIWIS|
|sub|string|要求JWT使用者|T_wiwis|
|aud|string|發起要求的系統程式|WISTRON.WIWIS|
|exp|timestamp|效期|1568296167(當下時間+3600秒)|
|nbf|timestamp|不可在此時之前|1568292267(當下時間-300秒)|
|iat|timestamp|Token簽發時間|1568292567(當下時間)|
|jti|uuid|token唯一key|8f4b962e-1219-42c8-a89d-cc9d6b423e28|
|user_account|string|使用者帳號|身分證字號 => DayPass病患<br>5碼員工編號 => 院內醫護人員<br>Email => 長照機構人員|
|user_id|uuid|使用者ID|91284ee6-a610-4fa5-807c-f4db8638060a|
|user_name|string|使用者姓名(DayPass和長照機構用這個姓名)||
|roles|array of role|角色||
|organization_id|string|組織ID|e08cdc75-380a-4b68-a446-9574197a4653|
|organization_class|string|組織分類(便於辨別恩主公與長照機構用)|eck=恩主公<br>ltci=長照機構|
|employee_id|uuid|員工ID(院內員工才有，院外使用者回傳空白)|1986a375-6019-491e-8477-d36f7a232651|
|employee_no|string|員工編號(五碼數字)(院內員工才有，院外使用者回傳空白)|12038|
|employee_name|string|員工姓名(院內員工才有，院外使用者回傳空白)|陳Ｏ翔|
|division_id|uuid|科別ID(院內員工才有，院外使用者回傳空白)|d6db1103-9746-491a-a01f-a0b420bf98be|
|division_name|string|科別名稱|一般外科|
|position_id|uuid|職稱ID(院內員工才有，院外使用者回傳空白)|c98dab84-39e3-4ae2-8a7f-dc0fc1628e5e|
|position_type|string|職稱分類|doctor=醫師<br>nurse=護理師<br>other=其他|
|position_description|string|職稱說明|護理師|
|consultant_id|uuid|諮詢對象ID|a6e3bdd7-8bf9-44b0-828b-ce88b018fb37|
|consultant_level|string|諮詢層級(被設定在諮詢討論版成員的層級)|professional=傷口專家<br>doctor=醫師<br>case_manager=傷口個管師<br>consultation_window=諮詢窗口|
|consultant_level_description|string|諮詢層級說明|傷口專家<br>醫師<br>傷口個管師<br>諮詢窗口|
|higher_consultant_user_id|uuid|向上諮詢使用者ID|d655f0a7-82c6-4a85-8301-c4213bfab8c7|
|daypass_patient_id|uuid|DayPass病患ID(DayPass病患才有，其餘使用者回傳空白)|84d10f61-eb11-494a-93b1-28de88cd3c50|
|chart_id|string|病歷ID(DayPass病患才有，其餘使用者回傳空白)|152ba30d-434f-123d-aca1-92aefad12987|
|effective_date|date|生效日(DayPass病患才有，其餘使用者回傳空白)|2019-09-20|
|expiration_date|date|使用期限(DayPass病患才有，其餘使用者回傳空白)|2019-10-03|
|sso_token|string|共用SSO產生的token||

*Role*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|role_id|uuid|角色ID|3083aa70-5367-48e2-9b6b-baae5b6f018f|
|role_code|uuid|角色代號|eck_superadmin = 恩主公高級管理者(Web)<br>eck_admin = 恩主公一般管理者(Web)<br>eck_user = 恩主公一般使用者(App)<br>long_term_care_institution_superadmin = 長照機構高級管理者(Web)<br>long_term_care_institution_admin = 長照機構一般管理者(Web)<br>long_term_care_institution_user = 長照機構一般使用者(App)<br>daypass_user = DayPass一般使用者(App)|
|role_description|string|角色說明|恩主公高級管理者(Web)<br>恩主公一般管理者(Web)<br>恩主公一般使用者(App)<br>長照機構高級管理者(Web)<br>長照機構一般管理者(Web)<br>長照機構一般使用者(App)<br>DayPass一般使用者(App)|


*VERIFY SIGNATURE*
```
HMACSHA512(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  your-256-bit-secret
)
```

<br>
<br>
<br>
<br>

### 17.2. POST /system/log-out

|欄位|描述|
|:---|:---|
|Description|登出|
|Pagination|N/A|
|Access Control|所有使用者|
|Using|App->登出<br>Web->登出|

**Note**
- 帳號判斷
  - 身分證字號 => DayPass病患
  - 5碼員工編號 => 院內醫護人員
  - Email => 長照機構人員
  - 其他 => 不合法帳號，回傳400
- 登出流程
  - DayPass病患
    - 這邊指紀錄Log(但要等ELK系統架好才可以做)
    - 直接回傳登出成功
  - 長照機構人員
    - 這邊指紀錄Log(但要等ELK系統架好才可以做)
    - 直接回傳登出成功
  - 院內醫護人員
    - 呼叫共用SSO登出

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|

***
**Response Body**

*HTTP 200*
```json
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

## 18. daypass-patients
### 18.1. GET /daypass-patients

|欄位|描述|
|:---|:---|
|Description|查詢DayPass病患|
|Pagination|offset-limit base|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->DayPass管理|

**Note**
- 查詢資料表
  - daypass_patients，查詢DayPass病患ID、使用天數、生效日、使用期限、更新時間
  - 用user_id關聯users，查詢帳號
  - 用chart_id關聯charts，查詢病患姓名、身分證字號、性別、生日、電話
    - 用charts.visiting_staff_no關聯employees.employee_no，查詢主治醫師姓名
  
***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is-using|bool|正在使用|true<br>false|
|require|is-expired|bool|已過期|true<br>false|
|option|query|string|模糊查詢(病患姓名、身分證字號、性別、生日、電話、主治醫師、帳號)||
|option|sortby|string|按照哪個屬性排序|patient_name<br>patient_id_no<br>patient_sex<br>patient_birthday<br>patient_telephone<br>visiting_staff_name<br>user_account<br>effective_period<br>expiration_date<br>last_update_time|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "daypass_patient_id": "84d10f61-eb11-494a-93b1-28de88cd3c50",
      "patient_name": "王Ｏ明",
      "patient_sex_description": "男",
      "patient_id_no": "A123456789",
      "patient_birthday": "1980-01-01",
      "patient_telephone": "0987654321",
      "visiting_staff": {
        "employee_name": "章Ｏ龍",
        "position_description": "主任醫師",
        "division_name": "心臟外科"
      },
      "user_account": "A123456789",
      "password": "a93e8c",
      "effective_period": 10,
      "expire": {
        "effective_date": "2019-09-20",
        "expiration_date": "2019-09-30"
      },
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    },
    {
      "index": 1,
      "daypass_patient_id": "53b7d0ae-e3b0-41f2-9a2b-d4933411211c",
      "patient_name": "李Ｏ旭",
      "patient_sex_description": "男",
      "patient_id_no": "A123454321",
      "patient_birthday": "1980-01-01",
      "patient_telephone": "0912345678",
      "visiting_staff": {
        "employee_name": "章Ｏ龍",
        "position_description": "主任醫師",
        "division_name": "心臟外科"
      },
      "user_account": "A123454321",
      "password": "09ed83",
      "effective_period": 14,
      "expire": {
        "effective_date": "2019-09-20",
        "expiration_date": "2019-10-03"
      },
      "last_update_time": "2019-07-18T13:54:37.917+0000"
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)|0|
|daypass_patient_id|uuid|DayPass病患ID|84d10f61-eb11-494a-93b1-28de88cd3c50|
|patient_name|string|姓名|王Ｏ明|
|patient_sex_description|string|性別|男|
|patient_id_no|string|身分證字號|A123456789|
|patient_birthday|timestamp|生日|1980-01-01|
|patient_telephone|string|電話|0987654321|
|visiting_staff|object of visiting_staff|主治醫師|章Ｏ龍|
|user_account|string|使用者帳號|身分證字號 => DayPass病患<br>5碼員工編號 => 院內醫護人員<br>Email => 長照機構人員|
|password|string|密碼|6位"小寫英文＆數字"組合的值|
|effective_period|int|使用天數|3,5,7,10,14(天)|
|expire|object of expire|期限||
|last_update_time|datetime|更新日期|2019-07-18T13:54:37.917+0000|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|


*visiting_staff*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|employee_name|string|諮詢對象姓名|章Ｏ龍|
|position_description|string|諮詢對象職稱|主任醫師|
|division_name|string|諮詢對象科別|一般外科|

*expire*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|effective_date|date|生效日|2019-09-20|
|expiration_date|date|使用期限|2019-10-03|

<br>
<br>
<br>
<br>

### 18.2. POST /daypass-patients

|欄位|描述|
|:---|:---|
|Description|新增DayPass病患|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->DayPass管理|

**Note**
- 先驗證輸入之身分證字號是否存在
  - 有存在病患清單，但不存在HIS
    - 不可新增，回傳查無病患資料。(DayPass病患資料必須存在HIS)
  - 有存在HIS，但不存在病患清單
    - 新增一筆病患資料(charts)，備註、個人史、疾病史、手術史留空，其餘欄位使用HIS資料
  - 同時存在病患清單及HIS
    - 備註、個人史、疾病史、手術史使用病患清單資料，其餘欄位使用HIS資料，此時資料來源應為his(從HIS新增)
- 再來新增使用者資料(users)
  - employee_id, organization_id留空
  - user_account填入病患身分證字號
  - password隨機產生一組6位"小寫英文＆數字"組合的值
- 最後新增DayPass病患資料(daypass_patients)
  - 使用天數依據輸入值
  - 生效日為當天
  - 使用期限用生效日加上使用天數計算出來，ex:生效日為2019-09-01，使用天數為10天，那使用期限經計算會是2019-09-10

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "patient_id_no": "A123456789",
  "effective_period": 10
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|patient_id_no|string|身分證字號|A123456789|
|require|effective_period|int|使用天數|3,5,7,10,14(天)|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|


<br>
<br>
<br>
<br>

### 18.3. PUT /daypass-patients/{daypass-patient-id}/effective-period
暫時先不做

### 18.4. PUT /daypass-patients/{daypass-patient-id}/password

|欄位|描述|
|:---|:---|
|Description|重新設定DayPass病患密碼|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->DayPass管理|

**Note**
- password隨機產生一組6位"小寫英文＆數字"組合的值

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json

```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|


<br>
<br>
<br>
<br>

### 18.5. PUT /daypass-patients/{daypass-patient-id}/is_delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除DayPass病患|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->DayPass管理|

**Note**
- 把is_delete欄位改為true

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|daypass-patient-id|uuid|DayPass病患ID|afc28428-f75e-4f7e-9814-114307b004dd|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>



## 19. long-term-care-institutions
### 19.1. GET /long-term-care-institutions

|欄位|描述|
|:---|:---|
|Description|查詢長照機構|
|Pagination|offset-limit base|
|Access Control|恩主公高級管理者, 恩主公一般管理者|
|Using|Web->長照機構管理|

**Note**
- 查詢long_term_care_institutions、long_term_care_institution_contact_windows
- 查詢時會把聯絡人1,2都查詢出來，畫面只顯示聯絡人1，不過查看詳細和修改時會用到全部
  
***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|option|query|string|模糊查詢(單位/組織名稱、聯絡人1、聯絡電話1、諮詢窗口(包含姓名、職稱、科別))||
|option|sortby|string|按照哪個屬性排序|organization_name<br>contact_window_name<br>contact_window_phone<br>employee_name<br>last_update_time<br>is_share_patient<br>is_using_eck_dressing|
|option|order|string|排序順序 (需搭配sortby)|asc<br>desc|
|option|limit|int|一頁多少筆 (需搭配offset)|10|
|option|offset|int|從第幾筆開始 (需搭配limit)|0|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "index": 0,
      "long_term_care_institution_id": "84d10f61-eb11-494a-93b1-28de88cd3c50",
      "organization_id": "23a9c105-b4a5-4fc7-8f9c-e09aec8ebd21",
      "organization_name": "台北市真善美老人長期照護中心",
      "address": "台北市松山區忠孝東路9號1樓",
      "tax_id_number": "39381019",
      "responsible_person": "王Ｏ同",
      "fax": "02-88761234",
      "email": "kawaii@zenshanmei.com",
      "contact_windows": [
        {
          "contact_window_serial_number": 1,
          "contact_window_name": "王Ｏ明",
          "contact_window_phone": "0987654321"
        },
        {
          "contact_window_serial_number": 2,
          "contact_window_name": "張Ｏ堂",
          "contact_window_phone": "0987654322"
        }
      ],
      "consultant": {
        "consultant_id": "1ba0eb72-86e0-4a5d-8ba9-60389dff1249",
        "employee_name": "李Ｏ欣",
        "position_description": "專科護理師",
        "division_name": "心臟外科"
      },
      "admin_user": {
        "user_id": "29bd791a-5e87-4060-bf73-bb9036814a7e",
        "user_account": "kawaii@zenshanmei.com"
      },
      "note": "",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_share_patient": true,
      "is_using_eck_dressing": true
    },
    {
      "index": 1,
      "long_term_care_institution_id": "efa66bc1-b68b-43b0-af8a-5b3a656095c5",
      "organization_id": "560ac59e-61d7-41cf-90cd-1bcfe88a80d7",
      "organization_name": "新北市安心老人長期照護中心",
      "address": "新北市三峽區大學路111號1樓",
      "tax_id_number": "19283943",
      "responsible_person": "安Ｏ新",
      "fax": "02-98765432",
      "email": "kiki@ansing.com",
      "contact_windows": [
        {
          "contact_window_serial_number": 1,
          "contact_window_name": "陳Ｏ麗",
          "contact_window_phone": "0987654333"
        },
        {
          "contact_window_serial_number": 2,
          "contact_window_name": "盧Ｏ織",
          "contact_window_phone": "0987654444"
        }
      ],
      "consultant": {
        "consultant_id": "8e6dd4d0-c766-461b-9ab0-261aa744cb86",
        "employee_name": "周Ｏ怡",
        "position_description": "專科護理師",
        "division_name": "一般外科"
      },
      "admin_user": {
        "user_id": "b493c67f-c416-4c46-90d5-e0739257dcf2",
        "user_account": "kiki@ansing.com"
      },
      "note": "",
      "last_update_time": "2019-07-18T13:54:37.917+0000",
      "is_share_patient": true,
      "is_using_eck_dressing": true
    }
  ],
  "page_info": {
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|index|int|第幾筆資料(分頁用)|0|
|long_term_care_institution_id|uuid|長照機構ID|efa66bc1-b68b-43b0-af8a-5b3a656095c5|
|organization_id|uuid|組織ID|560ac59e-61d7-41cf-90cd-1bcfe88a80d7|
|organization_name|string|組織名稱|新北市安心老人長期照護中心|
|address|string|地址|新北市三峽區大學路111號1樓|
|tax_id_number|string|統一編號|19283943|
|responsible_person|string|負責人|安Ｏ新|
|fax|string|傳真|02-98765432|
|email|string|電子郵件|kiki@ansing.com|
|contact_windows|array of contact_window|聯絡人||
|consultant|object of consultant|諮詢窗口||
|admin_user|object of admin_user|長照機構高級管理者帳號||
|note|string|備註||
|last_update_time|datetime|更新時間|2019-07-18T13:54:37.917+0000|
|is_share_patient|bool|是否分享病患資料|true<br>false|
|is_using_eck_dressing|bool|使否引用醫院敷料|true<br>false|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|total_count|int|總筆數|980|

*contact_window*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|contact_window_serial_number|int|聯絡人流水號|1|
|contact_window_name|string|聯絡人姓名|陳Ｏ麗|
|contact_window_phone|string|聯絡人電話|0987654333|

*consultant*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|consultant_id|uuid|諮詢對象ID|8e6dd4d0-c766-461b-9ab0-261aa744cb86|
|employee_name|string|諮詢對象姓名|周Ｏ怡|
|position_description|string|諮詢對象職稱|專科護理師|
|division_name|string|諮詢對象科別|一般外科|

*admin_user*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|user_id|uuid|長照機構高級管理者使用者ID|b493c67f-c416-4c46-90d5-e0739257dcf2|
|user_account|string|長照機構高級管理者使用者帳號|新增時的email(新增後無法修改)|

<br>
<br>
<br>
<br>

### 19.2. POST /long-term-care-institutions

|欄位|描述|
|:---|:---|
|Description|新增長照機構|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->長照機構管理管理|

**Note**
- 新增organization，填入organization_name
- 新增long_term_care_institutions，填入剛新增的organization_id，和Request Body帶入的address、tax_id_number、responsible_person、fax、email、consultant_id、note
- 新增long_term_care_institution_contact_windows，填入Request Body帶入的contact_window_serial_number、contact_window_name、contact_window_phone
- 新增users、roles，用email當作user_account，role_code=long_term_care_institution_superadmin(長照機構高級管理者(Web))

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request Body**

```json
{
  "organization_name": "台北市真善美老人長期照護中心",
  "address": "台北市松山區忠孝東路9號1樓",
  "tax_id_number": "12930040",
  "responsible_person": "王Ｏ同",
  "fax": "02-88761234",
  "email": "kawaii@zenshanmei.com",
  "contact_windows": [
    {
      "contact_window_serial_number": 1,
      "contact_window_name": "李Ｏ明",
      "contact_window_phone": "0987654321"
    },
    {
      "contact_window_serial_number": 2,
      "contact_window_name": "張Ｏ堂",
      "contact_window_phone": "0987654322"
    }
  ],
  "consultant": {
    "consultant_id": "1ba0eb72-86e0-4a5d-8ba9-60389dff1249"
  },
  "note": ""
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|asker_organization_name|string|組織名稱|新北市安心老人長期照護中心|
|require|address|string|地址|新北市三峽區大學路111號1樓|
|require|tax_id_number|string|統一編號|19283943|
|require|responsible_person|string|負責人|安Ｏ新|
|require|fax|string|傳真|02-98765432|
|require|email|string|電子郵件|kiki@ansing.com|
|require|contact_windows|array of contact_window|聯絡人||
|require|consultant|object of consultant|諮詢窗口||
|require|note|string|備註||

*contact_window*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|contact_window_serial_number|int|聯絡人流水號|1|
|require|contact_window_name|string|聯絡人姓名|陳Ｏ麗|
|require|contact_window_phone|string|聯絡人電話|0987654333|

*consultant*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant_id|uuid|諮詢對象ID|8e6dd4d0-c766-461b-9ab0-261aa744cb86|

***
**Response Body**

*HTTP 201*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|


<br>
<br>
<br>
<br>

### 19.3. PUT /long-term-care-institutions/{long-term-care-institution-id}

|欄位|描述|
|:---|:---|
|Description|修改長照機構|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->長照機構管理|

**Note**
- 長照機構高級管理者帳號(users)的內容不可變更，其餘才可以修改

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|long-term-care-institution-id|uuid|長照機構ID|afc28428-f75e-4f7e-9814-114307b004dd|

***
**Request Body**

```json
{
  "organization_name": "台北市真善美老人長期照護中心",
  "address": "台北市松山區忠孝東路9號1樓",
  "tax_id_number": "12930040",
  "responsible_person": "王Ｏ同",
  "fax": "02-88761234",
  "email": "kawaii@zenshanmei.com",
  "contact_windows": [
    {
      "contact_window_serial_number": 1,
      "contact_window_name": "李Ｏ明",
      "contact_window_phone": "0987654321"
    },
    {
      "contact_window_serial_number": 2,
      "contact_window_name": "張Ｏ堂",
      "contact_window_phone": "0987654322"
    }
  ],
  "consultant": {
    "consultant_id": "1ba0eb72-86e0-4a5d-8ba9-60389dff1249"
  },
  "note": ""
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|asker_organization_name|string|組織名稱|新北市安心老人長期照護中心|
|require|address|string|地址|新北市三峽區大學路111號1樓|
|require|tax_id_number|string|統一編號|19283943|
|require|responsible_person|string|負責人|安Ｏ新|
|require|fax|string|傳真|02-98765432|
|require|email|string|電子郵件|kiki@ansing.com|
|require|contact_windows|array of contact_window|聯絡人||
|require|consultant|object of consultant|諮詢窗口||
|require|note|string|備註||

*contact_window*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|contact_window_serial_number|int|聯絡人流水號|1|
|require|contact_window_name|string|聯絡人姓名|陳Ｏ麗|
|require|contact_window_phone|string|聯絡人電話|0987654333|

*consultant*

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|consultant_id|uuid|諮詢對象ID|8e6dd4d0-c766-461b-9ab0-261aa744cb86|

***
**Response Body**

*HTTP 200*
```json
{
  "long_term_care_institution_id": "84d10f61-eb11-494a-93b1-28de88cd3c50",
  "organization_id": "23a9c105-b4a5-4fc7-8f9c-e09aec8ebd21",
  "organization_name": "台北市真善美老人長期照護中心",
  "address": "台北市松山區忠孝東路9號1樓",
  "tax_id_number": "39381019",
  "responsible_person": "王Ｏ同",
  "fax": "02-88761234",
  "email": "kawaii@zenshanmei.com",
  "contact_windows": [
    {
      "contact_window_serial_number": 1,
      "contact_window_name": "王Ｏ明",
      "contact_window_phone": "0987654321"
    },
    {
      "contact_window_serial_number": 2,
      "contact_window_name": "張Ｏ堂",
      "contact_window_phone": "0987654322"
    }
  ],
  "consultant": {
    "consultant_id": "1ba0eb72-86e0-4a5d-8ba9-60389dff1249",
    "employee_name": "李Ｏ欣",
    "position_description": "專科護理師",
    "division_name": "心臟外科"
  },
  "admin_user": {
    "user_id": "29bd791a-5e87-4060-bf73-bb9036814a7e",
    "user_account": "kawaii@zenshanmei.com"
  },
  "note": "",
  "last_update_time": "2019-07-18T13:54:37.917+0000",
  "is_share_patient": true,
  "is_using_eck_dressing": true
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|long_term_care_institution_id|uuid|長照機構ID|efa66bc1-b68b-43b0-af8a-5b3a656095c5|
|organization_id|uuid|組織ID|560ac59e-61d7-41cf-90cd-1bcfe88a80d7|
|organization_name|string|組織名稱|新北市安心老人長期照護中心|
|address|string|地址|新北市三峽區大學路111號1樓|
|tax_id_number|string|統一編號|19283943|
|responsible_person|string|負責人|安Ｏ新|
|fax|string|傳真|02-98765432|
|email|string|電子郵件|kiki@ansing.com|
|contact_windows|array of contact_window|聯絡人||
|consultant|object of consultant|諮詢窗口||
|admin_user|object of admin_user|長照機構高級管理者帳號||
|note|string|備註||
|last_update_time|datetime|更新時間|2019-07-18T13:54:37.917+0000|
|is_share_patient|bool|是否分享病患資料|true<br>false|
|is_using_eck_dressing|bool|使否引用醫院敷料|true<br>false|

<br>
<br>
<br>
<br>

### 19.4. PUT /long-term-care-institutions/{long-term-care-institution-id}/password

|欄位|描述|
|:---|:---|
|Description|重新設定長照機構密碼|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->長照機構管理|

**Note**
- password隨機產生一組6位"小寫英文＆數字"組合的值

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|long-term-care-institution-id|uuid|長照機構ID|afc28428-f75e-4f7e-9814-114307b004dd|

***
**Request Body**

```json

```

***
**Response Body**

*HTTP 200*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

### 19.5. PUT /long-term-care-institutions/{long-term-care-institution-id}/is_delete

|欄位|描述|
|:---|:---|
|Description|邏輯刪除長照機構|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->長照機構管理|

**Note**
- 把is_delete欄位改為true
- 長照機構病患及藥膏、敷料、特殊器材、現行處置設定都保留

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|long-term-care-institution-id|uuid|長照機構ID|afc28428-f75e-4f7e-9814-114307b004dd|

***
**Request Body**

```json
{
  "is_delete": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_delete|bool|是否已刪除|true<br>false|

***
**Response Body**

*HTTP 200*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

### 19.6. PUT /long-term-care-institutions/{long-term-care-institution-id}/is-using-eck-dressing

|欄位|描述|
|:---|:---|
|Description|修改長照機構敷料引用權限|
|Pagination|N/A|
|Access Control|恩主公高級管理者、恩主公一般管理者|
|Using|Web->長照機構管理|

**Note**
- 修改is_using_eck_dressing

***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|long-term-care-institution-id|uuid|長照機構ID|afc28428-f75e-4f7e-9814-114307b004dd|

***
**Request Body**

```json
{
  "is_using_eck_dressing": true
}
```

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|is_using_eck_dressing|bool|使否引用醫院敷料|true<br>false|

***
**Response Body**

*HTTP 200*
```json

```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|

<br>
<br>
<br>
<br>

## 20. care-item-follow-ups
### 20.1. GET /users/{user-id}/care-item-follow-ups

|欄位|描述|
|:---|:---|
|Description|查詢關懷清單回診資料|
|Pagination|cursored-base|
|Access Control|恩主公醫師、恩主公護理師|
|Using|App->通知|

**Note**
- 查詢資料表
  - 查詢使用者本人所加的關懷清單病患 JOIN care_item_follow_ups JOIN notifications 的資料
  - 依照回診日期降冪排序
- 將以上查詢到的資料notifications.is_read改為true
- 查詢時的時間範圍從該病患被加入關懷清單的時間開始以後的所有資料
  
***
**Request Header**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|authorization|string|bearer+空格+JWT token|bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c|

***
**Request URL Parameters**

|Require|Name|Data Type|Description|Example|
|:-|:-|:-|:-|:-|
|require|user-id|string|使用者ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|first|int|回傳開頭的前N筆資料|10|
|option|after|string|回傳該curosr後面的資料。一定要搭配first|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|option|last|int|回傳倒數的N筆資料|10|
|option|before|string|回傳該curosr前面的資料。一定要搭配last|98a4f867-8dcd-4982-aa3a-14e1030bcd88|

***
**Response Body**

*HTTP 200*
```json
{
  "edges": [
    {
      "cursor": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "notification_id": "98a4f867-8dcd-4982-aa3a-14e1030bcd88",
      "notification_description": "已掛號回診",
      "chart_id": "12b5a2c7-a13d-a98b-123a-44215abda121",
      "chart_no": "093948234",
      "patient_name": "王Ｏ明",
      "clinic_date": "2019-10-30",
      "division_id": "0d5db08a-897d-4399-83aa-499fb6001218",
      "division_no": "S001",
      "division_name": "一般外科",
      "visiting_staff_no": "00123",
      "visiting_staff_name": "吳Ｏ明",
      "position_description": "主治醫師"
    },
    {
      "cursor": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "notification_id": "89ea130a-2bc2-4ea6-9b77-719e23279435",
      "notification_description": "已掛號回診",
      "chart_id": "152ba30d-434f-123d-aca1-92aefad12987",
      "chart_no": "04820392",
      "patient_name": "李Ｏ龍",
      "clinic_date": "2019-11-20",
      "division_id": "0d5db08a-897d-4399-83aa-499fb6001218",
      "division_no": "S001",
      "division_name": "一般外科",
      "visiting_staff_no": "00123",
      "visiting_staff_name": "吳Ｏ明",
      "position_description": "主治醫師"
    }
  ],
  "page_info": {
    "has_next_page": true,
    "has_previous_page": true,
    "total_count": 2
  }
}
```

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|edges|array|資料內容||
|page_info|object|分頁資訊||

*edges*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|cursor|string|定位點(提供下次分頁查詢用)，依據不同資料表有不同格式，通常是用primary key當作cursor|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|notification_id|string|通知ID|98a4f867-8dcd-4982-aa3a-14e1030bcd88|
|notification_description|string|通知訊息內容|已掛號回診|
|chart_id|string|病歷ID|12b5a2c7-a13d-a98b-123a-44215abda121|
|chart_no|string|病歷號碼|09383043|
|patient_name|string|姓名|王Ｏ明|
|clinic_date|date|回診日期|2019-10-30|
|division_id|uuid|科別ID|0d5db08a-897d-4399-83aa-499fb6001218|
|division_no|string|科別編號|S001|
|division_name|string|科別名稱|一般外科|
|visiting_staff_no|string|主治醫師編號|00234|
|visiting_staff_name|string|主治醫師姓名|陳Ｏ誠|
|position_description|string|諮詢對象職稱|主任醫師|

*page_info*

|Name|Data Type|Description|Example|
|:-|:-|:-|:-|
|has_next_page|bool|是否有下一頁|true<br>false|
|has_previous_page|bool|是否有上一頁|true<br>false|
|total_count|int|總筆數|20|

<br>
<br>
<br>
<br>
