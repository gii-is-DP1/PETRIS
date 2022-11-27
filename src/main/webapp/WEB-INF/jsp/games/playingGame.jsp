<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<game:layout pageName="playingGame">


    <h2><fmt:message key="welcome"/></h2>
    
    <p>	

    <div class="row">
        <div class="col-md-12">
            <game:board petrisBoard="${petrisBoard}">
            </game:board>
        </div>
    </div>
</game:layout>
