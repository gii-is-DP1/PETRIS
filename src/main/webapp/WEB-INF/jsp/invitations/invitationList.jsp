<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="invitacionesDeAmistad">
	<body>
		<h2>Solicitudes de Amistad</h2>

		<table id="invitacionesAmistadTable" class="table table-striped">
			<tbody>
				<c:forEach items="${request}" var="invitacion">
					<tr>
						<td>${invitacion.user1.username} quiere agregarte como
							amigo!.</td>
						<td><form:form modelAttribute="invitacion"
								class="form-horizontal" id="add-invitation-form"
								action="/users/${user.username}/friends/friendRequest/${invitacion.id}/accept">
								<div class="form-group">
									<div class="col-sm-offset-2">
										<button class="btn btn-success" type="submit">Aceptar</button>
									</div>
								</div>
							</form:form></td>
						<td><form:form modelAttribute="invitacion"
								class="form-horizontal" id="add-invitation-form"
								action="/users/${user.username}/friends/friendRequest/${invitacion.id}/decline">
								<div class="form-group">
									<div class="col-sm-offset-2">
										<button class="btn btn-danger" type="submit">Rechazar</button>
									</div>
								</div>
							</form:form></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
</petclinic:layout>
</body>
