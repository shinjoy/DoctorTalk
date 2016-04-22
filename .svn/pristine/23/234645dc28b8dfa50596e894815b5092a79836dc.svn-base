<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
// 		aside.setActive(3,1);
 		aside.setActive(8,5);
	});

	/* 저장 */
	function submitForm(frm) {

// 		if (frm.goalSmblood.value == "") {
// 			alert("카테고리를 입력해 주세요.");
// 			return false;
// 		}

		var param = {
				indexSeq : frm.indexSeq.value,
				goalSmblood : frm.goalSmblood.value,
				goalBmblood : frm.goalBmblood.value,
				goalEblood : frm.goalEblood.value,
				goalEsblood : frm.goalEsblood.value,
				goalSblood : frm.goalSblood.value,
				goalSsblood : frm.goalSsblood.value,
				goalHba : frm.goalHba.value,
				goalSpre : frm.goalSpre.value,
				goalBpre : frm.goalBpre.value,
				goalSpreOld : frm.goalSpreOld.value,
				goalBpreOld : frm.goalBpreOld.value,
				goalPul : frm.goalPul.value,
				goalCol : frm.goalCol.value,
				goalLdl : frm.goalLdl.value,
				goalHdl : frm.goalHdl.value,
				goalTg : frm.goalTg.value,
				goalSbmi : frm.goalSbmi.value,
				goalBbmi : frm.goalBbmi.value
		};
		//console.log(param);
		$.ajax({
			type:"POST",
			url:"/admin/notice/manage_index_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json) {
				alert(json.message);
				if(json.result) {
					document.location.reload();
				}
			}
		});
		return false;
	}


	
</script>

<style>
	table.cat { margin-left:20px; }
	table.cat td { padding-bottom:10px; }
	td h2 { margin-top:10px; }
	.itext { text-align:right; }
</style>

</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 서비스관리 > 관리목표 설정
			</header>
		
			<div class="contents-block">
			
				<h1>관리목표 설정</h1>
				
				<div>
					<form method="post" name="printcenterForm" onsubmit="return submitForm(this); return false;">
					<input type="hidden" name="indexSeq" value="${goal.indexSeq}">	

						<div>		
							<h2>혈당</h1>		
							<table class="register">	
							<tbody>
								<tr>
									<th>아침공복혈당</th>
									<td>
										<input type="text" class="itext" name="goalSmblood" value="${goal.goalSmblood}"> mg/dL 이상  -
										<input type="text" class="itext" name="goalBmblood" value="${goal.goalBmblood}"> mg/dL 이하
									</td>
								</tr>
								<tr>
									<th>식후혈당</th>
									<td>
										<input type="text" class="itext" name="goalEsblood" value="${goal.goalEsblood}"> mg/dL 이상  -
										<input type="text" class="itext" name="goalEblood" value="${goal.goalEblood}"> mg/dL 미만
									</td>
								</tr>
								<tr>
									<th>취침전혈당</th>
									<td>
										<input type="text" class="itext" name="goalSsblood" value="${goal.goalSsblood}"> mg/dL 이상  -
										<input type="text" class="itext" name="goalSblood" value="${goal.goalSblood}"> mg/dL 미만
									</td>
								</tr>
								<tr>
									<th>당화혈색소</th>
									<td>
										<input type="text" class="itext" name="goalHba" value="${goal.goalHba}"> % 이하
									</td>
								</tr>
							</tbody>
							</table>
							
							<h2>혈압</h1>		
							<table class="register">	
							<tbody>
								<tr>	
									<th>60세미만 SBP</th>												
									<td>
										<input type="text" class="itext" name="goalSpre" value="${goal.goalSpre}"> mmHg 미만	
									</td>
								</tr>
								<tr>	
									<th>60세미만 DBP</th>												
									<td>
										<input type="text" class="itext" name="goalBpre" value="${goal.goalBpre}"> mmHg 미만	
									</td>
								</tr>
								<tr>	
									<th>60세이상 SBP</th>												
									<td>
										<input type="text" class="itext" name="goalSpreOld" value="${goal.goalSpreOld}"> mmHg 미만	
									</td>
								</tr>
								<tr>	
									<th>60세이상 DBP</th>												
									<td>
										<input type="text" class="itext" name="goalBpreOld" value="${goal.goalBpreOld}"> mmHg 미만	
									</td>
								</tr>
								<tr>
									<th>맥박</th>
									<td>
										<input type="text" class="itext" name="goalPul" value="${goal.goalPul}"> 회
									</td>
								</tr>
							</tbody>
							</table>
							
							<h2>콜레스테롤</h1>		
							<table class="register">	
							<tbody>
								<tr>
									<th>총콜레스테롤</th>
									<td>
										<input type="text" class="itext" name="goalCol" value="${goal.goalCol}"> ㎎/㎗ 미만	
									</td>
								</tr>
								<tr>
									<th>저밀도 콜레스테롤 (LDL)</th>
									<td>
										<input type="text" class="itext" name="goalLdl" value="${goal.goalLdl}"> ㎎/㎗ 미만
									</td>
								</tr>
								<tr>
									<th>고밀도 콜레스테롤 (HDL)</th>
									<td>
										<input type="text" class="itext" name="goalHdl" value="${goal.goalHdl}"> ㎎/㎗ 이상
									</td>
								</tr>
								<tr>
									<th>중성지방 (TG)</th>
									<td>
										<input type="text" class="itext" name="goalTg" value="${goal.goalTg}"> ㎎/㎗ 미만	
									</td>
								</tr>
							</tbody>
							</table>
							
							<h2>BMI</h1>		
							<table class="register">	
							<tbody>
								<tr>
									<th>BMI</th>
									<td>
										<input type="text" class="itext" name="goalSbmi" value="${goal.goalSbmi}"> -
										<input type="text" class="itext" name="goalBbmi" value="${goal.goalBbmi}"> 
									</td>
								</tr>
							</tbody>
							</table>
							
							<div style="width:400px; text-align:center; margin:20px;">
								<button type="submit" class="btn-blue" >수정</button>			
							</div>	
						</div>
					</form>	
				</div>
				
				<br><br>

			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>