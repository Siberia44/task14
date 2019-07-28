<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="_token" content="{{ csrf_token() }}">

<form action="product" method="post">
    <select name="count">
        <option value="3">3</option>
        <option value="5">5</option>
        <option value="10">10</option>
    </select>
    <button type="submit">submit</button>
</form>

<div class="row">
    <c:forEach var="c" items="${allproducts}">
        <div class="card col-sm-4">
            <img alt="${c.productName}"
                 class="card-img-top"
                 src="${c.productImg}">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-placement="top" data-toggle="tooltip">
                    ${c.productName}</h5>
                <p class="card-text">Class: ${c.productInfo}</p>
                <h5>Price: ${c.productCost}</h5>
                <a name="buyItem" class="btn btn-primary" onclick="buyItem(event);" id=${c.id}>Buy</a>
            </div>
        </div>
    </c:forEach>
</div>

<script crossorigin="anonymous"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>
    $.ajaxSetup({ headers: { 'csrftoken' : '{{ csrf_token() }}' } });
</script>
<script>
    const buyItemButtons = $("[name='buyItem']");
    buyItemButtons.click(buyItem);
    function buyItem(event) {
        let carId = $(event.target).attr('id');
        $.post('./addToCart', { 'id': carId }).done(function(data) {
            window.location.reload();
        });
    }
</script>

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

<div>
    <c:if test="${productCount > maxCountOfItem}">
        <b> Pages :</b>
        <c:forEach begin="1"
                   end="${productCount/maxCountOfItem + (productCount % 3 == maxCountOfItem ? 0 : 1)}"
                   var="i">

            &#8199;<a href="product?page=${i -1}">
            <c:out value="${i}"/>
        </a>
        </c:forEach>
    </c:if>
    <c:if test="${2 ==0}">
        <a><b>not found</b></a>
    </c:if>
</div>