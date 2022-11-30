<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="game" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<game:layout pageName="playingGame">
    <head>
    <style>   
        h1  
        {
        display: inline-block;
        cursor: pointer;
        text-align: center;	
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #4CAF50;
        border: none;
        border-radius: 15px;
        box-shadow: 0 9px #999;
       }
    </style>
    </head>
    <body>

    <h2><fmt:message key="welcome"/></h2>
    
    <p>	
        
        <div class="row">
        <div class="col-md-12">
            <game:board petrisBoard="${petrisBoard}">
                <c:forEach items="${petrisBoard.tokens}" var="token">
                    <game:token size="100" token="${token}"/>            	
                </c:forEach>
            </game:board>
        </div>
    </div>
        <h1 class="h1" style="height: 62px; width: 200px; position: absolute; right: 3%; bottom: 80%; font-size: 26px;text-align:center ;">Game Code: ${code}</h1>
    </body>
</game:layout>
