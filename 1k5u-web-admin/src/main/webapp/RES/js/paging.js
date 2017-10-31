	//下一页
	function nextPage()
	{
		var startIndex = parseInt( $("#startIndex").val() );
		$("#startIndex").val(startIndex + parseInt( $("#pageSize").val()) ) ;
		
		document.queryForm.submit() ;
	}
	
	//上一页
	function prePage()
	{
		var startIndex = parseInt( $("#startIndex").val() );
		$("#startIndex").val(startIndex - parseInt( $("#pageSize").val()) ) ;
		
		document.queryForm.submit() ;
	}
	
	//指定页
	function getPage(obj)
	{
		$("#startIndex").val((obj-1) * parseInt( $("#pageSize").val()) ) ;
		
		document.queryForm.submit() ;
	}
	
	
	//查询按钮
	function queryInfo()
	{
		$("#startIndex").val(0) ;
		
		document.queryForm.submit() ;
	}