<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(4,2);
	});
	
	
	function submitForm(frm) {
		
		
// 		var frag = frm.frag.value;
// 		for(var i=0; i<frag.length; i++) // 일반적인 배열의 루프이지요?
// 		{
// 		    if(frag[i].checked == true) { // 하나만 체크되면 OK이므로
// 		    	frag = 1;
// 		    }
// 		}
// 		if (frag == 0) {
// 		    alert("플라그 수치를 선택해주세요.");
// 		    return false;
// 		} 

		if (frm.linkUrl.value == "") {
			alert("링크를 입력해 주세요.");
			return false;
		}
	
	 	var param = {
 			seq : frm.seq.value,
			linkUrl	: frm.linkUrl.value,
			backgroundColor : frm.backgroundColor.value,
			startDate : frm.startDate.value,
			endDate : frm.endDate.value,
			sido : frm.areaSido.value,
			gugun : frm.areaGugun.value
	 	};
		
		frm.action = "/admin/banner/banner_edit_do.go";
		frm.submit();
		return false;
	}
	
	function deleteBanner(seq,contentsHtml) {
		if(confirm("배너 광고를 삭제하시겠습니까?")) {
			var param = {
				seq	:	seq,
				imgFile : contentsHtml
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/banner/banner_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/banner/banner.go";
					}
				}
			});
		}
		return false;

	}
	
	function deleteImg(seq,contentsHtml) {
		if(confirm("이미지를 삭제하시겠습니까?")) {
			var param = {
					seq	:	seq,
					imgFile : contentsHtml
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/banner/banner_file_delete_do.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.reload();
					}
				}
			});
		}
		return false;

	}
	

	$(function() {
	    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
	    $( ".datepicker" ).datepicker(
	        {
	        	dateFormat: 'yy-mm-dd',
	 	       showButtonPanel: true
	        }
	    );
	});	
	
	function searchArea(frm) {
		
		if(frm.areaSido.value == "0"){
			$('areaSido').find('option:first').attr('selected', 'selected');
		}
		
		var param = {
				areaSido		: 	frm.areaSido.value
		};
		
				
		$.ajax({
			type:"POST",
			url:"/admin/area/search.go",
			dataType:"json",
			data:param,
			success:function(json){
				var list = json.list;
				var str = '<option value="">== 검색결과 ==</option>';
				
				for (var i=0; i<list.length; i++) {
					str += '<option value="'+list[i].gugun+'>'+list[i].gugun+'</option>';
				}
				$("#areaGugun").html(str);
			}
		});
	 
		return false;
	}
	
	function select(frm, obj) {
		frm.areaSeq.value = obj.value;
	}
	
	

</script>

<style>
table.register th {
   vertical-align: top;
   text-align: right;
   padding:10px;
   font-weight: bold;
}

table.register td {
	padding:10px;
    vertical-align: top;
}	

</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 배너관리 > 배너광고 등록/수정
			</header>
		
			<div class="contents-block">
			
				<h1>배너광고 등록/수정</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="printcenterForm"  enctype="multipart/form-data"  onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="seq" value="${bannerAd.bannerSeq}">
						<table class="register">
							<tr>
								<th><div class="icon">배너</div></th>
								<td>
									<c:choose>
										<c:when test="${bannerAd.contentsHtml == '' || bannerAd.contentsHtml == null}">
											
											<input type="file" name="imgFile">(200*200픽셀 이하의 이미지만 업로드 시켜주세요.)
										</c:when>
										<c:otherwise>
											<img src="/files/imagefile/${bannerAd.contentsHtml}" style="max-height:50px;">
											<button type="button" class="btn tiny" onclick="deleteImg(${bannerAd.bannerSeq},'${bannerAd.contentsHtml}');">삭제</button>	
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th><div class="icon">링크</div></th>
								<td><input type="text"  name="linkUrl" class="itext" value="${bannerAd.linkUrl}"></td>
							</tr>
							<tr>
								<th><div class="icon">배경색</div></th>
								<td><input type="text"  name="backgroundColor" class="itext" value="${bannerAd.backgroundColor}"></td>
							</tr>
							<tr>
								<th>게시기간</th>
								<td>
									<input type="text" name="startDate" class="itext datepicker" style="width:150px;" value="${bannerAd.startDate.length() >= 10 ? bannerAd.startDate.substring(0,10) : bannerAd.startDate}"> ~
									<input type="text" name="endDate" class="itext datepicker" style="width:150px;" value="${bannerAd.endDate.length() >= 10 ? bannerAd.endDate.substring(0,10) : bannerAd.endDate}">
								</td>
							</tr>
							<tr>
								<th><div class="icon">지역설정</div></th>
								<td>
									<select onchange="searchArea(this.form);" name="areaSido">
										<option value="0">=선택=</option>
										<c:forEach items="${location}" var="it" >
											<option value="${it.area_sido}" >${it.area_sido}</option>								
										</c:forEach>		
									</select>
							
									<select id="areaGugun" name="areaGugun" style="width:106px;">
										<option>== 검색 ==</option>
									</select>
								</td>
							</tr>
						</table>	
					</div>

				</div>
				
				<div class="btn-tools">
					<button type="button" class="btn" onclick="submitForm(printcenterForm);">배너저장</button>
					<c:if test="${bannerAd.bannerSeq > 0 }">
						<button type="button" class="btn" onclick="deleteBanner(${bannerAd.bannerSeq});">삭제</button>					
					</c:if>
					<button type="button" class="btn" onclick="document.location.href='/admin/banner/banner.go';">목록</button>
				</div>
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>