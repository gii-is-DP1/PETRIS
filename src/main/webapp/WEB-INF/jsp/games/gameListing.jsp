<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  
<petclinic:layout pageName="publicGames">

    <table id="publicGames" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <thead>
				<tr>
					<th style="width: 150px;">Active games</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${games}" var="game">
					<thead>
						<tr>
							<th style="width: 150px;">Game: </th>
						</tr>
					</thead>
					<tr>
                        <th style = "width: 1300px; right: 15%">Opponent </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${game.player1.user.username}"/>
						</td>
					</tr>
                    <tr>
						<th>Opponent points </th>
						<td style = "position: absolute; right: 20%">
							<c:out value="${game.player1.user.points}"/>
						</td>
					</tr>
                    <tr>
                        <th><a href='<spring:url value="/games/join/${game.code}" htmlEscape="false"/>' target="_blank">Join</a></th>
						</td>
					</tr>
                   
				</c:forEach>
        </tbody>
    </table>
</petclinic:layout>