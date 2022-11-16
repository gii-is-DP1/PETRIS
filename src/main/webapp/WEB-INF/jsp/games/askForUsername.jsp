<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<petclinic:layout pageName="joingames">
    <head>
        <title>Form login</title>
    <style>
* {
    margin: 0px;
    padding: 0px;
}
body{
    height: 850px;
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;       
}
#contenedor1{
    background: #f9f7f7;
    width: 460px;
    height: 370px;
    margin: auto;
    margin-top: 100px;
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
        <div class="form-group has-feedback">
            Type your opponent username:
            <input type="text" name="opponentUserName">
        </div>
        <div class="form-group">
            <div class="col-sm-offset-0 ">
                <button class="btn btn-default" type="submit">Join Game</button>
            </div>
        </div>
    </body>
</petclinic:layout>