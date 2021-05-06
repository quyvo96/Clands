<%@page import="edu.vinaenter.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="header_area">
	<div class="wrapper header">
		<div class="clearfix header_top">
			<div class="clearfix logo floatleft">
				<a href="${pageContext.request.contextPath}/trang-chu"><h1>
						<span>C</span>Land
					</h1></a>
			</div>
			<div class="clearfix search floatright">
				<form method="get"
					action="${pageContext.request.contextPath}/trang-chu">
					<input type="text" placeholder="Search" name="s" /> <input
						type="submit" />
				</form>
			</div>
		</div>
		<div class="header_bottom">
			<nav>
				<ul id="nav">
					<li><a href="${pageContext.request.contextPath}/trang-chu">Trang
							chủ</a></li>
					<li id="dropdown"><a href="#">Bất động sản</a> <c:choose>
							<c:when test="${not empty listCat && not empty listNum}">

								<ul>
									<c:forEach items="${listCat}" var="cat">


										<li class="cat-item"><a
											href="${pageContext.request.contextPath}/danh-muc/${StringUtil.makeSlug(cat.cname)}">
												${cat.cname}</a></li>
									</c:forEach>
								</ul>

							</c:when>
						</c:choose></li>
					<li><a href="${pageContext.request.contextPath}/admin/index">Quản
							lý</a></li>
					<li><a href="${pageContext.request.contextPath}/lien-he">Liên
							hệ</a></li>
				</ul>
			</nav>
		</div>
	</div>
</section>