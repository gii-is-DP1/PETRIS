
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="editProfileAdmin">
    
    <form:form modelAttribute="user1" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <input type="hidden" name="username" value="${user1.username}"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Password" name="password"/> 
            <input type="hidden" name="enabled" value="${user1.enabled}">     
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                 <button class="btn btn-default" type="submit">Update user</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>