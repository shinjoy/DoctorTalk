<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<html>
<head>
<link rel="stylesheet" href="../lib/kendostatic/kendo.common.min.css" />
<link rel="stylesheet" href="../lib/kendostatic/kendo.default.min.css" />
<script src="../lib/jquery/jquery-2.1.1.min.js"></script>
<script src="../lib/kendostatic/kendo.all.min.js"></script>

<title>Recover</title>


<script type="text/javascript">
function createChart() {
	
	var hbaArr = new Array();
	
	var periodArr = new Array();
	
	 <c:forEach items="${list}" var="item1">
		<c:choose>
			<c:when test="${item1.hbaNumone==0 }">
				hbaArr.push(undefined);
			</c:when>
			<c:otherwise>
				hbaArr.push("${item1.hbaNumone}");
			</c:otherwise>
		</c:choose>
		
		 periodArr.push("&nbsp; ${item1.date} ");
	 </c:forEach>
	
	
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
	        name: "당화혈색소 목표:${goalhba}% 이하",
	        data: hbaArr
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