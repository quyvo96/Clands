<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp"%>
<div class="col-md-10">

	<div class="row">
		<div class="col-md-12 panel-info">
			<div class="content-box-header panel-heading">
				<div class="panel-title ">Sửa thông tin</div>
			</div>
			<div class="content-box-large box-with-header">
				<div>
					<form action="${pageContext.request.contextPath}/admin/user/edit"
						method="post">
						<div class="row mb-10"></div>
						<c:choose>
							<c:when test="${not empty user}">

								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="name">ID</label> <input type="text"
												readonly="readonly" value="${user.id }" class="form-control"
												name="id" placeholder="Nhập danh mục">
										</div>
										<div class="form-group">
											<label for="name">User name</label> <input type="text"
												class="form-control" value="${user.username }"
												name="username">
											<form:errors path="user.username" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Full name</label> <input type="text"
												class="form-control" value="${user.fullname }"
												name="fullname">
											<form:errors path="user.fullname" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label>Vai tro</label> <input type="text"
												class="form-control" value="${user.role.name}"
												name="role.id" readonly="readonly">
										</div>
										<div class="form-group">
											<label for="name">New Password</label> <input type="password"
												class="form-control" name="password">
											<form:errors path="user.password" cssStyle="color:red"
												cssClass="error" />
										</div>
									</div>
								</div>
							</c:when>

						</c:choose>
						<hr>

						<div class="row">
							<div class="col-sm-12">
								<input type="submit" value="Sửa" class="btn btn-success" /> <input
									type="reset" value="Nhập lại" class="btn btn-default" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row col-size -->

</div>