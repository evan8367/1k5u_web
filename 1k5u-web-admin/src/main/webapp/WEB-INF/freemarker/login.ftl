<!DOCTYPE html>

<html lang="en" class="app">

<head>  

  <meta charset="utf-8" />

  <title>简法后台登录</title>
 	
	<#include "/common/include.ftl"/>
  <script language="javascript">
  $(function(){
  	if (window!=top) // 判断当前的window对象是否是top对象
		top.location.href =window.location.href; // 如果不是，将top对象的网址自动导向被嵌入网页的网址
  });
  </script>

</head>

<body class="bg-info dker">

  <section id="content" class="m-t-lg wrapper-md animated fadeInUp">    

    <div class="container aside-xl">

      <a class="block text-center" href="#"><h2>简法</h2></a>

      <section class="m-b-lg">

        <header class="wrapper text-center">

        </header>

        <form action="loginSubmit.htm" data-parsley-validate id="submitForm" method="post">
        
          <div class="form-group">

            <input type="text" name="adminName" placeholder="账号" class="form-control rounded input-lg text-center" required >

          </div>

          <div class="form-group">

             <input type="password" name="password"  placeholder="密码" class="form-control rounded input-lg text-center" required>

          </div>

          <button type="button" onclick="subForm();" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span></button>

        </form>

      </section>

    </div>

  </section>

  <!-- footer -->

  <footer id="footer">

    <div class="text-center padder">

      <p>

        <small>简法后台管理平台<br>&copy; 2017</small>

      </p>

    </div>

  </footer>
<script type="text/javascript">
 function subForm(){
 	 $('#submitForm').submit(); 
 }
 
</script> 
</body>

</html>