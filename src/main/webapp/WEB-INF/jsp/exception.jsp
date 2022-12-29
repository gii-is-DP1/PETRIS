<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <spring:url value="/resources/images/FotoError.jpg" var="FotoError" />
            <img src="${FotoError}" />
        </div>
        <div class="col-md-4"></div>
    </div>



    <br>
    <br>

    <h2 align="center">�Algo ha salido mal en el laboratorio!</h2>

    <p>${exception.message}</p>

</petclinic:layout>
