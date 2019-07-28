<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="type" required="true" type="java.util.Collection" %>

<div class="form-group form-check">
    <c:forEach var="t" items="${type}">
        <input type="checkbox" class="form-check-input" name="type" value="${t}" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">${t}</label>
    </c:forEach>
</div>