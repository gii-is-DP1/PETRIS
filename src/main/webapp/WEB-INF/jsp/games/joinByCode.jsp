<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="joingames">

    <head>
        <title>Form login</title>
    <style>
body{
    height: 850px;
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    background-attachment: fixed; 
          
}
#contenedor1{
    background: #f9f7f7;
    width: 460px;
    height: 370px;
    margin: auto;
    margin-top: 100px;
    background-attachment: fixed; 
}
#form1{
    width: 100%;
    padding: 50px 20px 10px 20px;
    box-sizing: border-box;

}
#contenedor1 h1{
    text-align: center;
    padding-top: 20px;
    color: #566573;
    font-size: 45px;
}
#form1 input[type="text"]{
    border: none;
    background: none;
    width: 90%;
    height: 40px;
    font-size: 15px;
    font-weight: bold;
    text-align: center;
}
#form1 input[type="submit"]{
    width: 100%;
    height: 60px;
    background: #1b4f72;
    color: white;
    cursor: pointer;
    border: 2px solid white;
    font-size: 25px;
    font-weight: 900;
}
#form1 input[type="submit"]:hover{
    background: #3498db;
}
#form1 hr{
    margin-bottom: 40px;
    color: #aed6f1;
}
img{
    float: left;
}

    </style>
    </head>
    <body>
        <c:if test="${message != null}">
            <div class="alert alert-success alert-dismissible fade show" role="alert" data-dismiss="alert">
                 <button2 type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;
                </span></button2>
                <strong><c:out value="${message}"></c:out></strong><p style="font-size: 30px; position: absolute; right: 73.5%; bottom: -26%;"></p>
            </div>
        </c:if>
        <form >
                    Type game code:
                    <input type="text" name = "gameCode" value="">
                <br>

                <div class="form-group">
                    <div class="col-sm-offset-0 ">
                        <button class="btn btn-default" type="submit">Join Game</button>
                    </div>
                </div>
        </form>
    </body>
</petclinic:layout>