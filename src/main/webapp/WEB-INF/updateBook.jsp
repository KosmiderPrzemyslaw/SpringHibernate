<%--
  Created by IntelliJ IDEA.
  User: przemysaw
  Date: 07.03.22
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Update Book</title>
</head>
<body>
<form:form method="post" modelAttribute="book">
    Select book to update:
    <form:select path="id" items="${bookList}" itemValue="id" itemLabel="title"/>
    <br>
    Set new title:
    <input type="text" id="title" name="title">
    <form:errors path="title" cssClass="ui-state-error"/>
    <br>
    Set new author:
    <form:select path="author" itemValue="id" items="${authorList}"/>
    <br>
    Set new description:
    <input type="text" id="description" name="description">
    <form:errors path="description" cssClass="ui-state-error"/>
    <br>
    Set new rating between 1-10:
    <input type="number" max="10" min="1" id="rating">
    <form:errors path="rating" cssClass="ui-state-error"/>
    <br>
    Set new Publisher:
    <form:select path="publisher" itemValue="id" items="${publisherList}"/>

    <br>
    Set pages number
    <input type="number" min="1" id="pages">
    <form:errors path="pages"/>
    <br>
    <input type="submit">
</form:form>
</body>
</html>
