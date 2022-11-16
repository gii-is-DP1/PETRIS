<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="games">
    <div class="inicio">
        <html>
            <head>
            <meta charset="utf-8">
            <title>PETRIS - Play Game</title>
            <style> 
               body
               {
                background-image: url('https://fondosmil.com/fondo/3585.jpg');
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                background-attachment: fixed;
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
               
            </style>   
            </head>
            <body>

                <form action="/games/create/{username}"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Create game</button></form>
                <form action="/games/join/private"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">Join private game</button></form>
                <form action="/games/join/public"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">Join public game</button></form>

            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</petclinic:layout>