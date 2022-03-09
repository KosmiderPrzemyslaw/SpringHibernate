<%--
  Created by IntelliJ IDEA.
  User: przemysaw
  Date: 06.03.22
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${violations}" var="violation">
    ${violation.propertyPath} = ${violation.message} <br>
</c:forEach>
</body>
</html>
