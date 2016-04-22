var user = {
	togleCombo : function() {
		if ($(".user-tool-combo").css("display") == "none") {
			$(".user-tool-combo").css("display","block");
			$(".user-tool-combo").animate({height: 68}, 200 );
			$(".user-block .head-tool .arrow").html("▲");
		} else {
			$(".user-tool-combo").css("display","none");
			$(".user-tool-combo").animate({height: 0}, 100 );
			$(".user-block .head-tool .arrow").html("▼");
		}
	},
	changePw : function() {
		document.location.href = "/admin/user_edit_password.go";
	},
	logout : function(from) {
		document.location.href = "/logout_do.go?from="+from;
	},
	getCount : function() {
		$.ajax({
		    type:"POST",
		    url:"/admin/count.go",
		    dataType:"json",
		    success:function(json) {
		        $("#user-count").html(json.userCount);
		        $("#company-count").html(json.companyCount);
		        $("#analysis-count").html(json.analysisCount);
		    },
		    error:function(xhr, status, error) {
		    },
		    complete:function(data) {
		    }
		});
	}
};