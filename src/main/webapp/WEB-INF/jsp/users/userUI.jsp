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
                background-image: url('https://c4.wallpaperflare.com/wallpaper/458/849/290/nintendo-super-mario-nintendo-entertainment-system-controllers-wallpaper-preview.jpg');
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

              
              <c:if test="${au.authority == 'admin'}">
                <form action="/games/playing"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 25%;">Games in progress</button></form>
                <form action="/games/finished"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 15%;">Finished games</button></form>
                <form action="/registeredUser"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 5%;">Registered users</button></form>
              </c:if>




                <form action="/games"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Play</button></form>
                <form action="/games/spectate"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">Spectate</button></form>
                <form action="/users/${user.username}/ranking"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 35%;">Ranking</button></form>
                <form action="/video"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">How to play</button></form>
                <!-- <img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="height: 150px; position: absolute; right: 35%; bottom: 80%;">-->
                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


                <script>
                    window.setTimeout(function() {
                        $(".alert").fadeTo(1500, 0).slideDown(1000, function(){
                            $(this).remove();
                        });
                    }, 2000);
                </script>
                

            
            
            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</petclinic:layout>
