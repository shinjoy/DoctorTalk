<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
function submitForm(frm) {
	
	if (frm.menuName.value == "") {
		alert("메뉴명을 입력해주세요.");
		return false;
	}
	
	if (frm.cover.value == "") {
		alert("한줄 소개를 입력해주세요.");
		return false;
	}
	
	if (frm.menuOption1.value == "") {
		alert("가격을 입력해주세요.");
		return false;
	}
	if (frm.introduce.value == "") {
		alert("소개글을 입력해주세요.");
		return false;
	}
	if (frm.infoDelivery.value == "") {
		alert("배송안내를 입력해주세요.");
		return false;
	}
	
// 	var param = {
// 		seq	: frm.seq.value,
// 		userId	: frm.userId.value,
// 		notiType	: frm.notiType.value,
// 		title	: frm.title.value,
// 		ir1	: frm.ir1.value,
// 		ir1_text	: frm.ir1_text.value
// 	};
	
	$.ajax({
		type:"POST",
		url:"/admin/firm/menu_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.href = "/admin/firm/firm.go";
			}
		}
	});

	
	//frm.action = "/admin/notice/notice_edit_do.go";
	//frm.submit();
	return false;
}
</script> 



		
		<div class="tbl-list">
		<form method="post" name="firmForm" >	
			<table class="request">
				<tbody class="rl">
						<tr>
							<th><div class="icon"> 메뉴명</div></th>
							<td><input type="text" class="itext" name="menuName"  value="${keyword.keyword}"></td> 
						</tr>
						<tr>
							<th><div class="icon"> 한줄 소개</div></th>
							<td><input type="text" class="itext" name="cover"  value="${keyword.targetUrl}"></td>
						</tr>
						
						<tr>
							<th><div class="icon">가격</div></th>
							<td><input type="text" class="itext" name="menuOption1"  value="${keyword.keyword}"></td>
							<td><input type="text" class="itext" name="menuPrice1"  value="${keyword.keyword}"></td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><input type="text" class="itext" name="menuOption2"  value="${keyword.keyword}"></td>
							<td><input type="text" class="itext" name="menuPrice2"  value="${keyword.keyword}"></td>
						</tr>	
						<tr>
							<th>&nbsp;</th>
							<td><input type="text" class="itext" name="menuOption3"  value="${keyword.keyword}"></td>
							<td><input type="text" class="itext" name="menuPrice3"  value="${keyword.keyword}"></td>
						</tr>
						<tr>
							<th><div class="icon">소개글</div></th>
							<td><input type="text" class="itext" name="introduce"  value="${keyword.keyword}"></td>
						</tr>
						<tr>
							<th><div class="icon">이용안내</div></th>
							<td><input type="text" class="itext" name="keywordinput"  value="${keyword.keyword}"></td>
						</tr>
						<tr>
							<th>소개사진</th>
							<td>
								<c:choose>
									<c:when test="${analysis.photo1 == '' || analysis.photo1 == null}">
										<input type="file" name="photo3">(200*200픽셀 이하의 이미지만 업로드 시켜주세요.)
									</c:when>
									<c:otherwise>
										<img src="/files/imagefile/${analysis.photo1}" style="max-width:100%;">
										<br>
										<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	
										<img src="/files/imagefile/${analysis.photo2}" style="max-width:100%;">
										<br>
										<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	
										<img src="/files/imagefile/${analysis.photo3}" style="max-width:100%;">
										<br>
										<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	
									</c:otherwise>
								</c:choose>
							</td>
						</tr>	
						<tr>
							<th><div class="icon">배송(배달)안내</div></th>
							<td><input type="text" class="itext" name="infoDelivery"  value="${keyword.keyword}"></td>
						</tr>					
				</tbody>
			</table>
		
		</div>			
	
			<div><button type="button" class="btn" style="margin-top:10px; margin-left:10px;" onclick="submitForm(firmForm);"  >메뉴저장</button></div>						
		</form>		