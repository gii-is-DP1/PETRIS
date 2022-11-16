<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2>Users</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de usuario</th>
            <th>Friends</th>
            <th>Info</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="user">
            <tr>
                <td>
                    <spring:url value="/users/{userId}/{username}" var="userUrl">
                        <spring:param name="username" value="${user.username}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(userUrl)}"><c:out value="${user.username}"/></a>
                </td>
                <td>
                    <c:forEach var="friends" items="${user.friends}">
                        <c:out value="${friends.username} "/>
                    </c:forEach>
                </td>
                &nbsp;
                &nbsp;
                <td>
                <a href="${fn:escapeXml(userUrl)}"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                </td>    
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
