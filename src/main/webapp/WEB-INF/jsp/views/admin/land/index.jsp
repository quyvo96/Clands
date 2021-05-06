<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp"%> 
<div class="col-md-10">

  			<div class="content-box-large">
  				<div class="row">
	  				<div class="panel-heading">
	  					<div class="panel-title ">Quản lý tin</div>
		  			</div>
				</div>
				<hr>	
				<div class="row">
					<div class="col-md-8">
						<a href="${pageContext.request.contextPath}/admin/land/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
						
						</div>

					
                	<div class="col-md-4">
                	<form action="${pageContext.request.contextPath}/admin/land/index" method="get">

                	<div class="input-group form">
                	  <select  name="i" style="height: 35px;width: 100px">
                	  	<c:choose >
						<c:when test="${not empty listCat && not empty i && i != 0}">	
							<c:forEach  items="${listCat}" var="catS">
								<c:if test="${i == catS.cid}"><option value="${catS.cid}">${catS.cname}</option></c:if>
							</c:forEach>
							</c:when>
							<c:otherwise><option value="0">--Danh Muc--</option></c:otherwise>
						</c:choose>
						<c:choose >
						<c:when test="${not empty listCat }">
						<c:forEach  items="${listCat}" var="cat">
							<option value="${cat.cid}">${cat.cname}</option>
							</c:forEach>
							</c:when>
						</c:choose>
						
							<option value="0">--Tat ca--</option>
						</select>
                       <input type="text" style="height: 35px;width: 150px" placeholder="Search..." name="s">
                       <span class="input-group-btn">
                         <button class="btn btn-primary" type="submit">Search</button>
                       </span>
                  	 </div>
                	</form>

                  	</div>
				</div>

				<div class="row">
  				<div class="panel-body">
  					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
							
								<th>ID</th>
								<th>Tên</th>
								<th>Danh mục</th>
								<th>Lượt đọc</th>
								<th>Hình ảnh</th>
								<th>Chức năng</th>
							
							</tr>
						</thead>
						<tbody>
						<c:choose >
						<c:when test="${not empty listLands }">
						<c:forEach  items="${listLands}" var="land">
							<tr class="odd gradeX">
								<td>${land.lid}</td>
								<td>${land.lname}</td>
								<td>${land.cat.cname}</td>
								<td>${land.count_views}</td>
								<td class="center text-center" >
									<img style="width: 100px; height: 70px;" src="${pageContext.request.contextPath}/resources/files/${land.picture}" />
								</td>
								<td class="center text-center">
									<a href="${pageContext.request.contextPath}/admin/land/edit/${land.lid}" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/land/del?lid=${land.lid}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
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
					      <li class="disabled"><a href='${pageContext.request.contextPath}/admin/land/index/${currentPage-1}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>' aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					      </c:if>
					      <c:forEach begin="1" end="${totalPage}" var="num">
					      <li class='<c:if test="${num == currentPage}">active</c:if>'><a href='${pageContext.request.contextPath}/admin/land/index/${num}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>'>${num} <span class="sr-only">(current)</span></a></li>
					      
					      </c:forEach>
					      <c:if test="${currentPage < totalPage}">
					      <li><a href='${pageContext.request.contextPath}/admin/land/index/${currentPage+1}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>' aria-label="Next"><span aria-hidden="true">»</span></a></li>
					   </c:if>
					   </ul>
					</nav>
					<!-- /.pagination -->
					</c:if>
  				</div>
  				</div><!-- /.row -->
  			</div><!-- /.content-box-large -->



		  </div>