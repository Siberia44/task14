<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">

    <!-- Bootstrap CSS -->
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">

    <title>Hello, world!</title>
</head>

<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="registration.jsp">Registration</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
        </form>
    </div>
</nav>
<div class="container">
    <form id="registration-form" action="check-login" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleInputEmail1">Enter name</label>
            <input type="name" class="form-control" name="userName" id="input-Name" aria-describedby="nameHelp"
                   placeholder="Enter name">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Enter email</label>
            <input type="email" class="form-control" name="userEmail" value="${email}" id="inputEmail"
                   aria-describedby="nameEmail"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Enter password</label>
            <input type="password" class="form-control" name="userPassword" value="${password}" id="inputPassword"
                   aria-describedby="passwordHelp" placeholder="Enter password">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Confirm password</label>
            <input type="name" class="form-control" name="userConfirmPassword" value="${password}"
                   id="inputConfirmPassword"
                   aria-describedby="confirmPasswordHelp" placeholder="Confirm password">
        </div>

        <div>
            <input type="file" name="image" id="avatar" accept="image/x-png,image/gif,image/jpeg">
        </div>
        <info:captcha captchaId="${captchaId}" image="${pageContext.request.contextPath}/registerCaptcha.jpeg"/>
        <input type="text" id="captcha" name="captcha" placeholder="Numbers from picture">

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<form></form>
<script src="js/commonFunction.js"></script>
<script src="js/validation.js"></script>
<!--<script src="js/validationJquery.js"></script>-->
</body>

</html>