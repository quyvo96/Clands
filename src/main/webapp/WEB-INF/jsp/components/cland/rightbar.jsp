<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="edu.vinaenter.service.LandsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.vinaenter.util.StringUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="clearfix sidebar">
	<div class="clearfix single_sidebar category_items">
	
		<h2>Danh mục bất động sản</h2>
		<c:choose>
			<c:when test="${not empty listCat && not empty listNum}">

				<ul>
					<c:forEach items="${listCat}" var="cat">
						<c:forEach items="${listNum}" var="num">
							<c:if test="${cat.cid == num.id}">
								<li class="cat-item"><a
									href="${pageContext.request.contextPath}/danh-muc/${StringUtil.makeSlug(cat.cname)}">
										${cat.cname}</a>( ${num.count} )</li>
							</c:if>
						</c:forEach>
					</c:forEach>
				</ul>

			</c:when>
		</c:choose>
	</div>

	<div class="clearfix single_sidebar">
		<div class="popular_post">
			<div class="sidebar_title">
				<h2>Xem nhiều</h2>
			</div>
			<ul>
				<c:choose>
					<c:when test="${not empty listLandsCount }">
						<c:forEach items="${listLandsCount}" var="lands">
							<li><a href="${pageContext.request.contextPath}/${StringUtil.makeSlug(lands.cat.cname)}/${StringUtil.makeSlug(lands.lname)}-${lands.lid}">${lands.lname }</a></li>
						</c:forEach>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</div>

</div>