<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
    <c:url value="/resources/admin" var="contextPathAdmin" scope="application"/>
<!-- header -->
<!DOCTYPE html>
<html>
  <head>
    <title>VNE Login</title>
    <link rel="shortcut icon" type="image/ico" href="${contextPathAdmin}/images/icon-180x180.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${contextPathAdmin}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="${contextPathAdmin}/css/style1.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="index.html">VNE-Admin</a></h1>
	              </div>
	           </div>
	           <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12"></div>
	              </div>
	           </div>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="profile.html">Profile</a></li>
	                          <li><a href="login.html">Logout</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>
<!-- /.header -->
<form action="${pageContext.request.contextPath}/auth/login" method="post">
	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			            	<img width="100px" height="100px" class="img-circle" src="${contextPathAdmin}/images/icon-180x180.png">
			                <h6>????ng nh???p</h6>
			                
			                <div class="form-group">
			                	<label class="text-left pull-left" for="username">T??n ????ng nh???p</label>
			               		<input class="form-control" name="username" type="text" placeholder="Username">
			                </div>
			                
			                <div class="form-group">
			                	<label class="text-left pull-left" for="password">M???t kh???u</label>
			                	<input class="form-control" name="password" type="password" placeholder="Password">
			                </div>
			                
			                <div class="action">
			                	<input class="form-control" type="submit" value="Login">
			                    
			                </div>                
			            </div>
			        </div>

			        <div class="already">
			            <p>Don't have an account yet?</p>
			            <a href="javascript:void(0)">Sign Up</a>
			        </div>
			    </div>
			</div>
		</div>
	</div>
</form>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${contextPathAdmin}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPathAdmin}/js/custom.js"></script>
  </body>
</html>