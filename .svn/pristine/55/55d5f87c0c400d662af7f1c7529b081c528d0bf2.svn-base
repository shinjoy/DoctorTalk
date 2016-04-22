<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<header id="header">
		<div class="logo"><imgalt="" class="logo-img"></div>
		<ul class="mobile-view top-menu">
			<c:choose>
				<c:when test="${USER_TYPE==1}">
					<li class="setting" class="nav_active" id="drop-btn3" onclick="dropNav(3);">환경설정</li>
					<li class="design" class="nav_active" id="drop-btn2" onclick="dropNav(2);">디자인</li>
					<li class="manage" class="nav_active" id="drop-btn1" onclick="dropNav(1);">회원관리</li>
				</c:when>
				<c:otherwise>
					<li class="manage" class="nav_active" id="drop-btn1" onclick="dropNav(1);">메뉴</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</header>
	
	<div class="mobile-view" style="height:45px;"></div>
