<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="login" method="post">
    <div class="form-group">
        <label>Name address</label>
        <input aria-describedby="nameHelp" class="form-control" name="userName" placeholder="Enter name" type="name">
        <small class="form-text text-muted" id="nameHelp">We'll never share your name with anyone else.</small>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input class="form-control" name="userPassword" placeholder="Password" type="password">
    </div>
    <div class="form-group form-check">
        <input class="form-check-input" id="exampleCheck1" type="checkbox">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <button class="btn btn-primary" type="submit">Submit</button>
</form>