<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profile">
    
    <h1 style = "width: 500px; position: absolute; right: 20%;">My profile</h1>

    <table id="profile" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <tr>
                <th  style = "width: 500px; position: absolute; right: 36%; bottom: 72%;">Username </th>
                <td style = "position: absolute; right: 36%;bottom: 72%">
                    <c:out value="${user.username}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 67%">Email </th>
                <td style = "position: absolute; right: 36%; bottom: 67%">
                    <c:out value="${user.email}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 62%">Password </th>
                <td style = "position: absolute; right: 36%; bottom: 62%">
                    <c:out value="${user.password}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 59.6%"></th>
            </tr>
        </tbody>
    </table>
    <div><form action="/users/${user.username}/edit" style="height: 70px; width: 500px; position: absolute; right: 19%; bottom: 50%;"><button class="button">Edit profile</button></form></div>
</petclinic:layout>
