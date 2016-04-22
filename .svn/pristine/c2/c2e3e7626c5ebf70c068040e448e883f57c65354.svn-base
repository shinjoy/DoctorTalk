<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>FileUploader Callback</title>
</head>
<body>
	<form method="post" name=frm>
		<input type="hidden" name="callback" value="${callback}">
		<input type="hidden" name="callback_func" value="${callback_func}">
		<input type="hidden" name="errstr" value="${errstr}">
		<input type="hidden" name="bNewLine" value="${bNewLine}">
		<input type="hidden" name="sFileName" value="${sFileName}">
		<input type="hidden" name="sFileURL" value="${sFileURL}">
		<input type="hidden" name="params" value="${params}">
	</form>

    <script type="text/javascript">

			// document.domain
			//try { document.domain = "http://218.234.17.138:8080"; } catch(e) {}
			try { document.domain = "http://218.234.17.222:8088"; } catch(e) {}
			//try { document.domain = "http://218.234.17.138"; } catch(e) {}
			
	        // execute callback script
	        //var sUrl = document.location.search.substr(1);
    		var sUrl = frm.params.value.substr(1);
	        //var fUrl = document.location.href;
	        //alert("sUrl : "+ sUrl);
	        //alert("fUrl : "+ fUrl);
	        
			if (sUrl != "blank") {
		        var oParameter = {}; // query array
	
		        sUrl.replace(/([^=]+)=([^&]*)(&|$)/g, function(){
		            oParameter[arguments[1]] = arguments[2];
		            return "";
		        });
		    	//alert("oParameter : "+ oParameter);
		    	//alert("oParameter : "+ oParameter.sFileName);
		    	//alert("oParameter : "+ oParameter.callback_func);
		    	//alert("oParameter : "+ oParameter.callback_func+'_success');
		        
		        if ((oParameter.errstr || '').length) { // on error
		            (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_error'])(oParameter);
		        } else {
			        (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_success'])(oParameter);
			   }
			}
    </script>
</body>
</html>