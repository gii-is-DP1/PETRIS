<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>Available Achievements</h2>

    <table id="achievementsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>BadgeImage</th>
            <th>Threshold</th>
            <th>Metric</th>
            <c:if test="${au.authority == 'admin'}">
                <th></th>
                <th></th>
            </c:if>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${achievements}" var="achievement">
            <tr>
                <td>
                    <c:out value="${achievement.name}"/>
                </td>
                <td>                    
                      <c:out value="${achievement.actualDescription} "/>                                        
                </td>
                <td>                    
                    <c:if test="${achievement.badgeImage == ''}">none</c:if>
                    <c:if test="${achievement.badgeImage != ''}">
                        <img src="${achievement.badgeImage}" width="100px"  /> 
                    </c:if>
                </td>
                
                <td>       
                    <c:out value="${achievement.threshold} "/>
                </td>
            
                <td>       
                    <c:out value="${achievement.metric} "/>
                </td>
                <c:if test="${au.authority == 'admin'}">
                    <td> 
                        <a href="/achievements/${achievement.id}/edit"> 
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                        </a>       
                    </td>
                    <td> 
                        <a href="/achievements/${achievement.id}/delete"> 
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a>      
                    </td>
                </c:if>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${au.authority == 'admin'}">
        <a class="btn btn-default" href="/achievements/new">Create new achievement</a>
    </c:if>
</petclinic:layout>