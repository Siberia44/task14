
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="country" required="true" type="java.util.Collection" %>

<br>
<div class="form-group form-check">
    <c:forEach var="classes" items="${country}">
        <input type="checkbox" class="form-check-input" name="countries" value="${classes}" id="exampleCheck1">
        <label>${classes}</label>
    </c:forEach>
</div>
