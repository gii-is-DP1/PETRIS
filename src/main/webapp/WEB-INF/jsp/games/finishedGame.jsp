<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  
<petclinic:layout pageName="gamesInProgress">

    <table id="gamesFinished" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <thead>
				<tr>
					<th style="width: 150px;">Game finished</th>
				</tr>
			</thead>
			<tbody>
					<thead>
						<tr>
							<th style="width: 150px;">Game: </th>
						</tr>
					</thead>
                    <tr>
                        <th style = "width: 1300px; right: 15%">ID </th>
                        <td style = " position: absolute; right: 20%" >
                            <c:out value="${game.id}"/>
                        </td>
                    </tr> 
					<tr>
                        <th style = "width: 1300px; right: 15%">Player 1 </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${game.player1.user.username}"/>
						</td>
					</tr>
					<tr>
                        <th style = "width: 1300px; right: 15%">Player 2 </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${game.player2.user.username}"/>
						</td>
					</tr> 
                    <tr>
                        <th style = "width: 1300px; right: 15%">Time </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${game.time} min"/>
						</td>
					</tr>     
                    <tr>
                        <th style = "width: 1300px; right: 15%">Winner </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${winnerUser.username}"/>
						</td>
					</tr>     
                    <tr>
                        <th style = "width: 1300px; right: 15%">Won/Lost points </th>
						<td style = " position: absolute; right: 20%" >
                            <c:if test="${pointOfTheGame>0}">
                                <c:out value="+${pointOfTheGame}"/>
                            </c:if>
                            <c:if test="${pointOfTheGame!=15}">
                                <c:out value="${pointOfTheGame}"/>
                            </c:if>
						</td>
					</tr>  
                    <tr>
                        <th style = "width: 1300px; right: 15%">Total points </th>
						<td style = " position: absolute; right: 20%" >
                            <c:out value="${user.points}"/>
						</td>
					</tr>     


        </tbody>
    </table>
</petclinic:layout>