<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${list2[0].TITLE }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var render = function( vo, mode ) {
	var htmls = 
		"<tr id='cg-" + vo.no "'>" +
		"<td>" + vo.CNO + "</td>" +
		"<td>" + vo.NAME + "</td>" +
		"<td>" + vo.PNO + "</td>" +
		"<td>" + vo.DESCRIPTION + "</td>" +
		"<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>" +
		"</tr>";
		
	if( mode == true ) { // prepend
		$( "#list" ).prepend( htmls );
	} else { // append	
		$( "#list" ).append( htmls );
	}
}

var fetchList = function() {
	if( isEnd == true ) {
		return;
	}
	$.ajax({
		url: "${pageContext.request.contextPath }/api/list",
		type: "post",
		dataType: "json",
		data:"blogNo=" + blogNo,
		success: function( response ) { // response.result = "success" or "fail"
										// response.data = [{}, {}, {}...]
			if( response.result != "success" ) {
				console.error( response.message );
				isEnd = true;
				return;
			}
			
			// rendering
			$( response.data ).each( function(index, vo){
				render( vo, false );
			});
		},
		error: function( jqXHR, status, e ) {
			console.error( status + ":" + e );
		}
	});
}

$(function() {
	$("#category").submit(function(event) {
		event.preventDefault();
		var name = $("#name").val();
		if (name == "") {
			$( "#dialog p" ).text( "카테고리명은 필수 입력 항목입니다." );
			$( "#dialog" ).dialog();
			return false;
		}
		var desc = $("#desc").val();
		if (desc == "") {
			$( "#dialog p" ).text( "설명은 필수 입력 항목입니다." );
			$( "#dialog" ).dialog();
			return false;
		}
		var blogNo = $.getUrlVar('list2[0].UNO');
		
		$.ajax({
			url:"${pageContext.request.contextPath }/api/add",
			type:"get",
			dataType:"json",
			data:"name=" + name + "&description=" + desc + "&blogNo=" + blogNo,
			success:function(response) {
				if (response.result != "success") {
					console.error(response.message);
					return;
				}
				render( response.data, true );
				$( "#category" )[0].reset();
			},
			error: function( jqXHR, status, e ) {
				console.error( status + ":" + e );
			}
		});
	});
	fetchList();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/headerAdmin.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/adminMenu.jsp">
					<c:param name="menu" value="category"/>
				</c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<ul id="list"></ul>  
				</table>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
      			<form id="category" action="" method="post">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="desc" type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</form>
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
	<div id="dialog" title="카테고리폼 체크" style="display:none">
  		<p></p>
	</div>
</body>
</html>