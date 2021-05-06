<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<div class="col-md-10">

  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">Quản lý liên hệ</div>
		  			</div>
				</div>
				<hr>	
				<c:if test="${not empty msg }">
				<div class="alert alert-success" role="alert">
				  ${msg}
				</div>
				</c:if>
				<div class="row">
				</div>

				<div class="row">
  				<div class="panel-body">
  					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Full Name</th>
								<th>Email</th>
								<th>subject</th>
								<th>content</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<c:choose >
						<c:when test="${not empty listContact }">
						<c:forEach  items="${listContact}" var="contact">
							<tr class="odd gradeX">
								<td>${contact.cid}</td>
								<td>${contact.fullname}</td>
								<td>${contact.email}</td>
								<td>${contact.subject}</td>
								<td>${contact.content}</td>
								<td class="center text-center">
                                    <a href="${pageContext.request.contextPath}/admin/contact/del?id=${contact.cid}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
								</td>
							</c:forEach>
							</c:when>
						</c:choose>
						</tbody>
					</table>

					<c:if test="${totalPage>1}">
					<!-- Pagination -->
					<nav class="text-center" aria-label="...">
					   <ul class="pagination">
						<c:if test="${currentPage > 1}">
					      <li class="disabled"><a href="${pageContext.request.contextPath}/admin/contact/index/${currentPage-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					      </c:if>
					      <c:forEach begin="1" end="${totalPage}" var="num">
					      <li class='<c:if test="${num == currentPage}">active</c:if>'><a href="${pageContext.request.contextPath}/admin/contact/index/${num}">${num} <span class="sr-only">(current)</span></a></li>
					      
					      </c:forEach>
					      <c:if test="${currentPage < totalPage}">
					      <li><a href="${pageContext.request.contextPath}/admin/contact/index/${currentPage+1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
					   </c:if>
					   </ul>
					</nav>
					<!-- /.pagination -->
					</c:if>
					
  				</div>
  				</div><!-- /.row -->
  			</div><!-- /.content-box-large -->



		  </div>