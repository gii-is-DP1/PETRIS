<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="editProfile">
    
    <h1 style = "width: 500px; position: absolute; right: 20%;">My profile</h1>

    <form:form modelAttribute="user" class="form-horizontal" id="update-user-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Email" name="user.email"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
        </div>
        <div class="form-group" style="bottom: 50%;">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" action="/users/{userId}/edit" method="POST" type="submit" style="height: 45px; width: 100px; position: absolute; right: 66%;">Confirm</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>