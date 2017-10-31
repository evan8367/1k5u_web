<!DOCTYPE html>

<html lang="en" class="app">

<head>  

  <title>API-首页</title>

	<#include "/common/include.ftl" />

</head>

<body class="">
         


          <section class="vbox">

            <header>
              <ul class="breadcrumb">
                    <li><a href="#"><i class="fa fa-home"></i> 首页</a></li>

                    <li><i class="fa fa-list-ul"></i> 基础管理</li>

                    <li class="active">编辑信息</li>

                  </ul>
            </header>
            <section class="scrollable wrapper">

              <section class="panel panel-default">

                <header class="panel-heading font-bold">

                  <#if !user.id??> 新增<#else>编辑</#if>用户信息 (账号为手机号，初始密码与手机号相同)

                </header>

                <div class="panel-body">

                  <form action="editUserInfoSubmit.htm" class="form-horizontal" method="post" data-parsley-validate  enctype="multipart/form-data" id="submitForm">

                    <div class="form-group">

                      <label class="col-sm-2 control-label">姓名</label>

                      <div class="col-sm-6">
                        <input type="hidden" name="id" id="id" value="${user.id!}">
                      
                        <input type="text" name="name" id="name" value="${user.name!}" class="form-control" placeholder="姓名" required>

                      </div>

                    </div>

                    <div class="line line-dashed b-b line-lg pull-in"></div>

                    <!-- ----------------------------------------------------------------------------- -->
                    

                    <div class="form-group">

                      <label class="col-sm-2 control-label">手机号</label>

                      <div class="col-sm-6">

                         <input type="text" name="phone" id="name" value="${user.phone!}" class="form-control" placeholder="手机号" required>


                      </div>
                    </div>
                      <div class="line line-dashed b-b line-lg pull-in"></div>
    
                    <!-- ----------------------------------------------------------------------------- -->
                   
                       <div class="form-group">
                      <label class="col-sm-2 control-label" >所属公司</label>

                      <div class="col-sm-6">
						   
						<select name="companyId" class="form-control" id="company">
						<#list companyList as company>
                          <option value="${company.id!}" <#if user.companyId?? && company.id == user.companyId>selected</#if>>${company.name!}</option>
                         </#list>
                        </select>                        
                      </div>
                    </div>

                    <div class="line line-dashed b-b line-lg pull-in"></div>
    
                     
                    <!-- ----------------------------------------------------------------------------- -->
                    <div class="form-group">

                      <div class="col-sm-4 col-sm-offset-2">

                        <button type="button" class="btn btn-default" onclick="self.location.href='userList.htm'">取消</button>

                        <button type="button" class="btn btn-primary" onclick="submitM();">保存</button>

                      </div>

                    </div>

                  </form>

                </div>

              </section>
              
            </section>
          </section>

          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
<script type="text/javascript">
  function submitM(){
       $("#submitForm").submit();
   }
   
   <#if message??>
         	alert("${message!}");
   </#if>
   
</script> 
</body>

</html>