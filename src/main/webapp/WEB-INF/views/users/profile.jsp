<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <h1>Profile</h1>
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal == null}">
         <b>Error:</b> not logged in!
        </c:when>
        <c:otherwise>
            <ul>
                <li><b>User name :</b>  ${pageContext.request.userPrincipal.name}</li>
                <li><b>Name :</b> ${pageContext.request.userPrincipal.name}</li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>