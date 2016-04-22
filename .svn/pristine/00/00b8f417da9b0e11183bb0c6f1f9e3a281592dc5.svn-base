<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(2,1);
	});
	
	
	function submitForm(frm) {
		
		
		var frag = frm.frag.value;
		for(var i=0; i<frag.length; i++) // 일반적인 배열의 루프이지요?
		{
		    if(frag[i].checked == true) { // 하나만 체크되면 OK이므로
		    	frag = 1;
		    }
		}
		if (frag == 0) {
		    alert("플라그 수치를 선택해주세요.");
		    return false;
		} 

		if (frm.explain.value == "") {
			alert("추가 설명을 입력해 주세요.");
			return false;
		}

		frm.action = "/admin/assay/assay_edit_do.go";
		frm.submit();
		return false;
	}
	
	function deleteImg(seq,photo3) {
		if(confirm("이미지를 삭제하시겠습니까?")) {
			var param = {
					seq	:	seq,
					photo3 : photo3
			};
			
			$.ajax({
				type:"POST",
				url:"/admin/assay/assay_view_delete_do.go",
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
	
	//이미지 다운로드
	function imgDownload(img) {
		//var frm = listForm;
		document.location.href = "/admin/download.go?fileName="+img;
	}	

</script>

<style>
.device { font-weight: bold; font-size:25px; color:#464242; padding-bottom:30px;}	
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ 홈 > 분석관리 > 분석 결과
			</header>
		
			<div class="contents-block">
			
				<h1>분석 결과</h1>
				
				<div class="contents-main">

					<div class="contents-box">
				
						<form method="post" name="printcenterForm" enctype="multipart/form-data" onsubmit="return submitForm(this); return false;">
						<input type="hidden" name="seq" value="${analysis.seq}">
							<table class="info">
							<colgroup>
								<col style="560">
								<col style="300">
							</colgroup>
							<tr>
								<td>
									<dl class="title-box">
										<dt>분석요청 이미지</dt>
										<dd>
											<table style="width:540px;">
											<tr>	
												<td style="padding-right:5px;">
													<img src="/files/imagefile/${analysis.photo1}" style="width:100%;">
													<br>
													<button type="button" class="btn tiny" onclick="imgDownload('/files/imagefile/${analysis.photo1}');">다운로드</button>
												</td>
												<td style="">
													<img src="/files/imagefile/${analysis.photo2}"  style="width:100%;">
													<br>
													<button type="button" class="btn tiny" onclick="imgDownload('/files/imagefile/${analysis.photo2}');">다운로드</button>
												</td>
											</tr>
											</table>
										</dd>
									</dl>
									
								</td>
								<td>
								
									<dl class="title-box">
										<dt>분석 요청 정보</dt>
										<dd>
											<table class="info">
											<tr>
												<th>요청상태</th>
												<td class="data">${analysis.frag==0 ? '분석대기' : '분석완료'}</td>
											</tr>
											<tr>
												<th>의뢰인</th>
												<td class="data">${user.userName}</td>
											</tr>
											<tr>
												<th>의뢰일</th>	
												<td class="data">${analysis.anaDate.substring(0,16)}</td>
											</tr>
											<tr>
												<th>분석일</th>
												<td class="data">${analysis.regDate.substring(0,16)}</td>
											</tr>
											<tr>	
												<th>연령</th>
												<td class="data">${user.userAge}세</td>
											</tr>
											<tr>
												<th>성별</th>
												<td class="data">${user.genderText}</td>
											</tr>
											</table>
										</dd>
									</dl>
									
								</td>
							</tr>
							<tr>
								<td>
								
									<dl class="title-box">
										<dt>분석결과 이미지</dt>
										<dd>

											<table>
											<tr>
												<td>
													<c:choose>
														<c:when test="${analysis.photo3 == '' || analysis.photo3 == null}">
															<input type="file" name="photo3">(200*200픽셀 이하의 이미지만 업로드 시켜주세요.)
														</c:when>
														<c:otherwise>
															<img src="/files/imagefile/${analysis.photo3}" style="width:540px;">
															<br>
															<button type="button" class="btn tiny" onclick="deleteImg('${analysis.seq}','${analysis.photo3}');">삭제</button>	
														</c:otherwise>
													</c:choose>
												</td>
											</tr>							
											</table>
										</dd>
									</dl>

								</td>
								<td>
								
									<dl class="title-box">
										<dt>플라그 수치</dt>
										<dd>
											<div class="radio">
												<label><input type="radio" name="frag" value="1"  ${analysis.frag == 1 ? 'checked=\"checked\"' : ''}>1</label>
												<label><input type="radio" name="frag" value="2"  ${analysis.frag == 2 ? 'checked=\"checked\"' : ''}>2</label>
												<label><input type="radio" name="frag" value="3"  ${analysis.frag == 3 ? 'checked=\"checked\"' : ''}>3</label>
												<label><input type="radio" name="frag" value="4"  ${analysis.frag == 4 ? 'checked=\"checked\"' : ''}>4</label>	
												<label><input type="radio" name="frag" value="5"  ${analysis.frag == 5 ? 'checked=\"checked\"' : ''}>5</label>	
											</div>
										</dd>
									</dl>

									<dl class="title-box">
										<dt>추가 설명</dt>
										<dd>
											<textarea name="explain" style="width:100%; height:200px;">${analysis.comment}</textarea>
										</dd>
									</dl>

								</td>								
							</tr>
							</table>
						
						</form>
					</div>

				</div>
				
				<div class="btn-tools">
					<button type="button" class="btn" onclick="submitForm(printcenterForm);">분석 저장</button>
					<button type="button" class="btn" onclick="document.location.href='/admin/assay/assay.go';">목록</button>
				</div>
					
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>