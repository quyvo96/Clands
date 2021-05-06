<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<div class="col-md-10">

  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">Quản lý danh mục</div>
		  			</div>
				</div>
				<hr>	
				<c:if test="${not empty msg }">
				<div class="alert alert-success" role="alert">
				  ${msg}
				</div>
				</c:if>
				<div class="row">
					<div class="col-md-8">
						<a href="${pageContext.request.contextPath}/admin/cat/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>

					</div>

				</div>

				<div class="row">
  				<div class="panel-body">
  					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
								<th>ID Danh Mục</th>
								<th>Tên Danh Mục</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<c:choose >
						<c:when test="${not empty listCat }">
						<c:forEach  items="${listCat}" var="cat">
							<tr class="odd gradeX">
								<td>${cat.cid}</td>
								<td>${cat.cname}</td>
								<td class="center text-center">
									<a href="${pageContext.request.contextPath}/admin/cat/edit?cid=${cat.cid}" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/cat/del?cid=${cat.cid}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
								</td>
							</c:forEach>
							</c:when>
						</c:choose>
						</tbody>
					</table>

					<!-- Pagination -->

					<!-- /.pagination -->
					
  				</div>
  				</div><!-- /.row -->
  			</div><!-- /.content-box-large -->



		  </div>