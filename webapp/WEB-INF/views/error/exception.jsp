<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Exception 발생</h1>
	<!-- 시스템 오류 메세지를 이용자에게 보여줄 필요가 없으므로... -->
	<!-- <p> ${exceptionMessage }</p>  -->
	<p>
		앗 죄송합니다.<br>
		서비스에 일시적인 장애가 발생했습니다.<br>
		잠시 후 다시 시도해 주세요.
	</p>
</body>
</html>