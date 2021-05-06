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
						<a href="${pageContext.request.contextPath}/admin/user/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
					</div>
					<form action="${pageContext.request.contextPath}/admin/user/index" method="get">
                	<div class="col-md-4">
                 	 <div class="input-group form">
                       <input type="text" class="form-control" name="s" placeholder="Search...">
                       <span class="input-group-btn">
                         <button class="btn btn-primary" type="submit">Search</button>
                       </span>
                  	 </div>
                  	</div>
                  	</form>
				</div>

				<div class="row">
  				<div class="panel-body">
  					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
								<th>ID</th>
								<th>User Name</th>
								<th>Full Name</th>
								<th>Vai trò</th>
								<th>Trạng thái</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<c:choose >
						<c:when test="${not empty listUser }">
						<c:forEach  items="${listUser}" var="user">
							<tr class="odd gradeX">
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.fullname}</td>
								<td>${user.role.name}</td>
								<c:choose>
								<c:when test="${user.id != 1}">
									<c:if test="${user.enabled == 1}">
										<td><input checked="checked" type="checkbox" id = "inputCheck${user.id}" value="${user.enabled}" onclick="checkEnabled(this.value,${user.id})">
										<span id="check${user.id}" style="color: blue;">Đã kích hoạt</span></td>
									</c:if>
									<c:if test="${user.enabled == 0}">
									<td><input type="checkbox" value="${user.enabled}" id = "inputCheck${user.id}" onclick="checkEnabled(this.value,${user.id})">
									<span id="check${user.id}" style="color:red;">Chưa kích hoạt</span></td>
									</c:if>
								</c:when>
								<c:otherwise><td>
										<span style="color: blue;">Đã kích hoạt(mặc định)</span></td>
										</c:otherwise>
								</c:choose>

								<td class="center text-center">
									<c:if test="${user.id ==userLogin.id}">
									<a href="${pageContext.request.contextPath}/admin/user/edit?id=${user.id}" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
									</c:if>
									<c:if test="${userLogin.id == 1 && user.id !=1}">
                                    <a href="${pageContext.request.contextPath}/admin/user/del?id=${user.id}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
                                    </c:if>
								</td>
								</tr>
							</c:forEach>
							</c:when>
						</c:choose>
						</tbody>
					</table>

					<!-- Pagination -->
					<nav class="text-center" aria-label="...">
					   <ul class="pagination">
						<c:if test="${currentPage > 1}">
					      <li class="disabled"><a href='${pageContext.request.contextPath}/admin/user/index/${currentPage-1}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>' aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					      </c:if>
					      <c:forEach begin="1" end="${totalPage}" var="num">
					      <li class='<c:if test="${num == currentPage}">active</c:if>'><a href='${pageContext.request.contextPath}/admin/user/index/${num}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>'>${num} <span class="sr-only">(current)</span></a></li>
					      
					      </c:forEach>
					      <c:if test="${currentPage < totalPage}">
					      <li><a href='${pageContext.request.contextPath}/admin/user/index/${currentPage+1}<c:if test="${not empty s || not empty i}">?i=${i}&s=${s}</c:if>' aria-label="Next"><span aria-hidden="true">»</span></a></li>
					   </c:if>
					   </ul>
					</nav>
					<!-- /.pagination -->
					
  				</div>
  				</div><!-- /.row -->
  			</div><!-- /.content-box-large -->
	<script >
							function checkEnabled(idCheck,idUser){
								
								var x = document.getElementById("check"+idUser);
								var y = document.getElementById("inputCheck"+idUser);
								$.ajax({
									url: '${pageContext.request.contextPath}/admin/user/check',
									type: 'POST',
									cache: false,
									data: {
										"idCheck" : idCheck,
										"idUser" : idUser,
									},
									success: function(data){
										$("#check"+idUser).html(data);
										if(idCheck == 1){
											x.style.color = "red";   
											y.value = '0'
										}else{
											x.style.color = "blue";   
											y.value = '1'
										}

									},
									error: function (){
										alert('Có lỗi xảy ra');
									}
								});
								return false;
								
								
							}
	</script >


		  </div>