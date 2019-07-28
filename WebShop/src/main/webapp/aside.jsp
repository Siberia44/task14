<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-3 ">
            <form action="${pageContext.request.contextPath}/search" class="sticky-top p-2">
                <h3>Type</h3>
                <tag:productTypeTag type="${type}"/>

                <h3>Country</h3>
                <tag:productCountryTag country="${country}"/>

                <div class="form-group">
                    <label for="exampleInputEmail1">Min price</label>
                    <input class="input-number" id="exampleInputEmail1" name="minPrice" type="number">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Max price</label>
                    <input class="input-number" id="exampleInputPassword1" name="maxPrice" type="number">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Search for name</label>
                    <input class="input-number2" id="exampleInputPassword12" name="searchByName" type="text"
                           value="${searchForm.query}">
                </div>

                <button class="btn btn-primary" type="submit">Search</button>
                <form action="${pageContext.request.contextPath}/search" method="post">
                    <button class="btn btn-primary" type="submit">reset</button>
                </form>
            </form>

        </div>
    </div>
</div>