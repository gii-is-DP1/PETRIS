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
                font-size: 18px;
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
        	
            <div class="row">
            <div class="col-md-12">
                <game:board petrisBoard="${petrisBoard}">
                    <c:forEach items="${petrisBoard.tokens}" var="token">
                        <game:token size="100" token="${token}"/>            	
                    </c:forEach>
                </game:board>
            </div>
        </div>
            <h1 class="h1" style="height: 62px; width: 200px; position: absolute; right: 3%; bottom: 80%; font-size: 26px;text-align:center;">Game Code: ${code}</h1>
            <form action="/games/${game.id}/endTurn"><button class="button" style="font-size: 18px; height: 65px; width: 80px; position: absolute; right: 23%; bottom: 77%;">End Turn</button></form>
        
        <form style="position:absolute; right: 4%; bottom: 40%;">
            Movement
            <br>
            Space 1:
            <br>
            <input type="text" name = "space1Id" value="" >
            <br><br>
            
            Space 2:
            <br>
            <input type="text" name = "space2Id" value="" >
            <br><br>
            Num Bacteria to move:
            <br>
            <input type="text" name = "numBacteriaToMove" value="" >
            <br>
        <br>
        <div class="form-group">
            <div class="col-sm-offset-0 ">
                <button class="btn btn-default" type="submit">Make movement</button>
            </div>
        </div>
        </form>
    </body>
</game:layout>
