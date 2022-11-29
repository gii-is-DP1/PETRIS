<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="chat">
	<body>
		<h2 style="text-align: center">Chat</h2>
		<br>
		<div class="row">
			<c:forEach items="${chats}" var="chat">

				<div class="col-md-12">
					<div class="col-md-4"></div>
					<div class="col-md-4" style="margin-left: 50px;">
						<c:choose>
							<c:when test="${chat.user eq user }">
								<h1 style="color: green;">${user.username}:
									${chat.text}</h1>
							</c:when>
							<c:otherwise>
								<h1 style="color: red;">${chat.user.username}:
									${chat.text}</h1>

							</c:otherwise>
						</c:choose>

					</div>
					<div class="col-md-4"></div>

				</div>



			</c:forEach>
		</div>

		<div class="row">
			<form:form modelAttribute="NuevoMensaje" class="form-horizontal"
				id="add-user-form" action="/games/${gameId}/chat/save">

				<div class="col-md-10">
					<petclinic:inputField label="Escribe un nuevo mensaje" name="text" />
				</div>
				<div class="col-md-2">
					<button class="btn btn-default" type="submit">
						<span>Enviar</span>
					</button>
				</div>

			</form:form>
		</div>
</petclinic:layout>