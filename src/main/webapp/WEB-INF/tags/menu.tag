<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>
	

<nav class="navbar navbar-default" role="navigation">
	<div class="container2" style="padding-left: 200px; padding-right: 200px;">
		<div class="navbar-header">
			<a class="navbar-brand2" href="/users/userId"><img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="width: 170px;"></a>
                    <button2 class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button2>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/users/userId"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/users/userId/personalStatistics"
					title="personal statistics">
					<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
					<span>Estadisticas</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/users/userId/historial"
					title="historial">
					<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
					<span>Historial</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/users/userId/friends"
					title="friends">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<span>Amigos</span>
				</petclinic:menuItem>
			</ul>

			

			<ul class="nav navbar-nav navbar-right">

				<li>
				<form:form class="navbar-form" style="margin-top: 20px; right: 5px;" modelAttribute="user" action="/users/{userId}/findAll" method="get" 
                  id="search-owner-form">
				<div class="form-group input-group">
				<div class="control-group" id="username">
      			<form:input class="form-control" path="username" size="30" maxlength="80" placeholder="Search"/>
				<div class="input-group-btn">
      			<button class="btn btn-outline-success my-2 my-sm-0"  type="submit"><span class="glyphicon glyphicon-search"></span></button>
    			</div>
				</div>
				</div>
				</form:form>
				</li>

				

				

				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>

							


                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/users/userId/profile" />" class="btn btn-primary btn-block">Mi perfil</a>
												<a href="#" class="btn btn-danger btn-block">Change Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
