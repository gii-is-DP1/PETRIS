<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<layout pageName="home">
    <div class="inicio">
        <html>
            <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

            <title>Pantalla inicio</title>
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
               
               
               
            </style>   
            </head>
            <body>
                    <br/>
                    <c:if test="${message != null}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" data-dismiss="alert">
                        <button2 type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;
                        </span></button2>
                        <strong><c:out value="${message}"></c:out></strong> Please login to play the game.<p style="font-size: 30px; position: absolute; right: 73.5%; bottom: -26%;">&#128516;</p>
                    </div>
                    </div>
                    </c:if>
        
                
                
                
                <form action="/login"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Log in</button></form>
                <form action="/users/new"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">Sign in</button></form>
                <form action="/video"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 45%;">How to play</button></form>
                <img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="height: 150px; position: absolute; right: 35%; bottom: 80%;">




                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


                <script>
                    window.setTimeout(function() {
                        $(".alert").fadeTo(1500, 0).slideDown(1000, function(){
                            $(this).remove();
                        });
                    }, 4000);
                </script>

            </body>
            
            
        </html>
      </div>

      

</layout>
