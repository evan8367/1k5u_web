function preview(file, previewId, width, height)  
 {  
	 var prevDiv = document.getElementById(previewId);  
	 if (file.files && file.files[0])  {  
		 var reader = new FileReader();  
		 reader.onload = function(evt){  
			 if(width != null && height != null) {
				 prevDiv.innerHTML = '<img class="tip" src="' + evt.target.result + '" width="'+width+'" height="'+height+'" />';
			 }
			 else if(width != null && height == null) {
				 prevDiv.innerHTML = '<img class="tip" src="' + evt.target.result + '" width="'+width+'" />';
			 } else if(width == null && height != null) {
				 prevDiv.innerHTML = '<img class="tip" src="' + evt.target.result + '" height="'+height+'" />';
			 } else {
				 prevDiv.innerHTML = '<img class="tip" src="' + evt.target.result + '" />';
			 }
			 
			 showImageTip(prevDiv.getElementsByTagName("IMG")[0]);
		 }    
	 	reader.readAsDataURL(file.files[0]);  
	}  
	 else    
	 {  
		 prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		 showImageTip(prevDiv.getElementsByTagName("DIV")[0]);
	 }  
	 
	 
 } 
function previewfile(file, previewId)  
{  
	 var prevDiv = document.getElementById(previewId);  
	 if (file.files && file.files[0])  {  
		 var reader = new FileReader();  
		 reader.onload = function(evt){  
			 prevDiv.innerHTML = '<p class="tip" >'+file.value+'</p>';
		 }    
	 	reader.readAsDataURL(file.files[0]);  
	}   
	 
	 
} 

function showImageTip(obj) {
	var obj1;
	if(obj != null)
		obj1 = $(obj);
	else
		obj1 = $('.tip'); 
	obj1.mouseover(function () {
	      var $tip = $('<div id="tip"><div class="t_box"><div><s><i></i></s><img src="' + this.src + '" width="500" /></div></div></div>');
	      $('body').append($tip);
	      $('#tip').show('fast');
	    }).mouseout(function () {
	      $('#tip').remove();
	    }).mousemove(function (e) {
	      $('#tip').css({ "top": (e.pageY - 60) + "px", "left": (e.pageX + 30) + "px" })
	    });
}