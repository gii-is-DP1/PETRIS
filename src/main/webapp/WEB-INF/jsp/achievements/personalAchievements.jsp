<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>Achievements</h2>

    <ul>
        <c:forEach items="${achievements}" var="achievement">
            <li>               
                    <c:out value="${achievement.name}"/>
                    <c:if test="${achievement.badgeImage == ''}">none</c:if>
                    <c:if test="${achievement.badgeImage != ''}">
                        <img src="${achievement.badgeImage}" width="100px"  /> 
                    </c:if>
            </li>                                
        </c:forEach>
    </ul>    
    <a class="btn btn-default" href="/achievements">Available achievements</a>
</petclinic:layout>