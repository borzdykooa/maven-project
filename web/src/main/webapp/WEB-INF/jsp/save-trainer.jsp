<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h2>Save a new Trainer</h2>
<form action="${pageContext.request.contextPath}/saveTrainer" method="post">
    Name: <input type="text" name="name" value="${trainer.name}"><br>
    Language: <input type="text" name="language" value="${trainer.language}"><br>
    Experience: <input type="text" name="experience" value="${trainer.experience}"><br>
    <input type="submit" value="Save">
</form>
</body>
</html>
