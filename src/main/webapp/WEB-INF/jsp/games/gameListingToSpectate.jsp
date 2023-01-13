<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  
<petclinic:layout pageName="publicGamesToSpectate">

    <table id="publicGamesToSpectate" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <thead>
				<tr>
					<th style="width: 150px;">Games in progress</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listGames.content}" var="game">
					<thead>
						<tr>
							<th style="width: 150px;">Game: </th>
						</tr>
					</thead>
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
                        <th><a href='<spring:url value="/games/spectate/${game.code}" htmlEscape="false"/>' target="_blank">Spectate</a></th>
						</td>
					</tr>
					<c:if test="${!listGames.last}">
      					<li class="next">
       					<a href="?page=${listGames.number+1}">Next &rarr;</a>
        				</li>
 					</c:if>
					<c:if test="${listGames.number!=0}">
						<li class="Back">
						 	<a href="?page=${listGames.number-1}">Back &rarr;</a>
					  	</li>
					</c:if>           
				</c:forEach>


        </tbody>
    </table>
</petclinic:layout>