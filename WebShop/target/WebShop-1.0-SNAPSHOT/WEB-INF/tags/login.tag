<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <t:choose>
            <%--===========================================================================
            This way we define the ADMIN MENU.
            ===========================================================================--%>
            <t:when test="${empty sessionScope.user}">
                <!-- LOGIN -->
                <li class="nav-item">
                                    <a class="nav-link" href="/check-login">Registration</a>
                                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">SignIn</a>
                </li>
    </ul>
    </t:when>

    <t:otherwise>
        <li class="nav-item">
            <form action="logout" method="POST" class="form-inline my-2 my-lg-0">
                <input type="submit" class="btn btn-outline-light" value="Logout" />

            </form>
        </li>
        <li class="nav-item">
            <div class="d-flex align-items-center">
                <img class="user-icon ml-2" src="img?image=${sessionScope.user.image}">
                <p class="text-light bg-dark">
                    <c:out value="${sessionScope.user.name}" />
                </p>
            </div>
        </li>
    </t:otherwise>
    </t:choose>
</div>