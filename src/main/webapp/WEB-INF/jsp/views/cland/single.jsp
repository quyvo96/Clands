<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.vinaenter.util.StringUtil"%>
<div class="clearfix main_content floatleft">


	<div class="clearfix content">
		<c:choose>
			<c:when test="${not empty land}">
				<h1>${land.lname}</h1>
				<div class="clearfix post-meta">
					<p>
						<span><i class="fa fa-clock-o"></i> Địa chỉ:
							${land.address}</span> <span><i class="fa fa-folder"></i> Diện
							tích: ${land.area}m2</span>
					</p>
				</div>
				<div class="clearfix post_excerpt">
					<img style="width: 500px; height: 300px;"
						src="${pageContext.request.contextPath}/resources/files/${land.picture}" />
				</div>
				<div class="vnecontent">
					<p>${land.description}</p>
				</div>

				<c:if test="${back == 0}">
					<a class="btn"
						href="${pageContext.request.contextPath}/back/${land.lid}">Bài
						trước</a>
				</c:if>
				<c:if test="${next == 0}">
					<a class="btn"
						href="${pageContext.request.contextPath}/next/${land.lid}">Bài
						kế</a>
				</c:if>
			</c:when>
		</c:choose>
	</div>

	<div class="more_themes">
		<h2>
			Bất động sản liên quan <i class="fa fa-thumbs-o-up"></i>
		</h2>
		<div class="more_themes_container">
			<c:choose>
				<c:when test="${not empty listLandsLQ }">
					<c:forEach items="${listLandsLQ}" var="lands">
						<div class="single_more_themes floatleft">
							<img style="width: 220px; height: 150px;"
								src="${pageContext.request.contextPath}/resources/files/${lands.picture}" />
							<a href="${pageContext.request.contextPath}/${StringUtil.makeSlug(lands.cat.cname)}/${StringUtil.makeSlug(lands.lname)}-${lands.lid}"><h2>${lands.lname}</h2></a>
						</div>
					</c:forEach>
				</c:when>
			</c:choose>

		</div>
	</div>

</div>