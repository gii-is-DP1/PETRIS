<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="amigos">
	<body style="background: url(/resources/images/bck-petris.png)">
		<h2>Lista de tus amigos</h2>
		<table id="amigosTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 150px;">Nombre de usuario</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${amigos}" var="amigo">
					<tr>
						<td>
							<p>
								<c:out value="${amigo.username}" />
							</p>
						</td>
						<td><spring:url value="/users/{userId}/delete/{username}"
								var="friendUrl">
								<spring:param name="username" value="${amigo.username}" />
							</spring:url> <a href="${fn:escapeXml(friendUrl)}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                &nbsp;
                                &nbsp;
                            <spring:url value="/users/{userId}/search/{username}"
								var="friendUrl">
								<spring:param name="username" value="${amigo.username}" />
							</spring:url> <a href="${fn:escapeXml(friendUrl)}"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a></td>

                            
					</tr>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>
</body>

