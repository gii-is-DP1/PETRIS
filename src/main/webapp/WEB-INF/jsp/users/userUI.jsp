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
                height: 800px;
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
    

    /* Busqueda de usuario */

    .flexsearch--wrapper {
	height: auto;
	width: auto;
	max-width: 100%;
	overflow: hidden;
	background: transparent;
	margin: 0;
	position: static;
}
	
.flexsearch--form {
	overflow: hidden;
	position: relative;
}
	
.flexsearch--input-wrapper {
	padding: 0 66px 0 0; /* Right padding for submit button width */
	overflow: hidden;
}

.flexsearch--input {
  width: 100%;
}

.flexsearch {
  padding: 0 25px 0 200px; /* Padding for other horizontal elements */
}

.flexsearch--input {
  -webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
 	height: 60px;
  padding: 0 46px 0 10px;
	border-color: #888;
  border-radius: 35px; /* (height/2) + border-width */
  border-style: solid;
	border-width: 5px;
  margin-top: 15px;
  color: #333;
  font-family: 'Helvetica', sans-serif;
	font-size: 26px;
	-webkit-appearance: none;
	-moz-appearance: none;
}
	
.flexsearch--submit {
  position: absolute;
	right: 0;
	top: 0;
	display: block;
	width: 60px;
	height: 60px;
  padding: 0;
  border: none;
	margin-top: 20px; /* margin-top + border-width */
  margin-right: 5px; /* border-width */
	background: transparent;
  color: #888;
  font-family: 'Helvetica', sans-serif;
  font-size: 40px;
  line-height: 60px;
}

.flexsearch--input:focus {
  outline: none;
  border-color: #333;
}

.flexsearch--input:focus.flexsearch--submit {
 	color: #333; 
}

.flexsearch--submit:hover {
  color: #333;
  cursor: pointer;
}

::-webkit-input-placeholder {
	color: #888;  
}

input:-moz-placeholder {
  color: #888
}


.h3 {
  float: left;
  margin: 25px;
  color: white;
  font-family: 'Helvetica', sans-serif;
  font-size: 45px;
  font-weight: bold;
  line-height: 45px;
  text-align: center;
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

                <form action="/games"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Jugar una partida</button></form>
                <form action=""><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">Espectador</button></form>
                <form action="/video"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">Como Jugar</button></form>
                <form action="/users/{userId}/find"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">Buscar usuario</button></form>
                <!-- <img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="height: 150px; position: absolute; right: 35%; bottom: 80%;">-->
                

            
            
            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</petclinic:layout>
