<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function() {
	$("#join-form").submit(function() {
		if ($("#img-checkid").is(":visible") == false) {
			$( "#dialog p" ).text( "아이디 중복체크 필수" );
			$( "#dialog" ).dialog();
			// alert("아이디 중복체크 필수");
			return false;
		}
		if ($("#agree-prov").is(":checked") == false) {
			$( "#dialog p" ).text( "약관 동의 필수" );
			$( "#dialog" ).dialog();
			// alert("약관 동의 필수");
			return false;			
		}
	});
	
	$("#id").change(function() {
		$("#btn-checkid").show();
		$("#img-checkid").hide();
	});
	
	$("#btn-checkid").click(function() {
		var id = $("#id").val();
		if (id == "") {
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response) {
				if (response.result == "fail") {
					return;
				}
				
				if (response.data == "exist") {
					$( "#dialog p" ).text( "이미 존재하는 아이디" );
					$( "#dialog" ).dialog();
					// alert("이미 존재하는 아이디");
					$("#id").val("").focus();
					return;
				}
				
				$("#btn-checkid").hide();
				$("#img-checkid").show();
			},
			error:function(jqXHR, status, e) {
				console.error( status + ":" + e );
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/loginform">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/joinform">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }">로그아웃</a></li>
				<li><a href="">내블로그</a></li>
			</c:otherwise>
		</c:choose>
		</ul>
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="color:red">
			<form:errors path="name" />
			</p>
			<label class="block-label" for="id">아이디</label>
			<form:input path="id" />
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="color:red">
			<form:errors path="id" />
			</p>
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password"  />
			<p style="color:red">
			<form:errors path="password" />
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
	<div id="dialog" title="가입폼 체크" style="display:none">
  		<p></p>
</body>
</html>
