<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp"%>
<div class="col-md-10">

	<div class="row">
		<div class="col-md-12 panel-info">
			<div class="content-box-header panel-heading">
				<div class="panel-title ">Thêm danh mục</div>
			</div>
			<div class="content-box-large box-with-header">
				<div>
					<c:choose>
						<c:when test="${not empty land}">
							<form action="${pageContext.request.contextPath}/admin/land/edit"
								method="post" enctype="multipart/form-data">
								<div class="row mb-10"></div>

								<div class="row">

									<div class="col-sm-6">
										<div class="form-group">
											<label for="name">ID </label> <input type="text"
												readonly="readonly" class="form-control" value="${land.lid}"
												name="lid">
										</div>
										<div class="form-group">
											<label for="name">Tên </label> <input type="text"
												class="form-control" value="${land.lname}" name="lname"
												placeholder="Nhập Tên">
											<form:errors path="land.lname" cssStyle="color:red"
												cssClass="error" />
										</div>

										<div class="form-group">
											<label>Danh mục</label> <select class="form-control"
												name="cat.cid">
												<c:choose>
													<c:when test="${not empty listCat }">
														<option value="${land.cat.cid}">${land.cat.cname}</option>
														<c:forEach items="${listCat}" var="cat">
															<option value="${cat.cid}">${cat.cname}</option>
														</c:forEach>
													</c:when>
												</c:choose>
											</select>
										</div>

										<div class="form-group">
											<label>Thêm hình ảnh</label>
											<c:if test="${not empty land.picture}">
												<div>
													<img style="width: 100px; height: 70px;"
														src="${pageContext.request.contextPath}/resources/files/${land.picture}" />
												</div>
												<div>${land.picture}</div>
											</c:if>
											<input type="file" class="btn btn-default"
												value="${land.picture}" name="file" id="exampleInputFile1">
											<p class="help-block">
												<em>Định dạng: jpg, png, jpeg,...</em>
											</p>
										</div>

										<div class="form-group">
											<label>Mô tả</label>
											<textarea class="form-control" name="description" rows="3">${land.description}</textarea>
											<form:errors path="land.description" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Diện tích </label> <input type="text"
												class="form-control" value="${land.area}" name="area"
												placeholder="Nhập Diện tích">
											<form:errors path="land.area" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Địa Chỉ</label> <input type="text"
												class="form-control" value="${land.address}" name="address"
												placeholder="Nhập Địa Chỉ">
											<form:errors path="land.address" cssStyle="color:red"
												cssClass="error" />
										</div>

									</div>


								</div>
								<hr>

								<div class="row">
									<div class="col-sm-12">
										<input type="submit" value="Sửa" class="btn btn-success" />
										<input type="reset" value="Nhập lại" class="btn btn-default" />
									</div>
								</div>
							</form>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row col-size -->

</div>