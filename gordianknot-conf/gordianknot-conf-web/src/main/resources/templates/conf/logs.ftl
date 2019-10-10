<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>修改日誌 - ${projectName!}配置系統</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <#include "header.ftl">
  <#include "meun.ftl">
   <div class="content-wrapper">
    <section class="content">
      <div class="row">
      	<div class="col-xs-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
              <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
              	<div class="row"  style="margin-bottom:10px;margin-left:2px;font-size:14px;font-weight:bold;">
              	            所屬環境：<#if conf.env=='test'>線下測試環境</#if>
              	           <#if conf.env=='dev'>程序開發環境</#if>
              	           <#if conf.env=='online'>線上測試環境</#if>
              	           <#if conf.env=='prod'>程序生產環境</#if>
              	           &nbsp;&nbsp;&nbsp;&nbsp;
              		系統名稱：${conf.systemName!} &nbsp;&nbsp;&nbsp;&nbsp;
              		配置文件名稱：${conf.confFileName!}&nbsp;&nbsp;&nbsp;&nbsp;
              		配置Key：${conf.key!}
              	</div>
              	<div class="row">
              	<div class="col-sm-12">
              	<table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
                	<thead>
             		   <tr role="row">
             		      <th>原始值</th>
             		   	  <th>修改之後值</th>
             		   	  <th>修改用戶</th>
             		   	  <th>修改時間</th>
             		   	  <th>修改描述</th>
             		   </tr>
               		</thead>
 	                <tbody>
 	                	<#if logs??>
 	                		<#list logs as bo>
 		                		<tr>
 		                			<td>${bo.oldValue!}</td>
 		                			<td>${bo.newValue!}</td>
 		                			<td>${bo.username!}</td>
 		                			<td>${bo.updateTime?datetime}</td>
 		                			<td>${bo.updateDesc!}</td>
 		                		</tr>
 	                		</#list>
 	                	</#if>
 	                </tbody>
              </table>
              </div></div>
              <div class="row">
              	<div class="col-sm-5">
              		<!--<div class="dataTables_info" id="example1_info" role="status" aria-live="polite">共1000條&nbsp;100頁</div>-->
              	</div>
              	<div class="col-sm-7">
              	</div>
              	</div></div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      </div>
    </section>
  </div>
</div>
</body>
</html>