<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="editProfile">
    
    <h1 style = "width: 500px; position: absolute; right: 20%;">My profile</h1>

    <form modelAttribute="user" class="form-horizontal" id="update-user-form" action="/users/{userId}/edit" method="POST">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Email" name="user.email"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
            <input type="submit" name="submit" value="Confirmar">
        </div>
        </div>
    </form>
</petclinic:layout>