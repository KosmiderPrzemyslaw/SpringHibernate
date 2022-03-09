<%--
  Created by IntelliJ IDEA.
  User: przemysaw
  Date: 07.02.22
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Add book form</title>
</head>
<body>
<form:form modelAttribute="book" method="post">
    <label for="title">Tytuł książki:
        <input type="text" name="title" id="title">
        <form:errors path="title" cssClass="ui-state-error-text" element="div"/>
    </label>
    <br><label for="rating">Rating:
    <input type="number" id="rating" name="rating">
    <form:errors path="rating" cssClass="ui-state-error" element="span"/>
</label>
    <br><label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="5" cols="30">
        <form:errors path="description"/>
</textarea>
    <br>
    <label for="publisher" id="publisher">Wydawnictwo:
        <form:select path="publisher.id" itemValue="id" itemLabel="name" items="${publisherList}"/>
        <form:errors path="publisher"/>
    </label>
    <br>
    <label for="author" id="author">Autor:
        <form:select path="author" itemValue="id" items="${authorList}"/>
    </label>
    <br>
    <input type="submit">
</form:form>
</body>

</html>
