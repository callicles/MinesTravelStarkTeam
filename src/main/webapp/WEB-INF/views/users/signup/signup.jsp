<%--
  Created by IntelliJ IDEA.
  User: Nicolas
  Date: 04/02/14
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.springframework.security.web.WebAttributes"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div class="span-10">
    <c:url var="usersUrl" value="/users"/>
    <form:form  method="post" modelAttribute="user">
        <fieldset>
            <legend>Sign Up :</legend>
            <p>
                <label for="username">User:</label>
                <br />
                <form:input id="username" path="username"/>
                <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId : "username",
                        widgetType : "dijit.form.ValidationTextBox",
                        widgetAttrs : { promptMessage : "Your username", required : true }}));
                </script>
            </p>
            <p>
                <label for="password">Password:</label>
                <br />
                <form:input type="password" id="password" path="password"/>
                <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId : "password",
                        widgetType : "dijit.form.ValidationTextBox",
                        widgetAttrs : { promptMessage : "Your password", required : true}}));
                </script>
            </p>
            <p>
                <label for="name">Name:</label>
                <br />
               <form:input id="name" path="name"/>
                <script type="text/javascript">
                    Spring.addDecoration(new Spring.ElementDecoration({
                        elementId : "name",
                        widgetType : "dijit.form.ValidationTextBox",
                        widgetAttrs : { promptMessage : "Your Name", required : true}}));
                </script>
            </p>

            <p>
                <button id="submit" type="submit">Sign In</button>
                <script type="text/javascript">
                    Spring.addDecoration(new Spring.ValidateAllDecoration({event : 'onclick', elementId : 'submit'}));
                </script>
            </p>
        </fieldset>
    </form:form>
</div>

