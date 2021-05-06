<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<div class="col-md-10">

	  			<div class="row">
	  				<div class="col-md-12 panel-info">
			  			<div class="content-box-header panel-heading">
		  					<div class="panel-title ">Thêm danh mục</div>
			  			</div>
			  			<div class="content-box-large box-with-header">
				  			<div>
				  				<form action="${pageContext.request.contextPath}/admin/land/add" method="post" enctype="multipart/form-data">
								<div class="row mb-10"></div>
								
								<div class="row">
										
									<div class="col-sm-6">
										<div class="form-group">
											<label for="name">Tên </label>
											<input type="text" class="form-control" name="lname" placeholder="Nhập Tên">
											<form:errors path="land.lname" cssStyle="color:red"
												cssClass="error" />
										</div>
										
										<div class="form-group">
											<label>Danh mục</label>
											<select class="form-control" name="cat.cid">
						<c:choose >
						<c:when test="${not empty listCat }">
						<c:forEach  items="${listCat}" var="cat">
											   <option value="${cat.cid}">${cat.cname}</option>
							</c:forEach>
							</c:when>
						</c:choose>
											</select>
										</div>

										<div class="form-group">
											<label>Thêm hình ảnh</label>
											<input type="file" class="btn btn-default" name="file" id="exampleInputFile1">
											<p class="help-block"><em>Định dạng: jpg, png, jpeg,...</em></p>
										</div>
										
										<div class="form-group">
										   <label>Mô tả</label>
										   <textarea class="form-control" name="description" rows="3"></textarea>
										   <form:errors path="land.description" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Diện tích </label>
											<input type="text" class="form-control" name="area" placeholder="Nhập Diện tích">
											<form:errors path="land.area" cssStyle="color:red"
												cssClass="error" />
										</div>
										<div class="form-group">
											<label for="name">Địa Chỉ</label>
											<input type="text" class="form-control" name="address" placeholder="Nhập Địa Chỉ">
											<form:errors path="land.address" cssStyle="color:red"
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
							</div>
						</div>
			  		</div>
	  			</div><!-- /.row col-size -->
	  		
		  </div>