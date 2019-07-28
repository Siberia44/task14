<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="pref" tagdir="/WEB-INF/tags" %>


<div id="shoppingCart">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Beer</th>
            <th>Price</th>
            <th class="hidden-print">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${shoppingCart.products}">
            <tr id="product${item.product.id }" class="item">
                <td class="text-center"><img class="small" src="${item.product.productImg}"
                                             alt="${item.product.productType}"><br>${item.product.productName}
                </td>
                <td class="price">${item.product.productCost}</td>
                <td class="count"><input type="number" id="${item.product.id}" name="editItemQuantityInput" value="1"
                                         min="1" max="10"></td>
                <td class="hidden-print">
                    <button class="btn btn-danger " name="deleteItem" data-id-product="${item.product.id}"> Remove</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2" class="text-right"><strong>Total</strong></td>
            <td colspan="2" class="total" id="totalCost"> ${shoppingCart.totalCost}</td>
        </tr>
        </tbody>
    </table>
    <div class="row hidden-print">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <c:choose>
                <c:when test="${user != null}">
                    <form action="${pageContext.request.contextPath}/order" method="POST">
                        <div class="form-group">
                            <label for="creditCardNum">Credit card number</label>
                            <input type="text" id="creditCardNum" name="creditCardNum" value="${order.creditCard}">
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" id="address" name="address" value="${order.address}" required>

                        </div>
                        <button type="submit" class="btn btn-success">Make order</button>
                    </form>

                </c:when>
                <c:otherwise>
                    <li>
                        <a class="btn btn-danger" href="registration.jsp">SignUp</a>
                    </li>
                    <li>
                        <a class="btn btn-danger" href="login.jsp">SignIn</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>