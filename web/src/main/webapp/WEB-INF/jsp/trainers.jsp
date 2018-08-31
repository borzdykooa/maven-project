<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
</head>
<body>
<div>
    <h3>List of trainers:</h3>
    <ol>
        <c:forEach items="${requestScope.trainers}" var="trainer">
            <h4>
                <li>${trainer.name}</li>
            </h4>
            <p>Language: ${trainer.language}</p>
            <p>Experience: ${trainer.experience} years</p>
        </c:forEach>
    </ol>
</div>
</body>
</html>
