<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		
		
		<div class="tbl-list" style="width:1000px;">
				<ul>
					<c:choose>	
						<c:when test="${list.size() > 0}">
							<c:forEach var="it" items="${list}">
								<li class="imglist">
									<div class="round-box">
										<table>	
											<tr>
												<a href="/user/assay/assay_view.go?userId=${it.userId}&seq=${it.seq}">
													<td style="color: #fff; padding-bottom:8px;">${it.userName}(${it.userAge}세)</td>	
												</a>										
											</tr>
											<tr>
												<td style="height:50px;">
													<c:choose>
														<c:when test="${it.photo1 =='' || it.photo1==null || it.photo2 =='' || it.photo2==null }">
															
														</c:when>
														<c:otherwise>
															<a href="/user/assay/assay_view.go?userId=${it.userId}&seq=${it.seq}">
																<img src="/files/imagefile/${it.photo3}"  style="margin:0; width:100%; ">
															</a>	
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td style="color: #fff; padding-top:8px; text-align:right;" >${it.regDate.substring(0,16)}</td>
											</tr>

										</table>									
									</div>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li style="height:200px; text-align:center; padding-top:50px;"> 조회된 데이터가 없습니다.</li>
						</c:otherwise>												
					</c:choose>
				<div style="clear:both;"></div>			
				</ul>
			${paging}
		</div>

		
						