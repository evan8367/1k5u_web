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

                    <li class="active">用户管理</li>

                  </ul>
            </header>
            
            <section class="scrollable wrapper">
              
              <section class="panel panel-default">

                <header class="panel-heading">
                  用户管理
                </header>
                
					<div class="row wrapper">
					  <form class="form-inline" id="queryForm" name="queryForm" action="userList.htm" role="form" method="post">
					 
					<div class="form-group m-l m-r-xs">
					      <input type="text" name="phone" class="input-sm form-control" placeholder="手机号" value="${phone!}">
					      <button class="btn btn-sm btn-default" type="submit">搜索</button>          
					</div>	
					<button type="button" class="btn btn-info pull-right m-r-lg" onclick="self.location.href='editUserInfo.htm';">添加用户</button>
									
					<@page.pageInput pageInfo.startRow pageInfo.pageSize />
					  </form>
					</div>

                

                <div class="table-responsive">

                  <table class="table table-striped b-t b-light">

                    <thead>

                      <tr>
                        <th class="text-center">用户姓名</th>

                        <th class="text-center">用户手机号</th>
                                                
                        <th class="text-center">用户公司</th>
                        
                        <th class="text-center">操作</th>

                      </tr>

                    </thead>

                    <tbody>
						<#list pageInfo.list as user>
                      <tr>   
                        <td class="text-center v-middle">${user.name!}</td>                       
                        <td class="text-center v-middle">${user.phone!}</td>
                        
                        <td class="text-center v-middle">${user.companyName!}</td>
                        <td class="text-center v-middle">
                          <a href="editUserInfo.htm?id=${user.id!}" class="btn btn-sm btn-info">编辑</a>
                        </td>

                      </tr>
                      </#list>

                     

                      

                    </tbody>

                  </table>

                </div>
                </div>

                <footer class="panel-footer">

                  <div class="row">
                    <div class="col-sm-12 text-right text-center-xs">                

                      <@page.pageList pageNumber=pageInfo.pageNum pageCount=pageInfo.pages />

                    </div>


                </footer>
              </section>




            </section>

          </section>

          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>

        

</body>

</html>