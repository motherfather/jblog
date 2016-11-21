<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${userID }/logo/${list2[0].LOGO }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${list2 }" var="list2">
				<li><a href="${pageContext.request.contextPath }/${userId }/${list2.NO }/0">${list2.NAME }</a></li>
			</c:forEach>
			</ul>
		</div>