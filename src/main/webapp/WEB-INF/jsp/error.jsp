<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html>
<head>
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
		
	 </style>   
</head>

<body>
	<div align="center" style="margin-left: 50px; margin-top: 40px;">

		<spring:url value="/resources/static/resources/images/FotoError.jpg"
			htmlEscape="true" var="fotoPetris" />
		<img id="f1" class="img-responsive"
			style="border: 2px solid; border-radius: 15px; color: #E8CEF5;"
			src="${fotoPetris}" width="600" height="400" />
	</div>

	<br></br>

	<h2 align="center">ERROR ${error}, ${status}</h2>
		<br>
	<h4 align="center">Algo ha ocurrido, mira el mensaje superior para recibir más información</h4>

</body>
</html>