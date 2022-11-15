<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  



<petclinic:layout pageName="record">

    <table id="record" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <thead>
				<tr>
					<th style="width: 150px;">Paridas Jugadas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${res}" var="res">
					<tr>
						<th style = "width: 1300px; right: 15%">Id de la partida </th>
						<td style = " position: absolute; right: 15%" >
							<c:out value="${res.id}"/>
						</td>
					</tr>
					<tr>
						<th>Ganador de la partida </th>
						<td style = "position: absolute; right: 15%">
							<c:out value="${res.winner}"/>
						</td>
					</tr>
					<tr>
						<th>Tiempo de la partida </th>
						<td style = "position: absolute; right: 15%">
							<c:out value="${res.time}"/>
						</td>
					</tr>
					<tr>
						<th>Ronda final de la partida </th>
						<td style = "position: absolute; right: 15%">
							<c:out value="${res.round}"/>
						</td>
					</tr>
					<tr>
						<th>Jugador A </th>
						<td style = "position: absolute; right: 15%">
							<c:out value="${res.player1.user.username}"/>
						</td>
					</tr>
					<tr>
						<th>Jugador B </th>
						<td style = "position: absolute; right: 15%">
							<c:out value="${res.player2.user.username}"/>
						</td>
					</tr>
				</c:forEach>
        </tbody>
    </table>
</petclinic:layout>