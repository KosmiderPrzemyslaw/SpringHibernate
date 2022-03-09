<%--
  Created by IntelliJ IDEA.
  User: przemysaw
  Date: 08.03.22
  Time: 22:04
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
<form action="/allBooks" method="post">
    <table border="solid black">
        <tr>
            <th>wydawca</th>
            <th>tytuł</th>
            <th>autorzy</th>
            <th>ocena</th>
            <th>opis</th>
            <th>utworzono</th>
            <th>aktualizacja</th>
        </tr>

        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>
                        ${book.publisher}
                </td>
                <td>
                        ${book.title}
                </td>
                <td>
                        ${book.authors}
                </td>
                <td>
                        ${book.rating}
                </td>
                <td>
                        ${book.description}
                </td>
                <td>
                    ${book.createdOn}
                </td>
                <td>
                    ${book.updatedOn}
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/book/add" >Dodaj książkę</a>
</form>
</body>
</html>
