<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <div class="inicio">
        <html>
            <head>
            <meta charset="utf-8">
            <title>Pantalla inicio</title>
            <style> 
               body
               {
                height: 400px;
                background-image: url('https://c4.wallpaperflare.com/wallpaper/458/849/290/nintendo-super-mario-nintendo-entertainment-system-controllers-wallpaper-preview.jpg');
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
                bottom: 35%;
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
               
            </style>   
            </head>
            <body>
                <c:forEach items="${selections}" var="player">
            <tr>
                <td>
                        <spring:param name="playerId" value="${player.id}"/>
                </td>

            </tr>
        </c:forEach>


                <form action="/game"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Jugar una partida</button></form>
                <form action=""><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">Historial</button></form>
                <form action=""><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">Espectador</button></form>
                <form action="/users/{userId}/personalStatistics"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 25%;">Estadisticas</button></form>
                <form action=""><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 35%;">Amigos</button></form>
                <!-- <img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="height: 150px; position: absolute; right: 35%; bottom: 80%;">-->


            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</petclinic:layout>
