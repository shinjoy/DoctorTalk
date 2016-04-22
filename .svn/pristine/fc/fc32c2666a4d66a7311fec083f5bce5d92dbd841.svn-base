<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>
<html>
<head>
<link rel="stylesheet" href="../lib/kendostatic/kendo.common.min.css" />
<link rel="stylesheet" href="../lib/kendostatic/kendo.default.min.css" />
<script src="../lib/jquery/jquery-2.1.1.min.js"></script>
<script src="../lib/kendostatic/angular.min.js"></script>
<script src="../lib/kendostatic/kendo.all.min.js"></script>

<title>Recover</title>


<body style="background-color:#255057;">
	<div class="deom-section k-content wide">
	   <div id="chart1" style="height:260px;background-color:#255057;"></div>
	</div>
</body>
<script type="text/javascript">
function createChart() {
	
	var colArr = new Array();
	var ldlArr = new Array();
	var hdlArr = new Array();
	var tgArr = new Array();
	var periodArr = new Array();
	
	 <c:forEach items="${list}" var="item1">

		<c:choose>
			<c:when test="${item1.col==0}">
				colArr.push(undefined);
			</c:when>
			<c:otherwise>
				colArr.push("${item1.col}");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${item1.ldl==0}">
				ldlArr.push(undefined);
			</c:when>
			<c:otherwise>
				ldlArr.push("${item1.ldl}");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${item1.hdl==0}">
				hdlArr.push(undefined);
			</c:when>
			<c:otherwise>
				hdlArr.push("${item1.hdl}");
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${item1.tg==0}">
				tgArr.push(undefined);
			</c:when>
			<c:otherwise>
				tgArr.push("${item1.tg}");
			</c:otherwise>
		</c:choose>
		
		periodArr.push("&nbsp; ${item1.date} ");
	</c:forEach>

		$("#chart1").kendoChart(
						{
							title : {
								visible : false
							},
							legend : {
								position : "top"
							},
							chartArea : {
								background : ""
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
							series : [ {
								name : "총콜레스테롤목표:${goalcol}미만",
								data : colArr
							}, {
								name : "중성지방목표:${goaltg}미만",
								data : tgArr
							}, {
								name : " LDL목표:${goalldl}미만",
								data : ldlArr,
								color : "#32cd32"
							}, {
								name : "HDL목표:${goalhdl}이상",
								data : hdlArr,
								color : "#0000FF"
							} ],
							valueAxis : {
								labels : {
									format : "{0}",
									color : "#ffffff"
								},
								line : {
									visible : false
								},
								axisCrossingValue : -10
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
			                /*
							categoryAxis : {
								categories : [2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011], //periodArr,
								majorGridLines : {
									visible : false
								},
								labels : {
									rotation : "auto"
								}
							},
							*/
							tooltip : {
								visible : true,
								format : "{0}",
								template : "#= series.name # 측정값:#= value #"
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