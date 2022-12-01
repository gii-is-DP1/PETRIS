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
            button
            {
             display: inline-block;
             padding: 15px 25px;
             font-size: 24px;
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

            button:hover {background-color: #3e8e41}

            button:active 
            {
             background-color: #3e8e41;
             box-shadow: 0 5px #666;
             transform: translateY(8px);
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

    <form action="/games/${gameId}/chat" target="_blank"><button class="button" style="height: 60px; width: 100px; position: absolute; right: 10%; bottom: 45%;">Chat</button></form>

    
        
    </body>
</game:layout>
