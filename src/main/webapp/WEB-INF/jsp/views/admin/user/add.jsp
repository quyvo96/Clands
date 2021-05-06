<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp"%>
<div class="col-md-10">

	  			<div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Thêm người dùng</div>
			  			</div>
			  			<div class="content-box-large box-with-header">
				  			<div>
				  				<form action="${pageContext.request.contextPath}/admin/user/add" method="post">
								<div class="row mb-10"></div>
								
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<div><span id="checkUserName" ></span></div>
											<label for="name">User name</label>
											<input type="text" class="form-control" value="" name="username" placeholder="Nhập tên" onchange="checkNameUser(this.value)">
											<form:errors path="user.username" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Full name</label>
											<input type="text" class="form-control" name="fullname" placeholder="Nhập tên đầy đủ">
											<form:errors path="user.fullname" cssStyle="color:red"
												cssClass="error" />
										</div>
								<div class="form-group">
									<label>Vai tro</label> 
									<select class="form-control"
										name="role.id">
										<c:choose>
											<c:when test="${not empty listRole }">
												<c:forEach items="${listRole}" var="role">
													<option value="${role.id}">${role.name}</option>
												</c:forEach>
											</c:when>
										</c:choose>
									</select>
								</div>
								<div class="form-group">
											<label for="name">Password</label>
											<input type="password" class="form-control" name="password" >
											<form:errors path="user.password" cssStyle="color:red"
												cssClass="error" />
										</div>
								</div>
								</div>
								<hr>

								<div class="row">
									<div class="col-sm-12">
										<input type="submit" value="Thêm" class="btn btn-success" />
										<input type="reset" value="Nhập lại" class="btn btn-default" />
									</div>
								</div>
								
						</form>
					<c:if test="${not empty msg }">
						<div class="alert alert-danger" role="alert">${msg}</div>
					</c:if>
				</div>
						</div>
			  		</div>
	  			</div><!-- /.row col-size -->
	  		
		  </div>
		  	<script >
							function checkNameUser(name){
								
								var x = document.getElementById("checkUserName");
								var name123 = "";
								$.ajax({
									url: '${pageContext.request.contextPath}/admin/user/checkName',
									type: 'POST',
									cache: false,
									data: {
										"nameCheck" : name,
									},
									success: function(data){
										if(data == 1){
											$("#checkUserName").html("Tên đăng nhập chưa tồn tại");
											x.style.color = "blue"; 
										}else{
											$("#checkUserName").html("Tên đăng nhập đã tồn tại");
											x.style.color = "red"; 
										}								
									},
									error: function (){
										alert('Có lỗi xảy ra');
									}
								});
								return false;
							}
	</script >