<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<div class="tbl-list">
			<table>
				<tbody class="rl">
				<c:choose>
					<c:when test="${listImg.size() > 0}">
						<c:forEach var="listImg" items="${listImg}">
							<tr>
								<td><img src="/files/company/thumb/${listImg.photo1}" style="height:50px;">&nbsp; &nbsp;
								<button class="btn tiny">파일삭제</button>  </td>									
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<!-- 						<td colspan="7" style="height:200px;">조회된 데이터가 없습니다.</td> -->
						
						<tr>
							<td>
								사진추가<input type="file"> 
							</td>	
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		
		</div>