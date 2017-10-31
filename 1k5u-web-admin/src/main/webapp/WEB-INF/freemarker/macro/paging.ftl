<#-- 分页组件

 使用方式：
    <#import "/macro/paging.ftl" as page>
    <@page.pageInput condition.startIndex condition.maxCount />
    <@page.pageList pageNumber=PageParam.pageNumber pageCount=PageParam.pageCount />
、
属性说明：
   startIndex     查询开始记录序号
   maxCount			每页最大显示条数
   
   pageNumber     当前页码
   pageCount	总页码


 -->

<#macro pageInput startIndex=1 pageSize=15>
<input type="hidden" name="startIndex" id="startIndex" value="<#if startIndex gt 0>${startIndex-1}<#else>0</#if>">
<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
</#macro>


<#macro pageList pageNumber=1 pageCount=0> 

<ul class="pagination pagination-sm m-t-none m-b-none">

                      
		 
		 <#if pageNumber gt 1 >
		 
			 <li><a href="javascript:void(0)"  onclick="prePage(); ">上一页</a></li>
		 
		 </#if>
		 
		 
		 
		 <#if pageNumber gt 2 >
		 
			 <li><a href="javascript:void(0)" onclick="getPage(${pageNumber-2}) ; " >${pageNumber-2}</a></li>
		 
		 </#if>
		 
		<#if pageNumber gt 1 >
		 
			 <li><a  href="javascript:void(0)" onclick=" getPage(${pageNumber-1}) ; " >${pageNumber-1}</a></li>
		 
		 </#if>
		 
		 <li><span>${pageNumber}</span></li>
		 
		 <#if pageNumber lt pageCount>
			 <li><a  href="javascript:void(0)" onclick=" getPage(${pageNumber+1}) ; " >${pageNumber+1}</a></li>
		 </#if>
		 
		 <#if pageNumber+1 lt pageCount>
		 	<li><a  href="javascript:void(0)" onclick=" getPage(${pageNumber+2}) ; " >${pageNumber+2}</a></li>
		 </#if>
		 <#if pageNumber lt pageCount>
		 	<li><a href="javascript:void(0)"  onclick=" nextPage() ; ">下一页</a></li>
		  </#if>
</ul>
		 
 </#macro>