<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<!--
<layout pageName="home">
    <div class="inicio">
        <html>
            <head>
            <meta charset="utf-8">
            <title>Estadísticas personales</title>
            <style> 
               body
               {
                height: 400px;
                background-image: url('https://fondosmil.com/fondo/3585.jpg');
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
            
                
               }
               h1
               {
                color: black;
                text-align: center;
                font-family: "Franchise";
                font-size: 80px;
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
               h2{
                color: rgb(184, 0, 0);
                font-family: "Algerian";
                font-size: 60px;

               } 
               text{
                color: rgb(255, 255, 255);
                font-size: 20px;
                width: 90%;
                height: 40px;
                font-weight: bold;
                text-align: center;
               }   
               
            </style>   
            </head>
            <body>

                <table id="peronalStatistics" class="table table-striped">
                    <thead>
                        <h1>Estadisitcas personales</h1>
                    </thead>
                    <tbody>
                        <tr>
                            <th>Partidas jugadas: </th>
                            <td>
                                <c:out value="${user.playedGames}"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                

            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</layout>
-->

<petclinic:layout pageName="statistics">
    
    <h1 style = "width: 500px; position: absolute; right: 28%;">Personal statistics</h1>

    <table id="personalStatistics" class="table table-striped">
        <thead>
        </thead>
        <tbody>
            <tr>
                <th  style = "width: 500px; position: absolute; right: 36%; bottom: 72%;">Played games</th>
                <td style = "position: absolute; right: 36%;bottom: 72%">
                    <c:out value="${user.playedGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 67%">Won games</th>
                <td style = "position: absolute; right: 36%; bottom: 67%">
                    <c:out value="${user.wonGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 62%">Lost games </th>
                <td style = "position: absolute; right: 36%; bottom: 62%">
                    <c:out value="${user.lostGames}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 57%">Total points</th>
                <td style = "position: absolute; right: 36%; bottom: 57%">
                    <c:out value="${user.points}"/>
                </td>
            </tr>
            <tr>
                <th style = "width: 500px; position: absolute; right: 36%; bottom: 52%">Win rate </th>
                <td style = "position: absolute; right: 36%; bottom: 52%">
                    <c:out value="${wr}"/>
                </td>
            </tr>
        </tbody>
    </table>
</petclinic:layout>
