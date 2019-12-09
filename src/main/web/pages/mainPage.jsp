<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css" />
    <script type="text/javascript" src="resources/script/script.js"></script>
    <title>Директории и файлы</title>
</head>
<body>

<spring:form action="/addToList" method="post" modelAttribute="addDirFormBean">
    Новая директория: <spring:input path="path"  id="directory" />
    <input type="submit" value="Добавить в список">
    <p><spring:errors path="path" cssStyle="color: red"/></p>
</spring:form>

<table class="table_dirList">
    <caption>Список директорий и файлов</caption>
    <tr>
        <th>Дата</th>
        <th>Базовая директория</th>
        <th>Директорий</th>
        <th>Файлов</th>
        <th>Суммарный размер файлов</th>
        <th></th>
    </tr>
    <c:forEach items="${addedDirectories}" var="directory">
        <tr>
            <td>${directory.date}</td>
            <td>${directory.path}</td>
            <td>${directory.numberOfDirectories}</td>
            <td>${directory.numberOfFiles}</td>
            <td>${directory.sizeOfFiles}</td>
            <td><button id="btn" onclick="loadFiles(${directory.directoryId});printHeader('${directory.path} + ${directory.date}')">Файлы</button></td>
        </tr>
    </c:forEach>

    <div id="customModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close" onclick="closeModal()">&times;</span>
                <div id="header">
                    <h2>Шапка модального окна</h2>
                </div>
            </div>
            <div class="modal-body" id="modal-content">
                <p>Какой-то текст в теле модального окна</p>
                <p>Ещё другой текст...</p>
            </div>
        </div>
    </div>



<%--    --%>
<%--    <div id="customModal" class="modal">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <span class="close" onclick="closeModal()">&times;</span>--%>
<%--                <div id="header">--%>
<%--                <h2>Шапка модального окна</h2>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="modal-body" id="modal-content">--%>
<%--                <p>Какой-то текст в теле модального окна</p>--%>
<%--                <p>Ещё другой текст...</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

</body>
</html>
