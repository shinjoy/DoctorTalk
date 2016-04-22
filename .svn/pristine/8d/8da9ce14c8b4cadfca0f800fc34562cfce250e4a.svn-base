<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
var side = {
    showSubMenu:function(sub) {
    	if (sub==1) {
    		$("#menu-sub-1").css("display","block");
    		$("#menu-sub-1").animate({height: 100}, 200 );
    		if($("#menu1").hasClass("active") == true  || $("#menu-sub-1").animate({height: 100}, 200 ) != true){  
    			$("#menu-sub-1").stop().animate({height: 100}, 200 );    			
    		}
  			if($("#menu2").hasClass("active") == false){
    	   		$("#menu-sub-2").animate({height: 0}, 100, function() { $("#menu-sub-2").css("display","none"); } );
    		}
    		if($("#menu3").hasClass("active") ==false){
    	   		$("#menu-sub-3").animate({height: 0}, 100, function() { $("#menu-sub-3").css("display","none"); } );
    		}
    	} else if (sub==2) {
    		$("#menu-sub-2").css("display","block");
    		$("#menu-sub-2").animate({height: 40}, 200);
    		if($("#menu2").hasClass("active") == true || $("#menu-sub-2").animate({height: 40}, 200 ) != true){  
    			$("#menu-sub-2").stop().animate({height: 40}, 200 );
    		}
    		if ($("#menu1").hasClass("active") == false) {
	    		$("#menu-sub-1").animate({height: 0}, 100, function() { $("#menu-sub-1").css("display","none"); } );
    		}
    		if($("#menu3").hasClass("active") == false){
    	   		$("#menu-sub-3").animate({height: 0}, 100, function() { $("#menu-sub-3").css("display","none"); } );
    		}
    	} else if (sub==3) {
    		$("#menu-sub-3").css("display","block");
    		$("#menu-sub-3").animate({height: 100}, 200 );
    		if ($("#menu1").hasClass("active") == false) {
	    		$("#menu-sub-1").animate({height: 0}, 100, function() { $("#menu-sub-1").css("display","none"); } );
    		}
    		if($("#menu2").hasClass("active") == false){
    	   		$("#menu-sub-2").animate({height: 0}, 100, function() { $("#menu-sub-2").css("display","none"); } );
    		}
    	}
    },
};
</script>


<style>
.menu1 { 
	cursor: pointer; 
	padding: 15px 5px 15px 35px; 
	color: #fff; 
	text-transform: uppercase; 
	font-size: 14px;  
	font-weight:bold; 
	border-top: 1px solid #565c5f; 
	background-image: url('/images/star.png');
	background-repeat: no-repeat;
	background-position: 10px;
}
.menu1.active {
	background-image: url('/images/menu_over.png');
}

.menu2 { 
	cursor: pointer; 
	padding: 15px 5px 15px 35px; 
	color: #fff; 
	text-transform: uppercase; 
	font-size: 14px;  
	font-weight:bold; 
	border-top: 1px solid #565c5f; 
	background-image: url('/images/img_seeting.png');
	background-repeat: no-repeat;
	background-position: 10px;
}
.menu2.active {
	background-image: url('/images/img_seeting_dw.png');
}


.menu3 { 
	cursor: pointer; 
	padding: 15px 5px 15px 35px; 
	color: #fff; 
	text-transform: uppercase; 
	font-size: 14px;  
	font-weight:bold; 
	border-top: 1px solid #565c5f; 
	background-image: url('/images/img_list.png');
	background-repeat: no-repeat;
	background-position: 10px;
}
.menu3.active {
	background-image: url('/images/img_list_dw.png');
}


.logout {

	font-size: 10px; 
	line-height: 1.5;
	display: inline-block;
	position: relative;
	white-space: nowrap;
	overflow: visible;
	text-decoration: none !important;
	margin: 0;
	padding: 3px 7px 3px 5px;
	margin-top : -23px;
	margin-left: 70px;
	border: 1px solid #fff;
	border-radius: 13px;
	background: #535b60;
	color: #fff;
}
.pw {

	font-size: 10px; 
	line-height: 1.5;
	display: inline-block;
	position: relative;
	white-space: nowrap;
	overflow: visible;
	text-decoration: none !important;
	margin: 0;
	padding: 3px 7px 3px 5px;
	margin-top :7px;
	margin-left: -10px;
	border: 1px solid #fff;
	border-radius: 13px;
	background: #535b60;
	color: #fff;
}



.tab1 { 
	padding:5px; 
	cursor:pointer;  
	padding: 5px 5px 5px 40px; 
	background-color: #353c42; 
	font: 12px/1.6 NanumBarunGothic,'맑은 고딕' ; 
	border-bottom: 1px solid #353c42; 
	color: #fff;
}
.logo-img { padding: 10px 10px 20px 35px;  }

</style>


		<!-- PC 버전 -->
		<div class="pc-view lnb">
		
			<div class="logo-img">
				<img src="/images/logo_1_1.png" style="width:130px;">
			</div>
		
		
			<div class="login-sec">
				<p style="color:#fff;">${USER_NAME}님 반갑습니다.</p>
				<p><button class="pw " onclick="document.location.href='/admin/user_edit_password.go';">비밀번호변경</button></p>
				<p><button class="logout" onclick="document.location.href='/logout_do.go';">로그아웃</button></p>
			</div>	
			
			
			<nav>
				<ul class="nav">
					<li>
					<li class="menu1" id="menu1" onclick="side.showSubMenu(1)">오토메이션</li>
						<li id="menu-sub-1" style="display:none;">
						<ul class="sm">
							<li class="tab1 " id="tab1"  ><a href="/admin/device/device.go" > - 단말기 관리</a></li>
							<li class="tab1" id="tab2" ><a id="menu12" href="/admin/keyword/keyword.go" > - 키워드 관리</a></li>
							<li class="tab1" id="tab3"  ><a id="menu13" href="/admin/device/device_manage.go"> - 오토메이션 현황</a></li>
						</ul>
					</li>
					<li>
						
						<li class="menu2" id="menu2" onclick="side.showSubMenu(2)">환경 설정</li>					
						<li id="menu-sub-2" style="display:none;">
						<ul class="sm">
							<li class="tab1" id="tab4" ><a id="menu31" href="/admin/environment/environment.go" > - 환경 설정</a></li>
						</ul>
					</li>
					<li>
						<li class="menu3" id="menu3" onclick="side.showSubMenu(3)">오토메이션 로그</li>
						<li id="menu-sub-3" style="display:none;">
						<ul class="sm">
							<li class="tab1" id="tab5" ><a id="menu41" href="/admin/total/total.go"> - 키워드별 실행로그</a></li>
							<li class="tab1" id="tab6" ><a id="menu42" href="/admin/product/product.go"> - 전체 실행 내역</a></li>
						</ul>
					</li>
				
				</ul>
			</nav>
			
		</div><!-- End of class="lnb" //-->
				
		<!-- 모바일 버전 -->
		<div id="drop-nav1" class="mobile-view drop-nav">
			<div class="wrap">
				<ul>
					<li><a id="menu11" href="/admin/veri_manager.go">인증코드관리</a></li>
					<li><a id="menu12" href="/admin/veri_history.go">인증내역</a></li>
				</ul>
			</div>
		</div>
		