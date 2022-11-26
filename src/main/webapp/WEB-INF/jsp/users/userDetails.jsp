<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h2>User Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.username}"/></b></td>
        </tr>
        <tr>
            <th>Points</th>
            <td><c:out value="${user.points}"/></td>
        </tr>
        <tr>
            <th>Played Games</th>
            <td><c:out value="${user.playedGames}"/></td>
        </tr>
        <tr>
            <th>Won Games</th>
            <td><c:out value="${user.wonGames}"/></td>
        </tr>
        <tr>
            <th>Lost Games</th>
            <td><c:out value="${user.lostGames}"/></td>
        </tr>
    </table>

    <c:if test="${usuActual != user}">

		<td><spring:url value="/users/${usuActual.username}/friends/friendRequest/{username1}/{username2}/save" var="amigoUrl">
			<spring:param name="username1" value="${usuActual.username}"/>
			<spring:param name="username2" value="${user.username}"/>
			</spring:url> <a href="${fn:escapeXml(amigoUrl)}" class="btn btn-default">Send invitation friend</a></td>
		</c:if>



    
    </table>

</petclinic:layout>
