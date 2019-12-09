<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Директории и файлы</title>
</head>
<body>

<spring:form action="/addToList" method="post" modelAttribute="addDirFormBean">
<p>
    <spring:input path="path"/>
    <spring:errors path="path" cssStyle="color: red"/>
    <input type="submit" value="Добавить в список">
</p>

<table>
    <thead>
    <tr>
        <th>Дата</th>
        <th>Базовая директория</th>
        <th>Директорий</th>
        <th>Файлов</th>
        <th>Суммарный размер файлов</th>
        <th></th>
    </tr>
    </thead>

    <c:forEach items="${addedDirectories}" var="directory">
        <tr>
            <td>${directory.date}</td>
            <td>${directory.path}</td>
            <td>${directory.numberOfDirectories}</td>
            <td>${directory.numberOfFiles}</td>
            <td>${directory.sizeOfFiles}</td>
            <th><button>Файлы</button></th>
        </tr>

    </c:forEach>

</spring:form>

</body>
</html>
