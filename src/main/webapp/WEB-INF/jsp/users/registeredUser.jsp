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
				<c:forEach items="${users.content}" var="usersp">
					<thead>
						<tr>
							<th style="width: 150px;">User: 
							<form action="/registeredUser/${usersp.username}"><button style="height: 5px; position: absolute; right: 25%;">Update</button></form>
							<form action="/registeredUser/delete/{usersp.username}"><button style="height: 5px; position: absolute; right: 20%;">Delete</button></form>
							&nbsp;
							</th>

						</tr>
					</thead>
					<tr>
                        <th style = "width: 1300px; right: 15%">Username </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${usersp.username}"/>
						</td>
					</tr>
					<tr>
                        <th style = "width: 1300px; right: 15%">Email </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${usersp.email}"/>
						</td>
					</tr> 
					<tr>
                        <th style = "width: 1300px; right: 15%">Enabled </th>
						<td style = " position: absolute; right: 20%" >
							<c:out value="${usersp.enabled}"/>
						</td>
					</tr>  
					          
				</c:forEach>
				<c:if test="${!users.last}">
      					<li class="next">
       					<a href="?page=${users.number+1}">Next &rarr;</a>
        				</li>
 				</c:if>
				<c:if test="${users.number!=0}">	
						<li class="Back">
						 	<a href="?page=${users.number-1}">Back &rarr;</a>
					  	</li>
				</c:if>


        </tbody>
    </table>
</petclinic:layout>