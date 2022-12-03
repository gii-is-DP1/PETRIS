<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  
<petclinic:layout pageName="gamesInProgress">

    <table id="gamesInProgress" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <thead>
				<tr>
					<th style="width: 150px;">Users</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="users">
					<thead>
						<tr>
							<th style="width: 150px;">User: </th>
						</tr>
					</thead>
					<tr>
                        <th style = "width: 1300px; right: 15%">Username </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${users.username}"/>
						</td>
					</tr>
					<tr>
                        <th style = "width: 1300px; right: 15%">Email </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${users.email}"/>
						</td>
					</tr> 
					<tr>
                        <th style = "width: 1300px; right: 15%">Enabled </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${users.enabled}"/>
						</td>
					</tr>            
				</c:forEach>


        </tbody>
    </table>
</petclinic:layout>