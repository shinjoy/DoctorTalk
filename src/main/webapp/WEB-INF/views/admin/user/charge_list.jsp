<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
function pointChargeForm(pointEditForm) {

	if (pointEditForm.chargeMoney1.value == "") {
		alert("충전액을 입력해주세요.");
		return false;
	}
	if (pointEditForm.chargePoint1.value == "") {
		alert("충전포인트를 입력해주세요.");
		return false;
	}
	if (pointEditForm.point1.value == "") {
		alert("적용포인트를 입력해주세요.");
		return false;
	}
	
	if (pointEditForm.comment1.value == "") {
		alert("혜택을 입력해주세요.");
		return false;
	}
	
	var param = {
		chargeMoney : pointEditForm.chargeMoney1.value,
		chargePoint : pointEditForm.chargePoint1.value,
		point : pointEditForm.point1.value,
		comment : pointEditForm.comment1.value
	};
	
	
	$.ajax({
		type:"POST",
		url:"/admin/user/charge_edit_do.go",
		dataType:"json",
		data:param,
		success:function(json){
			alert(json.message);
			if (json.result) {
				document.location.reload();
			}
		}
	});

	return false;
}
</script>



<div>
	<form method="post" name="pointEditForm" onsubmit="return false;">
		<table class="list">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="20%">
				<col width="25%">
				<col width="15%">			
			</colgroup>
			<thead>
				<tr>
					<th>충전액</th>
					<th>충전포인트</th>
					<th>적용포인트</th>
					<th>혜택</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}">
						<input type="hidden" name="chargeSeq"  value="${it.chargeSeq}"> 
						<tr>
							<td style="text-align:left; padding-left:10px;"><input type="text" class="itext"  name="chargeMoney1" value="${it.chargeMoney}">원</td>
							<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="chargePoint1" value="${it.chargePoint}">P</td>
							<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="point1" value="${it.point}">P</td>
							<td style="text-align:left; padding-left:10px;"><input type="text" class="itext" name="comment1" value="${it.comment}"></td>
							<td>
								<button type="button" class="btn" onclick="pointChargeForm(this.form);">수정</button>
								<button class="btn" onclick="deletePoint(${it.chargeSeq});">삭제</button>
							</td>					
						</tr>
					
				</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
					<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td>
				</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</form>	
</div>

	<div style="margin-top:30px;">
		<h2>포인트 충전 정책 등록</h2> 
		<form method="post" name="pointForm" onsubmit="return submitForm(this); return false;">
			<input type="hidden" name="chargeSeq"  value="0"> 
			<table class="list">
				<tr>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="chargeMoney" >원</td>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="chargePoint" >P</td>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="point" >P</td>
					<td style="text-align: left; padding-left: 10px;"><input type="text" class="itext" name="comment" ></td>
					<td><button class="btn">추가</button>  </td>
				</tr>
			</table>
		</form>		
	</div>
		
	
	
