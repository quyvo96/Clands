
<%@page import="edu.vinaenter.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<div class="clearfix main_content floatleft">
				
					<div class="clearfix slider">
						<ul class="pgwSlider">
							<li><img src="${contextPath}/images/thumbs/megamind_07.jpg" alt="Paris, France" data-description="Eiffel Tower and Champ de Mars" data-large-src="${contextPath}/images/slides/megamind_07.jpg"/></li>
							<li><img src="${contextPath}/images/thumbs/wall-e.jpg" alt="Montréal, QC, Canada" data-large-src="${contextPath}/images/slides/wall-e.jpg" data-description="Eiffel Tower and Champ de Mars"/></li>
							<li>
								<img src="${contextPath}/images/thumbs/up-official-trailer-fake.jpg" alt="Shanghai, China" data-large-src="${contextPath}/images/slides/up-official-trailer-fake.jpg" data-description="Shanghai ,chaina">
							</li>


						</ul>
					</div>
					
					<div class="clearfix content">
						<c:if test="${not empty s}">
							<div class="content_title"><h2>${s}</h2></div>
						</c:if>
						<c:if test="${empty s}">
							<div class="content_title"><h2>Bài viết mới</h2></div>
						</c:if>
						
						<c:choose >
						<c:when test="${not empty listLands }">
						<c:forEach  items="${listLands}" var="lands">
						<div class="clearfix single_content">
							<div class="clearfix post_date floatleft">
								<div class="date">
									<h3>27</h3>
									<p>Tháng 3</p>
								</div>
							</div>
							<div class="clearfix post_detail">
								<h2><a href="">${lands.cat.cname}</a></h2>
								<div class="clearfix post-meta">
									<p><span><i class="fa fa-clock-o"></i> Địa chỉ: ${lands.address}</span> <span><i class="fa fa-folder"></i> Diện tích: ${lands.area}m2</span></p>
								</div>
								<div class="clearfix post_excerpt">
									<img style="width: 200px; height: 140px;" src="${pageContext.request.contextPath}/resources/files/${lands.picture}" />
									<p>${lands.lname}</p>
								</div>
			
								<a href="${pageContext.request.contextPath}/${StringUtil.makeSlug(lands.cat.cname)}/${StringUtil.makeSlug(lands.lname)}-${lands.lid}">Đọc thêm</a>
							</div>

						</div>
							</c:forEach>
							</c:when>
						</c:choose>
						<c:if test="${listLands.size() < 1}"><h3 style="color: red;">Không thấy kết quả!</h3></c:if>
					</div>
					<c:if test="${totalPage>1}">
					<!-- Pagination -->
					<div class="pagination">
					<nav >
					   <ul >
						<c:if test="${currentPage > 1}">
						
					      <li ><a href='${pageContext.request.contextPath}/trang-chu/${currentPage-1}<c:if test="${not empty s}">?s=${s}</c:if>' ><<</a></li>
					      </c:if>
					      <c:forEach begin="1" end="${totalPage}" var="num">
					      <li class='<c:if test="${num == currentPage}">active</c:if>'><a href='${pageContext.request.contextPath}/trang-chu/${num}<c:if test="${not empty s}">?s=${s}</c:if>'>${num}</a></li>
					      
					      </c:forEach>
					      <c:if test="${currentPage < totalPage}">
					      <li><a href='${pageContext.request.contextPath}/trang-chu/${currentPage+1}<c:if test="${not empty s}">?s=${s}</c:if>' >>></a></li>
					   </c:if>
					   </ul>
					</nav>
					</div>
					<!-- /.pagination -->
					</c:if>
				</div>