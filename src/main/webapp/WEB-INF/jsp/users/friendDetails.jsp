<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="friendDetails">
    
    <h1 style = "width: 500px; position: absolute; right: 28%;">Detalles del amigo</h1>

    <table id="friendDetails" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <tr>
                <th  style = "width: 500px; position: absolute; right: 36%; bottom: 72%;">Nombre</th>
                <td style = "position: absolute; right: 36%;bottom: 72%">
                    <c:out value="${friend.username}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 67%">Partidas jugadas </th>
                <td style = "position: absolute; right: 36%; bottom: 67%">
                    <c:out value="${friend.playedGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 62%">Partidas ganadas </th>
                <td style = "position: absolute; right: 36%; bottom: 62%">
                    <c:out value="${friend.wonGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 57%">Partidas perdidas </th>
                <td style = "position: absolute; right: 36%; bottom: 57%">
                    <c:out value="${friend.lostGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 52%">Puntos totales </th>
                <td style = "position: absolute; right: 36%; bottom: 52%">
                    <c:out value="${friend.points}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 47%">Win rate </th>
                <td style = "position: absolute; right: 36%; bottom: 47%">
                    <c:out value="${wr}"/>
                </td>
            </tr>
        </tbody>
    </table>
</petclinic:layout>
