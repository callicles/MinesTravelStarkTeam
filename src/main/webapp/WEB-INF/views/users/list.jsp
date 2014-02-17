<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Users list: </h1>

<div id="usersResults">
    <table class="summary">
        <thead>
        <tr>
            <th>Name</th>
            <th>User name</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${not empty usersList}">
                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.username}</td>
                        <!--<td><ul><c:forEach var="role" items="${user.roles}">
                            <li>${role}</li>
                        </c:forEach></ul></td>-->
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty usersList}">
                <tr>
                    <td colspan="5">No users found</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>