<!DOCTYPE html>

<html lang="en" class="app">

<head>  

  <title>API-首页</title>

	<#include "/common/include.ftl" />



  <script type="text/javascript">

  function setIframeWidth(obj) 
  {
  	//obj.style.width = ($(window).width()-200) + "px"; 
  	//obj.height = "100%";
  } 
 
  function redirectUrl(url) {
  	document.getElementById("iframeContent").src = url;
  }


    </script>

</head>

<body class="">

  <section class="vbox">

    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">

      <div class="navbar-header aside bg-info dk">

        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">

          <i class="icon-list"></i>

        </a>

        <a href="index.html" class="navbar-brand text-lt">

          <i class=" icon-briefcase"></i>

          <img src="images/logo.png" alt="." class="hide">

          <span class="hidden-nav-xs m-l-sm">简法</span>

        </a>

        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">

          <i class="icon-settings"></i>

        </a>

      </div>      <ul class="nav navbar-nav hidden-xs">

        <li>

          <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">

            <i class="fa fa-indent text"></i>

            <i class="fa fa-dedent text-active"></i>

          </a>

        </li>

      </ul>

      <div class="navbar-right ">

        <ul class="nav navbar-nav m-n hidden-xs nav-user user">

          <li class="dropdown">

            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown" id="username">


               <b class="caret"></b>

            </a>

            <ul class="dropdown-menu animated fadeInRight">            

              <li>

                <span class="arrow top"></span>

                <a href="login.htm">退出登录</a>

              </li>

            </ul>

          </li>

        </ul>

      </div>      

    </header>

    <section>

      <section class="hbox stretch" style="height:100%">

       <!-- .aside -->

        <aside class="bg-black dk aside hidden-print" id="nav">          

          <section class="vbox">

            <section class="w-f-md scrollable">

              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">

                <!-- nav -->                 

                <nav class="nav-primary hidden-xs">
                  <ul class="nav" data-ride="collapse">

                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">


                    </li>

                    <li >

                      <a href="#" class="auto">

                        <span class="pull-right text-muted">

                          <i class="fa fa-angle-left text"></i>

                          <i class="fa fa-angle-down text-active"></i>

                        </span>

                        <i class="icon-user icon">

                        </i>

                        <span>基本管理</span>

                      </a>

                      <ul class="nav dk text-sm">
                        <li >

                          <a href="javascript:redirectUrl('base/userList.htm');" class="auto">                                                        

                            <i class="fa fa-angle-right text-xs"></i>



                            <span>用户管理</span>

                          </a>

                        </li>
                        <li >

                          <a href="javascript:redirectUrl('base/companyList.htm');" class="auto">                                                        

                            <i class="fa fa-angle-right text-xs"></i>



                            <span>公司管理</span>

                          </a>

                        </li>
                       
                      </ul>

                    </li>
                    <li >

                      <a href="#" class="auto">

                        <span class="pull-right text-muted">

                          <i class="fa fa-angle-left text"></i>

                          <i class="fa fa-angle-down text-active"></i>

                        </span>

                        <i class="icon-home icon">

                        </i>

                        <span>首页管理</span>

                      </a>

                      <ul class="nav dk text-sm">
                        <li >

                          <a href="javascript:redirectUrl('home/adList.htm');" class="auto">                                                        

                            <i class="fa fa-angle-right text-xs"></i>



                            <span>广告管理</span>

                          </a>

                        </li>
                        <li >

                          <a href="javascript:redirectUrl('home/caseList.htm');" class="auto">                                                        

                            <i class="fa fa-angle-right text-xs"></i>



                            <span>案例管理</span>

                          </a>

                        </li>
                       
                      </ul>

                    </li>
                    <li >

                      <a href="#" class="auto">

                        <span class="pull-right text-muted">

                          <i class="fa fa-angle-left text"></i>

                          <i class="fa fa-angle-down text-active"></i>

                        </span>

                        <i class="icon-question icon">

                        </i>

                        <span>咨询管理</span>

                      </a>

                      <ul class="nav dk text-sm">
                        <li >

                          <a href="javascript:redirectUrl('consultation/consultationList.htm');" class="auto">                                                        

                            <i class="fa fa-angle-right text-xs"></i>



                            <span>用户咨询</span>

                          </a>

                        </li>
                       
                      </ul>

                    </li>
                    <li >

                      <a href="#" class="auto">

                        <span class="pull-right text-muted">

                          <i class="fa fa-angle-left text"></i>

                          <i class="fa fa-angle-down text-active"></i>

                        </span>

                        <i class="icon-heart icon">

                        </i>

                        <span>服务管理</span>

                      </a>

                      <ul class="nav dk text-sm">

                        <li >

                          <a href="javascript:redirectUrl('service/templateList.htm');" class="auto">                            
                                                                                 
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>文件模版管理</span>

                          </a>

                        </li>

                        <li >

                          <a href="javascript:redirectUrl('service/thirdPartyList.htm');" class="auto">                            
                                                                                 
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>第三方服务管理</span>

                          </a>

                        </li>
                    
                        <li >

                          <a href="javascript:redirectUrl('service/videoList.htm');" class="auto">                            
                                                                                 
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>简法课堂管理</span>

                          </a>

                        </li>

                        <li >

                          <a href="javascript:redirectUrl('service/activityList.htm');" class="auto">                            
                                                                                 
                            <i class="fa fa-angle-right text-xs"></i>
                            <span>公益活动管理</span>

                          </a>

                        </li>
                    
   
                      

                      </ul>
                      </li>
                     
                   
                   
                      </ul>
                    </li>
                  </ul>
                </nav>
                <!-- / nav -->
              </div>
            </section>

          </section>

        </aside>

        <!-- /.aside -->
        <section id="content">
       	<iframe id="iframeContent" name="content" width="100%" height="100%" src="welcome.htm" frameborder="0" scrolling="auto" onload="setIframeWidth(this);" >
		</section>
      </section>

    </section>    

  </section>

</body>

</html>