<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>



<petclinic:layout pageName="amigos">
	<body style="background: url(/resources/images/bck-petris.png)">
		<h2>Ranking list</h2>
		<table id="amigosTable" class="table table-striped">
            
			<thead>
				<tr>
					<th style="width: 150px;">Username</th>
                    <th>Points</th>
                    <th>Winrate</th>
				</tr>
               
            
			</thead>
			<tbody>
				<c:forEach items="${users}" var="users">
					<tr>
						<td>
							<p>
								<c:out value="${users.username}" />
							</p>
						</td>
                        <td>
                            <p>
                                <c:out value="${users.points}"/>
                            </p>
                        </td>
                        <td>
                            <p>
                                <c:out value="${users.wonGames/user.playedGames}"/>
                            </p>
                        </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</petclinic:layout>
</body>

