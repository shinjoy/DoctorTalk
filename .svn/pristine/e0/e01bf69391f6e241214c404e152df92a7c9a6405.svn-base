<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" href="../lib/kendostatic/kendo.common.min.css" />
<link rel="stylesheet" href="../lib/kendostatic/kendo.default.min.css" />
<script src="../lib/jquery/jquery-1.10.2.min.js"></script>
<script src="../lib/kendostatic/kendo.all.min.js"></script>

<title>Recover</title>
<script>

function createChart() {
	var emptyArr = new Array();
   	var AfterEatArr = new Array();
   	var SleepArr = new Array();
   	var periodArr = new Array();
   	
   	<c:forEach items="${listEmpty}" var="item1">
		<c:choose>
			<c:when test="${item1.bloodNum==0}">
				emptyArr.push(undefined);
			</c:when>
			<c:otherwise>
				emptyArr.push("${item1.bloodNum}");
			</c:otherwise>
		</c:choose>
   			periodArr.push("&nbsp; ${item1.date} ");
    </c:forEach>
   	 <c:forEach items="${listAfterEat}" var="item1">
		 <c:choose>
			<c:when test="${item1.bloodNum==0}">
				AfterEatArr.push(undefined);
			</c:when>
			<c:otherwise>
				AfterEatArr.push("${item1.bloodNum}");
			</c:otherwise>
		</c:choose>
   	</c:forEach>
   	 <c:forEach items="${listBeforeSleep}" var="item1">
		<c:choose>
			<c:when test="${item1.bloodNum==0}">
				SleepArr.push(undefined);
			</c:when>
			<c:otherwise>
				SleepArr.push("${item1.bloodNum}");
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	//var sleep = ["120","105","134","123",undefined,"125","132"]; 
	//alert(sleep);
	
	
	$("#chart1").kendoChart({
		 title: {
			visible : false
	    },
	    legend: {
	        position: "top"
	    },
	    chartArea: {
	        background: ""
	    },
	    seriesDefaults: {
	        type: "line",
	        style: "smooth",
	        labels: {
                    visible: true,
                    format: "{0}",
                    color: "##ffffff"
            }
	    },
	    series: [{
	        name: "식후혈당목표:${goalEat}미만",
	        data: AfterEatArr
	    },{
	        name: "취침전혈당목표:${goalSleep}미만",
	        data: SleepArr,
			color: "#FF0000"
	    },{
	        name: "공복혈당목표:${goalEmpty}",
	        data: emptyArr,
	        color: "#32cd32"
	    }],
	    valueAxis: {
	        labels: {
	            format: "{0}",
				color : "#ffffff"
	        },
	        line: {
	            visible: false
	        },
	        axisCrossingValue: -10
	    },
	    categoryAxis: {
	        categories: periodArr,
	        majorGridLines: {
	            visible: false
	        },
	        labels: {
	            rotation: "auto",
				color : "#ffffff"
	        }
	    },
	    tooltip: {
	        visible: true,
	        format: "{0}",
	        template: "#= series.name # 측정값:#= value #"
	    }
	});   
}

              
                
  // $(document).ready(createChart);
   $(document).bind("kendo:skinChange", createChart);
   
   var loadTimer;
   loadTimer = setTimeout(showChart, 2000);
   
   function showChart() {
	   createChart();
	   $(".loader").css("display","none");
	   clearTimeout(loadTimer);
   }
   
 </script>
<style>
.loader { position:absolute; top:50%; left:50%; width:500px; height:240px; overflow:hidden; margin-top:-120px; margin-left:-250px; }
</style>
</head>

<body style="background-color:#255057;">
	
   <div id="chart1" style="height:260px;background-color:#255057;"></div>
   
   <div class="loader"><img src="/images/loading_2.gif"></div>
   
</body>
</html>