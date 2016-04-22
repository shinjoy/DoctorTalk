<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		$("#user-menu3 A").css("color","#000");	
	});
</script>

<style>
.login-title { margin-top:70px; margin-left:30px; font-weight:bold; font-size:20px;}	
.gray-top { width:1000px; padding-bottom:20px; border-bottom:1px solid #ddd;}
.gray-bottom { border-bottom:1px solid #ddd; width:950px; margin-left:30px; }
	
.btn-analy { margin-left:450px;}
	
table.request th {
   vertical-align: top;
   text-align: right;
   padding:7px;
   border-bottom: 1px solid #ddd;
   font-weight: bold;
}

table.request td.subject {
    color: #000;
}

table.request td {
	padding:7px;
	border-bottom: 1px solid #ddd;
    vertical-align: top;
    width: 133px;
}	

dl.user-report {
    margin: 0 20px 20px 0;
    border: 1px solid #ddd;
}

dl.user-report dt {
    padding:10px;
    background-color: #eee;
    font-weight: bold;
    font-size: 14px;
}
dl.user-report dd{
	margin: 10px;
}	
</style>
</head>
<body>
	
	<div class="login-wrap">
	
		<%@ include file="/WEB-INF/views/home_header.jsp"%> 	
		
		
		<div class="login-content">
	
			<div class="clear" ></div>
		
			<div class="login-content" >
				<div class="inner">
					<div class="login-title">분석내역>분석결과</div>
					
					<div class="gray-top" ></div>
					
				<div style="margin-top:70px; margin-left:70px;">
				<table>
					<tr>
					<td>
						<form method="post" name="printcenterForm" onsubmit="return submitForm(this); return false;">
								<input type="hidden" name="seq" value="${analysis.seq}">
							<table>
								<tr>
									<td>
										<table style="width:540px;">
											<tr>
												<td>
													<dl class="user-report">
														<dt>분석요청 이미지</dt>
														<dd>
															<table style="width:540px;">
															<tr>	
																<td style="padding-right:5px;">
																	<img src="/files/imagefile/${analysis.photo1}" style="width:100%;">	
																</td>
																<td style="">
																	<img src="/files/imagefile/${analysis.photo2}"  style="width:100%;">
																</td>
															</tr>
															</table>
														</dd>
													</dl>
													
												</td>
											</tr>
											<tr>
												<td>
													<dl class="user-report">
														<dt>분석결과 이미지</dt>
														<dd>
															<table style="width:540px;">
															<tr>	
																<td colspan="2">
																	<img src="/files/imagefile/${analysis.photo3}" style="max-width:100%;">
																</td>
															</tr>
															</table>
														</dd>
													</dl>
													
												</td>
											</tr>						
										</table>	
									</td>
									
																						
									<td style="vertical-align:top;">
									
										<dl class="user-report" style="margin-bottom:45px;" >
											<dt>분석 요청 정보</dt>
												<table class="request" >
													<tr>
														<th>요청상태</th>
														<td class="subject" >${analysis.frag==0 ? '분석대기' : '분석완료'}</td>
													</tr>
													<tr>
														<th>의뢰인</th>
														<td class="subject">${user.userName}</td>
													</tr>
													<tr>
														<th>의뢰일</th>	
														<td class="subject">${analysis.anaDate.substring(0,16)}</td>
													</tr>
													<tr>
														<th>분석일</th>
														<td class="subject">${analysis.regDate.substring(0,16)}</td>
													</tr>
													<tr>	
														<th>연령</th>
														<td class="subject">${user.userAge}세</td>
													</tr>
													<tr>
														<th>성별</th>
														<td>${user.genderText}</td>
													</tr>
												</table>
										</dl>
										
										<dl class="user-report" >
											<dt>코멘트</dt>
												<dd>
									
												<table>
													<tr>
														<td>
														<div style="padding-top:10px; padding-right:10px; padding-bottom:10px;">
															<p class="analy-call"><span>플라그 수치</span></p>
														</div>
														</td>
														<td>
														<div style="padding-top:15px; padding-right:20px; padding-bottom:10px;">
															${analysis.frag}
														</div>
														</td>
													</tr>
													<tr>
														<td><p class="analy-call"><span>추가 설명</span></p></td>
														<td>${analysis.comment}</td>
													</tr>										
												</table>
										</dd>
									</dl>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				</table>

				</div>
					
				</div>
			</div><!-- End of class="contents" //-->
			
			<div class="btn-analy">
				<button type="button" class="btn" onclick="document.location.href='/user/assay/assay.go';">목록</button>
			</div>

		</div>	

		<div style="padding-bottom:30px;"></div>
		
		<div class="clear"></div>
		<%@ include file="/WEB-INF/views/home_footer.jsp"  %>  	 		
	
	</div>
</body>
</html>