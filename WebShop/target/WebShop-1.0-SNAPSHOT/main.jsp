<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link href="css/icon.css" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">

    <title>Hello, world!</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="index.jsp">Home</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <div class="d-flex align-items-center">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="main.jsp">Books</a>
                </li>
                <li class="nav-item" ${shoppingCart.size==0 ?'hidden':''}>
                    <a class="nav-link" href="${pageContext.request.contextPath}/shopping-cart"> Shopping cart</a>
                </li>
                <t:login/>

            </ul>
        </div>

    </div>
    <div class="d-flex flex-end">
        <form action="logout" method="POST" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        </form>
    </div>
</nav>
<div class="container-fluid">
    <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
        <jsp:include page="/aside.jsp"/>
    </aside>
    <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
        <jsp:include page="${currentPage}"/>
    </main>
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
    const $editItemQuantityInputs = $("[name='editItemQuantityInput']");
    const $totalCost = $('#totalCost');
    $editItemQuantityInputs.change(changeItemQuantity);

    function changeItemQuantity(event) {
        let id = $(event.target).attr('id');
        let count = $(event.target).val();
        $.post("./shopping-cart", {id: id, count: count}).done(function (data) {
            $(event.target).parent().prev().text(data.itemPrice);
            $totalCost.text(data.totalPrice);
        });
    }

</script>

<script>
    const deleteButton = $("[name='deleteItem']");
    deleteButton.click(deleteItem);

    function deleteItem(event) {
        let Id = $(event.target).attr('data-id-product');
        $.post('./deleteFromCart', {'id': Id}).done(function (data) {
            window.location.reload();
        });
    }
</script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>

</html>